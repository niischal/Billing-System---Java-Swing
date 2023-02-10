package com.nist.billingsystem.billing;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;

public class InventoryManagement extends JFrame {

	private JPanel contentPane;
	private JTable inventoryTable;
	private JLabel searchLabel;
	private JTextField searchTextField;
	private JButton addButton;
	private JButton btnEdit;
	private JButton addButton_2;
	Product productImpl = new ProductImpl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryManagement frame = new InventoryManagement();
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
	/**
	 * 
	 */
	public InventoryManagement() {
		setTitle("Inventory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 524);
		
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
				new InventoryManagement().loadTableData();
				new InventoryManagement().setVisible(true);
				
			}
		});
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		inventoryTable = new JTable();
		inventoryTable.setBounds(45, 49, 452, 300);
		String[] columnName = {"ID","Brand","Name","Cost","Quantity Left"};
		DefaultTableModel tableModel = new DefaultTableModel(columnName,0);
		inventoryTable.setModel(tableModel);
		loadTableData();
		contentPane.setLayout(null);
		JScrollPane inventoryScrollPane = new JScrollPane(inventoryTable);
		inventoryScrollPane.setBounds(47, 57, 452, 340);
		contentPane.add(inventoryScrollPane);
		
		searchLabel = new JLabel("Search");
		searchLabel.setBounds(267, 32, 62, 14);
		contentPane.add(searchLabel);
		


		
		
		searchTextField = new JTextField();
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String searchName = searchTextField.getText();
				if(searchName==null || searchName.isEmpty()) {
					loadTableData();
				} else {
					searchProducts(searchName);
				}
			}
		});
		searchTextField.setBounds(339, 26, 160, 20);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);
		
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AddNewProduct().setVisible(true);
			}
		});
		addButton.setBounds(72, 415, 84, 23);
		contentPane.add(addButton);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel) inventoryTable.getModel();
				int row = inventoryTable.getSelectedRow();
				Object id = tableModel.getValueAt(row, 0);
				Object brand = tableModel.getValueAt(row, 1);
				Object name = tableModel.getValueAt(row, 2);
				Object cost = tableModel.getValueAt(row, 3);
				Object quantity = tableModel.getValueAt(row, 4);
				AddNewProduct addNewProduct = new AddNewProduct();
				addNewProduct.idLabel.setText("ID:  "+id.toString());
				addNewProduct.brandTextField.setText(brand.toString());
				addNewProduct.nameTextField.setText(name.toString());
				addNewProduct.costTextField.setText(cost.toString());
				addNewProduct.quantity.setValue(quantity);
				dispose();
				addNewProduct.setVisible(true);
			}
		});
		btnEdit.setBounds(228, 415, 84, 23);
		contentPane.add(btnEdit);
		
		addButton_2 = new JButton("Delete");
		addButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel  tableModel = (DefaultTableModel) inventoryTable.getModel();
				int row = inventoryTable.getSelectedRow();
				Object id = tableModel.getValueAt(row, 0);
				int status=JOptionPane.showConfirmDialog(InventoryManagement.this, "Click Yes to Confirm.","Do you really want to delete??",JOptionPane.YES_NO_OPTION);
				if(status == 0) {
					productImpl.deleteProduct(Integer.parseInt(id.toString()));
					loadTableData();
				}
				
			}
		});
		addButton_2.setBounds(384, 415, 91, 23);
		contentPane.add(addButton_2);
		
		
	}
	public  void  loadTableData() {
		List<ProductDTO> productList = productImpl.getProducts();
		DefaultTableModel tableModel = (DefaultTableModel) inventoryTable.getModel();
		tableModel.setRowCount(0);
		for (ProductDTO prod : productList) {
			tableModel.addRow(new Object[] {
					prod.getId(),prod.getBrand(),prod.getName(),prod.getCost(),prod.getQuantity()
			});
		}
	}
	public void searchProducts(String searchKey) {
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
