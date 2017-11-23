package MySpotServer.Utility;

import MySpotServer.DAO.TerritoryDAO;
import MySpotServer.Entites.GmapGIS.Feature;
import MySpotServer.Entites.GmapGIS.GmapGIS;
import MySpotServer.Entites.LatLng;
import MySpotServer.Entites.Territory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonToTerritories {

	private static final String PATH = "D:\\Users\\Squirrel\\Documents\\Git\\MySpot\\app\\data\\GmapGIS.json";

	public static void readJson() throws Exception {
		GmapGIS gmapGIS = new ObjectMapper().readValue(new File(PATH), GmapGIS.class);

		List<Territory> territories = new ArrayList<>();
		for(Feature feature : gmapGIS.getFeatures()) {
			Territory territory = new Territory();
			for(ArrayList<ArrayList<Double>> polygon : feature.getGeometry().getCoordinates()) {
				for(ArrayList<Double> point : polygon) {
					territory.getPositions().add(new LatLng((float)(double)point.get(1), (float)(double)point.get(0)));
				}
			}
			territories.add(territory);
		}

		mergeTerritories(territories, 10.0);
		cleanDuplicates(territories);
		TerritoryDAO.AddTerritories(territories);
	}

	/**
	 * Merges points which's distance is bellow the one requested.
	 * @param territories
	 * @param minDistanceInMeter
	 */
	public static void mergeTerritories(List<Territory> territories, double minDistanceInMeter) {
		for (int a1 = 0; a1 < territories.size(); a1++) {
			List<LatLng> positions1 = territories.get(a1).getPositions();

			for (int b1 = 0; b1 < positions1.size(); b1++) {
				LatLng latLng1 = positions1.get(b1);

				for (int a2 = a1; a2 < territories.size(); a2++) {
					List<LatLng> positions2 = territories.get(a2).getPositions();

					for (int b2 = 0; b2 < positions2.size(); b2++) {
						LatLng latLng2 = positions2.get(b2);

						if (latLng1.distance(latLng2) < minDistanceInMeter) {
							latLng2.setLatitude(latLng1.getLatitude());
							latLng2.setLongitude(latLng1.getLongitude());
						}
					}
				}
			}
		}
	}

	/**
	 * Removes useless points within a same territory.
	 * @param territories
	 */
	public static void cleanDuplicates(List<Territory> territories) {
		for (Territory territory : territories) {
			List<LatLng> positions1 = territory.getPositions();
			for (int a = 0; a < positions1.size(); a++) {
				List<LatLng> positions2 = territory.getPositions();
				for (int b = 0; b < positions2.size(); b++) {
					if(a == b) {
						continue;
					}

					LatLng latLng1 = positions1.get(a);
					LatLng latLng2 = positions2.get(b);
					if(latLng1.equals(latLng2)) {
						positions2.remove(b);
						--b;
					}
				}
			}
		}
	}
}
