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
    
    public void addTeam(Match m){
        ArrayList<String> teams = m.getTeams();
        if(teams.get(0).equals(number) && !alliance.contains(teams.get(0)))
        {
            alliance.add(teams.get(1));
        }
        if(teams.get(1).equals(number) && !alliance.contains(teams.get(1))){
            alliance.add(teams.get(0));
        }
        else if(teams.get(2).equals(number) && !alliance.contains(teams.get(2))){
            alliance.add(teams.get(3));
        }
        else if(teams.get(3).equals(number) && !alliance.contains(teams.get(3))){
            alliance.add(teams.get(2));
        }
    }

     @Override
    public String toString()
    {
        return number;
    }
}
