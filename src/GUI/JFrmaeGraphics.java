package GUI;

import java.awt.Color;
/**
 * Code taken from: https://javatutorial.net/display-text-and-graphics-java-jframe
 * 
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class JFrmaeGraphics extends JPanel{


	public void paint(Graphics g){
		 Image image = Toolkit.getDefaultToolkit().getImage("C:\\Ariel1.jpg");
			int w = this.getWidth();
			int h = this.getHeight();
			g.setColor(Color.blue);
			String s = " ["+w+","+h+"]";
		    g.drawString(s, w/3, h/2);
		   
		    
	}
	
	public static void main(String[] args){
		
		JFrame frame= new JFrame("C:\\Ariel1.jpg");	
		frame.getContentPane().add(new JFrmaeGraphics());
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setName("JFrame example");
	}
}