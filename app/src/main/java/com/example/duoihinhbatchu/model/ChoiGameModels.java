package com.example.duoihinhbatchu.model;

import android.widget.AbsListView;

import com.example.duoihinhbatchu.ChoiGameActivity;
import com.example.duoihinhbatchu.object.CauDo;

import java.util.ArrayList;

public class ChoiGameModels {
    ChoiGameActivity c;
    ArrayList<CauDo> arr;
    int cauSo=0;
    public ChoiGameModels(ChoiGameActivity c){
        this.c =c;
        taoData();
    }
    private  void taoData(){
        arr = new ArrayList<>();
        arr.add(new CauDo("Man 1","yeuot","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSb5mHD5Ss87BcXowNkZBG2yvzbMnTfD-8TMDM45p0vAw&s"));
    }
    public CauDo LayCauDo(){
        cauSo++;
        if(cauSo>=arr.size()){
            cauSo=arr.size()-1;
        }
        return arr.get(cauSo);
    }
}
