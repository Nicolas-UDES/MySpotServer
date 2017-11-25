package MySpotServer.DAO;

import MySpotServer.Entites.Marking;
import MySpotServer.Entites.Player;

import static MySpotServer.DAO.EntityManager.DATABASE;

public class MarkingDAO {

	public static void addMarking(Marking marking) throws Exception {
		try(EntityManager entityManager = new EntityManager(DATABASE)){
			entityManager.getTransaction().begin();

			entityManager.persist(marking);
			marking.getPlayer().setBlader(marking.getPlayer().getBlader() - marking.getAmount());

			entityManager.getTransaction().commit();
		}
	}

	public static Marking getMarking(int id) throws Exception {
		try(EntityManager entityManager = new EntityManager(DATABASE)){
			return entityManager.find(Marking.class, id);
		}
	}
}
