package project4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/** 
* MainPanel class-blueprint for a panel object
* @author julian
*/

public class MainPanel extends JPanel implements ActionListener, Runnable, KeyListener, MouseListener
{    
    /** The condition of the rooms (in room or not)*/
    boolean outRoom=true;
    /** The condition needed to be true to sell items through key events)*/
    boolean buyItem=false;
    /** The hero playing*/
    private Hero hero;
    /** The counter for loading maps*/
    private int map=1;
    /** The enemy encountered*/
    private Enemy enemyEncountered;
    /** The buttons in the panel*/
    private JButton fight, run, buy, sell, done, begin, physical, magical, thunderClap, magicMissile, fireBall ;
    /** The labels in the panel*/
    private JLabel charInfo, inventory, name, level, hp, credit, entry1, entry2, actionEntry, defeatEntry, receiveEntry, enemyAttack, enemyQuip, heroQuip, runLabel, enemy, sellLabel;
    /** The text fields in the panel*/
    private JTextField nameField, levelField, hpField, creditField, cryField, enemyHp ;
    
    /** 
    * constructor - creates a panel with components 
    */
    MainPanel()
    {
        boolean start=true;
        nameField=new JTextField();
        cryField=new JTextField();
        int error=JOptionPane.ERROR_MESSAGE;
        
        //creates hero object by prompting for name and battle cry
        while(start==true)
        {
            Object[] message = 
            {
                "Name:", nameField,
                "BattleCry:", cryField       
            };

            int option = JOptionPane.showConfirmDialog(null, message, "New Game", JOptionPane.OK_CANCEL_OPTION);
            
            //if user clicks "OK"
            if (option == JOptionPane.OK_OPTION) 
            {
                //create hero if name and cry are not empty
                if(!nameField.getText().isEmpty() && !cryField.getText().isEmpty() )
                {
                    Map.getInstance().loadMap(1);
                    hero=new Hero(nameField.getText(),cryField.getText());  
                    start=false;
                }
                //let the user know if input is invalid
                else if(nameField.getText().isEmpty() || cryField.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(this, "Invalid Input", "Warning", error);
                }
            }  
            //exits program if "CANCEL" or window is closed
            else 
            {
                System.exit(0);
            }
        }                
        
        this.setLayout(null);
        addKeyListener(this);
        setFocusable(true);
        addMouseListener(this);
                
        //BUTTONS
        fight=new JButton("Fight!");
        fight.setBounds(735, 480, 100, 30);
        fight.addActionListener(this);
        fight.setBackground(Color.white);
        fight.setVisible(false);
        
        thunderClap=new JButton("ThunderClap");
        thunderClap.setVisible(false);
        thunderClap.setBounds(710, 540, 150, 30);
        thunderClap.setBackground(Color.white);
        thunderClap.addActionListener(this);
        
        magicMissile=new JButton("Magic Missile");
        magicMissile.setVisible(false);
        magicMissile.setBounds(710, 570, 150, 30);
        magicMissile.setBackground(Color.white);
        magicMissile.addActionListener(this);
        
        physical=new JButton("Physical");
        physical.setVisible(false);
        physical.setBounds(735, 520, 100, 30);
        physical.setBackground(Color.white);
        physical.addActionListener(this);
        
        magical=new JButton("Magical");
        magical.setVisible(false);
        magical.setBounds(735, 550, 100, 30);
        magical.setBackground(Color.white);
        magical.addActionListener(this);
        
        run=new JButton("Run Away");
        run.setBounds(735, 510, 100, 30);
        run.addActionListener(this);
        run.setBackground(Color.white);
        run.setVisible(false);
        
        buy=new JButton("Buy Potion");
        buy.setBackground(Color.white);
        buy.addActionListener(this);
        buy.setBounds(825, 500, 100, 20);
        buy.setVisible(false);       
        
        sell=new JButton("Sell Items");
        sell.addActionListener(this);
        sell.setBackground(Color.white);
        sell.setBounds(825, 520, 100, 20);
        sell.setVisible(false);
        
        done=new JButton("Done");      
        done.setBackground(Color.white);
        done.addActionListener(this);
        done.setBounds(825, 540, 100, 20);
        done.setVisible(false);           
        
        fireBall=new JButton("FireBall");
        fireBall.setVisible(false);
        fireBall.setBounds(710, 510, 150, 30);
        fireBall.setBackground(Color.white);
        fireBall.addActionListener(this);
                
        //LABELS
        enemyAttack=new JLabel();
        enemyAttack.setBounds(710, 420, 200, 40);
        enemyAttack.setVisible(false);
        enemyAttack.setForeground(Color.white);
        
        runLabel=new JLabel();
        runLabel.setBounds(710, 410, 300, 20);
        runLabel.setVisible(false);
        runLabel.setForeground(Color.white);
        
        enemyQuip=new JLabel();
        enemyQuip.setBounds(710, 400, 250, 40);
        enemyQuip.setVisible(false);
        enemyQuip.setForeground(Color.white);
        
        enemy=new JLabel();
        enemy.setBounds(870, 440, 150, 180);
        enemy.setVisible(false);
        enemy.setForeground(Color.white);
        
        heroQuip=new JLabel();
        heroQuip.setBounds(710, 440, 200, 40);
        heroQuip.setVisible(false);
        heroQuip.setForeground(Color.white);       
        
        actionEntry=new JLabel();
        actionEntry.setVisible(false);
        actionEntry.setBounds(720, 450, 200, 40);
        actionEntry.setForeground(Color.white);
               
        entry2=new JLabel("Entry");
        entry2.setBounds(855, 350, 200, 40);
        entry2.setForeground(Color.white);
        entry2.setVisible(true);
        
        defeatEntry=new JLabel();
        defeatEntry.setVisible(false);
        defeatEntry.setBounds(710, 420, 200, 40);
        defeatEntry.setForeground(Color.white);
        
        receiveEntry=new JLabel();
        receiveEntry.setVisible(false);
        receiveEntry.setBounds(710, 440, 200, 40);
        receiveEntry.setForeground(Color.white);
             
        entry1=new JLabel();
        entry1.setBounds(710, 390, 350, 20);
        entry1.setBackground(Color.white);
        entry1.setVisible(true);
        entry1.setForeground(Color.white); 
        entry1.setText("You entered the dungeon...");
        
        charInfo=new JLabel("Player Information");
        charInfo.setBounds(820,15,150,30);
        charInfo.setVisible(true);
        charInfo.setForeground(Color.white);
        
        name=new JLabel("Name: ");
        name.setBounds(710, 70, 100, 30);
        name.setVisible(true);
        
        level=new JLabel("Level: ");
        level.setBounds(710, 95, 100, 30);
        level.setVisible(true);
        
        hp=new JLabel("HP: ");
        hp.setBounds(710,115, 100,30);
        hp.setVisible(true);  
        
        credit=new JLabel("$: ");
        credit.setBounds(710, 135, 100, 30);
        credit.setVisible(true);
        
        inventory=new JLabel("Inventory: ");
        inventory.setBounds(710, 200, 100, 30);
        inventory.setVisible(true);
        
        sellLabel=new JLabel("Choose something to sell");
        sellLabel.setBounds(710, 400, 150, 30);
        sellLabel.setVisible(false);
        sellLabel.setForeground(Color.white);
        
        //TEXT FIELDS
        nameField.setVisible(true);
        nameField.setBounds(770,75, 80, 20);
        nameField.setEditable(false);
        nameField.setText(hero.getName());
               
        cryField.setBackground(Color.white);
        cryField.setBounds(780,190,100,30);
        cryField.setVisible(false);
        
        enemyHp=new JTextField();
        enemyHp.setVisible(false);
        enemyHp.setBounds(780, 440, 150, 20);
        enemyHp.setEditable(false);
        enemyHp.setBackground(Color.white);             
        
        levelField=new JTextField(Integer.toString(hero.getLevel()));
        levelField.setBackground(Color.white);
        levelField.setBounds(770,100,30,20);
        levelField.setVisible(true);
        levelField.setEditable(false);    
        
        hpField=new JTextField();
        hpField.setBackground(Color.white);
        hpField.setVisible(true);
        hpField.setBounds(770, 125, 50, 20);
        hpField.setEditable(false);
        hpField.setText(Integer.toString(hero.getHp())+"/"+hero.getMaxHp());

        
        creditField=new JTextField();
        creditField.setBounds(770, 150, 30, 20);
        creditField.setVisible(true);
        creditField.setBackground(Color.white);
        creditField.setEditable(false);
        creditField.setText(Integer.toString(hero.getGold()));
        
        this.add(fight);
        this.add(run);
        this.add(buy);
        this.add(sell);
        this.add(done);
        this.add(nameField);
        this.add(cryField);
        this.add(charInfo);
        this.add(name);
        this.add(level);
        this.add(levelField);
        this.add(hp);
        this.add(hpField);
        this.add(credit);
        this.add(creditField);
        this.add(inventory);
        this.add(entry1);
        this.add(enemyHp);
        this.add(physical);
        this.add(magical);
        this.add(magicMissile);
        this.add(thunderClap);
        this.add(fireBall);
        this.add(entry2);
        this.add(actionEntry);
        this.add(defeatEntry);
        this.add(receiveEntry);
        this.add(enemyQuip);
        this.add(enemyAttack);
        this.add(heroQuip);
        this.add(runLabel);
        this.add(enemy);
        this.add(sellLabel);
    }

