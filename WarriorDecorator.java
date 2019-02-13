package project4;

/** 
* WarriorDecorator class-decorates enemies
* @author julian
*/

public class WarriorDecorator extends Decorator
{
    /** 
    * constructor-creates the warrior decorator object
    * @param e is the enemy to be decorated
    */
    public WarriorDecorator(Enemy e)
    {
        super(e,e.getName()+" Warrior ",e.getQuip(),e.getLevel(),e.getMaxHp()+2, e.getFileName());
    }       
}
