package com.example.android.miwok;
import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.AdapterView;



public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
            else if( focusChange==AudioManager.AUDIOFOCUS_GAIN)
            {
                mMediaPlayer.start();
            }

            else if( focusChange==AudioManager.AUDIOFOCUS_LOSS)
            {
                releaseMediaPlayer();
            }

        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer)
        {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("Two","ottiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("Three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("Four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("Five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("Six","temokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("Seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("Eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("Nine","wo ' e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("Ten","na ' accha",R.drawable.number_ten,R.raw.number_ten));



        WordAdapter adapter =new WordAdapter(this,words,R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
             {
                 releaseMediaPlayer();
                 // Get the {@link Word} object at the given position the user clicked on
                 Word word = words.get(position);
                 // Request audio focus so in order to play the audio file. The app needs to play a
                 // short audio file, so we will request audio focus with a short amount of time
                 // with AUDIOFOCUS_GAIN_TRANSIENT.
                 int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                         AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);


                 if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                     // We have audio focus now.
                     // Create and setup the {@link MediaPlayer} for the audio resource associated
                     // with the current word
                     mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                     mMediaPlayer.start();
                     mMediaPlayer.setOnCompletionListener(mCompletionListener);
                 }



             }
         });



    }
    @Override
    protected  void onStop()
    {
        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null)
        {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}
