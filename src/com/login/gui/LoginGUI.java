package com.login.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.gestionCategorie.gui.CategorieGUI;
import com.gestionCategorie.model.Categorie;
import com.gestionVente.gui.VenteGUI;
import com.login.model.Admin;
import com.login.model.Utilisateur;
import com.login.model.Vendeur;

import database.DB;

public class LoginGUI extends JFrame {

	JFrame fLogin;
	JPanel panelLogoSlogan;
	JPanel panelLogin;
	JTextField tusername;
	JPasswordField tpassword;
	JLabel lblLogin;
	JLabel lblSlogan1;
	JLabel lblSlogan2;
	JLabel lblSlogan3;
	JLabel lblShoppingCartLogo;
	ImageIcon image_logo;
	ImageIcon image_logo2;
	JLabel lblUserName;
	JCheckBox chckbxShowPassword;
	JButton btnLogin;
	JLabel lblIconUserLogo;
	JLabel lblPassword;

	
	
	public LoginGUI() {

		fLogin = new JFrame("Login");
		fLogin.setLayout(new GridLayout(0, 2));
		
		JComboBox<String> cbRole = new JComboBox<String>(new String[] { "Admin", "Vendeur" });
		cbRole.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		panelLogoSlogan = new JPanel(new GridBagLayout());
		panelLogin = new JPanel(new GridBagLayout());
		panelLogoSlogan.setBackground(new Color(255, 204, 0));
		panelLogin.setBackground(Color.DARK_GRAY);
		
		
		lblShoppingCartLogo = new JLabel("");
		lblShoppingCartLogo.setBackground(new Color(255, 204, 153));
		image_logo2 = new ImageIcon(this.getClass().getResource("/icons8-shopping-cart-100.png"));
		lblShoppingCartLogo.setIcon(image_logo2);
		panelLogoSlogan.add(lblShoppingCartLogo);

		lblSlogan1 = new JLabel("Chez IYO");
		lblSlogan1.setBackground(Color.WHITE);
		lblSlogan1.setForeground(Color.DARK_GRAY);
		lblSlogan1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelLogoSlogan.add(lblSlogan1);
		
		lblSlogan2 = new JLabel("Les Produits");
		lblSlogan2.setForeground(Color.DARK_GRAY);
		lblSlogan2.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelLogoSlogan.add(lblSlogan2);

		lblSlogan3 = new JLabel("Sont Idéaux");
		lblSlogan3.setForeground(Color.DARK_GRAY);
		lblSlogan3.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelLogoSlogan.add(lblSlogan3);
		
	
		
		lblLogin = new JLabel("LOGIN");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Century Gothic", Font.BOLD, 20));
		panelLogin.add(lblLogin);
		
		lblIconUserLogo = new JLabel("");
		lblIconUserLogo.setBackground(Color.WHITE);
		image_logo = new ImageIcon(this.getClass().getResource("/icons8-user-60 (1).png"));
		lblIconUserLogo.setIcon(image_logo);
		panelLogin.add(lblIconUserLogo);
		
		tusername = new JTextField(10);
		tusername.setMargin(new Insets(2,2,2,30));
		Border mrg = new EmptyBorder(5, 10, 5, 10);
		Border line = BorderFactory.createCompoundBorder(mrg,tusername.getBorder());
		tusername.setBorder(line);
		tusername.setBackground(Color.DARK_GRAY);
		tusername.setFont(new Font("Century Gothic", Font.BOLD, 14));
		tusername.setForeground(new Color(255, 204, 0));
		panelLogin.add(tusername);
		
		lblUserName = new JLabel("USERNAME");
		lblUserName.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblUserName.setForeground(Color.WHITE);
		panelLogin.add(lblUserName);
		
		lblPassword = new JLabel("PASSWORD");
		
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelLogin.add(lblPassword);
		
		tpassword = new JPasswordField(10);
		tpassword.setMargin(new Insets(2,2,2,30));
		Border mrg1 = new EmptyBorder(5, 10, 5, 10);
		Border line1 = BorderFactory.createCompoundBorder(mrg1,tpassword.getBorder());
		tpassword.setBorder(line1);
		tpassword.setForeground(new Color(255, 204, 0));
		tpassword.setBackground(Color.darkGray);
		tpassword.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelLogin.add(tpassword);
		
		  chckbxShowPassword = new JCheckBox("Show Password");
		  chckbxShowPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		  chckbxShowPassword.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
			  if(chckbxShowPassword.isSelected()) {
		         tpassword.setEchoChar((char)0); } else { tpassword.setEchoChar('*'); } } });
		         chckbxShowPassword.setBackground(Color.DARK_GRAY);
		         chckbxShowPassword.setForeground(Color.WHITE);
		         panelLogin.add(chckbxShowPassword);
		         
		         
		         btnLogin = new JButton("Login"); 
		         btnLogin.setBackground(new Color(255, 204, 0));
		 		 btnLogin.setForeground(Color.DARK_GRAY);
		 		 btnLogin.setFont(new Font("Century Gothic", Font.BOLD, 14));
		         btnLogin.addActionListener(new ActionListener() { 
		       	@Override 
		        public void actionPerformed(ActionEvent arg0) {
		       		String username = tusername.getText();
		       		String pass = tpassword.getText();
		       		Admin a = new Admin(0, username, pass);
		       		Vendeur v = new Vendeur(0, username, pass,"");
		       		
		       	  if(cbRole.getSelectedItem().toString().equals("Admin")) {
		       		
				  try { 
				  DB.connect(); //Cette requête permet de vérifier si l'utilisateur se trouve dans la table admin.
				  String sql1 = "Select * from admin where nomAdmin='"+v.getNom()+"' and passwordAdmin='"+v.getPassword()+"'"; DB.ps =
				  DB.conn.prepareStatement(sql1); 
				  DB.rs= DB.ps.executeQuery(); 
				  if(DB.rs.next()) { 
					  JOptionPane.showMessageDialog(null,"Vous êtes connecté avec succès"); 
					  CategorieGUI cat = new CategorieGUI(); 
					  cat.setVisible(true); 
					  fLogin.dispose();
					  cat.SelectCategorie();
				  }
				  else { 
					  JOptionPane.showMessageDialog(null, "Nom utilisateur ou password sont incorrectes!", "Erreur", JOptionPane.ERROR_MESSAGE); tusername.setText(null); 
					  tpassword.setText(null);
					  tusername.requestFocusInWindow(); }
				  
		       	  DB.conn.close();}catch(Exception e) { e.printStackTrace(); }}
				  
		       	  else {
		       	  try {
		       	  DB.connect();//Cette requête permet de vérifier si l’utilisateur se trouve dans la table vendeur.
				  String sql2 = "Select * from vendeur where nomVendeur='"+v.getNom()+"' and passwordVendeur='"+v.getPassword()+"'"; 
				  DB.ps = DB.conn.prepareStatement(sql2); 
				  DB.rs= DB.ps.executeQuery(); 
				  if (DB.rs.next()) { 
					  JOptionPane.showMessageDialog(null,"Vous êtes connecté avec succès");  
					  VenteGUI vente = new VenteGUI(); 
					  vente.setVisible(true); 
					  fLogin.dispose(); 
					  vente.SelectProduit();

				  }
				  
				  else { 
					  JOptionPane.showMessageDialog(null, "Nom utilisateur ou password sont incorrectes!", "Erreur", JOptionPane.ERROR_MESSAGE); tusername.setText(null); 
					  tpassword.setText(null);
					  tusername.requestFocusInWindow(); }
				  
				  
				  DB.conn.close(); }catch(Exception e) { e.printStackTrace(); }}
				 
   	
		       	}
		       		  
		       		  });
		         
		      
		       panelLogin.add(btnLogin);
		       		  
		       		  
		       		  JButton btnCancel = new JButton("Exit"); 
		       		  btnCancel.setFont(new Font("Century Gothic", Font.BOLD, 14));
		       		  btnCancel.addActionListener(new ActionListener() { 
		       			  public void actionPerformed(ActionEvent e) { System.exit(0);; } });
		       		  btnCancel.setBounds(165, 280, 121, 41);
		       		  panelLogin.add(btnCancel);
		       		

		        	GridBagConstraints c=new GridBagConstraints();
		        	GridBagConstraints c1=new GridBagConstraints();
		        	
		        	// position et ajout du lable 1 au panel
		        	c.gridx=1;
		        	c.gridy=1;
		        	c.insets=new Insets(0,0,10,70);
		        	panelLogin.add(lblLogin, c);
		        	
		        	c.gridx=1;
		        	c.gridy=2;
		        	c.insets=new Insets(0,0,20,70);
		        	panelLogin.add(lblIconUserLogo,c);
		        	
		        	c.gridx=0;
		        	c.gridy=3;
		        	c.insets=new Insets(0,0,0,0);
		        	panelLogin.add(lblUserName,c);
		        	
		        	c.gridx=1;
		        	c.gridy=3;
		        	c.insets=new Insets(0,0,0,0);
		        	panelLogin.add(tusername,c);
		        	
		        	c.gridx=0;
		        	c.gridy=4;
		        	c.insets=new Insets(0,0,0,0);
		        	panelLogin.add(lblPassword,c);
		        	
		        	c.gridx=1;
		        	c.gridy=4;
		        	c.insets=new Insets(0,0,0,0);
		        	panelLogin.add(tpassword,c);
		        	
		        	c.gridx=1;
		        	c.gridy=5;
		        	c.insets=new Insets(0,0,15,55);
		        	panelLogin.add(chckbxShowPassword,c);
		        	
		        	c.gridx=1;
		        	c.gridy=6;
		        	c.insets=new Insets(0,0,15,95);
		        	panelLogin.add(cbRole,c);
		        	
		        	c.gridx=0;
		        	c.gridy=7;
		        	c.gridwidth = 2;
		        	c.gridheight = 1; 
		        	c.anchor = GridBagConstraints.NORTHWEST;
		        	c.insets=new Insets(5,85,0,0);
		        	panelLogin.add(btnLogin,c);
		        
		        	c.gridx=1;
		        	c.gridy=7;
		        	c.gridwidth = 2;
		        	c.gridheight = 1; 
		        	c.anchor = GridBagConstraints.NORTHEAST;
		        	c.insets=new Insets(5,0,0,10);
		        	panelLogin.add(btnCancel,c);
		        	
					c1.gridx=2; 
					c1.gridy=1; 
					c1.insets=new Insets(0,0,30,0);
					panelLogoSlogan.add(lblShoppingCartLogo,c1);
					  
					c1.gridx=1;
					c1.gridy=3;
				    c1.insets=new Insets(2,0,0,0);
				    panelLogoSlogan.add(lblSlogan1,c1);
					  
				    c1.gridx=2;
				    c1.gridy=4;
				    c1.insets=new Insets(2,0,0,0);
				    panelLogoSlogan.add(lblSlogan2,c1);
					  
				    c1.gridx=3;
				    c1.gridy=5;
				    c1.insets=new Insets(2,0,40,0);
				    panelLogoSlogan.add(lblSlogan3,c1);
					
					  

		fLogin.add(panelLogoSlogan,BorderLayout.WEST);
		fLogin.add(panelLogin,BorderLayout.EAST);
        
		fLogin.setSize(720, 400);
		fLogin.setLocationRelativeTo(null);
		fLogin.setVisible(true);
		fLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new LoginGUI();

	}

}
