/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        /* array vs ArrayList
         *
         * array: can't change size, isn't a class, can't use methods to access and modify elements,
         * stores primitive and object data types
         * ArrayList: changes size, is a class, uses methods to access and modify elements, stores object data types
         *
         *
         * Do Stuff with Arrays
         * create: String[] names = new String[2];
         * modify elements: names[0] = "Bob";, names[1] = "Charlie";
         * access elements: names[0];, names[1];
         * size: names.length;
         *
         * Do Stuff with ArrayList
         * create: ArrayList<String> names = new ArrayList<String>();
         * modify elemnts: names.add("bob");, names.add("Charlie");, names.remove("Charlie");
         * access element: names.get(0);, names.get(1);, does name.get("Charlie"); work too?
         * size: names.size();
         */


        //ArrayList for list of numbers in default (English) and Miwok
        //ArrayList is the better structure here because more numbers might be added eventually
        //create a list of words
        //words has to be final so that you can use it to get it in the onItemClickListener below
        final ArrayList<Word> words = new ArrayList<Word>();
        //add words to the list, these are Word objects being created
        words.add(new Word("one", "lutti", R.drawable.number_one,
                R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two,
                R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three,
                R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four,
                R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five,
                R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six,
                R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven,
                R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight,
                R.raw.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine,
                R.raw.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten,
                R.raw.number_ten));

        /* How ArrayAdapter and ListView Work (View Recycling, Too!)
         *
         * the ListView is powered by the ArrayAdapter, the ListView is just an empty container without the ArrayAdapter
         * the ArrayAdapter holds on to an array or a list of data and knows how to translate (adapt)
         * it to a list item view that will be displayed in the ListView
         *
         * here's the steps for how that works when a ListView is first associated to an ArrayAdapter:
         * 1. the ListView asks the ArrayAdapter how many items to display
         * 2. the ListView calls a method on the ArrayAdapter and gives it a position in the list (0, 1, 2, etc.)
         * 3. the ArrayAdapter checks the internal source of data (the array, list, whatever), and gets the info out (the value stored at that index)
         * 4. the arrayAdapter has instruction for how to make a list item view so it makes it with that item and returns it to the ListView
         * 5. when the screen is full, the ListView will stop asking for more items from the ArrayAdapter (views are only created on-demand, when needed)
         * 6. when views are scrolled off screen they are added to the scrap pile
         * 7. the scrap pile views are reused by passing them back to the ArrayAdapter when a new list item is requested
         * 8. the ListView asks for the item at the specific position and gives the ArrayAdapter the resusable scrap view
         * 9. the ArrayAdapted reuses the view by just changing the text in it like by using setText method
         * 10. the new list item is sent back to the ListView and added to the list
         */

        //create a WordAdapter whose data source is an ArrayList of Word objects, as created above, and named words
        //the adapter creates layouts for each item in the list by using the layout resource defined
        //in the list_item.xml file we made for a custom layout
        //this layout contains two TextViews (and an image) which the custom adapter (WordAdapter) will
        // set to display the two words (and image)
        //the 3 things passed in here are the context (this = NumbersActivity), the ArrayList of words, and the color resource
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers, R.raw.number_one);
        //find the ListView object (ListView with id "list") from the word_list.xml
        ListView listView = (ListView) findViewById(R.id.list);
        //make the listView use the ArrayAdapter created above so the ListView will display the list items
        //for each Word object in the ArrayList (words), aka, associate the ListView with the ArrayAdapter
        listView.setAdapter(adapter);

        //in summary, ArrayAdapter knows about the data source (array, list) and it knows how to
        //present each of the items in that data source as a view and the ListView handles showing
        //those views on screen, detects user touch gestures, and maintains state of where the user is within
        //the entire list, the ListView in charge of the user interface stuff side of stuff and the
        //ArrayAdapter is the boss of the data
        //since these guys are separated, you can swap out one part for another, for example,
        //you could use the ArrayAdapter in a GridView instead or even a spinner (dropdown menu)

        //GridLayoutManager is the newer version of GridLayout and a tutorial can be found here:
        //https://developer.android.com/guide/topics/ui/layout/recyclerview

        //the ArrayAdapter class expects that it'll be populating a single TextView by default but the
        //ListView we want to populate is more complex (it has two TextViews, the default translation
        //and the Miwok translation and eventually an ImageView) so we'll have to override the getView()
        //method with our own custom behavior and to do that we'll have to subclass the ArrayAdapter class
        //to do that, a new class, WordAdapter was created and modified to inherit behavior from the ArrayAdapter class


        /* STEPS TO PLAY A SOUND WHEN A LIST ITEM IS CLICKED
         *
         * 1. update the word class to store audio information for each word: add the sound part to
         *      both constructors and make a getter for retrieving the sound id
         *2. update each Word in the list of Word objects to include the sound id
         *3. create/update the OnItemClickListener to play the correct sound for the respective Word:
         *      get the position if the Word in the list, get the sound id of it by calling the getting in the Word class
         */


        //set the onItemClickListener so that audio plays when a list item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //get the {@link Word} object at the position the user clicked on
                Word word = words.get(position);

                //create and setup the {@link MediaPlayer} for the audio resource of the selected item
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmSoundResourceId());
                mMediaPlayer.start();
            }
        });
    }
}
