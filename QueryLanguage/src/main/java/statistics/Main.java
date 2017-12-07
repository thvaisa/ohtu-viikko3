package statistics;

import java.util.LinkedList;
import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
          
        /*Matcher m = new And( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists"),
                             new PlaysIn("PHI")
        );*/
        
        
        QueryBuilder query = new QueryBuilder();
        /*
        Matcher m = new Or( new HasAtLeast(40, "goals"),
                            new HasAtLeast(60, "assists"),
                            new HasAtLeast(85, "points")
        );   
        */
        
        
        Matcher m1 = query.hasAtLeast(40, "goals").build();
        Matcher m2 = query.hasAtLeast(60, "assists").build();
        Matcher m3 = query.hasAtLeast(85, "points").build();

        Matcher m = query.or(m1, m2, m3).build();
        

        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
        /*
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
        */
    }

    private static class QueryBuilder {
        private Matcher pino;
        
        public QueryBuilder() {
        }
        
        public QueryBuilder and(Matcher... matchers){
            this.pino = new And(matchers);
            return this;
        }
        
        
        public QueryBuilder or(Matcher... matchers){
            this.pino = new Or(matchers);
            return this;
        }

        public QueryBuilder hasAtLeast(int value, String category) {
            this.pino = new HasAtLeast(value,category);
            return this;
        }       

        public QueryBuilder hasFewerThan(int value, String category) {
            this.pino = new HasFewerThan(value,category);
            return this;
        }       

        public QueryBuilder not(Matcher matcher) {
            this.pino = new Not(matcher);
            return this;
        }
        
        public QueryBuilder playsIn(String team) {
            this.pino = new PlaysIn(team);
            return this;
        } 
        
        public Matcher build() {
            return this.pino;
        }
    }
}
