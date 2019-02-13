package project4;

/** 
* Item class-represents an item
* @author julian
*/

public class Item implements Prototype
{
    
    /** The name of the item*/    
    private String name;
    /** The value of the item*/
    private int value;  
    /** The name of the image file*/
    private String image;
    
    /** 
    * constructor-creates the item
    * @param n is the name
    * @param v is the value
    * @param im is the image file name
    */ 
    public Item(String n, int v, String im)
    {
        this.name=n;
        this.value=v;
        this.image=im;
    }
    
    /** 
    * Constructor representative of a prototype
    * @param i is the item 
    */
    public Item(Item i)
    {
        if(i!=null)
        {
            name=i.name;
            value=i.value;
            image=i.image;
        }
    }
    
    /** 
    * gets the name of the item
    * @return the name of the item
    */    
    public String getName()
    {
        return name;
    }
    
    /** 
    * gets the value of the item
    * @return the value of the item
    */    
    public int getValue(){
        return value;
    }
    
    /** 
    * method representative of a prototype
    * @return the clone of the item
    */
    @Override
    public Item clone() 
    {
       return new Item(this); 
    }
    
    /** 
    * returns the image file name
    * @return the name of the file
    */
    public String getImagePath()
    {
        return image;
    }
}

