package com.gestionProduit.gui;



	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Font;
	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.GridLayout;
	import java.awt.HeadlessException;
	import java.awt.Insets;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;

	import javax.swing.BorderFactory;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
    import javax.swing.JComboBox;
    import javax.swing.JDialog;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JPasswordField;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.JTextField;
	import javax.swing.SwingConstants;
	import javax.swing.border.Border;
	import javax.swing.border.EmptyBorder;
	import javax.swing.border.TitledBorder;
	import javax.swing.event.ListSelectionEvent;
	import javax.swing.event.ListSelectionListener;
	import javax.swing.table.DefaultTableModel;

import com.gestionCategorie.gui.CategorieGUI;
import com.gestionCategorie.model.Categorie;
	import com.gestionProduit.model.Produit;
import com.gestionVendeur.gui.VendeurGUI;
import com.login.gui.LoginGUI;

	import database.DB;

	public class ProduitGUI extends JFrame{

		JFrame fProduit;
		JPanel panelbuttons;
		JScrollPane paneltable;
		JPanel panelmenu;
		JLabel lblCat;
		JLabel lblEmploye;
		JLabel lblProd;
		JTextField tidProduit;
		JTextField tnomProduit;
		JTextField tprixProduit;
		JTextField tqteProduit;
		JLabel lblidProduit;
		JLabel lblnomProduit;
		JLabel lblprixProduit;
		JLabel lblqteProduit;
		JLabel lblcatProduit;
		JLabel lblgstProduit;
		JLabel lbllistProduit;
		JLabel lbllogin;
		
		JComboBox<String> cbCat;
		

		private JButton btnajouter;
		private JButton btnmodifier;
		private JButton btnsupprimer;
		private JButton btnvider;

		JTable tProduits;
		String[] row;
		String[] cols = { "IdProduit", "NomProduit", "PrixProduit","QteProduit","Catégorie" };
		Produit p; 
		
		
		

		
		public void SelectProduit()
		{
			try {
				DB.connect();

				DB.ps = DB.conn.prepareStatement("select * from produit");
				DB.rs = DB.ps.executeQuery();
				DefaultTableModel tblmodel = new DefaultTableModel();
				tblmodel.setColumnIdentifiers(cols);
				tProduits.setModel(tblmodel);
				while (DB.rs.next()) {
					int id = DB.rs.getInt("idProd");
					String nom = DB.rs.getString("nomProd");
					int prix = DB.rs.getInt("prixProd");
					int qte = DB.rs.getInt("qteProd");
					String cat = DB.rs.getString("catProd");
					Categorie c = new Categorie(0, cat, "");
					Produit p1 = new Produit(id, nom, prix, qte, c);
					tProduits.setModel(tblmodel);
					tblmodel.addRow(new Object[] { p1.getIdProd(), p1.getNomProd(), p1.getPrixProd(),p1.getQteProd(),c.getNomCat() });
					
					

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
			
		 public void GetCategorie()
		 {
		   try{
			   DB.connect();
			   DB.ps = DB.conn.prepareStatement("select * from categorie");
			   DB.rs = DB.ps.executeQuery();
			   
		    while (DB.rs.next())
		    {
		        String cat = DB.rs.getString("nomCat");
		        cbCat.addItem(cat);
		    }
		   }catch(Exception e)
		   {
		       e.printStackTrace();
		   }  
		 }
		

		public ProduitGUI() {
			
			
			fProduit = new JFrame("Produit");
			fProduit.setLayout(new GridLayout(3, 0));

			panelmenu = new JPanel(new GridBagLayout());
			panelmenu.setBackground(Color.DARK_GRAY);
			
			cbCat = new JComboBox<String>();
			cbCat.setFont(new Font("Tahoma", Font.BOLD, 12));
			cbCat.setSize(10, 2);
			

			lblCat = new JLabel("CATEGORIE");
			lblCat.setFont(new Font("Century Gothic", Font.BOLD, 18));
			lblCat.setForeground(Color.WHITE);
			panelmenu.add(lblCat);
			lblEmploye = new JLabel("EMPLOYE");
			lblEmploye.setFont(new Font("Century Gothic", Font.BOLD, 18));
			lblEmploye.setForeground(Color.WHITE);
			panelmenu.add(lblEmploye);
			lblProd = new JLabel("PRODUIT");
			lblProd.setFont(new Font("Century Gothic", Font.BOLD, 18));
			lblProd.setForeground(new Color(255, 204, 0));
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
			
			
			tidProduit = new JTextField(10);
			tidProduit.setMargin(new Insets(2, 2, 2, 30));
			Border mrg = new EmptyBorder(5, 0, 5, 50);
			Border line = BorderFactory.createCompoundBorder(mrg, tidProduit.getBorder());
			tidProduit.setBorder(line);
			tidProduit.setBackground(Color.WHITE);
			tidProduit.setFont(new Font("Century Gothic", Font.BOLD, 14));
			tidProduit.setForeground(Color.DARK_GRAY);
			panelbuttons.add(tidProduit);

			lblidProduit = new JLabel("IdProduit");
			lblidProduit.setFont(new Font("Century Gothic", Font.BOLD, 14));
			lblidProduit.setForeground(Color.DARK_GRAY);
			panelbuttons.add(lblidProduit);

			lblnomProduit = new JLabel("NOM");
			lblnomProduit.setForeground(Color.DARK_GRAY);
			lblnomProduit.setFont(new Font("Century Gothic", Font.BOLD, 14));
			panelbuttons.add(lblnomProduit);

			tnomProduit = new JTextField(10);
			tnomProduit.setMargin(new Insets(2, 2, 2, 30));
			Border mrg1 = new EmptyBorder(5, 0, 5, 50);
			Border line1 = BorderFactory.createCompoundBorder(mrg1, tnomProduit.getBorder());
			tnomProduit.setBorder(line1);
			tnomProduit.setForeground(Color.DARK_GRAY);
			tnomProduit.setBackground(Color.WHITE);
			tnomProduit.setFont(new Font("Century Gothic", Font.BOLD, 14));
			panelbuttons.add(tnomProduit);

			tprixProduit = new JTextField(10);
			tprixProduit.setMargin(new Insets(2, 2, 2, 30));
			Border mrg2 = new EmptyBorder(25, 0, 5, 50);
			Border line2 = BorderFactory.createCompoundBorder(mrg2, tprixProduit.getBorder());
			tprixProduit.setBorder(line2);
			tprixProduit.setBackground(Color.WHITE);
			tprixProduit.setFont(new Font("Century Gothic", Font.BOLD, 14));
			tprixProduit.setForeground(Color.DARK_GRAY);
			panelbuttons.add(tprixProduit);

			lblprixProduit = new JLabel("PRIX");
			lblprixProduit.setFont(new Font("Century Gothic", Font.BOLD, 14));
			lblprixProduit.setForeground(Color.DARK_GRAY);
			panelbuttons.add(lblprixProduit);
			
			lblcatProduit = new JLabel("CATEGORIE");
			lblcatProduit.setForeground(Color.DARK_GRAY);
			lblcatProduit.setFont(new Font("Century Gothic", Font.BOLD, 14));
			panelbuttons.add(lblcatProduit);

			
		//////////	
			
			tqteProduit = new JTextField(10);
			tqteProduit.setMargin(new Insets(2, 2, 2, 30));
			Border mrg3 = new EmptyBorder(25, 0, 5, 50);
			Border line3 = BorderFactory.createCompoundBorder(mrg3, tqteProduit.getBorder());
			tqteProduit.setBorder(line3);
			tqteProduit.setBackground(Color.WHITE);
			tqteProduit.setFont(new Font("Century Gothic", Font.BOLD, 14));
			tqteProduit.setForeground(Color.DARK_GRAY);
			panelbuttons.add(tqteProduit);

			lblqteProduit = new JLabel("QUANTITE");
			lblqteProduit.setFont(new Font("Century Gothic", Font.BOLD, 14));
			lblqteProduit.setForeground(Color.DARK_GRAY);
			panelbuttons.add(lblqteProduit);
			
			
			
			
			
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

			tProduits = new JTable();
			tProduits.setBackground(Color.WHITE);
			tProduits.setPreferredSize(new Dimension(150, 232));

			paneltable = new JScrollPane();
			paneltable.setViewportView(tProduits);
			paneltable.setBorder(BorderFactory.createTitledBorder(null, "Liste des produit", TitledBorder.CENTER, 0,
					new Font("Century Gothic", Font.BOLD, 16), Color.DARK_GRAY));
			paneltable.setBackground(new Color(255, 204, 0));
			
			

			btnajouter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {

					int idProd = Integer.parseInt(tidProduit.getText());
					String nomProd = tnomProduit.getText();
					int prixProd = Integer.parseInt(tprixProduit.getText());
					int qteProd = Integer.parseInt(tqteProduit.getText());
					
					Produit p = new Produit(idProd, nomProd, prixProd, qteProd, null);

					if (tidProduit.getText().isEmpty() || nomProd.isEmpty() || tprixProduit.getText().isEmpty()||tqteProduit.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!");
					} else {
						try {
							DB.connect();
							DB.ps = DB.conn.prepareStatement("insert into produit(idProd,nomProd,prixProd,qteProd,catProd)values(?,?,?,?,?)");
							DB.ps.setInt(1, p.getIdProd());
							DB.ps.setString(2, p.getNomProd());
							DB.ps.setInt(3, p.getPrixProd());
							DB.ps.setInt(4, p.getQteProd());
							DB.ps.setString(5, cbCat.getSelectedItem().toString());
							

							int row = DB.ps.executeUpdate();
							SelectProduit();
							// affichage d'un message qui nous informe que l'insertion est bien effectuée
							JOptionPane.showMessageDialog(null, "le produit a été bien ajouté", "Ajout",
									JOptionPane.INFORMATION_MESSAGE);
							DB.conn.close();
							
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}

			});
			
				tProduits.addMouseListener (new MouseAdapter() {
					@Override
				public void mouseClicked(MouseEvent e) {
				int row = tProduits.getSelectedRow();
				tidProduit.setText(tProduits.getModel().getValueAt(row, 0).toString());
		        tnomProduit.setText(tProduits.getModel().getValueAt(row, 1).toString());
		        tprixProduit.setText(tProduits.getModel().getValueAt(row,2).toString());
		        tqteProduit.setText(tProduits.getModel().getValueAt(row,3).toString());
				}
				});
				
				
				
				lbllogin.addMouseListener (new MouseAdapter() {
					@Override
				public void mouseClicked(MouseEvent e) {
						LoginGUI login = new LoginGUI();
						login.setVisible(true);
		       			fProduit.dispose();
				}
				});
				
				
			
			  btnsupprimer.addActionListener(new ActionListener() {
			  
			  @Override public void actionPerformed(ActionEvent arg0) {
				  int dialogButton = JOptionPane.YES_NO_OPTION;
				  int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez-vous vraiment supprimer ce produit?","Warning",dialogButton);
				  if(dialogResult == JOptionPane.YES_OPTION){
				   
			      try{
			    	  DB.connect();
			    	   String idProd = tidProduit.getText();
			    	   String sql = "delete from produit where idProd ='"+idProd+"'";
			    	   DB.ps = DB.conn.prepareStatement (sql);
			    	   DB.ps.executeUpdate(sql);
			    	   SelectProduit();
			    	   JOptionPane.showMessageDialog(null, "le produit a été bien supprimé");
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

						int idProd = Integer.parseInt(tidProduit.getText());
						String nomProd = tnomProduit.getText();
						int prixProd = Integer.parseInt(tprixProduit.getText());
						int qteProd = Integer.parseInt(tqteProduit.getText());
						Produit p = new Produit(idProd, nomProd, prixProd, qteProd, null);
							try {
								DB.connect();
								DB.ps = DB.conn.prepareStatement("update produit set nomProd=?,prixProd=?, qteProd=?,catProd=? where idProd=?");
								DB.ps.setString(1, p.getNomProd());
								DB.ps.setInt(2, p.getPrixProd());
								DB.ps.setInt(3, p.getQteProd());
								DB.ps.setString(4, cbCat.getSelectedItem().toString());
								DB.ps.setInt(5, p.getIdProd());
								

								int row = DB.ps.executeUpdate();
								SelectProduit();
								// affichage d'un message qui nous informe que l'insertion est bien effectuée
								JOptionPane.showMessageDialog(null, "le produit a été bien modifié", "Modification",
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
					tidProduit.setText("");
					tnomProduit.setText("");
					tprixProduit.setText("");
					tqteProduit.setText("");
				}
			});
			
			lbllogin.addMouseListener (new MouseAdapter() {
				@Override
			public void mouseClicked(MouseEvent e) {
					LoginGUI login = new LoginGUI();
					login.setVisible(true);
	       			fProduit.dispose();
			}
			});
			
			lblEmploye.addMouseListener (new MouseAdapter() {
				@Override
			public void mouseClicked(MouseEvent e) {
					VendeurGUI vendeur = new VendeurGUI();
					vendeur.setVisible(true);
					fProduit.dispose();
					vendeur.SelectVendeur();
			}
			});
			
			lblCat.addMouseListener (new MouseAdapter() {
				@Override
			public void mouseClicked(MouseEvent e) {
					CategorieGUI cat = new CategorieGUI();
					cat.setVisible(true);
	       			fProduit.dispose();
	       			cat.SelectCategorie();
			}
			});
			

			GridBagConstraints p = new GridBagConstraints();

		

			p.gridx = 0;
			p.gridy = 3;
			p.insets = new Insets(0, 0, 0, 10);
			panelbuttons.add(lblidProduit, p);

			p.gridx = 1;
			p.gridy = 3;
			p.insets = new Insets(0, 0, 0, 0);
			panelbuttons.add(tidProduit, p);

			p.gridx = 2;
			p.gridy = 3;
			p.insets = new Insets(0, 0, 0, 0);
			panelbuttons.add(lblnomProduit, p);

			p.gridx = 3;
			p.gridy = 3;
			p.insets = new Insets(0, 0, 0, 0);
			panelbuttons.add(tnomProduit, p);

			p.gridx = 0;
			p.gridy = 5;
			p.insets = new Insets(25, 0, 0, 0);
			panelbuttons.add(lblprixProduit, p);

			p.gridx = 1;
			p.gridy = 5;
			p.insets = new Insets(0, 0, 0, 0);
			panelbuttons.add(tprixProduit, p);

			///////
			p.gridx = 2;
			p.gridy = 5;
			p.insets = new Insets(25, 0, 0, 0);
			panelbuttons.add(lblqteProduit, p);

			p.gridx = 3;
			p.gridy = 5;
			p.insets = new Insets(0, 0, 0, 0);
			panelbuttons.add(tqteProduit, p);
			
			p.gridx = 0;
			p.gridy = 7;
			p.insets = new Insets(20, 0, 0, 0);
			panelbuttons.add(lblcatProduit, p);
			
			p.gridx = 1;
			p.gridy = 7;
			p.insets = new Insets(20, 0, 0, 0);
			panelbuttons.add(cbCat, p);

			
			
			
			
			
			
			
			p.gridx = 0;
			p.gridy = 8;
			p.gridwidth = 2;
			p.gridheight = 1;
			p.anchor = GridBagConstraints.NORTHWEST;
			p.insets = new Insets(30, 0, 0, 10);
			panelbuttons.add(btnajouter, p);

			p.gridx = 1;
			p.gridy = 8;
			p.gridwidth = 2;
			p.gridheight = 1;
			p.insets = new Insets(30, 80, 0, 0);
			panelbuttons.add(btnmodifier, p);

			p.gridx = 2;
			p.gridy = 8;
			p.gridwidth = 2;
			p.gridheight = 1;
			p.insets = new Insets(30, 0, 0, 200);
			panelbuttons.add(btnsupprimer, p);

			p.gridx = 3;
			p.gridy = 8;
			p.gridwidth = 2;
			p.gridheight = 1;
			p.anchor = GridBagConstraints.NORTHEAST;
			p.insets = new Insets(30, 0, 0, 0);
			panelbuttons.add(btnvider, p);

			p.gridx = 1;
			p.gridy = 1;
			p.insets = new Insets(2, 0, 0, 20);
			panelmenu.add(lblCat, p);

			
			p.gridx = 1;
			p.gridy = 2;
			p.insets = new Insets(25, 0, 10, 38);
			panelmenu.add(lblEmploye, p);

			p.gridx = 1;
			p.gridy = 3;
			p.insets = new Insets(25, 0, 0, 40);
			panelmenu.add(lblProd, p);
			
			p.gridx = 2;
			p.gridy = 4;
			p.insets = new Insets(25, 0, 0, 55);
			panelmenu.add(lbllogin, p);
			
			p.gridx = 0;
			p.gridy = 1;
			p.insets = new Insets(0, 0, 0, 20);
			panelmenu.add(lblCatIcon, p);
			
			p.gridx = 0;
			p.gridy = 2;
			p.insets = new Insets(20, 0, 0, 20);
			panelmenu.add(lblIconUsersLogo, p);
			
			p.gridx = 0;
			p.gridy = 3;
			p.insets = new Insets(20, 0, 0, 20);
			panelmenu.add(lblProdIcon, p);
			
			p.gridx = 0;
			p.gridy = 4;
			p.insets = new Insets(20, 0, 0, 20);
			panelmenu.add(lblLoginIcon, p);

			fProduit.add(panelmenu, BorderLayout.NORTH);
			fProduit.add(panelbuttons, BorderLayout.CENTER);
			fProduit.add(paneltable, BorderLayout.SOUTH);
	        
			fProduit.setSize(720, 650);
			fProduit.setLocationRelativeTo(null);
			fProduit.setVisible(true);
			fProduit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		public static void main(String[] args) {
			new ProduitGUI();

		}

	}


