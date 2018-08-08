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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ebob extends AppCompatActivity {
    private Spinner spn_adet;
    private List<Integer> list;
    private LinearLayout linearLayout;
    int sayi1;
    List<Integer> sayi_liste;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebob);

        linearLayout = (LinearLayout) findViewById(R.id.lnr1);

        list = new ArrayList<>();
        doldur();
        sayi_liste = new ArrayList<>();
        button = new Button(this);

        spn_adet = (Spinner) findViewById(R.id.spn_adet1);
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>
                (this,R.layout.spinner1,list);
        arrayAdapter.setDropDownViewResource(R.layout.spinner1);
        spn_adet.setAdapter(arrayAdapter);
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

    public void tamam(View view) {
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
        button.setOnClickListener(click1);
        linearLayout.addView(textView);
        linearLayout.addView(button);
    }

    View.OnClickListener click1 = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            int en_kucuk_sayi = 0;
            int en_buyuk_sayi = 0;
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
                en_buyuk_sayi = sayi_liste.get(0);
            }

            for(int k=1;k<sayi_liste.size();k++) {
                if(sayi_liste.get(k) <= en_kucuk_sayi)
                    en_kucuk_sayi = sayi_liste.get(k);
            }

            for(int k=1;k<sayi_liste.size();k++)
            {
                if(sayi_liste.get(k) > en_buyuk_sayi)
                    en_buyuk_sayi = sayi_liste.get(k);
            }

            Map<Integer,ArrayList<Integer>> map = new HashMap();

            //Toast.makeText(getApplicationContext(),""+en_kucuk_sayi,Toast.LENGTH_LONG).show();

            map.clear();
            for(int i=0;i<sayi;i++)
            {
                try {
                    if(linearLayout.getChildAt(i) instanceof EditText)
                    {
                        EditText edt = (EditText) linearLayout.getChildAt(i);
                        ArrayList<Integer> listt = new ArrayList<>();

                        int sayilarca = Integer.parseInt(edt.getText().toString().trim());
                        for (int k=2;k <= sayilarca ; k++)
                        {
                            if(sayilarca % k == 0) {
                                listt.add(k);
                            }
                        }
                        map.put(i+1,listt);
                    }
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            int ebob = 1;
            List<Integer> carpanlar = new ArrayList<>();
            carpanlar.clear();
            for(int i=en_kucuk_sayi;i >= 2;i--)
            {
                boolean durum = true;
                for(int j=0;j<map.size();j++)
                {
                    if(map.get(j+1).contains(i))
                    {
                        durum = durum && true;
                    }
                    else if(!map.get(j+1).contains(i))
                    {
                        durum = durum && false;
                    }
                }

                if(durum)
                {
                    ebob = ebob * i;
                    break;
                }
            }


            int carpim = 1;
            for(int y=0;y<sayi_liste.size();y++)
            {
                carpim = carpim * sayi_liste.get(y);
            }

            int ekok = 1,h;
            int carpan = carpim / en_buyuk_sayi;
            int min_ekok = 0;


            for(h = 1 ; h <= carpan ; h++)
            {
                boolean drm = true;
                min_ekok = en_buyuk_sayi * h;
                for(int r = 0 ; r < sayi_liste.size() ; r++)
                {
                    if(min_ekok % sayi_liste.get(r) == 0)
                        drm = drm && true;
                    else if(min_ekok % sayi_liste.get(r) != 0)
                        drm = drm && false;
                }
                if(drm)
                {
                    ekok = min_ekok;
                    break;
                }
            }
            if(h == carpan+1)
            {
                ekok = carpim;
            }

            for(int j=0;j<linearLayout.getChildCount();j++)
            {
                if(linearLayout.getChildAt(j) instanceof TextView && j == sayi)
                {
                    TextView tv = (TextView)linearLayout.getChildAt(j);
                    tv.setText("Ebob : "+ebob+"\n"+
                            "Ekok : "+ekok);
                    break;
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
            System.exit(0);
        }
        return true;
    }
}
