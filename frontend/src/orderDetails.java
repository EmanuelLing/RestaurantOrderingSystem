

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class orderDetails {

	private JFrame frame;
	private JTable tablePaidOrderList;
	private JComboBox<String> comboStatus;
	private CallingAPI api;
	private JLabel lblStaffID;
	private JTextField txtStaffID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					orderDetails window = new orderDetails("","", "", "Pending");
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
	public orderDetails(String staffID,String orderID, String selectedStaffID, String status) {
		api = new CallingAPI(); 
        initialize(staffID, orderID, selectedStaffID, status);
        fetchMealDetails(orderID);
	}
	
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

	/**
	 * Initialize the contents of the frame.
	 * @param status 
	 * @param staffID 
	 * @param orderID 
	 */
	private void initialize(String staffID, String orderID, String selectedStaffID, String status) {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.setBounds(100, 100, 800, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(230, 255, 255));
		frame.getContentPane().setLayout(null);
		
		JLabel lblOrders = new JLabel("Order Details");
		lblOrders.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblOrders.setBounds(310, 30, 167, 29);
		frame.getContentPane().add(lblOrders);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 148, 679, 289);
		frame.getContentPane().add(scrollPane);
		
		tablePaidOrderList = new JTable();
		scrollPane.setViewportView(tablePaidOrderList);
		tablePaidOrderList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				

			}
		});
	    
		tablePaidOrderList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tablePaidOrderList.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"MealID", "MealName","MealType", "Price", "Quantity"
				//Meal Image
			}
		));
		
		JButton btnBack = new JButton("Back To Dashboard");
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
		btnBack.setBounds(320, 461, 167, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Order ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(69, 92, 61, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblStaffId = new JLabel("Staff ID");
		lblStaffId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStaffId.setBounds(222, 92, 75, 14);
		frame.getContentPane().add(lblStaffId);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStatus.setBounds(405, 92, 61, 14);
		frame.getContentPane().add(lblStatus);
		
		txtStaffID = new JTextField();
		txtStaffID.setBounds(279, 90, 96, 19);
		frame.getContentPane().add(txtStaffID);
		txtStaffID.setColumns(10);

			txtStaffID.setText(staffID);



		
		comboStatus = new JComboBox();
		comboStatus.setModel(new DefaultComboBoxModel(new String[] {"Pending", "InProgress", "Unpaid", "Completed", "Canceled"}));
		comboStatus.setBounds(453, 88, 159, 21);
		comboStatus.setSelectedItem(status); 
		frame.getContentPane().add(comboStatus);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 updateOrder(selectedStaffID,orderID,staffID);
			}
		});
		btnNewButton.setBounds(622, 86, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblMealDetails = new JLabel("Meal Details");
		lblMealDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMealDetails.setBounds(355, 123, 76, 14);
		frame.getContentPane().add(lblMealDetails);
		
		JLabel lblOrderID = new JLabel("New label");
		lblOrderID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrderID.setBounds(140, 93, 89, 13);
		frame.getContentPane().add(lblOrderID);
		lblOrderID.setText(orderID);
		


	}

	private void fetchMealDetails(String orderID) {
        try {
            api.readOrderMealAPI(orderID);
            JSONArray orderMeals = api.getJsonResponse().getJSONArray("ordered_meals");

            DefaultTableModel model = (DefaultTableModel) tablePaidOrderList.getModel();
            for (int i = 0; i < orderMeals.length(); i++) 
            {
                JSONObject orderMeal = orderMeals.getJSONObject(i);
                String mealID = orderMeal.getString("MealID");
                int quantity = orderMeal.getInt("Quantity");

                api.readMealAPI();
                JSONArray meals = api.getJsonResponse().getJSONArray("Meals");
                for (int j = 0; j < meals.length(); j++) 
                {
                    JSONObject meal = meals.getJSONObject(j);
                    if (meal.getString("MealID").equals(mealID)) {
                        String mealName = meal.getString("MealName");
                        String mealType = meal.getString("MealType");
                        double price = meal.getDouble("Price");
                        model.addRow(new Object[] { mealID, mealName, mealType, price, quantity });
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
   
	private void updateOrder(String selectedStaffID,String orderID,String staffID) {
	    try {
	        
	        String status = comboStatus.getSelectedItem().toString();	
	        String txtStaff = txtStaffID.getText();
	        api.updateCustomerOrderAPI(orderID, status, txtStaff);
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