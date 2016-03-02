/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchcolor;

import java.util.ArrayList;

/**
 *
 * @author Linnea
 */
public class Team {
    private static ArrayList<String> alliance = new ArrayList<>();
    private static ArrayList<String> opponent = new ArrayList<>();
    private String number;
    
    public ArrayList<String> getAllies(){
        return alliance;
    }
    
    public ArrayList<String> getOpponent(){
        return opponent;
    }
    
    
    public void addTeam(Match m){
        ArrayList<String> teams = m.getTeams();
        if(teams.get(0).equals(number))
        {
            alliance.add(teams.get(1));
            opponent.add(teams.get(2));
            opponent.add(teams.get(3));
        }
        
        else if(teams.get(1).equals(number)){
            alliance.add(teams.get(0));
            opponent.add(teams.get(2));
            opponent.add(teams.get(3));
        }
        
        else if(teams.get(2).equals(number)){
            alliance.add(teams.get(3));
            opponent.add(teams.get(1));
            opponent.add(teams.get(0));
        }
        
        else if(teams.get(3).equals(number)){
            alliance.add(teams.get(2));
            opponent.add(teams.get(1));
            opponent.add(teams.get(0));
        }
    }
    
    
}
