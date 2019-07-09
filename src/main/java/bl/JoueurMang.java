package bl;

import bo.Animal;
import bo.Joueur;
import bo.Position;

public class JoueurMang implements IJoueyrMang {

	
	 
	 /** l'unique instance de cette classe * */
		private static JoueurMang staticInstance = null;

		/**
		 * Constructeur
		 */
		protected JoueurMang() {
			// interdir l'instantiation volentairement
		}

		/**
		 * Retourne l'unique instance de cette fabrique
		 */
		public static JoueurMang getInstance() {

			if (staticInstance == null) {
				staticInstance = new JoueurMang();
			}
			return staticInstance;
		}
	
	
	
	public Joueur getTheAutherOne(Joueur j) {
		
		return (j.getE().getJ1()!=j)?j.getE().getJ1():j.getE().getJ2();	
	}
	
	public Animal getAnimalWithLabel( Joueur j,String label) {
		for (Animal it : j.getL()) {

			if ((it.getStringRepresentation()).equals(label)) {

				return it;
			}
		}

		return null;
	}
	
	public boolean listnotempty(Joueur j){
		return (j.getL().size()!=0);
	}

	public Animal getAnimalAt(Joueur j,Position P) {
		for (Animal a : j.getL()) {
			if (a.getPos().equals(P))
				return a;
		}
		
		return null;
	}

	public void IncrimenteScor(Joueur j) {
		j.setScore(j.getScore()+1);
	}

	public void removeAnimal(Joueur j,Animal a) {
		j.getL().remove(a);
	}
	
	public boolean sanqtuairesvide(Joueur j){
		boolean Q1=true;
		boolean Q2=true;
		
		Joueur j1 = getTheAutherOne(j);
		
		Q1=getAnimalAt(j,j.getSanctuaire())==null;
		Q2=getAnimalAt(j1,j.getSanctuaire())==null;
		
		return Q1&&Q2;
	}

	public String getStringRepresentation(Joueur j) {
		 return j.getC();
	}
	
}
