package bl;

import java.util.List;
import java.util.Random;

import bo.Animal;
import bo.Echequier;
import bo.Joueur;
import bo.Position;
import gui.Fenetre;

public class EchequierMang implements IEchequierMang   {

	  static JoueurMang J=JoueurMang.getInstance();
	  AnimalMang A=AnimalMang.getInstance(this);

	  
		private static EchequierMang staticInstance = null;

		/**
		 * Constructeur
		 */
		protected EchequierMang() {
			// interdir l'instantiation volentairement
		}

		/**
		 * Retourne l'unique instance de cette fabrique
		 */
		public static EchequierMang getInstance() {

			if (staticInstance == null) {
				staticInstance = new EchequierMang();
			}
			return staticInstance;
		}
	  
	
	public Joueur getJoueurAyantLeTour(Echequier e) {
		
		return (e.getJ1().getNum()==e.getTour())?e.getJ1():e.getJ2();	 
	}

	
	public void inverserTour(Echequier e) {
		e.setTour(-(e.getTour())); 
	}

	public void incrementer(Echequier e) {
		e.setNmbiter(e.getNmbiter()+1); 
	}
	 
	public boolean sanqtuairesvide(Echequier e){
		return (J.sanqtuairesvide(e.getJ1()))&&(J.sanqtuairesvide(e.getJ2()));
	}

	 
	public boolean listnotempty(Echequier e){
		return (J.listnotempty(e.getJ1()))&&(J.listnotempty(e.getJ2()));
	}
	
	 
	public   Animal getAnimalAt(Echequier e,Position P) {
		
		Animal a=J.getAnimalAt(e.getJ1(),P);
		Animal b=J.getAnimalAt(e.getJ2(),P);
		
		return (a!=null)?a:b;
		
	}
	
	
	public void removeAnimal(Echequier e,Animal a) {
		
		Joueur j1 =e.getJ1();
		Joueur j2 =e.getJ2();
		
		//on commence par chercher a quelle joueur appartient l'animal
		if (a.getJ() == j1) {
			J.IncrimenteScor(j2);;// Mise à jour du score
			J.removeAnimal(j1, a);;//on enléve a de la liste de j1
		} else if (a.getJ() == j2) {
			J.IncrimenteScor(j1);;// Mise à jour du score
			J.removeAnimal(j2, a);;//on enléve a de la liste de j2
		}

	}
	
	
	public static   boolean isPosInEchequier(Position p) {

		return (p.getX() >= 0 && p.getX() <= 6) && (p.getY() >= 0 && p.getY() <= 8);

	}

	
	 public void randomDeplacement(Echequier e,List<Animal> lPieces) {
		Animal a = lPieces.get(new Random().nextInt(lPieces.size()));
		if(A.getPossibleMoves(a).size()!=0)
		{
			A.randomDeplacement(a);
			e.setNmbiter(e.getNmbiter()+1);;
		}else
		{
			randomDeplacement(e,lPieces);
		}

	}

	
	 public void randomDeplacement(Echequier e) {

		List<Animal> lPieces =getJoueurAyantLeTour(e).getL();

		Animal a = lPieces.get(new Random().nextInt(lPieces.size()));
		if(A.getPossibleMoves(a).size()!=0)
		{
			A.randomDeplacement(a);
			e.setNmbiter(e.getNmbiter()+1);;
		}else
		{
			randomDeplacement(e);
		}

	}
	
	
	 public void randomGame(Echequier e) {
		Fenetre.showEchequier(e);

		while (true) {

			List<Animal> lPieces = getJoueurAyantLeTour(e).getL();

			if (lPieces.size() == 0) {
				break;
			}

			randomDeplacement(e,lPieces);

			// attente de 3 secondes
			try {
				Thread.sleep(3000);
			} catch (InterruptedException E) {
			}
			Fenetre.showEchequier(e);

		}

	}
	
	
	
}
