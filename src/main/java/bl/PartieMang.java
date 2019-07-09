package bl;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import bo.Animal;
import bo.Echequier;
import bo.Partie;
import bo.Joueur;
import bo.Position;
import gui.Fenetre;
import jeux.core.dao.AbstractDaoFactory;
import jeux.core.dao.ObjectNotFoundException;
import jeux.dao.base.PartieDao;

public class PartieMang implements IPartieMang {
	

	  EchequierMang E=EchequierMang.getInstance(); 
	 
	 private  PartieDao d =AbstractDaoFactory.getFactory().getPartieDao();
	
	 
	 
	 /** l'unique instance de cette classe * */
		private static PartieMang staticInstance = null;

		/**
		 * Constructeur
		 */
		protected PartieMang() {
			// interdir l'instantiation volentairement
		}

		/**
		 * Retourne l'unique instance de cette fabrique
		 */
		public static PartieMang getInstance() {

			if (staticInstance == null) {
				staticInstance = new PartieMang();
			}
			return staticInstance;
		}
	 
	 
	 
	 
	 
	
	public  boolean gamenotover(Partie j){
		Echequier echequier =j.getEchequier();
		boolean Q1=true;
		boolean Q2=true;
		Q1=E.sanqtuairesvide(echequier);
		Q2=E.listnotempty(echequier);
		return Q1&&Q2;
	}
	
	
	public  int getgagnant(Partie j){
		
		Echequier echequier =j.getEchequier();
		
		int i=0;
		if(!E.sanqtuairesvide(echequier)) {
			 i=(E.getAnimalAt(echequier,echequier.getJ1().getSanctuaire())==null)?1:2;
		}
		if(!E.listnotempty(echequier)) {
			 i=(echequier.getJ1().getL().size()!=0)?1:2;
		}
		return i;
	}
	
	
	public  void raisondeperte(Partie j) {
		Echequier echequier =j.getEchequier();

		int i=0;
		if(!E.sanqtuairesvide(echequier)) {
			 i=(E.getAnimalAt(echequier,echequier.getJ1().getSanctuaire())!=null)?1:2;
				System.out.println("le sanctuaire du joueur "+i+" est penetrer");
		}
		if(!E.listnotempty(echequier)) {
			 i=(echequier.getJ1().getL().size()==0)?1:2;
			 System.out.println("le joueur "+i+" n'a plus de pieces");
		}
	}
	
	
	public  void afficherresultat(Partie j) {
		Echequier echequier =j.getEchequier();
		System.out.println("apres "+echequier.getNmbiter()+" iteration le score est");
		System.out.println("Score joueur1  : "  + echequier.getJ1().getScore());
		System.out.println("Score joueur2  : "  + echequier.getJ2().getScore());
		raisondeperte(j);
		System.out.println("Le gangnat est le joueur numero : "+ getgagnant(j));
	}

	
	public  List<String> Chercher(String p) {
		return null;	
		}

	
	public  List<Partie> getALL() {
		return d.getAll();	
		}
	
	public  Long savePartie(Partie p) {
			return d.Creer(p);	
	}
	
	
	public  Partie getPartie(Long p) throws ObjectNotFoundException  {
			return d.findById(p);
			
		}

	
	public  void deletePartie(Long p) throws ObjectNotFoundException {
		 d.supp(p);	
		}
	
}
