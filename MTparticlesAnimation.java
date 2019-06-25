package MTparticlesAnimation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

public class MTparticlesAnimation extends JFrame{
    //panel dedicated to buttons
    private JPanel bpanel;
    //panel dedicated to animation
    private JPanel apanel;
    private JButton [] buttons = new JButton [3]; 
    //slider to change the particles speed
    JSlider speed;
    //constructor preparing all visuals and button actions
    MTparticlesAnimation(){
        JPanel bpanel=new JPanel();
        APanel apanel=new APanel();
        //screen size properties
        int x = Toolkit.getDefaultToolkit().getScreenSize().width;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setTitle("Particles");
        this.setBounds(x/4, y/4, x/2, y/2);
        bpanel.setBackground(Color.YELLOW);
        apanel.setBackground(Color.CYAN);
        this.getContentPane().add(bpanel,BorderLayout.SOUTH);
        this.getContentPane().add(apanel);
        buttons[0]=(JButton)(bpanel.add(new JButton("Start/Stop")));
        buttons[1]=(JButton)(bpanel.add(new JButton("Add")));
        buttons[2]=(JButton)(bpanel.add(new JButton("Clear")));
        speed =(JSlider)(bpanel.add(new JSlider(JSlider.HORIZONTAL,1, 10, 5)));
        //overwritten buttons / slider event actions 
        buttons[0].addActionListener((ActionEvent ae) -> {
            apanel.switchPause();
        });
        buttons[1].addActionListener((ActionEvent ae) -> {
            apanel.newDot();
        });
        buttons[2].addActionListener((ActionEvent ae) -> {
            apanel.clearAll();
        });
        speed.addChangeListener((ChangeEvent ce) -> {
            apanel.speedInt = speed.getValue();
        });
        this.setDefaultCloseOperation(3);
    }
    public static void main(String[] args) {
        //running GUI
        new MTparticlesAnimation().setVisible(true);
    }
}
 
