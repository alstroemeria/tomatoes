package ca.jackymok.tomatoes.misc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ca.jackymok.tomatoes.R;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;


public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    private ImageLoader mImageLoader;
    
    public MovieArrayAdapter(Context context, 
                              int textViewResourceId, 
                              List<Movie> objects,
                              ImageLoader imageLoader
                              ) {
        super(context, textViewResourceId, objects);
        mImageLoader = imageLoader;
    }

    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.lv_movie_row, null);
        }
        
        ViewHolder holder = (ViewHolder) v.getTag(R.id.id_holder);       
        
        if (holder == null) {
            holder = new ViewHolder(v);
            v.setTag(R.id.id_holder, holder);
        }        
        
        Movie entry = getItem(position);
        if (entry.getPosters().getThumbnail() != null) {
            holder.image.setImageUrl(entry.getPosters().getThumbnail(), mImageLoader);
        } else {
            holder.image.setImageResource(R.drawable.no_image);
        }
        
        holder.title.setText(entry.getTitle());
        holder.subtitle.setText(entry.getCritics_consensus());
        
        int score = entry.getRatings().getCritics_score();
        if (score >0)
        {
	        StringBuilder actors = new StringBuilder();
	        
	        if (entry.getAbridged_cast().size()>1)
	        {
	        	actors.append(entry.getAbridged_cast().get(0).getName());
	        	actors.append(", ");
	        	actors.append(entry.getAbridged_cast().get(1).getName());
	        	actors.append(" | ");
	        }
	
	        if (entry.getRelease_dates()!=null){
	        	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	        	actors.append(df.format(entry.getRelease_dates().getTheater()));
	        }
	       
	        holder.status.setText(actors);
	        holder.rating.setText(entry.getRatings().getCritics_score()+"");
	        holder.rating.setTextColor(score>50? Color.parseColor("#ea6153"):Color.parseColor("#27ae60"));
        }
        else
        {
        	
        }
        return v;
    }
    
    
    
    private class ViewHolder {
        NetworkImageView image;
        TextView title; 
        TextView subtitle;
        TextView rating;
        TextView status;
        
        public ViewHolder(View v) {
            image = (NetworkImageView) v.findViewById(R.id.iv_thumb);
            title = (TextView) v.findViewById(R.id.title);
            subtitle = (TextView) v.findViewById(R.id.subtitle);
            rating = (TextView) v.findViewById(R.id.rating);
            status = (TextView) v.findViewById(R.id.status);
            v.setTag(this);
        }
    }
}
