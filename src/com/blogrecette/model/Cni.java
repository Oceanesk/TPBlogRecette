

package com.blogrecette.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
 
@Entity
@Table(name = "cni")
public class Cni {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false) 
    private Integer id;
     
    @Column(name = "nom")
    private String nom;
    
    @Column(name = "prenom")
    private String prenom;
    
    @Column(name = "numero")
    private int numero;
    
	@OneToOne    //pour le bidirectionnel
	private Membre membre;
    
    public Cni() {
    }

	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param numero
	 */
	public Cni(Integer id, String nom, String prenom, int numero) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.numero = numero;
	}

	/**
	 * @param nom
	 * @param prenom
	 * @param numero
	 */
	public Cni(String nom, String prenom, int numero) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.numero = numero;
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
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Membre getMembre() {
		return membre;
	}
	
	public void setMembre(Membre membre) {
		this.membre = membre;
	}
	
	@Override
	public String toString() {
		return "Cni [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", numero=" + numero + "]";
	}
    
    
    
    
    
}

