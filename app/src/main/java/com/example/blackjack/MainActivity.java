package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static Socket s;
    static PrintWriter out;
    static BufferedReader in;
    static RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rvPlayer);

        new GameThread(this).start();



    }

    public void pedir(View view) throws IOException {
        out.write("P");
        parseCommand(in.readLine());
    }

    public void plantarse(View view) {
        out.write("PL");
    }

    public void salir(View view) {
    }

    public void parseCommand(String s) {
        String crupier = s.split("\n")[0];
        String jugador = s.toString().split("\n")[1].split(":")[1].split(" ")[1];
        ArrayList<Card> cartas = new ArrayList<Card>();
        for(int i = 0; i < jugador.length(); i+=2) {
            cartas.add(new Card(jugador.substring(i, i+2), this));
        }
        rv.setAdapter(new CardAdapter(cartas));

    }
}