package com.example.blackjack;

import static com.example.blackjack.MainActivity.s;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class GameThread extends Thread {
    Context c;

    public GameThread(Context c) {
        this.c = c;
    }

    @Override
    public void run() {
        try {
            MainActivity.s = new Socket("localhost", 9999);
            MainActivity.out = new PrintWriter(s.getOutputStream(), true);
            MainActivity.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            MainActivity.out.write("R");
            parseCommand(MainActivity.in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseCommand(String s) {
        String crupier = s.split("\n")[0];
        String jugador = s.toString().split("\n")[1].split(":")[1].split(" ")[1];
        ArrayList<Card> cartas = new ArrayList<Card>();
        for (int i = 0; i < jugador.length(); i += 2) {
            cartas.add(new Card(jugador.substring(i, i + 2), c));
        }
        MainActivity.rv.setAdapter(new CardAdapter(cartas));

    }
}
