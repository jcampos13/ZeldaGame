package project4;

/** 
* Decorator class-represents a decorator 
* @author julian
*/

public abstract class Decorator extends Enemy
{
    /** Enemy to be decorated*/
    private Enemy enemy;
    
    /** 
    * constructor-creates a decorated enemy
    * @param e is the enemy to be decorated
    * @param n is the name
    * @param l is the level
    * @param q is the battle cry
    * @param m is the max hit points
    * @param file is the image file name
    */
    public Decorator(Enemy e, String n, String q, int l, int m, String file )
    {
        super(n,q,l,m, file);
        enemy=e;
    }
    
    /** 
    * attack method
    */
    @Override
    public void attack(Entity e)
    {
        enemy.attack(e);
    }
    
}
