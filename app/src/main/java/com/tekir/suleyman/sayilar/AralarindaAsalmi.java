package com.tekir.suleyman.sayilar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AralarindaAsalmi extends AppCompatActivity {
    private Spinner spn_adet;
    private List<Integer> list;
    private LinearLayout linearLayout;
    int sayi1;
    List<Integer> sayi_liste;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aralarinda_asalmi);

        list = new ArrayList<>();
        linearLayout = (LinearLayout) findViewById(R.id.lnr);
        sayi_liste = new ArrayList<>();
        button = new Button(this);
        doldur();

        spn_adet = (Spinner) findViewById(R.id.spn_adet);
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>
                (this,R.layout.spinner1,list);
        arrayAdapter.setDropDownViewResource(R.layout.spinner1);
        spn_adet.setAdapter(arrayAdapter);
    }

    public void tamam(View view)
    {
        linearLayout.removeAllViews();

        sayi1 = Integer.parseInt(spn_adet.getSelectedItem().toString());

        for(int i=0;i<sayi1;i++) {
            EditText editText = new EditText(this);
            editText.setId(i+1);
            editText.setTextColor(Color.BLACK);
            editText.setTextSize(28f);
            editText.setGravity(1);
            editText.setHint( (i+1)+". sayiyi giriniz.");
            editText.setHintTextColor(Color.BLACK);
            linearLayout.addView(editText);
        }

        button.setText("Hesapla");
        button.setTextSize(25f);
        button.setTextColor(Color.BLACK);

        TextView textView = new TextView(this);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(28f);
        textView.setGravity(1);
        button.setOnClickListener(click);
        linearLayout.addView(textView);
        linearLayout.addView(button);
    }

    public void doldur()
    {
        list.clear();
        String[] s_list = getResources().getStringArray(R.array.adet);
        for(String s:s_list)
        {
            list.add(Integer.parseInt(s));
        }
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            int en_kucuk_sayi = 0;
            sayi_liste.clear();
            int sayi = Integer.parseInt(spn_adet.getSelectedItem().toString());

            for(int i=0;i<sayi;i++) {
                try {
                    if(linearLayout.getChildAt(i) instanceof EditText)
                    {
                        EditText edt = (EditText) linearLayout.getChildAt(i);
                        sayi_liste.add(Integer.parseInt(edt.getText().toString()));
                    }
                }
                catch (Exception e) {}
            }

            if(!sayi_liste.isEmpty()) {
                en_kucuk_sayi = sayi_liste.get(0);
            }

            for(int k=1;k<sayi_liste.size();k++) {
                if(sayi_liste.get(k) <= en_kucuk_sayi)
                    en_kucuk_sayi = sayi_liste.get(k);
            }

            int sayac = 0,m;
            boolean ddd = false;
            for(m=2;m<=en_kucuk_sayi;m++) {
                boolean durum = true;

                for (sayac = 0; sayac < sayi_liste.size(); sayac++) {
                    if (sayi_liste.get(sayac) % m == 0)
                        durum = durum && true;
                    else
                        durum = durum && false;
                }
                if(durum)
                {
                    for(int j=0;j<linearLayout.getChildCount();j++)
                    {
                        if(linearLayout.getChildAt(j) instanceof TextView && j == sayi)
                        {
                            TextView tv = (TextView)linearLayout.getChildAt(j);
                            tv.setText("Sayılar Aralarında Asal Değildir.");
                            break;
                        }
                    }
                    break;
                }

                ddd = durum;
            }
            if(!ddd && m == en_kucuk_sayi+1)
            {
                for(int j=0;j<linearLayout.getChildCount();j++)
                {
                    if(linearLayout.getChildAt(j) instanceof TextView && j == sayi)
                    {
                        TextView tv = (TextView)linearLayout.getChildAt(j);
                        tv.setText("Sayılar Aralarında Asaldır.");
                        break;
                    }
                }
            }
        }
    };

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
