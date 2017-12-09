package MySpotServer.DAO;

import javax.persistence.TypedQuery;
import java.util.List;
import MySpotLibrary.Entites.*;

import static MySpotServer.DAO.EntityManager.DATABASE;

public class TerritoryDAO {

	public static void addTerritories(List<Territory> territories) throws Exception {
		try(EntityManager entityManager = new EntityManager(DATABASE)){
			entityManager.getTransaction().begin();
			for(Territory territory : territories) {
				for(GeoPos geoPos : territory.getPositions()) {
					entityManager.persist(geoPos);
				}
				entityManager.persist(territory.getCenter());
				entityManager.persist(territory);
			}
			entityManager.getTransaction().commit();
		}
	}

	public static Territory getTerritory(long id) throws Exception {
		try(EntityManager entityManager = new EntityManager(DATABASE)){
			return entityManager.find(Territory.class, id);
		}
	}

	public static List<Territory> getAllTerritories() throws Exception {
		try(EntityManager entityManager = new EntityManager(DATABASE)){
			TypedQuery<Territory> query = entityManager.createQuery("SELECT t FROM Territory t", Territory.class);
			return query.getResultList();
		}
	}
}
