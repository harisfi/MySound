package com.hryzx.mysound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.SoundPool;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SoundPool sp;
    int soundId;
    boolean spLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSound = findViewById(R.id.btn_soundpool);

        sp = new SoundPool.Builder()
                .setMaxStreams(10)
                .build();
        sp.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
            if (status == 0) {
                spLoaded = true;
            } else {
                Toast.makeText(MainActivity.this, "Gagal load", Toast.LENGTH_SHORT).show();
            }
        });
        soundId = sp.load(this, R.raw.clinking_glasses, 1);

        btnSound.setOnClickListener(v -> {
            if (spLoaded) {
                sp.play(soundId, 1, 1, 0, 0, 1);
            }
        });
    }
}