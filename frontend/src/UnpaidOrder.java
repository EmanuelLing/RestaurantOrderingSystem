

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import foodorderingsystem.CallingAPI;

public class UnpaidOrder {

	private JFrame frame;
	private JTable tablePaidOrderList;
	private CallingAPI api;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnpaidOrder window = new UnpaidOrder("");
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
	public UnpaidOrder(String staffID) {
		api = new CallingAPI();

		initialize(staffID);
		fetchAndDisplayOrders("Unpaid");
	}

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String staffID) {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.setBounds(100, 100, 800, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(230, 255, 255));
		frame.getContentPane().setLayout(null);
		
		JLabel lblOrders = new JLabel("Unpaid Orders");
		lblOrders.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblOrders.setBounds(294, 29, 177, 29);
		frame.getContentPane().add(lblOrders);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 85, 679, 356);
		frame.getContentPane().add(scrollPane);
		
		tablePaidOrderList = new JTable();
		scrollPane.setViewportView(tablePaidOrderList);

	    
		tablePaidOrderList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tablePaidOrderList.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"OrderID", "OrderDateTime","OrderType", "TableNo", "Status","CustomerID","StaffID","TotalPrice"
			}
		));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
                     StaffDashboard dashboard = new StaffDashboard(staffID);
                     dashboard.setVisible(true);
                     frame.dispose(); // close login frame
                 } catch (Exception e1) {
                     e1.printStackTrace();
                 }
			}
		});
		btnBack.setBounds(359, 461, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JButton btnView = new JButton("View Order");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DefaultTableModel dtm = (DefaultTableModel) tablePaidOrderList.getModel();
			        int selectedIndex = tablePaidOrderList.getSelectedRow();
			        if (selectedIndex != -1) 
			        {
			            String selectedOrderID = dtm.getValueAt(selectedIndex, 0).toString();
			            String selectedStaffID = dtm.getValueAt(selectedIndex, 6).toString();
			            String selectedStatus = dtm.getValueAt(selectedIndex, 4).toString();
			            
			            try {
			            	 orderDetails detailsWindow = new orderDetails(staffID,selectedOrderID, selectedStaffID, selectedStatus);
				             detailsWindow.setVisible(true);
		                     frame.dispose(); 
		                 } catch (Exception e1) {
		                     e1.printStackTrace();
		                 }


			        }
			}
		});
		btnView.setBounds(458, 461, 111, 23);
		frame.getContentPane().add(btnView);
		
		JButton btnConfirmPayment = new JButton("Confirm Payment");
		btnConfirmPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DefaultTableModel dtm = (DefaultTableModel) tablePaidOrderList.getModel();
			        int selectedIndex = tablePaidOrderList.getSelectedRow();
			        if (selectedIndex != -1) 
			        {
			            String selectedOrderID = dtm.getValueAt(selectedIndex, 0).toString();
			            String selectedStaffID = dtm.getValueAt(selectedIndex, 6).toString();
			            String selectedStatus = dtm.getValueAt(selectedIndex, 4).toString();
			            
			            try {
		                     addPayment addPayment = new addPayment(selectedOrderID,staffID);
		                     addPayment.setVisible(true);
		                     frame.dispose(); // close login frame
		                 } catch (Exception e1) {
		                     e1.printStackTrace();
		                 }
			        }
			}
		});
		btnConfirmPayment.setBounds(199, 461, 150, 23);
		frame.getContentPane().add(btnConfirmPayment);

	}
	
	private void fetchAndDisplayOrders(String status) {
		try {
			api.readCustomerOrderAPI(status);
			JSONObject jsonResponse = api.getJsonResponse();
			if (jsonResponse != null) 
			{
				JSONArray orders = jsonResponse.getJSONArray("customer_order");
				DefaultTableModel model = (DefaultTableModel) tablePaidOrderList.getModel();
				for (int i = 0; i < orders.length(); i++) 
				{
					JSONObject order = orders.getJSONObject(i);
	                model.addRow(new Object[]{
	                    order.getString("OrderID"),
	                    order.getString("OrderDateTime"),
	                    order.getString("OrderType"),
	                    order.getInt("TableNo"),
	                    order.getString("Status"),
	                    order.getString("CustomerID"),
	                    order.optString("StaffID"), 
	                    order.getDouble("TotalPrice")
	                });
				}
			} else 
			{
				System.out.println("No response from the API");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	 
}
