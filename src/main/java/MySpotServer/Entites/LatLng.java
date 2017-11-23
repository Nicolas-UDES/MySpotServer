package MySpotServer.Entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class LatLng implements Serializable {

	public final static int PRECISION = 100000;

	@Id
	@GeneratedValue
	private long id;

	public Float latitude;

	public Float longitude;

	public LatLng() {
	}

	public LatLng(Float latitude, Float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double distance(LatLng latLng) {
		double earthRadius = 3958.75;
		double latDiff = Math.toRadians(latLng.getLatitude() - getLatitude());
		double lngDiff = Math.toRadians(latLng.getLongitude() - getLongitude());
		double a = Math.sin(latDiff /2) * Math.sin(latDiff /2) +
				Math.cos(Math.toRadians(getLatitude())) * Math.cos(Math.toRadians(latLng.getLatitude())) *
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
		return (int)(getLatitude() * PRECISION) == (int)(latLng.getLatitude() * PRECISION) &&
				(int)(getLongitude() * PRECISION) == (int)(latLng.getLongitude() * PRECISION);
	}

	@Override
	public int hashCode() {
		return new Integer((int) (getLatitude() * PRECISION)).hashCode() ^
				new Integer((int) (getLongitude() * PRECISION)).hashCode();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
}
