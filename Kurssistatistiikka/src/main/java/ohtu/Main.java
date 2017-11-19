package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "0000000";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        url="https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String bodyText2 = Request.Get(url).execute().returnContent().asString();
        //System.out.println("json-muotoinen data:");
 
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course course = mapper.fromJson(bodyText2, Course.class);
        int exercises=0;
        int hours=0;
        System.out.println("Kurssi "+course.getName()+","+course.getTerm());
        System.out.println();
        System.out.println("opiskelijanumero: "+studentNr);
        System.out.println();
        for (Submission submission : subs) {
            System.out.println(" viikko "+submission.getWeek()+":");
            System.out.println("   tehtyjä tehtäviä yhteensä: "
                    +submission.getSum()+" (maksimi "+ course.getMaxExercises(hours)
                    +"), aikaa kului " + submission.getHours() 
                    +" tuntia, tehdyt tehtävät"+submission.exercisesToString());
            hours+=submission.getHours();
            exercises+=submission.getSum();
        }
        System.out.println();
        System.out.println("opiskelijanumero: "+studentNr);
    }
}