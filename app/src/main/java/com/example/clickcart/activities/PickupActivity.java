package com.example.clickcart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.clickcart.R;

public class PickupActivity extends AppCompatActivity {

    Button confirm;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);

        toolbar = findViewById(R.id.pickup_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirm = findViewById(R.id.confirm_pickup);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PickupActivity.this, "Pick-Up Your Goods Within 2 Days!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PickupActivity.this,HomeActivity.class));
            }
        });
    }
}