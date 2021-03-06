package MySpotServer.Controller;

import MySpotLibrary.BLL.PlayerBLL;
import MySpotLibrary.Entites.*;
import MySpotServer.DAO.MarkingDAO;
import MySpotServer.DAO.PlayerDAO;
import MySpotServer.DAO.TerritoryDAO;
import MySpotServer.Utility.Functions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller("marking")
@RequestMapping("/marking/")
public class MarkingController {

	@GetMapping("/markTerritory/{playerId}/{territoryId}/{amount}")
	public @ResponseBody String markTerritory(@PathVariable("playerId") int playerId, @PathVariable("territoryId") int territoryId, @PathVariable("amount") double amount) throws Exception {
		Player player = PlayerDAO.getPlayer(playerId);
		Territory territory = TerritoryDAO.getTerritory(territoryId);

		amount = Math.min(amount, player.getBlader());
		Marking marking = new Marking(0, new Date(), amount, player.getUrineStrength(), territory, player);
		MarkingDAO.addMarking(marking);

		return new ObjectMapper().writeValueAsString(marking);
	}

	@PostMapping("/addMark/{playerId}/{territoryId}/{delay}")
	public @ResponseBody String addMark(@PathVariable("playerId") long playerId, @PathVariable("territoryId") long territoryId, @PathVariable("delay") long delay) throws Exception {
		Player player = PlayerDAO.getPlayer(playerId);
		Territory territory = TerritoryDAO.getTerritory(territoryId);

		double strength = player.getUrineStrength();
		double percent = Math.min(1.0, delay);

		double amount = Math.min(Functions.levelToBladerSize(player.getLevel()) * percent, Functions.levelToBladerSize(player.getLevel()) - player.getStomach());
		Marking marking = new Marking(0, new Date(), amount, strength, territory, player);
		MarkingDAO.addMarking(marking);

		return new ObjectMapper().writeValueAsString(marking);
	}
}
