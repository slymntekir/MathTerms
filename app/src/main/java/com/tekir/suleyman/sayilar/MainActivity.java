package com.tekir.suleyman.sayilar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edt_sayi;
    private TextView txt_sonuc,txt_carpan,txt_pbs,txt_bln;
    private int sayi;
    private List<Integer> carpanlar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_sonuc = (TextView) findViewById(R.id.txt_sonuc);
        txt_carpan = (TextView) findViewById(R.id.txt_carpan);
        txt_pbs = (TextView) findViewById(R.id.txt_pbs);
        txt_bln = (TextView) findViewById(R.id.txt_bln);

        edt_sayi = (EditText)findViewById(R.id.edt_sayi);
        carpanlar = new ArrayList<>();

        txt_sonuc.setText("");
    }

    public void hesapla(View view) {
        sayi = Integer.parseInt(edt_sayi.getText().toString());
        txt_carpan.setText("");
        txt_pbs.setText("");
        txt_bln.setText("");
        int sonuc = 1;
        carpanlar.clear();
        StringBuilder sb = new StringBuilder();
        int sayac = 0;

        if(sayi < 0)
            txt_sonuc.setText("Lütfen Pozitif Sayı Giriniz..!");
        else if(sayi == 1 || sayi == 0)
        {
            txt_sonuc.setText("Sayı asal değil");
        }
        else if(sayi >= 2)
        {
            for(int i=1;i<=sayi;i++)
            {
                if(sayi % i == 0)
                    sayac++;
            }
            if(sayac == 2)
                txt_sonuc.setText("Sayı asaldır.");
            else if(sayac != 2)
                txt_sonuc.setText("Sayı asal değildir.");


            for(int i=2;i<=sayi;i++)
            {
                boolean durum = false;
                int sayac_1 = 0;
                while(sayi % i == 0)
                {
                    sayac_1++;
                    sayi = sayi/i;
                    if(!durum)
                        sb.append(i+" , ");
                    durum = true;
                }
                carpanlar.add(sayac_1+1);
            }
            txt_carpan.setText(sb.substring(0,sb.length()-2));
            sb = new StringBuilder();

            for(int i=0;i<carpanlar.size();i++)
            {
                sonuc *=carpanlar.get(i);
            }
            txt_pbs.setText(String.valueOf(sonuc));

            sayi = Integer.parseInt(edt_sayi.getText().toString());
            for (int k=1;k <= sayi ; k++)
            {
                if(sayi % k == 0)
                {
                    sb.append(k+" , ");
                }
            }
            txt_bln.setText(sb.substring(0,sb.length()-2));
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
}