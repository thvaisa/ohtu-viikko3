/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import javax.swing.JTextField;

/**
 *
 * @author rokka
 */
public class Nollaa implements Komento {
    private JTextField in;
    private JTextField out;
    private Sovelluslogiikka app;
    private int edellinen;
    
    public Nollaa(JTextField in,JTextField out,Sovelluslogiikka sovelluslogiikka) {
        this.in = in;
        this.out = out;
        this.app = sovelluslogiikka;
    }

    
      
    private void tulosta(){
        out.setText(Integer.toString(app.tulos()));
    }  
    
    
    @Override
    public void suorita() {
        try {
            edellinen = app.tulos();
            app.setTulos(0);
            tulosta();
        } catch (Exception e) {
        }     
    }
    
    @Override
    public void peru() {
        try {
            app.setTulos(edellinen);
            tulosta();
        } catch (Exception e) {
        }     
    }    
    
    
}