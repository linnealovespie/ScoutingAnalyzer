/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
/**
 *
 * @author may_884771
 */
public class Team {
     private ArrayList<Integer> alliance;
     private String number;
     private int totScore; 
     
     public Team(){
        number = "";
        alliance = new ArrayList<Integer>();
        totScore = 0;
    }
    
    public Team(String n)
    {
        number = n;
        /*System.out.println("Setting team to: " + number);*/
        alliance = new ArrayList<Integer>();
        totScore = 0;
    }
    
    public void setTeam(String n){
        number = n;
        alliance.clear();
 
    }
    
    public void addAlly(int a){
        alliance.add(a);
    }
    
    
    public ArrayList<Integer> getAllies(){
        return alliance;
    }
    
    public void addScore(int s){
        totScore += s;
    }
    

     @Override
    public String toString()
    {
        return number;
    }
}
