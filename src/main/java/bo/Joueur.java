package bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import bo.Echequier;

@Entity(name = "Joueur")
@Table(name = "Joueur")
public class Joueur {
	@Id
	 @GeneratedValue
	@Column(name = "Joueur_id")
	private Long  id;
	private int num;
	private String c;
	@OneToMany(mappedBy = "j",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Animal>l=new ArrayList<Animal>();
	@OneToOne( targetEntity = Position.class,cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
	private Position sanctuaire;
	private int score;
	@OneToOne
	private Echequier Echequier;
	private ArrayList<Position>piege=new ArrayList<Position>();
	
	public Joueur(int n,Echequier E) {
		
		this.num=n;
		this.Echequier=E;
		if(num==1) {
			sanctuaire=new Position(3,0);
			c="r";
			
			piege.add(new Position(2,0));
			piege.add(new Position(4,0));
			piege.add(new Position(3,1));
			
			l.add(new Rat(new Position(6,2),this));
			l.add(new chat(new Position(1,1),this));
			l.add(new Loup(new Position(2,2),this));
			l.add(new Chien(new Position(5,1),this));
			l.add(new Panthere(new Position(4,2),this));
			l.add(new Tigre(new Position(0,0),this));
			l.add(new Lion(new Position(6,0),this));
			l.add(new Elephant(new Position(0,2),this));
		}
		if(num==-1) {
			sanctuaire=new Position(3,8);
			c="n";

			piege.add(new Position(2,8));
			piege.add(new Position(4,8));
			piege.add(new Position(3,7));
			
			l.add(new Rat(new Position(0,6),this));
			l.add(new chat(new Position(5,7),this));
			l.add(new Loup(new Position(4,6),this));
			l.add(new Chien(new Position(1,7),this));
			l.add(new Panthere(new Position(2,6),this));
			l.add(new Tigre(new Position(6,8),this));
			l.add(new Lion(new Position(0,8),this));
			l.add(new Elephant(new Position(6,6),this));
		}
		score=0;
	}
			
	public Joueur() {
		
	}

	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public List<Animal> getL() {
		return l;
	}
	public void setL(List<Animal> l) {
		this.l = l;
	}
	public Position getSanctuaire() {
		return sanctuaire;
	}
	public void setSanctuaire(Position sanctuaire) {
		this.sanctuaire = sanctuaire;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Echequier getE() {
		return Echequier;
	}
	public void setE(Echequier e) {
		this.Echequier = e;
	}
	public ArrayList<Position> getPiege() {
		return piege;
	}
	public void setPiege(ArrayList<Position> piege) {
		this.piege = piege;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
