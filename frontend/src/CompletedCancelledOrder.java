

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import foodorderingsystem.CallingAPI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompletedCancelledOrder {

	private JFrame frame;
	private JTable tablePaidOrderList;
	private CallingAPI api;
	private String selectedOrderID;
	private String selectedStaffID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompletedCancelledOrder window = new CompletedCancelledOrder("");
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
	public CompletedCancelledOrder(String staffID) {
		api = new CallingAPI();
		initialize(staffID);
		fetchAndDisplayOrders("Completed");
		fetchAndDisplayOrders("Cancelled");
	}

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
		
		JLabel lblOrders = new JLabel("Completed / Cancelled Orders");
		lblOrders.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblOrders.setBounds(206, 33, 364, 29);
		frame.getContentPane().add(lblOrders);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 84, 679, 361);
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
		btnBack.setBounds(523, 463, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) tablePaidOrderList.getModel();
                int selectedIndex = tablePaidOrderList.getSelectedRow();
                if (selectedIndex != -1) {
                    String selectedOrderID = dtm.getValueAt(selectedIndex, 0).toString();
                    deleteOrder(selectedOrderID);
                }
			}
		});
		btnDelete.setBounds(424, 463, 89, 23);
		frame.getContentPane().add(btnDelete);
		

		
		JButton btnViewOrder = new JButton("View Order");
		btnViewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DefaultTableModel dtm = (DefaultTableModel) tablePaidOrderList.getModel();
			        int selectedIndex = tablePaidOrderList.getSelectedRow();
			        if (selectedIndex != -1) {
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
		
		btnViewOrder.setBounds(176, 463, 98, 23);
		frame.getContentPane().add(btnViewOrder);
		
		JButton btnView = new JButton("View Payment");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 DefaultTableModel dtm = (DefaultTableModel) tablePaidOrderList.getModel();
			        int selectedIndex = tablePaidOrderList.getSelectedRow();
			        if (selectedIndex != -1) {
			            String selectedOrderID = dtm.getValueAt(selectedIndex, 0).toString();
			            String selectedStaffID = dtm.getValueAt(selectedIndex, 6).toString();

						try {
		                    viewPayment viewPayment = new viewPayment(staffID,selectedOrderID,selectedStaffID);
		                    viewPayment.setVisible(true);
		                    frame.dispose();
		                } catch (Exception e1) {
		                    e1.printStackTrace();
		                }
			            

			        }
			        
			        

			}
		});
		btnView.setBounds(284, 463, 128, 23);
		frame.getContentPane().add(btnView);
		
		

	}
	
	private void fetchAndDisplayOrders(String status) {
		try {
			api.readCustomerOrderAPI(status);
			JSONObject jsonResponse = api.getJsonResponse();
			if (jsonResponse != null) {
				JSONArray orders = jsonResponse.getJSONArray("customer_order");
				DefaultTableModel model = (DefaultTableModel) tablePaidOrderList.getModel();
				for (int i = 0; i < orders.length(); i++) {
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
			} else {
				System.out.println("No response from the API");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteOrder(String orderID) {
	    try {
	        // (1) delete related order_meal record 
	        api.deleteOrderMealAPI(orderID);
	        JSONObject jsonOrderMealResponse = api.getJsonResponse();
	        if (jsonOrderMealResponse != null && jsonOrderMealResponse.has("message") && jsonOrderMealResponse.getString("message").equals("ordered meal successfully deleted")) {
	            // (2) delete customer_order record
	            api.deleteCustomerOrderAPI(orderID);
	            JSONObject jsonOrderResponse = api.getJsonResponse();
	            if (jsonOrderResponse != null && jsonOrderResponse.has("message") && jsonOrderResponse.getString("message").equals("customer order successfully deleted")) {
	                JOptionPane.showMessageDialog(frame, "Order deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	                refreshTable();
	            } else {
	                JOptionPane.showMessageDialog(frame, "Failed to delete order.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(frame, "Failed to delete order meal.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(frame, "An error occurred while deleting the order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void refreshTable() {
	    DefaultTableModel model = (DefaultTableModel) tablePaidOrderList.getModel();
	    model.setRowCount(0); 
	    fetchAndDisplayOrders("Completed"); 
	    fetchAndDisplayOrders("Cancelled"); 
	}
	
	

}
