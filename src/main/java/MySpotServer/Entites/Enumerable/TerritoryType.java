package MySpotServer.Entites.Enumerable;

import javax.persistence.Entity;

/**
 * Created by Squirrel on 2017-11-24.
 */

public enum TerritoryType {
    Water(0),
    Gainable(1);

    private int id;

    private TerritoryType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
