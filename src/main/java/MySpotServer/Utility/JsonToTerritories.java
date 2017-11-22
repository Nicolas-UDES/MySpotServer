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
					territory.getPositions().add(new LatLng(point.get(0), point.get(1)));
				}
			}
			territories.add(territory);
		}

		mergeTerritories(territories);
		TerritoryDAO.AddTerritories(territories);
	}

	public static void mergeTerritories(List<Territory> territories) {
		for(Territory territory : territories) {
			for(LatLng latLng : territory.getPositions()) {
				for(Territory subTerritory : territories) {
					for(LatLng subLatLng : subTerritory.getPositions()) {
						if(latLng.distance(subLatLng) < 1.0) {
							subLatLng.setLat(latLng.getLat());
							subLatLng.setLng(latLng.getLng());
						}
					}
				}
			}
		}
	}
}
