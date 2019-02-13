package project4;

/** 
* Troll class-representation of an enemy type
* @author julian
*/

public class Troll extends Enemy 
{

    /** The name of the image file*/
    private String imageFile;
    
    /** 
    * constructor-creates the troll 
    * @param n is the name
    * @param q is the battle cry
    * @param l is the level
    * @param m is the max hit points
    * @param file is the image file name
    */
    public Troll(String n, String q, int l, int m, String file)
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
