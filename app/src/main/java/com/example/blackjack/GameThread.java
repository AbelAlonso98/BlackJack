package com.example.blackjack;

import static com.example.blackjack.MainActivity.s;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class GameThread implements Runnable {
    Context c;



    @Override
    public void run() {
        try {
            MainActivity.s = new Socket("otterleek.ddns.net", 9999);
            MainActivity.out = new PrintWriter(s.getOutputStream(), true);
            MainActivity.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            MainActivity.out.println("R");
            MainActivity.out.flush();
            Log.d("NoEntiendoNada", "aqui llega");
            String aux = MainActivity.in.readLine();
            Log.d("NoEntiendoNada", aux); // odio la vida
            parseCommand(aux);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseCommand(String s) {
        Log.d("NoEntiendoNada", "hola");
        String crupier = s.split("\n")[0].split(":")[1].split(" ")[1];
        String jugador = s.toString().split("\n")[1].split(":")[1].split(" ")[1];
        ArrayList<Card> cartas = new ArrayList<Card>();
        for (int i = 0; i < jugador.length(); i += 2) {
            cartas.add(new Card(jugador.substring(i, i + 2), c));
        }
        MainActivity.rv.setAdapter(new CardAdapter(cartas));

    }
}
