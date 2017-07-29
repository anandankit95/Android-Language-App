package com.example.android.miwok;

/**
 * Created by Ankit on 21/07/17.
 */

public class Word {
    private String mDefaultTranslation;
    private  String mMiwokTranslation;
    private int mAudioResourceId;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mImageResourceId = NO_IMAGE_PROVIDED;


    Word(String DefaultTranslation,String MiwokTranslation,int audioResourceId)
    {
        mDefaultTranslation=DefaultTranslation;
        mMiwokTranslation=MiwokTranslation;
        mAudioResourceId=audioResourceId;
    }
    Word(String DefaultTranslation,String MiwokTranslation,int ImageResourceId,int audioResourceID)
    {
        mDefaultTranslation=DefaultTranslation;
        mMiwokTranslation=MiwokTranslation;
        mImageResourceId=ImageResourceId;
        mAudioResourceId=audioResourceID;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public  String getmMiwokTranslation()
    {
        return  mMiwokTranslation;
    }

    public int getImageResourceId()
    {
               return mImageResourceId;
    }

    public boolean hasImage()
    {
              return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId()
    {
        return mAudioResourceId;
    }


}
