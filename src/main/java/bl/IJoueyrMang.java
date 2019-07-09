package bl;

import bo.Animal;
import bo.Joueur;
import bo.Position;

public interface IJoueyrMang {

	Joueur getTheAutherOne(Joueur j);

	Animal getAnimalWithLabel(Joueur j, String label);

	boolean listnotempty(Joueur j);

	Animal getAnimalAt(Joueur j, Position P);

	boolean sanqtuairesvide(Joueur j);

	String getStringRepresentation(Joueur j);

}