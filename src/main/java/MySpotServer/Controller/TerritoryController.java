package MySpotServer.Controller;

import MySpotLibrary.BLL.TerritoryBLL;
import MySpotServer.DAO.MarkingDAO;
import MySpotServer.DAO.PlayerDAO;
import MySpotServer.DAO.TerritoryDAO;

import MySpotServer.Utility.JsonToTerritories;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
			territory.setOwnedById(TerritoryBLL.getOwner(territory));
			territory.setOwnedBy(PlayerDAO.getPlayer(territory.getOwnedById()));
            territory.setMarkings(MarkingDAO.getMarkingsByTerritory(territory));
        }
		return new ObjectMapper().writeValueAsString(result);
	}

	@GetMapping("/getOwner/{territoryId}")
	public @ResponseBody String getOwner(@PathVariable("territoryId") int territoryId) throws Exception{

	    Player player = MarkingDAO.getMarkingByTerritory(territoryId).getPlayer();
		return new ObjectMapper().writeValueAsString(player);
	}
}
