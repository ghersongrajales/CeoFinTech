package com.ceofintech;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;

public class Ceo_calc_div_mon extends AppCompatActivity {
    private Button btn_1;
    private Button btn_2;
    private TextView text_rdo;

    private EditText edit_valor_uno;
    private EditText edit_valor_dos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceo_calc_div_mon);

        edit_valor_uno = findViewById(R.id.ceo_div);
        edit_valor_dos = findViewById(R.id.ceo_pesos);
        btn_1 = findViewById(R.id.ceo_importe);
        btn_2 = findViewById(R.id.ceo_regresar);
        text_rdo = findViewById(R.id.ceo_rdo);

        Button btn_1 = findViewById(R.id.ceo_importe);
        btn_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                text_rdo.setText(Resultado(Double.parseDouble(edit_valor_uno.getText().toString()),
                        Double.parseDouble(edit_valor_dos.getText().toString())) + "");

            }
        });
    }

    public double Resultado(double a, double b) {

        return a / b;
    }

    public void Regresar6(View view) {
        Intent regresar = new Intent(getApplicationContext(), Ceo_menu_calc.class);
        startActivity(regresar);
    }


}