    /** 
    * checks for button actions 
    * @param e is source of the action
    */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //if BUY button is clicked
        if(e.getSource()==buy)
        {           
            if(hero.getGold()<25)
            {
                entry1.setText("You do not have enough gold :(");
            }
            else if(hero.getGold()>25 && hero.getNumItems()<5)
            {
                entry1.setText("Thank you for your purchase!");
                hero.pickUpItem(ItemGenerator.getInstance().getPotion());
            }
            else if(hero.getNumItems()>=5)
            {
                entry1.setText("You do not have enough room for a potion :(");
            }
        }
        
        //if DONE button is clicked
        else if(e.getSource()==done)
        {
            sellLabel.setVisible(false);
            entry1.setText("You entered the dungeon again...");
            entry2.setText("entry");
            buy.setVisible(false);
            sell.setVisible(false);
            done.setVisible(false);
            runLabel.setVisible(false);
            outRoom=true;
        }
        
        //if SELL button is clicked
        else if(e.getSource()==sell)
        {
            buyItem=true;
            if(hero.getNumItems()==0)
            {
                entry1.setText("You do not have anything to sell >:(");
            }
            else if(hero.getNumItems()>0)
            {
                runLabel.setVisible(false);
                sellLabel.setVisible(true);
                outRoom=false;
            }
        }
        
