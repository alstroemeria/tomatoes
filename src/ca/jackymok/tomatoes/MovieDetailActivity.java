package ca.jackymok.tomatoes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;
import ca.jackymok.tomatoes.misc.Movie;

/**
 * An activity representing a single Movie detail screen. This activity is only
 * used on handset devices. On tablet-size devices, item details are presented
 * side-by-side with a list of items in a {@link MovieListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link MovieDetailFragment}.
 */
public class MovieDetailActivity extends Activity  {
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_detail);

		getActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra("movie");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(movie.getSynopsis());
        setTitle(movie.getTitle());
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this, new Intent(this,
					MainActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
