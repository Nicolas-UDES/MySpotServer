package MySpotServer.Controller;

import MySpotServer.DAO.TerritoryDAO;

import MySpotServer.Utility.JsonToTerritories;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/mapCreation")
public class MapCreationController {

	@GetMapping("/readJson")
	public @ResponseBody String greeting() throws Exception {
		JsonToTerritories.readJson();
		List<MySpotServer.Entites.Territory> result = TerritoryDAO.GetAllTerritories();
		return new ObjectMapper().writeValueAsString(result);
	}
}
