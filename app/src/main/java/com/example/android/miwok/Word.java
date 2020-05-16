package com.example.android.miwok;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */

public class Word {

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    /**
     * Create a new Word object
     *
     * @param defaultTranslation is theword in a language that the user already knows
     *
     * @param miwokTranslation is the word in the Miwok language
     */
    public Word(String defaultTranslation, String miwokTranslation){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

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



}
