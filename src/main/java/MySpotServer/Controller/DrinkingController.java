package MySpotServer.Controller;

import MySpotLibrary.BLL.PlayerBLL;
import MySpotServer.DAO.DrinkingDAO;
import MySpotServer.DAO.PlayerDAO;
import MySpotServer.DAO.TerritoryDAO;
import MySpotServer.Utility.Functions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import MySpotLibrary.Entites.*;
import java.util.Date;
import java.util.List;

@Controller("drinking")
@RequestMapping("/drinking/")
public class DrinkingController {

	@PostMapping("/drinkInTerritory/{playerId}/{territoryId}/{delay}")
	public @ResponseBody String drinkInTerritory(@PathVariable("playerId") long playerId, @PathVariable("territoryId") long territoryId, @PathVariable("delay") long delay) throws Exception {
		Player player = PlayerDAO.getPlayer(playerId);
		Territory territory = TerritoryDAO.getTerritory(territoryId);

		double time = PlayerBLL.timeNeededToDrinkInSeconds(player.getLevel()) * 1000;
		double percent = Math.min(1.0, delay / time);

		double amount = Math.min(Functions.levelToBladerSize(player.getLevel()) * percent, Functions.levelToBladerSize(player.getLevel()) - player.getStomach());
		Drinking drinking = new Drinking(0, amount, new Date(), territory, player);
		DrinkingDAO.addDrinking(drinking);

		return new ObjectMapper().writeValueAsString(drinking);
	}

	@GetMapping("/getNonEmpty/{playerId}")
	public @ResponseBody String getNonEmpty(@PathVariable("playerId") int playerId) throws Exception {

		List<Drinking> drinkings = DrinkingDAO.getNonEmptyDrinking(playerId);
		return new ObjectMapper().writeValueAsString(drinkings);
	}
}
