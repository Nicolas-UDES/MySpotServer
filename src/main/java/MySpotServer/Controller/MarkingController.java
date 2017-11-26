package MySpotServer.Controller;

import MySpotServer.DAO.MarkingDAO;
import MySpotServer.DAO.PlayerDAO;
import MySpotServer.DAO.TerritoryDAO;
import MySpotServer.Entites.Marking;
import MySpotServer.Entites.Player;
import MySpotServer.Entites.Territory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller("marking")
@RequestMapping("/marking/")
public class MarkingController {

	@GetMapping("/markTerritory/{playerId}/{territoryId}/{amount}")
	public @ResponseBody String markTerritory(@PathVariable("playerId") int playerId, @PathVariable("territoryId") int territoryId, @PathVariable("amount") double amount) throws Exception {
		Player player = PlayerDAO.getPlayer(playerId);
		Territory territory = TerritoryDAO.getTerritory(territoryId);

		amount = Math.min(amount, player.getBlader());
		Marking marking = new Marking(-1, new Date(), amount, player.getUrineStrength(), territory, player);
		MarkingDAO.addMarking(marking);

		return new ObjectMapper().writeValueAsString(marking);
	}
}
