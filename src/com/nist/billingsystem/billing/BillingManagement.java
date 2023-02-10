package com.nist.billingsystem.billing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.sound.midi.Soundbank;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class BillingManagement extends JFrame {
	private JPanel contentPane;
	private JTable inventoryTable;
	private JTable orderTable;
	private JLabel lblNewLabel;
	private JTextField searchTextField;
	private ArrayList<ProductDTO> orderList = new ArrayList<>();
	Product productImpl = new ProductImpl();
	Order orderImpl = new OrderImpl();
	private JTextField quantityTextField;
	private JTextField customerNameTextField;
	JLabel brandLabel, costLabel, subTotalLabel,totalLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillingManagement frame = new BillingManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BillingManagement() {
		setTitle("Billing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 572);
		contentPane = new JPanel();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Billing");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Billing System");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new BillingManagement().loadInventoryTableData();
				new BillingManagement().setVisible(true);
			}
		});
		mntmNewMenuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("Inventory");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Add Item");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				new AddNewProduct().setVisible(true);
			}
		});
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Inventory List");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new InventoryManagement().loadTableData();
				new InventoryManagement().setVisible(true);
				
			}
		});
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		costLabel = new JLabel("");
		costLabel.setBounds(72, 236, 133, 14);
		contentPane.add(costLabel);
		
		brandLabel = new JLabel("");
		brandLabel.setBounds(72, 211, 133, 14);
		contentPane.add(brandLabel);

		ProductDTO selectedProduct= new ProductDTO();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		inventoryTable = new JTable();
		inventoryTable.setBounds(16, 40, 402, 143);
		String[] columnName = {"ID","Brand","Name","Cost","Stock"};
		DefaultTableModel inventoryTableModel = new DefaultTableModel(columnName,0);
		inventoryTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"123", "COcacola", "Subash ko Kidney", "6000000", "100"},
			},
			new String[] {
				"ID", "Brand", "Name", "Cost", "Stock"
			}
		));
		inventoryTable.getColumnModel().getColumn(0).setPreferredWidth(24);
		inventoryTable.getColumnModel().getColumn(0).setMinWidth(12);
		inventoryTable.getColumnModel().getColumn(1).setPreferredWidth(64);
		inventoryTable.getColumnModel().getColumn(2).setPreferredWidth(109);
		inventoryTable.getColumnModel().getColumn(3).setPreferredWidth(58);
		inventoryTable.getColumnModel().getColumn(4).setPreferredWidth(37);
		loadInventoryTableData();
		JScrollPane inventoryScrollPane = new JScrollPane(inventoryTable);
		inventoryTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel  inventoryTableModel = (DefaultTableModel) inventoryTable.getModel();
				int row = inventoryTable.getSelectedRow();
				Object id = inventoryTableModel.getValueAt(row, 0);
				Object brand = inventoryTableModel.getValueAt(row, 1);
				Object name = inventoryTableModel.getValueAt(row, 2);
				Object cost = inventoryTableModel.getValueAt(row, 3);
				Object quantity = inventoryTableModel.getValueAt(row, 4);
				selectedProduct.setId(Integer.parseInt(id.toString()));
				selectedProduct.setBrand(brand.toString());
				selectedProduct.setName(name.toString());
				selectedProduct.setCost(Float.parseFloat(cost.toString()));
				selectedProduct.setQuantity(Integer.parseInt(quantity.toString()));
				brandLabel.setText(brand+" " + name.toString());
				costLabel.setText(cost.toString());
			}
		});
		inventoryScrollPane.setBounds(16, 40, 402, 143);
		contentPane.add(inventoryScrollPane);
		
		
		lblNewLabel = new JLabel("Search");
		lblNewLabel.setBounds(238, 14, 46, 14);
		contentPane.add(lblNewLabel);
		
		searchTextField = new JTextField();
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String searchName = searchTextField.getText();
				if(searchName==null || searchName.isEmpty()) {
					loadInventoryTableData();
				} else if (searchName !=null) {
					searchInventoryProducts(searchName);
				}
			}
		});
		searchTextField.setBounds(285, 11, 133, 20);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(16, 194, 402, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1_1 = new JLabel("Product: ");
		lblNewLabel_1_1.setBounds(16, 211, 69, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Cost:");
		lblNewLabel_1_2_1.setBounds(16, 236, 46, 14);
		contentPane.add(lblNewLabel_1_2_1);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Quantity:");
		lblNewLabel_2.setBounds(215, 211, 69, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("SubTotal:");
		lblNewLabel_2_1.setBounds(215, 236, 69, 14);
		contentPane.add(lblNewLabel_2_1);
		
		subTotalLabel = new JLabel("0.0");
		subTotalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		subTotalLabel.setBounds(285, 236, 125, 14);
		contentPane.add(subTotalLabel);
		
		
		
		List<OrderDTO> orderDTOList = new ArrayList<>();
		JButton addToOrder = new JButton("Add");
		addToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderDTO order = new OrderDTO();
				order.setId(selectedProduct.getId());
				order.setProductName( brandLabel.getText());
				order.setCost( Float.parseFloat(costLabel.getText()));
				order.setQuantity(Integer.parseInt(quantityTextField.getText()));
				order.setSubTotal(order.getCost()*order.getQuantity());
				if(selectedProduct.getQuantity()>=order.getQuantity()) {
					orderDTOList.add(order);
				} else {
					JOptionPane.showConfirmDialog(BillingManagement.this,"Not Enough in Stock","Error",JOptionPane.PLAIN_MESSAGE);
				}
				
				clearFields();
				loadOrderTableData(orderDTOList);
			}
		});
		addToOrder.setBounds(172, 255, 89, 23);
		contentPane.add(addToOrder);
		
		
		
		
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(16, 289, 402, 2);
		contentPane.add(separator_1);
		
		orderTable = new JTable();
		orderTable.setBounds(16, 357, 402, 143);
		String[] orderColumnName = {"Product Name","Cost","Quantity", "Subtotal"};
		DefaultTableModel orderTableModel = new DefaultTableModel(orderColumnName,0);
		orderTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product Name", "Cost", "Quantity", "Subtotal"
			}
		));
		orderTable.getColumnModel().getColumn(0).setPreferredWidth(131);
		orderTable.getColumnModel().getColumn(1).setPreferredWidth(62);
		orderTable.getColumnModel().getColumn(2).setPreferredWidth(56);
		orderTable.getColumnModel().getColumn(3).setPreferredWidth(84);
		
		JScrollPane orderScrollPane = new JScrollPane(orderTable);
		orderScrollPane.setBounds(16, 326, 402, 115);
		contentPane.add(orderScrollPane);
		
		JButton completeOrder = new JButton("Complete Order");
		completeOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerName = customerNameTextField.getText();
				if ( customerName != null | !customerName.isEmpty()){
					//orderImpl.saveOrder(customerName, orderDTOList);
					for(OrderDTO o : orderDTOList) {
						ProductDTO p = productImpl.getProductById(o.getId());
						int quantityAfter = p.getQuantity()-o.getQuantity();
						System.out.println(p.getName());
						p.setQuantity(quantityAfter);
						productImpl.updateProduct(p);
					}
				} else {
					JOptionPane.showConfirmDialog(BillingManagement.this,"Customer Name Field is Empty","Error",JOptionPane.PLAIN_MESSAGE); 
				}

				loadInventoryTableData();
			}
		});
		completeOrder.setBounds(152, 477, 132, 23);
		contentPane.add(completeOrder);

		JLabel error = new JLabel("");
		error.setForeground(new Color(255, 0, 0));
		error.setBounds(378, 235, 46, 14);
		contentPane.add(error);
		
		quantityTextField = new JTextField();
		quantityTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String quantityStr = quantityTextField.getText();
					if (quantityStr == ""| quantityStr == null | quantityStr.isEmpty()) {
						subTotalLabel.setText("0.0");
					}else{
						int quantity = Integer.parseInt(quantityStr);
						float cost = Float.parseFloat(costLabel.getText());
						float subTotal = quantity*cost;
						subTotalLabel.setText(Float.toString(subTotal));
					}
				}catch (Exception exp) {
					System.out.println(exp);
				}finally {
					quantityTextField.setToolTipText("*");
				}
				
			}
		});
		quantityTextField.setBounds(285, 208, 125, 20);
		contentPane.add(quantityTextField);
		quantityTextField.setColumns(10);
		
		JLabel cutomerNameLabel = new JLabel("Customer Name:");
		cutomerNameLabel.setBounds(16, 298, 133, 14);
		contentPane.add(cutomerNameLabel);
		
		customerNameTextField = new JTextField();
		customerNameTextField.setColumns(10);
		customerNameTextField.setBounds(143, 295, 275, 20);
		contentPane.add(customerNameTextField);
		
		JLabel lblNewLabel_1 = new JLabel("Total:");
		lblNewLabel_1.setBounds(238, 452, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		totalLabel = new JLabel("");
		totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		totalLabel.setBounds(285, 452, 125, 14);
		contentPane.add(totalLabel);
		
		
		
	}
	
	public void loadInventoryTableData() {
		List<ProductDTO> productList = productImpl.getProducts();
		DefaultTableModel inventoryTableModel = (DefaultTableModel) inventoryTable.getModel();
		inventoryTableModel.setRowCount(0);
		for (ProductDTO prod : productList) {
			inventoryTableModel.addRow(new Object[] {
					prod.getId(),prod.getBrand(),prod.getName(),prod.getCost(),prod.getQuantity()
			});
		}
	}
	public void loadOrderTableData(List<OrderDTO> orderList) {
		double total=0.0;
		DefaultTableModel orderTableModel = (DefaultTableModel) orderTable.getModel();
		orderTableModel.setRowCount(0);
		for (OrderDTO order : orderList) {
			total = total + order.getSubTotal();
			orderTableModel.addRow(new Object[] {
					order.getProductName(),order.getCost(),order.getQuantity(),order.getSubTotal()
			});
		}
		totalLabel.setText(Double.toString(total));
	}
	
	public void clearFields() {
		brandLabel.setText("");
		costLabel.setText("");
		subTotalLabel.setText("");
		quantityTextField.setText(null);
		
	}
	public void searchInventoryProducts(String searchKey) {
		List<ProductDTO> productList = productImpl.searchProduct(searchKey);
		DefaultTableModel tableModel = (DefaultTableModel) inventoryTable.getModel();
		tableModel.setRowCount(0);
		for (ProductDTO p : productList) {
			tableModel.addRow(new Object[] {
					p.getId(),p.getBrand(),p.getName(),p.getCost(),p.getQuantity()
			});
			
		}
	}
}
