package jeux.core.dao;



import org.apache.log4j.Logger;

import jeux.core.config.ConfigurationLoader;
import jeux.dao.base.PartieDao;

/**
 * Cette classe fabrique les fabriques des DAOs (Factory of Factories)
 * 
 * @author boudaa
 *
 */
public abstract class AbstractDaoFactory {

	private static final Logger LOGGER = Logger.getLogger(AbstractDaoFactory.class);

	public static final String JDBC = "jdbc";
	public static final String ORM = "hibernate";

	public abstract PartieDao getPartieDao();

	

	/**
	 * Méthode permettant de récupérer les Factory
	 * 
	 * @return
	 */
	public static AbstractDaoFactory getFactory() {

		// lire la configuration avec:
		//ConfigurationLoader.getProperties().getProperty("dao.type")
		//ResourceBundle.getBundle("configuration").getString("dao.type")
		String daoType =ConfigurationLoader.getProperties().getProperty("dao.type") ;
		LOGGER.info("### L'application utilise une configuration de type " + daoType + "###");
		System.out.println("### L'application utilise une configuration de type " + daoType + "###");

		// selon la configuration, soit on va instancier la fabrique de type ORM ou de
		// type JDBC
		
		if (ORM.equals(daoType)) {

			return new HibernateDaoFactory();

		}

		throw new RuntimeException("mauvaise configuration de l'application");

	}

}