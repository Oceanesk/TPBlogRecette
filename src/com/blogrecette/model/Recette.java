package com.blogrecette.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
 
@Entity
@Table(name = "recette")
public class Recette {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

     
    @Column(name = "titre")
    private String titre;
     
    @Column(name="description")
    private String description;
    
   
    @Column(name="photo")
    private String photo;
     
    @Temporal(TemporalType.DATE)
    protected Date dateCreation;

    @ManyToOne
	@JoinColumn(name = "membre_id")
	private Membre membre;
   
    @ManyToOne
    @JoinColumn(name= "categorie_id")
    private Categorie categorie;
    

    @OneToMany(mappedBy = "recette")
    private Collection <Commentaire>commentaires;
    
    @OneToMany(mappedBy = "recette")
    private Collection<Ingredient>ingredients; 
    
 
	@ManyToMany()
	private Collection<Tag> tags;
	
	@Transient
	private int moyenneNote;
	
	public Collection<Tag>getTag(){
		return tags;
		
	}
	
	
	public void addTag(Tag tag) {
		this.tags.add(tag);
		tag.addRecette(this);
	}

	public void removeTag(Tag tag) {
		 tags.remove(tag);
		 tag.removeRecette(this);
	}
	
    
    
    /*@ManyToOne
    private Membre membre;
    */
    
    
    public Recette() {
    }

	/**
	 * @param id
	 * @param idMembre
	 * @param idCategorie
	 * @param titre
	 * @param description
	 * @param photo
	 * @param dateCreation
	 */
	public Recette(Integer id, String titre, String description, String photo,
			Date dateCreation) {
		super();
		this.id = id;
		
		this.titre = titre;
		this.description = description;
		this.photo = photo;
		this.dateCreation = dateCreation;
	}



	/**
	 * @param idMembre
	 * @param idCategorie
	 * @param titre
	 * @param description
	 * @param photo
	 * @param dateCreation
	 */
	public Recette( String titre, String description, String photo, Date dateCreation) {
		super();
		this.titre = titre;
		this.description = description;
		this.photo = photo;
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
	 * @return the idMembre
	 */


	
	public Membre getMembre() {
		return membre;
		
	}
	
	
	public void setMembre(Membre membre) {
		this.membre = membre;
	}
	
	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}



	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}



	/**
	 * @return the descritption
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param descritption the descritption to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}



	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
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
		return "Recette [id=" + id +  ", titre=" + titre
				+ ", descritption=" + description + ", photo=" + photo + ", dateCreation=" + dateCreation + "]";
	}


	public void setMoyenneNote(int moyenneNote) {
		this.moyenneNote =moyenneNote;
	
	}
    
    
}