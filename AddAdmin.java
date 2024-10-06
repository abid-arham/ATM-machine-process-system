import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AddAdmin implements MouseListener {

    JFrame frame;
    JLabel usernameLabel, passwordLabel, headingLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton addButton, exitButton;
    
    public AddAdmin() {
        frame = new JFrame("Admin Adding Portal");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(200, 50, 500, 500);
        frame.setLayout(null);

        headingLabel = new JLabel("Add New Admin");
        headingLabel.setBounds(200, 100, 500, 30);
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 20));

        usernameLabel = new JLabel("Admin ID");
        usernameLabel.setBounds(50, 200, 150, 30);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/LoginB.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 500, 1);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 500, 500);

        usernameField = new JTextField();
        usernameField.setBounds(150, 200, 300, 30);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 250, 150, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 250, 300, 30);

        addButton = new JButton("Add");
        addButton.setBounds(220, 300, 100, 30);
        addButton.setBackground(new Color(0, 48, 73));
        addButton.setForeground(Color.WHITE);
        addButton.addMouseListener(this);

        // Add ActionListener to handle adding new admin
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Call the method to write the admin credentials to a file
                if (writeAdminCredentials(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Admin added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Error adding admin.", "Error", JOptionPane.ERROR_MESSAGE);
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
        frame.add(addButton);
        frame.add(image);
        frame.add(exitButton);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Method to write the admin credentials to a file
    private boolean writeAdminCredentials(String username, String password) {
        try {
            FileWriter fileWriter = new FileWriter("admin_credentials.txt", true);  // 'true' to append to the file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the username and password, separated by a comma
            bufferedWriter.write(username + "," + password);
            bufferedWriter.newLine();

            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == addButton) {
            addButton.setBackground(Color.BLUE);
            addButton.setForeground(Color.WHITE);
        } else if (me.getSource() == exitButton) {
            exitButton.setBackground(Color.BLUE);
            exitButton.setForeground(Color.WHITE);
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == addButton) {
            addButton.setBackground(new Color(0, 48, 73));
            addButton.setForeground(Color.WHITE);
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
        new AddAdmin();
    }
}

