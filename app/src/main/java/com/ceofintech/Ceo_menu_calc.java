package com.ceofintech;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Ceo_menu_calc extends AppCompatActivity {
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceo_menu_calc);
        btn_1 = findViewById(R.id.ceo_mc01);
        btn_2 = findViewById(R.id.ceo_mc02);
        btn_3 = findViewById(R.id.ceo_mc03);
        btn_4 = findViewById(R.id.ceo_mc04);
        btn_5 = findViewById(R.id.ceo_mc05);
        btn_6 = findViewById(R.id.ceo_mc06);
        btn_0 = findViewById(R.id.ceo_salir);
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
        finish();
        System.exit(0);
    }

}