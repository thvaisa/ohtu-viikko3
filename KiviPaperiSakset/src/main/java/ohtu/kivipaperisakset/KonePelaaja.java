/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import static ohtu.kivipaperisakset.PelaaTemplate.scanner;

/**
 *
 * @author rokka
 */
public class KonePelaaja implements Pelaaja {
    String teksti;
    
    TekoalyInterface AI;
    public KonePelaaja(TekoalyInterface AI, String teksti){
        this.teksti = teksti;
        this.AI = AI;
    }
    
    @Override
    public String pelaaVuoro(String edellinen) {
        String siirto;
        if(!edellinen.isEmpty()){
            AI.asetaSiirto(edellinen);
        }
        siirto = AI.annaSiirto();
        System.out.println(teksti + siirto);
        return siirto;
    }
    
}
