package com.example.ikhsanlaisa.asyntask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    //membuat object
    private Button bName, bImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //mereferensikan object dengan id
        bName = findViewById(R.id.cari_nama);
        bImage = findViewById(R.id.cari_gambar);

        //fungsi ketika button di klik maka akan melakukan sebuah aktivitas
        bName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, NamaAsyncTask.class));
            }
        });

        //fungsi ketika button di klik maka akan melakukan sebuah aktivitas
        bImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, ImageAsyncTask.class));
            }
        });
    }
}
