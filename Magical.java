package project4;

/** 
 * Magical interface-methods to be overridden by classes implementing it 
 * @author julian
 */

public interface Magical 
{   
    /** The menu for the attacks*/
    public static final String MAGIC_MENU="1.Magic Missile\n2.Fireball\n3.Thunderclap";
    /** magic attack*/
    public int magicMissile();
    /** magic attack*/
    public int fireBall();
    /** magic attack*/
    public int thunderClap();
}

