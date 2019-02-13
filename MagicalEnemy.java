package project4;

import java.util.Random;

/** 
* MagicalEnemy class-creates an enemy of magical type
* @author julian
*/

public class MagicalEnemy extends Enemy implements Magical
{
    
    /** 
    * constructor - calls the super constructor to create enemy
    * @param n is the name of the enemy
    * @param q is the battle cry
    * @param I is the level
    * @param m is the max hit points
    * @param file is the name of the image file
    */
    public MagicalEnemy(String n, String q, int I, int m, String file)
    {
        super(n,q,I,m, file);
    }
    
    /** 
    * performs an attack 
    * @param e is the entity receiving the attack
    */
    @Override
    public void attack(Entity e)
    {
        Random random=new Random();
        int randomInt;
        
        randomInt=random.nextInt(3 - 1 + 1) + 1;
        
        switch(randomInt)
        {
            case 1:
            e.takeDamage(magicMissile());
            System.out.println(getName() + " hits " + e.getName()+ " with magic missile for " + randomInt + " damage. ");
            break;
            case 2:
            e.takeDamage(fireBall());
            System.out.println(getName() + " hits " + e.getName()+ " with fireball for " + randomInt + " damage. ");
            break;
            case 3:
            e.takeDamage(thunderClap());
            System.out.println(getName() + " hits " + e.getName()+ " with thunderclap for " + randomInt + " damage. ");
            break;
        }
    }

    /** 
    * magical attack
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
    * magical attack
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
    * magical attack
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

