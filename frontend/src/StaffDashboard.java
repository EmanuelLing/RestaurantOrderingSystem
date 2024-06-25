import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffDashboard {

    private JFrame frame;

    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StaffDashboard window = new StaffDashboard("");
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
    public StaffDashboard(String staffID) {
        initialize(staffID);
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
    private void initialize(String staffID) {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(230, 255, 255));
        frame.getContentPane().setLayout(null);

        JLabel lblStaffDashboard = new JLabel("Staff Dashboard");
        lblStaffDashboard.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblStaffDashboard.setBounds(282, 84, 197, 29);
        frame.getContentPane().add(lblStaffDashboard);

        JButton btnPaidOrders = new JButton("Paid Orders");
        btnPaidOrders.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 try {
                     PaidOrders paidOrders = new PaidOrders(staffID);
                     paidOrders.setVisible(true);
                     frame.dispose();
                     } catch (Exception e1) {
                     e1.printStackTrace();
                 }
        	}
        });
        btnPaidOrders.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnPaidOrders.setBounds(259, 175, 250, 29);
        frame.getContentPane().add(btnPaidOrders);

        JButton btnUnpaidOrders = new JButton("Unpaid Orders");
        btnUnpaidOrders.addActionListener(new ActionListener() {
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
        btnUnpaidOrders.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnUnpaidOrders.setBounds(259, 224, 250, 29);
        frame.getContentPane().add(btnUnpaidOrders);

        JButton btnCompletedCancelled = new JButton("Completed / \r\nCancelled Orders");
        btnCompletedCancelled.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
          		 try {
          			 CompletedCancelledOrder completedCancelledOrder = new CompletedCancelledOrder(staffID);
          			 completedCancelledOrder.setVisible(true);
          			frame.dispose();
                 } catch (Exception e1) {
                     e1.printStackTrace();
                 }
        	}
        });
        btnCompletedCancelled.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCompletedCancelled.setBounds(259, 267, 250, 29);
        frame.getContentPane().add(btnCompletedCancelled);
    }
    

}
