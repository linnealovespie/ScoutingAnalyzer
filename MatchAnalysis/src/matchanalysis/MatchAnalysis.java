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
import java.io.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
    
    public static void main(String argv[]) throws FileNotFoundException, IOException 
    {
        myFile = new File("../DataFiles/SampleMatches.xlsx");//CHANGE NAME TO CURRENT COMPETITION
        inp = new FileInputStream(myFile);
        wb = new XSSFWorkbook(inp);
        sh = wb.getSheetAt(0);
        fileOut = new FileOutputStream("../DataFiles/SampleMatches.xlsx");
        
        //----------TEAM NUMBER DATA FILE
        f1 = new File("../DataFiles/Regionals_Kane_Teams.xlsx");
        inp2 = new FileInputStream(f1);
        wb2 = new XSSFWorkbook(inp2);
        sh1 = wb2.getSheetAt(0);
    }
}