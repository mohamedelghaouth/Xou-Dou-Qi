package bo;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import bl.AnimalMang;
import bl.ImpossibleDeplacementException;
import bo.Animal;
import bo.Joueur;
import bo.Position;

@Entity(name = "Echequier")
@Table(name = "Echequier")
public class Echequier {

	@Id
	 @GeneratedValue
	 @Column(name = "Echequier_id")
	private Long id;
	
	  @OneToOne( cascade = CascadeType.ALL)
	private Joueur j1;
	  @OneToOne( cascade = CascadeType.ALL)
	private Joueur j2;
	private int tour=1;
	int nmbiter;
	
	public Echequier(Joueur j1, Joueur j2) {
		this.j1 = j1;
		this.j2 = j2;
		nmbiter=0;
	}
	public Echequier(int tour) {
		this.j1 = new Joueur(tour,this);
		this.j2 = new Joueur(-tour,this);
		nmbiter=0;
	}
	
	public Echequier() {
		super();
	}
	public Joueur getJ1() {
		return j1;
	}
	public void setJ1(Joueur j1) {
		this.j1 = j1;
	}
	public Joueur getJ2() {
		return j2;
	}
	public void setJ2(Joueur j2) {
		this.j2 = j2;
	}
	public int getTour() {
		return tour;
	}
	public void setTour(int tour) {
		this.tour = tour;
	}	
	public int getNmbiter() {
		return nmbiter;
	}
	public void setNmbiter(int nmbiter) {
		this.nmbiter = nmbiter;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
