package com.example.multimedia2018310072;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_nav)
    BottomNavigationView navigationBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigationBottom.setOnNavigationItemSelectedListener(listener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
            switch (menuitem.getItemId()){
                case R.id.nav_audio_sederhana:
                    startActivity(new Intent(MainActivity.this, AudioSederhanaActivity.class));
                    break;
                case R.id.nav_audio_streaming:
                    startActivity(new Intent(MainActivity.this, AudioStreamingActivity.class));
                    break;
            }
            return false;
        }
    };
}