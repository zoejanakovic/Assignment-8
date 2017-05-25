
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author janaz9178
 */
public class A8Q1 extends JComponent {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    //Title of the window
    String title = "My Game";

    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;


    // YOUR GAME VARIABLES WOULD GO HERE

    Color brown = new Color(161, 119, 63);
    Color eyes = new Color(101, 214, 84);
    Color pink = new Color(176, 28, 69);
    Color horns = new Color(240, 228, 94);
    Color steam = new Color(189, 214, 222);
    
    int ringHeight = 75;
    int ringDirection = -1;
    
    int firstSteamx = 230;
    int secondSteamx = 525;
    
    int lidsy = 150;
    int lidDirection = 1;

    // GAME VARIABLES END HERE   

    
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public A8Q1(){
        // creates a windows to show my game
        JFrame frame = new JFrame(title);

        // sets the size of my game
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(this);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        
        // add listeners for keyboard and mouse
        frame.addKeyListener(new Keyboard());
        Mouse m = new Mouse();
        
        this.addMouseMotionListener(m);
        this.addMouseWheelListener(m);
        this.addMouseListener(m);
    }
    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE
        
        //ears
        g.setColor(brown);
        g.fillOval(100, 200, 150, 75);
        g.fillOval(550, 200, 150, 75);
        g.setColor(pink);
        g.fillOval(120, 205, 130, 65);
        g.fillOval(550, 205, 130, 65);
        
        //ear steam
        g.setColor(steam);
        g.fillOval(firstSteamx, 200, 75, 75);
        g.fillOval(secondSteamx, 200, 75, 75);
        
        //horns
        g.setColor(horns);
        int[] xPoints = {330, 150, 125, 95, 330};
        int[] yPoints = {135, 135, 25, 175, 175};
        g.fillPolygon(xPoints, yPoints, 5);
        int[] xPointsTwo = {480, 660, 685, 710, 480};
        int[] yPointsTwo = {135, 135, 25, 175, 175};
        g.fillPolygon(xPointsTwo, yPointsTwo, 5);
        
        //base
        g.setColor(brown);
        g.fillOval(200, 100, 400, 400);
        
        //eyes
        g.setColor(Color.WHITE);
        g.fillOval(300, 200, 75, 75);
        g.fillOval(425, 200, 75, 75);
        g.setColor(eyes);
        g.fillOval(310, 210, 40, 40);
        g.fillOval(435, 210, 40, 40);
        g.setColor(Color.BLACK);
        g.fillOval(315, 220, 20, 20);
        g.fillOval(440, 220, 20, 20);
        
        //eye lids
        g.setColor(brown);
        g.fillArc(290, lidsy, 100, 100, 0, 180);
        g.fillArc(415, lidsy, 100, 100, 0, 180);
        
        //nose
        g.setColor(pink);
        g.fillOval(275, 275, 250, 150);
        g.setColor(Color.BLACK);
        g.fillOval(310, 300, 75, 50);
        g.fillOval(420, 300, 75, 50);
        g.setColor(Color.YELLOW);
        g.drawArc(365, 310, 75, ringHeight, 135, 270);
        
        
        // GAME DRAWING ENDS HERE
    }


    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void  preSetup(){
       // Any of your pre setup before the loop starts should go here

       
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        preSetup();

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            
            //move the nose ring "out" and "back" like it is being blown
            if(ringHeight < 30){
                ringDirection = 1;
            }
            if(ringHeight > 75){
                ringDirection = -1;
            }
            
            ringHeight = ringHeight + ringDirection;
         
            
            //get steam to come out of the ears
            if(secondSteamx > 0){
                secondSteamx++;
            }
            if(secondSteamx > WIDTH + 75){
                secondSteamx = 525;
            }
            
            if(firstSteamx > - 75){
                firstSteamx--;
            }
            if(firstSteamx == - 75){
                firstSteamx = 230;
            }
            
            
            //get eyes to close
            if(lidsy < 150){
                lidDirection = 1;
            }
            if(lidsy > 190){
                lidDirection = -1;
            }
            
            lidsy = lidsy + lidDirection;
                 
            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };
        }
    }

    

    // Used to implement any of the Mouse Actions
    private class Mouse extends MouseAdapter {
        // if a mouse button has been pressed down
        @Override
        public void mousePressed(MouseEvent e){
            
        }
        
        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e){
            
        }
        
        // if the scroll wheel has been moved
        @Override
        public void mouseWheelMoved(MouseWheelEvent e){
            
        }

        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e){
            
        }
    }
    
    // Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter{
        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e){
            
        }
        
        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e){
            
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates an instance of my game
        A8Q1 game = new A8Q1();
                
        // starts the game loop
        game.run();
    }
}
