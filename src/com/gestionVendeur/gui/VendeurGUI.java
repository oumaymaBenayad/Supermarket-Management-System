package com.gestionVendeur.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.gestionCategorie.gui.CategorieGUI;
import com.gestionCategorie.model.Categorie;
import com.gestionProduit.gui.ProduitGUI;
import com.login.gui.LoginGUI;
import com.login.model.Vendeur;

import database.DB;

public class VendeurGUI extends JFrame{
	
	JFrame fVendeur;
	JPanel panelbuttons;
	JScrollPane paneltable;
	JPanel panelmenu;
	JLabel lblCat;
	JLabel lblEmploye;
	JLabel lblProd;
	JTextField tidVend;
	JTextField tnomVend;
	JTextField tpassVend;
	JLabel lblidVend;
	JLabel lblnomVend;
	JLabel lblpassVend;
	JLabel lblgenreVend;
	JLabel lblgstCat;
	JLabel lbllistCat;
	JLabel lbllogin;
	

	private JButton btnajouter;
	private JButton btnmodifier;
	private JButton btnsupprimer;
	private JButton btnvider;

	JTable tvendeurs;
	String[] row;
	String[] cols = { "IdVendeur", "NomVendeur", "Password","Genre" };
	Vendeur v; 

	JComboBox<String> cbGenre;
	

	
	public void SelectVendeur()
	{
		try {
			DB.connect();

			DB.ps = DB.conn.prepareStatement("select * from vendeur");
			DB.rs = DB.ps.executeQuery();
			DefaultTableModel tblmodel = new DefaultTableModel();
			tblmodel.setColumnIdentifiers(cols);
			tvendeurs.setModel(tblmodel);
			while (DB.rs.next()) {
				int id = DB.rs.getInt("idVendeur");
				String nom = DB.rs.getString("nomVendeur");
				String pass = DB.rs.getString("passwordVendeur");
				String genre = DB.rs.getString("genre");
				Vendeur v1 = new Vendeur(id, nom, pass, genre);
				tvendeurs.setModel(tblmodel);
				tblmodel.addRow(new Object[] { v1.getId(), v1.getNom(), v1.getPassword() ,v1.getGenre() });
				
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		
	

	public VendeurGUI() {
		
		
		fVendeur = new JFrame("Vendeur");
		fVendeur.setLayout(new GridLayout(3, 0));

		panelmenu = new JPanel(new GridBagLayout());
		panelmenu.setBackground(Color.DARK_GRAY);
		
		cbGenre = new JComboBox<String>(new String[] { "Femme", "Homme" });
		cbGenre.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblCat = new JLabel("CATEGORIE");
		lblCat.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblCat.setForeground(Color.WHITE);
		panelmenu.add(lblCat);
		lblEmploye = new JLabel("EMPLOYE");
		lblEmploye.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblEmploye.setForeground(new Color(255, 204, 0));
		panelmenu.add(lblEmploye);
		lblProd = new JLabel("PRODUIT");
		lblProd.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblProd.setForeground(Color.WHITE);
		panelmenu.add(lblProd);
		
		lbllogin = new JLabel("LOGOUT");
		lbllogin.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lbllogin.setForeground(Color.WHITE);
		panelmenu.add(lbllogin);

		JLabel lblIconUsersLogo = new JLabel("");
		lblIconUsersLogo.setBackground(Color.WHITE);
		ImageIcon image_users = new ImageIcon(this.getClass().getResource("/icons8-member-30.png"));
		lblIconUsersLogo.setIcon(image_users);
		panelmenu.add(lblIconUsersLogo);

		
		JLabel lblProdIcon = new JLabel("");
		ImageIcon image_prod = new ImageIcon(this.getClass().getResource("/icons8-grocery-shelf-30.png"));
		lblProdIcon.setIcon(image_prod); 
		panelmenu.add(lblProdIcon);
		  
		JLabel lblCatIcon = new JLabel("");
		ImageIcon image_cat = new ImageIcon(this.getClass().getResource("/icons8-category-30.png"));
		lblCatIcon.setIcon(image_cat); 
		panelmenu.add(lblCatIcon);
		
		JLabel lblLoginIcon = new JLabel("");
		ImageIcon image_login = new ImageIcon(this.getClass().getResource("/icons8-login-30.png"));
		lblLoginIcon.setIcon(image_login); 
		panelmenu.add(lblLoginIcon);
		 

		panelbuttons = new JPanel(new GridBagLayout());
		panelbuttons.setBackground(Color.WHITE);
		
		
		tidVend = new JTextField(10);
		tidVend.setMargin(new Insets(2, 2, 2, 30));
		Border mrg = new EmptyBorder(5, 0, 5, 50);
		Border line = BorderFactory.createCompoundBorder(mrg, tidVend.getBorder());
		tidVend.setBorder(line);
		tidVend.setBackground(Color.WHITE);
		tidVend.setFont(new Font("Century Gothic", Font.BOLD, 14));
		tidVend.setForeground(Color.DARK_GRAY);
		panelbuttons.add(tidVend);

		lblidVend = new JLabel("IdVend");
		lblidVend.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblidVend.setForeground(Color.DARK_GRAY);
		panelbuttons.add(lblidVend);

		lblnomVend = new JLabel("NOM");
		lblnomVend.setForeground(Color.DARK_GRAY);
		lblnomVend.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbuttons.add(lblnomVend);

		tnomVend = new JTextField(10);
		tnomVend.setMargin(new Insets(2, 2, 2, 30));
		Border mrg1 = new EmptyBorder(30, 0, 5, 50);
		Border line1 = BorderFactory.createCompoundBorder(mrg1, tnomVend.getBorder());
		tnomVend.setBorder(line1);
		tnomVend.setForeground(Color.DARK_GRAY);
		tnomVend.setBackground(Color.WHITE);
		tnomVend.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbuttons.add(tnomVend);

		tpassVend = new JTextField(10);
		tpassVend.setMargin(new Insets(2, 2, 2, 30));
		Border mrg2 = new EmptyBorder(5, 5, 5, 0);
		Border line2 = BorderFactory.createCompoundBorder(mrg2, tpassVend.getBorder());
		tpassVend.setBorder(line2);
		tpassVend.setBackground(Color.WHITE);
		tpassVend.setFont(new Font("Century Gothic", Font.BOLD, 14));
		tpassVend.setForeground(Color.DARK_GRAY);
		panelbuttons.add(tpassVend);

		lblpassVend = new JLabel("PASSWORD");
		lblpassVend.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblpassVend.setForeground(Color.DARK_GRAY);
		panelbuttons.add(lblpassVend);
		
		lblgenreVend = new JLabel("GENRE");
		lblgenreVend.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblgenreVend.setForeground(Color.DARK_GRAY);
		panelbuttons.add(lblgenreVend);

		btnajouter = new JButton("ajouter");
		btnajouter.setBackground(new Color(255, 204, 0));
		btnajouter.setForeground(Color.DARK_GRAY);
		btnajouter.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbuttons.add(btnajouter);

		btnmodifier = new JButton("modifier");
		btnmodifier.setBackground(new Color(255, 204, 0));
		btnmodifier.setForeground(Color.DARK_GRAY);
		btnmodifier.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbuttons.add(btnmodifier);

		btnsupprimer = new JButton("supprimer");
		btnsupprimer.setBackground(new Color(255, 204, 0));
		btnsupprimer.setForeground(Color.DARK_GRAY);
		btnsupprimer.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbuttons.add(btnsupprimer);

		btnvider = new JButton("vider champs");
		btnvider.setBackground(new Color(255, 204, 0));
		btnvider.setForeground(Color.DARK_GRAY);
		btnvider.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbuttons.add(btnvider);

		tvendeurs = new JTable();
		tvendeurs.setBackground(Color.WHITE);
		tvendeurs.setPreferredSize(new Dimension(150, 232));

		paneltable = new JScrollPane();
		paneltable.setViewportView(tvendeurs);
		paneltable.setBorder(BorderFactory.createTitledBorder(null, "Liste des vendeurs", TitledBorder.CENTER, 0,
				new Font("Century Gothic", Font.BOLD, 16), Color.DARK_GRAY));
		paneltable.setBackground(new Color(255, 204, 0));
		
		

		btnajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				int idVend = Integer.parseInt(tidVend.getText());
				String nomVend = tnomVend.getText();
				String passVend = tpassVend.getText();
				
				Vendeur v = new Vendeur(idVend, nomVend,passVend,null);

				if (tidVend.getText().isEmpty() || nomVend.isEmpty() || tpassVend.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!");
				} else {
					try {
						DB.connect();
						DB.ps = DB.conn.prepareStatement("insert into vendeur(idVendeur,nomVendeur,passwordVendeur,genre)values(?,?,?,?)");
						DB.ps.setInt(1, v.getId());
						DB.ps.setString(2, v.getNom());
						DB.ps.setString(3, v.getPassword());
						DB.ps.setString(4, cbGenre.getSelectedItem().toString());

						int row = DB.ps.executeUpdate();
						SelectVendeur();
						// affichage d'un message qui nous informe que l'insertion est bien effectuée
						JOptionPane.showMessageDialog(null, "le vendeur(se) a été bien ajouté", "Ajout",
								JOptionPane.INFORMATION_MESSAGE);
						DB.conn.close();
						
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}

		});
		
			tvendeurs.addMouseListener (new MouseAdapter() {
				@Override
			public void mouseClicked(MouseEvent e) {
			int row = tvendeurs.getSelectedRow();
			tidVend.setText(tvendeurs.getModel().getValueAt(row, 0).toString());
	        tnomVend.setText(tvendeurs.getModel().getValueAt(row, 1).toString());
	        tpassVend.setText(tvendeurs.getModel().getValueAt(row,2).toString());
			}
			});
			
			
			
			lbllogin.addMouseListener (new MouseAdapter() {
				@Override
			public void mouseClicked(MouseEvent e) {
					LoginGUI login = new LoginGUI();
					login.setVisible(true);
	       			fVendeur.dispose();
			}
			});
			
			lblProd.addMouseListener (new MouseAdapter() {
				@Override
			public void mouseClicked(MouseEvent e) {
					ProduitGUI prod = new ProduitGUI();
					prod.setVisible(true);
	       			fVendeur.dispose();
	       			prod.GetCategorie();
	       			prod.SelectProduit();
			}
			});
			
			lblCat.addMouseListener (new MouseAdapter() {
				@Override
			public void mouseClicked(MouseEvent e) {
					CategorieGUI cat = new CategorieGUI();
					cat.setVisible(true);
	       			fVendeur.dispose();
	       			cat.SelectCategorie();
			}
			});
			
			
			
			
		
		  btnsupprimer.addActionListener(new ActionListener() {
		  
		  @Override public void actionPerformed(ActionEvent arg0) {
			  int dialogButton = JOptionPane.YES_NO_OPTION;
			  int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez-vous vraiment supprimer ce vendeur?","Warning",dialogButton);
			  if(dialogResult == JOptionPane.YES_OPTION){
			   
		      try{
		    	  DB.connect();
		    	   String idCat = tidVend.getText();
		    	   String sql = "delete from vendeur where idVendeur ='"+idCat+"'";
		    	   DB.ps = DB.conn.prepareStatement (sql);
		    	   DB.ps.executeUpdate(sql);
		    	   SelectVendeur();
		    	   JOptionPane.showMessageDialog(null, "le vendeur(se) a été bien supprimé");
		    	          }catch(Exception e)
		    	            {
		    	                e.printStackTrace();
		    	            }
		    	        }  
		  }
		   });
		 
		  btnmodifier.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {

					int idVend = Integer.parseInt(tidVend.getText());
					String nomVend = tnomVend.getText();
					String passVend = tpassVend.getText();
					Vendeur v = new Vendeur(idVend, nomVend, passVend,null);
						try {
							DB.connect();
							DB.ps = DB.conn.prepareStatement("update vendeur set nomVendeur=?,passwordVendeur=?,genre=? where idVendeur=?");
							DB.ps.setString(1, v.getNom());
							DB.ps.setString(2, v.getPassword());
							DB.ps.setString(3, cbGenre.getSelectedItem().toString());
							DB.ps.setInt(4, v.getId());

							int row = DB.ps.executeUpdate();
							SelectVendeur();
							// affichage d'un message qui nous informe que la modification est bien effectuée
							JOptionPane.showMessageDialog(null, "le vendeur(se) a été bien modifié", "Modification",
									JOptionPane.INFORMATION_MESSAGE);
							DB.conn.close();
							
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				

			});
		  
		  

		btnvider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tidVend.setText("");
				tnomVend.setText("");
				tpassVend.setText("");
			}
		});

