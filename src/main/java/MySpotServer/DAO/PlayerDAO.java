package MySpotServer.DAO;

import MySpotServer.Entites.LatLng;
import MySpotServer.Entites.Player;
import MySpotServer.Entites.Territory;

import static MySpotServer.DAO.EntityManager.DATABASE;

public class PlayerDAO {

	public static void addPlayer(Player player) throws Exception {
		try(EntityManager entityManager = new EntityManager(DATABASE)){
			entityManager.getTransaction().begin();
			entityManager.persist(player);
			entityManager.getTransaction().commit();
		}
	}

	public static Player getPlayer(int id) throws Exception {
		try(EntityManager entityManager = new EntityManager(DATABASE)){
			return entityManager.find(Player.class, id);
		}
	}
}
