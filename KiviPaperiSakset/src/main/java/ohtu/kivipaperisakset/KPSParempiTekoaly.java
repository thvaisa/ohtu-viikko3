package ohtu.kivipaperisakset;

import java.util.Scanner;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends PelaaTemplate{

    @Override
    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        Pelaaja player1 = new IhmisPelaaja("Ekan pelaajan vuoro: ");
        Pelaaja player2 = new KonePelaaja(new TekoalyParannettu(20), "Kone valitsi: ");
        logiikka(player1,player2,tuomari);
    }
}
