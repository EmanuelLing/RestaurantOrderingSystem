import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import foodorderingsystem.CallingAPI;
import javax.swing.JPasswordField;

public class Customer_Login {

    private JFrame frame;
    private JTextField txtCustomerName;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Customer_Login window = new Customer_Login();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     * @wbp.parser.entryPoint
     */
    public Customer_Login() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(230, 255, 255));
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Login Account");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setBounds(272, 81, 241, 29);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Customer Name");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(279, 169, 101, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Password");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1.setBounds(279, 233, 85, 14);
        frame.getContentPane().add(lblNewLabel_1_1);

        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(279, 194, 228, 20);
        frame.getContentPane().add(txtCustomerName);
        txtCustomerName.setColumns(10);

        JButton btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(e -> login());
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton.setBounds(348, 364, 89, 23);
        frame.getContentPane().add(btnNewButton);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(279, 257, 228, 20);
        frame.getContentPane().add(passwordField);
    }

    private void login() {
        try {
            String customerName = txtCustomerName.getText();
			char[] pf  = passwordField.getPassword();
			String password = new String(pf);

            CallingAPI api = new CallingAPI();
            api.readCustomerAPI(customerName, password);
            JSONObject response = api.getJsonResponse();

            if (response != null && response.has("customer")) 
            {
                System.out.println("Response: " + response.toString()); 
                JSONArray CustomerArray = response.getJSONArray("customer");

                boolean isValidUser = false;
                String customerID = "";
                for (int i = 0; i < CustomerArray.length(); i++) {
                    JSONObject customer = CustomerArray.getJSONObject(i);
                    if (customer.getString("CustomerName").equals(customerName) && customer.getString("Password").equals(password)) 
                    {
                        isValidUser = true;
                        customerID = customer.getString("CustomerID");
                        break;
                    }
                }

                if (isValidUser) 
                {
                    String finalcustomerID = customerID; // for use in the lambda
                    JOptionPane.showMessageDialog(frame, "Login successful. \nWelcome, " + customerName + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // open homepage(customer homepage)
                    EventQueue.invokeLater(() -> {
                        try {
                            HomePage homepage = new HomePage(finalcustomerID);
                            homepage.setVisible(true);
                            frame.dispose(); // close login frame
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                } else 
                {
                    JOptionPane.showMessageDialog(frame, "Login failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else 
            {
                JOptionPane.showMessageDialog(frame, "No response from server.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
