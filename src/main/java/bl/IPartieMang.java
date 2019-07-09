package bl;

import java.util.List;

import bo.Partie;
import jeux.core.dao.ObjectNotFoundException;

public interface IPartieMang {

	boolean gamenotover(Partie j);

	int getgagnant(Partie j);

	void raisondeperte(Partie j);

	void afficherresultat(Partie j);

	List<String> Chercher(String p);

	List<Partie> getALL();

	Long savePartie(Partie p);

	Partie getPartie(Long p) throws ObjectNotFoundException;

	void deletePartie(Long p) throws ObjectNotFoundException;

}