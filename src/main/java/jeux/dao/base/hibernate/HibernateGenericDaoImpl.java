package jeux.dao.base.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jeux.core.dao.ObjectNotFoundException;
import jeux.dao.base.IGenericDao;

/**
 * Classe de base pour tous les DAOs, elle implémente les méthodes CRUD
 * génériques définit par le contrat GenericDAO<T>. Cette implémentation est
 * basée sur Hibernate nativement
 * 
 * @author <a href="mailto:tarik.boudaa@gmail.com">T.BOUDAA Ecole Nationale des
 *         Sciences Appliquées </a>
 * 
 * @param <T> le type d'objet métier manipulé
 * @param <PK> le type utilisé pour l'indentifiant d'un objet métier
 */
public abstract class HibernateGenericDaoImpl<T, PK extends Serializable> implements IGenericDao<T, PK> {

	/** La classe BO manipulé par le DAO */
	protected Class<T> boClass;

	/** Utilisé par tous les DAOs */
	protected final Logger LOGGER;

	/** référence l'unique instance de Session Factory */
	protected SessionFactory sf = SessionFactoryBuilder.getSessionFactory();

	public HibernateGenericDaoImpl(Class<T> pClass) {

		boClass = pClass;

		LOGGER = Logger.getLogger(boClass);

		LOGGER.debug("le dao de " + boClass + " a été initialisé");
	}

	/**
	 * Permet de persister l'objet passer en paramètre
	 * 
	 * @param pObject objet à persister
	 */
	public PK Creer(T pObject) {

		Session session = null;
		Transaction tx = null;
		PK id;
		try {

			// on obtient une session
			session = sf.getCurrentSession();

			// On commence une transaction
			tx = session.beginTransaction();

			// on execute les opérations bases de données

			// enregistrer un étudiant dans la base de données
			id = (PK) session.save(pObject);

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
		return id;
	}

	/**
	 * Méthode de mise à jour
	 * 
	 * @param pObj
	 */
	public void mod(T pObj) {

		Session session = null;
		Transaction tx = null;
		try {

			// on obtient une session
			session = sf.getCurrentSession();

			// On commence une transaction
			tx = session.beginTransaction();

			// on execute les opérations bases de données
			session.update(pObj);

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

	}

	/**
	 * Méthode pour la suppression d'un étudiant
	 * 
	 * @param pId : identifiant de l'entité à supprimer
	 * @throws ObjectNotFoundException 
	 */

	public void supp(PK pId) throws ObjectNotFoundException {

		Session session = null;
		Transaction tx = null;
		try {

			// on obtient une session
			session = sf.getCurrentSession();

			// On commence une transaction
			tx = session.beginTransaction();

			// on execute les opérations bases de données

			// Avec Hibernate pour supprimer une entité, il faut d'abord la charger de la
			// base de données vers la session (toutes les opérations sur les objets se font
			// via la session)
			T etd = (T) session.get(boClass, pId);

			// L'objet qu'est attaché à la session est supprimé (il devient transitoire,
			// l'enregistement associé en base de données sera donc supprimé)
			session.delete(etd);

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

	}
	public T findById(PK pId) throws ObjectNotFoundException {

		T etd = null;

		Session session = null;
		Transaction tx = null;

		try {

			// on obtient une session
			session = sf.getCurrentSession();

			// On commence une transaction
			tx = session.beginTransaction();

			// on execute les op�rations bases de donn�es

			// r�cup�rer l'entit� par son id
			etd = (T) session.get(boClass, pId);

			// On valide la transaction, ceci ferme �galement la session si on la r�cup�rer
			// avec getCurrentSession
			// si elle est r�cup�r� par openSession dans ce cas il faut appeler close de la
			// session explicitement
			tx.commit();
		} catch (HibernateException ex) {

			// Si il y a des probl�mes et une transaction a �t� d�j� cr�e on l'annule
			if (tx != null) {
				// Annulation d'une transaction
				tx.rollback();

			}

			// On n'oublie pas de remonter l'erreur originale
			throw ex;

		} finally {

			// Si la session n'est pas encore ferm�e par commit
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

		return etd;

	}

	
	public List<T> getAll(){
		List<T> etd = new ArrayList<T>();

		Session session = null;
		Transaction tx = null;

		try {

			// on obtient une session
			session = sf.getCurrentSession();

			// On commence une transaction
			tx = session.beginTransaction();

			// on execute les op�rations bases de donn�es

			// r�cup�rer l'entit� par son id
			etd =  session.createCriteria(boClass).list();

			// On valide la transaction, ceci ferme �galement la session si on la r�cup�rer
			// avec getCurrentSession
			// si elle est r�cup�r� par openSession dans ce cas il faut appeler close de la
			// session explicitement
			tx.commit();
		} catch (HibernateException ex) {

			// Si il y a des probl�mes et une transaction a �t� d�j� cr�e on l'annule
			if (tx != null) {
				// Annulation d'une transaction
				tx.rollback();

			}

			// On n'oublie pas de remonter l'erreur originale
			throw ex;

		} finally {

			// Si la session n'est pas encore ferm�e par commit
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

		return etd;
		
	}
	
	
	
}
