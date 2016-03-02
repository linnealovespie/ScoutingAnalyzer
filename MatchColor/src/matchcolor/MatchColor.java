/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchcolor;
import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author Linnea
 */
public class MatchColor {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        File myFile = new File("DataFiles/SampleMatches.xlsx");//CHANGE NAME TO CURRENT COMPETITION
        FileInputStream inp = new FileInputStream(myFile);
        XSSFWorkbook wb = new XSSFWorkbook(inp);
        Sheet sh = wb.getSheetAt(0);
        
        ArrayList matches = new ArrayList<>();
        
        Scanner sc = new Scanner(System.in);
        System.out.print("What team do you want to color code for first?");
        String teamNum = sc.next();
        
        Team t = new Team();
        
        CellStyle bff = wb.createCellStyle();
        bff.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        bff.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        //get the team numbers
        for(int i = 1; i < 11; i++)
        {
                Row row = matchList.getRow(i);
                Cell red1 = row.getCell(1);
                red1.setCellStyle(bff);
                Cell red2 = row.getCell(2);
                Cell blue1 = row.getCell(3);
                Cell blue2  = row.getCell(4);
                String red1Num = String.valueOf((int)red1.getNumericCellValue());
                String red2Num = String.valueOf((int)red2.getNumericCellValue());
                String blue1Num = String.valueOf((int)blue1.getNumericCellValue());
                String blue2Num = String.valueOf((int)blue2.getNumericCellValue());
                /*if(!red1.toString().contains("*"))
                    red1Num = String.valueOf((int)red1.getNumericCellValue());
                Cell red2 = row.getCell(2);
                if(!red2.toString().contains("*"))
                    red2Num = String.valueOf((int)red2.getNumericCellValue());
                Cell blue1 = row.getCell(3);
                if(!blue1.toString().contains("*"))
                    blue1Num = String.valueOf((int)blue1.getNumericCellValue());
                Cell blue2 = row.getCell(4);
                if(!blue2.toString().contains("*"))
                    blue2Num = String.valueOf((int)blue2.getNumericCellValue()); */
                Match match = new Match (red1Num, red2Num, blue1Num, blue2Num, teamNum, i);
                matches.add(match);
                t.addTeam(match);
        }
        
        for (Iterator it = matches.iterator(); it.hasNext();) {
            Match m = (Match) it.next();
            if(m.hasTeam())
            {
               m.setColour();
            
            }
            else if(m.hasAlly(t))
            {
                m.setAllyColor();
            }
        }
    }        
} 