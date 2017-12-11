package MySpotServer.DAO;

import static MySpotServer.DAO.EntityManager.DATABASE;
import MySpotLibrary.Entites.*;

import javax.persistence.Query;
import java.util.List;

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
    public static List<Marking> getMarkingsByTerritory(Territory territory) throws Exception {
        try (EntityManager em = new EntityManager(DATABASE)){
            Query q = em.createQuery("SELECT m FROM marking WHERE m.locationId = :locationId");
            q.setParameter("locationId",territory.getId());
            return q.getResultList();
        }
    }

    public static Marking getMarkingByTerritory(long id) throws Exception {
        try (EntityManager em = new EntityManager(DATABASE)){
            Query q = em.createQuery("SELECT m FROM marking WHERE m.locationId = :locationId");
            q.setParameter("locationId",id);
            List<Marking> result = q.getResultList();
            return result.get(result.size()-1);
        }
    }

}
