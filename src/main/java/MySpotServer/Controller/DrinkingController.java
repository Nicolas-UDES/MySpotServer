package MySpotServer.Controller;

import MySpotServer.DAO.DrinkingDAO;
import MySpotServer.DAO.PlayerDAO;
import MySpotServer.DAO.TerritoryDAO;
import MySpotServer.Entites.Drinking;
import MySpotServer.Entites.Player;
import MySpotServer.Entites.Territory;
import MySpotServer.Utility.Functions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller("drinking")
@RequestMapping("/drinking/")
public class DrinkingController {

	@GetMapping("/drinkInTerritory/{playerId}/{territoryId}/{amount}")
	public @ResponseBody String drinkInTerritory(@PathVariable("playerId") int playerId, @PathVariable("territoryId") int territoryId, @PathVariable("amount") double amount) throws Exception {
		Player player = PlayerDAO.getPlayer(playerId);
		Territory territory = TerritoryDAO.getTerritory(territoryId);

		amount = Math.min(amount, Functions.levelToBladerSize(player.getLevel()) - player.getStomach());
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
