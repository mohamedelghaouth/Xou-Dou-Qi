package bl;

import java.util.List;

import bo.Animal;
import bo.Echequier;
import bo.Joueur;
import bo.Position;

public interface IEchequierMang {

	Joueur getJoueurAyantLeTour(Echequier e);

	void inverserTour(Echequier e);

	boolean sanqtuairesvide(Echequier e);

	boolean listnotempty(Echequier e);


	void removeAnimal(Echequier e, Animal a);


	/**
	 * Permet de déplacer aléatoirement l'une des piece passées en parametre
	 * 
	 * @param lPieces
	 */
	void randomDeplacement(Echequier e, List<Animal> lPieces);

	/**
	 * Permet de déplacer aléatoirement l'une des pieces du joueur ayant la main
	 */
	void randomDeplacement(Echequier e);

	/**
	 * Permet de lancer une partie de jeu d'une façon aléatoire
	 */
	void randomGame(Echequier e);
	
	public void incrementer(Echequier e);

	Animal getAnimalAt(Echequier e, Position p);

}