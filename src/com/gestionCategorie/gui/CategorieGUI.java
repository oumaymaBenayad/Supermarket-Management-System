package com.gestionCategorie.gui;

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

import com.gestionCategorie.model.Categorie;
import com.gestionProduit.model.Produit;
import com.login.gui.LoginGUI;
import com.gestionProduit.gui.ProduitGUI;
import com.gestionVendeur.gui.VendeurGUI;

import database.DB;

public class CategorieGUI extends JFrame{

	JFrame fCategorie;
	JPanel panelbuttons;
	JScrollPane paneltable;
	JPanel panelmenu;
	JLabel lblCat;
	JLabel lblEmploye;
	JLabel lblProd;
	JTextField tidCat;
	JTextField tnomCat;
	JTextField tdescCat;
	JLabel lblidCat;
	JLabel lblnomCat;
	JLabel lbldescCat;
	JLabel lblgstCat;
	JLabel lbllistCat;
	JLabel lbllogin;
	

	private JButton btnajouter;
	private JButton btnmodifier;
	private JButton btnsupprimer;
	private JButton btnvider;

	JTable tcategories;
	String[] row;
	String[] cols = { "IdCat", "NomCat", "DescriptionCat" };
	Categorie c; 

	
	

	
	public void SelectCategorie()
	{
		try {
			DB.connect();

			DB.ps = DB.conn.prepareStatement("select * from categorie");
			DB.rs = DB.ps.executeQuery();
			DefaultTableModel tblmodel = new DefaultTableModel();
			tblmodel.setColumnIdentifiers(cols);
			tcategories.setModel(tblmodel);
			while (DB.rs.next()) {
				int id = DB.rs.getInt("idCat");
				String nom = DB.rs.getString("nomCat");
				String desc = DB.rs.getString("descCat");
				Categorie c1 = new Categorie(id, nom, desc);
				tcategories.setModel(tblmodel);
				tblmodel.addRow(new Object[] { c1.getIdCat(), c1.getNomCat(), c1.getDescriptionCat() });
				
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		
	

	public CategorieGUI() {
		
		
		fCategorie = new JFrame("Catégorie");
		fCategorie.setLayout(new GridLayout(3, 0));

		panelmenu = new JPanel(new GridBagLayout());
		panelmenu.setBackground(Color.DARK_GRAY);

		lblCat = new JLabel("CATEGORIE");
		lblCat.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblCat.setForeground(new Color(255, 204, 0));
		panelmenu.add(lblCat);
		lblEmploye = new JLabel("EMPLOYE");
		lblEmploye.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblEmploye.setForeground(Color.WHITE);
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
		
		
		tidCat = new JTextField(10);
		tidCat.setMargin(new Insets(2, 2, 2, 30));
		Border mrg = new EmptyBorder(5, 0, 5, 100);
		Border line = BorderFactory.createCompoundBorder(mrg, tidCat.getBorder());
		tidCat.setBorder(line);
		tidCat.setBackground(Color.WHITE);
		tidCat.setFont(new Font("Century Gothic", Font.BOLD, 14));
		tidCat.setForeground(Color.DARK_GRAY);
		panelbuttons.add(tidCat);

		lblidCat = new JLabel("IdCat");
		lblidCat.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblidCat.setForeground(Color.DARK_GRAY);
		panelbuttons.add(lblidCat);

		lblnomCat = new JLabel("NOM");
		lblnomCat.setForeground(Color.DARK_GRAY);
		lblnomCat.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbuttons.add(lblnomCat);

		tnomCat = new JTextField(10);
		tnomCat.setMargin(new Insets(2, 2, 2, 30));
		Border mrg1 = new EmptyBorder(5, 0, 5, 50);
		Border line1 = BorderFactory.createCompoundBorder(mrg1, tnomCat.getBorder());
		tnomCat.setBorder(line1);
		tnomCat.setForeground(Color.DARK_GRAY);
		tnomCat.setBackground(Color.WHITE);
		tnomCat.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbuttons.add(tnomCat);

		tdescCat = new JTextField(10);
		tdescCat.setMargin(new Insets(2, 2, 2, 30));
		Border mrg2 = new EmptyBorder(25, 0, 5, 50);
		Border line2 = BorderFactory.createCompoundBorder(mrg2, tdescCat.getBorder());
		tdescCat.setBorder(line2);
		tdescCat.setBackground(Color.WHITE);
		tdescCat.setFont(new Font("Century Gothic", Font.BOLD, 14));
		tdescCat.setForeground(Color.DARK_GRAY);
		panelbuttons.add(tdescCat);

		lbldescCat = new JLabel("DESCRIPTION");
		lbldescCat.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lbldescCat.setForeground(Color.DARK_GRAY);
		panelbuttons.add(lbldescCat);

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

		tcategories = new JTable();
		tcategories.setBackground(Color.WHITE);
		tcategories.setPreferredSize(new Dimension(150, 232));

		paneltable = new JScrollPane();
		paneltable.setViewportView(tcategories);
		paneltable.setBorder(BorderFactory.createTitledBorder(null, "Liste des catégories", TitledBorder.CENTER, 0,
				new Font("Century Gothic", Font.BOLD, 16), Color.DARK_GRAY));
		paneltable.setBackground(new Color(255, 204, 0));
		
		

		btnajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				int idCat = Integer.parseInt(tidCat.getText());
				String nomCat = tnomCat.getText();
				String descriptionCat = tdescCat.getText();
				Categorie c = new Categorie(idCat, nomCat, descriptionCat);

				if (tidCat.getText().isEmpty() || nomCat.isEmpty() || descriptionCat.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!");
				} else {
					try {
						DB.connect();
						DB.ps = DB.conn.prepareStatement("insert into categorie(idCat,nomCat,descCat)values(?,?,?)");
						DB.ps.setInt(1, c.getIdCat());
						DB.ps.setString(2, c.getNomCat());
						DB.ps.setString(3, c.getDescriptionCat());

						int row = DB.ps.executeUpdate();
						SelectCategorie();
						// affichage d'un message qui nous informe que l'insertion est bien effectuée
						JOptionPane.showMessageDialog(null, "la catégorie a été bien ajouté", "Ajout",
								JOptionPane.INFORMATION_MESSAGE);
						DB.conn.close();
						
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}

		});
		
			tcategories.addMouseListener (new MouseAdapter() {
				@Override
			public void mouseClicked(MouseEvent e) {
			int row = tcategories.getSelectedRow();
			tidCat.setText(tcategories.getModel().getValueAt(row, 0).toString());
	        tnomCat.setText(tcategories.getModel().getValueAt(row, 1).toString());
	        tdescCat.setText(tcategories.getModel().getValueAt(row,2).toString());
			}
			});
			
			
			
			lbllogin.addMouseListener (new MouseAdapter() {
				@Override
			public void mouseClicked(MouseEvent e) {
					LoginGUI login = new LoginGUI();
					login.setVisible(true);
	       			fCategorie.dispose();
			}
			});
			
			lblProd.addMouseListener (new MouseAdapter() {
				@Override
			public void mouseClicked(MouseEvent e) {
					ProduitGUI prod = new ProduitGUI();
					prod.setVisible(true);
	       			fCategorie.dispose();
	       			prod.GetCategorie();
	       			prod.SelectProduit();
			}
			});
			
			lblEmploye.addMouseListener (new MouseAdapter() {
				@Override
			public void mouseClicked(MouseEvent e) {
					VendeurGUI vendeur = new VendeurGUI();
					vendeur.setVisible(true);
	       			fCategorie.dispose();
	       			vendeur.SelectVendeur();
			}
			});
			
			
			
			
		
		  btnsupprimer.addActionListener(new ActionListener() {
		  
		  @Override public void actionPerformed(ActionEvent arg0) {
			  int dialogButton = JOptionPane.YES_NO_OPTION;
			  int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez-vous vraiment supprimer cette catégorie?","Warning",dialogButton);
			  if(dialogResult == JOptionPane.YES_OPTION){
			   
		      try{
		    	  DB.connect();
		    	   String idCat = tidCat.getText();
		    	   String sql = "delete from categorie where idCat ='"+idCat+"'";
		    	   DB.ps = DB.conn.prepareStatement (sql);
		    	   DB.ps.executeUpdate(sql);
		    	   SelectCategorie();
		    	   JOptionPane.showMessageDialog(null, "la catégorie a été bien supprimé");
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

					int idCat = Integer.parseInt(tidCat.getText());
					String nomCat = tnomCat.getText();
					String descriptionCat = tdescCat.getText();
					Categorie c = new Categorie(idCat, nomCat, descriptionCat);
						try {
							DB.connect();
							DB.ps = DB.conn.prepareStatement("update categorie set nomCat=?,descCat=? where idCat=?");
							DB.ps.setString(1, c.getNomCat());
							DB.ps.setString(2, c.getDescriptionCat());
							DB.ps.setInt(3, c.getIdCat());

							int row = DB.ps.executeUpdate();
							SelectCategorie();
							// affichage d'un message qui nous informe que la modification est bien effectuée
							JOptionPane.showMessageDialog(null, "la catégorie a été bien modifié", "Modification",
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
				tidCat.setText("");
				tnomCat.setText("");
				tdescCat.setText("");
			}
		});

		GridBagConstraints c = new GridBagConstraints();

	

		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 0);
		panelbuttons.add(lblidCat, c);

		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 0);
		panelbuttons.add(tidCat, c);

		c.gridx = 2;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 0);
		panelbuttons.add(lblnomCat, c);

		c.gridx = 3;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 0);
		panelbuttons.add(tnomCat, c);

		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(25, 0, 0, 0);
		panelbuttons.add(lbldescCat, c);

		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(0, 0, 0, 0);
		panelbuttons.add(tdescCat, c);

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

		fCategorie.add(panelmenu, BorderLayout.NORTH);
		fCategorie.add(panelbuttons, BorderLayout.CENTER);
		fCategorie.add(paneltable, BorderLayout.SOUTH);
        
		fCategorie.setSize(720, 650);
		fCategorie.setLocationRelativeTo(null);
		fCategorie.setVisible(true);
		fCategorie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new CategorieGUI();

	}

}
