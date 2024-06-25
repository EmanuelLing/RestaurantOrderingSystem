import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;
import foodorderingsystem.CallingAPI;

public class PaidOrders {

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
					PaidOrders window = new PaidOrders("");
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
	public PaidOrders(String staffID) {
		api = new CallingAPI();
		initialize(staffID);
		fetchAndDisplayOrders("Pending");
		fetchAndDisplayOrders("InProgress");
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

		JLabel lblOrders = new JLabel("Paid Orders");
		lblOrders.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblOrders.setBounds(317, 30, 147, 29);
		frame.getContentPane().add(lblOrders);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 85, 679, 330);
		frame.getContentPane().add(scrollPane);
		

		tablePaidOrderList = new JTable();
		scrollPane.setViewportView(tablePaidOrderList);
		tablePaidOrderList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tablePaidOrderList.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"OrderID", "OrderDateTime", "OrderType", "TableNo", "Status", "CustomerID", "StaffID", "TotalPrice"
			}
		));

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
		btnBack.setBounds(346, 462, 89, 23);
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
		btnView.setBounds(335, 436, 111, 23);
		frame.getContentPane().add(btnView);
	}

	private void fetchAndDisplayOrders(String status) {
		try {
			api.readCustomerOrderAPI(status);
			JSONObject jsonResponse = api.getJsonResponse();
			if (jsonResponse != null) {
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
			} else {
				System.out.println("No response from the API");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
