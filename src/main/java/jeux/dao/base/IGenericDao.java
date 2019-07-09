package jeux.dao.base;

import java.util.List;

import jeux.core.dao.ObjectNotFoundException;

/**
 * Interface des DAO génériques
 * 
 * @author Tarik BOUDAA
 *
 * @param <T,
 *            PK>
 */
public interface IGenericDao<T, PK> extends IDao {

	/**
	 * Méthode permettant la sauvegarde d'un objet de type T pasé en pramétre à la
	 * méthode
	 * 
	 * @param obj
	 *            l'objet à pérsister
	 * @return
	 */
	public abstract PK Creer(T obj);

	/**
	 * Méthode pour supprimer un objet dont l'id est passé en paramétre
	 * 
	 * @param id
	 *            l'identifiant de l'objet é supprimer
	 * @return
	 * @throws ObjectNotFoundException 
	 */
	public abstract void supp(PK id) throws ObjectNotFoundException;

	/**
	 * Méthode de mise à jour d'un objet passé en paramétre
	 * 
	 * @param obj
	 *            contient la nouvelle version de l'objet avec laquelle la mise é
	 *            jour sera effectuée
	 * @return
	 */
	public abstract void mod(T obj) ;

	/**
	 * Méthode pour rechercher une entité par son identifiant
	 * 
	 * @param id
	 *            identifiant de l'entité recherchée
	 * @return
	 */
	public abstract  List<T> getAll() ;

	public abstract T findById(PK pId) throws ObjectNotFoundException ;

	
}
