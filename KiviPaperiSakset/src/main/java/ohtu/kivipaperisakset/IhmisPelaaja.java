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
public class IhmisPelaaja implements Pelaaja {
    String teksti;
    
    public IhmisPelaaja(String teksti){
        this.teksti = teksti;
    }
    
    @Override
    public String pelaaVuoro(String edellinen) {
        System.out.print(teksti);
        return scanner.nextLine();    
    }
    
}
