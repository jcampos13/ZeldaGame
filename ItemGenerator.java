package project4;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.util.Random;

/** 
* ItemGenerator class-generates items from text file 
* @author julian
*/

public class ItemGenerator 
{
    /** Instance of the object itself (singleton)*/
    private static ItemGenerator instance=null;
    /** list of items*/
    private ArrayList<Item> itemList;
    
    /** 
    * constructor - reads the items from text file 
    */
    private ItemGenerator()
    {     
        itemList=new ArrayList<>();
        
        //attempts to read from .txt file and stores the name,value, and image file name of the item into an arraylist
        try
        {
            
            Scanner scan=new Scanner(new File("ItemList.txt"));
               
            //checks for next line in the file
            while(scan.hasNextLine())
            {           
                String line=scan.nextLine();
            
                //removes the comma from the string
                String [] stringTokens = line.split(",");
                           
                //contructs an Item 
                Item item = new Item(stringTokens[0],Integer.parseInt(stringTokens[1]),stringTokens[2]);
            
                //adds it to the list
                itemList.add(item);
            }
            scan.close();        
        }
        
        catch(IOException exception)
        {
            System.out.print(exception);
        }       
    }
    
    /** 
    * method representative of a singleton
    * @return the instance 
    */
    public static ItemGenerator getInstance()
    {
        if(instance==null)
        {
            instance=new ItemGenerator();
        }
        return instance;
    }
    
    /** 
    * generates random item from list 
    * @return the item generated
    */
    public Item generateItem()
    {
        Random randomGen = new Random();
        int index = randomGen.nextInt(itemList.size());
        Item item=itemList.get(index);
        return item.clone();
    }
     
    /** 
    * gets the potion from the list of items
    * @return the potion
    */
    public Item getPotion()
    {
        return itemList.get(0);           
    }
}

