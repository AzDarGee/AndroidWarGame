package gui;
import item.ConsumableItem;
import item.EquippableItem;
import item.Item;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.FlowLayout;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class MainGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Item.initializeItems();
					MainGUI frame = new MainGUI();
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
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Menu", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel shopPanel = new JPanel();
		panel.add(shopPanel, BorderLayout.NORTH);
		shopPanel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabConsumable = new JTabbedPane(JTabbedPane.TOP);
		shopPanel.add(tabConsumable);
		
		JPanel panel_2 = new JPanel();
		tabConsumable.addTab("New tab", null, panel_2, null);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JList listConsumableShop = generateConsumableShoplist();
		listConsumableShop.setBorder(new LineBorder(new Color(0, 0, 0)));
		listConsumableShop.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listConsumableShop.setVisibleRowCount(3);
		panel_2.add(listConsumableShop);
		
		JButton btnBuyConsumable = new JButton("New button");
		panel_2.add(btnBuyConsumable);
		
		JPanel panel_3 = new JPanel();
		tabConsumable.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JList listEquippableShop = generateEquippableShoplist();
		listEquippableShop.setBorder(new LineBorder(new Color(0, 0, 0)));
		listEquippableShop.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listEquippableShop.setVisibleRowCount(3);
		panel_3.add(listEquippableShop);
		
		JButton btnBuyEquippable = new JButton("New button");
		panel_3.add(btnBuyEquippable);
		
		JPanel inventoryPanel = new JPanel();
		panel.add(inventoryPanel, BorderLayout.CENTER);
		inventoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JList listInventory = new JList();
		inventoryPanel.add(listInventory);
		
		JButton btnUseItem = new JButton("New button");
		inventoryPanel.add(btnUseItem);
		
		JPanel attackPanel = new JPanel();
		panel.add(attackPanel, BorderLayout.SOUTH);
		attackPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JList listAttack = new JList();
		attackPanel.add(listAttack);
		
		JButton btnChooseAtk = new JButton("New button");
		attackPanel.add(btnChooseAtk);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Main", null, panel_1, null);
	}

	public JList generateConsumableShoplist()
	{
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		for(int n=0;n<Item.allConsumableItems.size();n++)
		{
			Item item = Item.allConsumableItems.get(n);
			dlm.addElement(item.getName() + " " + item.getCost());
		}

		return new JList<String>(dlm);
	}
	
	public JList generateEquippableShoplist()
	{
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		for(int n=0;n<Item.allEquippableItems.size();n++)
		{
			Item item = Item.allEquippableItems.get(n);
			dlm.addElement(item.getName() + " " + item.getCost());
		}

		return new JList<String>(dlm);
	}
}
