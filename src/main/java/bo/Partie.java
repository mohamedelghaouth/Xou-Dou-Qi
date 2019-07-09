package bo;

import javax.persistence.*;

@Entity(name = "Partie")
@Table(name = "Partie")
public class Partie {

	 @Id
	 @GeneratedValue
	private Long id;
	
	private String nom;
	
	private int type;

	@OneToOne( cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
	 @JoinColumn(name="echequier")
	private   Echequier echequier ;

	public Partie(String nom, Echequier echequier) {
		this.nom = nom;
		this.echequier = echequier;
	}
	
	public Partie() {
	}
	
	public Partie(String nom) {
		this.nom = nom;
		this.echequier = new Echequier(1);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Echequier getEchequier() {
		return echequier;
	}

	public void setEchequier(Echequier echequier) {
		this.echequier = echequier;
	}

	
	public int getType() {
		return type;
	}

	
	public void setType(int type) {
		this.type = type;
	}

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	
}
