package MySpotServer.Entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Squirrel on 2017-11-20.
 */
@Entity
public class Territories implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private List<Territory> territories;

    public Territories() {
        this(new ArrayList<Territory>());
    }

    public Territories(ArrayList<Territory> territories) {
        this.territories = territories;
    }

    public void addAll(Territory ... territory){
        Collections.addAll(territories, territory);
    }

    public void add(Territory territory){
        territories.add(territory);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(ArrayList<Territory> territories) {
        this.territories = territories;
    }
}
