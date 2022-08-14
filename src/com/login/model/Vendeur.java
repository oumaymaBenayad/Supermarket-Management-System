package com.login.model;

public class Vendeur extends Utilisateur {
	private String genre;


	public Vendeur(int id, String nom, String password, String genre) {
		super(id, nom, password);
		this.setGenre(genre);
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}

}
