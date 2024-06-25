import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import foodorderingsystem.CallingAPI;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class viewPayment {

    private JFrame frame;
    private JTextField txtPaymentID;
    private JTextField txtPaymentDateTime;
    private JTextField txtTotal;
    private JComboBox comboType;
    private JLabel lblStaffID;
    private CallingAPI api;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    viewPayment window = new viewPayment("","","");
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
    public viewPayment(String staffID, String orderID,String selectedStaffID) {
        api = new CallingAPI(); // Initialize the CallingAPI instance
        initialize(staffID,orderID,selectedStaffID);
        fetchPaymentDetails(orderID); // Fetch payment details after initialization
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(String staffID,String orderID,String selectedStaffID) {
        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
        frame.setBounds(100, 100, 800, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(230, 255, 255));
        frame.getContentPane().setLayout(null);

        JLabel lblOrders = new JLabel("View Payment");
        lblOrders.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblOrders.setBounds(305, 52, 178, 29);
        frame.getContentPane().add(lblOrders);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    StaffDashboard dashboard = new StaffDashboard(staffID);
                    dashboard.setVisible(true);
                    frame.dispose(); 
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnBack.setBounds(344, 429, 89, 23);
        frame.getContentPane().add(btnBack);

        JLabel lblNewLabel = new JLabel("Payment ID");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(225, 138, 94, 13);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblPaymentDateTime = new JLabel("Payment Date Time");
        lblPaymentDateTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPaymentDateTime.setBounds(225, 163, 131, 13);
        frame.getContentPane().add(lblPaymentDateTime);

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

        lblStaffID = new JLabel("");
        lblStaffID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblStaffID.setBounds(388, 274, 155, 13);
        frame.getContentPane().add(lblStaffID);

        txtPaymentID = new JTextField();
        txtPaymentID.setBounds(386, 138, 157, 19);
        frame.getContentPane().add(txtPaymentID);
        txtPaymentID.setColumns(10);

        txtPaymentDateTime = new JTextField();
        txtPaymentDateTime.setColumns(10);
        txtPaymentDateTime.setBounds(386, 163, 157, 19);
        frame.getContentPane().add(txtPaymentDateTime);

        txtTotal = new JTextField();
        txtTotal.setColumns(10);
        txtTotal.setBounds(386, 191, 157, 19);
        frame.getContentPane().add(txtTotal);

        comboType = new JComboBox();
        comboType.setModel(new DefaultComboBoxModel(new String[] {"Cash", "Credit Card", "Online Banking", "E-Wallet"}));
        comboType.setBounds(384, 217, 159, 21);
        frame.getContentPane().add(comboType);

        JLabel lblOrderID = new JLabel("");
        lblOrderID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOrderID.setBounds(388, 249, 149, 13);
        frame.getContentPane().add(lblOrderID);
        lblOrderID.setText(orderID);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		deletePayment();
        	}
        });
        btnDelete.setBounds(344, 399, 89, 23);
        frame.getContentPane().add(btnDelete);

        fetchPaymentDetails(orderID);
    }

    private void fetchPaymentDetails(String orderID) {
        try {
            api.readPaymentAPI(orderID);
            JSONObject jsonResponse = api.getJsonResponse();
            if (jsonResponse != null && jsonResponse.has("payment")) 
            {
                JSONArray payments = jsonResponse.getJSONArray("payment");
                if (payments.length() > 0) 
                {
                    JSONObject payment = payments.getJSONObject(0);
                    txtPaymentID.setText(payment.getString("PaymentID"));
                    txtPaymentDateTime.setText(payment.getString("PaymentDateTime"));
                    txtTotal.setText(String.valueOf(payment.getDouble("TotalPayment")));
                    comboType.setSelectedItem(payment.getString("PaymentType"));
                    lblStaffID.setText(payment.optString("StaffID"));
                }
            } else 
            {
                JOptionPane.showMessageDialog(frame, "No payment details found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred while fetching payment details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deletePayment() {
        try {
            String paymentID = txtPaymentID.getText();
            if (paymentID.isEmpty()) 
            {
                JOptionPane.showMessageDialog(frame, "Payment ID is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            api.deletePayment(paymentID);
            JSONObject jsonResponse = api.getJsonResponse();
            if (jsonResponse != null && jsonResponse.has("message") && jsonResponse.getString("message").equals("payment successfully deleted")) 
            {
                JOptionPane.showMessageDialog(frame, "Payment deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else 
            {
                JOptionPane.showMessageDialog(frame, "Failed to delete payment.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred while deleting payment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void clearFields() {
        txtPaymentID.setText("");
        txtPaymentDateTime.setText("");
        txtTotal.setText("");
        comboType.setSelectedItem("");
        lblStaffID.setText("");
    }
}
