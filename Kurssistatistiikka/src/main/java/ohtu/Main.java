package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "00000000";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        url="https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String bodyText2 = Request.Get(url).execute().returnContent().asString();
        url="https://studies.cs.helsinki.fi/ohtustats/stats";
        String bodyText3 = Request.Get(url).execute().returnContent().asString();
        //System.out.println("json-muotoinen data:");
        //System.out.println(bodyText3);
        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(bodyText3).getAsJsonObject();
        
   
        
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course course = mapper.fromJson(bodyText2, Course.class);
        int exercises=0;
        int hours=0;
        System.out.println("Kurssi "+course.getName()+","+course.getTerm());
        System.out.println();
        System.out.println("opiskelijanumero: "+studentNr);
        System.out.println();
        
        int total=0;
        int students=0;
        for (Submission submission : subs) {
            System.out.println(" viikko "+submission.getWeek()+":");
            System.out.println("   tehtyjä tehtäviä yhteensä: "
                    +submission.getSum()+" (maksimi "+ course.getMaxExercises(hours)
                    +"), aikaa kului " + submission.getHours() 
                    +" tuntia, tehdyt tehtävät"+submission.exercisesToString());
            hours+=submission.getHours();
            exercises+=submission.getSum();
            total+=Integer.parseInt(parsittuData.get(Integer.toString(submission.getWeek())).getAsJsonObject().get("exercise_total").toString());
            students+=Integer.parseInt(parsittuData.get(Integer.toString(submission.getWeek())).getAsJsonObject().get("students").toString());
        }
        System.out.println();
        System.out.println("opiskelijanumero: "+studentNr);
        System.out.println();
        System.out.println("kurssilla yhteensä "+students+", palautusta, palautettuja tehtäviä "
        +total+" kpl");
    }
}