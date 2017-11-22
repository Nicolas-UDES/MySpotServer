package MySpotServer.DAO;

import javax.persistence.*;

/**
 * @since 1.7
 */
public class EntityManager implements AutoCloseable {

	public final static String DATABASE = "$objectdb/db/points.odb";

	EntityManagerFactory emf;
	javax.persistence.EntityManager em;

	public EntityManager(String persistenceUnitName) {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		em = emf.createEntityManager();
	}

	public Query createQuery(String query){
		return em.createQuery(query);
	}

	public <T> TypedQuery<T> createQuery(String query, Class<T> clazz){
		return em.createQuery(query, clazz);
	}

	public EntityTransaction getTransaction(){
		return em.getTransaction();
	}

	public void persist(Object o){
		em.persist(o);
	}

	public <T> T find(Class<T> aClass, Object o){
		return em.find(aClass, o);
	}

	public void close() throws Exception {
		em.close();
		emf.close();
	}
}
