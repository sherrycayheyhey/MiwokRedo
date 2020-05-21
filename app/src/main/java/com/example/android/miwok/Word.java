package com.example.android.miwok;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */

public class Word {

    //********INSTANCE FIELDS********

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    /** id of the list item image, set to the no image state by default */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** image status */
    private static final int NO_IMAGE_PROVIDED = -1;


    //********CONSTRUCTORS********

    //one constructor has images and the other doesn't, make sure the activity classes call the correct one
    /**
     * Create a new Word object using two words (PhasesActivity class will use this)
     *
     * @param defaultTranslation is the word in a language that the user already knows
     *
     * @param miwokTranslation is the word in the Miwok language
     */
    public Word(String defaultTranslation, String miwokTranslation){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    /**
     * Create a new Word object using two words and and image (ColorsActivity, FamilyActivity, and NumbersActivity will use this)
     *
     * @param defaultTranslation is theword in a language that the user already knows
     *
     * @param miwokTranslation is the word in the Miwok language
     *
     * @param imageResourceId is the int drawable resource id for the associated image
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
    }


    //********METHODS********

    /**
     * get the default translation (the WordAdapter class calls this to get the word)
     */
    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * get the Miwok translation (the WordAdapter class calls this to get the word)
     */
    public String getmMiwokTranslation() {

        return  mMiwokTranslation;
    }

    /**
     * get the image id (the WordAdapter class calls this to get the image)
     */
    public int getmImageResourceID() {
        return mImageResourceId;
    }

    /**
     * returns whether the Word Object has an image
     */
    public boolean hasImage() {
        //is the image equal to the NO_IMAGE_PROVIDED constant?
        return mImageResourceId != NO_IMAGE_PROVIDED; //returns true if these is an image, false if there isn't
    }
}
