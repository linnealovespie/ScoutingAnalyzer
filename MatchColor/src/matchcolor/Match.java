/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchcolor;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    int allyIndex; //Index of the other team, either alliance partner or opponent
    int oppIndex;
    
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
    public Match(String R1, String R2, String R3, String R4, String t, int r){
        teams.clear();
        teams.add(R1);
        teams.add(R2);
        teams.add(R3);
        teams.add(R4);
        team = t;
        row = r;
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
                System.out.println("ally team: " + a + "us team: " + e);
                if(a.equals(e))
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
    
    public boolean hasOpp(Team t){
        ArrayList <String> opponent = t.getOpponent();
        System.out.println("--TEAM: " + team + " MATCH: " + row);
        for(String a: opponent)
        {
            for(String e: teams){
                System.out.println("opp team: " + a + " us team: " + e);
                if(a.equals(e))
                {
                    oppIndex = teams.indexOf(e);
                    return true;
                }
            }
        }
        return false;
    }
    
    public int getRowNum(){
        return row;
    }
    
    public String getTeam(int i)
    {
        return teams.get(i);
    }
    
    public int getAllyIndex(){
        return allyIndex;
    }
    
    public int getOppIndex(){
        return oppIndex;
    }
}
