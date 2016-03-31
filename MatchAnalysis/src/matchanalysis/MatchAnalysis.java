/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchanalysis;

/**
 *
 * @author may_884771
 */
import java.awt.Color;
import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
public class MatchAnalysis 
{
    
    //Matches
    private static File myFile;
    private static FileInputStream inp;
    private static XSSFWorkbook wb;
    private static Sheet sh;
    private static FileOutputStream fileOut;
    
    //Teams
    private static File f1;
    private static FileInputStream inp2;
    private static XSSFWorkbook wb2;
    private static Sheet sh1;
    
    private static int[][] teamMatrix;
    private static Team[] teams;
     private static int[] teamNumbers;
    private static int[] OPR; 
    private static int[] totalPoints;
    
    private static Match[] matches;
    
    public static void main(String argv[]) throws FileNotFoundException, IOException 
    {
        myFile = new File("C:/Users/may_884771/Desktop/GitHub/ScoutingAnalyzer/MatchAnalysis/DataFiles/SampleMatches.xlsx");//CHANGE NAME TO CURRENT COMPETITION
        inp = new FileInputStream(myFile);
        wb = new XSSFWorkbook(inp);
        sh = wb.getSheetAt(0);
        //fileOut = new FileOutputStream("C:/Users/may_884771/Desktop/GitHub/ScoutingAnalyzer/MatchAnalysis/DataFiles/SampleMatches.xlsx");
        
        //----------TEAM NUMBER DATA FILE
        f1 = new File("C:/Users/may_884771/Desktop/GitHub/ScoutingAnalyzer/MatchAnalysis/DataFiles/Regionals_Kane_Teams.xlsx");
        inp2 = new FileInputStream(f1);
        wb2 = new XSSFWorkbook(inp2);
        sh1 = wb2.getSheetAt(0);
        
        int numOfTeams = 41; //Will be 64 for Worlds
        int numOfMatches = 12; //Change to the proper number
        teamMatrix = new int[numOfTeams][numOfTeams]; 
        teamNumbers = new int[numOfTeams];
        teams = new Team[numOfTeams];
        OPR = new int[numOfTeams];
        totalPoints = new int[numOfTeams];
        matches = new Match[numOfMatches];

        //Read in team and match numbers
        loadTeams();
        loadMatches();
        
        for(int m = 1; m <=numOfMatches; m++){
           //getTeam() //Get the team numbers for red then blue, and index of both, then add to teamMatrix count at [t1][t2]
        }
    }
    
    static void loadTeams(){
        for(int r = 0; r < 41; r++)
        {
            Row teamRow = sh1.getRow(r+1);
            Cell teamCell = teamRow.getCell(0);
            Team t = new Team(String.valueOf((int)teamCell.getNumericCellValue()));
            teamNumbers[r] = (int)teamCell.getNumericCellValue();
            teams[r]= t; 
            
        }
    }
 
    static void loadMatches(){
        for(int r = 0; r < 26; r++)
        {
            Row matchRow = sh.getRow(r+1);
            Cell red1 = matchRow.getCell(1);
            Cell red2 = matchRow.getCell(2);
            Cell blue1 = matchRow.getCell(3);
            Cell blue2  = matchRow.getCell(4);
            int red1Num = ((int)red1.getNumericCellValue());
            int red2Num = ((int)red2.getNumericCellValue());
            int blue1Num = ((int)blue1.getNumericCellValue());
            int blue2Num = ((int)blue2.getNumericCellValue());
            Match m = new Match(red1Num, red2Num, blue1Num, blue2Num, r + 1);
        }
    }
    
    static int getTeam(int teamN){
        return teams.indexOf(teamN);
    }
}