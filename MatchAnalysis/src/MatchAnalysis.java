/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author may_884771
 */
import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Jama.*;
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
    
    private static double[][] teamMatrix;
    private static Team[] teams;
     private static int[] teamNumbers;
    private static double[][] OPR; 
    private static double[] totalPoints;
    
    private static Match[] matches;
    
    private static int numOfTeams;
    private static int numOfMatches;
    
    
    
    public static void main(String argv[]) throws FileNotFoundException, IOException 
    {
        myFile = new File("DataFiles/ESupers_Hopper_Match_Results.xlsx");
        //myFile = new File("C:/Users/may_884771/Desktop/GitHub/ScoutingAnalyzer/MatchAnalysis/DataFiles/SampleMatches.xlsx");//CHANGE NAME TO CURRENT COMPETITION
        inp = new FileInputStream(myFile);
        wb = new XSSFWorkbook(inp);
        sh = wb.getSheetAt(0);
        //fileOut = new FileOutputStream("C:/Users/may_884771/Desktop/GitHub/ScoutingAnalyzer/MatchAnalysis/DataFiles/SampleMatches.xlsx");
        
        //----------TEAM NUMBER DATA FILE
        f1 = new File("DataFiles/ESupers_Hopper_Teams.xlsx");
        inp2 = new FileInputStream(f1);
        wb2 = new XSSFWorkbook(inp2);
        sh1 = wb2.getSheetAt(0);
        
        numOfTeams = 36; //Will be 64 for Worlds
        numOfMatches = 81; //Change to the proper number
        teamMatrix = new double[numOfTeams][numOfTeams]; 
        teamNumbers = new int[numOfTeams];
        teams = new Team[numOfTeams];
        OPR = new double[numOfTeams][numOfTeams];
        totalPoints = new double[numOfTeams];
        matches = new Match[numOfMatches];

        //Read in team and match numbers
        loadTeams();
        loadMatches();
        
        for(int m = 0; m < numOfMatches; m++){ // Go through each match and count how many times each team plays with each other
            System.out.println("MATCH #" + (m+1));
            int red1 = matches[m].getTeam(0);
            int red2 = matches[m].getTeam(1);
            int red1Index = Arrays.binarySearch(teamNumbers, red1);
            //System.out.println("Red 1 Index : " + red1Index);//Index and corresponding team works 
            int red2Index = Arrays.binarySearch(teamNumbers, red2);
            //System.out.println("Red 2 Index : " + red2Index);
            if(red1Index > -1 && red2Index > -1)
            {
                //Add a count to having played with the team
                teamMatrix[red1Index][red2Index]++;
                teamMatrix[red2Index][red1Index]++;
                //Add the other allinace partner to the Team's list of them
                teams[red1Index].addAlly(red2);
                teams[red2Index].addAlly(red1);
                //Add the alliance's score to the total score of each team
                totalPoints[red1Index] += (matches[m].getRedScore());
                totalPoints[red2Index] += (matches[m].getRedScore());
            }
            else {System.out.println("Red Team doesn't exist");}
            
            
            int blue1 = matches[m].getTeam(2);
            int blue2 = matches[m].getTeam(3);
            int blue1Index = Arrays.binarySearch(teamNumbers, blue1);
            System.out.println("Blue 1 Index : " + blue1Index);
            int blue2Index = Arrays.binarySearch(teamNumbers, blue2);
            System.out.println("Blue 2 Index : " + blue2Index);
            if(blue1Index > -1 && blue2Index > -1)
            {
                //Add a count to having played with the team
                teamMatrix[blue2Index][blue1Index]++;
                teamMatrix[blue1Index][blue2Index]++;
                //Add the other allinace partner to the Team's list of them
                teams[blue1Index].addAlly(blue2);
                teams[blue2Index].addAlly(blue1);
                //Add the alliance's score to the total score of each team
                totalPoints[blue1Index] += (matches[m].getBlueScore());
                totalPoints[blue2Index] += (matches[m].getBlueScore());
            } 
            else {System.out.println("Blue Team doesn't exist");}
        }
        
        //Print out the total scores
        /*for(double score: totalPoints){
            System.out.println(score);
        }*/
        
        //Print out the matrix before inversion
        /*System.out.println("****Matrix before inversion");
        for(int r = 0; r < numOfTeams;  r++){
            for(int c = 0; c < numOfTeams; c++){
                System.out.print(teamMatrix[r][c] + " ");
            }
            System.out.println();
        }*/
        
        Matrix m = new Matrix(teamMatrix); 
        Matrix invt = m.inverse();
        //System.out.println("****Matrix after inversion");
        //System.out.println(invt.toString());
        
        Matrix s = new Matrix(totalPoints, numOfTeams);
        //Matrix opr = s.times(invt);
        
        CholeskyDecomposition cd = new CholeskyDecomposition(s);
        Matrix opr = cd.solve(m);

        //OPR = opr.getArray(); 
        System.out.println(OPR[0].length);
        for(int i = 0; i < numOfTeams; i++){
           System.out.println("TEAM Number: " + teamNumbers[i] + " OPR: " + OPR[0][i] + ' ');
        }
        //System.out.println("****OPR of each team \n " + opr.vectorToString());
        
        /*for(int r = 0; r < numOfTeams;  r++){
            for(int c = 0; c < numOfTeams; c++){
                System.out.print(teamMatrix[r][c] + " ");
            }
            System.out.println();
        }*/
        
        /*for(int j = 0; j < numOfTeams; j++)
        {
            System.out.println("Allies for team " + teamNumbers[j] + " " + (teams[j].getAllies()).toString());
        }*/
        
    }
    
    static void loadTeams(){
        for(int r = 0; r < numOfTeams; r++)
        {
            Row teamRow = sh1.getRow(r+1);
            Cell teamCell = teamRow.getCell(0);
            Team t = new Team(String.valueOf((int)teamCell.getNumericCellValue()));
            teamNumbers[r] = (int)teamCell.getNumericCellValue();
            teams[r]= t; 
            
        }
    }
 
    static void loadMatches(){
        for(int r = 0; r < numOfMatches; r++)
        {
            Row matchRow = sh.getRow(r+1);
            Cell red1 = matchRow.getCell(1);
            Cell red2 = matchRow.getCell(2);
            Cell blue1 = matchRow.getCell(3);
            Cell blue2  = matchRow.getCell(4);
            Cell redS = matchRow.getCell(5);
            Cell blueS = matchRow.getCell(6);
            int red1Num = ((int)red1.getNumericCellValue());
            int red2Num = ((int)red2.getNumericCellValue());
            int blue1Num = ((int)blue1.getNumericCellValue());
            int blue2Num = ((int)blue2.getNumericCellValue());
            int redScore = ((int)redS.getNumericCellValue());
            int blueScore = ((int)blueS.getNumericCellValue());
            Match m = new Match(red1Num, red2Num, blue1Num, blue2Num, r + 1, redScore, blueScore);
            matches[r] = m;
            //System.out.println("***" + m.toString());
        }
    }
    
    /*static int getTeam(int teamN){
        return teamNumbers.indexOf(teamN); // Need to make an actual indexOf() method
    }*/
}