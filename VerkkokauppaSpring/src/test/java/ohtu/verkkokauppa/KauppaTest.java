/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author rokka
 */
public class KauppaTest {
    
    public KauppaTest() {
    }

    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void tehdaanOstosKunVarastossaTavaraaJaVarmistetaanEttaPankkiTekeeOsansa() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        //public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu  
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",5);   
    }
    
    @Test
    public void testaaPankkiKayttaaOikeaaAsiakasta() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(9000); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "aurinko", 1));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2); 
        k.tilimaksu("pekka", "12345");
        //public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu  
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",6);   
    }
    
    @Test
    public void testaaTilisiirotaKunOstetaanKahtaTuotetta() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa 
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        //public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu  
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",10);   
    }
    
    @Test
    public void testaaTilisiirtoaTuotettaEiTarpeeksi() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(0); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "aurinko", 1));
        
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa 
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        //public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu  
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",5);   
    }
    
        
    @Test
    public void testaaNollaaEdellisenOstoksenTiedot() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(5); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "aurinko", 1));
        
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa 
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        //public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu  
        k.aloitaAsiointi();
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",0);   
    }
    
    @Test
    public void testaaNollaaEdellisenOstoksenTiedot2() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(2); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "aurinko", 1));
        
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa 
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        //public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu  
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",1);   
    }
    
    @Test
    public void testaaUusiViitenumero() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).
            thenReturn(1).
            thenReturn(2).
            thenReturn(3);
        // määritellään että viitegeneraattori palauttaa viitten 42
        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(2); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "aurinko", 1));
        
        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa 
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(anyString(), eq(1), anyString(), anyString(),anyInt()); 
        verify(viite,times(1)).uusi();  
        //public  tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu  
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        verify(viite,times(2)).uusi();  
        verify(pankki).tilisiirto(anyString(), eq(2), anyString(), anyString(),anyInt()); 
    }

    
    @Test
    public void testaaPoistaKorista() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(9000); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "aurinko", 1));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2); 
        k.poistaKorista(1);
        k.tilimaksu("pekka", "12345");
        //public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu  
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",1); 
    }
    
    
    
}