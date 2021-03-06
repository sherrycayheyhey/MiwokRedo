package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

//Word adapter inherits (extends) from the ArrayAdapter class and this WordAdapter class is also
//customized to expect the data source to be a list of Word objects because we added <Word> to the class declaration
public class WordAdapter extends ArrayAdapter<Word> {

    /**resource ID for the background color of this list of words */
    private int mColorResourceId;
    private int mSoundResourceId;

    //constructor
    //the context is the current context, used to inflate the layout file
    //the list the the data wanted to populate into the lists, a list of Word objects to display in a list
    //the colorResourceId is the category color for the different activities
    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId, int soundResourceId) {
        //the constructor from ArrayAdapter is being called (super())
        //here the ArrayAdapter's internal storage for the context and the list are initialized
        //the second argument is used when the ArrayAdapter is populating a single TextView
        //since this is a custom adapter for 2 TextViews and an ImageView, the adapter isn't going
        //to use this second argument so it can be any any value (we chose 0)
        super(context, 0, words);
        mColorResourceId = colorResourceId;
        mSoundResourceId = soundResourceId;
    }

    //override the getView method so we can use more than one TextView to make populate the ListView

    /**
     * this provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        //find the TextView in the list_item.xml layout for the default (English) word
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        //get the default translation from the current Word object and set the text on the default TextView
        defaultTextView.setText(currentWord.getmDefaultTranslation());

        //find the TextView in the list_item.xml layout for the Miwok word
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        //get the default translation from the current Word object and set the text on the default TextView
        miwokTextView.setText(currentWord.getmMiwokTranslation());

        //find the ImageView in the list_item.xml layout for the artwork
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        //if there's an image, get the image from the current Word object and set the image on the ImageView
        if(currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getmImageResourceID());
            //make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        }
        //if there's not an image, set the visibility to GONE
        else {
            imageView.setVisibility(View.GONE);
            //INVISIBLE leaves the blank space for the image but GONE removes that space
        }

        //set the background color to the respective color
        View textContainer = listItemView.findViewById(R.id.text_container);
        //find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }

    /*
     * Custom Array Adapters
     * 1. create a way to store custom objects: in this case we used the "words" ArrayList (from the
     * NumbersActivity) which contains Word objects
     * 2. create a custom adapter that extends from ArrayAdapter and takes a custom object: in this case
     * we created the WordAdapter class which extended from the ArrayAdapter<Word>
     * 3. modify the getView() method in the custom adapter to provide a list item view for the ListView:
     * we modified it to get Word objects from the ArrayAdapter at the appropriate position and either
     * use that info to populate a recycled view or te populate a newly created view
     */
}
