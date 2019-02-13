package project4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/** 
* Enemy generator class-generates enemies from a set of base classes
* @author julian
*/

public class EnemyGenerator 
{
    
    /**instance of the object itself (singleton)*/
    private static EnemyGenerator instance=null;
    
    /** 
    * constructor - used to create single instance of this class 
    * @param i supplies the enemy generator with an instance of the ItemGenerator
    */
    private EnemyGenerator()
    {  
        
    }
    
    /** 
    * method representative of a singleton. checks for instance of object.
    * @return the instance of the object
    */ 
    public static EnemyGenerator getInstance()
    {
        if(instance==null)
        {
            instance=new EnemyGenerator();
        }
        return instance;
    }
    
    /** 
    * creates an enemy at random based on hero's level
    * @param level is the level this enemy will have
    * @return the random enemy 
    */
    public Enemy generateEnemy(int level)
    {
        Random r=new Random();
        int randomNum=r.nextInt(4)+1;
        
        //if the hero is in level one, create non-decorated enemies, return random one
        if(level==1)
        {
            if(randomNum==1)
            {
                return new WarriorDecorator(new Goblin("Goblin","Ack Ack",level,2,"goblin.jpg"));
            }
            else if (randomNum==2)
            {
                return new WarriorDecorator(new Troll("Troll","Ugh uh",level,5,"troll.jpg"));
            }
            else if(randomNum==3)
            {
                return new WarriorDecorator(new Orc("Orc","Blarghh",level,4,"orc.jpg"));
            }
            else if(randomNum==4)
            {
                return new WarriorDecorator(new Froglok("Froglok","Croooak",level,5, "froglok.jpg"));
            }
        }
      
        //if level is greater than 1, create decorated enemies, return random one
        else 
        {
            if(randomNum==1)
            {
                return new WarlockDecorator(new Goblin("Goblin","Ack Ack",level,2,"goblin.jpg"));
            }
            else if (randomNum==2)
            {
                return new WarlockDecorator(new Troll("Troll","Ugh uh",level,5,"troll.jpg"));
            }
            else if(randomNum==3)
            {
                return new WarlockDecorator(new Orc("Orc","Blarghh",level,4,"orc.jpg"));
            }
            else if(randomNum==4)
            {
                return new WarlockDecorator(new Froglok("Froglok","Croooak",level,5, "froglok.jpg"));
            }
        }
        return null;
    }    
}

