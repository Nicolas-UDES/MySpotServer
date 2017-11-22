package MySpotServer.Entites;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Squirrel on 2017-11-20.
 */
@Entity
public class Territory implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<LatLng> positions;

    public Territory() {
        this(new ArrayList<LatLng>());
    }

    public Territory(List<LatLng> positions) {
        this.positions = positions;
    }

    public void addAll(LatLng ... latLngs){
        Collections.addAll(positions, latLngs);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<LatLng> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<LatLng> positions) {
        this.positions = positions;
    }
}
