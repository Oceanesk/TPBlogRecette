/**
 * 
 */
package com.blogrecette.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 
@Entity
@Table(name = "ingredient")
public class Ingredient {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nom")
    private String nom;
     
    @Column(name="quantite")
    private double quantite;
    
   
    @Column(name="unit")
    private String unit;
     
    @ManyToOne 
    @JoinColumn(name="recette_id")
    private Recette recette;
    
    
    public Ingredient() {
    }



	/**
	 * @param id
	 * @param idRecette
	 * @param nom
	 * @param quantite
	 * @param unit
	 */
	public Ingredient(Integer id, String nom, double quantite, String unit) {
		super();
		this.id = id;
		this.nom = nom;
		this.quantite = quantite;
		this.unit = unit;
	}



	/**
	 * @param idRecette
	 * @param nom
	 * @param quantite
	 * @param unit
	 */
	public Ingredient( String nom, double quantite, String unit) {
		super();
		this.nom = nom;
		this.quantite = quantite;
		this.unit = unit;
	}



	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}






	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}



	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}



	/**
	 * @return the quantite
	 */
	public double getQuantite() {
		return quantite;
	}



	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}



	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}



	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}



	@Override
	public String toString() {
		return "Ingredient [id=" + id + ",  nom=" + nom + ", quantite=" + quantite
				+ ", unit=" + unit + "]";
	}
    
    
    
}
