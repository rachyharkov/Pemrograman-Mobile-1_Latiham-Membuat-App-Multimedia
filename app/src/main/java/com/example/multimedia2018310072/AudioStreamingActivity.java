package com.example.multimedia2018310072;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.example.multimedia2018310072.R;
import java.io.IOException;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AudioStreamingActivity extends AppCompatActivity {

    @BindView(R.id.progress_bar_stream)
    ProgressBar progressBarStream;
    @BindView(R.id.btn_play_streaming)
    Button btnPlayStreaming;
    @BindView(R.id.btn_stop_streaming)
    Button btnStopStreaming;
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_streaming);

        ButterKnife.bind(this);

        progressBarStream.setVisibility(View.VISIBLE);
        progressBarStream.setIndeterminate(false);
        progressBarStream.setMax(100);

        prepareAudioStream();
        btnPlayStreaming.setEnabled(true);
        btnStopStreaming.setEnabled(false);

    }

    private void prepareAudioStream() {
        //masukan url Streaming, misalkan
        String url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
        mPlayer = new MediaPlayer();
        //menampilkan error tanpa crash dengan try
        try {
            mPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //membuat buffering
        mPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                progressBarStream.setIndeterminate(true);
                progressBarStream.setSecondaryProgress(100);
            }
        });
    }

    @OnClick({R.id.btn_play_streaming, R.id.btn_stop_streaming})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_play_streaming:
                //memfungsikan button play
                btnPlayStreaming.setEnabled(false);
                btnStopStreaming.setEnabled(true);
                progressBarStream.setVisibility(View.VISIBLE);
                progressBarStream.setIndeterminate(true);
                mPlayer.prepareAsync();
                mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        progressBarStream.setIndeterminate(false);
                    }
                });
                break;
            case R.id.btn_stop_streaming:
                //memfungsikan button stop
                if(mPlayer == null) return;
                if(mPlayer.isPlaying()){
                    btnPlayStreaming.setEnabled(true);
                    btnStopStreaming.setEnabled(false);
                    mPlayer.stop();
                    mPlayer.release();
                    prepareAudioStream();
                }
                break;
        }
    }
}