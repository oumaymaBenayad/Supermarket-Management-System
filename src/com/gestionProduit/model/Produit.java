package com.gestionProduit.model;
import com.gestionCategorie.model.Categorie;

public class Produit {
	private int idProd;
	private String nomProd;
	private int prixProd;
	private int qteProd;
	private Categorie categorie;
	
	
	public int getIdProd() {
		return idProd;
	}
	public void setIdProd(int idProd) {
		this.idProd = idProd;
	}
	public String getNomProd() {
		return nomProd;
	}
	public void setNomProd(String nomProd) {
		this.nomProd = nomProd;
	}
	public int getPrixProd() {
		return prixProd;
	}
	public void setPrixProd(int prixProd) {
		this.prixProd = prixProd;
	}
	public int getQteProd() {
		return qteProd;
	}
	public void setQteProd(int qteProd) {
		this.qteProd = qteProd;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
	public Produit(int idProd, String nomProd, int prixProd, int qteProd, Categorie categorie) {
		super();
		this.idProd = idProd;
		this.nomProd = nomProd;
		this.prixProd = prixProd;
		this.qteProd = qteProd;
		this.categorie = categorie;
	}

}
