package MySpotServer.Controller;

import MySpotServer.DAO.MarkingDAO;
import MySpotServer.DAO.TerritoryDAO;

import MySpotServer.Utility.JsonToTerritories;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import MySpotLibrary.Entites.*;
import java.util.List;

@Controller("territory")
@RequestMapping("/territory/")
public class TerritoryController {

	@PostMapping("/readJson")
	public @ResponseBody String readJson() throws Exception {
		JsonToTerritories.readJson();
		return "Done!";
	}

	@GetMapping("/getAll")
	public @ResponseBody String getTerritories() throws Exception {
		List<Territory> result = TerritoryDAO.getAllTerritories();
		for (Territory territory :result){
            territory.setMarkings(MarkingDAO.getMarkingsByTerritory(territory));
        }
		return new ObjectMapper().writeValueAsString(result);
	}
}
