package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals("player1"))
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    private String scoreToString(int score){
        switch (score){
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "Deuce";
        }        
    }
   
    private String scoreToStringDraw(int score){
        if(score<4 && score>=0){
            return scoreToString(score)+"-All";
        }
        return scoreToString(score);
    }
   
    private String whoIsWinning(int scoreDifference){
        if (scoreDifference==1) return "Advantage player1";
        else if (scoreDifference ==-1) return "Advantage player2";
        else if (scoreDifference>=2) return "Win for player1";
        return "Win for player2";
    }
    

    public String getScore() {
        if (m_score1==m_score2){
            return scoreToStringDraw(m_score1);
        }else if (m_score1>=4 || m_score2>=4){
            return whoIsWinning(m_score1-m_score2);
        }else{
            String score = scoreToString(m_score1);
            score += "-"+scoreToString(m_score2);
            return score;
        }
    }
}