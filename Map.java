package project4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.*;

/** 
* Map class-representation of the map 
* @author julian
*/

public class Map extends Thread
{   
    /** Instance of the object itself (singleton)*/
    private static Map instance=null;
    /** The map where the game will be played*/
    private char [][] map;
    /** will reveal the areas that have been visited*/
    private boolean [][] revealed;
    
    /** 
    * constructor-creates the map object
    */
    private Map()
    {
       map= new char [5][5];
       revealed=new boolean[5][5];
    }
    
    /** 
    * draws the map
    * @param g is the object that allows drawing
    * @param h is the hero
    */
    public void draw(Graphics g, Hero h)
    {
        
        int sideLength=115;
        System.out.println(findStart().x);
        System.out.println(findStart().y);
        for ( int i=0; i<map.length; i++ )
        {
            int x=90+sideLength*i;
            for( int j=0; j<map[i].length; j++ )
            {
                int y=30+sideLength*j;
                g.drawRect(x,y,sideLength, sideLength);

                if(h.getLocation().x==j&&h.getLocation().y==i)
                {
                   g.drawString(h.getName(), x+sideLength/2, y+sideLength/2);
                }     
                else if(revealed[j][i]==true)
                {
                    g.drawString(Character.toString(map[j][i]), x+sideLength/2, y+sideLength/2);
                }
            }  
        }
    }
    
    /** 
    * method representative of a singleton
    * @return the instance 
    */
    public static Map getInstance()
    {
        if(instance==null)
        {
            instance =new Map();
        }
        return instance;
    }
    
    /** 
    * loads the map 
    * @param MapNum is the map level where the player is located
    */
    public void loadMap(int MapNum)
    {       
        //attemps to load .txt file
        try
        {
            String s="Map"+MapNum+".txt";
            File file=new File(s);
            Scanner sc=new Scanner(file);       
        
            while(sc.hasNextLine())
            {          
                for( int i=0; i<5; i++ )
                {     
                    for( int j=0; j<5; j++ )
                    {
                                                            
                        //fills up the array with characters
                        char currentCharacter=sc.next().charAt(0);
                        map[i][j]=currentCharacter;
                        revealed[i][j]=false;          
                    }
                }  
            }         
            sc.close();       
        }
        catch(IOException exception)
        {
            System.out.println(exception);
        }
    }
    
    /** 
    * find the initial location at each map
    * @return the player's starting point
    */    
    public Point findStart()
    {        
        Point location=new Point();
        
        //iterates through array to locate the "s" character
        for ( int i=0; i<map.length; i++ )
        {
            for( int j=0; j<map[i].length; j++ )
            {
                if( map[i][j] == 's' )
                {        
                    revealed[i][j]=true;
                    location.setLocation(i,j);
                    break;
                }
            }
        }
        
        return location;
    }   
 
    /** 
    * reveals the point
    * @param p is the location that should be revealed
    */     
    public void reveal(Point p)
    {
        revealed[p.x][p.y]=true;
    }
    
    /** 
    * replaces character at location with "n"
    * @param p is the location where the character exists
    */    
    public void removeCharAtLoc(Point p)
    {       
        char n;
        if( map[p.x][p.y]=='s' )
        {
            n='s';
        }
        else 
        {
            n='n';
        }
        n=map[p.x][p.y];
    }

    /** 
    * returns the character at the new location
    * @param p is the location where the character exists
    * @return the character that the location
    */     
    public char getCharAtLoc(Point p)
    {
        return map[p.x] [p.y];
    }
}
