/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchanalysis;
import java.util.ArrayList;
/**
 *
 * @author may_884771
 */
public class Team {
     private static ArrayList<String> alliance = new ArrayList<>();
     private String number;
     
     public Team(){
        number = "";
    }
    
    public Team(String n)
    {
        number = n;
        /*System.out.println("Setting team to: " + number);*/
    }
    
    public void setTeam(String n){
        number = n;
        alliance.clear();
 
    }
    
    public ArrayList<String> getAllies(){
        return alliance;
    }
    
    

     @Override
    public String toString()
    {
        return number;
    }
}
