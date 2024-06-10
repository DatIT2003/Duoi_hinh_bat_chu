package com.example.duoihinhbatchu;


import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duoihinhbatchu.adpater.DapanAdapter;
import com.example.duoihinhbatchu.model.ChoiGameModels;
import com.example.duoihinhbatchu.object.CauDo;

import java.util.ArrayList;
import java.util.Random;

public class ChoiGameActivity extends AppCompatActivity {
    ChoiGameModels models;
    CauDo cauDo;
    private String dapAn="dalat";
    ArrayList<String> arrCauTraLoi;
    GridView gdvCauTraLoi;
    int index=0;
    ArrayList<String> arrDapAn;
    GridView gdvDapAn;
    GridView imgAnhCauDo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choi_game);
        init();
        anhXa();
        setOnClick();
        bamData();
        hienThiCauTraLoi();
        hienThiDapAn();
        hienCauDo();
    }
    private void anhXa(){
        gdvCauTraLoi = findViewById(R.id.gdvCauTraLoi);
        gdvDapAn = findViewById(R.id.gdvDapAn);
        imgAnhCauDo = findViewById(R.id.imgAnhCauDo);
    }
    private void  init(){
        models = new ChoiGameModels(this);
        arrCauTraLoi = new ArrayList<>();
        arrDapAn = new ArrayList<>();
    }
    private void hienCauDo(){
        cauDo = models.LayCauDo();
        dapAn=cauDo.dapAn;
        bamData();
        hienThiCauTraLoi();
        hienThiDapAn();


    }
    private void hienThiCauTraLoi(){
        gdvCauTraLoi.setNumColumns(arrCauTraLoi.size());
        gdvCauTraLoi.setAdapter(new DapanAdapter(this, 0, arrCauTraLoi));
    }
    private void hienThiDapAn(){
        gdvDapAn.setNumColumns(arrDapAn.size()/2);
        gdvDapAn.setAdapter(new DapanAdapter(this, 0, arrDapAn));
    }
    private void setOnClick(){
       gdvDapAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String s = (String) parent.getItemAtPosition(position);
               if (s.length()!=0 && index<arrCauTraLoi.size()){
                   if (arrCauTraLoi.get(index).length()!=0){
                       return;
                   }
                   for (int i=0;i<arrCauTraLoi.size();i++){
                       if (arrCauTraLoi.get(i).length()==0){
                           index =i;
                           break;
                       }
                   }
                   arrDapAn.set(position,"");
                   arrCauTraLoi.set(index,s);
                   index++;
                   hienThiCauTraLoi();
                   hienThiDapAn();
                   CheckWin();
               }
           }
       });
       gdvCauTraLoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String s = (String) parent.getItemAtPosition(position);
               if (s.length()!=0) {
                   index = position;
                   arrCauTraLoi.set(position, "");
                   for (int i = 0; i < arrDapAn.size(); i++) {
                       if (arrDapAn.get(i).length() == 0) {
                           arrDapAn.set(i, s);
                           break;
                       }
                   }
                   hienThiCauTraLoi();
                   hienThiDapAn();


               }
           }

       });
    }
    private void bamData(){
        arrCauTraLoi.clear();
        Random r = new Random();
        for(int i=0; i<dapAn.length();i++){
            arrCauTraLoi.add("");
            String s = ""+ (char) (r.nextInt( 26)+65);
            arrDapAn.add(s);
            String sl = ""+ (char) (r.nextInt( 26)+65);
            arrDapAn.add(sl);
        }
        for(int i=0; i<dapAn.length();i++){
            String s = ""+dapAn.charAt(i);
            arrDapAn.set(i,s.toUpperCase());
        }
        for (int i=0; i<10;i++){
            String s = arrDapAn.get(i);
            int vt = r.nextInt(arrDapAn.size());
            arrDapAn.set(i, arrDapAn.get(vt));// A B C D
            arrDapAn.set(vt,s);// C D A D
        }
    }
    private void CheckWin(){
        String s="";
        for (String sl: arrCauTraLoi){
            s=s+sl;
        }
        s=s.toUpperCase();
        if (s.equals(dapAn.toUpperCase())){
            Toast.makeText(this, "Ban da chien thang", Toast.LENGTH_SHORT).show();
            hienCauDo();
        }
    }
}