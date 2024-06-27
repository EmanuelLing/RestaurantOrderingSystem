import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class HomePage {

    private JFrame frame;

    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomePage window = new HomePage("");
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
    public HomePage(String customerID) {
        initialize(customerID);
    }

    /**
     * Make the frame visible.
     */
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(String customerID) {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(230, 255, 255));
        frame.getContentPane().setLayout(null);
        // to scale image to the size of the bounds
     	ImageIcon icon = new ImageIcon(getClass().getResource("resources/Ordering system Logo.png"));
        JButton btnPaidOrders = new JButton("Order History");
        btnPaidOrders.setBounds(268, 378, 250, 29);
        frame.getContentPane().add(btnPaidOrders);
        btnPaidOrders.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			OrderHistory paidOrders = new OrderHistory(customerID);
        			paidOrders.setVisible(true);
        			frame.dispose();
        			} catch (Exception e1) {
        				e1.printStackTrace();
        				}
        		}
        	});
        btnPaidOrders.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JLabel lblStaffDashboard = new JLabel("");
        lblStaffDashboard.setBounds(257, 24, 272, 289);
        frame.getContentPane().add(lblStaffDashboard);
        Image img = icon.getImage().getScaledInstance(lblStaffDashboard.getWidth(), lblStaffDashboard.getHeight(), 0);
        lblStaffDashboard.setIcon(new ImageIcon(img));
        JButton btnNewOrder = new JButton("New Order");
        btnNewOrder.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FoodMenu foodmenu = new FoodMenu(customerID);
        		foodmenu.setVisible(true);
    			frame.dispose();
        	}
        });
        btnNewOrder.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnNewOrder.setBounds(268, 339, 250, 29);
        frame.getContentPane().add(btnNewOrder);
        
        JButton LogOutButton = new JButton("Log Out");
        LogOutButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null,"Log out Successful");
        		System.exit(0);
        	}
        });
        LogOutButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        LogOutButton.setBounds(268, 417, 250, 29);
        frame.getContentPane().add(LogOutButton);
    }
}
