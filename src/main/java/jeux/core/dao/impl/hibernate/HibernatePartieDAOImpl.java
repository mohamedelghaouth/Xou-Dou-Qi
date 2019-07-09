package jeux.core.dao.impl.hibernate;



import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bo.Partie;
import jeux.dao.base.PartieDao;
import jeux.dao.base.hibernate.HibernateGenericDaoImpl;

/**
 * Classe d'implémentation d'un DAO pour Client
 * 
 * @author Tarik BOUDAA
 * 
 */
public class HibernatePartieDAOImpl extends HibernateGenericDaoImpl<Partie, Long> implements PartieDao {

	public HibernatePartieDAOImpl() {
		super(Partie.class);

	}

	public List<String> Chercher(String nom)  {
		Session session = null;
		Transaction tx = null;
		List<String> list = null;
		try {

			// on obtient une session
			session = sf.getCurrentSession();
			

			// On commence une transaction
			tx = session.beginTransaction();
			// Compatibe Hibernate 5.x
						
			
			// Requete HQL paramétrée pour chercher un étudiant par son nom
			String hqlQuery = "select nom "
					+"from Partie c "+
					
					"where c.nom LIKE :nom ";

			// Préparer la requete
			//Query<Object> query 
			list= session.createQuery(hqlQuery).setParameter("nom", "%"+nom+"%").list();

			// definir le paramètre de la requete (le nom)
			//query.setParameter("nom", "%"+nom+"%");

			// executer et récupérer les résultats sous forme d'une lise d'étudiants
			//list = query.getResultList();

			// On valide la transaction, ceci ferme également la session si on la récupérer
			// avec getCurrentSession
			// si elle est récupéré par openSession dans ce cas il faut appeler close de la
			// session explicitement
			tx.commit();
			
		} catch (HibernateException ex) {

			// Si il y a des problèmes et une transaction a été déjà crée on l'annule
			if (tx != null) {
				// Annulation d'une transaction
				tx.rollback();

			}
			// On n'oublie pas de remonter l'erreur originale
			throw ex;

		} finally {

			// Si la session n'est pas encore fermée par commit
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

		return list;

	
	}
	
	
}
