package MySpotServer.DAO;

import MySpotServer.Entites.Drinking;

import static MySpotServer.DAO.EntityManager.DATABASE;

public class DrinkingDAO {

	public static void addDrinking(Drinking drinking) throws Exception {
		try(EntityManager entityManager = new EntityManager(DATABASE)){
			entityManager.getTransaction().begin();

			entityManager.persist(drinking);
			drinking.getPlayer().setStomach(drinking.getPlayer().getStomach() + drinking.getAmount());

			entityManager.getTransaction().commit();
		}
	}

	public static Drinking getDrinking(int id) throws Exception {
		try(EntityManager entityManager = new EntityManager(DATABASE)){
			return entityManager.find(Drinking.class, id);
		}
	}
}
