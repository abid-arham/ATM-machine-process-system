import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Splash extends JFrame implements MouseListener, ActionListener {

    JFrame frame = new JFrame("ATM");
    JButton nextButton, exit, contribution;
    JCheckBox agreeCheckbox;
    JRadioButton userRadio, adminRadio;
    ButtonGroup buttongroup;

    public Splash() {

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm.gif"));
        Image i2 = i1.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 600);
        
        
        userRadio = new JRadioButton("User");
        userRadio.setBounds(500, 360, 80, 30);
        userRadio.setSelected(true);

        adminRadio = new JRadioButton("Admin");
        adminRadio.setBounds(700, 360, 80, 30);

        buttongroup = new ButtonGroup();
        buttongroup.add(userRadio);
        buttongroup.add(adminRadio);

        agreeCheckbox = new JCheckBox("I agree to the terms and conditions");
        agreeCheckbox.setBounds(500, 400, 280, 30);
        agreeCheckbox.setBackground(null);
        agreeCheckbox.setForeground(Color.BLACK);

        
        agreeCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                nextButton.setEnabled(agreeCheckbox.isSelected());
            }
        });

        nextButton = new JButton("Next");
        nextButton.setBounds(550, 440, 200, 50);
        nextButton.setBackground(new Color(0, 48, 73));
        nextButton.setForeground(Color.WHITE);
        nextButton.setEnabled(false); 
        nextButton.addMouseListener(this);
        nextButton.addActionListener(this);

        exit = new JButton("Exit");
        exit.setBounds(550, 500, 200, 50);
        exit.setBackground(new Color(214, 40, 40));
        exit.setForeground(Color.WHITE);
        exit.addMouseListener(this);
        exit.addActionListener(this);
        
        
        
        
        
        contribution = new JButton("Contribution");
        contribution.setBounds(10, 500, 150, 30);
        contribution.setBackground(new Color(214, 40, 40));
        contribution.setForeground(Color.WHITE);
        contribution.addMouseListener(this);
        contribution.addActionListener(this);

        image.setLayout(null);
        image.add(nextButton);
        image.add(exit);
        image.add(contribution);
        frame.add(userRadio);
        frame.add(adminRadio);

        frame.add(agreeCheckbox);
        frame.add(image);
        
        

        frame.setBounds(50, 50, 800, 600);
        frame.setBackground(new Color(234, 226, 183));
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void mouseClicked(MouseEvent me) {}

    public void mousePressed(MouseEvent me) {}

    public void mouseReleased(MouseEvent me) {}

    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == nextButton) {
            nextButton.setBackground(Color.BLUE);
            nextButton.setForeground(Color.WHITE);
        } 
        else if (me.getSource() == exit) {
            exit.setBackground(Color.BLUE);
            exit.setForeground(Color.WHITE);
        }
        else if (me.getSource() == contribution) {
            exit.setBackground(Color.BLUE);
            exit.setForeground(Color.WHITE);
        }
    }

    public void mouseExited(MouseEvent me) {
        if (me.getSource() == nextButton) {
            nextButton.setBackground(new Color(0, 48, 73));
            nextButton.setForeground(Color.WHITE);
        } 
        
        else if (me.getSource() == exit) {
            exit.setBackground(new Color(214, 40, 40));
            exit.setForeground(Color.WHITE);
        }
        
        else if (me.getSource() == contribution) {
            exit.setBackground(new Color(214, 40, 40));
            exit.setForeground(Color.WHITE);
        }
    }

    public void actionPerformed(ActionEvent e) {
    
    	if(userRadio.isSelected()){
    		if (e.getSource() == nextButton) {
            new UserLogin();
        			}
        			
				 
        else if (e.getSource() == exit) {
            System.exit(90);
        }
        
    	}
    	
    	
    	
    	else if(adminRadio.isSelected()){
    	
    	if (e.getSource() == nextButton) {
            new AdminLogin();
        			}
        			
        			 
        else if (e.getSource() == exit) {
            System.exit(90);
        }
    	
    	
    	}
    	
    	
    	
    	if (e.getSource() == contribution) {
            new Contribution();
        }
    
    
    
    
        
    }

    public static void main(String[] args) {
        new Splash();
    }
}

