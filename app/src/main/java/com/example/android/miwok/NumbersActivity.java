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

        /*//logs to check that the list was created properly
        Log.i("NumbersActivity", "Word at index 0: " + words.get(0));
        Log.i("NumbersActivity", "Word at index 1: " + words.get(1));
        Log.i("NumbersActivity", "Word at index 2: " + words.get(2));
        Log.i("NumbersActivity", "Word at index 3: " + words.get(3));
        Log.i("NumbersActivity", "Word at index 4: " + words.get(4));
        Log.i("NumbersActivity", "Word at index 5: " + words.get(5));
        Log.i("NumbersActivity", "Word at index 6: " + words.get(6));
        Log.i("NumbersActivity", "Word at index 7: " + words.get(7));
        Log.i("NumbersActivity", "Word at index 8: " + words.get(8));
        Log.i("NumbersActivity", "Word at index 9: " + words.get(9));*/

        //to make text show up as a list when the numbers activity is opened, 1. find the LinearLayout,
        //and then 2. create and add the TextViews to the LinearLayout
        //1. find the linear layout that was stored in rootView (the activity_numbers.xml)
        //specifically cast this view to a LinearLayout data type so that child views can be added to it
        //LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);
        //2. create and add TextViews to the LinearLayout
        /*//"this" refers to this class, which is NumberActivity so that's the context
        //create a TextView object and store it in a variable called wordView
        TextView wordView = new TextView(this);
        //change the text that is displayed on screen
        //words.get(0) maps to the word "one"
        wordView.setText(words.get(0));
        //add the view as a child (wordView) to the parent view (rootView)
        rootView.addView(wordView);

        TextView wordView2 = new TextView(this);
        wordView2.setText(words.get(1));
        rootView.addView(wordView2);*/

        //loop tip: if the counter starts at 0 then count < n will loop n times; count < 10 = 10 times because that bonus 0th element

        /*//loop to create and display a TextView for each element in the words list
        for (int i = 0; i < words.size(); i++) {
            //create a TextView object, store it in wordView variable
            TextView wordView = new TextView(this);
            //set the text that is displayed on the screen
            wordView.setText(words.get(i));
            //add the view as a child (wordView) to the parent view (rootView)
            rootView.addView(wordView);
        }*/

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

    }

    //view recycling (only showing 5 views at a time on the screen when there's like 1000 total)
    //view recycling = reusing views that are no longer visible on the screen anymore
    //view is the whole row, so like a contact in a phone book app
    //is a combination of ListView and adapter and makes sure apps use less memory
    //basically it costs a lot to inflate each view so you want to only create the ones that will be used
    //instead of actually creating a the view for each one at the start
    //it's a good idea to recycle views when there's a lot but better to just list them when it's a
    //fixed number that's kinda small (twitter vs a list of my 7 favorite pokemon)
    //good explanation of how ListView and ArrayAdapter work: https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView

}
