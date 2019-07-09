package bo;


import javax.persistence.Entity;

import bl.EchequierMang;
import bl.ImpossibleDeplacementException;
import bo.Echequier;

@Entity(name = "Elephant")

public class Elephant extends Animal {
	 
		public static int pow=8;

		public Elephant(Position pos, Joueur J) {
			super(pow, pos, J);
		}

		public Elephant() {
			
		}

		public void ramenerpouvoir() {
			this.setPower(pow);
		}

		public String getStringRepresentation() {
			return "E"+getJ().getC();
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
			if((a != null)&&(a.getPower()==1)) 
			{
				throw new ImpossibleDeplacementException("un elephant ne peut pas manger un Rat");
			}
			
			// la nouvelle position
			return P;
		}
		
		
	}
