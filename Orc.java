package project4;

/** 
* Orc class-representation of an enemy type
* @author julian
*/

public class Orc extends Enemy 
{
    /** The name of the image file*/
    private String imageFile;

    /** 
    * constructor-creates the orc 
    * @param n is the name
    * @param q is the battle cry
    * @param l is the level
    * @param m is the max hit points
    * @param file is the image file name
    */
    public Orc(String n, String q, int l, int m, String file) 
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
