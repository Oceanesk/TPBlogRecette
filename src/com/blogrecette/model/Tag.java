/**
 * 
 */
package com.blogrecette.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author HB
 *
 */
@Entity
@Table(name = "tag")
public class Tag {



	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Integer id;

	@Column(name = "nom")
	private String nom;

	@ManyToMany(mappedBy = "tags")
	private Collection<Recette> recettes;


	public Tag() {
		// TODO Auto-generated constructor stub
	}

	public Tag(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}


	/**
	 * @param nom
	 * @param recettes
	 */
	public Tag(String nom, Collection<Recette> recettes) {
		super();
		this.nom = nom;
		this.recettes = recettes;
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
	 * @param recettes the recettes to set
	 */
	public void setRecettes(Collection<Recette> recettes) {
		this.recettes = recettes;
	}


	public Collection<Recette>getRecettes(){
		return recettes;

	}


	public void addRecette(Recette recette) {
		this.recettes.add(recette);
		recette.addTag(this);
	}

	public void removeRecette(Recette recette) {
		recettes.remove(recette);
		recette.removeTag(this);
	}



	@Override
	public String toString() {
		return "Tag [id=" + id + ", nom=" + nom + "]";
	}


}
