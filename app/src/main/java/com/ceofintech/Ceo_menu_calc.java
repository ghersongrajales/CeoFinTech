package com.ceofintech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ceo_menu_calc extends AppCompatActivity {
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;
    private Button bt6;
    private Button bt0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceo_menuc_calc);
        btn_1 = (Button)findViewById(R.id.ceo_mc_01);
        btn_2 = (Button)findViewById(R.id.ceo_mc_02);
        btn_3 = (Button)findViewById(R.id.ceo_mc_03);
        btn_4 = (Button)findViewById(R.id.ceo_mc_04);
        btn_5 = (Button)findViewById(R.id.ceo_mc_05);
        btn_6 = (Button)findViewById(R.id.ceo_mc_06);
        btn_0 = (Button)findViewById(R.id.ceo_salir);
    }

    public void Menu01 (View view) {
        Intent menu01 = new Intent ( this, Ceo_calc_mon_uvt.class);
        startActivity(menu01);
    }

    public void Menu02 (View view) {
        Intent menu02 = new Intent ( this, Ceo_calc_mon_uvr.class);
        startActivity(menu02);
    }

    public void Menu03 (View view) {
        Intent menu03 = new Intent ( this, Ceo_calc_mon_div.class);
        startActivity(menu03);
    }

    public void Menu04 (View view) {
        Intent menu04 = new Intent ( this, Ceo_calc_uvt_mon.class);
        startActivity(menu04);
    }

    public void Menu05 (View view) {
        Intent menu05 = new Intent ( this, Ceo_calc_uvr_mon.class);
        startActivity(menu05);
    }

    public void Menu06 (View view) {
        Intent menu06 = new Intent ( this, Ceo_calc_div_mon.class);
        startActivity(menu06);
    }

    public void Salir (View view) {
        getActivity().finish();
        System.exit(0);
    }

}