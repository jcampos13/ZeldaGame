package project4;

import java.util.Random;

/** 
* Enemy class-represents the enemy
* @author julian
*/

public abstract class Enemy extends Entity
{
    
    /** The item each hero holds*/
    private Item item;
    /** The image file for the enemy*/
    private String file;

    /** 
    * constructor-creates the enemy 
    * @param n is the enemy's name
    * @param q is the enemy's battle cry
    * @param l is the enemies level
    * @param m is the max hit points
    * @param f is the file name
    */
    public Enemy(String n, String q, int l, int m, String f)
    {
        super(n,q,l,m);
        file=f;
        this.item=ItemGenerator.getInstance().generateItem();
    }
    
    /** 
    * returns the item left by the enemy
    * @return the item
    */    
    public Item getItem()
    {
        return item;
    }

    /** 
    * returns the file name
    * @return the name of the file
    */
    public String getFileName()
    {
        return file;
    }
    
    /** 
    * attack method for enemy
    * @param e is the entity receiving the attack 
    */
    @Override
    public void attack(Entity e) 
    {
        Random random=new Random();
        int damage=random.nextInt((4 - 1 + 1) + 1)*e.getLevel();
        System.out.println(getName() + " hits " + e.getName() + " with " + damage + " damage.");
        e.takeDamage(damage);
    }  
}

