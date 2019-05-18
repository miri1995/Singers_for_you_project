package com.example.myapplicationtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 3/14/2017.
 */

public class ArtistsListAdapter extends ArrayAdapter<Artist> {

    private static final String TAG = "ArtistsListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView name;
        TextView precent;

    }

    /**
     * Default constructor for the ArtistsListAdapter
     * @param context
     * @param resource
     * @param artists
     */
    public ArtistsListAdapter(Context context, int resource, List<Artist> artists) {
        super(context, resource, artists);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String name = getItem(position).getName();
        String precent = getItem(position).getPrecent();

        //Create the person object with the information
        Artist person = new Artist(name,precent);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView1);
            holder.precent = (TextView) convertView.findViewById(R.id.textView2);


            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }




        holder.name.setText(person.getName());
        holder.precent.setText(person.getPrecent());



        return convertView;
    }
}
