import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dashboard extends JFrame implements ActionListener {
    private double balance = 0;  
    private ArrayList<String> transactionHistory = new ArrayList<>();
    private JLabel welcomeLabel, usernameLabel;
    private JButton balanceButton, depositButton, withdrawButton, historyButton, logoutButton, exitButton;
    private String username;
    private File userFile = new File("UserInfo.txt");
    private List<String> userInfoLines = new ArrayList<>();

    public Dashboard(String username) {
        this.username = username;  
        loadUserData();  

        setTitle("ATM Dashboard");
        setBounds(100, 50, 400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 1, 10, 10));

        
        welcomeLabel = new JLabel("Welcome to ATM", SwingConstants.CENTER);
        add(welcomeLabel);

        
        usernameLabel = new JLabel("Account Name: " + username, SwingConstants.CENTER);
        add(usernameLabel);

        
        balanceButton = new JButton("Check Balance");
        depositButton = new JButton("Deposit Money");
        withdrawButton = new JButton("Withdraw Money");
        historyButton = new JButton("Transaction History");
        logoutButton = new JButton("Logout");
        exitButton = new JButton("Exit");

        
        add(balanceButton);
        add(depositButton);
        add(withdrawButton);
        add(historyButton);
        add(logoutButton);
        add(exitButton);

        
        balanceButton.addActionListener(this);
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        historyButton.addActionListener(this);
        logoutButton.addActionListener(this);
        exitButton.addActionListener(this);

        setVisible(true);
    }

    private void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                userInfoLines.add(line);  // Store all lines in memory
                String[] userData = line.split(",");
                if (userData[0].equals(username)) {  // Check if this is the logged-in user
                    balance = Double.parseDouble(userData[2]);  // Get the balance from the file
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading user data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateUserData() {
    try {
        List<String> updatedUserInfoLines = new ArrayList<>();  // To store the updated lines

        // Debugging: Print current user data
        System.out.println("Original Data:");
        for (String line : userInfoLines) {
            System.out.println(line);  // Print all the user data for debugging
        }

        // Loop through each line in userInfoLines to find the logged-in user
        for (String line : userInfoLines) {
            String[] userData = line.split(",");
            if (userData[0].equals(username)) {  // username is the first field in the user file
                // Update the balance for the logged-in user
                System.out.println("Updating balance for user: " + username);  // Debugging
                userData[2] = String.valueOf(balance);  // Update the balance in the array
                line = String.join(",", userData);  // Rebuild the line with the updated balance
                System.out.println("New Balance: " + balance);  // Debugging
            }
            updatedUserInfoLines.add(line);  // Add the (possibly updated) line to the list
        }

        // Write back all the data (with the updated balance for the logged-in user) to the file
        try (PrintWriter writer = new PrintWriter(new FileWriter(userFile))) {
            for (String updatedLine : updatedUserInfoLines) {
                writer.println(updatedLine);
            }
        }

        // Update the in-memory userInfoLines to reflect the updated user data
        userInfoLines = updatedUserInfoLines;

        // Debugging: Print the updated user data
        System.out.println("Updated Data:");
        for (String updatedLine : updatedUserInfoLines) {
            System.out.println(updatedLine);  // Print the updated lines for debugging
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error updating user data!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == balanceButton) {
            JOptionPane.showMessageDialog(this, "Current Balance: $" + balance);
        } else if (e.getSource() == depositButton) {
            String depositAmount = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
            if (depositAmount != null && !depositAmount.isEmpty()) {
                double amount = Double.parseDouble(depositAmount);
                balance += amount;
                transactionHistory.add("Deposited: $" + amount);
                JOptionPane.showMessageDialog(this, "Deposit Successful! New Balance: $" + balance);
                updateUserData();  
            }
        } else if (e.getSource() == withdrawButton) {
            String withdrawAmount = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            if (withdrawAmount != null && !withdrawAmount.isEmpty()) {
                double amount = Double.parseDouble(withdrawAmount);
                if (amount <= balance) {
                    balance -= amount;
                    transactionHistory.add("Withdrew: $" + amount);
                    JOptionPane.showMessageDialog(this, "Withdrawal Successful! Remaining Balance: $" + balance);
                    updateUserData();  
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient balance!");
                }
            }
        } else if (e.getSource() == historyButton) {
            StringBuilder history = new StringBuilder("Transaction History:\n");
            for (String transaction : transactionHistory) {
                history.append(transaction).append("\n");
            }
            JOptionPane.showMessageDialog(this, history.toString());
        } else if (e.getSource() == logoutButton) {
            JOptionPane.showMessageDialog(this, "Logged out successfully!");
            dispose();
            new UserLogin();  
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Dashboard("");  // Example username
    }
}

