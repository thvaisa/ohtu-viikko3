package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja  extends PelaaTemplate{

     public void pelaa() {
        Tuomari tuomari = new Tuomari();
        Pelaaja player1 = new IhmisPelaaja("Ekan pelaajan vuoro: ");
        Pelaaja player2 = new IhmisPelaaja("Tokan pelajan vuoro: ");
        logiikka(player1,player2,tuomari);
    }

}