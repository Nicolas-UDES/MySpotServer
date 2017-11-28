package MySpotServer.Controller;

import MySpotServer.DAO.PlayerDAO;
import MySpotServer.Entites.Player;
import MySpotServer.Utility.Functions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller("player")
@RequestMapping("/player/")
public class PlayerController {

	@GetMapping("/get/{playerId}")
	public @ResponseBody
	String getPlayer(@PathVariable("playerId") int playerId) throws Exception {
		Player player = PlayerDAO.getPlayer(playerId);
		if(player == null) {
			return "{}";
		}

		return new ObjectMapper().writeValueAsString(player);
	}

	@PostMapping("/create/{username}")
	public @ResponseBody
	String createPlayer(@PathVariable("username") String username) throws Exception {
		double bladerSize = Functions.levelToBladerSize(1);

		Player player = new Player(0, username, 0.5 * bladerSize, 0.25 * bladerSize, 1, 1, null, new Date());
		PlayerDAO.addPlayer(player);
		player.getDrinks().get(0).setPlayer(null);

		return new ObjectMapper().writeValueAsString(player);
	}
}