		GridBagConstraints c = new GridBagConstraints();

	

		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 10);
		panelbuttons.add(lblidVend, c);

		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 0);
		panelbuttons.add(tidVend, c);

		c.gridx = 2;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 0);
		panelbuttons.add(lblpassVend, c);

		c.gridx = 3;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 0);
		panelbuttons.add(tpassVend, c);

		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(25, 0, 0, 0);
		panelbuttons.add(lblnomVend, c);

		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(0, 0, 0, 0);
		panelbuttons.add(tnomVend, c);
		
		c.gridx = 2;
		c.gridy = 5;
		c.insets = new Insets(25, 0, 0, 0);
		panelbuttons.add(lblgenreVend, c);
		
		c.gridx = 3;
		c.gridy = 5;
		c.insets = new Insets(25, 0, 0, 100);
		panelbuttons.add(cbGenre, c);
		
		

		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.insets = new Insets(30, 0, 0, 10);
		panelbuttons.add(btnajouter, c);

		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(30, 80, 0, 0);
		panelbuttons.add(btnmodifier, c);

		c.gridx = 2;
		c.gridy = 6;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(30, 0, 0, 200);
		panelbuttons.add(btnsupprimer, c);

		c.gridx = 3;
		c.gridy = 6;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.NORTHEAST;
		c.insets = new Insets(30, 0, 0, 0);
		panelbuttons.add(btnvider, c);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(2, 0, 0, 20);
		panelmenu.add(lblCat, c);

		
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(25, 0, 10, 38);
		panelmenu.add(lblEmploye, c);

		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(25, 0, 0, 40);
		panelmenu.add(lblProd, c);
		
		c.gridx = 2;
		c.gridy = 4;
		c.insets = new Insets(25, 0, 0, 55);
		panelmenu.add(lbllogin, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 20);
		panelmenu.add(lblCatIcon, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(20, 0, 0, 20);
		panelmenu.add(lblIconUsersLogo, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(20, 0, 0, 20);
		panelmenu.add(lblProdIcon, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(20, 0, 0, 20);
		panelmenu.add(lblLoginIcon, c);

		fVendeur.add(panelmenu, BorderLayout.NORTH);
		fVendeur.add(panelbuttons, BorderLayout.CENTER);
		fVendeur.add(paneltable, BorderLayout.SOUTH);
        
		fVendeur.setSize(720, 650);
		fVendeur.setLocationRelativeTo(null);
		fVendeur.setVisible(true);
		fVendeur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	}
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		
		new VendeurGUI();
		
	}

}
