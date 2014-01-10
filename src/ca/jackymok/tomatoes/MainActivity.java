package ca.jackymok.tomatoes;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
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
	    ArrayList<Movie> mEntries = new ArrayList<Movie>();
	    MovieArrayAdapter mAdapter;
	    LinearLayout linlaHeaderProgress ;

	    

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        //setContentView(R.layout.list_frag);
	        linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
	        mAdapter = new MovieArrayAdapter(this, 0, mEntries, MyVolley.getImageLoader());
	        mLvMovie = (ListView) findViewById(R.id.lv_movie);
	        mLvMovie.setAdapter(mAdapter);
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

	        linlaHeaderProgress.setVisibility(View.VISIBLE);
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
	        	        linlaHeaderProgress.setVisibility(View.GONE);
	                	         	
	                	List<Movie> movies = response.getMovies();
	                	Movie movie;
	                    for (int i = 0; i < movies.size(); i++) {
	                    	movie = (Movie) movies.get(i);
	                        
	                        mEntries.add(movie);
	                    }
	                    mHasData = true;
	                    mAdapter.notifyDataSetChanged();
	                } catch (Exception e) {
	                	Log.d("exception",e.toString());
	                    showErrorDialog();
	                }
	            }
	        };
	    }


	    private Response.ErrorListener createMyReqErrorListener() {
	        return new Response.ErrorListener() {
	            @Override
	            public void onErrorResponse(VolleyError error) {
	    	        linlaHeaderProgress.setVisibility(View.GONE);

                	Log.d("exception",error.toString());

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


	    
	   
	}