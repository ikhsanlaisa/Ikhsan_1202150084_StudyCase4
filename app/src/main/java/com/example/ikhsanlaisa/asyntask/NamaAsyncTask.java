package com.example.ikhsanlaisa.asyntask;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NamaAsyncTask extends AppCompatActivity {
    Handler handler = new Handler();
    int status = 0;
    private ProgressDialog mPD;
    private ListView lvItem;
    public String mahasiswa[] = new String[]{
            "Abay", "Lutfi", "Ujang", "Ikhsan", "Galuh", "Bregas",
            "Enpi", "Indra", "Yadi", "Jimmy", "Yoel"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nama_async_task);
        //mereferensikan object dengan id
        lvItem = findViewById(R.id.list_view);
        //mengeset array kedalam adapter
        lvItem.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
    }

    class MyAsyncTask extends AsyncTask<Void, String, String> {
        ArrayAdapter<String> adapter;

        @Override
        protected void onPreExecute() {
            //mengambil data array yang di simpan dalam sebuah adapter
            adapter = (ArrayAdapter<String>) lvItem.getAdapter();

            //membuat object progress dialog
            mPD = new ProgressDialog(NamaAsyncTask.this);
            //membuat button cencel
            mPD.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            //mengeset judul
            mPD.setTitle("Loading Data");
            //mengeset panjang maksimal loading
            mPD.setMax(mahasiswa.length);
            mPD.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            //melakukan pengulangan untuk memunculkan nama nama
            for (String Names : mahasiswa) {
                publishProgress(Names);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Semua nama telah tertulis";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //menambahkan nama tersebut dimulai dengat indeks ke nol
            adapter.add(values[0]);
            //increaments untuk loading di bar
            status++;
            //mengeset progres
            mPD.setProgress(status);
            //mengeset messagae dalam bentuk progress
            mPD.setMessage(String.valueOf(status));
            //kondisi jika progress sudah maksimal makan progress dialognya kek close
            if (status == mahasiswa.length){
                mPD.dismiss();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //membuat toast message
            Toast.makeText(NamaAsyncTask.this, result, Toast.LENGTH_LONG).show();
        }
    }

    public void Start(View view) {
        //membuat object MyAsyncTask
        MyAsyncTask mAT = new MyAsyncTask();
        //ekseskusi method MyAsyncTask
        mAT.execute();
    }
}
