package com.rarebird.photonpack;

import com.dancingsasquatch.photonsacks.R;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
//import android.view.Menu; Ditto, see below comment.
//import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
//import android.widget.MediaController; We don't need controls for video
import android.widget.VideoView;

public class MainActivity extends Activity implements OnPreparedListener{
	VideoView vv;
	Uri uri;
	ImageView ii;

	
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
		//MediaController media_control = new MediaController(this);
	    //vv.setMediaController(media_control);
		// vv.setVisibility(View.GONE);
		ii.setVisibility(View.VISIBLE);
		vv.seekTo(0);

	    vv.setOnPreparedListener (new OnPreparedListener() {                    
	        @Override
	        public void onPrepared(MediaPlayer mp) {
	            mp.setLooping(true);
	        }
	    });
	    
	    
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int maskedAction = event.getActionMasked();
		//Log.d("VideoPosition", String.valueOf(vidPos));
		switch (maskedAction) {
		case MotionEvent.ACTION_DOWN:
			vv.setVisibility(View.VISIBLE);
			ii.setVisibility(View.GONE);
			if(!vv.isPlaying()) {
				vv.start();
			}
			break;
		case MotionEvent.ACTION_UP:
			// Stop Movie
			ii.setVisibility(View.VISIBLE);
		    vv.pause();
		    vv.seekTo(0);
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


}
