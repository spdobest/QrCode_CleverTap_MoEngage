package com.example.root.myvolleydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarAndGraphActivity extends AppCompatActivity {
    SeekBar customSeekbar;
    TextView progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar_and_graph);
        customSeekbar = (SeekBar) findViewById(R.id.seekBar1);
        progress = (TextView) findViewById(R.id.textView1);

        customSeekbar.setProgress(50);

        customSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


                progress.setText(" " + i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

}