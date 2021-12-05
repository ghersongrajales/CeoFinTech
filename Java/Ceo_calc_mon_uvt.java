package com.ceofintech;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Ceo_calc_mon_uvt extends AppCompatActivity {
    private Button btn_1;
    private Button btn_2;
    private TextView text_rdo;

    private EditText edit_valor_uno;
    private EditText edit_valor_dos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceo_calc_mon_uvt);

        edit_valor_uno = findViewById(R.id.ceo_uvt);
        edit_valor_dos = findViewById(R.id.ceo_pesos);
        btn_1 = findViewById(R.id.ceo_importe);
        btn_2 = findViewById(R.id.ceo_regresar);
        text_rdo = findViewById(R.id.ceo_rdo);

        btn_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                text_rdo.setText(Resultado(Double.parseDouble(edit_valor_uno.getText().toString()),
                        Double.parseDouble(edit_valor_dos.getText().toString())) + "");

            }
        });
    }

    public double Resultado(double a, double b) {

        return a * b;
    }

    public void Regresar1(View view) {
            Intent regresar1 = new Intent(this, Ceo_menu_calc.class);
            startActivity(regresar1);
        }


}