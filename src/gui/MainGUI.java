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
		setBounds(100, 100, 797, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel playerPanel = new JPanel();
		contentPane.add(playerPanel, BorderLayout.NORTH);
		
		JPanel actionPanel = new JPanel();
		contentPane.add(actionPanel, BorderLayout.SOUTH);
		
		JPanel shopPanel = new JPanel();
		contentPane.add(shopPanel, BorderLayout.WEST);
		
		JPanel logPanel = new JPanel();
		contentPane.add(logPanel, BorderLayout.EAST);
	}

	
}