        //if RUN button is clicked
        else if(e.getSource()==run)
        {           
            outRoom=true;
            Random generator = new Random();
            int i = 4 - generator.nextInt(4);
            enemyQuip.setVisible(false);
            heroQuip.setVisible(false);
            enemyAttack.setVisible(false);
            
            switch(i)
            {
                case 1:                    
                hero.goNorth();
                Map.getInstance().removeCharAtLoc(hero.getLocation());
                Map.getInstance().reveal(hero.getLocation());
                break;
                
                case 2:
                hero.goSouth();
                Map.getInstance().removeCharAtLoc(hero.getLocation());
                Map.getInstance().reveal(hero.getLocation());
                break;
                
                case 3:
                hero.goEast();
                Map.getInstance().removeCharAtLoc(hero.getLocation());
                Map.getInstance().reveal(hero.getLocation());
                break;
                
                case 4:
                hero.goWest();
                Map.getInstance().removeCharAtLoc(hero.getLocation());
                Map.getInstance().reveal(hero.getLocation());
                break;
            } 
            entry1.setText("You entered the dungeon again...");
            runLabel.setVisible(false);
            enemy.setVisible(false);
            fight.setVisible(false);
            run.setVisible(false);
        }
        
        //if FIGHT button is clicked
        else if(e.getSource()==fight)
        {
            heroQuip.setVisible(false);
            enemyQuip.setVisible(false);
            enemyAttack.setVisible(false);
            runLabel.setVisible(false);
            entry1.setVisible(false);
            outRoom=false;
            fight.setVisible(false);
            run.setVisible(false);
            physical.setVisible(true);
            magical.setVisible(true);         
        }
        
        //if PHYSICAL attack button is clicked
        else if(e.getSource()==physical)
        {

            entry1.setVisible(true);
            Random random=new Random();
            int heroDamage=random.nextInt((4 - 1 + 1) + 1*enemyEncountered.getLevel());
            int enemyDamage=random.nextInt((4 - 1 + 1) + 1*hero.getLevel());
            entry1.setText(hero.getName() + " hits " + enemyEncountered.getName() + " for " + heroDamage + " damage ");
            enemyQuip.setVisible(true);
            enemyQuip.setText(enemyEncountered.getName() + " frightfully shrieks ''" + enemyEncountered.getQuip() + "''" );
            enemyEncountered.takeDamage(heroDamage);
            
            if(enemyEncountered.getHp()<=0)
            {
                heroQuip.setVisible(false);
                enemyAttack.setVisible(false);
                outRoom=true;
                defeatEntry.setVisible(true);
                defeatEntry.setText("You've defeated the enemy...");  
                receiveEntry.setVisible(true);
                receiveEntry.setText("You received " + enemyEncountered.getItem().getName());
                physical.setVisible(false);
                magical.setVisible(false);
                hero.pickUpItem(enemyEncountered.getItem());
            }
            else if(enemyEncountered.getHp()>0)
            {
                
                enemyAttack.setVisible(true);
                enemyAttack.setText(enemyEncountered.getName()+" hits " + hero.getName() + " with " + enemyDamage + " damage ");
                heroQuip.setVisible(true);
                heroQuip.setText(hero.getName() + " terrifyingly exclaims ''"+hero.getQuip()+"''");
                hero.takeDamage(enemyDamage);
                hpField.setText(Integer.toString(hero.getHp())+"/"+Integer.toString(hero.getMaxHp()));
            }
            if(hero.getHp()<=0)
            {
                int error=JOptionPane.ERROR_MESSAGE;
                JOptionPane.showMessageDialog(this, "You Died", "Game Over", error);
                System.exit(0);
            }
        }
        
