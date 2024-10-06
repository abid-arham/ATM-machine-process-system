import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class AdminLogin implements MouseListener {

    JFrame frame;
    JLabel usernameLabel, passwordLabel, headingLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, exitButton;

    public AdminLogin() {
        frame = new JFrame("Admin Login Portal");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(200, 50, 500, 500);
        frame.setLayout(null);

        headingLabel = new JLabel("Admin Login Portal");
        headingLabel.setBounds(200, 100, 500, 30);
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 20));

        usernameLabel = new JLabel("Admin ID");
        usernameLabel.setBounds(50, 200, 150, 30);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));

        usernameField = new JTextField();
        usernameField.setBounds(150, 200, 300, 30);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 250, 150, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 250, 300, 30);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/LoginB.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 500, 1);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 500, 500);

        loginButton = new JButton("Login");
        loginButton.setBounds(220, 300, 100, 30);
        loginButton.setBackground(new Color(0, 48, 73));
        loginButton.setForeground(Color.WHITE);
        loginButton.addMouseListener(this);

        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                
                if (validateLogin(username, password)) {
                    frame.dispose();  
                    new UserInfo();   
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Admin ID or Password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitButton = new JButton("Exit");
        exitButton.setBounds(220, 350, 100, 30);
        exitButton.setBackground(new Color(214, 40, 40));
        exitButton.setForeground(Color.WHITE);
        exitButton.addMouseListener(this);
        exitButton.addActionListener(e -> frame.dispose());

        frame.add(headingLabel);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(exitButton);
	frame.add(image);
	frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    private boolean validateLogin(String username, String password) {
        try {
            File file = new File("admin_credentials.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] credentials = line.split(","); 
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Credentials file not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    
    @Override
    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == loginButton) {
            loginButton.setBackground(Color.BLUE);
            loginButton.setForeground(Color.WHITE);
        } else if (me.getSource() == exitButton) {
            exitButton.setBackground(Color.BLUE);
            exitButton.setForeground(Color.WHITE);
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == loginButton) {
            loginButton.setBackground(new Color(0, 48, 73));
            loginButton.setForeground(Color.WHITE);
        } else if (me.getSource() == exitButton) {
            exitButton.setBackground(new Color(214, 40, 40));
            exitButton.setForeground(Color.WHITE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {}
    @Override
    public void mousePressed(MouseEvent me) {}
    @Override
    public void mouseReleased(MouseEvent me) {}

    public static void main(String[] args) {
        new AdminLogin();
    }
}

