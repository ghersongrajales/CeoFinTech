package com.ceofintech;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import android.os.Bundle;

public class Ceo_calc_uvt_mon extends AppCompatActivity {
    private Spinner spn_1;
    private Button btn_1;
    private Button btn_2;
    private TextView text_rdo;

    private EditText edit_valor_uno;
    private EditText edit_valor_dos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceo_calc_uvt_mon);

        edit_valor_uno = findViewById(R.id.ceo_uvt);
        edit_valor_dos = findViewById(R.id.ceo_pesos);
        btn_1 = findViewById(R.id.ceo_importe);
        btn_2 = findViewById(R.id.ceo_regresar);
        text_rdo = findViewById(R.id.ceo_rdo);

        spn_1 = findViewById(R.id.ceo_tasas);
//        String[][] tasas = {{"35000", "2021"}, {"34500", "2020"}, {"34000", "2019"}};
//        ArrayAdapter <String> lt_tasas  = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tasas);
//        lt_tasas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tasas);
//        spn_1.setAdapter(lt_tasas);
 //       String valor2 = spn_1.getSelectedItem().toString();

        Button btn_1 = findViewById(R.id.ceo_importe);
        btn_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                text_rdo.setText(Resultado(Double.parseDouble(edit_valor_uno.getText().toString()),
                        Double.parseDouble(edit_valor_dos.getText().toString())) + "");
    //            text_rdo.setText(Resultado(Double.parseDouble(edit_valor_uno.getText().toString()),
                //                   Double.parseDouble(valor2)) + "");

            }
        });
    }

    public double Resultado(double a, double b) {

        return a / b;
    }

    public void Regresar4(View view) {
        Intent regresar4 = new Intent(this, Ceo_menu_calc.class);
        startActivity(regresar4);
    }


}