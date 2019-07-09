package jeux.core.dao;

import jeux.core.dao.impl.hibernate.HibernatePartieDAOImpl;
import jeux.dao.base.PartieDao;

/**
 * Fabrique des dao hibernate
 * 
 * @author boudaa
 *
 */
public class HibernateDaoFactory extends AbstractDaoFactory {

	/** l'unique instance de cette classe * */
	private static HibernateDaoFactory staticInstance = null;

	/**
	 * Constructeur
	 */
	protected HibernateDaoFactory() {
		// interdir l'instantiation volentairement
	}

	/**
	 * Retourne l'unique instance de DaoFactory
	 */
	public static AbstractDaoFactory getInstance() {

		if (staticInstance == null) {
			staticInstance = new HibernateDaoFactory();
		}
		return staticInstance;
	}

	public PartieDao getPartieDao() {
		 return new HibernatePartieDAOImpl();

	}


}
