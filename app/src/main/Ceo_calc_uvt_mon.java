package com.ceofintech;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Ceo_calc_uvt_mon extends AppCompatActivity  {
    private TextView text_rdo;
    private EditText edit_valor_uno;
    private EditText edit_valor_dos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceo_calc_uvt_mon);
        String[] periodo = {"2021", "2020", "2019"};
        String[] tasas  = {"35000", "34500", "34000"};
        edit_valor_uno = findViewById(R.id.ceo_uvt);
        edit_valor_dos = findViewById(R.id.ceo_pesos);
        text_rdo = findViewById(R.id.ceo_rdo);
        Spinner spn_1 = findViewById(R.id.ceo_titulo);

        ArrayAdapter<String> a_arreglo;
        a_arreglo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, periodo);
        spn_1.setAdapter(a_arreglo);

        spn_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String v_tasas = tasas[i];
//                edit_valor_uno.setText(v_tasas);
        System.out.println("La posicion es:" + i);
            }
        });
    }

    public void Calcular(View view) {

        String valor1 = edit_valor_uno.getText().toString();
        Double rdo;
        rdo = Resultado(Double.parseDouble(valor1), Double.parseDouble(edit_valor_dos.getText().toString()));
        text_rdo.setText(rdo.toString());
    }

    public double Resultado(double a, double b) {
        Double rdos;
         rdos = ( a / b);
        return (rdos);
    }

    public void Regresar4(View view) {
//        Intent regresar4 = new Intent(this, Ceo_menu_calc.class);
        Intent regresar4 = new Intent(this, MainActivity.class);
        startActivity(regresar4);
    }
}
