package gui;

import hlic.Engine;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Dimension;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Gui extends JFrame {

	private JPanel contentPane;
	private JTextField textField_url;
	private JTextField textField_noOfDocs;
	private JTextField textField_linkDepth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
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
	public Gui() {
		setResizable(false);
		setMaximizedBounds(new Rectangle(0, 0, 0, 0));
		setMaximumSize(new Dimension(0, 0));
		setTitle("Hyperlinks Integrity Checker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblUrl = new JLabel("Url:");
		
		textField_url = new JTextField();
		textField_url.setColumns(10);
		
		JLabel lblNumberOfDocuments = new JLabel("Number Of Documents:");
		
		textField_noOfDocs = new JTextField();
		textField_noOfDocs.setColumns(10);
		
		JLabel lblLinkDepth = new JLabel("Link Depth:");
		
		textField_linkDepth = new JTextField();
		textField_linkDepth.setColumns(10);
		
		JButton btnNewButton = new JButton("Excute");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Engine.getInstance(textField_url.getText(),
						Integer.parseInt(textField_linkDepth.getText()), Integer.parseInt(textField_noOfDocs.getText()));
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblUrl)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_url, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNumberOfDocuments)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_noOfDocs)
							.addGap(18)
							.addComponent(lblLinkDepth)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_linkDepth, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
						.addComponent(btnNewButton, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUrl)
						.addComponent(textField_url, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfDocuments)
						.addComponent(textField_noOfDocs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLinkDepth)
						.addComponent(textField_linkDepth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(154, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField_url, textField_noOfDocs, textField_linkDepth, btnNewButton}));
	}
}
