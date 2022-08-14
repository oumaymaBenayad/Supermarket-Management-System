package com.gestionVente.gui;

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
import java.awt.print.PrinterException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.gestionCategorie.model.Categorie;
import com.gestionProduit.model.Produit;
import com.login.gui.LoginGUI;

import database.DB;

public class VenteGUI extends JFrame {
	JFrame fVente;
	JPanel panelbuttons;
	JScrollPane paneltable;
	JPanel panelbill;
	JTextField tnomProd;
	JTextField tqteProd;
	JTextField tsearch;
	JTextArea tbill;
	JLabel lblnomProd;
	JLabel lblqteProdt;
	JLabel lbltotal;
	

	private JButton btnajoutbill;
	private JButton btnrechercher;
	private JButton btnactualiser;
	private JButton btnvider;
	private JButton btnprint;
	private JButton btnlogout;

	JTable tproduits;
	String[] row;
	String[] cols = { "IdProd", "NomProd", "QteProd", "PrixProd", "CatProd"};
	
	Produit p;
	
	public void SelectProduit()
	{
		try {
			DB.connect();

			DB.ps = DB.conn.prepareStatement("select * from produit");
			DB.rs = DB.ps.executeQuery();
			DefaultTableModel tblmodel = new DefaultTableModel();
			tblmodel.setColumnIdentifiers(cols);
			tproduits.setModel(tblmodel);
			while (DB.rs.next()) {
				int id = DB.rs.getInt("idProd");
				String nom = DB.rs.getString("nomProd");
				int qte = DB.rs.getInt("qteProd");
				int prix = DB.rs.getInt("prixProd");
				String cat = DB.rs.getString("catProd"); ;
				tproduits.setModel(tblmodel);
				tblmodel.addRow(new Object[] {id, nom, qte, prix, cat });

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	 
	 
	 int Prid,newQte;
	 public void update()
	 {
	       newQte = dispoQte - Integer.valueOf(tqteProd.getText());
	     try{
	                DB.connect();
	                String sql ="Update produit set qteProd="+newQte+""+" where idProd="+Prid;
	                DB.ps = DB.conn.prepareStatement (sql);
			    	DB.ps.executeUpdate(sql);
	                SelectProduit();
	                
	           }catch(SQLException e)
	           {
	               e.printStackTrace();
	           }
	 }
	 
	 Double Uprice,ProdTot=0.0,GrdTotal=0.0;
	 int dispoQte;
	 


	  
	
	public VenteGUI() {
		
		fVente = new JFrame("Point de vente");
		fVente.setLayout(new GridLayout(3, 0));

		panelbill = new JPanel(new GridBagLayout());
		panelbill.setBackground(Color.DARK_GRAY);
		 

		panelbuttons = new JPanel(new GridBagLayout());
		panelbuttons.setBackground(Color.WHITE);
		
		
		tnomProd = new JTextField(10);
		tnomProd.setMargin(new Insets(2, 2, 2, 30));
		Border mrg = new EmptyBorder(5, 10, 5, 10);
		Border line = BorderFactory.createCompoundBorder(mrg, tnomProd.getBorder());
		tnomProd.setBorder(line);
		tnomProd.setBackground(Color.WHITE);
		tnomProd.setFont(new Font("Century Gothic", Font.BOLD, 14));
		tnomProd.setForeground(Color.DARK_GRAY);
		panelbuttons.add(tnomProd);

		lblnomProd = new JLabel("NOM");
		lblnomProd.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblnomProd.setForeground(Color.DARK_GRAY);
		panelbuttons.add(lblnomProd);

		lblqteProdt = new JLabel("QUANTITE");
		lblqteProdt.setForeground(Color.DARK_GRAY);
		lblqteProdt.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbuttons.add(lblqteProdt);

		tqteProd = new JTextField(10);
		tqteProd.setMargin(new Insets(2, 2, 2, 30));
		Border mrg1 = new EmptyBorder(5, 0, 5, 0);
		Border line1 = BorderFactory.createCompoundBorder(mrg1, tqteProd.getBorder());
		tqteProd.setBorder(line1);
		tqteProd.setForeground(Color.DARK_GRAY);
		tqteProd.setBackground(Color.WHITE);
		tqteProd.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbuttons.add(tqteProd);

		tsearch = new JTextField(10);
		tsearch.setMargin(new Insets(2, 2, 2, 30));
		Border mrg2 = new EmptyBorder(5, 0, 5, 0);
		Border line2 = BorderFactory.createCompoundBorder(mrg2, tsearch.getBorder());
		tsearch.setBorder(line2);
		tsearch.setBackground(Color.WHITE);
		tsearch.setFont(new Font("Century Gothic", Font.BOLD, 14));
		tsearch.setForeground(Color.DARK_GRAY);
		panelbuttons.add(tsearch);

		btnajoutbill = new JButton("ajout dans facture");
		btnajoutbill.setBackground(new Color(255, 204, 0));
		btnajoutbill.setForeground(Color.DARK_GRAY);
		btnajoutbill.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbuttons.add(btnajoutbill);

		btnvider = new JButton("vider");
		btnvider.setBackground(new Color(255, 204, 0));
		btnvider.setForeground(Color.DARK_GRAY);
		btnvider.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbuttons.add(btnvider);

		btnrechercher = new JButton("rechercher");
		btnrechercher.setBackground(new Color(255, 204, 0));
		btnrechercher.setForeground(Color.DARK_GRAY);
		btnrechercher.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbuttons.add(btnrechercher);

		btnactualiser = new JButton("actualiser");
		btnactualiser.setBackground(new Color(255, 204, 0));
		btnactualiser.setForeground(Color.DARK_GRAY);
		btnactualiser.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbuttons.add(btnactualiser);

		tproduits = new JTable();
		tproduits.setBackground(Color.WHITE);
		tproduits.setPreferredSize(new Dimension(150, 232));
		
		tbill = new JTextArea(8,30);
		tbill.setAlignmentX(TOP_ALIGNMENT);
		tbill.setBackground(Color.WHITE);
		tbill.setFont(new Font("Century Gothic", Font.BOLD, 14));
		tbill.setForeground(Color.DARK_GRAY);
		panelbill.add(tbill);
		
		btnprint = new JButton("imprimer");
		btnprint.setBackground(new Color(255, 204, 0));
		btnprint.setForeground(Color.DARK_GRAY);
		btnprint.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbill.add(btnprint);
		
		btnlogout = new JButton("logout");
		btnlogout.setBackground(new Color(255, 204, 0));
		btnlogout.setForeground(Color.DARK_GRAY);
		btnlogout.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panelbill.add(btnlogout);
		
		lbltotal = new JLabel("Total");
		lbltotal.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lbltotal.setForeground(Color.WHITE);
		panelbill.add(lbltotal);

		paneltable = new JScrollPane(tproduits);
		paneltable.setBorder(BorderFactory.createTitledBorder(null, "Liste des produits", TitledBorder.CENTER, 0,
				new Font("Century Gothic", Font.BOLD, 16), Color.DARK_GRAY));
		paneltable.setBackground(new Color(255, 204, 0));
		
		
	
		tproduits.addMouseListener (new MouseAdapter() {
			@Override
		public void mouseClicked(MouseEvent e) {
				
		int viewRow = tproduits.getSelectedRow();
		int modelRow = tproduits.convertRowIndexToModel(viewRow);
	
        Prid = Integer.valueOf(tproduits.getModel().getValueAt(modelRow, 0).toString());
		tnomProd.setText(tproduits.getModel().getValueAt(modelRow, 1).toString());
		dispoQte = Integer.valueOf(tproduits.getModel().getValueAt(modelRow, 2).toString());
		Uprice = Double.valueOf(tproduits.getModel().getValueAt(modelRow, 3).toString());
        
		}
		});
		
		
		btnajoutbill.addActionListener(new ActionListener() {
			int i = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(tqteProd.getText().isEmpty()||tnomProd.getText().isEmpty())
			    {
			        JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
			    }
			else if(dispoQte <= Integer.valueOf(tqteProd.getText()))
			{
			    JOptionPane.showMessageDialog(null, "N'est pas suffisant dans le stock");
			}
			else{
			    i++;
			    ProdTot = Uprice * Double.valueOf(tqteProd.getText());
			    GrdTotal = GrdTotal+ProdTot;
			    if(i == 1)
			    {
			        tbill.setText(tbill.getText()+"         "+"================IYO SHOP================\n"+"         "+"NUM     PRODUIT        PRIX       QUANTITE    TOTAL\n"+"             "+i+"        "+tnomProd.getText()+"        "+Uprice+"            "+tqteProd.getText()+"             "+ProdTot+"\n");
			    }
			    else{       
			    	tbill.setText(tbill.getText()+"             "+i+"        "+tnomProd.getText()+"        "+Uprice+"            "+tqteProd.getText()+"             "+ProdTot+"\n");
			    }
			    lbltotal.setText("Total = "+GrdTotal+" DH");
			    update();
				
			}
			}
		});
		
		
		
		btnvider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tnomProd.setText("");
				tqteProd.setText("");
				
			}
		});
		
