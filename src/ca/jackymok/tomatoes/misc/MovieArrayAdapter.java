package ca.jackymok.tomatoes.misc;

import java.util.List;

import android.content.Context;
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
        //holder.subtitle.setText(entry.getCritics_consensus());
        
        return v;
    }
    
    
    
    private class ViewHolder {
        NetworkImageView image;
        TextView title; 
        //TextView subtitle;
        //ListView listview;
        
        public ViewHolder(View v) {
            image = (NetworkImageView) v.findViewById(R.id.iv_thumb);
            title = (TextView) v.findViewById(R.id.tv_title);
            //subtitle = (TextView) v.findViewById(R.id.tv_subtitle);
            //listview = (ListView) v.findViewById(R.id.lv_list);
            v.setTag(this);
        }
    }
}
