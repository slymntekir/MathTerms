package com.tekir.suleyman.sayilar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Bolunebilme extends AppCompatActivity {
    private EditText edt_bol_sayi;
    private int sayi;
    private LinearLayout lnr_bol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolunebilme);

        lnr_bol = (LinearLayout) findViewById(R.id.lnr_bol);
        edt_bol_sayi = (EditText) findViewById(R.id.edt_bol_sayi);
    }


    public void bol_hesapla(View view) {
        try {
            if(!edt_bol_sayi.getText().toString().trim().isEmpty())
            {
                sayi = Integer.parseInt(edt_bol_sayi.getText().toString().trim());
                olustur();

                for(int i=2;i<=102;i++)
                {
                    if(lnr_bol.getChildAt(i-2) instanceof TextView &&
                            lnr_bol.getChildAt(i-2).getId() == i-1)
                    {
                        TextView textView = (TextView)lnr_bol.getChildAt(i-2);
                        if(sayi % i == 0) {
                            textView.setText(i+" ile tam bölünür.");
                            textView.setTextColor(Color.GREEN);
                        }
                        else if(sayi % i != 0) {
                            textView.setText(i+" ile tam bölünemez.");
                            textView.setTextColor(Color.RED);
                        }
                    }
                }
            }
            else if(edt_bol_sayi.getText().toString().trim().isEmpty())
            {
                Toast.makeText(getApplicationContext(),"Boş bıraktınız...", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Lütfen Geçerli Bir Sayı Giriniz",Toast.LENGTH_LONG).show();
        }
    }

    public void olustur()
    {
        lnr_bol.removeAllViews();
        for(int i=0;i<101;i++)
        {
            TextView txt = new TextView(this);
            txt.setId(i+1);
            txt.setTextColor(Color.BLACK);
            txt.setTextSize(27f);
            txt.setGravity(1);
            lnr_bol.addView(txt);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.action_cikis)
        {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){}
                }
            }).start();

            Intent ı = new Intent(getApplicationContext(),Anasayfa.class);
            startActivity(ı);
        }
        else if(id == R.id.action_cikis1)
        {
            finishAffinity();
        }
        return true;
    }
}