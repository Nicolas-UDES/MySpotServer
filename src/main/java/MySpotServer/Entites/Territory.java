package MySpotServer.Entites;

import MySpotServer.Entites.Enumerable.TerritoryType;

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

    private TerritoryType territoryType;

    private LatLng center;

    private String name;

    private List<Mark> marks;

    public Territory() {
        this(new ArrayList<LatLng>());
    }

    public Territory(List<LatLng> positions) {
        this.positions = positions;
        marks = new ArrayList<>();
    }

    public Territory(long id, TerritoryType territoryType, List<LatLng> positions) {
        this(positions);
        this.id = id;
        this.territoryType = territoryType;
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

    public void setPositions(List<LatLng> positions) {
        this.positions = positions;
    }

    public TerritoryType getTerritoryType() {
        return territoryType;
    }

    public void setTerritoryType(TerritoryType territoryType) {
        this.territoryType = territoryType;
    }

    public LatLng getCenter() {
        return center;
    }

    public void setCenter(LatLng center) {
        this.center = center;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }
}
