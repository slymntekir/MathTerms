package com.tekir.suleyman.sayilar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Anasayfa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
    }

    public void asalCarpan(View view) {
        Intent ıntent = new Intent(Anasayfa.this,MainActivity.class);
        startActivity(ıntent);
    }

    public void aralarindaAsal(View view) {
        try {
            Intent ıntent = new Intent(Anasayfa.this,AralarindaAsalmi.class);
            startActivity(ıntent);
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void ebob(View view) {
        Intent ıntent = new Intent(Anasayfa.this,ebob.class);
        startActivity(ıntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cikis,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.action_cikis1)
        {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){}
                }
            }).start();

            finishAffinity();
            System.exit(0);
        }
        return true;
    }

    public void bolunebilme(View view) {
        startActivity(new Intent(getApplicationContext(),Bolunebilme.class));
    }

    public void altkumesayisi(View view) {
    }
    
    public void kok_alma(View view) {
    }

    public void kuvvet_alma(View view) {
    }
}
