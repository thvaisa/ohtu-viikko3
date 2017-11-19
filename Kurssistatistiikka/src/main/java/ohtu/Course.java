/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

/**
 *
 * @author rokka
 */
public class Course {
    private int[] exercises;
    private String name;
    private String term;
    public int getMaxExercises(int week){
        return exercises[week];
    }
    
    public String getName(){
        return name;
    }
    
    
    public String getTerm(){
        return term;
    }
}
