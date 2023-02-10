package com.nist.billingsystem.billing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class AddNewProduct extends JFrame {

	public JPanel contentPane;
	public JTextField brandTextField;
	public JTextField nameTextField;
	public JTextField costTextField;
	public JSpinner quantity;
	public JLabel idLabel;
	InventoryManagement inventoryMgmt = new InventoryManagement();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewProduct frame = new AddNewProduct();
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
	public AddNewProduct() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 251, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Billing");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Billing System");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
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
				new InventoryManagement().setVisible(true);
				new InventoryManagement().loadTableData();
			}
		});
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu_1.add(mntmNewMenuItem_1);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel brandLabel = new JLabel("Brand:");
		brandLabel.setBounds(17, 42, 46, 14);
		contentPane.add(brandLabel);
		
		brandTextField = new JTextField();
		brandTextField.setBounds(80, 39, 136, 20);
		contentPane.add(brandTextField);
		brandTextField.setColumns(10);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(80, 70, 136, 20);
		contentPane.add(nameTextField);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(17, 73, 46, 14);
		contentPane.add(nameLabel);
		
		costTextField = new JTextField();
		costTextField.setColumns(10);
		costTextField.setBounds(80, 101, 136, 20);
		contentPane.add(costTextField);
		
		JLabel costLabel = new JLabel("Cost:");
		costLabel.setBounds(17, 104, 46, 14);
		contentPane.add(costLabel);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Quantity:");
		lblNewLabel_1_1_1.setBounds(17, 141, 46, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		quantity = new JSpinner();
		quantity.setBounds(80, 138, 46, 20);
		contentPane.add(quantity);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Product productImpl = new ProductImpl();
				ProductDTO product = new ProductDTO();
				product.setBrand(brandTextField.getText());
				product.setName(nameTextField.getText());
				product.setCost(Float.parseFloat(costTextField.getText()));
				product.setQuantity(Integer.parseInt(quantity.getValue().toString()));
				if(idLabel.getText().isEmpty() | idLabel.getText()==null) {
				productImpl.addNewProduct(product);
				} else {
					productImpl.updateProduct(product);
				}
				JOptionPane.showConfirmDialog(AddNewProduct.this, "","Product added sucessfully",JOptionPane.PLAIN_MESSAGE);
			}
		});
		addButton.setBounds(19, 197, 89, 23);
		contentPane.add(addButton);
		
		JButton btnCancel = new JButton("Go to List");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				inventoryMgmt.loadTableData();
				inventoryMgmt.setVisible(true);
			}
		});
		btnCancel.setBounds(127, 197, 89, 23);
		contentPane.add(btnCancel);
		
		idLabel = new JLabel("");
		idLabel.setBounds(170, 11, 46, 14);
		contentPane.add(idLabel);
	}
}
