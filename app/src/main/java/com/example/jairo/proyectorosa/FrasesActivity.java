package com.example.jairo.proyectorosa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class FrasesActivity extends AppCompatActivity {
    public static final int READ_BLOCK_SIZE = 200;
    public EditText frasesEditText;
    public RadioButton rosaRadioButton;
    public RadioButton jairoRadioButton;
    public Button agregarFraseButton;
    public ListView frasesListView;
    public ArrayAdapter<String> adapter;
    public ArrayList<String> frasesArrayList;
    static final String rosa ="Rosa";
    static final String jairo ="Jairo";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frases);
        frasesEditText = (EditText) findViewById(R.id.fraseEditText);
        rosaRadioButton = (RadioButton) findViewById(R.id.rosaRadioButton);
        jairoRadioButton = (RadioButton) findViewById(R.id.jairoRadioButton);
        agregarFraseButton = (Button) findViewById(R.id.agregarFraseButton);
        frasesListView = (ListView) findViewById(R.id.frasesListView);
        frasesArrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, frasesArrayList);
        frasesListView.setAdapter(adapter);
        //Eliminar mientras pulsamos
        frasesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> AdapterView, View view, int i, long l) {
            final int posicion=i;

            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(FrasesActivity.this);
            dialogo1.setTitle("Importante");
            dialogo1.setMessage("¿Quieres Eliminarla?");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    frasesArrayList.remove(posicion);
                    adapter.notifyDataSetChanged();
                }
            });
            dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                }
            });
            dialogo1.show();

            return false;
        }
    });
        cargar();
    }
    //Metodo para Recargar la ListView
    public void cargar() {
        try {
            InputStreamReader arch = new InputStreamReader(openFileInput("FrasesAutor.txt"));
            BufferedReader br = new BufferedReader(arch);
            String linea = br.readLine();
            String todo = "";
            while (linea != null) {
                todo = todo + linea + "\n";
                linea = br.readLine();
            }
            br.close();
            arch.close();
            frasesArrayList.add(todo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregar(View view) {
        String frase = frasesEditText.getText().toString();
        try {
            //http://stackoverflow.com/questions/14872429/append-text-to-the-end-of-the-file
            //http://stackoverflow.com/questions/4542318/android-append-text-file
            FileOutputStream outStream = openFileOutput("FrasesAutor.txt", MODE_APPEND);
            OutputStreamWriter archivo = new OutputStreamWriter(outStream);
            if (rosaRadioButton.isChecked()) {
                archivo.append(rosa + ": " + frase + "\n");
            } else {
                archivo.append(jairo + ": " + frase + "\n");
            }
            archivo.flush();
            archivo.close();
            frasesEditText.setText("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        cargar();
    }
}
