/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchcolor;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Linnea
 */
public class Match {
    private ArrayList<String> teams = new ArrayList<>();//Set up as R1, R2, B1, B2

    private String team;
    private int row;
    int otherIndex; //Index of the other team, either alliance partner or opponent
    
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
    
    
    public Match(String R1, String R2, String R3, String R4, String t, int r){
        teams.add(R1);
        teams.add(R2);
        teams.add(R3);
        teams.add(R4);
        team = t;
        
        try{ 
            myFile = new File("DataFiles/SampleMatches.xlsx");//CHANGE NAME TO CURRENT COMPETITION
            inp = new FileInputStream(myFile);
            wb = new XSSFWorkbook(inp);
            sh = wb.getSheetAt(0);
            matchRow = sh.getRow(row);
        }
        catch(IOException i){System.out.println("Wrong file, bozo!");}
        
        //Your Team style
        teamStyle = (XSSFCellStyle) wb.createCellStyle();
        teamStyle.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex()); 
        /*teamColor = new XSSFColor(Color.decode("#98FB98"));*/
        /*teamStyle.setFillForegroundColor(teamColor);*/
        teamStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            
        //Alliance Partner Style
        allyStyle = (XSSFCellStyle) wb.createCellStyle();
        allyColor = new XSSFColor(Color.decode("#FFFF32"));
        allyStyle.setFillForegroundColor(allyColor);
        allyStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

         //Opponent Style
        opponentStyle = (XSSFCellStyle) wb.createCellStyle();
        opponentColor = new XSSFColor(Color.decode("#DF5555"));
        opponentStyle.setFillForegroundColor(opponentColor);
        opponentStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
    }
    
    public ArrayList<String> getTeams()
    {
        return teams;
    }
    
    public boolean hasTeam()
    {
        for(String e: teams)
        {
            if(e.equals(team))
                return true;
        }
        return false;
    }
    
    public boolean hasAlly(Team t){
        ArrayList <String> ally = t.getAllies();
        for(String a:ally)
        {
            for(String e: teams){
                if(a.equals(e))
                {
                    otherIndex = teams.indexOf(e);
                    return true;
                }
                    
            }
        }
        return false;
    }
    
    public boolean hasOpp(Team t){
        ArrayList <String> opponent = t.getOpponent();
        for(String a: opponent)
        {
            for(String e: teams){
                if(a.equals(e))
                    return true;
            }
        }
        return false;
    }
    
    public void setColour()
    {
            if(team.equals(teams.get(0)))
            {
                Cell teamC = matchRow.getCell(1);
                teamC.setCellStyle(teamStyle);
                Cell allyC = matchRow.getCell(2);
                allyC.setCellStyle(allyStyle);
                Cell opp1C = matchRow.getCell(3);
                opp1C.setCellStyle(opponentStyle);
                Cell opp2C = matchRow.getCell(4);
                opp2C.setCellStyle(opponentStyle);
            }
            else if(team.equals(teams.get(1)))
            {
                Cell teamC = matchRow.getCell(2);
                teamC.setCellStyle(teamStyle);
                Cell allyC = matchRow.getCell(1);
                allyC.setCellStyle(allyStyle);
                Cell opp1C = matchRow.getCell(3);
                opp1C.setCellStyle(opponentStyle);
                Cell opp2C = matchRow.getCell(4);
                opp2C.setCellStyle(opponentStyle);
            }
            else if(team.equals(teams.get(2)))
            {
                Cell teamC = matchRow.getCell(3);
                teamC.setCellStyle(teamStyle);
                Cell allyC = matchRow.getCell(4);
                allyC.setCellStyle(allyStyle);
                Cell opp1C = matchRow.getCell(1);
                opp1C.setCellStyle(opponentStyle);
                Cell opp2C = matchRow.getCell(2);
                opp2C.setCellStyle(opponentStyle);
            }
            else if(team.equals(teams.get(3)))
            {
                Cell teamC = matchRow.getCell(4);
                teamC.setCellStyle(teamStyle);
                Cell allyC = matchRow.getCell(3);
                allyC.setCellStyle(allyStyle);
                Cell opp1C = matchRow.getCell(1);
                opp1C.setCellStyle(opponentStyle);
                Cell opp2C = matchRow.getCell(2);
                opp2C.setCellStyle(opponentStyle);
            }
            
    }
    
    void setAllyColor(){
        Cell allyC = matchRow.getCell(otherIndex +1);
        allyC.setCellStyle(allyStyle);
    }
    
    public void setOppColor(){
        Cell oppC = matchRow.getCell(otherIndex +1);
        oppC.setCellStyle(opponentStyle);
    }
}
