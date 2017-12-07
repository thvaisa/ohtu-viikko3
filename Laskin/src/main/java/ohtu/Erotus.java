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
public class Erotus implements Komento {
    private JTextField in;
    private JTextField out;
    private Sovelluslogiikka app;
    private int edellinen;
    
    public Erotus(JTextField in,JTextField out,Sovelluslogiikka sovelluslogiikka) {
        this.in = in;
        this.out = out;
        this.app = sovelluslogiikka;
        edellinen=0;
    }

    @Override
    public void suorita() {
      //  out.setText("miinusta arvo:");
        try {
            int luku = Integer.parseInt(in.getText());
            app.setTulos(app.tulos()-luku);
            edellinen=luku;
            tulosta();
        } catch (Exception e) {
        }
    }
    
        
    private void tulosta(){
        out.setText(Integer.toString(app.tulos()));
    }
    
    @Override
    public void peru() {
        try {
            app.setTulos(app.tulos()+edellinen);
            tulosta();
        } catch (Exception e) {
        }
    }
    
}