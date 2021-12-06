package com.ceofintech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Seguimiento";
    private TextView text_rdo;
    private TextView text_und;
    private TextView text_periodo;
    private TextView edit_valor_uno;
    private EditText edit_valor_dos;
    private Spinner spn_1;
    private Double v_rdos;
    private FirebaseDatabase dbceo;
    private DatabaseReference nodo_uvt, nodo_uvr, nodo_dolar;
    String[] menu = {"De pesos a UVT", "De pesos a UVR", "De pesos a dólar", "De UVT a pesos", "De UVR a pesos", "De dólar a pesos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_periodo = findViewById(R.id.ceo_fecha);
        edit_valor_uno = findViewById(R.id.ceo_tasa);
        edit_valor_dos = findViewById(R.id.ceo_pesos);
        text_rdo = findViewById(R.id.ceo_rdo);
        text_und = findViewById(R.id.ceo_unidad);

        spn_1 = findViewById(R.id.ceo_titulo);
        ArrayAdapter<String> a_arreglo;
        a_arreglo = new ArrayAdapter<String>(this, R.layout.activity_ceo_spinner_menu, menu);
        spn_1.setAdapter(a_arreglo);

        TextView btn_calcular = findViewById(R.id.ceo_importe);
        btn_calcular.setOnClickListener(v -> {
            String dato_dos = edit_valor_dos.getText().toString();
            Log.i (TAG, "Periodo: " + text_periodo.getText().toString());
            if ((text_periodo.getText().toString().equals("Periodo")) || (text_periodo.getText().toString().isEmpty()) || (text_periodo.getText().toString().equals(null))) {
                Toast.makeText(MainActivity.this, "*** Debe definir primero el periodo ***", 120).show();
            } else if ((dato_dos.equals("0")) || (dato_dos.isEmpty()) || (dato_dos.equals(null))) {
                Toast.makeText( MainActivity.this, "*** La cantidad debe ser diferente a cero (0) ***", 120).show();
            } else {
                text_rdo.setText("");
                text_rdo.setText(Resultado(Double.parseDouble(edit_valor_uno.getText().toString()),
                        Double.parseDouble(edit_valor_dos.getText().toString())) + "");
            }
        });
    }

    public void calcular(View view) {
            String valor1 = edit_valor_uno.getText().toString();
            v_rdos = 0.0;
            v_rdos = Resultado(Double.parseDouble(valor1), Double.parseDouble(edit_valor_dos.getText().toString()));
            text_rdo.setText(v_rdos.toString());
    }

    public double Resultado(double a, double b) {
        int v_proc = Unidad();
        if (v_proc == 1) {
            v_rdos = (a / b);
        } else if (v_proc == 2) {
            v_rdos = (a * b);
        } else {
            v_rdos = 0.0;
        }
        return v_rdos;
    }

    public int Unidad() {
        int v_proc = 1;
        String v_menu = spn_1.getSelectedItem().toString();
        if (v_menu.equals("De UVT a pesos") || v_menu.equals("De UVR a pesos") || v_menu.equals("De dólar a pesos")) {
            v_proc = 2;
            text_und.setText("Pesos");
        } else if (v_menu.equals("De pesos a UVT")) {
            text_und.setText("UVT");
        } else if (v_menu.equals("De pesos a UVR")) {
            text_und.setText("UVR");
        } else if (v_menu.equals("De pesos a dólar")) {
            text_und.setText("Dólar(es)");
        } else {
            text_und.setText("Unidad");
        }
        if ((text_periodo.getText().toString().length()==4) && ((v_menu.equals("De pesos a UVT")) || (v_menu.equals("De UVT a pesos")))) {
            return v_proc;
        } else if ((text_periodo.getText().toString().length()==10) && ((!v_menu.equals("De pesos a UVT")) && (!v_menu.equals("De UVT a pesos")))) {
            return v_proc;
        } else {
            edit_valor_uno.setText("Tarifa de la tasa");
            text_periodo.setText("Periodo");
            text_rdo.setText("Resultado");
            text_und.setText("Unidad");
            if ((v_menu.equals("De pesos a UVT")) || (v_menu.equals("De UVT a pesos"))) {
                Toast.makeText(MainActivity.this, "*** Seleccione el año del periodo ***", 120).show();
            } else {
                Toast.makeText(MainActivity.this, "*** Seleccione el día del periodo ***", 120).show();
            }
            return 0;
        }
    }

    public void Calendario(View view) {
        Calendar periodo = Calendar.getInstance();
        int anual = periodo.get(Calendar.YEAR);
        int v_mes   = periodo.get(Calendar.MONTH);
        int v_dia   = periodo.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = year + "-";
                int mes = month + 1;
                if (mes < 10) {
                    fecha = fecha + "0" + mes + "-";
                } else {
                    fecha = fecha + mes + "-";
                }
                if (dayOfMonth < 10) {
                    fecha = fecha + "0" + dayOfMonth;
                } else {
                    fecha = fecha + dayOfMonth;
                }

                String v_consu = fecha;
                String v_tasa = "0";
                edit_valor_uno.setText(v_tasa);
                text_rdo.setText("Resultado");

                dbceo      = FirebaseDatabase.getInstance();
                nodo_uvt   = dbceo.getReference("ceo_uvt");
                nodo_uvr   = dbceo.getReference("ceo_uvr");
                nodo_dolar = dbceo.getReference("ceo_dolar");

                String v_menu = spn_1.getSelectedItem().toString();
                if (v_menu.equals("De pesos a UVT") || v_menu.equals("De UVT a pesos"))  {
                    v_consu = fecha.substring(0,4);
                    Query db_query = nodo_uvt.orderByChild("periodo").equalTo(v_consu).limitToFirst(1);
                    db_query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                String v_ceouvt = snapshot.getValue().toString();
                                String v_ceouvt2 = v_ceouvt.substring(v_ceouvt.indexOf("tasa=") + 5);
                                String v_ceouvt3 = v_ceouvt2.substring(0, v_ceouvt2.indexOf("}"));
                                edit_valor_uno.setText(v_ceouvt3);
                                Log.i(TAG, "Filtrado UVT con: dato " + v_ceouvt3 + ", en " + v_ceouvt);
                            } else {
                                Toast.makeText( MainActivity.this, "*** Tasa UVT sin definir aun ***", 120).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText( MainActivity.this, "*** Error en la conexión de la dB ***", 120 ).show();
                        }
                    });
                } else if (v_menu.equals("De pesos a UVR") || v_menu.equals("De UVR a pesos")) {
                    Query db_query = nodo_uvr.orderByChild("periodo").equalTo(v_consu).limitToFirst(1);
                    db_query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                String v_ceouvr = snapshot.getValue().toString();
                                String v_ceouvr2 = v_ceouvr.substring(v_ceouvr.indexOf("tasa=") + 5);
                                String v_ceouvr3 = v_ceouvr2.substring(0, v_ceouvr2.indexOf("}"));
                                edit_valor_uno.setText(v_ceouvr3);
                                Log.i(TAG, "Filtrado UVR con: dato " + v_ceouvr3 + ", en " + v_ceouvr);
                            } else {
                                Toast.makeText( MainActivity.this, "*** Tasa UVR sin definir aun ***", 120).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText( MainActivity.this, "*** Error en la conexión de la dB ***", 120 ).show();
                        }
                    });
                } else if (v_menu.equals("De pesos a dólar") || v_menu.equals("De dólar a pesos")) {
                    Query db_query = nodo_dolar.orderByChild("periodo").equalTo(v_consu).limitToFirst(1);
                    db_query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                String v_ceodolar = snapshot.getValue().toString();
                                String v_ceodolar2 = v_ceodolar.substring(v_ceodolar.indexOf("tasa=") + 5);
                                String v_ceodolar3 = v_ceodolar2.substring(0, v_ceodolar2.indexOf("}"));
                                edit_valor_uno.setText(v_ceodolar3);
                                Log.i(TAG, "Filtrado Dólar con: dato " + v_ceodolar3 + ", en " + v_ceodolar);
                            } else {
                                Toast.makeText( MainActivity.this, "*** Tasa Dólar sin definir aun ***", 120).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText( MainActivity.this, "*** Error en la conexión de la dB ***", 120 ).show();
                        }
                    });
                }
                text_periodo.setText(v_consu);
            }
        }, anual, v_mes, v_dia);
        dpd.show();
    }

    public void Salir (View view) {
        finishAndRemoveTask();
        finish();
        System.exit(0);
    }

}