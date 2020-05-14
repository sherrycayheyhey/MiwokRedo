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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        /* array vs ArrayList
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


        //ArrayList for list of numbers in English
        //ArrayList is the better structure here because more numbers might be added eventually

        ArrayList<String> words = new ArrayList<String>();
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");

        //the ListView is powered by the ArrayAdapter, the ListView is just an empty container without the ArrayAdapter
        //the ArrayAdapter holds on to an array or a list of data and knows how to translate (adapt)
        //it to a list item view that will be displayed in the ListView
        //here's the steps for how that works when a ListView is first associated to an ArrayAdapter:
        //1. the ListView asks the ArrayAdapter how many items to display
        //2. the ListView calls a method on the ArrayAdapter and gives it a position in the list (0, 1, 2, etc.)
        //3. the ArrayAdapter checks the internal source of data (the array, list, whatever), and gets the info out (the value stored at that index)
        //4. the arrayAdapter has instruction for how to make a list item view so it makes it with that item and returns it to the ListView
        //5. when the screen is full, the ListView will stop asking for more items from the ArrayAdapter (views are only created on-demand, when needed)
        //6. when views are scrolled off screen they are added to the scrap pile
        //7. the scrap pile views are reused by passing them back to the ArrayAdapter when a new list item is requested
        //8. the ListView asks for the item at the specific position and gives the ArrayAdapter the resusable scrap view
        //9. the ArrayAdapted reuses the view by just changing the text in it like by using setText method
        //10. the new list item is sent back to the ListView and added to the list

        //create an ArrayAdapter whose data source is a list of Strings
        //the adapter creates layouts for each item in the list by using the layout resource defined
        //in the Android framework (simple_list_item_1.xml)
        //this style layout contains a single TextView which the adapter will set to display a single word
        //the 3 things passed in here are the context (this = NumbersActivity), the layout file, and a list of objects
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
        //find the ListView object (ListView with id "list") from the activity_numbers.xml
        ListView listView = (ListView) findViewById(R.id.list);
        //make the listView use the ArrayAdapter created above so the ListView will display the list items
        //for each word in the list of words, aka, associate the ListView with the ArrayAdapter
        listView.setAdapter(itemsAdapter);

        //in summary, ArrayAdapter knows about the data source (array, list) and it knows how to
        //present each of the items in that data source as a view and the ListView handles showing
        //those views on screen, detects user touch gestures, and maintains state of where the user is within
        //the entire list, the ListView in charge of the user interface stuff side of stuff and the
        //ArrayAdapter is the boss of the data
        //since these guys are separated, you can swap out one part for another, for example,
        //you could use the ArrayAdapter in a GridView instead or even a spinner (dropdown menu)

        //GridLayoutManager is the newer version of GridLayout and a tutorial can be found here:
        //https://developer.android.com/guide/topics/ui/layout/recyclerview

    }
}
