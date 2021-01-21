package com.example.multimedia2018310072;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.example.multimedia2018310072.R;
import java.io.IOException;
import android.media.MediaPlayer;
import android.widget.MediaController;
import android.widget.VideoView;
import android.net.Uri;

public class VideoStreamingActivity extends AppCompatActivity {

    private static final String VIDEO_URL = "http://dedykuncoro.com/childrens-song/uploads/videos/japanese_childrens_song_-_okina_kuri_no_ki_no_shita_de.mp4";

    ProgressDialog pDialog;
    VideoView videoView;
    MediaController mediaController;
    Uri video;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_streaming);

        videoView = (VideoView) findViewById(R.id.v_video);
        videoStream();
    }

    private void videoStream() {
        //membuat progressbar nya
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Buffering");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        try {

            mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            // VideoURL
            video = Uri.parse(VIDEO_URL);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(video);
            videoView.requestFocus();
            //menutup progress dialognya dan play video
            videoView.setOnPreparedListener(mp -> {
                pDialog.dismiss();
                videoView.start();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}