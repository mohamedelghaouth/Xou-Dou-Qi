package bo;

import java.util.ArrayList;

import javax.persistence.Entity;

import bl.EchequierMang;
import bl.ImpossibleDeplacementException;
import bo.Echequier;

@Entity(name = "Rat")
public class Rat extends Animal {
	 
		public static int pow=1;

		public Rat(Position pos, Joueur J) {
			super(pow, pos, J);
		}

		public Rat() {
			
		}

		public void ramenerpouvoir() {
			this.setPower(pow);
		}

		public String getStringRepresentation() {
			return "R"+getJ().getC();
		}

		public boolean NotReadyToKill() {
			ArrayList<Integer> rline = new ArrayList<Integer>();	
			rline.add(3);
			rline.add(4);
			rline.add(5);
			ArrayList<Integer> rcolumn = new ArrayList<Integer>() ;	
			rcolumn.add(1);
			rcolumn.add(2);
			rcolumn.add(4);
			rcolumn.add(5);
			
			if((rcolumn.contains(Integer.valueOf(pos.getX())))&&
					(rline.contains(Integer.valueOf(pos.getY())))) {
				return true;
			}
			else 	return false;

		}

		public Position getPositionAfterMoveIfPossible(Position P)throws ImpossibleDeplacementException {
			if (!EchequierMang.isPosInEchequier(P)) {
				throw new ImpossibleDeplacementException("le deplacement en dehors du cadre est interdit");
			}
			
			// vérification des regles de déplacement de la piece
					this.isPossibleMove(P);

			Animal a = EchequierMang.getInstance().getAnimalAt(j.getE(),P);

			if((a != null)&&(a.getJ() == j)) 
			{
				throw new ImpossibleDeplacementException("on peur pas manger un coequipier");
			}
			if((a != null)&&((getPower() < a.getPower())
					&&(a.getPower()<8))) 
			{
				throw new ImpossibleDeplacementException("on peut pas manger un animal plus fort que l'animal selectioner");
			}
			if((a != null)&&(((a.getPower()==8) & (NotReadyToKill())))) 
			{
				throw new ImpossibleDeplacementException("un rat ne peut pas manger à la sortie du riviere");
			}
			
			// la nouvelle position
			return P;
		}

		public  void isPossibleMove(Position P) throws ImpossibleDeplacementException{
		//interdir le deplacement diagonale 
		if(((Math.abs(P.getX()-pos.getX())!=0))&&(Math.abs(P.getY()-pos.getY())!=0)){
			throw new ImpossibleDeplacementException("le deplacement diagonale est interdit");
		}
		//interdir un deplacement superieure a un carreau
		if(((Math.abs(P.getX()-pos.getX())>1))||(Math.abs(P.getY()-pos.getY())>1)){
			throw new ImpossibleDeplacementException("un deplacement superieure a un carreau est interdit");
		}
		//interdir le deplacement vers  le sanctaire seulement
		if((j.getSanctuaire().equals(P))) {
			throw new ImpossibleDeplacementException("le deplacement  vers son propre le sanctaire est interdit");
		}
		}
				
	}
