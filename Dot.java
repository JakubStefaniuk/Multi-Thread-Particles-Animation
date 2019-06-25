package MTparticlesAnimation;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
/**
 * 
 *  This file contains Dot - moveable properties and interface
 * 
 */
class Dot{
    private Image dot =  new ImageIcon("dot.jpg").getImage();
    //Dot properties - x,y - coordinates, dx,dy - describe dot current movement
    private int x,y,dx,dy;
    //APanel borders
    boolean paused = false;
    //panel bounds to correctly handle moveBy method
    private int i1, i2,i3,i4;
    Dot(){
        x=y=0;
        dx=dy=1;
    }
    Dot(int x, int y, int dx, int dy, Rectangle panel){
        this.x=x;
        this.y=y;
        this.dx=dx;
        this.dy=dy;
        this.i1 = panel.x;
        this.i2 = i1 + panel.width;
        this.i3 = panel.y;
        this.i4 = i3 + panel.height;
    }
    public void moveBy(){
        if(!paused){
            this.x+=dx;
            this.y+=dy;
            if((this.y+dot.getHeight(null))>i4){
                this.y=(i4-dot.getHeight(null));
                dy=-dy;
            }
            if((this.x+dot.getWidth(null))>i2){
                this.x=(i2-dot.getWidth(null));
                dx=-dx;
            }
            if((this.x-dot.getWidth(null))<i1){
                this.x=(i1+dot.getWidth(null));
                dx=-dx;
            }
            if((this.y-dot.getHeight(null))<i3){
                this.y=(i3+dot.getHeight(null));
                dy=-dy;
            }
        }
    }
    public Image returnImage(){
        return dot;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}