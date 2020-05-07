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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the View that shows the numbers category and set a click listener on it
        TextView numbers = (TextView) findViewById(R.id.numbers);
        /** setOnClickListener() is the better way to deal with button/view clicks because it helps
         *  keep the code and presentation separate over the alternative way of adding the method to
         *  the xml's onclick field, using setOnClickListener() keeps it all Java (or probably Kotlin too if you were using that)
         *  setOnClickListener() is the way to do this programmatically
         *  if the listener is defined using the onClick attribute then the view only looks for a method with that name in its own host activity
         *  setting the listener programmatically means the button's behavior can be controlled from somewhere other than the host activity
         *  this becomes helpful when using fragments (mini activities) because then you can build reusable collections of
         *  views with the own lifecycle which can then be combined into activities
         *  fragments require OnClickListeners to control their buttons because they're not activities so they wouldn't be searched for listeners defined in onClick
         */
        numbers.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                /**intents: can be implicit or explicit
                 * IMPLICIT intents are for when you don't care which app component handles the intent, as long as they can
                 * for example, any camera can handle the intent in a social media app
                 * EXPLICIT intents are for when you know the exact app component that should handle the intent
                 * for example, when you click the numbers category from the main screen it should send an explicit
                 * intent to the numbers activity within this app, it doesn't make sense no send it anywhere else
                 * you almost never use explicit intents to open third party apps because you don't know what's on a user's device
                 * explicit intents are basically only used within an app because you know what's available
                 **/
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);

                // Start the new activity
                startActivity(numbersIntent);
            }
        });

        /**
         * How to Setup an EventListener
         * 1. define the event listener (and the custom behavior for when the event happens), this is like the floorplan
         * 2. create a new object instance of the event listener (using the constructor), this is like making the house
         * 3. attach the listener to the view
         */

        // Find the family view and set a click listener on it
        TextView family = (TextView) findViewById(R.id.family);
        //Step 3. attach the listener to the view, Step 2. create a new object instance of event listener
        family.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Step 3. define the event listener inline (and the custom behavior for when the event happens)
                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(familyIntent);
            }
        });

        // Find the View that shows the colors category and set a click listener on it
        TextView colors = (TextView) findViewById(R.id.colors);
        colors.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });

        // Find the View that shows the phrases category and set a click listener on it
        TextView phrases = (TextView) findViewById(R.id.phrases);
        phrases.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the phrases category is clicked on.
            @Override
            public void onClick(View view) {
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });
    }
}
