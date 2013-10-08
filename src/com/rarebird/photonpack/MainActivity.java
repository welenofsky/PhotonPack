package com.rarebird.photonpack;

import java.io.IOException;

import com.dancingsasquatch.photonsacks.R;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends Activity implements OnPreparedListener, OnTouchListener{
	VideoView vv;
	ImageView ii;
	Uri uri;
	SoundPool soundPool;
	int play_off = 0;
	int shutdown_sound;
	int shooting_sound;
	int first_sound;
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Load video file on create
		uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pack_on);
		vv = (VideoView) findViewById(R.id.videoView1);
		ii = (ImageView) findViewById(R.id.PackOffOverlay);
		// try/catch this shit
		vv.setVideoURI(uri);
		ii.setVisibility(View.VISIBLE);
		vv.seekTo(001);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	    vv.setOnPreparedListener (new OnPreparedListener() {                    
	        @Override
	        public void onPrepared(MediaPlayer mp) {
	            mp.setLooping(true);
	        }
	    });
	    
	    try {
	    	AssetManager assetManager = getAssets();
	    	AssetFileDescriptor packshooting_descriptor = assetManager.openFd("game_stream_sound_enh.mp3");
	    	shooting_sound = soundPool.load(packshooting_descriptor, 1);
	    } catch (IOException e) {
	    	Log.d("MEDIA ERROR", "Couldn't Open MP3's");
	    }
	    soundPool.play(first_sound, 1, 1, 1, 0, 1);
	    	
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int maskedAction = event.getActionMasked();
		soundPool.stop(first_sound);
		switch (maskedAction) {
		case MotionEvent.ACTION_DOWN:
     		soundPool.play(shooting_sound, 1, 1, 1, 0, 1);
			vv.setVisibility(View.VISIBLE);
			ii.setVisibility(View.GONE);
			play_off = 1;
			if(!vv.isPlaying()) {
				vv.start();
			}
			break;
		case MotionEvent.ACTION_UP:
			ii.setVisibility(View.VISIBLE);
		    vv.pause();
		    vv.seekTo(0);
		    soundPool.stop(shooting_sound);
			break;
		}
		return true;
	}
	@Override 
	protected void onResume(){
		super.onResume();
	    vv.requestFocus();
	}
	public void playSounds() {
		// get oop son
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		ii.setVisibility(View.GONE);
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}


}
