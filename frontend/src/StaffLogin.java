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

public class StaffLogin {

    private JFrame frame;
    private JTextField txtStaffName;
    private JTextField txtPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StaffLogin window = new StaffLogin();
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
    public StaffLogin() {
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

        JLabel lblNewLabel = new JLabel("Login Staff Account");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setBounds(264, 81, 241, 29);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Staff Name");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(264, 169, 85, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Password");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1.setBounds(264, 233, 85, 14);
        frame.getContentPane().add(lblNewLabel_1_1);

        txtStaffName = new JTextField();
        txtStaffName.setBounds(264, 194, 228, 20);
        frame.getContentPane().add(txtStaffName);
        txtStaffName.setColumns(10);

        txtPassword = new JTextField();
        txtPassword.setColumns(10);
        txtPassword.setBounds(264, 253, 228, 20);
        frame.getContentPane().add(txtPassword);

        JButton btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(e -> login());
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton.setBounds(326, 394, 89, 23);
        frame.getContentPane().add(btnNewButton);
    }

    private void login() {
        try {
            String staffName = txtStaffName.getText();
            String password = txtPassword.getText();

            CallingAPI api = new CallingAPI();
            api.readStaffAPI(staffName, password);
            JSONObject response = api.getJsonResponse();

            if (response != null && response.has("staff")) 
            {
                System.out.println("Response: " + response.toString()); 
                JSONArray staffArray = response.getJSONArray("staff");

                boolean isValidUser = false;
                String staffID = "";
                for (int i = 0; i < staffArray.length(); i++) {
                    JSONObject staff = staffArray.getJSONObject(i);
                    if (staff.getString("StaffName").equals(staffName) && staff.getString("Password").equals(password)) 
                    {
                        isValidUser = true;
                        staffID = staff.getString("StaffID");
                        break;
                    }
                }

                if (isValidUser) 
                {
                    String finalStaffID = staffID; // for use in the lambda
                    JOptionPane.showMessageDialog(frame, "Login successful. \nWelcome, " + staffName + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // open staff dashboard
                    EventQueue.invokeLater(() -> {
                        try {
                            StaffDashboard dashboard = new StaffDashboard(finalStaffID);
                            dashboard.setVisible(true);
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
