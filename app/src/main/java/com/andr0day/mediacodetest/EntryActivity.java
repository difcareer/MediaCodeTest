package com.andr0day.mediacodetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.andr0day.mediacodetest.gl10.p1.G1P1Activity;
import com.andr0day.mediacodetest.gl10.p2.G1P2Activity;
import com.andr0day.mediacodetest.gl10.p3.G1P3Activity;
import com.andr0day.mediacodetest.gl10.p4.G1P4Activity;
import com.andr0day.mediacodetest.gl10.p5.G1P5Activity;
import com.andr0day.mediacodetest.gl10.p6.G1P6Activity;
import com.andr0day.mediacodetest.gl20.p1.G2P1Activity;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        (findViewById(R.id.btn1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntryActivity.this, MediaCodecActivity.class);
                startActivity(intent);
            }
        });

        (findViewById(R.id.btn2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntryActivity.this, MediaMuxerActivity.class);
                startActivity(intent);
            }
        });

        (findViewById(R.id.btn3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntryActivity.this, MediaPlayerActivity.class);
                startActivity(intent);
            }
        });

        (findViewById(R.id.G1P1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntryActivity.this, G1P1Activity.class);
                startActivity(intent);
            }
        });

        (findViewById(R.id.G1P2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntryActivity.this, G1P2Activity.class);
                startActivity(intent);
            }
        });

        (findViewById(R.id.G1P3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntryActivity.this, G1P3Activity.class);
                startActivity(intent);
            }
        });

        (findViewById(R.id.G1P4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntryActivity.this, G1P4Activity.class);
                startActivity(intent);
            }
        });

        (findViewById(R.id.G1P5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntryActivity.this, G1P5Activity.class);
                startActivity(intent);
            }
        });

        (findViewById(R.id.G1P6)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntryActivity.this, G1P6Activity.class);
                startActivity(intent);
            }
        });

        (findViewById(R.id.G2P1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntryActivity.this, G2P1Activity.class);
                startActivity(intent);
            }
        });
    }
}