		btnrechercher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 TableRowSorter<TableModel> rowSorter= new TableRowSorter<>(tproduits.getModel());
				 tproduits.setRowSorter(rowSorter);
				 String text = tsearch.getText();
				  if (text.trim().length() == 0) {
				     rowSorter.setRowFilter(null);
				  } else {
				     rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				  }
				
			}
		});
		
		
		
		btnactualiser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
		        try {
					DB.connect();
					DB.ps = DB.conn.prepareStatement("select * from produit");
					DB.rs = DB.ps.executeQuery();
					DefaultTableModel model = new DefaultTableModel(null, cols);
					tproduits.setModel(model); 
					TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tproduits.getModel());
					tproduits.setRowSorter(sorter);
					while (DB.rs.next()) {
						int id = DB.rs.getInt("idProd");
						String nom = DB.rs.getString("nomProd");
						int qte = DB.rs.getInt("qteProd");
						int prix = DB.rs.getInt("prixProd");
						String cat = DB.rs.getString("catProd"); ;
						tproduits.setModel(model);
						model.addRow(new Object[] {id, nom, qte, prix, cat });

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				
			}
		});
		
		btnprint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 try {
					tbill.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		btnlogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
       			fVente.dispose();
				
			}
		});
		
		
		
		
		
		
		GridBagConstraints c = new GridBagConstraints();


		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 50, 0, 0);
		panelbuttons.add(lblnomProd, c);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		panelbuttons.add(tnomProd, c);

		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0, 50, 0, 0);
		panelbuttons.add(lblqteProdt, c);

		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 0, 0);
		panelbuttons.add(tqteProd, c);

		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(20, 0, 0, 0);
		panelbuttons.add(tsearch, c);

		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(20, 0, 0, 0);
		panelbuttons.add(btnrechercher, c);
		
		c.gridx = 2;
		c.gridy = 4;
		c.insets = new Insets(20, 0, 0, 0);
		panelbuttons.add(btnactualiser, c);
		

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(20, 0, 0, 0);
		panelbuttons.add(btnajoutbill, c);

		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(20, 0, 0, 0);
		panelbuttons.add(btnvider, c);
		
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(20, 0, 0, 0);
		panelbuttons.add(btnlogout, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		panelbill.add(tbill, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 0, 0);
		panelbill.add(lbltotal, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 0);
		panelbill.add(btnprint, c);

		

		

		fVente.add(panelbuttons, BorderLayout.NORTH);
		fVente.add(paneltable, BorderLayout.CENTER);
		fVente.add(panelbill, BorderLayout.SOUTH);
        
		fVente.setSize(720, 660);
		fVente.setLocationRelativeTo(null);
		fVente.setVisible(true);
		fVente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
	}


	
	
	public static void main(String[] args) {
		new VenteGUI();
		

	}











































	
}
