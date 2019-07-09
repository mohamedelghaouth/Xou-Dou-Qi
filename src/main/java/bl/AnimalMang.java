package bl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bl.ImpossibleDeplacementException;
import bo.Animal;
import bo.Position;

public class AnimalMang implements IAnimalMang {

	  EchequierMang E; 
	  JoueurMang J=JoueurMang.getInstance();
	
	
	  private static AnimalMang staticInstance = null;

		/**
		 * Constructeur
		 */
		protected AnimalMang(EchequierMang pE) {
			// interdir l'instantiation volentairement
			E=pE;
		}

		/**
		 * Retourne l'unique instance de cette fabrique
		 */
		public static AnimalMang getInstance(EchequierMang pE) {

			if (staticInstance == null) {
				staticInstance = new AnimalMang(pE);
			}
			return staticInstance;
		}
	  
	  
	  
	public void sedeplace(Animal A,Position P) throws ImpossibleDeplacementException {
		
		//raméne lui son power
		A.ramenerpouvoir();
	
		// on obtient la position apres le déplacement, si ce déplacement est possible
		// pour la piece appelante
		// cette méthode fait un appel à la méthode isPossibleMove qui est propre à
		// chaque type de piece
		Position tempPos = A.getPositionAfterMoveIfPossible(P);

		// Si on arrive à ce point alors le déplacement est possible et il reste à
		// éliminer la piece adversaire si elle est dans tempPos

		
		
		Animal a = E.getAnimalAt(A.getJ().getE(), tempPos);

		if (a != null) {
			E.removeAnimal(A.getJ().getE(), a);;
		}
		
		
		//si un animal adversaire est dans une piege son power est 0
		if(J.getTheAutherOne(A.getJ()).getPiege().contains(tempPos))
		{
			A.setPower(0);
		}
		// mettre à jour la position de la piece
		A.setPos(tempPos);

		E.inverserTour(A.getJ().getE());

	}
	
	 
	public List<Position> getPossibleMoves(Animal A) {

		Position pos=A.getPos();
		List<Position> moves = new ArrayList<Position>();
		Position[] Direction=new Position[8];
		Direction[0]=new Position (pos.getX()+1,pos.getY());//deplacement horizontale :right
		Direction[1]=new Position (pos.getX()-1,pos.getY());//deplacement horizontale:left
		Direction[2]=new Position (pos.getX(),pos.getY()-1);//deplacement verticale:botom
		Direction[3]=new Position (pos.getX(),pos.getY()+1);//deplacement verticale:advence
		Direction[4]=new Position (pos.getX(),pos.getY()+4);//sotter la riviere verticalement vers l'avant
		Direction[5]=new Position (pos.getX(),pos.getY()-4);//sotter la riviere verticalement vers l'arriére
		Direction[6]=new Position (pos.getX()+3,pos.getY());//sotter la riviere horizontalement vers la gauche
		Direction[7]=new Position (pos.getX()-3,pos.getY());//sotter la riviere horizontalement vers la droit 
		for (Position it : Direction) {
			try {
				moves.add(A.getPositionAfterMoveIfPossible(it));

			} catch (ImpossibleDeplacementException ex) {
				// on ignore volentairement cette exception
				// si position illégale on ajoute pas le dépalcement dans la liste moves
			}
		}

		return moves;
	}
	
	
	public void randomDeplacement(Animal A) {

		// On obtient les déplacements possibles d'une piece, en prenant en compte les
		// regles de déplacement générales et les regles de chaque piece
		List<Position> possibleMoves = getPossibleMoves(A);
		
		int randomIndex =0;
		
		// Un entier par hazard dans l'intervale [0,possibleMoves.size()-1]
		 randomIndex = new Random().nextInt(possibleMoves.size());
		 
		// On traite le cas ou la case contient une piece adverse on l'élémine de
		// l'echequier
		Position temp = possibleMoves.get(randomIndex);
		Animal a = E.getAnimalAt(A.getJ().getE(),temp);
		// il contient une piece adverse
		if (a != null) {
			E.removeAnimal(A.getJ().getE(), a);
		}

		// on change la case de la piece
		A.setPos(temp);

		// On passe la main à l'autre joueur
		E.inverserTour(A.getJ().getE());

	}
	
	
}
