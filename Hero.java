package project4;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import javax.imageio.ImageIO;

/** 
* Hero class-represents the player's character
* @author julian
*/

public class Hero extends Entity implements Magical
{ 
    /** The hero's inventory*/
    private ArrayList<Item>items;
    /** Hero's location in the map*/
    private Point location;
    /** The money the hero has*/
    private int gold;
    /** The array that holds the images for items in the inventory*/
    BufferedImage [] imgs;  
    
    /** 
    * constructor-creates the hero 
    * @param n is the player's name
    * @param q is the player's battle cry 
    */
    public Hero(String n, String q)
    {
        super(n,q,1,15);
        gold =10;
        items=new ArrayList();
        location=Map.getInstance().findStart();
        imgs=new BufferedImage[5];
    }
       
    /** 
    *retrieve the hero's location 
    * @return the location of the hero
    */
    public Point getLocation()
    {
        return location.getLocation();
    }
    
    /** 
    * changes the coordinates of the hero's location
    * @return the character the hero will encounter in the new location
    */  
    public char goNorth()
    {       
        int xBound=location.x;
    
        //checks for x coordinate bounds
        if(xBound>0)
        {        
            location.translate(-1,0);  
            Map.getInstance().reveal(location);
        }
        else
        {       
            //returns out-of-bounds character
            return 'b';
        }
        
        return Map.getInstance().getCharAtLoc(location);
    }
    
    /** 
    * changes the coordinates of the hero's location
    * @return the character the hero will encounter in the new location
    */
    public char goSouth()
    {
        
        int xBound=location.x;

        //checks for x coordinate bounds
        if(xBound<4)
        {
            location.translate(1,0); 
            Map.getInstance().reveal(location);
        }
        else
        {
            //returns out-of-bounds character
            return 'b';
        }
        return Map.getInstance().getCharAtLoc(location);
    }
    
    /** 
    * changes the coordinates of the hero's location
    * @return the character the hero will encounter in the new location
    */ 
    public char goEast()
    {
        
        int yBound=location.y;

        //checks for y coordinate bounds
        if(yBound<4)        
        {
            location.translate(0,1);  
            Map.getInstance().reveal(location);       
        }
        else
        {
            //returns out-of-bounds character
            return 'b';
        }
        return Map.getInstance().getCharAtLoc(location);
    }
    
    /** 
    * changes the coordinates of the hero's location
    * @return the character the hero will encounter in the new location
    */     
    public char goWest()
    {
        
        int yBound=location.y;

        //checks for y coordinate bounds
        if(yBound>0)
        {
            location.translate(0,-1);  
            Map.getInstance().reveal(location);
        }
        else
        {
           //returns out-of-bounds character
            return 'b';
        }
        
        return Map.getInstance().getCharAtLoc(location);
    }
    
    /** 
    * retrieves names of items in list
    * @return a concatenated string containing all the items
    */   
    public String getItems()
    {
        String itemStr="";
        if(items!=null)
        {
            for(Item i:items)
            {
                itemStr+=i.getName()+".";
            }
        }
        return itemStr;
    }
    
