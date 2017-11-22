package MySpotServer.Entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
public class LatLng implements Serializable {

	public final static int PRECISION = 100000;

	@Id
	@GeneratedValue
	private long id;

	private Double lat;

	private Double lng;

	public LatLng() {
	}

	public LatLng(Double lat, Double lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public double distance(LatLng latLng) {
		double earthRadius = 3958.75;
		double latDiff = Math.toRadians(latLng.getLat() - getLat());
		double lngDiff = Math.toRadians(latLng.getLng() - getLng());
		double a = Math.sin(latDiff /2) * Math.sin(latDiff /2) +
				Math.cos(Math.toRadians(getLat())) * Math.cos(Math.toRadians(latLng.getLat())) *
						Math.sin(lngDiff /2) * Math.sin(lngDiff /2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = earthRadius * c;

		int meterConversion = 1609;

		return distance * meterConversion;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof LatLng)) {
			return false;
		}

		return equals((LatLng)obj);
	}

	public boolean equals(LatLng latLng) {
		return (int)(getLat() * PRECISION) == (int)(latLng.getLat() * PRECISION) &&
				(int)(getLng() * PRECISION) == (int)(latLng.getLng() * PRECISION);
	}

	@Override
	public int hashCode() {
		return new Integer((int) (getLat() * PRECISION)).hashCode() ^
				new Integer((int) (getLng() * PRECISION)).hashCode();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}
}
