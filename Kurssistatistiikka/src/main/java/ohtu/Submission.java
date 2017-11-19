package ohtu;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;
 
    
    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public int[] getExercises() {
        return exercises;
    }
    
    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }
    
    
    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public int getSum(){
        return exercises.length;
    }
    
    public String exercisesToString(){
        String str="";
        for(int i=0;i<exercises.length;++i){
            str=str+" "+exercises[i];
        }
        return str;
    }
    
    

    @Override
    public String toString() {
        return "";
   }
    
}