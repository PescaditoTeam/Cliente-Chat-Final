package com.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PuertoIP extends JFrame {

	private JPanel contentPane;
	private JTextField TFPuerto;
	private JTextField TFIP;


	public PuertoIP(VistaCliente miUi) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TFPuerto = new JTextField();
		TFPuerto.setBounds(15, 34, 146, 26);
		contentPane.add(TFPuerto);
		TFPuerto.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Puerto");
		lblNewLabel.setBounds(187, 40, 69, 20);
		contentPane.add(lblNewLabel);
		
		TFIP = new JTextField();
		TFIP.setBounds(15, 93, 146, 26);
		contentPane.add(TFIP);
		TFIP.setColumns(10);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(187, 96, 69, 20);
		contentPane.add(lblIp);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TFIP.getText()!=null&&TFPuerto.getText()!=null){
					miUi.puerto=Integer.parseInt(TFPuerto.getText());//NO VERIFICA DATOS
					miUi.serverAddr=TFIP.getText();
				}
				System.out.println(miUi.puerto+" "+miUi.serverAddr);
				setVisible(false);
			}
		});
		btnGuardar.setBounds(264, 62, 115, 29);
		contentPane.add(btnGuardar);
	}

}
