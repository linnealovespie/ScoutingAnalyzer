/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchcolor;
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

/**
 *
 * @author Linnea
 */
public class MatchColor {
    
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
    
    private  static XSSFCellStyle teamStyle;
    private static XSSFColor teamColor;
    private  static XSSFCellStyle allyStyle;
    private static XSSFColor allyColor;
    private  static XSSFCellStyle opponentStyle;
    private static XSSFColor opponentColor;
    
    
    public MatchColor() throws FileNotFoundException, IOException{
        //----------MATCHES DATA FILE
        myFile = new File("../DataFiles/SampleMatches.xlsx");//CHANGE NAME TO CURRENT COMPETITION
        inp = new FileInputStream(myFile);
        wb = new XSSFWorkbook(inp);
        sh = wb.getSheetAt(0);
        fileOut = new FileOutputStream("../DataFiles/SampleOutput.xls");
        
        //----------TEAM NUMBER DATA FILE
        f1 = new File("../DataFiles/Regionals_Kane_Teams.xlsx");
        inp2 = new FileInputStream(f1);
        wb2 = new XSSFWorkbook(inp2);
        sh1 = wb2.getSheetAt(0);
        
        
        //----------CELL STYLES----------//
         //Your Team style
        teamStyle = (XSSFCellStyle) wb.createCellStyle();
        /*teamStyle.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());*/
        XSSFColor teamColor = new XSSFColor(Color.decode("#98FB98"));
        teamStyle.setFillForegroundColor(teamColor);
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

    public static ArrayList<String> getTeamNums(){
        Row tRow;
        Cell teamCell;
        ArrayList<String> teamNumbers = new ArrayList<>();
        for(int i = 1; i < 40; i++)
        {
            tRow = sh1.getRow(i);
            teamCell = tRow.getCell(0);
            String teamNum = String.valueOf((int)teamCell.getNumericCellValue());
            teamNumbers.add(teamNum);
            //System.out.println("Adding team" + teamNum);
        }
        return teamNumbers;
    }
    
    public void setColor(String teamNum, Match m, Sheet teamSheet)
    {
           Row matchRow = teamSheet.getRow(m.getRowNum());
                if(teamNum.equals(m.getTeam(0)))
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
                else if(teamNum.equals(m.getTeam(1)))
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
                else if(teamNum.equals(m.getTeam(2)))
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
                else if(teamNum.equals(m.getTeam(3)))
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
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        ArrayList matches = new ArrayList<>();
        MatchColor mc = new MatchColor();
        ArrayList<String> teamNums = new ArrayList<>();
        teamNums = getTeamNums();
        
        Scanner sc = new Scanner(System.in);
        /*System.out.println("What team do you want to color code for first?");
        String teamNum = sc.next();
        teamNum.trim();*/
        System.out.println("How many matches are there?");
        int totalMatches = sc.nextInt();
        
        Team t;
        Sheet teamSheet;
        Match match;
        Row teamRow;
        Cell teamCell;
       
        fileOut = new FileOutputStream("../DataFiles/SampleMatches.xls");
        for(int j = 0; j < teamNums.size(); j++)    
        {
            teamSheet = wb.getSheetAt(j);
            t = new Team(teamNums.get(j));
            //t = new Team(teamNum);
            teamRow = teamSheet.getRow(10);
            teamCell = teamRow.createCell(7);
            teamCell.setCellValue(teamNums.get(j));
             //Load in each match
            for(int i = 1; i <= totalMatches; i++)
            {
                    Row row = teamSheet.getRow(i);
                    Cell red1 = row.getCell(1);
                    Cell red2 = row.getCell(2);
                    Cell blue1 = row.getCell(3);
                    Cell blue2  = row.getCell(4);
                    String red1Num = String.valueOf((int)red1.getNumericCellValue());
                    String red2Num = String.valueOf((int)red2.getNumericCellValue());
                    String blue1Num = String.valueOf((int)blue1.getNumericCellValue());
                    String blue2Num = String.valueOf((int)blue2.getNumericCellValue());
                    /*System.out.println("RED 1: " + red1Num + " RED 2: " + red2Num + " BLUE1: " + blue1Num + " BLUE2: " + blue2Num);*/
                    match = new Match(red1Num, red2Num, blue1Num, blue2Num, teamNums.get(j), i);
                    //match = new Match(red1Num, red2Num, blue1Num, blue2Num, teamNum, i);
                    t.addTeam(match);
                    matches.add(match);
                    
            }
            //Loop through and determine color
            for (Iterator it = matches.iterator(); it.hasNext();) {
                Match m = (Match) it.next();
                if(m.hasTeam())
                {
                   mc.setColor(teamNums.get(j), m, teamSheet);
                   //mc.setColor(teamNum, m, teamSheet);
                   //System.out.println("It has team");

                }
                else if(m.hasAlly(t))
                {
                    Row matchRow = teamSheet.getRow(m.getRowNum());
                    Cell allyC = matchRow.getCell(m.getAllyIndex() +1);
                    allyC.setCellStyle(allyStyle);
                    //System.out.println("It has ally");
                }
                else if(m.hasOpp(t))
                {
                    Row matchRow = teamSheet.getRow(m.getRowNum());
                    Cell oppC = matchRow.getCell(m.getOppIndex() +1);
                    oppC.setCellStyle(opponentStyle);
                    //System.out.println("It has enemy");
                }
           }
            
            t.resetTeam();
        }
       
        wb.write(fileOut);
        fileOut.close();
    }        
} 