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
    
    private static Team[][] teamMatrix;
    
    public static void main(String argv[]) throws FileNotFoundException, IOException 
    {
        /*myFile = new File("C:/Users/may_884771/Desktop/GitHub/ScoutingAnalyzer/MatchAnalysis/DataFiles/SampleMatches.xls");//CHANGE NAME TO CURRENT COMPETITION
        inp = new FileInputStream(myFile);
        wb = new XSSFWorkbook(inp);
        sh = wb.getSheetAt(0);
        fileOut = new FileOutputStream("C:/Users/may_884771/Desktop/GitHub/ScoutingAnalyzer/MatchAnalysis/DataFiles/SampleMatches.xls");*/
        
        //----------TEAM NUMBER DATA FILE
        f1 = new File("C:/Users/may_884771/Desktop/GitHub/ScoutingAnalyzer/MatchAnalysis/DataFiles/Regionals_Kane_Teams.xlsx");
        inp2 = new FileInputStream(f1);
        wb2 = new XSSFWorkbook(inp2);
        sh1 = wb2.getSheetAt(0);
        
        teamMatrix = new Team[41][41]; //Will be 64 for Worlds

        loadTeams();
    }
    
    static void loadTeams(){
        for(int r = 0; r < 41; r++)
        {
            Row teamRow = sh1.getRow(r+1);
            Cell teamCell = teamRow.getCell(0);
            Team t = new Team(String.valueOf((int)teamCell.getNumericCellValue()));
            teamMatrix[0][r] = t;
            teamMatrix[r][0] = t;
            for(int c = 1; c < 41; c++)
            {
                t = new Team("  ");
                teamMatrix[r][c] = t;
            }
        }
       for(int i = 0; i<41; i++)
        {
            for(int j = 0; j<41; j++)
            {
                System.out.printf("%5d", Integer.parseInt(teamMatrix[i][j].toString()));
            }
            System.out.println();
        }
    }
}