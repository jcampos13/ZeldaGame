package project4;
import java.util.Random;

/** 
* WarlockDecorator class-decorates enemies
* @author julian
*/

public class WarlockDecorator extends Decorator implements Magical 
{
    /** 
    * constructor-creates the warlock decorator object
    * @param e is the enemy to be decorated
    */
    public WarlockDecorator(Enemy e)
    {
        super(e,e.getName()+" Warlock",e.getQuip(),e.getLevel(),e.getMaxHp()+1,e.getFileName());
    }   

    
    /** 
    * magical attack method
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
    * magical attack method
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
    * magical attack method
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
