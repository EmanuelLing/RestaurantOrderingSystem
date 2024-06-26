import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JSlider;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import foodorderingsystem.CallingAPI;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Panel;
import java.awt.List;
import java.awt.ScrollPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Scrollbar;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLayeredPane;
import java.awt.GridBagLayout;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

public class FoodMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TotalTextField;
	private HashMap<String, Double> itemMap = new HashMap<>();
	private Vector<String> itemIDs = new Vector<>();
	private Vector<String> itemNames = new Vector<>();
    private Vector<Double> itemSumPrices = new Vector<>();
    private Vector<Double> itemPrices = new Vector<>();
    private Vector<Integer> itemQuantities = new Vector<>();
    private ArrayList<FoodMenu> orderItems = new ArrayList<>();
	private double total,tax,price,grandTotal = 0.0;
	private String selectedOption;
	JLabel DateLabel = new JLabel("");
	JLabel TimeLabel = new JLabel("");
	JTextArea PreviewTextArea = new JTextArea();
	private String[] MealID;
	private String[] MealName;
	private Integer[] MealQty;
	private double[] MealPrice;
	private String id;
    private String name;
    private int quantity;
    private String OrderType;
    private CallingAPI api = new CallingAPI(); // Instantiate CallingAPI
    private String orderID;
    private String tableNo;
    private String selectedPayment;
    private String PaymentType;
    private String status;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 * @param customerID 
	 * @wbp.parser.constructor
	 */
	public FoodMenu(String customerID) {
		// Initialize arrays
        MealID = new String[28]; // Adjust size as per your requirement
        MealName = new String[18];
        MealQty = new Integer[18];
        MealPrice = new double[18];
		
		food();
		initComponents(customerID);
		init();				
	}
	
    // Constructor
    public FoodMenu(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

	
	public void init() {
		setTime();
		
	}

	public void food()
	{
		MealID[0] = "M00001";
		MealName[0] = "Beef Caramelized Shallot Burger";
		MealPrice[0] = 10.00;
		
		MealID[1] = "M00002";
		MealName[1] = "Double Black Mushroom Burger";
		MealPrice[1] = 15.00;
		
		MealID[2] = "M00003";
		MealName[2] = "Double BBQ Chicken Burger";
		MealPrice[2] = 15.00;
		
		MealID[3] = "M00004";
		MealName[3] = "Garlic Shrimp Cheese Burger";
		MealPrice[3] = 18.00;
		
		MealID[4] = "M00005";
		MealName[4] = "Double Chicken Egg's Benidict Burger";
		MealPrice[4] = 19.50;
		
		MealID[5] = "M00006";
		MealName[5] = "Classic Cheese Burger";
		MealPrice[5] = 10.00;
		
		MealID[6] = "D00001";
		MealName[6] = "Soft Drink Fanta Grape";
		MealPrice[6] = 3.50;
		
		MealID[7] = "D00002";
		MealName[7] = "Soft Drink Fanta Orange";
		MealPrice[7] = 3.50;
		
		MealID[8] = "D00003";
		MealName[8] = "Soft Drink Fanta Strawberry";
		MealPrice[8] = 3.50;
		
		MealID[9] = "D00004";
		MealName[9] = "Soft Drink Sprite";
		MealPrice[9] = 3.50;
		
		MealID[10] = "D00005";
		MealName[10] = "Soft Drink Ice Lemon Tea";
		MealPrice[10] = 3.50;
		
		MealID[11] = "D00006";
		MealName[11] = "Mineral Water Spritzer";
		MealPrice[11] = 1.50;
		
		MealID[12] = "MS0001";
		MealName[12] = "Cheesy Fries";
		MealPrice[12] = 5.00;
		
		MealID[13] = "MS0002";
		MealName[13] = "Cheesy Wedges";
		MealPrice[13] = 5.00;
		
		MealID[14] = "MS0003";
		MealName[14] = "Curly Fries";
		MealPrice[14] = 5.00;
		
		MealID[15] = "MS0004";
		MealName[15] = "Jacket Potato";
		MealPrice[15] = 5.00;
		
		MealID[16] = "MS0005";
		MealName[16] = "Ceaser Salad";
		MealPrice[16] = 4.00;
		
		MealID[17] = "MS0006";
		MealName[17] = "Onion Rings";
		MealPrice[17] = 5.00;
		
	}
	
	
	private void initComponents(String customerID) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 921, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menu");
		lblNewLabel.setBounds(254, 80, 45, 13);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 10, 908, 58);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel OrderingSystemName = new JLabel("Fire Cracker");
		OrderingSystemName.setBounds(10, 17, 163, 24);
		panel.add(OrderingSystemName);
		OrderingSystemName.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		//JLabel TimeLabel = new JLabel("");
		TimeLabel.setBounds(599, 12, 94, 34);
		panel.add(TimeLabel);
		TimeLabel.setBackground(Color.WHITE);
		TimeLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		TimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		//JLabel DateLabel = new JLabel("");
		DateLabel.setBounds(690, 12, 168, 34);
		panel.add(DateLabel);
		DateLabel.setBackground(Color.WHITE);
		DateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DateLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		//JTextArea PreviewTextArea = new JTextArea();
		PreviewTextArea.setBounds(586, 104, 311, 320);
		contentPane.add(PreviewTextArea);
		
		JTabbedPane MenutabbedPane = new JTabbedPane(JTabbedPane.TOP);
		MenutabbedPane.setBounds(10, 80, 548, 463);
		contentPane.add(MenutabbedPane);

		JPanel BurgerTab = new JPanel();
		BurgerTab.setLayout(null);

		// Create a JScrollPane and add the panel to it
		JScrollPane BurgerScrollPane = new JScrollPane(BurgerTab);
		BurgerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		BurgerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		BurgerScrollPane.setBounds(10, 10, 495, 400); // Adjust size if needed
		MenutabbedPane.addTab("Burger", null, BurgerScrollPane, null);
	
		// Adjust the size of the panel to be larger than the JScrollPane's viewport
		BurgerTab.setPreferredSize(new Dimension(495, 440)); // Adjust height as needed

		// Add your BurgerPanels to the panel
		Panel BurgerPanel = new Panel();
		BurgerPanel.setLayout(null);
		BurgerPanel.setBackground(Color.LIGHT_GRAY);
		BurgerPanel.setBounds(0, 0, 170, 220);
		BurgerTab.add(BurgerPanel);
		
		JLabel Image = new JLabel("New label");
		Image.setBounds(0, 0, 170, 105);
		// to scale image to the size of the bounds
		ImageIcon icon = new ImageIcon(getClass().getResource("resources/Black Truffle and Brie Beef Burger with Caramelized Shallots Recipe.jpeg"));
		Image img = icon.getImage().getScaledInstance(Image.getWidth(), Image.getHeight(), DO_NOTHING_ON_CLOSE);
		Image.setIcon(new ImageIcon(img));
		BurgerPanel.add(Image);
		
		JLabel ItemName_0 = new JLabel("<html>Beef Caramelized<br>Shallot Burger</html>");
		ItemName_0.setHorizontalAlignment(SwingConstants.CENTER);
		ItemName_0.setFont(new Font("Times New Roman", Font.BOLD, 12));
		ItemName_0.setBounds(11, 110, 148, 30);
		BurgerPanel.add(ItemName_0);

		JLabel ItemPrice = new JLabel("RM10.00");
		ItemPrice.setHorizontalAlignment(SwingConstants.CENTER);
		ItemPrice.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ItemPrice.setBounds(74, 150, 74, 13);
		BurgerPanel.add(ItemPrice);

		JSpinner spinner0 = new JSpinner();
		spinner0.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		spinner0.setFont(new Font("Times New Roman", Font.BOLD, 12));
		spinner0.setBounds(92, 170, 45, 20);
		BurgerPanel.add(spinner0);

		JLabel lblQuantit = new JLabel("Quantity: ");
		lblQuantit.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblQuantit.setBounds(27, 175, 64, 13);
		BurgerPanel.add(lblQuantit);

		JLabel lblPrice = new JLabel("Price: ");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPrice.setBounds(27, 150, 64, 13);
		BurgerPanel.add(lblPrice);
		
		JLabel lblPurchase = new JLabel("Purchase: ");
		lblPurchase.setHorizontalAlignment(SwingConstants.LEFT);
		lblPurchase.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPurchase.setBounds(27, 197, 64, 13);
		BurgerPanel.add(lblPurchase);
		
		JCheckBox checkboxes0 = new JCheckBox("");
		checkboxes0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int qty = Integer.parseInt(spinner0.getValue().toString());
				MealQty[0] = qty;
				String itemID = MealID[0];
	            String itemName = MealName[0];
	            double SumPrice = MealQty[0] * MealPrice[0];

	                if (qty > 0) {
	                    if (checkboxes0.isSelected()) {
	                    	itemIDs.add(itemID);
	                    	itemNames.add(itemName);
	                    	itemPrices.add(MealPrice[0]);
	                    	itemSumPrices.add(SumPrice);
	                        itemQuantities.add(MealQty[0]);
	                     //  itemMap.put(itemName, SumPrice);
	                     // Update the preview and total
	    	                addRemoveItem();
	                    } else {
	                    	int removeIndex = itemNames.indexOf(itemName);
	                        itemNames.remove(removeIndex);
	                        itemPrices.remove(removeIndex);
	                        itemSumPrices.remove(removeIndex);
	                        itemQuantities.remove(removeIndex);
	                      // itemMap.remove(itemName);
	                     // Update the preview and total
	    	                addRemoveItem();
	                    }
	                } else {
	                    checkboxes0.setSelected(false);
	                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
	                }
			}
		});
		checkboxes0.setFont(new Font("Times New Roman", Font.BOLD, 12));
		checkboxes0.setBounds(98, 193, 21, 21);
		BurgerPanel.add(checkboxes0);
		
		
		Panel BurgerPanel_1 = new Panel();
		BurgerPanel_1.setLayout(null);
		BurgerPanel_1.setBackground(Color.LIGHT_GRAY);
		BurgerPanel_1.setBounds(176, 0,170, 220);
		BurgerTab.add(BurgerPanel_1);
		
		JLabel ItemName_1 = new JLabel("<html>Double Black<br>Mushroom Burger ");
		ItemName_1.setHorizontalAlignment(SwingConstants.CENTER);
		ItemName_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		ItemName_1.setBounds(11, 110, 148, 30);
		BurgerPanel_1.add(ItemName_1);
		
		JLabel Image_1 = new JLabel("New label");
		Image_1.setBounds(0, 0, 170, 105);
		// to scale image to the size of the bounds
		ImageIcon icon1 = new ImageIcon(getClass().getResource("resources/Black Garlic Mushroom Burger_ Umami Tsunami - Chiles and Smoke.jpeg"));
		Image img1 = icon1.getImage().getScaledInstance(Image_1.getWidth(), Image_1.getHeight(), DO_NOTHING_ON_CLOSE);
		Image_1.setIcon(new ImageIcon(img1));
		BurgerPanel_1.add(Image_1);
		
		JLabel ItemPrice_1 = new JLabel("RM15.00");
		ItemPrice_1.setHorizontalAlignment(SwingConstants.CENTER);
		ItemPrice_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ItemPrice_1.setBounds(74, 150, 74, 13);
		BurgerPanel_1.add(ItemPrice_1);
		
		JSpinner spinner1 = new JSpinner();
		spinner1.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		spinner1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		spinner1.setBounds(92, 170, 45, 20);
		BurgerPanel_1.add(spinner1);
		
		JLabel lblQuantit_1 = new JLabel("Quantity: ");
		lblQuantit_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantit_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblQuantit_1.setBounds(27, 175, 64, 13);
		BurgerPanel_1.add(lblQuantit_1);
		
		JLabel lblPrice_1 = new JLabel("Price: ");
		lblPrice_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPrice_1.setBounds(27, 150, 64, 13);
		BurgerPanel_1.add(lblPrice_1);
		
		JLabel lblPurchase_1 = new JLabel("Purchase: ");
		lblPurchase_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPurchase_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPurchase_1.setBounds(27, 197, 64, 13);
		BurgerPanel_1.add(lblPurchase_1);
		
		JCheckBox checkboxes1 = new JCheckBox("");
		checkboxes1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int qty = Integer.parseInt(spinner1.getValue().toString());
				MealQty[1] = qty;
				String itemID = MealID[1];
	            String itemName = MealName[1];
	            double SumPrice = MealQty[1] * MealPrice[1];

	                if (qty > 0) {
	                    if (checkboxes1.isSelected()) {
	                    	itemIDs.add(itemID);
	                    	itemNames.add(itemName);
	                    	itemPrices.add(MealPrice[1]);
	                    	itemSumPrices.add(SumPrice);
	                        itemQuantities.add(MealQty[1]);
	                     //  itemMap.put(itemName, SumPrice);
	                     // Update the preview and total
	    	                addRemoveItem();
	                    } else {
	                    	int removeIndex = itemNames.indexOf(itemName);
	                        itemNames.remove(removeIndex);
	                        itemPrices.remove(removeIndex);
	                        itemSumPrices.remove(removeIndex);
	                        itemQuantities.remove(removeIndex);
	                      // itemMap.remove(itemName);
	                     // Update the preview and total
	    	                addRemoveItem();
	                    }
	                } else {
	                    checkboxes1.setSelected(false);
	                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
	                } 	
			}
		});
		checkboxes1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		checkboxes1.setBounds(98, 193, 21, 21);
		BurgerPanel_1.add(checkboxes1);
		
		Panel BurgerPanel_2 = new Panel();
		BurgerPanel_2.setLayout(null);
		BurgerPanel_2.setBackground(Color.LIGHT_GRAY);
		BurgerPanel_2.setBounds(352, 0, 170, 220);
		BurgerTab.add(BurgerPanel_2);
		
		JLabel Image_2 = new JLabel("New label");
		Image_2.setBounds(0, 0, 170, 105);
		// to scale image to the size of the bounds
		ImageIcon icon2 = new ImageIcon(getClass().getResource("resources/Delicious BBQ Chicken Sandwich Recipe.jpeg"));
		Image img2 = icon2.getImage().getScaledInstance(Image_2.getWidth(),Image_2.getHeight(),DO_NOTHING_ON_CLOSE);
		Image_2.setIcon(new ImageIcon(img2));
		BurgerPanel_2.add(Image_2);
		
		JLabel ItemName_2 = new JLabel("<html>Double BBQ<br>Chicken Burger</html> ");
		ItemName_2.setHorizontalAlignment(SwingConstants.CENTER);
		ItemName_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		ItemName_2.setBounds(11, 110, 148, 30);
		BurgerPanel_2.add(ItemName_2);
		
		JLabel ItemPrice_2 = new JLabel("RM15.00");
		ItemPrice_2.setHorizontalAlignment(SwingConstants.CENTER);
		ItemPrice_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ItemPrice_2.setBounds(74, 150, 74, 13);
		BurgerPanel_2.add(ItemPrice_2);
		
		JSpinner spinner2 = new JSpinner();
		spinner2.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		spinner2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		spinner2.setBounds(92, 170, 45, 20);
		BurgerPanel_2.add(spinner2);
		
		JLabel lblQuantit_2 = new JLabel("Quantity: ");
		lblQuantit_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantit_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblQuantit_2.setBounds(27, 175, 64, 13);
		BurgerPanel_2.add(lblQuantit_2);
		
		JLabel lblPrice_2 = new JLabel("Price: ");
		lblPrice_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPrice_2.setBounds(27, 150, 64, 13);
		BurgerPanel_2.add(lblPrice_2);
		
		JLabel lblPurchase_2 = new JLabel("Purchase: ");
		lblPurchase_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPurchase_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPurchase_2.setBounds(27, 197, 64, 13);
		BurgerPanel_2.add(lblPurchase_2);
		
		JCheckBox checkboxes2 = new JCheckBox("");
		checkboxes2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int qty = Integer.parseInt(spinner2.getValue().toString());
				MealQty[2] = qty;
				String itemID = MealID[2];
	            String itemName = MealName[2];
	            double SumPrice = MealQty[2] * MealPrice[2];

	                if (qty > 0) {
	                    if (checkboxes2.isSelected()) {
	                    	itemIDs.add(itemID);
	                    	itemNames.add(itemName + "\t");
	                    	itemPrices.add(MealPrice[2]);
	                    	itemSumPrices.add(SumPrice);
	                        itemQuantities.add(MealQty[2]);
	                     //  itemMap.put(itemName, SumPrice);
	                     // Update the preview and total
	    	                addRemoveItem();
	                    } else {
	                    	int removeIndex = itemNames.indexOf(itemName + "\t");
	                        itemNames.remove(removeIndex);
	                        itemPrices.remove(removeIndex);
	                        itemSumPrices.remove(removeIndex);
	                        itemQuantities.remove(removeIndex);
	                      // itemMap.remove(itemName);
	                     // Update the preview and total
	    	                addRemoveItem();
	                    }
	                } else {
	                    checkboxes2.setSelected(false);
	                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
	                }
			}
		});
		checkboxes2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		checkboxes2.setBounds(98, 193, 21, 21);
		BurgerPanel_2.add(checkboxes2);
		
		
		Panel BurgerPanel_3 = new Panel();
		BurgerPanel_3.setBounds(0, 220, 170, 220);
		BurgerTab.add(BurgerPanel_3);
		BurgerPanel_3.setLayout(null);
		BurgerPanel_3.setBackground(Color.LIGHT_GRAY);
		
		JLabel Image_3 = new JLabel("New label");
		Image_3.setBounds(0, 0, 170, 105);
		ImageIcon icon3 = new ImageIcon(getClass().getResource("resources/MEXICAN CHORIZO + GARLIC SHRIMP BURGER.jpeg"));
		Image img3 = icon3.getImage().getScaledInstance(Image_3.getWidth(),Image_3.getHeight(), DO_NOTHING_ON_CLOSE);
		Image_3.setIcon(new ImageIcon(img3));
		BurgerPanel_3.add(Image_3);
		
		JLabel ItemName_3 = new JLabel("<html>Garlic Shrimp<br>Cheese Burger");
		ItemName_3.setHorizontalAlignment(SwingConstants.CENTER);
		ItemName_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		ItemName_3.setBounds(11, 110, 148, 30);
		BurgerPanel_3.add(ItemName_3);
		
		JLabel ItemPrice_3 = new JLabel("RM18.00");
		ItemPrice_3.setHorizontalAlignment(SwingConstants.CENTER);
		ItemPrice_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ItemPrice_3.setBounds(74, 150, 74, 13);
		BurgerPanel_3.add(ItemPrice_3);
		
		JSpinner spinner3 = new JSpinner();
		spinner3.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		spinner3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		spinner3.setBounds(92, 170, 45, 20);
		BurgerPanel_3.add(spinner3);
		
		JLabel lblQuantit_3 = new JLabel("Quantity: ");
		lblQuantit_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantit_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblQuantit_3.setBounds(27, 175, 64, 13);
		BurgerPanel_3.add(lblQuantit_3);
		
		JLabel lblPrice_3 = new JLabel("Price: ");
		lblPrice_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPrice_3.setBounds(27, 150, 64, 13);
		BurgerPanel_3.add(lblPrice_3);
		
		JLabel lblPurchase_3 = new JLabel("Purchase: ");
		lblPurchase_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblPurchase_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPurchase_3.setBounds(27, 197, 64, 13);
		BurgerPanel_3.add(lblPurchase_3);
		
		JCheckBox checkboxes3 = new JCheckBox("");
		checkboxes3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int qty = Integer.parseInt(spinner3.getValue().toString());
				MealQty[3] = qty;
				String itemID = MealID[3];
	            String itemName = MealName[3];
	            double SumPrice = MealQty[3] * MealPrice[3];

	                if (qty > 0) {
	                    if (checkboxes3.isSelected()) {
	                    	itemIDs.add(itemID);
	                    	itemNames.add(itemName);
	                    	itemPrices.add(MealPrice[3]);
	                    	itemSumPrices.add(SumPrice);
	                        itemQuantities.add(MealQty[3]);
	                     //  itemMap.put(itemName, SumPrice);
	                     // Update the preview and total
	    	                addRemoveItem();
	                    } else {
	                    	int removeIndex = itemNames.indexOf(itemName);
	                        itemNames.remove(removeIndex);
	                        itemPrices.remove(removeIndex);
	                        itemSumPrices.remove(removeIndex);
	                        itemQuantities.remove(removeIndex);
	                      // itemMap.remove(itemName);
	                     // Update the preview and total
	    	                addRemoveItem();
	                    }
	                } else {
	                    checkboxes3.setSelected(false);
	                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
	                }
			}
		});
		checkboxes3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		checkboxes3.setBounds(98, 193, 21, 21);
		BurgerPanel_3.add(checkboxes3);
		
		Panel BurgerPanel_4 = new Panel();
		BurgerPanel_4.setLayout(null);
		BurgerPanel_4.setBackground(Color.LIGHT_GRAY);
		BurgerPanel_4.setBounds(176, 220, 170, 220);
		BurgerTab.add(BurgerPanel_4);
		
		JLabel Image_4 = new JLabel("New label");
		Image_4.setBounds(0, 0, 170, 105);
		// to scale image to the size of the bounds
		ImageIcon icon4 = new ImageIcon(getClass().getResource("resources/Delicious Chicken Burger Recipe.jpeg"));
		Image img4 = icon4.getImage().getScaledInstance(Image_4.getWidth(),Image_4.getHeight(),DO_NOTHING_ON_CLOSE);
		Image_4.setIcon(new ImageIcon(img4));
		BurgerPanel_4.add(Image_4);
		
		JLabel ItemName_4 = new JLabel("<html>Double Chicken<br>Egg's Benedict Burger</html>");
		ItemName_4.setHorizontalAlignment(SwingConstants.CENTER);
		ItemName_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		ItemName_4.setBounds(11, 110, 148, 30);
		BurgerPanel_4.add(ItemName_4);
		
		JLabel ItemPrice_4 = new JLabel("RM19.50");
		ItemPrice_4.setHorizontalAlignment(SwingConstants.CENTER);
		ItemPrice_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ItemPrice_4.setBounds(74, 150, 74, 13);
		BurgerPanel_4.add(ItemPrice_4);
		
		JSpinner spinner4 = new JSpinner();
		spinner4.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		spinner4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		spinner4.setBounds(92, 170, 45, 20);
		BurgerPanel_4.add(spinner4);
		
		JLabel lblQuantit_4 = new JLabel("Quantity: ");
		lblQuantit_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantit_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblQuantit_4.setBounds(27, 175, 64, 13);
		BurgerPanel_4.add(lblQuantit_4);
		
		JLabel lblPrice_4 = new JLabel("Price: ");
		lblPrice_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPrice_4.setBounds(27, 150, 64, 13);
		BurgerPanel_4.add(lblPrice_4);
		
		JLabel lblPurchase_4 = new JLabel("Purchase: ");
		lblPurchase_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblPurchase_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPurchase_4.setBounds(27, 197, 64, 13);
		BurgerPanel_4.add(lblPurchase_4);
		
		JCheckBox checkboxes4 = new JCheckBox("");
		checkboxes4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int qty = Integer.parseInt(spinner4.getValue().toString());
				MealQty[4] = qty;
				String itemID = MealID[4];
	            String itemName = MealName[4];
	            double SumPrice = MealQty[4] * MealPrice[4];

	                if (qty > 0) {
	                    if (checkboxes4.isSelected()) {
	                    	itemIDs.add(itemID);
	                    	itemNames.add(itemName);
	                    	itemPrices.add(MealPrice[4]);
	                    	itemSumPrices.add(SumPrice);
	                        itemQuantities.add(MealQty[4]);
	                     //  itemMap.put(itemName, SumPrice);
	                     // Update the preview and total
	    	                addRemoveItem();
	                    } else {
	                    	int removeIndex = itemNames.indexOf(itemName);
	                        itemNames.remove(removeIndex);
	                        itemPrices.remove(removeIndex);
	                        itemSumPrices.remove(removeIndex);
	                        itemQuantities.remove(removeIndex);
	                      // itemMap.remove(itemName);
	                     // Update the preview and total
	    	                addRemoveItem();
	                    }
	                } else {
	                    checkboxes4.setSelected(false);
	                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
	                }
			}
		});
		checkboxes4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		checkboxes4.setBounds(98, 193, 21, 21);
		BurgerPanel_4.add(checkboxes4);
		
		Panel BurgerPanel_5 = new Panel();
		BurgerPanel_5.setLayout(null);
		BurgerPanel_5.setBackground(Color.LIGHT_GRAY);
		BurgerPanel_5.setBounds(352, 220, 170, 220);
		BurgerTab.add(BurgerPanel_5);
		
		JLabel Image_5 = new JLabel("New label");
		Image_5.setBounds(0, 0, 170, 105);
		 // to scale image to the size of the bounds
		ImageIcon icon5 = new ImageIcon(getClass().getResource("resources/We Had No Idea Veggie Burgers Could Be This Good.jpeg"));
		Image img5 = icon5.getImage().getScaledInstance(Image_5.getWidth(),Image_5.getHeight(),DO_NOTHING_ON_CLOSE);
		Image_5.setIcon(new ImageIcon(img5));
		BurgerPanel_5.add(Image_5);
		
		JLabel ItemName_5 = new JLabel("<html>Classic<br> Cheese Burger</html>");
		ItemName_5.setHorizontalAlignment(SwingConstants.CENTER);
		ItemName_5.setFont(new Font("Times New Roman", Font.BOLD, 12));
		ItemName_5.setBounds(11, 110, 148, 30);
		BurgerPanel_5.add(ItemName_5);
		
		JLabel ItemPrice_5 = new JLabel("RM10.00");
		ItemPrice_5.setHorizontalAlignment(SwingConstants.CENTER);
		ItemPrice_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ItemPrice_5.setBounds(74, 150, 74, 13);
		BurgerPanel_5.add(ItemPrice_5);
		
		JSpinner spinner5 = new JSpinner();
		spinner5.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		spinner5.setFont(new Font("Times New Roman", Font.BOLD, 12));
		spinner5.setBounds(92, 170, 45, 20);
		BurgerPanel_5.add(spinner5);
		
		JLabel lblQuantit_5 = new JLabel("Quantity: ");
		lblQuantit_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantit_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblQuantit_5.setBounds(27, 175, 64, 13);
		BurgerPanel_5.add(lblQuantit_5);
		
		JLabel lblPrice_5 = new JLabel("Price: ");
		lblPrice_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPrice_5.setBounds(27, 150, 64, 13);
		BurgerPanel_5.add(lblPrice_5);
		
		JLabel lblPurchase_5 = new JLabel("Purchase: ");
		lblPurchase_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblPurchase_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPurchase_5.setBounds(27, 197, 64, 13);
		BurgerPanel_5.add(lblPurchase_5);
		
		JCheckBox checkboxes5 = new JCheckBox("");
		checkboxes5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int qty = Integer.parseInt(spinner5.getValue().toString());
				MealQty[5] = qty;
				String itemID = MealID[5];
	            String itemName = MealName[5];
	            double SumPrice = MealQty[5] * MealPrice[5];

	                if (qty > 0) {
	                    if (checkboxes5.isSelected()) {
	                    	itemIDs.add(itemID);
	                    	itemNames.add(itemName + "\t");
	                    	itemPrices.add(MealPrice[5]);
	                    	itemSumPrices.add(SumPrice);
	                        itemQuantities.add(MealQty[5]);
	                     //  itemMap.put(itemName, SumPrice);
	                     // Update the preview and total
	    	                addRemoveItem();
	                    } else {
	                    	int removeIndex = itemNames.indexOf(itemName + "\t");
	                        itemNames.remove(removeIndex);
	                        itemPrices.remove(removeIndex);
	                        itemSumPrices.remove(removeIndex);
	                        itemQuantities.remove(removeIndex);
	                      // itemMap.remove(itemName);
	                     // Update the preview and total
	    	                addRemoveItem();
	                    }
	                } else {
	                    checkboxes5.setSelected(false);
	                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
	                }
			}
		});
		checkboxes5.setFont(new Font("Times New Roman", Font.BOLD, 12));
		checkboxes5.setBounds(98, 193, 21, 21);
		BurgerPanel_5.add(checkboxes5);
		
		JPanel DrinksTab = new JPanel();
		
		// Create a JScrollPane and add the panel to it
				JScrollPane DrinklScrollPane1 = new JScrollPane(DrinksTab);
				DrinklScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				DrinklScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				DrinklScrollPane1.setBounds(10, 10, 495, 400); // Adjust size if needed
				MenutabbedPane.addTab("Drinks", null, DrinklScrollPane1, null);
			

				// Adjust the size of the panel to be larger than the JScrollPane's viewport
				DrinksTab.setPreferredSize(new Dimension(495, 440)); // Adjust height as needed
				DrinksTab.setLayout(null);
				
				Panel DrinksPanel = new Panel();
				DrinksPanel.setLayout(null);
				DrinksPanel.setBackground(Color.LIGHT_GRAY);
				DrinksPanel.setBounds(0, 0, 170, 220);
				DrinksTab.add(DrinksPanel);
				
				JLabel Image_6 = new JLabel("New label");
				Image_6.setBounds(0, 0, 170, 105);
				// to scale image to the size of the bounds
				ImageIcon icon6 = new ImageIcon(getClass().getResource("resources/fanta grapes.jpg"));
				Image img6 = icon6.getImage().getScaledInstance(Image_6.getWidth(),Image_6.getHeight(),DO_NOTHING_ON_CLOSE);
				Image_6.setIcon(new ImageIcon(img6));
				DrinksPanel.add(Image_6);
				
				JLabel ItemName_6 = new JLabel("<html>Soft Drink<br>Fanta Grape</html>");
				ItemName_6.setHorizontalAlignment(SwingConstants.CENTER);
				ItemName_6.setFont(new Font("Times New Roman", Font.BOLD, 12));
				ItemName_6.setBounds(11, 110, 148, 30);
				DrinksPanel.add(ItemName_6);
				
				JLabel ItemPrice_6 = new JLabel("RM3.50");
				ItemPrice_6.setHorizontalAlignment(SwingConstants.CENTER);
				ItemPrice_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				ItemPrice_6.setBounds(74, 150, 74, 13);
				DrinksPanel.add(ItemPrice_6);
				
				JSpinner spinner6 = new JSpinner();
				spinner6.setModel(new SpinnerNumberModel(0, 0, 99, 1));
				spinner6.setFont(new Font("Times New Roman", Font.BOLD, 12));
				spinner6.setBounds(92, 170, 45, 20);
				DrinksPanel.add(spinner6);
				
				JLabel lblQuantit_6 = new JLabel("Quantity: ");
				lblQuantit_6.setHorizontalAlignment(SwingConstants.LEFT);
				lblQuantit_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblQuantit_6.setBounds(27, 175, 64, 13);
				DrinksPanel.add(lblQuantit_6);
				
				JLabel lblPrice_6 = new JLabel("Price: ");
				lblPrice_6.setHorizontalAlignment(SwingConstants.LEFT);
				lblPrice_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPrice_6.setBounds(27, 150, 64, 13);
				DrinksPanel.add(lblPrice_6);
				
				JLabel lblPurchase_6 = new JLabel("Purchase: ");
				lblPurchase_6.setHorizontalAlignment(SwingConstants.LEFT);
				lblPurchase_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPurchase_6.setBounds(27, 197, 64, 13);
				DrinksPanel.add(lblPurchase_6);
				
				JCheckBox checkboxes6 = new JCheckBox("");
				checkboxes6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int qty = Integer.parseInt(spinner6.getValue().toString());
						MealQty[6] = qty;
						String itemID = MealID[6];
			            String itemName = MealName[6];
			            double SumPrice = MealQty[6] * MealPrice[6];

			                if (qty > 0) {
			                    if (checkboxes6.isSelected()) {
			                    	itemIDs.add(itemID);
			                    	itemNames.add(itemName + "\t");
			                    	itemPrices.add(MealPrice[6]);
			                    	itemSumPrices.add(SumPrice);
			                        itemQuantities.add(MealQty[6]);
			                     //  itemMap.put(itemName, SumPrice);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    } else {
			                    	int removeIndex = itemNames.indexOf(itemName+ "\t");
			                        itemNames.remove(removeIndex);
			                        itemPrices.remove(removeIndex);
			                        itemSumPrices.remove(removeIndex);
			                        itemQuantities.remove(removeIndex);
			                      // itemMap.remove(itemName);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    }
			                } else {
			                    checkboxes6.setSelected(false);
			                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
			                }	
					}
				});
				checkboxes6.setFont(new Font("Times New Roman", Font.BOLD, 12));
				checkboxes6.setBounds(98, 193, 21, 21);
				DrinksPanel.add(checkboxes6);
				
				Panel DrinksPanel_1 = new Panel();
				DrinksPanel_1.setLayout(null);
				DrinksPanel_1.setBackground(Color.LIGHT_GRAY);
				DrinksPanel_1.setBounds(176, 0, 170, 220);
				DrinksTab.add(DrinksPanel_1);
				
				JLabel Image_7 = new JLabel("New label");
				Image_7.setBounds(0, 0, 170, 105);
				// to scale image to the size of the bounds
				ImageIcon icon7 = new ImageIcon(getClass().getResource("resources/Fanta Oren.jpg"));
				Image img7 = icon7.getImage().getScaledInstance(Image_7.getWidth(),Image_7.getHeight(),DO_NOTHING_ON_CLOSE);
				Image_7.setIcon(new ImageIcon(img7));
				DrinksPanel_1.add(Image_7);
				
				JLabel ItemName_7 = new JLabel("<html>Soft Drink<br>Fanta Orange</html>");
				ItemName_7.setHorizontalAlignment(SwingConstants.CENTER);
				ItemName_7.setFont(new Font("Times New Roman", Font.BOLD, 12));
				ItemName_7.setBounds(11, 110, 148, 30);
				DrinksPanel_1.add(ItemName_7);
				
				JLabel ItemPrice_7 = new JLabel("RM3.50");
				ItemPrice_7.setHorizontalAlignment(SwingConstants.CENTER);
				ItemPrice_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				ItemPrice_7.setBounds(74, 150, 74, 13);
				DrinksPanel_1.add(ItemPrice_7);
				
				JSpinner spinner7 = new JSpinner();
				spinner7.setModel(new SpinnerNumberModel(0, 0, 99, 1));
				spinner7.setFont(new Font("Times New Roman", Font.BOLD, 12));
				spinner7.setBounds(92, 170, 45, 20);
				DrinksPanel_1.add(spinner7);
				
				JLabel lblQuantit_7 = new JLabel("Quantity: ");
				lblQuantit_7.setHorizontalAlignment(SwingConstants.LEFT);
				lblQuantit_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblQuantit_7.setBounds(27, 175, 64, 13);
				DrinksPanel_1.add(lblQuantit_7);
				
				JLabel lblPrice_7 = new JLabel("Price: ");
				lblPrice_7.setHorizontalAlignment(SwingConstants.LEFT);
				lblPrice_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPrice_7.setBounds(27, 150, 64, 13);
				DrinksPanel_1.add(lblPrice_7);
				
				JLabel lblPurchase_7 = new JLabel("Purchase: ");
				lblPurchase_7.setHorizontalAlignment(SwingConstants.LEFT);
				lblPurchase_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPurchase_7.setBounds(27, 197, 64, 13);
				DrinksPanel_1.add(lblPurchase_7);
				
				JCheckBox checkboxes7 = new JCheckBox("");
				checkboxes7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int qty = Integer.parseInt(spinner7.getValue().toString());
						MealQty[7] = qty;
						String itemID = MealID[7];
			            String itemName = MealName[7];
			            double SumPrice = MealQty[7] * MealPrice[7];

			                if (qty > 0) {
			                    if (checkboxes7.isSelected()) {
			                    	itemIDs.add(itemID);
			                    	itemNames.add(itemName + "\t");
			                    	itemPrices.add(MealPrice[7]);
			                    	itemSumPrices.add(SumPrice);
			                        itemQuantities.add(MealQty[7]);
			                     //  itemMap.put(itemName, SumPrice);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    } else {
			                    	int removeIndex = itemNames.indexOf(itemName+ "\t");
			                        itemNames.remove(removeIndex);
			                        itemPrices.remove(removeIndex);
			                        itemSumPrices.remove(removeIndex);
			                        itemQuantities.remove(removeIndex);
			                      // itemMap.remove(itemName);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    }
			                } else {
			                    checkboxes7.setSelected(false);
			                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
			                }	
					}
				});
				checkboxes7.setFont(new Font("Times New Roman", Font.BOLD, 12));
				checkboxes7.setBounds(98, 193, 21, 21);
				DrinksPanel_1.add(checkboxes7);
				
				Panel DrinksPanel_2 = new Panel();
				DrinksPanel_2.setLayout(null);
				DrinksPanel_2.setBackground(Color.LIGHT_GRAY);
				DrinksPanel_2.setBounds(352, 0, 170, 220);
				DrinksTab.add(DrinksPanel_2);
				
				JLabel Image_8 = new JLabel("New label");
				Image_8.setBounds(0, 0, 170, 105);
				// to scale image to the size of the bounds
				ImageIcon icon8 = new ImageIcon(getClass().getResource("resources/Fanta Strawbbery.jpeg"));
				Image img8 = icon8.getImage().getScaledInstance(Image_8.getWidth(),Image_8.getHeight(),DO_NOTHING_ON_CLOSE);
				Image_8.setIcon(new ImageIcon(img8));
				DrinksPanel_2.add(Image_8);
				
				JLabel ItemName_8 = new JLabel("<html>Soft Drink<br>Fanta Strawberry</html>");
				ItemName_8.setHorizontalAlignment(SwingConstants.CENTER);
				ItemName_8.setFont(new Font("Times New Roman", Font.BOLD, 12));
				ItemName_8.setBounds(11, 110, 148, 30);
				DrinksPanel_2.add(ItemName_8);
				
				JLabel ItemPrice_8 = new JLabel("RM3.50");
				ItemPrice_8.setHorizontalAlignment(SwingConstants.CENTER);
				ItemPrice_8.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				ItemPrice_8.setBounds(74, 150, 74, 13);
				DrinksPanel_2.add(ItemPrice_8);
				
				JSpinner spinner8 = new JSpinner();
				spinner8.setModel(new SpinnerNumberModel(0, 0, 99, 1));
				spinner8.setFont(new Font("Times New Roman", Font.BOLD, 12));
				spinner8.setBounds(92, 170, 45, 20);
				DrinksPanel_2.add(spinner8);
				
				JLabel lblQuantit_8 = new JLabel("Quantity: ");
				lblQuantit_8.setHorizontalAlignment(SwingConstants.LEFT);
				lblQuantit_8.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblQuantit_8.setBounds(27, 175, 64, 13);
				DrinksPanel_2.add(lblQuantit_8);
				
				JLabel lblPrice_8 = new JLabel("Price: ");
				lblPrice_8.setHorizontalAlignment(SwingConstants.LEFT);
				lblPrice_8.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPrice_8.setBounds(27, 150, 64, 13);
				DrinksPanel_2.add(lblPrice_8);
				
				JLabel lblPurchase_8 = new JLabel("Purchase: ");
				lblPurchase_8.setHorizontalAlignment(SwingConstants.LEFT);
				lblPurchase_8.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPurchase_8.setBounds(27, 197, 64, 13);
				DrinksPanel_2.add(lblPurchase_8);
				
				JCheckBox checkboxes8 = new JCheckBox("");
				checkboxes8.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int qty = Integer.parseInt(spinner8.getValue().toString());
						MealQty[8] = qty;
						String itemID = MealID[8];
			            String itemName = MealName[8];
			            double SumPrice = MealQty[8] * MealPrice[8];

			                if (qty > 0) {
			                    if (checkboxes8.isSelected()) {
			                    	itemIDs.add(itemID);
			                    	itemNames.add(itemName + "\t");
			                    	itemPrices.add(MealPrice[8]);
			                    	itemSumPrices.add(SumPrice);
			                        itemQuantities.add(MealQty[8]);
			                     //  itemMap.put(itemName, SumPrice);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    } else {
			                    	int removeIndex = itemNames.indexOf(itemName+ "\t");
			                        itemNames.remove(removeIndex);
			                        itemPrices.remove(removeIndex);
			                        itemSumPrices.remove(removeIndex);
			                        itemQuantities.remove(removeIndex);
			                      // itemMap.remove(itemName);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    }
			                } else {
			                    checkboxes8.setSelected(false);
			                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
			                }
					}
				});
				checkboxes8.setFont(new Font("Times New Roman", Font.BOLD, 12));
				checkboxes8.setBounds(98, 193, 21, 21);
				DrinksPanel_2.add(checkboxes8);
				
				
				Panel DrinksPanel_3 = new Panel();
				DrinksPanel_3.setLayout(null);
				DrinksPanel_3.setBackground(Color.LIGHT_GRAY);
				DrinksPanel_3.setBounds(0, 220, 170, 220);
				DrinksTab.add(DrinksPanel_3);
				
				JLabel Image_9 = new JLabel("New label");
				Image_9.setBounds(0, 0, 170, 105);
				// to scale image to the size of the bounds
				ImageIcon icon9 = new ImageIcon(getClass().getResource("resources/Sprite.jpeg"));
				Image img9 = icon9.getImage().getScaledInstance(Image_9.getWidth(), Image_9.getHeight(), DO_NOTHING_ON_CLOSE);
				Image_9.setIcon(new ImageIcon(img9));
				DrinksPanel_3.add(Image_9);
				
				JLabel ItemName_9 = new JLabel("<html>Soft Drink<br>Sprite</html>");
				ItemName_9.setHorizontalAlignment(SwingConstants.CENTER);
				ItemName_9.setFont(new Font("Times New Roman", Font.BOLD, 12));
				ItemName_9.setBounds(11, 110, 148, 30);
				DrinksPanel_3.add(ItemName_9);
				
				JLabel ItemPrice_9 = new JLabel("RM3.50");
				ItemPrice_9.setHorizontalAlignment(SwingConstants.CENTER);
				ItemPrice_9.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				ItemPrice_9.setBounds(74, 150, 74, 13);
				DrinksPanel_3.add(ItemPrice_9);
				
				JSpinner spinner9 = new JSpinner();
				spinner9.setModel(new SpinnerNumberModel(0, 0, 99, 1));
				spinner9.setFont(new Font("Times New Roman", Font.BOLD, 12));
				spinner9.setBounds(92, 170, 45, 20);
				DrinksPanel_3.add(spinner9);
				
				JLabel lblQuantit_9 = new JLabel("Quantity: ");
				lblQuantit_9.setHorizontalAlignment(SwingConstants.LEFT);
				lblQuantit_9.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblQuantit_9.setBounds(27, 175, 64, 13);
				DrinksPanel_3.add(lblQuantit_9);
				
				JLabel lblPrice_9 = new JLabel("Price: ");
				lblPrice_9.setHorizontalAlignment(SwingConstants.LEFT);
				lblPrice_9.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPrice_9.setBounds(27, 150, 64, 13);
				DrinksPanel_3.add(lblPrice_9);
				
				JLabel lblPurchase_9 = new JLabel("Purchase: ");
				lblPurchase_9.setHorizontalAlignment(SwingConstants.LEFT);
				lblPurchase_9.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPurchase_9.setBounds(27, 197, 64, 13);
				DrinksPanel_3.add(lblPurchase_9);
				
				JCheckBox checkboxes9 = new JCheckBox("");
				checkboxes9.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int qty = Integer.parseInt(spinner9.getValue().toString());
						MealQty[9] = qty;
						String itemID = MealID[9];
			            String itemName = MealName[9];
			            double SumPrice = MealQty[9] * MealPrice[9];

			                if (qty > 0) {
			                    if (checkboxes9.isSelected()) {
			                    	itemIDs.add(itemID);
			                    	itemNames.add(itemName + "\t");
			                    	itemPrices.add(MealPrice[9]);
			                    	itemSumPrices.add(SumPrice);
			                        itemQuantities.add(MealQty[9]);
			                     //  itemMap.put(itemName, SumPrice);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    } else {
			                    	int removeIndex = itemNames.indexOf(itemName+ "\t");
			                        itemNames.remove(removeIndex);
			                        itemPrices.remove(removeIndex);
			                        itemSumPrices.remove(removeIndex);
			                        itemQuantities.remove(removeIndex);
			                      // itemMap.remove(itemName);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    }
			                } else {
			                    checkboxes9.setSelected(false);
			                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
			                }
					}
				});
				checkboxes9.setFont(new Font("Times New Roman", Font.BOLD, 12));
				checkboxes9.setBounds(98, 193, 21, 21);
				DrinksPanel_3.add(checkboxes9);
				
				
				Panel DrinksPanel_4 = new Panel();
				DrinksPanel_4.setLayout(null);
				DrinksPanel_4.setBackground(Color.LIGHT_GRAY);
				DrinksPanel_4.setBounds(176, 220, 170, 220);
				DrinksTab.add(DrinksPanel_4);
				
				JLabel Image_10 = new JLabel("New label");
				Image_10.setBounds(0, 0, 170, 105);
				// to scale image to the size of the bounds
				ImageIcon icon10 = new ImageIcon(getClass().getResource("resources/Ice lemon tea.jpeg"));
				Image img10 = icon10.getImage().getScaledInstance(Image_10.getWidth(), Image_10.getHeight(), DO_NOTHING_ON_CLOSE);
				Image_10.setIcon(new ImageIcon(img10));
				DrinksPanel_4.add(Image_10);
				
				JLabel ItemName_10 = new JLabel("<html>Soft Drink<br>Ice Lemon Tea</html>");
				ItemName_10.setHorizontalAlignment(SwingConstants.CENTER);
				ItemName_10.setFont(new Font("Times New Roman", Font.BOLD, 12));
				ItemName_10.setBounds(11, 110, 148, 30);
				DrinksPanel_4.add(ItemName_10);
				
				JLabel ItemPrice_10 = new JLabel("RM3.50");
				ItemPrice_10.setHorizontalAlignment(SwingConstants.CENTER);
				ItemPrice_10.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				ItemPrice_10.setBounds(74, 150, 74, 13);
				DrinksPanel_4.add(ItemPrice_10);
				
				JSpinner spinner10 = new JSpinner();
				spinner10.setModel(new SpinnerNumberModel(0, 0, 99, 1));
				spinner10.setFont(new Font("Times New Roman", Font.BOLD, 12));
				spinner10.setBounds(92, 170, 45, 20);
				DrinksPanel_4.add(spinner10);
				
				JLabel lblQuantit_10 = new JLabel("Quantity: ");
				lblQuantit_10.setHorizontalAlignment(SwingConstants.LEFT);
				lblQuantit_10.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblQuantit_10.setBounds(27, 175, 64, 13);
				DrinksPanel_4.add(lblQuantit_10);
				
				JLabel lblPrice_10 = new JLabel("Price: ");
				lblPrice_10.setHorizontalAlignment(SwingConstants.LEFT);
				lblPrice_10.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPrice_10.setBounds(27, 150, 64, 13);
				DrinksPanel_4.add(lblPrice_10);
				
				JLabel lblPurchase_10 = new JLabel("Purchase: ");
				lblPurchase_10.setHorizontalAlignment(SwingConstants.LEFT);
				lblPurchase_10.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPurchase_10.setBounds(27, 197, 64, 13);
				DrinksPanel_4.add(lblPurchase_10);
				
				JCheckBox checkboxes10 = new JCheckBox("");
				checkboxes10.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int qty = Integer.parseInt(spinner10.getValue().toString());
						MealQty[10] = qty;
						String itemID = MealID[10];
			            String itemName = MealName[10];
			            double SumPrice = MealQty[10] * MealPrice[10];

			                if (qty > 0) {
			                    if (checkboxes10.isSelected()) {
			                    	itemIDs.add(itemID);
			                    	itemNames.add(itemName + "\t");
			                    	itemPrices.add(MealPrice[10]);
			                    	itemSumPrices.add(SumPrice);
			                        itemQuantities.add(MealQty[10]);
			                     //  itemMap.put(itemName, SumPrice);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    } else {
			                    	int removeIndex = itemNames.indexOf(itemName+ "\t");
			                        itemNames.remove(removeIndex);
			                        itemPrices.remove(removeIndex);
			                        itemSumPrices.remove(removeIndex);
			                        itemQuantities.remove(removeIndex);
			                      // itemMap.remove(itemName);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    }
			                } else {
			                    checkboxes10.setSelected(false);
			                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
			                }
					}
				});
				checkboxes10.setFont(new Font("Times New Roman", Font.BOLD, 12));
				checkboxes10.setBounds(98, 193, 21, 21);
				DrinksPanel_4.add(checkboxes10);
				
				Panel DrinksPanel_5 = new Panel();
				DrinksPanel_5.setLayout(null);
				DrinksPanel_5.setBackground(Color.LIGHT_GRAY);
				DrinksPanel_5.setBounds(352, 220, 170, 220);
				DrinksTab.add(DrinksPanel_5);
				
				JLabel Image_11 = new JLabel("New label");
				Image_11.setBounds(0, 0, 170, 105);
				// to scale image to the size of the bounds
				ImageIcon icon11 = new ImageIcon(getClass().getResource("resources/Spritzer.jpeg"));
				Image img11 = icon11.getImage().getScaledInstance(Image_11.getWidth(), Image_11.getHeight(), DO_NOTHING_ON_CLOSE);
				Image_11.setIcon(new ImageIcon(img11));
				DrinksPanel_5.add(Image_11);
				
				JLabel ItemName_11 = new JLabel("<html>Mineral Water<br>Spritzer</html>");
				ItemName_11.setHorizontalAlignment(SwingConstants.CENTER);
				ItemName_11.setFont(new Font("Times New Roman", Font.BOLD, 12));
				ItemName_11.setBounds(11, 110, 148, 30);
				DrinksPanel_5.add(ItemName_11);
				
				JLabel ItemPrice_11 = new JLabel("RM1.50");
				ItemPrice_11.setHorizontalAlignment(SwingConstants.CENTER);
				ItemPrice_11.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				ItemPrice_11.setBounds(74, 150, 74, 13);
				DrinksPanel_5.add(ItemPrice_11);
				
				JSpinner spinner11 = new JSpinner();
				spinner11.setModel(new SpinnerNumberModel(0, 0, 99, 1));
				spinner11.setFont(new Font("Times New Roman", Font.BOLD, 12));
				spinner11.setBounds(92, 170, 45, 20);
				DrinksPanel_5.add(spinner11);
				
				JLabel lblQuantit_11 = new JLabel("Quantity: ");
				lblQuantit_11.setHorizontalAlignment(SwingConstants.LEFT);
				lblQuantit_11.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblQuantit_11.setBounds(27, 175, 64, 13);
				DrinksPanel_5.add(lblQuantit_11);
				
				JLabel lblPrice_11 = new JLabel("Price: ");
				lblPrice_11.setHorizontalAlignment(SwingConstants.LEFT);
				lblPrice_11.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPrice_11.setBounds(27, 150, 64, 13);
				DrinksPanel_5.add(lblPrice_11);
				
				JLabel lblPurchase_11 = new JLabel("Purchase: ");
				lblPurchase_11.setHorizontalAlignment(SwingConstants.LEFT);
				lblPurchase_11.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPurchase_11.setBounds(27, 197, 64, 13);
				DrinksPanel_5.add(lblPurchase_11);
				
				JCheckBox checkboxes11 = new JCheckBox("");
				checkboxes11.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int qty = Integer.parseInt(spinner11.getValue().toString());
						MealQty[11] = qty;
						String itemID = MealID[11];
			            String itemName = MealName[11];
			            double SumPrice = MealQty[11] * MealPrice[11];

			                if (qty > 0) {
			                    if (checkboxes11.isSelected()) {
			                    	itemIDs.add(itemID);
			                    	itemNames.add(itemName + "\t");
			                    	itemPrices.add(MealPrice[11]);
			                    	itemSumPrices.add(SumPrice);
			                        itemQuantities.add(MealQty[11]);
			                     //  itemMap.put(itemName, SumPrice);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    } else {
			                    	int removeIndex = itemNames.indexOf(itemName+ "\t");
			                        itemNames.remove(removeIndex);
			                        itemPrices.remove(removeIndex);
			                        itemSumPrices.remove(removeIndex);
			                        itemQuantities.remove(removeIndex);
			                      // itemMap.remove(itemName);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    }
			                } else {
			                    checkboxes11.setSelected(false);
			                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
			                }
					}
				});
				checkboxes11.setFont(new Font("Times New Roman", Font.BOLD, 12));
				checkboxes11.setBounds(98, 193, 21, 21);
				DrinksPanel_5.add(checkboxes11);
		
		JPanel SidesTab = new JPanel();
		SidesTab.setLayout(null);
		
		// Create a JScrollPane and add the panel to it
				JScrollPane SideScrollPane2 = new JScrollPane(SidesTab);
				SideScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				SideScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				SideScrollPane2.setBounds(10, 10, 495, 400); // Adjust size if needed
				MenutabbedPane.addTab("Sides", null, SideScrollPane2, null);
			

				// Adjust the size of the panel to be larger than the JScrollPane's viewport
				SidesTab.setPreferredSize(new Dimension(495, 420)); // Adjust height as needed
				SidesTab.setLayout(null);
				
				Panel SidesPanel = new Panel();
				SidesPanel.setLayout(null);
				SidesPanel.setBackground(Color.LIGHT_GRAY);
				SidesPanel.setBounds(0, 0, 170, 220);
				SidesTab.add(SidesPanel);
				
				JLabel Image_12 = new JLabel("New label");
				Image_12.setBounds(0, 0, 170, 105);
				// to scale image to the size of the bounds
				ImageIcon icon12 = new ImageIcon(getClass().getResource("resources/Cheese Fries The Ultimate Comfort Food.jpeg"));
				Image img12 = icon12.getImage().getScaledInstance(Image_12.getWidth(), Image_12.getHeight(), DO_NOTHING_ON_CLOSE);
				Image_12.setIcon(new ImageIcon(img12));
				SidesPanel.add(Image_12);
				
				JLabel ItemName_12 = new JLabel("Cheesy Fries");
				ItemName_12.setHorizontalAlignment(SwingConstants.CENTER);
				ItemName_12.setFont(new Font("Times New Roman", Font.BOLD, 12));
				ItemName_12.setBounds(11, 120, 148, 13);
				SidesPanel.add(ItemName_12);
				
				JLabel ItemPrice_12 = new JLabel("RM5.00");
				ItemPrice_12.setHorizontalAlignment(SwingConstants.CENTER);
				ItemPrice_12.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				ItemPrice_12.setBounds(74, 150, 74, 13);
				SidesPanel.add(ItemPrice_12);
				
				JSpinner spinner12 = new JSpinner();
				spinner12.setModel(new SpinnerNumberModel(0, 0, 99, 1));
				spinner12.setFont(new Font("Times New Roman", Font.BOLD, 12));
				spinner12.setBounds(92, 170, 45, 20);
				SidesPanel.add(spinner12);
				
				JLabel lblQuantit_12 = new JLabel("Quantity: ");
				lblQuantit_12.setHorizontalAlignment(SwingConstants.LEFT);
				lblQuantit_12.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblQuantit_12.setBounds(27, 175, 64, 13);
				SidesPanel.add(lblQuantit_12);
				
				JLabel lblPrice_12 = new JLabel("Price: ");
				lblPrice_12.setHorizontalAlignment(SwingConstants.LEFT);
				lblPrice_12.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPrice_12.setBounds(27, 150, 64, 13);
				SidesPanel.add(lblPrice_12);
				
				JLabel lblPurchase_12 = new JLabel("Purchase: ");
				lblPurchase_12.setHorizontalAlignment(SwingConstants.LEFT);
				lblPurchase_12.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPurchase_12.setBounds(27, 197, 64, 13);
				SidesPanel.add(lblPurchase_12);
				
				JCheckBox checkboxes12 = new JCheckBox("");
				checkboxes12.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int qty = Integer.parseInt(spinner12.getValue().toString());
						MealQty[12] = qty;
						String itemID = MealID[12];
			            String itemName = MealName[12];
			            double SumPrice = MealQty[12] * MealPrice[12];

			                if (qty > 0) {
			                    if (checkboxes12.isSelected()) {
			                    	itemIDs.add(itemID);
			                    	itemNames.add(itemName + "\t");
			                    	itemPrices.add(MealPrice[12]);
			                    	itemSumPrices.add(SumPrice);
			                        itemQuantities.add(MealQty[12]);
			                     //  itemMap.put(itemName, SumPrice);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    } else {
			                    	int removeIndex = itemNames.indexOf(itemName+ "\t");
			                        itemNames.remove(removeIndex);
			                        itemPrices.remove(removeIndex);
			                        itemSumPrices.remove(removeIndex);
			                        itemQuantities.remove(removeIndex);
			                      // itemMap.remove(itemName);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    }
			                } else {
			                    checkboxes12.setSelected(false);
			                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
			                }	
					}
				});
				checkboxes12.setFont(new Font("Times New Roman", Font.BOLD, 12));
				checkboxes12.setBounds(98, 193, 21, 21);
				SidesPanel.add(checkboxes12);
				
				Panel SidesPanel_1 = new Panel();
				SidesPanel_1.setLayout(null);
				SidesPanel_1.setBackground(Color.LIGHT_GRAY);
				SidesPanel_1.setBounds(176, 0, 170, 220);
				SidesTab.add(SidesPanel_1);
				
				JLabel Image_13 = new JLabel("New label");
				Image_13.setBounds(0, 0, 170, 105);
				// to scale image to the size of the bounds
				ImageIcon icon13 = new ImageIcon(getClass().getResource("resources/Cheesy Potato Wedges.jpeg"));
				Image img13 = icon13.getImage().getScaledInstance(Image_13.getWidth(), Image_13.getHeight(), DO_NOTHING_ON_CLOSE);
				Image_13.setIcon(new ImageIcon(img13));
				SidesPanel_1.add(Image_13);

				JLabel ItemName_13 = new JLabel("Cheesy Wedges");
				ItemName_13.setHorizontalAlignment(SwingConstants.CENTER);
				ItemName_13.setFont(new Font("Times New Roman", Font.BOLD, 12));
				ItemName_13.setBounds(11, 120, 148, 13);
				SidesPanel_1.add(ItemName_13);
				
				JLabel ItemPrice_13 = new JLabel("RM5.00");
				ItemPrice_13.setHorizontalAlignment(SwingConstants.CENTER);
				ItemPrice_13.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				ItemPrice_13.setBounds(74, 150, 74, 13);
				SidesPanel_1.add(ItemPrice_13);
				
				JSpinner spinner13 = new JSpinner();
				spinner13.setModel(new SpinnerNumberModel(0, 0, 99, 1));
				spinner13.setFont(new Font("Times New Roman", Font.BOLD, 12));
				spinner13.setBounds(92, 170, 45, 20);
				SidesPanel_1.add(spinner13);
				
				JLabel lblQuantit_13 = new JLabel("Quantity: ");
				lblQuantit_13.setHorizontalAlignment(SwingConstants.LEFT);
				lblQuantit_13.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblQuantit_13.setBounds(27, 175, 64, 13);
				SidesPanel_1.add(lblQuantit_13);
				
				JLabel lblPrice_13 = new JLabel("Price: ");
				lblPrice_13.setHorizontalAlignment(SwingConstants.LEFT);
				lblPrice_13.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPrice_13.setBounds(27, 150, 64, 13);
				SidesPanel_1.add(lblPrice_13);
				
				JLabel lblPurchase_13 = new JLabel("Purchase: ");
				lblPurchase_13.setHorizontalAlignment(SwingConstants.LEFT);
				lblPurchase_13.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPurchase_13.setBounds(27, 197, 64, 13);
				SidesPanel_1.add(lblPurchase_13);
				
				JCheckBox checkboxes13 = new JCheckBox("");
				checkboxes13.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int qty = Integer.parseInt(spinner13.getValue().toString());
						MealQty[13] = qty;
						String itemID = MealID[13];
			            String itemName = MealName[13];
			            double SumPrice = MealQty[13] * MealPrice[13];

			                if (qty > 0) {
			                    if (checkboxes13.isSelected()) {
			                    	itemIDs.add(itemID);
			                    	itemNames.add(itemName + "\t");
			                    	itemPrices.add(MealPrice[13]);
			                    	itemSumPrices.add(SumPrice);
			                        itemQuantities.add(MealQty[13]);
			                     //  itemMap.put(itemName, SumPrice);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    } else {
			                    	int removeIndex = itemNames.indexOf(itemName+ "\t");
			                        itemNames.remove(removeIndex);
			                        itemPrices.remove(removeIndex);
			                        itemSumPrices.remove(removeIndex);
			                        itemQuantities.remove(removeIndex);
			                      // itemMap.remove(itemName);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    }
			                } else {
			                    checkboxes13.setSelected(false);
			                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
			                }		
					}
				});
				checkboxes13.setFont(new Font("Times New Roman", Font.BOLD, 12));
				checkboxes13.setBounds(98, 193, 21, 21);
				SidesPanel_1.add(checkboxes13);
				
				Panel SidesPanel_2 = new Panel();
				SidesPanel_2.setLayout(null);
				SidesPanel_2.setBackground(Color.LIGHT_GRAY);
				SidesPanel_2.setBounds(352, 0, 170, 220);
				SidesTab.add(SidesPanel_2);
				
				JLabel Image_14 = new JLabel("New label");
				Image_14.setBounds(0, 0, 170, 105);
				// to scale image to the size of the bounds
				ImageIcon icon14 = new ImageIcon(getClass().getResource("resources/Curly Fries In The Air Fryer - Fork To Spoon.jpeg"));
				Image img14 = icon14.getImage().getScaledInstance(Image_14.getWidth(), Image_14.getHeight(), DO_NOTHING_ON_CLOSE);
				Image_14.setIcon(new ImageIcon(img14));
				SidesPanel_2.add(Image_14);

				JLabel ItemName_14 = new JLabel("Curly Fries");
				ItemName_14.setHorizontalAlignment(SwingConstants.CENTER);
				ItemName_14.setFont(new Font("Times New Roman", Font.BOLD, 12));
				ItemName_14.setBounds(11, 120, 148, 13);
				SidesPanel_2.add(ItemName_14);
				
				JLabel ItemPrice_14 = new JLabel("RM5.00");
				ItemPrice_14.setHorizontalAlignment(SwingConstants.CENTER);
				ItemPrice_14.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				ItemPrice_14.setBounds(74, 150, 74, 13);
				SidesPanel_2.add(ItemPrice_14);
				
				JSpinner spinner14 = new JSpinner();
				spinner14.setModel(new SpinnerNumberModel(0, 0, 99, 1));
				spinner14.setFont(new Font("Times New Roman", Font.BOLD, 12));
				spinner14.setBounds(92, 170, 45, 20);
				SidesPanel_2.add(spinner14);
				
				JLabel lblQuantit_14 = new JLabel("Quantity: ");
				lblQuantit_14.setHorizontalAlignment(SwingConstants.LEFT);
				lblQuantit_14.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblQuantit_14.setBounds(27, 175, 64, 13);
				SidesPanel_2.add(lblQuantit_14);
				
				JLabel lblPrice_14 = new JLabel("Price: ");
				lblPrice_14.setHorizontalAlignment(SwingConstants.LEFT);
				lblPrice_14.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPrice_14.setBounds(27, 150, 64, 13);
				SidesPanel_2.add(lblPrice_14);
				
				JLabel lblPurchase_14 = new JLabel("Purchase: ");
				lblPurchase_14.setHorizontalAlignment(SwingConstants.LEFT);
				lblPurchase_14.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPurchase_14.setBounds(27, 197, 64, 13);
				SidesPanel_2.add(lblPurchase_14);
				
				JCheckBox checkboxes14 = new JCheckBox("");
				checkboxes14.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int qty = Integer.parseInt(spinner14.getValue().toString());
						MealQty[14] = qty;
						String itemID = MealID[14];
			            String itemName = MealName[14];
			            double SumPrice = MealQty[14] * MealPrice[14];

			                if (qty > 0) {
			                    if (checkboxes14.isSelected()) {
			                    	itemIDs.add(itemID);
			                    	itemNames.add(itemName + "\t\t");
			                    	itemPrices.add(MealPrice[14]);
			                    	itemSumPrices.add(SumPrice);
			                        itemQuantities.add(MealQty[14]);
			                     //  itemMap.put(itemName, SumPrice);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    } else {
			                    	int removeIndex = itemNames.indexOf(itemName+ "\t\t");
			                        itemNames.remove(removeIndex);
			                        itemPrices.remove(removeIndex);
			                        itemSumPrices.remove(removeIndex);
			                        itemQuantities.remove(removeIndex);
			                      // itemMap.remove(itemName);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    }
			                } else {
			                    checkboxes14.setSelected(false);
			                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
			                }		
					}
				});
				checkboxes14.setFont(new Font("Times New Roman", Font.BOLD, 12));
				checkboxes14.setBounds(98, 193, 21, 21);
				SidesPanel_2.add(checkboxes14);
				
				Panel SidesPanel_3 = new Panel();
				SidesPanel_3.setLayout(null);
				SidesPanel_3.setBackground(Color.LIGHT_GRAY);
				SidesPanel_3.setBounds(0, 220, 170, 220);
				SidesTab.add(SidesPanel_3);
				
				JLabel Image_15 = new JLabel("New label");
				Image_15.setBounds(0, 0, 170, 105);
				// to scale image to the size of the bounds
				ImageIcon icon15 = new ImageIcon(getClass().getResource("resources/jack potato.jpeg"));
				Image img15 = icon15.getImage().getScaledInstance(Image_15.getWidth(), Image_15.getHeight(), DO_NOTHING_ON_CLOSE);
				Image_15.setIcon(new ImageIcon(img15));
				SidesPanel_3.add(Image_15);
				
				JLabel ItemPrice_15 = new JLabel("RM5.00");
				ItemPrice_15.setHorizontalAlignment(SwingConstants.CENTER);
				ItemPrice_15.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				ItemPrice_15.setBounds(74, 150, 74, 13);
				SidesPanel_3.add(ItemPrice_15);
				
				JSpinner spinner15 = new JSpinner();
				spinner15.setModel(new SpinnerNumberModel(0, 0, 99, 1));
				spinner15.setFont(new Font("Times New Roman", Font.BOLD, 12));
				spinner15.setBounds(92, 170, 45, 20);
				SidesPanel_3.add(spinner15);
				
				JLabel lblQuantit_15 = new JLabel("Quantity: ");
				lblQuantit_15.setHorizontalAlignment(SwingConstants.LEFT);
				lblQuantit_15.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblQuantit_15.setBounds(27, 175, 64, 13);
				SidesPanel_3.add(lblQuantit_15);

				JLabel ItemName_15 = new JLabel("Jacket Potato");
				ItemName_15.setHorizontalAlignment(SwingConstants.CENTER);
				ItemName_15.setFont(new Font("Times New Roman", Font.BOLD, 12));
				ItemName_15.setBounds(11, 120, 148, 13);
				SidesPanel_3.add(ItemName_15);
				
				JLabel lblPrice_15 = new JLabel("Price: ");
				lblPrice_15.setHorizontalAlignment(SwingConstants.LEFT);
				lblPrice_15.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPrice_15.setBounds(27, 150, 64, 13);
				SidesPanel_3.add(lblPrice_15);
				
				JLabel lblPurchase_15 = new JLabel("Purchase: ");
				lblPurchase_15.setHorizontalAlignment(SwingConstants.LEFT);
				lblPurchase_15.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPurchase_15.setBounds(27, 197, 64, 13);
				SidesPanel_3.add(lblPurchase_15);
				
				JCheckBox checkboxes15 = new JCheckBox("");
				checkboxes15.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int qty = Integer.parseInt(spinner15.getValue().toString());
						MealQty[15] = qty;
						String itemID = MealID[15];
			            String itemName = MealName[15];
			            double SumPrice = MealQty[15] * MealPrice[15];

			                if (qty > 0) {
			                    if (checkboxes15.isSelected()) {
			                    	itemIDs.add(itemID);
			                    	itemNames.add(itemName + "\t");
			                    	itemPrices.add(MealPrice[15]);
			                    	itemSumPrices.add(SumPrice);
			                        itemQuantities.add(MealQty[15]);
			                     //  itemMap.put(itemName, SumPrice);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    } else {
			                    	int removeIndex = itemNames.indexOf(itemName+ "\t");
			                        itemNames.remove(removeIndex);
			                        itemPrices.remove(removeIndex);
			                        itemSumPrices.remove(removeIndex);
			                        itemQuantities.remove(removeIndex);
			                      // itemMap.remove(itemName);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    }
			                } else {
			                    checkboxes15.setSelected(false);
			                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
			                }	
					}
				});
				checkboxes15.setFont(new Font("Times New Roman", Font.BOLD, 12));
				checkboxes15.setBounds(98, 193, 21, 21);
				SidesPanel_3.add(checkboxes15);
				
				Panel SidesPanel_4 = new Panel();
				SidesPanel_4.setLayout(null);
				SidesPanel_4.setBackground(Color.LIGHT_GRAY);
				SidesPanel_4.setBounds(176, 220, 170, 220);
				SidesTab.add(SidesPanel_4);
				
				JLabel Image_16 = new JLabel("New label");
				Image_16.setBounds(0, 0, 170, 105);
				// to scale image to the size of the bounds
				ImageIcon icon16 = new ImageIcon(getClass().getResource("resources/Free Photo _ Salad arrangement in dark bowl.jpeg"));
				Image img16 = icon16.getImage().getScaledInstance(Image_16.getWidth(), Image_16.getHeight(), DO_NOTHING_ON_CLOSE);
				Image_16.setIcon(new ImageIcon(img16));
				SidesPanel_4.add(Image_16);

				JLabel ItemName_16 = new JLabel("Ceaser Salad");
				ItemName_16.setHorizontalAlignment(SwingConstants.CENTER);
				ItemName_16.setFont(new Font("Times New Roman", Font.BOLD, 12));
				ItemName_16.setBounds(11, 120, 148, 13);
				SidesPanel_4.add(ItemName_16);
				
				JLabel ItemPrice_16 = new JLabel("RM4.00");
				ItemPrice_16.setHorizontalAlignment(SwingConstants.CENTER);
				ItemPrice_16.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				ItemPrice_16.setBounds(74, 150, 74, 13);
				SidesPanel_4.add(ItemPrice_16);
				
				JSpinner spinner16 = new JSpinner();
				spinner16.setModel(new SpinnerNumberModel(0, 0, 99, 1));
				spinner16.setFont(new Font("Times New Roman", Font.BOLD, 12));
				spinner16.setBounds(92, 170, 45, 20);
				SidesPanel_4.add(spinner16);
				
				JLabel lblQuantit_16 = new JLabel("Quantity: ");
				lblQuantit_16.setHorizontalAlignment(SwingConstants.LEFT);
				lblQuantit_16.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblQuantit_16.setBounds(27, 175, 64, 13);
				SidesPanel_4.add(lblQuantit_16);
				
				JLabel lblPrice_16 = new JLabel("Price: ");
				lblPrice_16.setHorizontalAlignment(SwingConstants.LEFT);
				lblPrice_16.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPrice_16.setBounds(27, 150, 64, 13);
				SidesPanel_4.add(lblPrice_16);
				
				JLabel lblPurchase_16 = new JLabel("Purchase: ");
				lblPurchase_16.setHorizontalAlignment(SwingConstants.LEFT);
				lblPurchase_16.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPurchase_16.setBounds(27, 197, 64, 13);
				SidesPanel_4.add(lblPurchase_16);
				
				JCheckBox checkboxes16 = new JCheckBox("");
				checkboxes16.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int qty = Integer.parseInt(spinner16.getValue().toString());
						MealQty[16] = qty;
						String itemID = MealID[16];
			            String itemName = MealName[16];
			            double SumPrice = MealQty[16] * MealPrice[16];

			                if (qty > 0) {
			                    if (checkboxes16.isSelected()) {
			                    	itemIDs.add(itemID);
			                    	itemNames.add(itemName + "\t");
			                    	itemPrices.add(MealPrice[16]);
			                    	itemSumPrices.add(SumPrice);
			                        itemQuantities.add(MealQty[16]);
			                     //  itemMap.put(itemName, SumPrice);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    } else {
			                    	int removeIndex = itemNames.indexOf(itemName+ "\t");
			                        itemNames.remove(removeIndex);
			                        itemPrices.remove(removeIndex);
			                        itemSumPrices.remove(removeIndex);
			                        itemQuantities.remove(removeIndex);
			                      // itemMap.remove(itemName);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    }
			                } else {
			                    checkboxes16.setSelected(false);
			                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
			                }	
					}
				});
				checkboxes16.setFont(new Font("Times New Roman", Font.BOLD, 12));
				checkboxes16.setBounds(98, 193, 21, 21);
				SidesPanel_4.add(checkboxes16);
				
				Panel SidesPanel_5 = new Panel();
				SidesPanel_5.setLayout(null);
				SidesPanel_5.setBackground(Color.LIGHT_GRAY);
				SidesPanel_5.setBounds(352, 220, 170, 220);
				SidesTab.add(SidesPanel_5);
				
				JLabel Image_17 = new JLabel("New label");
				Image_17.setBounds(0, 0, 170, 105);
				// to scale image to the size of the bounds
				ImageIcon icon17 = new ImageIcon(getClass().getResource("resources/Keto Onion Rings.jpeg"));
				Image img17 = icon17.getImage().getScaledInstance(Image_17.getWidth(), Image_17.getHeight(), DO_NOTHING_ON_CLOSE);
				Image_17.setIcon(new ImageIcon(img17));
				SidesPanel_5.add(Image_17);
				
				JLabel ItemName_17 = new JLabel("Onion Rings");
				ItemName_17.setHorizontalAlignment(SwingConstants.CENTER);
				ItemName_17.setFont(new Font("Times New Roman", Font.BOLD, 12));
				ItemName_17.setBounds(11, 120, 148, 13);
				SidesPanel_5.add(ItemName_17);
				
				JLabel ItemPrice_17 = new JLabel("RM5.00");
				ItemPrice_17.setHorizontalAlignment(SwingConstants.CENTER);
				ItemPrice_17.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				ItemPrice_17.setBounds(74, 150, 74, 13);
				SidesPanel_5.add(ItemPrice_17);
				
				JSpinner spinner17 = new JSpinner();
				spinner17.setModel(new SpinnerNumberModel(0, 0, 99, 1));
				spinner17.setFont(new Font("Times New Roman", Font.BOLD, 12));
				spinner17.setBounds(92, 170, 45, 20);
				SidesPanel_5.add(spinner17);
				
				JLabel lblQuantit_17 = new JLabel("Quantity: ");
				lblQuantit_17.setHorizontalAlignment(SwingConstants.LEFT);
				lblQuantit_17.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblQuantit_17.setBounds(27, 175, 64, 13);
				SidesPanel_5.add(lblQuantit_17);
				
				JLabel lblPrice_17 = new JLabel("Price: ");
				lblPrice_17.setHorizontalAlignment(SwingConstants.LEFT);
				lblPrice_17.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPrice_17.setBounds(27, 150, 64, 13);
				SidesPanel_5.add(lblPrice_17);
				
				JLabel lblPurchase_17 = new JLabel("Purchase: ");
				lblPurchase_17.setHorizontalAlignment(SwingConstants.LEFT);
				lblPurchase_17.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				lblPurchase_17.setBounds(27, 197, 64, 13);
				SidesPanel_5.add(lblPurchase_17);
				
				JCheckBox checkboxes17 = new JCheckBox("");
				checkboxes17.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int qty = Integer.parseInt(spinner17.getValue().toString());
						MealQty[17] = qty;
						String itemID = MealID[17];
			            String itemName = MealName[17];
			            double SumPrice = MealQty[17] * MealPrice[17];

			                if (qty > 0) {
			                    if (checkboxes17.isSelected()) {
			                    	itemIDs.add(itemID);
			                    	itemNames.add(itemName + "\t\t");
			                    	itemPrices.add(MealPrice[17]);
			                    	itemSumPrices.add(SumPrice);
			                        itemQuantities.add(MealQty[17]);
			                     //  itemMap.put(itemName, SumPrice);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    } else {
			                    	int removeIndex = itemNames.indexOf(itemName+ "\t\t");
			                        itemNames.remove(removeIndex);
			                        itemPrices.remove(removeIndex);
			                        itemSumPrices.remove(removeIndex);
			                        itemQuantities.remove(removeIndex);
			                      // itemMap.remove(itemName);
			                     // Update the preview and total
			    	                addRemoveItem();
			                    }
			                } else {
			                    checkboxes17.setSelected(false);
			                    JOptionPane.showMessageDialog(null, "Please increase the item quantity");
			                }	
					}
				});
				checkboxes17.setFont(new Font("Times New Roman", Font.BOLD, 12));
				checkboxes17.setBounds(98, 193, 21, 21);
				SidesPanel_5.add(checkboxes17);
				
				JPanel PreviewOrderPanel = new JPanel();
				PreviewOrderPanel.setBackground(Color.LIGHT_GRAY);
				PreviewOrderPanel.setBounds(586, 421, 311, 122);
				contentPane.add(PreviewOrderPanel);
				PreviewOrderPanel.setLayout(null);
				
				JComboBox<String> EarOrTakeAway = new JComboBox<>();
				EarOrTakeAway.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 selectedOption = (String) EarOrTakeAway.getSelectedItem();
					        if ("Dine In".equals(selectedOption)) {
					            // Add tax if "Dine In" is selected
					            getTax(total);
					            OrderType = "Dine In";
					            tableNo = JOptionPane.showInputDialog("Please Enter Table number: ");
					            
					        }
					        else {
					        	OrderType = "Take Away";
					        }
					}
				});
				EarOrTakeAway.setBounds(94, 8, 122, 21);
				PreviewOrderPanel.add(EarOrTakeAway);
				EarOrTakeAway.setFont(new Font("Times New Roman", Font.BOLD, 12));
				EarOrTakeAway.setModel(new DefaultComboBoxModel(new String[] {"Choose an Option","Dine In", "Take Away"}));
				
				TotalTextField = new JTextField();
				TotalTextField.setBounds(94, 36, 122, 19);
				PreviewOrderPanel.add(TotalTextField);
				TotalTextField.setHorizontalAlignment(SwingConstants.CENTER);
				TotalTextField.setText("0.0\r\n");
				TotalTextField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				TotalTextField.setColumns(10);
				
				JLabel TotalLabel = new JLabel("Total: ");
				TotalLabel.setBounds(48, 38, 45, 13);
				PreviewOrderPanel.add(TotalLabel);
				TotalLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
				
				JButton ResetButton = new JButton("Reset");
				ResetButton.setBounds(20, 96, 85, 21);
				PreviewOrderPanel.add(ResetButton);
				ResetButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				
				
				JButton OrderButton_1 = new JButton("Order");
				OrderButton_1.setBounds(115, 96, 85, 21);
				PreviewOrderPanel.add(OrderButton_1);
				OrderButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(total==0)
						{
							JOptionPane.showMessageDialog(null,"You haven't selected any item.\nPlease select any items you want");
						}else if("Choose an Option".equals(selectedOption))
						{
							JOptionPane.showMessageDialog(null,"Please choose Dine In\n or Take Away.");
						}else
						{
							processOrder(customerID);
						}
					}
				});
				OrderButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				
				JButton CancelButton = new JButton("Cancel");
				CancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnCancelActionPerformed(e);
					}
				});
				CancelButton.setBounds(210, 96, 85, 21);
				PreviewOrderPanel.add(CancelButton);
				CancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				
				JComboBox<String> btnComfirmPayment = new JComboBox<String>();
				btnComfirmPayment.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selectedPayment = (String) btnComfirmPayment.getSelectedItem();
				        if ("Cash".equals(selectedPayment)) {
				            // Add tax if "Dine In" is selected    
				        	PaymentType = "Cash";
				        	status = "Unpaid";
				        }
				        else if ("e-Wallet".equals(selectedPayment)){
				        	PaymentType = "e-Wallet";
				        	status = "Pending";
				        }
				        else {
				        	PaymentType = "Online Banking";
				        	status = "Pending";
				        }
					}
				});
				btnComfirmPayment.setModel(new DefaultComboBoxModel(new String[] {"Payment Method", "Cash", "e-Wallet", "Online Banking"}));
				btnComfirmPayment.setFont(new Font("Times New Roman", Font.BOLD, 12));
				btnComfirmPayment.setBounds(94, 65, 122, 21);
				PreviewOrderPanel.add(btnComfirmPayment);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.LIGHT_GRAY);
				panel_1.setBounds(586, 77, 311, 27);
				contentPane.add(panel_1);
				panel_1.setLayout(null);
				
				JLabel MyOrderLabel = new JLabel("My Order");
				MyOrderLabel.setBounds(115, 5, 81, 13);
				panel_1.add(MyOrderLabel);
				MyOrderLabel.setHorizontalAlignment(SwingConstants.CENTER);
				MyOrderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				
				ResetButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Reset the quantity
						spinner0.setValue(0);
						spinner1.setValue(0);
						spinner2.setValue(0);
						spinner3.setValue(0);
						spinner4.setValue(0);
						spinner5.setValue(0);
						spinner6.setValue(0);
						spinner7.setValue(0);
						spinner8.setValue(0);
						spinner9.setValue(0);
						spinner11.setValue(0);
						spinner10.setValue(0);
						spinner9.setValue(0);
						spinner13.setValue(0);
						spinner14.setValue(0);
						spinner17.setValue(0);
						spinner16.setValue(0);
						spinner15.setValue(0);
						
						checkboxes0.setSelected(false);
						checkboxes1.setSelected(false);
						checkboxes2.setSelected(false);
						checkboxes3.setSelected(false);
						checkboxes4.setSelected(false);
						checkboxes5.setSelected(false);
						checkboxes6.setSelected(false);
						checkboxes7.setSelected(false);
						checkboxes8.setSelected(false);
						checkboxes9.setSelected(false);
						checkboxes10.setSelected(false);
						checkboxes11.setSelected(false);
						checkboxes12.setSelected(false);
						checkboxes13.setSelected(false);
						checkboxes14.setSelected(false);
						checkboxes15.setSelected(false);
						checkboxes16.setSelected(false);
						checkboxes17.setSelected(false);
						TotalTextField.setText("0.0");
						PreviewTextArea.setText("");
						total = 0.0;
						tax = 0.0;
					}
				});
	}
	
	// to perform exit
	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt)
	{
		System.exit(0);
	}

	
	public void getTax (double t) {
		if(t>=0)
		{
			tax = 0.05;
		}
			
	}
	

	
	private void setTime()
	{
		// Get real Time & date
		// Must be after the TimeLabel and DateLabel Initialization
		Thread thread = new Thread(new Runnable() 
		{
			public void run()
			{
				while (true) 
				{ 
					try {
						Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					Date date = new Date();
					SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
					SimpleDateFormat df = new SimpleDateFormat("EEEE,dd-MM-yyyy");
					String time  = tf.format(date);
					String currentDate = df.format(date);
					TimeLabel.setText(time.split(" ")[0]+ " "+time.split(" ")[1]);
					DateLabel.setText(currentDate);
				}
				
			}
		});
		thread.start();
	}
	
	
	private void addRemoveItem() {
		
		StringBuilder previewText = new StringBuilder();
        int itemIndex = 1;
        total = 0;

        // Formatting for prices to two decimal places
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern("#0.00");

        for (int i = 0; i < itemNames.size(); i++) {
            String itemName = itemNames.get(i);
            double SumPrice = itemSumPrices.get(i);
            int quantity = itemQuantities.get(i);
            total += SumPrice;
            previewText.append(itemIndex).append(". ").append(itemName).append("\t").append(df.format(SumPrice)).append("\t(Qty: ").append(quantity).append(")\n");
            itemIndex++;
        }

        String header = "**************************FireCracker****************************\n"
                + "Time: " + TimeLabel.getText() + " " + "Date: " + DateLabel.getText() + "\n"
                + "***********************************************************************"
                + "\n Item Name:\t\t\t" + "Price(RM)\n";

        PreviewTextArea.setText(header + previewText.toString());
        String formattedTotal = df.format(total);
        TotalTextField.setText(formattedTotal);
		/*
		StringBuilder previewText = new StringBuilder();
	        int itemIndex = 1;
	        total = 0;
	        
	     // Formatting for prices to two decimal places
	        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
	        DecimalFormat df = (DecimalFormat) nf;
	        df.applyPattern("#0.00");

	        for (HashMap.Entry<String, Double> entry : itemMap.entrySet()) {
	            String itemName = entry.getKey();
	            double SumPrice = entry.getValue();
	            total += SumPrice;
	            previewText.append(itemIndex).append(". ").append(itemName).append("\t").append(df.format(SumPrice)).append("\n");
	            itemIndex++;
	        }
	        
	        String header = "**************************FireCracker****************************\n"
					+ "Time: " + TimeLabel.getText() + " " + "Date: " + DateLabel.getText() +"\n"
					+"***********************************************************************"
					+ "\n Item Name:\t\t\t"+"Price(RM)\n" ;

	        PreviewTextArea.setText(header + previewText.toString());
	        String formattedTotal = df.format(total);
	        TotalTextField.setText(formattedTotal);
	        */
	}
	

	
	public void processOrder(String customerID) {
		// Calculate tax, sub total, and total
	    double calculatedTax = total * tax;
	    double subTotal = total;
	    grandTotal = total + calculatedTax;

	    // Format numbers to two decimal places
	    DecimalFormat df = new DecimalFormat("#0.00");
	    String formattedTax = df.format(calculatedTax);
	    String formattedSubTotal = df.format(subTotal);
	    String formattedGrandTotal = df.format(grandTotal);
	    
	    TotalTextField.setText(Double.toString(grandTotal));

	    // Update PreviewTextArea with formatted values
	    PreviewTextArea.setText(PreviewTextArea.getText()
	            + "********************************************************************\n"
	            + "Tax: \t\t\t" + formattedTax + "\n"
	            + "Sub Total: \t\t\t" + formattedSubTotal + "\n"
	            + "Total: \t\t\t" + formattedGrandTotal + "\n\n"
	            + "**************************THANK YOU***************************");
	    
	   	
		 // to carry order information to payment()
		String PreviewOrder = PreviewTextArea.getText() ;
		
		 for (int i = 0; i < itemNames.size(); i++) {
			 orderItems.add(new FoodMenu(itemIDs.get(i), itemNames.get(i), itemPrices.get(i), itemQuantities.get(i)));
			 System.out.println(MealID[i] + "\t" + MealName[i] +"\t"+ MealPrice[i]+ "\t" + MealQty[i]);
		 }
		 
		 addCustomerOrder(customerID);
		 addOrderMeal(orderID,orderItems);
		 
		Thread thread1 = new Thread(new Runnable()
				{
					public void run() {
						
							try {
								JOptionPane.showMessageDialog(null,"Press 'Okay' to proceed\nto Payment Gateway");
								// To pause program for 2 seconds
								Thread.sleep(2000);
								Payment frame = new Payment(PreviewOrder,customerID,orderItems,orderID,grandTotal);	// To open Payment page
								frame.setVisible(true);
								dispose(); // To remove FoodMenu page
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					});
					thread1.start();
	}
	
	private void addCustomerOrder(String customerID)
	{
		try {
			// get current time
			//String OrderId = "O00060";
			String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String ordertype = OrderType;
			String tablenumber = tableNo;
			String Status = status;
			String total = Double.toString(grandTotal);
			String customerid  = customerID;
			
			System.out.println("Current Time: " + currentTime);
	        System.out.println("Order Type: " + ordertype);
	        System.out.println("Table Number: " + tablenumber);
	        System.out.println("Status: " + Status);
	        System.out.println("Total: " + total);
	        System.out.println("Customer ID: " + customerid);
        
        
        
			api.addCustomerOrderAPI(currentTime,ordertype,tablenumber,Status,total,customerid);
        
			// get response
			JSONObject response = api.getJsonResponse();
				if (response != null && response.has("message") && response.getString("message").equals("customer order added successfully")) {
					JOptionPane.showMessageDialog(null, "Customer order added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
					JSONObject order = api.getJsonResponse();
				    orderID = getOrderIDFromResponse(order);
				}else {
					JOptionPane.showMessageDialog(null, "Failed to add Customer order.", "Error", JOptionPane.ERROR_MESSAGE);
				}

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	}
	}
	
	private String getOrderIDFromResponse(JSONObject jsonResponse) {
	    try {
	        // Assuming the response structure contains an array of orders
	        // Modify the JSON parsing based on the actual structure of the response
	        if (jsonResponse != null) {
	        	orderID = jsonResponse.getString("OrderID");
	        	return orderID;
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
private void addOrderMeal(String orderID,ArrayList<FoodMenu> orderItems) {
		
	
		System.out.println(orderID);
		for(int i = 0; i< orderItems.size();i++)
		{
			FoodMenu item =  orderItems.get(i);
			String mealID = item.getId();
	        String quantity = Integer.toString(item.getQuantity());
	        api.addOrderMealAPI(orderID, mealID, quantity);;
		}
	}
}
