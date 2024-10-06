import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Contribution{

	JFrame frame;
	
	JLabel heading, name1, name2, name3;
	
	
	public Contribution(){
	
	
	heading = new JLabel("Contributions");
	heading.setBounds(280,50,200,30);
	heading.setFont(new Font("Tahoma", Font.BOLD, 24));
	
	name1 = new JLabel("1. Linkon Sen ID: 23-54623-3");
	name1.setBounds(50,100,500,30);
	name1.setFont(new Font("Tahoma", Font.BOLD, 20));
	
	
	name2 = new JLabel("2. Sumiya Rahman Hadir ID: 23-54990-3");
	name2.setBounds(50,150,500,30);
	name2.setFont(new Font("Tahoma", Font.BOLD, 20));
	
	
	name3 = new JLabel("3. Abid Arham ID: 23-54989-3");
	name3.setBounds(50,200,500,30);
	name3.setFont(new Font("Tahoma", Font.BOLD, 20));
	
	
	
	
	
	
	
	
	
	
	frame = new JFrame("Contribution");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(200, 50, 750, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        
		frame.setResizable(false);
	frame.add(heading);
	
	frame.add(name1);
	frame.add(name2);
	frame.add(name3);
	
		
	
	}

	
	
	public static void main(String args[]){
		new Contribution();
	
	
	
	}


}
