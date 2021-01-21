package com.example.multimedia2018310072;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindBitmap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoSederhanaActivity extends AppCompatActivity {
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    MediaController mediaController;
    @BindView(R.id.v_video)
    VideoView vVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_sederhana);
        ButterKnife.bind(this);
        mediaController = new MediaController(this);
    }

    @OnClick({R.id.btn_playvideo})
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_playvideo:
                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videoplayback);
                vVideo.setVideoURI(uri);
                vVideo.setMediaController(mediaController);
                mediaController.setAnchorView(vVideo);
                vVideo.start();
                break;
        }
    }
}