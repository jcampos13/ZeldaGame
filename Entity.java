package project4;

/** 
* Entity class-represents an entity
* @author julian
*/

public abstract class Entity 
{  
    /** The name*/
    private String name;
    /** The battle cry*/
    private String quip;
    /** The level*/
    private int level;
    /** The max hit points*/
    private int maxHp;
    /** The hit points*/
    private int hp;
    
    /** 
    * constructor-creates the entity
    * @param n is the name
    * @param q is the battle cry 
    * @param m is the max hit points 
    * @param l is the level
    */ 
    public Entity(String n, String q, int l, int m){
        this.name=n;
        this.quip=q;
        this.level=l;
        this.maxHp=m;
        this.hp=m;
    }
    
    /** 
    * retrieves the name of the entity
    * @return the name
    */
    public String getName()
    {
        return name;
    }
  
    /** 
    * retrieves the battle cry of the entity
    * @return the battle cry
    */
    public String getQuip()
    {
        return quip;
    }

    /** 
    * retrieves the level
    * @return the level
    */
    public int getLevel()
    {
        return level;
    }
    
    /** 
    * retrieves the  hit points
    * @return the hit points
    */   
    public int getHp()
    {
        return hp;
    }

    /** 
    * retrieves the max hit points
    * @return the max hit points
    */
    public int getMaxHp()
    {
        return maxHp;
    }
    
    /** 
    * abstract attack method that needs to be overridden
    * @param e is the recipient of the attack
    */    
    public abstract void attack(Entity e);

    /** 
    * deducts the hit points
    * @param h is the damage
    */
    public void takeDamage(int h)
    {
        hp-=h;
    }
    
    /** 
    * increases the level
    */
    public void increaseLevel()
    {
        level++;
    }
    
    /** 
    * increases the max hit points
    * @param h is the factor which increases the max hit points
    */   
    public void increaseMaxHP(int h)
    {
        maxHp+=h;      
    }
    
    /** 
    * decreases the max hit points
    * @param h is the factor which decreases the max hit points
    */      
    public void decreaseMaxhp(int h)
    {
        maxHp-=h;
    }
    
    /** 
    * raises the hit points
    * @param h is the amount by which the hit points increase
    */     
    public void heal(int h)
    {
        hp+=h;
    }

}

