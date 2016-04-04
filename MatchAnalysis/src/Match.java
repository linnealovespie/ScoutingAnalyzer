import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Match {
    private ArrayList<Integer> teams = new ArrayList<>();//Set up as R1, R2, B1, B2

    private String team;
    private int row;
    int allyIndex; //Index of the other team, either alliance partner or opponent
    int oppIndex;
    int redScore; 
    int blueScore;
    
    File myFile;
    InputStream inp;
    XSSFWorkbook wb;
    Sheet sh;
    Row matchRow;
    
    XSSFCellStyle teamStyle;
    XSSFColor teamColor;
    XSSFCellStyle allyStyle;
    XSSFColor allyColor;
    XSSFCellStyle opponentStyle;
    XSSFColor opponentColor;
    FileOutputStream fileOut;
    
    public Match(){
        team = "";
        row = 0;
    }
    public Match(int R1, int R2, int R3, int R4, int r, int redS, int blueS){
        teams.clear();
        teams.add(R1);
        teams.add(R2);
        teams.add(R3);
        teams.add(R4);
        //team = t;
        row = r;
        redScore = redS;
        blueScore = blueS;
    }
    
    public ArrayList<Integer> getTeams()
    {
        return teams;
    }
    
    public boolean hasTeam()
    {
        for(Integer e: teams)
        {
            if(e.equals(team))
                return true;
        }
        return false;
    }
    
    public boolean hasAlly(Team t){
        ArrayList <Integer> ally = t.getAllies();
        for(int a:ally)
        {
            for(Integer e: teams){
                //System.out.println("ally team: " + a + "us team: " + e);
                if(a == e)
                {
                    allyIndex = teams.indexOf(e);
                    /*System.out.println("It has ally from Matches");*/
                    return true;
                }
                    
            }
        }
        /*System.out.println("Nada in allies");*/
        return false;
    }
    
     public int getRowNum(){
        return row;
    }
    
    public int getTeam(int i)
    {
        return teams.get(i);
    }
    
    //Get get the specified team's index method
    
    public int getAllyIndex(){
        return allyIndex;
    }
    
    public int getRedScore(){
        return redScore; 
    }
    
    public int getBlueScore(){
        return blueScore;
    }
    
    @Override
    public String toString(){
        String ret = "Match Number: " + row + "\nR1 = " + teams.get(0) + " R2 = "+ teams.get(1) + "\nB1 = " + teams.get(2) + " B2 = " + teams.get(3) + "\nRed Score: " + 
                redScore + " Blue Score : " + blueScore;
        return ret;
    }

}