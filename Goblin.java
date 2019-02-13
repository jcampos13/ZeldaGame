package project4;

/** 
* Globin class-represents an enemy type
* @author julian
*/

public class Goblin extends Enemy 
{
    /** The name of the image file*/
    private String imageFile;
    
    /** 
    * constructor-creates the goblin
    * @param n is the name
    * @param q is the battle cry 
    * @param m is the max hit points 
    * @param l is the level
    * @param file is the image file name
    */ 
    public Goblin(String n, String q, int l, int m, String file) 
    {
        super(n, q, l, m, file);
    }

    /** 
    * retrieves the name of the image file
    * @return the name of the file
    */
    public String getImage()
    {
        return imageFile;
    }
}
