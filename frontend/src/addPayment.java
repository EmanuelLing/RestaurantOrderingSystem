import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.JSONObject;

import foodorderingsystem.CallingAPI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addPayment {

    private JFrame frame;
    private JTextField txtPaymentID;
    private JTextField txtTotal;
    private JComboBox<String> comboType;
    private CallingAPI api; // Declare the CallingAPI instance
    private JLabel lblStaffID;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    addPayment window = new addPayment("","");
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public addPayment(String orderID,String staffID) {
        api = new CallingAPI(); 
        initialize(orderID,staffID);
    }
    
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(String orderID,String staffID) {
        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
        frame.setBounds(100, 100, 800, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(230, 255, 255));
        frame.getContentPane().setLayout(null);

        JLabel lblOrders = new JLabel("Add Payment");
        lblOrders.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblOrders.setBounds(301, 61, 197, 29);
        frame.getContentPane().add(lblOrders);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 try {
                     UnpaidOrder unpaidOrder = new UnpaidOrder(staffID);
                     unpaidOrder.setVisible(true);
                     frame.dispose(); 
                 } catch (Exception e1) {
                     e1.printStackTrace();
                 }
        	}
        });
        btnBack.setBounds(345, 408, 89, 23);
        frame.getContentPane().add(btnBack);

        JLabel lblNewLabel = new JLabel("Payment ID");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(225, 161, 94, 13);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblTotalPayment = new JLabel("Total Payment");
        lblTotalPayment.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTotalPayment.setBounds(225, 191, 131, 13);
        frame.getContentPane().add(lblTotalPayment);

        JLabel lblPaymentType = new JLabel("Payment Type");
        lblPaymentType.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPaymentType.setBounds(225, 218, 131, 13);
        frame.getContentPane().add(lblPaymentType);

        JLabel lblOrderId = new JLabel("Order ID");
        lblOrderId.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOrderId.setBounds(225, 247, 131, 13);
        frame.getContentPane().add(lblOrderId);

        JLabel lblStaffId = new JLabel("Staff ID");
        lblStaffId.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblStaffId.setBounds(225, 274, 131, 13);
        frame.getContentPane().add(lblStaffId);

        txtPaymentID = new JTextField();
        txtPaymentID.setBounds(386, 161, 157, 19);
        frame.getContentPane().add(txtPaymentID);
        txtPaymentID.setColumns(10);

        txtTotal = new JTextField();
        txtTotal.setColumns(10);
        txtTotal.setBounds(386, 191, 157, 19);
        frame.getContentPane().add(txtTotal);
        
        comboType = new JComboBox<>();
        comboType.setModel(new DefaultComboBoxModel<>(new String[] {"Cash", "Credit Card", "Online Banking", "E-Wallet"}));
        comboType.setBounds(384, 217, 159, 21);
        frame.getContentPane().add(comboType);
        
        JLabel lblOrderID = new JLabel("");
        lblOrderID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOrderID.setBounds(386, 249, 157, 13);
        frame.getContentPane().add(lblOrderID);
        lblOrderID.setText(orderID);
        
        JLabel lblStaffID = new JLabel("");
        lblStaffID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblStaffID.setBounds(389, 274, 154, 13);
        frame.getContentPane().add(lblStaffID);
        lblStaffID.setText(staffID);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		addPaymentRecord(orderID,staffID);
        	}
        });
        btnAdd.setBounds(345, 378, 89, 23);
        frame.getContentPane().add(btnAdd);
        



        
    }

    private void addPaymentRecord(String orderID, String staffID) {
        try {
        
            // get current time
            String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            
            api.addPaymentAPI(
                txtPaymentID.getText(),
                txtTotal.getText(),
                currentTime,
                comboType.getSelectedItem().toString(),
                orderID,
                staffID
            );

            // get response
            JSONObject response = api.getJsonResponse();
            if (response != null && response.has("message") && response.getString("message").equals("payment successfully added")) {
                JOptionPane.showMessageDialog(frame, "Payment added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                updateOrderStatus(orderID, "Pending",staffID);
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to add payment.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void updateOrderStatus(String orderID, String status, String staffID) {
        try {
            
            api.updateCustomerOrderAPI(orderID, status, staffID);
            JSONObject response = api.getJsonResponse();
            
            if (response != null && response.has("message") && response.getString("message").equals("customer order successfully updated")) {
                JOptionPane.showMessageDialog(frame, "Order status updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to update order status.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
   