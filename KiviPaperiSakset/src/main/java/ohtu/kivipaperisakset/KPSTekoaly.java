package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends PelaaTemplate{

    @Override
    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        Pelaaja player1 = new IhmisPelaaja("Ekan pelaajan vuoro: ");
        Pelaaja player2 = new KonePelaaja(new Tekoaly(), "Kone valitsi: ");
        logiikka(player1,player2,tuomari);
    }


}