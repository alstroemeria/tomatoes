package ca.jackymok.tomatoes;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import ca.jackymok.tomatoes.app.MyVolley;
import ca.jackymok.tomatoes.misc.Movie;
import ca.jackymok.tomatoes.misc.MovieArrayAdapter;
import ca.jackymok.tomatoes.misc.Movies;
import ca.jackymok.tomatoes.toolbox.GsonRequest;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

public class SearchResultsActivity extends Activity {
	 private static final int RESULTS_PAGE_SIZE = 16;

	    private ListView mLvMovie;
	    private boolean mHasData = false;
	    private boolean mInError = false;
	    private ArrayList<Movie> mEntries = new ArrayList<Movie>();
	    private MovieArrayAdapter mAdapter;
	    String query = null;
	    int index = 1;
	    

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);

	        mLvMovie = (ListView) findViewById(R.id.lv_movie);
	        mAdapter = new MovieArrayAdapter(this, 0, mEntries, MyVolley.getImageLoader());
	        mLvMovie.setAdapter(mAdapter);
	        handleIntent(getIntent());
	        
	        mLvMovie.setOnScrollListener(new EndlessScrollListener());
	        mLvMovie.setOnItemClickListener(new OnItemClickListener() {

	            @Override
	            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
	                    long arg3) {
	                // TODO Auto-generated method stub
	                Intent intent = new Intent(getBaseContext(), MovieDetailActivity.class);
	                intent.putExtra("movie", mEntries.get(pos));
	                startActivity(intent);
	                Log.d("clicked on",mEntries.get(pos).getSynopsis() );
	            }

	        });
	    }

	    @Override
	    protected void onNewIntent(Intent intent) {
	        handleIntent(intent);
	    }
	    
	    private void handleIntent(Intent intent) {

	        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	            query = intent.getStringExtra(SearchManager.QUERY);
	            setTitle(query);
	        }
	    }

	    @Override
	    protected void onResume() {
	        super.onResume();

	        if (!mHasData && !mInError) {
	        	index = 1;
	            loadPage();
	        }
	    }


	    private void loadPage() {
	        RequestQueue queue = MyVolley.getRequestQueue();
	        
	        GsonRequest<Movies> myReq = new GsonRequest<Movies>(Method.GET,
	                                                "http://api.rottentomatoes.com/api/public/v1.0/movies.json?q="+ query +"&page_limit="+RESULTS_PAGE_SIZE+"&page="+index+++"&apikey=np8w2uaddk2bjan3b6fncefh",
	                                                Movies.class,
	                                                createMyReqSuccessListener(),
	                                                createMyReqErrorListener());

	        queue.add(myReq);
	    }


	    private Response.Listener<Movies> createMyReqSuccessListener() {
	        return new Response.Listener<Movies>() {
	            @Override
	            public void onResponse(Movies response) {
	                try {
	                	List<Movie> movies = response.getMovies();
	                	Movie movie;
	                    for (int i = 0; i < movies.size(); i++) {
	                    	movie = (Movie) movies.get(i);
	                        
	                        mEntries.add(movie);
	                    }
	                    mHasData = true;
	                    mAdapter.notifyDataSetChanged();
	                } catch (Exception e) {
	                    showErrorDialog();
	                }
	            }
	        };
	    }


	    private Response.ErrorListener createMyReqErrorListener() {
	        return new Response.ErrorListener() {
	            @Override
	            public void onErrorResponse(VolleyError error) {
	                showErrorDialog();
	            }
	        };
	    }


	    private void showErrorDialog() {
	        mInError = true;
	        
	        AlertDialog.Builder b = new AlertDialog.Builder(SearchResultsActivity.this);
	        b.setMessage("Error occured");
	        b.show();
	    }
	    
	    
	    /**
	     * Detects when user is close to the end of the current page and starts loading the next page
	     * so the user will not have to wait (that much) for the next entries.
	     * 
	    */
	    public class EndlessScrollListener implements OnScrollListener {
	        // how many entries earlier to start loading next page
	        private int visibleThreshold = 5;
	        private int currentPage = 0;
	        private int previousTotal = 0;
	        private boolean loading = true;

	        public EndlessScrollListener() {
	        }
	        public EndlessScrollListener(int visibleThreshold) {
	            this.visibleThreshold = visibleThreshold;
	        }

	        @Override
	        public void onScroll(AbsListView view, int firstVisibleItem,
	                int visibleItemCount, int totalItemCount) {
	            if (loading) {
	                if (totalItemCount > previousTotal) {
	                    loading = false;
	                    previousTotal = totalItemCount;
	                    currentPage++;
	                }
	            }
	            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
	                loadPage();
	                loading = true;
	            }
	        }

	        @Override
	        public void onScrollStateChanged(AbsListView view, int scrollState) {
	            
	        }
	        
	        
	        public int getCurrentPage() {
	            return currentPage;
	        }
	    }
	}