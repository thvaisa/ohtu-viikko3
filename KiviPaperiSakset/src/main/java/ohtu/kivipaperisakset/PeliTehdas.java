/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

/**
 *
 * @author rokka
 */
public class PeliTehdas {
    public static Peli KPSPelaajaVsPelaaja(){
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        return new KPSPelaajaVsPelaaja(); 
    }
    
    public static Peli KPSTekoaly(){
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        return new KPSTekoaly(); 
    }
    
    public static Peli KPSParempiTekoaly(){
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        return new KPSParempiTekoaly(); 
    }
}
