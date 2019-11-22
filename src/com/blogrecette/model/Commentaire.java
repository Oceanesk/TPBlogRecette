/**
 * 
 */
package com.blogrecette.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@Entity
@Table(name = "commentaire")
public class Commentaire {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "auteur")
    private String auteur;
     
    @Column(name="contenu")
    private String contenu;
    
   
    @Column(name="note")
    private int note;
     
    @Temporal(TemporalType.DATE)
    protected Date dateCreation;
    
    @ManyToOne 
    @JoinColumn(name="recette_id")
    private Recette recette;
    
    
    public Commentaire() {
    }



	/**
	 * @param id
	 * @param idRecette
	 * @param auteur
	 * @param contenu
	 * @param note
	 * @param dateCreation
	 */
	public Commentaire(Integer id, String auteur, String contenu, int note, Date dateCreation) {
		super();
		this.id = id;
		this.auteur = auteur;
		this.contenu = contenu;
		this.note = note;
		this.dateCreation = dateCreation;
	}



	/**
	 * @param idRecette
	 * @param auteur
	 * @param contenu
	 * @param note
	 * @param dateCreation
	 */
	public Commentaire( String auteur, String contenu, int note, Date dateCreation) {
		super();
	
		this.auteur = auteur;
		this.contenu = contenu;
		this.note = note;
		this.dateCreation = dateCreation;
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
	 * @return the auteur
	 */
	public String getAuteur() {
		return auteur;
	}



	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}



	/**
	 * @return the contenu
	 */
	public String getContenu() {
		return contenu;
	}



	/**
	 * @param contenu the contenu to set
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}



	/**
	 * @return the note
	 */
	public int getNote() {
		return note;
	}



	/**
	 * @param note the note to set
	 */
	public void setNote(int note) {
		this.note = note;
	}



	/**
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}



	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}



	@Override
	public String toString() {
		return "Commentaire [id=" + id +  ", auteur=" + auteur + ", contenu=" + contenu
				+ ", note=" + note + ", dateCreation=" + dateCreation + "]";
	}
    
    
    
    
}
