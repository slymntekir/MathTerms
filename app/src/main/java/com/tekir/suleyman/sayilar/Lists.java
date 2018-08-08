package com.tekir.suleyman.sayilar;

import java.util.ArrayList;

/**
 * Created by suleyman on 2/21/2018.
 */

public class Lists {
    private ArrayList<Integer> l;

    public Lists(ArrayList<Integer> ll) {
        l = new ArrayList<>();
        this.l=ll;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<l.size();i++)
        {
            sb.append(l.get(i)+" , ");
        }
        sb.append("\n");
        return sb.toString();
    }
}