    /** 
    * displays the heros item collection 
    * @param g is the object that draws
    */    
    public void displayItems(Graphics g)
    {       
        int sideLength=65;
        int counter=0;
        
        for(Item i:items)
        {
            //get the right image
            if(i.getImagePath().equals("health.jpg"))
            {
                try
                {
                    imgs[counter]=ImageIO.read(new File("health.jpg"));                      
                }
                catch(IOException e)
                {

                }
            }
            if(i.getImagePath().equals("boots.jpg"))
            {
                try
                {
                    imgs[counter]=ImageIO.read(new File("boots.jpg"));
                }
                catch(IOException e)
                {

                }                   
            }
            if(i.getImagePath().equals("bog.jpg"))
            {
                try
                {
                    imgs[counter]=ImageIO.read(new File("bog.jpg"));
                }
                catch(IOException e)
                {

                }                   
            }
            if(i.getImagePath().equals("belt.jpg"))
            {
                try
                {
                    imgs[counter]=ImageIO.read(new File("belt.jpg"));
                }
                catch(IOException e)
                {

                }                   
            }
            if(i.getImagePath().equals("gloves.jpg"))
            {
                try
                {
                    imgs[counter]=ImageIO.read(new File("gloves.jpg"));
                }
                catch(IOException e)
                {

                }                   
            }
            if(i.getImagePath().equals("helm.jpg"))
            {
                try
                {
                    imgs[counter]=ImageIO.read(new File("helm.jpg"));
                }
                catch(IOException e)
                {

                }                   
            }
            if(i.getImagePath().equals("ring.jpg"))
            {
                try
                {
                    imgs[counter]=ImageIO.read(new File("ring.jpg"));
                }
                catch(IOException e)
                {
                        
                }                   
            }
            if(i.getImagePath().equals("ringmail.jpg"))
            {
                try
                {
                    imgs[counter]=ImageIO.read(new File("ringmail.jpg"));
                }
                catch(IOException e)
                {

                }                   
            }                
            counter++;              
        }  
        counter++;
        
        //displays images
        for(int x=0;x<items.size();x++)
        {
            int y=710+sideLength*x;  
            imgs[x].getScaledInstance(5, 5, Image.SCALE_SMOOTH);
            g.drawImage(imgs[x], y, 250, 70,65, null);
        }
    }
    
    /** 
    * adds item to inventory
    * @param i is the item that is being picked up
    * @return the condition for added items (true if picked up and false if not)
    */ 
    public boolean pickUpItem(Item i)
    {
        boolean added;
        if(items.size()<5)
        {
            items.add(i);
            added=true;
        }
        else
        {
            added=false;
        }
        return added;
    }
    
    /** 
    * removes the item from hero's inventory
    * @param index is the specific index of the item to remove
    * @return item removed
    */ 
    public Item removeItem(int index)
    {
        Item removedItem;
        removedItem=items.get(index-1);
        items.remove(index-1);
        return removedItem;
    }
    
    /** 
    * enables hero to attack
    * @param e is the entity being attacked
    */    
    @Override
    public void attack(Entity e)
    {
        Scanner scan=new Scanner(System.in);
        int option;
        Random random=new Random();
        int damage=random.nextInt((4 - 1 + 1) + 1*e.getLevel());       
    }
    
    /** 
    * returns the total money the player has
    * @return the money
    */ 
    public int getGold()
    {
        return gold;
    }
    
    /** 
    * returns the total items in inventory
    * @return the number of items
    */     
    public int getNumItems()
    {
        int nOfItems=items.size();
        return nOfItems;
    }
    
    /** 
    * adds money to the hero's account
    * @param g is the deposit amount
    */ 
    public void collectGold(int g)
    {
        gold+=g;
    }
    
    /** 
    * subtracts money from the hero's account
    * @param g is the withdraw amount
    */     
    public void spendGold(int g)
    {
        gold-=g;
    }    
 
    /** 
    * checks if the hero's inventory contains a potion
    * @return yes or no
    */ 
    public boolean hasPotion()
    {
        if(items.contains("Health Potion"))
        {
            return true;
        }
        else
        { 
            return false;
        }
    }  

    /** 
    * hero's magic attack
    * @return the damage
    */ 
    @Override
    public int magicMissile() 
    {
        Random random=new Random();
        int randomInt;
        randomInt=random.nextInt(7)+1;
        return randomInt;
    }

    /** 
    * hero's magic attack
    * @return the damage
    */ 
    @Override
    public int fireBall() 
    {
        Random random=new Random();
        int randomInt;
        randomInt=random.nextInt(7)+1;
        return randomInt;   
    }
    
    /** 
    * hero's magic attack
    * @return the damage
    */ 
    @Override
    public int thunderClap() 
    {
        Random random=new Random();
        int randomInt;
        randomInt=random.nextInt(7)+1;
        return randomInt;
    }
}

  