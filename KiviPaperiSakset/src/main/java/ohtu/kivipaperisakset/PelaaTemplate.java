/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.Scanner;

/**
 *
 * @author rokka
 */
public abstract class PelaaTemplate implements Peli{    
    protected static final Scanner scanner = new Scanner(System.in);

    public abstract void pelaa();
    
    public void logiikka(Pelaaja player1, Pelaaja player2, Tuomari tuomari){
        
        String ekanSiirto = player1.pelaaVuoro("");
        String tokanSiirto = player2.pelaaVuoro("");

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            ekanSiirto = player1.pelaaVuoro("");
            tokanSiirto = player2.pelaaVuoro(ekanSiirto);
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }
    
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
    
}