        //if MAGICAL attack button is clicked
        else if (e.getSource()==magical)
        {           
            physical.setVisible(false);
            magical.setVisible(false);
            thunderClap.setVisible(true);
            fireBall.setVisible(true);
            magicMissile.setVisible(true);
        }
        
        //if MAGICAL type of attack button is clicked
        else if(e.getSource()==thunderClap)
        {
            Random random=new Random();
            int heroDamage=random.nextInt((4 - 1 + 1) + 1*enemyEncountered.getLevel());
            int enemyDamage=random.nextInt((4 - 1 + 1) + 1*hero.getLevel());
            entry1.setVisible(true);
            entry1.setText(hero.getName() + " hits " + enemyEncountered.getName() + " with a Thunderclap for " + hero.magicMissile() + " damage ");
            enemyEncountered.takeDamage(heroDamage);
            if(enemyEncountered.getHp()<=0)
            {                   
                defeatEntry.setVisible(true);
                defeatEntry.setText("You've defeated the enemy...");  
                receiveEntry.setVisible(true);
                receiveEntry.setText(" You received " + ItemGenerator.getInstance().generateItem().getName() + " from its corpse");
                physical.setVisible(false);
                magical.setVisible(false);
                outRoom=true;
                fight.setVisible(false);
                magical.setVisible(false);
                thunderClap.setVisible(false);
                magicMissile.setVisible(false);
                fireBall.setVisible(false);
                hero.pickUpItem(ItemGenerator.getInstance().generateItem());
            }
            
            else if(enemyEncountered.getHp()>0)
            {
                hero.takeDamage(enemyDamage);
                enemyAttack.setVisible(true);
                enemyAttack.setText(enemyEncountered.getName()+" hits " + hero.getName() + " with " + enemyDamage);
                enemyQuip.setVisible(true);
                enemyQuip.setText(enemyEncountered.getName() + " frightfully shrieks ''" + enemyEncountered.getQuip() + "''" );
                heroQuip.setVisible(true);
                heroQuip.setText(hero.getName() + "terrifyingly exclaims ''"+hero.getQuip()+"''");
                fight.setVisible(true);
                run.setVisible(true);
                thunderClap.setVisible(false);
                magicMissile.setVisible(false);
                fireBall.setVisible(false);
                hpField.setText(Integer.toString(hero.getHp())+"/"+Integer.toString(hero.getMaxHp()));
            }
            
            if(hero.getHp()<=0)
            {
                int error=JOptionPane.ERROR_MESSAGE;
                JOptionPane.showMessageDialog(this, "You Died", "Game Over", error);
                System.exit(0);
            }
        }
        
        //if MAGICAL type of attack button is clicked
        else if (e.getSource()==fireBall)
        {
            Random random=new Random();
            int heroDamage=random.nextInt((4 - 1 + 1) + 1*enemyEncountered.getLevel());
            int enemyDamage=random.nextInt((4 - 1 + 1) + 1*hero.getLevel());
            entry1.setVisible(true);
            entry1.setText(hero.getName() + " hits " + enemyEncountered.getName() + " with a Fireball for " + hero.magicMissile() + " damage ");
            enemyEncountered.takeDamage(heroDamage);
            if(enemyEncountered.getHp()<=0)
            {
                defeatEntry.setVisible(true);
                defeatEntry.setText("You've defeated the enemy...");  
                receiveEntry.setVisible(true);
                receiveEntry.setText("You received " + ItemGenerator.getInstance().generateItem().getName());
                physical.setVisible(false);
                outRoom=true;
                fight.setVisible(false);
                magical.setVisible(false);
                thunderClap.setVisible(false);
                magicMissile.setVisible(false);
                fireBall.setVisible(false);
                hero.pickUpItem(ItemGenerator.getInstance().generateItem());
            }
            
            else if(enemyEncountered.getHp()>0)
            {
                hero.takeDamage(enemyDamage);
                enemyAttack.setVisible(true);
                enemyAttack.setText(enemyEncountered.getName()+" hits " + hero.getName() + " with " + enemyDamage);
                enemyQuip.setVisible(true);
                enemyQuip.setText(enemyEncountered.getName() + " frightfully shrieks ''" + enemyEncountered.getQuip() + "''" );
                heroQuip.setVisible(true);
                heroQuip.setText(hero.getName() + " terrifyingly exclaims ''"+hero.getQuip()+"''");
                fight.setVisible(true);
                run.setVisible(true);
                thunderClap.setVisible(false);
                magicMissile.setVisible(false);
                fireBall.setVisible(false);
                hpField.setText(Integer.toString(hero.getHp())+"/"+Integer.toString(hero.getMaxHp()));
            }
            
            if(hero.getHp()<=0)
            {
                int error=JOptionPane.ERROR_MESSAGE;
                JOptionPane.showMessageDialog(this, "You Died", "Game Over", error);
                System.exit(0);
            }
        }
        
