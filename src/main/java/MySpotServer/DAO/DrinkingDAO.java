package MySpotServer.DAO;

import javax.persistence.TypedQuery;
import MySpotLibrary.Entites.*;
import java.util.List;

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

	public static List<Drinking> getNonEmptyDrinking(int playerId) throws Exception {
		try(EntityManager entityManager = new EntityManager(DATABASE)){
			TypedQuery<Drinking> query = entityManager.createQuery("SELECT d FROM Drinking d, Player p WHERE p.id = :playerId AND d MEMBER OF p.drinks AND d.emptied = false", Drinking.class);
			query.setParameter("playerId", playerId);
			return query.getResultList();
		}
	}
}
