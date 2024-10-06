import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class UserInfo {
    JFrame frame;
    JLabel label;
    JTable table;
    JTextField searchField, nameField, accountField, balanceField;
    JButton search, exit, update, delete, addAdmin;
    DefaultTableModel tableModel;
    File file = new File("UserInfo.txt");

    public UserInfo() {
        frame = new JFrame("User Information");
        frame.getContentPane().setBackground(new Color(114,114,114));
        frame.setBounds(200, 50, 600, 500);
        frame.setLayout(null);
        frame.setResizable(false);

        label = new JLabel("Search By Account Number");
        label.setForeground(Color.BLACK);
        label.setBounds(50, 20, 200, 30);

        searchField = new JTextField();
        searchField.setBounds(250, 20, 220, 30);

        search = new JButton("Search");
        search.setBounds(480, 20, 100, 30);
        search.addActionListener(e -> searchUser());

        update = new JButton("Update");
        update.setBounds(50, 400, 100, 30);
        update.addActionListener(e -> updateUser());
        
        
        
        addAdmin = new JButton("Add Admin");
        addAdmin.setBounds(50, 350, 210, 30);
        addAdmin.addActionListener(e -> new AddAdmin());

        delete = new JButton("Delete");
        delete.setBounds(160, 400, 100, 30);
        delete.addActionListener(e -> deleteUser());

        exit = new JButton("Exit");
        exit.setBounds(450, 400, 100, 30);
        exit.addActionListener(e -> frame.dispose());

        String[] columnNames = {"Name", "Account No.", "Balance", "DoB", "NID", "Phone"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        loadUserData(); 
        
        
        /*ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/LoginB.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 500, 1);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 600, 600);*/

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 60, 500, 200);
        table.setSelectionBackground(Color.BLUE);

        frame.add(label);
        frame.add(scrollPane);
        frame.add(searchField);
        frame.add(search);
        frame.add(update);
        frame.add(delete);
        frame.add(addAdmin);
        frame.add(exit);
        //frame.add(image);
        

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Search user by account number
    private void searchUser() {
        String accNo = searchField.getText();
        boolean found = false;

        for (int row = 0; row < table.getRowCount(); row++) {
            String tableAccountNumber = table.getValueAt(row, 1).toString();
            if (tableAccountNumber.equals(accNo)) {
                found = true;
                String name = table.getValueAt(row, 0).toString();
                String balance = table.getValueAt(row, 2).toString();

                JOptionPane.showMessageDialog(frame,
                        "Name: " + name + "\nAccount No: " + tableAccountNumber +
                                "\nBalance: " + balance,
                        "Information Found", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(frame, "Account Number not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

   
private void updateUser() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(frame, "Please select a row to update", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    
    String currentName = table.getValueAt(selectedRow, 0).toString();
    String currentPhone = table.getValueAt(selectedRow, 5).toString();

    
    String newName = JOptionPane.showInputDialog("Enter new name (leave empty to keep current):", currentName);
    if (newName != null && !newName.trim().isEmpty()) {
        table.setValueAt(newName.trim(), selectedRow, 0); 
    }

    
    String newPhone = JOptionPane.showInputDialog("Enter new phone number (leave empty to keep current):", currentPhone);
    if (newPhone != null && !newPhone.trim().isEmpty()) {
        table.setValueAt(newPhone.trim(), selectedRow, 5); 
    }

    saveUserData(); 
    JOptionPane.showMessageDialog(frame, "Information updated successfully!");
}


    
    private void deleteUser() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(frame, "Please select a row to delete", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String usernameToDelete = table.getValueAt(selectedRow, 0).toString(); // Username is the first column
    tableModel.removeRow(selectedRow); // Remove user from the table
    saveUserData(); // Save the updated table data to UserInfo.txt
    deleteUserFromCredentials(usernameToDelete); // Delete from credentials.txt
    JOptionPane.showMessageDialog(frame, "User and their credentials deleted successfully!");
}


private void deleteUserFromCredentials(String usernameToDelete) {
    File credentialsFile = new File("credentials.txt");
    File tempFile = new File("tempCredentials.txt");

    try (BufferedReader br = new BufferedReader(new FileReader(credentialsFile));
         BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

        String line;
        while ((line = br.readLine()) != null) {
            String[] credentialsData = line.split(",");
            String username = credentialsData[0]; // Username is the first field in credentials.txt

            // Only write the line back if it doesn't match the username to delete
            if (!username.equals(usernameToDelete)) {
                bw.write(line);
                bw.newLine();
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    // Replace the old credentials file with the updated one
    if (!credentialsFile.delete()) {
        JOptionPane.showMessageDialog(frame, "Error deleting old credentials file!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    if (!tempFile.renameTo(credentialsFile)) {
        JOptionPane.showMessageDialog(frame, "Error renaming temporary credentials file!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}



    
    private void loadUserData() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private void saveUserData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int row = 0; row < table.getRowCount(); row++) {
                for (int col = 0; col < table.getColumnCount(); col++) {
                    bw.write(table.getValueAt(row, col).toString());
                    if (col < table.getColumnCount() - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UserInfo();
    }
}

