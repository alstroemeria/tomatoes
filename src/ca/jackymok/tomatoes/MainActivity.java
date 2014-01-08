package ca.jackymok.tomatoes;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import ca.jackymok.tomatoes.app.MyVolley;
import ca.jackymok.tomatoes.misc.Movie;
import ca.jackymok.tomatoes.misc.MovieArrayAdapter;
import ca.jackymok.tomatoes.misc.Movies;
import ca.jackymok.tomatoes.toolbox.GsonRequest;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {

	    private ListView mLvMovie;
	    private boolean mHasData = false;
	    private boolean mInError = false;
	    private ArrayList<Movie> mEntries = new ArrayList<Movie>();
	    private MovieArrayAdapter mAdapter;
	    

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);

	        mLvMovie = (ListView) findViewById(R.id.lv_movie);
	        mAdapter = new MovieArrayAdapter(this, 0, mEntries, MyVolley.getImageLoader());
	        mLvMovie.setAdapter(mAdapter);
	        mLvMovie.setOnItemClickListener(new OnItemClickListener() {
	            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

	            	Context context = getApplicationContext();
	            	CharSequence text = (CharSequence) mEntries.get(position).getTitle();
	            	int duration = Toast.LENGTH_SHORT;

	            	Toast toast = Toast.makeText(context, text, duration);
	            	toast.show();
	            }
	          });
	       // mLvMovie.setOnScrollListener(new EndlessScrollListener());
	    }

		@Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.options_menu, menu);
	        // Associate searchable configuration with the SearchView
	        SearchManager searchManager =
	               (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	        SearchView searchView =
	                (SearchView) menu.findItem(R.id.search).getActionView();
	        searchView.setSearchableInfo(
	                searchManager.getSearchableInfo(getComponentName()));
	        return true;
	    }

	    @Override
	    protected void onResume() {
	        super.onResume();

	        if (!mHasData && !mInError) {
	            loadPage();
	        }
	    }



	    private void loadPage() {
	        RequestQueue queue = MyVolley.getRequestQueue();

	        GsonRequest<Movies> myReq = new GsonRequest<Movies>(Method.GET,
	                                                "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?limit=16&country=ca&apikey=np8w2uaddk2bjan3b6fncefh",
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
	        
	        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
	        b.setMessage("Error occured");
	        b.show();
	    }
	    
	    
	    /**
	     * Detects when user is close to the end of the current page and starts loading the next page
	     * so the user will not have to wait (that much) for the next entries.
	     * 
	     
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
	                // I load the next page of gigs using a background task,
	                // but you can call any function here.
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
	    }*/
	}