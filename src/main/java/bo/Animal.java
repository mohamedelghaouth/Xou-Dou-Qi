package bo;

import java.util.ArrayList;

import javax.persistence.*;

import bl.EchequierMang;
import bl.ImpossibleDeplacementException;
import bo.Joueur;

@Entity(name = "Animal")
@Table(name = "Animal")
public abstract  class Animal {
	@Id
	 @GeneratedValue
	@Column(name = "Animal_id")
	protected Long id;
	protected int power;

	@OneToOne( targetEntity = Position.class,cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
	protected Position pos;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Joueur_id")
	protected Joueur j;
	

	public Animal(int power, Position pos,Joueur J) {
		this.power = power;
		this.pos = pos;
		this.j= J;
	}
	public Animal() {
	}


	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public Position getPos() {
		return pos;
	}
	public void setPos(Position pos) {
		this.pos = pos;
	}
	public Joueur getJ() {
		return j;
	}	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setJ(Joueur j) {
		this.j = j;
	}
	
	
	public abstract void ramenerpouvoir();
	
	public  Position getPositionAfterMoveIfPossible(Position P)throws ImpossibleDeplacementException {
		
		
		if (!EchequierMang.getInstance().isPosInEchequier(P)) {
			throw new ImpossibleDeplacementException("le deplacement en dehors du cadre est interdit");
		}
		
		// vérification des regles de déplacement de la piece
				this.isPossibleMove(P);

		Animal a = EchequierMang.getInstance().getAnimalAt(j.getE(),P);

		if((a != null)&&(a.getJ() == j)) 
		{
			throw new ImpossibleDeplacementException("on peur pas manger un coequipier");
		}
		if((a != null)&&(getPower() < a.getPower())) 
		{
			throw new ImpossibleDeplacementException("on peut pas manger un animal plus fort que l'animal selectioner");
		}

		
		// la nouvelle position
		return P;
	}

	public  void isPossibleMove(Position P) throws ImpossibleDeplacementException{
	
		//ces deux liste contiennent respectivement les indices des lignes 
		// et des colonnes des rivieres
	ArrayList<Integer> rline = new ArrayList<Integer>();	
	rline.add(3);
	rline.add(4);
	rline.add(5);
	ArrayList<Integer> rcolumn = new ArrayList<Integer>() ;	
	rcolumn.add(1);
	rcolumn.add(2);
	rcolumn.add(4);
	rcolumn.add(5);
	//interdir le deplacement diagonale 
	if(((Math.abs(P.getX()-pos.getX())!=0))&&(Math.abs(P.getY()-pos.getY())!=0)){
		throw new ImpossibleDeplacementException("le deplacement diagonale est interdit");
	}
	//interdir un deplacement superieure a un carreau
	if(((Math.abs(P.getX()-pos.getX())>1))|(Math.abs(P.getY()-pos.getY())>1)){
		throw new ImpossibleDeplacementException("un deplacement superieure a un carreau est interdit");
	}
	//interdir le deplacement a traver la riviere 
	if((rcolumn.contains(Integer.valueOf(P.getX())))
			&&(rline.contains(Integer.valueOf(P.getY()))))
			 {
		throw new ImpossibleDeplacementException("le deplacement a traver la riviere est interdit");
	}
	//interdir le deplacement  vers  le sanctaire

	if(j.getSanctuaire().equals(P)) {
		throw new ImpossibleDeplacementException("le deplacement  vers son propre  sanctaire est interdit");

	}
	}

	@Override
	public String toString() {
		return "Animal [power=" + power + ", pos=" + pos + ", j=" + j + "]";
	}
	
	public abstract String getStringRepresentation();
	
	
}