        //if MAGICAL type of attack button is clicked
        else if (e.getSource()==magicMissile)
        {
            Random random=new Random();
            int heroDamage=random.nextInt((4 - 1 + 1) + 1*enemyEncountered.getLevel());
            int enemyDamage=random.nextInt((4 - 1 + 1) + 1*hero.getLevel());
            entry1.setVisible(true);
            entry1.setText(hero.getName() + " hits " + enemyEncountered.getName() + " with a Magic missile for " + hero.magicMissile() + " damage ");
            enemyEncountered.takeDamage(heroDamage);
            if(enemyEncountered.getHp()<=0)
            {
                defeatEntry.setVisible(true);
                defeatEntry.setText("You've defeated the enemy...");  
                receiveEntry.setVisible(true);
                receiveEntry.setText(" You received " + ItemGenerator.getInstance().generateItem().getName());
                physical.setVisible(false);
                outRoom=true;
                fight.setVisible(false);
                magical.setVisible(false);
                magical.setVisible(false);
                thunderClap.setVisible(false);
                magicMissile.setVisible(false);
                fireBall.setVisible(false);
                hero.pickUpItem(ItemGenerator.getInstance().generateItem());
            }
            else if(enemyEncountered.getHp()>0)
            {
                hero.takeDamage(enemyDamage);
                enemyAttack.setVisible(true);
                enemyAttack.setText(enemyEncountered.getName()+" hits " + hero.getName() + " with " + enemyDamage);
                enemyQuip.setVisible(true);
                enemyQuip.setText(enemyEncountered.getName() + " frightfully shrieks ''" + enemyEncountered.getQuip() + "''" );
                heroQuip.setVisible(true);
                heroQuip.setText(hero.getName() + "terrifyingly exclaims ''"+hero.getQuip()+"''");
                fight.setVisible(true);
                run.setVisible(true);
                thunderClap.setVisible(false);
                magicMissile.setVisible(false);
                fireBall.setVisible(false);
                hpField.setText(Integer.toString(hero.getHp())+"/"+Integer.toString(hero.getMaxHp()));
            }
                        
            if(hero.getHp()<=0)
            {
                int error=JOptionPane.ERROR_MESSAGE;
                JOptionPane.showMessageDialog(this, "You Died", "Game Over", error);
                System.exit(0);
            }
        }
    }
    
    /** 
    * draws the map, inventory and areas containing game info
    * @param g is the object that allows drawing
    */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.gray);
        g.fillRect(700, 350, 350, 280);
        g.fillRect(700, 10, 350, 330);
        g.setColor(Color.black);
        Map.getInstance().draw(g,hero);
        g.fillRect(700, 10, 350, 40);
        g.fillRect(700, 350, 350, 40);
        
        int sideLength=65;
        
        for ( int i=0; i<5; i++ )
        {               
            int x=710+sideLength*i;
            g.drawRect(x,250,sideLength, sideLength);
        }  
        
        if(hero.getNumItems()!=0)
        {
            hero.displayItems(g);
        }               
    }
    
    /** 
    * refreshes the map by use of a thread 
    */
    @Override
    public void run() 
    {
        while(true)
        {
            try
            {
                repaint();                
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {           
            }
        }
    }

    /** 
    * checks for key actions
    * @param e is the source of the event
    */
    @Override
    public void keyPressed(KeyEvent e) 
    {

        //ifs determining if the player can move
        if((e.getKeyCode()==KeyEvent.VK_W ||e.getKeyCode()==KeyEvent.VK_UP) && outRoom==true)
        {
            //hides labels and icon for monsters after defeating them
            heroQuip.setVisible(false);
            enemyQuip.setVisible(false);
            defeatEntry.setVisible(false);
            receiveEntry.setVisible(false);
            enemy.setVisible(false);
            char north=hero.goNorth(); 
            
            if(north=='m')
            {
                outRoom=false;
                entry2.setText("An Enemy!");
                monsterRoom(hero);              
            }
            
            else if (north=='n')
            {
                entry1.setText("There was nothing here...");
            }
            
            else if (north=='i')
            {
                itemRoom(hero);               
                String result=hero.getItems();
                System.out.println(result);
            }
            
            else if(north=='f')
            {
                entry1.setText("You moved on to the next level...");
                hero.increaseLevel(); 
                levelField.setText(Integer.toString(hero.getLevel()));
                map++;
                if(map>3)
                {
                    
                    map=1;
                    Map.getInstance().loadMap(map);
                }
                else
                {
                    Map.getInstance().loadMap(map);
                }
            }
            
            else if(north=='s')
            {
                entry2.setText("Trader Sam's");
                store(hero,ItemGenerator.getInstance());
                outRoom=false;
            }
            
            else if(north=='b')
            {
                entry1.setText(" You cannot leave the map hero! ");
            }
            
        }
        
        if((e.getKeyCode()==KeyEvent.VK_S ||e.getKeyCode()==KeyEvent.VK_DOWN)&& outRoom==true)
        {
            //hides labels and icon for monsters after defeating them
            heroQuip.setVisible(false);
            enemyQuip.setVisible(false);
            defeatEntry.setVisible(false);
            receiveEntry.setVisible(false);
            enemy.setVisible(false);
            char south=hero.goSouth();
            if(south=='m')
            {
                outRoom=false;
                monsterRoom(hero);
            }
            
            else if (south=='n')
            {
                entry1.setText("There was nothing here...");
            }
            
            else if (south=='i')
            {
                itemRoom(hero);               
                String result=hero.getItems();
                System.out.println(result);
            }
            
            else if(south=='f')
            {
                entry1.setText("You moved on to the next level...");
                hero.increaseLevel();
                levelField.setText(Integer.toString(hero.getLevel()));
                map++;
                
                if(map>3)
                {
                    map=1;
                    Map.getInstance().loadMap(map);
                }
                else
                {
                    Map.getInstance().loadMap(map);
                }
            }
            
            else if(south=='s')
            {
                entry2.setText("Trader Sam's");
                store(hero,ItemGenerator.getInstance());
                outRoom=false;
            }
            
            else if(south=='b')
            {
                entry1.setText(" You cannot leave the map hero! ");
            }
        }
        
        if((e.getKeyCode()==KeyEvent.VK_A ||e.getKeyCode()==KeyEvent.VK_LEFT)&& outRoom==true)
        {
            //hides labels and icon for monsters after defeating them
            heroQuip.setVisible(false);
            enemyQuip.setVisible(false);
            defeatEntry.setVisible(false);
            receiveEntry.setVisible(false);
            enemy.setVisible(false);
            char west=hero.goWest();
            if(west=='m')
            {
                outRoom=false;
                monsterRoom(hero);
            }
            
            else if (west=='n')
            {
                entry1.setText("There was nothing here...");
            }
            
            else if (west=='i')
            {
                itemRoom(hero);               
                String result=hero.getItems();
                System.out.println(result);
            }
            
            else if(west=='f')
            {
                entry1.setText("You moved on to the next level...");
                hero.increaseLevel();
                levelField.setText(Integer.toString(hero.getLevel()));
                
                map++;
                
                if(map>3)
                {
                    map=1;
                    Map.getInstance().loadMap(map);
                }
                else
                {
                    Map.getInstance().loadMap(map);
                }
            }
            
            else if(west=='s')
            {
                entry2.setText("Trader Sam's");
                store(hero,ItemGenerator.getInstance());
                outRoom=false;
            }
            
            else if(west=='b')
            {
                entry1.setText(" You cannot leave the map hero! ");
            }
        }       
        
        if((e.getKeyCode()==KeyEvent.VK_D ||e.getKeyCode()==KeyEvent.VK_RIGHT)&& outRoom==true)
        {
            //hides labels and icon for monsters after defeating them
            heroQuip.setVisible(false);
            enemyQuip.setVisible(false);
            defeatEntry.setVisible(false);
            receiveEntry.setVisible(false);
            enemy.setVisible(false);   
            char east=hero.goEast();
            if(east=='m')
            {
                outRoom=false;
                monsterRoom(hero);
            }
            
            else if (east=='n')
            {
                entry1.setText("There was nothing here...");
            }
            
            else if (east=='i')
            {
                itemRoom(hero);                
                String result=hero.getItems();
                System.out.println(result);
            }   
            
            else if(east=='f')
            {
                entry1.setText("You moved on to the next level...");
                hero.increaseLevel();
                levelField.setText(Integer.toString(hero.getLevel()));
                map++;
                
                if(map>3)
                {
                    map=1;
                    Map.getInstance().loadMap(map);
                }
                else
                {
                    Map.getInstance().loadMap(map);
                }
            }
            
            else if(east=='s')
            {
                entry2.setText("Trader Sam's");
                store(hero,ItemGenerator.getInstance());
                outRoom=false;
            }
            
            else if(east=='b')
            {
                entry1.setText(" You cannot leave the map hero! ");
            }
        }
        
        if (outRoom==false)
        {
            runLabel.setVisible(true);
            runLabel.setText("You're stuck. If you really want to leave click ''RUN'', hero!");
        }  
        
        if (outRoom==false  && Map.getInstance().getCharAtLoc(hero.getLocation())=='s')
        {
            runLabel.setVisible(true);
            runLabel.setText("If you really want to leave click ''DONE'', hero!");
        }  
    }

    /** 
    * mandatory override
    */
    @Override
    public void keyReleased(KeyEvent e) 
    {
    }
    
    /** 
    * mandatory override
    */
    @Override
    public void keyTyped(KeyEvent e) 
    {
    }
    
    /** 
    * initiates an interaction with an enemy
    * @param h is the hero 
    * @param m is the map
    * @param eg is the generator of enemies
    */
    Enemy monsterRoom(Hero h)
    {
        BufferedImage img;
        ImageIcon ImageIcon;
        enemyEncountered=EnemyGenerator.getInstance().generateEnemy(h.getLevel());
        
        try
        {
            if(enemyEncountered.getFileName().equals("troll.jpg"))
            {
                img=ImageIO.read(new File(enemyEncountered.getFileName()));
                Image image=img.getScaledInstance(150, 180,Image.SCALE_SMOOTH);
                ImageIcon=new ImageIcon(image);
                enemy.setVisible(true);
                enemy.setIcon(ImageIcon);
            }
            else if(enemyEncountered.getFileName().equals("goblin.jpg"))
            {
                img=ImageIO.read(new File(enemyEncountered.getFileName()));
                Image image=img.getScaledInstance(150, 180,Image.SCALE_SMOOTH);
                ImageIcon=new ImageIcon(image);
                enemy.setVisible(true);
                enemy.setIcon(ImageIcon);
            }
            else if(enemyEncountered.getFileName().equals("orc.jpg"))
            {
                img=ImageIO.read(new File(enemyEncountered.getFileName()));
                Image image=img.getScaledInstance(150, 180,Image.SCALE_SMOOTH);
                ImageIcon=new ImageIcon(image);
                enemy.setVisible(true);
                enemy.setIcon(ImageIcon);
            }
            else if(enemyEncountered.getFileName().equals("froglok.jpg"))
            {
                img=ImageIO.read(new File(enemyEncountered.getFileName()));
                Image image=img.getScaledInstance(150, 180,Image.SCALE_SMOOTH);
                ImageIcon=new ImageIcon(image);
                enemy.setVisible(true);
                enemy.setIcon(ImageIcon);
            }
        }
        catch(IOException e)
        {
            
        }
        enemyEncountered.increaseMaxHP(h.getLevel());
        entry1.setText("You've encountered a " + enemyEncountered.getName());
        
        fight.setVisible(true);
        run.setVisible(true);
                                                  
        return enemyEncountered;
    }
    
    /** 
    * allows you to access the store
    * @param h is the hero
    * @param ig is the generator of items
    */ 
    void store(Hero h, ItemGenerator ig)
    {       
        entry1.setText("Hello, "+h.getName()+ ". what can I do for you?");
        buy.setVisible(true);
        sell.setVisible(true);
        done.setVisible(true);
    }
    
    /** 
    * reveals that you've found an item
    * @param h is the hero
    * @param m is the map
    * @param is the generator for the item 
    */ 
    void itemRoom(Hero h)
    {
        Item foundItem=ItemGenerator.getInstance().generateItem();
        if(hero.getNumItems()>=5)
        {
            entry1.setText("You found "+ foundItem.getName() + " but your inventory is full :(");
        }
        if(hero.getNumItems()<5)
        {
            entry1.setText("You found "+ foundItem.getName());
        }
        if(foundItem.getValue()==20)
        {
            h.collectGold(foundItem.getValue());
        }
        if(foundItem.getValue()==15)
        {
           h.increaseMaxHP(foundItem.getValue());
           h.collectGold(foundItem.getValue());
        }
        else
        {
            h.pickUpItem(foundItem);
        }
    }      

    /** 
    * checks for mouse actions
    * @param e is the source of the event
    */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        Point p=new Point(e.getX(),e.getY());
        
        if(Map.getInstance().getCharAtLoc(hero.getLocation())=='s' && outRoom==false)
        {
            //only makes first inventory cell clickable
            if(hero.getNumItems()==1)
            {
                if((p.x>=710 && p.x<=775) && (p.y>=250 && p.y<=315))
                {
                    Item removed=hero.removeItem(1);
                    int value=removed.getValue();
                    hero.collectGold(value);
                    creditField.setText(Integer.toString(hero.getGold()));
                }
            }
            
            //makes first 2 inventory cells clickable
            else if( hero.getNumItems()==2)
            {
                if(((p.x>=710 && p.x<=775) && (p.y>=250 && p.y<=315)))
                {
                    Item removed=hero.removeItem(1);
                    int value=removed.getValue();
                    hero.collectGold(value);
                    creditField.setText(Integer.toString(hero.getGold()));
                }
                else if((p.x>=775 && p.x<=840) && (p.y>=250 && p.y<=315))
                {
                    Item removed=hero.removeItem(2);
                    int value=removed.getValue();
                    hero.collectGold(value);
                    creditField.setText(Integer.toString(hero.getGold()));
                }
            }
            
            //makes first 3 inventory cells clickable
            else if( hero.getNumItems()==3)
            {
                if(((p.x>=710 && p.x<=775) && (p.y>=250 && p.y<=315)))
                {
                    Item removed=hero.removeItem(1);
                    int value=removed.getValue();
                    hero.collectGold(value);
                    creditField.setText(Integer.toString(hero.getGold()));
                }
                else if((p.x>=775 && p.x<=840) && (p.y>=250 && p.y<=315))
                {
                    Item removed=hero.removeItem(2);
                    int value=removed.getValue();
                    hero.collectGold(value);
                    creditField.setText(Integer.toString(hero.getGold()));
                }
                else if((p.x>=840 && p.x<=905) && (p.y>=250 && p.y<=315))
                {
                    Item removed=hero.removeItem(3);
                    int value=removed.getValue();
                    hero.collectGold(value);
                    creditField.setText(Integer.toString(hero.getGold()));
                }              
            }
            
            //makes first 4 inventory cells clickable
            else if( hero.getNumItems()==4)
            {
                if(((p.x>=710 && p.x<=775) && (p.y>=250 && p.y<=315)))
                {
                    Item removed=hero.removeItem(1);
                    int value=removed.getValue();
                    hero.collectGold(value);
                    creditField.setText(Integer.toString(hero.getGold()));
                }
                else if((p.x>=775 && p.x<=840) && (p.y>=250 && p.y<=315))
                {
                    Item removed=hero.removeItem(2);
                    int value=removed.getValue();
                    hero.collectGold(value);
                    creditField.setText(Integer.toString(hero.getGold()));
                }
                else if((p.x>=840 && p.x<=905) && (p.y>=250 && p.y<=315))
                {
                    Item removed=hero.removeItem(3);
                    int value=removed.getValue();
                    hero.collectGold(value);
                    creditField.setText(Integer.toString(hero.getGold()));
                }  
                else if((p.x>=905 && p.x<=970) && (p.y>=250 && p.y<=315))
                {
                    Item removed=hero.removeItem(4);
                    int value=removed.getValue();
                    hero.collectGold(value);
                }  
            }
            
            //makes the 5 inventory cells clickable
            else if( hero.getNumItems()==5)
            {
                if(((p.x>=710 && p.x<=775) && (p.y>=250 && p.y<=315)))
                {
                    Item removed=hero.removeItem(1);
                    int value=removed.getValue();
                    hero.collectGold(value);
                    creditField.setText(Integer.toString(hero.getGold()));
                }
                else if((p.x>=775 && p.x<=840) && (p.y>=250 && p.y<=315))
                {
                    Item removed=hero.removeItem(2);
                    int value=removed.getValue();
                    hero.collectGold(value);
                    creditField.setText(Integer.toString(hero.getGold()));
                }
                else if((p.x>=840 && p.x<=905) && (p.y>=250 && p.y<=315))
                {
                    Item removed=hero.removeItem(3);
                    int value=removed.getValue();
                    hero.collectGold(value);
                    creditField.setText(Integer.toString(hero.getGold()));
                }
                else if((p.x>=905 && p.x<=970) && (p.y>=250 && p.y<=315))
                {
                    Item removed=hero.removeItem(4);
                    int value=removed.getValue();
                    hero.collectGold(value);
                    creditField.setText(Integer.toString(hero.getGold()));
                }
                else if((p.x>=970 && p.x<=1035) && (p.y>=250 && p.y<=315))
                {
                    Item removed=hero.removeItem(5);
                    int value=removed.getValue();
                    hero.collectGold(value);
                    creditField.setText(Integer.toString(hero.getGold()));
                }
            }            
        }
    }

    
    /** 
    * mandatory override
    */
    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    /** 
    * mandatory override
    */
    @Override
    public void mouseReleased(MouseEvent e) 
    {
    }

    /** 
    * mandatory override
    */
    @Override
    public void mouseEntered(MouseEvent e) 
    {
    }

    /** 
    * mandatory override
    */
    @Override
    public void mouseExited(MouseEvent e) 
    {
    }
}
