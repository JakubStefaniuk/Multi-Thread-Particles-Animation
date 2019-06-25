package MTparticlesAnimation;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * This file contains AnimationPanel settings
 * 
 * 
 */
class APanel extends JPanel{
    //ArrayList consisted of Dot objects to enable quick access
    ArrayList dots;
    Thread newThread;
    //ThreadGroup consisted of threads to make clear method possible
    ThreadGroup TGroup = new ThreadGroup("TGroup");
    //if paused, threaed sleeps for 0.5s, without repainting components and 
    //moving the dots, moves the dots in given interval otherwise
    boolean paused = true;
    //used in Dot constructor, is multiplied by dx and dy
    int speedInt=5;
    APanel(){
        super();
        dots = new ArrayList();
    }
    public void newDot(){
        //returns random coordinates from Panel
        PairInt randomCords = randPanel();
        int tmpdx=1,tmpdy=1;
        //quite random setting the direction
        if(randomCords.getFirst()%2==0){
            tmpdx=-1;
        }
        if(randomCords.getSecond()%2==0){
            tmpdy=-1;
        }
        Dot newOne = new Dot((int)randomCords.getFirst(),(int)randomCords.getSecond(),tmpdx*speedInt,tmpdy*speedInt,new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight()));
        dots.add(newOne);
        //adding thread to the current group TGroup and then starting it
        newThread = new Thread(TGroup,new DotRunnable(newOne));
        newThread.start();
    }     
    @Override
    public void paintComponent(Graphics graph){
        super.paintComponent(graph);
        for(int i = 0; i < dots.size(); i++){
            graph.drawImage(((Dot)dots.get(i)).returnImage(), ((Dot)dots.get(i)).getX(), ((Dot)dots.get(i)).getY(), null);
        }
    }
    public PairInt randPanel(){
        PairInt xRange = new PairInt(this.getX(),(this.getWidth()+this.getX()));
        PairInt yRange = new PairInt(this.getY(),(this.getHeight()+this.getY()));
        double randomx = Math.random();
        randomx*=(xRange.getSecond()-xRange.getFirst());
        randomx+=xRange.getFirst();
        double randomy = Math.random();
        randomy*=(yRange.getSecond()-yRange.getFirst());
        randomy+=yRange.getFirst();
        return new PairInt((int)randomx,(int)randomy);
    }
    public void switchPause(){
        paused=!paused;
    }
    public void clearAll() {
        TGroup.interrupt();
    }
    class PairInt{
        private int x,y;
        public PairInt(int x, int y){
            this.x=x;
            this.y=y;
        }
        int getFirst(){
            return x;
        }
        int getSecond(){
            return y;
        }
    }
    //class implementing Runnable interface to enable it to be run in new thread
    class DotRunnable implements Runnable{
        Dot a;
        DotRunnable(Dot a){
            this.a=a;
        }
        @Override
        public void run() {
            try{
                while(true){
                    while(!Thread.interrupted()){
                        if(!paused){
                           repaint();
                           a.moveBy();
                           Thread.sleep(30);
                        }
                        else{
                            Thread.sleep(500);
                        }
                    }
                }
            }
            catch(InterruptedException exc){
                //removing all Dots from ArrayList in case exception thrown in
                //clearAll method is caught 
                dots.clear();
                repaint();
            }
        }
    } 
}
   