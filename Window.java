package project4;

import javax.swing.*;

/** 
* Window class-represents a window object
* @author julian
*/

public class Window extends JFrame
{   
    /** 
    * constructor-creates the window object
    */
    Window()
    {
        MainPanel mainP=new MainPanel();
        setBounds(100,100,1100,700);
        setTitle("Dungeon Master FX");
        setContentPane(mainP);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread t=new Thread(mainP);
        t.start();
    }   
}
