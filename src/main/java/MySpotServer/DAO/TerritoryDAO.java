package MySpotServer.DAO;

import MySpotServer.Entites.LatLng;
import MySpotServer.Entites.Territory;

import javax.persistence.TypedQuery;
import java.util.List;

import static MySpotServer.DAO.EntityManager.DATABASE;

public class TerritoryDAO {

	public static void AddTerritories(List<Territory> territories) throws Exception {
		try(EntityManager entityManager = new EntityManager(DATABASE)){
			entityManager.getTransaction().begin();
			for(Territory territory : territories) {
				for(LatLng latLng : territory.getPositions()) {
					entityManager.persist(latLng);
				}
				entityManager.persist(territory);
			}
			entityManager.getTransaction().commit();
		}
	}

	public static Territory GetTerritory(int id) throws Exception {
		try(EntityManager entityManager = new EntityManager(DATABASE)){
			return entityManager.find(Territory.class, id);
		}
	}

	public static List<Territory> GetAllTerritories() throws Exception {
		try(EntityManager entityManager = new EntityManager(DATABASE)){
			TypedQuery<Territory> query = entityManager.createQuery("SELECT t FROM Territory t", Territory.class);
			return query.getResultList();
		}
	}
}
