package com.example.jairo.proyectorosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.frasesButton) Button frases;
    @BindView(R.id.recuerdosButton) Button recuerdos;
    @BindView(R.id.musicaButton) Button musica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.frasesButton)
    public void frasesButtonClick(){
       Intent FrasesActivityIntent = new Intent(MainActivity.this,Frases.class);
        startActivity(FrasesActivityIntent);

    }
    @OnClick(R.id.musicaButton)
    public void musicaButtonClick(){
        Intent MusicaActivityIntent = new Intent(MainActivity.this,Musica.class);
        startActivity(MusicaActivityIntent);

    }
    @OnClick(R.id.recuerdosButton)
    public void GaleriaButtonClick(){
        Intent GaleriaActivityIntent = new Intent(MainActivity.this,Galeria.class);
        startActivity(GaleriaActivityIntent);

    }

}
