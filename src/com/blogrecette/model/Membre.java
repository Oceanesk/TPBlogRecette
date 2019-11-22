/**
 * 
 */
package com.blogrecette.model;

import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

import com.blogrecette.services.MembreService;

@Entity
@Table(name = "membre")
public class Membre {

	@Id  //clé primaire
	@GeneratedValue(strategy = GenerationType.IDENTITY) //BDD mySQl qui auto incremente l'id
	@Column(name = "id") 
	private Integer id;

	@Column(name = "nom")
	private String nom;

	@Column(name="pseudo")
	private String pseudo;


	@Column(name="email")
	private String email;

	@Temporal(TemporalType.DATE)
	protected Date dateInscription;


	@Column(name="mdp")
	private String mdp;


	//@OneToOne(optional = false) //unidirectionel = possibilité de recupérer ma cni depuis le memmbre
//	private Cni cni;

	
	@OneToOne(mappedBy="membre") //bi directionnel 
	private Cni cni;
	
	
	
	
    @OneToMany(mappedBy = "membre") //quand il y a "many" il faut "collection"
    private Collection <Recette> recettes;
    //collection = pas besoin de getter et setter mais de constructeurs
	
	
	
	
	
	
	public Membre() {
		this.recettes = new ArrayList<Recette>();
	}


	
	/**
	 * @param id
	 * @param nom
	 * @param pseudo
	 * @param email
	 * @param dateInscription
	 * @param mdp
	 */
	public Membre(Integer id, String nom, String pseudo, String email, Date dateInscription, String mdp) {
		super();
		this.id = id;
		this.nom = nom;
		this.pseudo = pseudo;
		this.email = email;
		this.dateInscription = dateInscription;
		this.mdp = mdp;

	}


	/**
	 * @param nom
	 * @param pseudo
	 * @param email
	 * @param dateInscription
	 * @param mdp
	 */
	public Membre(String nom, String pseudo, String email, Date dateInscription, String mdp) {
		super();
		this.nom = nom;
		this.pseudo = pseudo;
		this.email = email;
		this.dateInscription = dateInscription;
		this.mdp = mdp;

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
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}


	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the dateInscription
	 */
	public Date getDateInscription() {
		return dateInscription;
	}


	/**
	 * @param dateInscription the dateInscription to set
	 */
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}


	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}


	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}



	public Cni getCni() {
		return cni;
	}
	public void setCni(Cni cni) {
		this.cni = cni;
	}
	


	public void addRecette(Recette recette) {  //ajouter une recette
		this.recettes.add(recette);  //Ajouter la recette dans toute les recettes
		recette.setMembre(this); //Attribuer à l
		
	}
	
	public void removeRecette(Recette recette) { 
		recettes.remove(recette);
		recette.setMembre(null);
		
	}
	

	
	@Override
	public String toString() {
		return "Membre [id=" + id + ", nom=" + nom + ", pseudo=" + pseudo + ", email=" + email + ", dateInscription="
				+ dateInscription + ", mdp=" + mdp + "]";
	}



}

