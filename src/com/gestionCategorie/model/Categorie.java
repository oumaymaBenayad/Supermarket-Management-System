package com.gestionCategorie.model;


public class Categorie {
	
	  private int idCat; private String nomCat; private String descriptionCat;
	  
	  
	  public int getIdCat() { return idCat; }
	  
	  
	  public void setIdCat(int idCat) { this.idCat = idCat; } public String
	  getNomCat() { return nomCat; } public void setNomCat(String nomCat) {
	  this.nomCat = nomCat; } public String getDescriptionCat() { return
	  descriptionCat; } public void setDescriptionCat(String descriptionCat) {
	  this.descriptionCat = descriptionCat; }
	 
	
	
	  public Categorie(int idCat, String nomCat, String descriptionCat) {
	  this.idCat = idCat; this.nomCat = nomCat; this.descriptionCat =
	  descriptionCat; }
	 

	
    }




