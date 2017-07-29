package com.example.android.miwok;


import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import java.util.ArrayList;
import android.support.v4.content.ContextCompat;

/**
 * Created by Ankit on 21/07/17.
 */

public class WordAdapter extends ArrayAdapter
{

    private int mColorResourceId;

    public WordAdapter(Context context, ArrayList<Word> words, int colorResourceId)
    {
        super(context,0, words);
        mColorResourceId=colorResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word my_word= (Word) getItem(position);
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(my_word.getmMiwokTranslation());
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(my_word.getDefaultTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.miwok_image_view);

        if (my_word.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(my_word.getImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);

        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);


        return listItemView;
    }

}

