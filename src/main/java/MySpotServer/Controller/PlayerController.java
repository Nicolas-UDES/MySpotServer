package MySpotServer.Controller;

import MySpotServer.DAO.PlayerDAO;
import MySpotServer.Entites.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller("/player")
public class PlayerController {

	@GetMapping("/getPlayer/{playerId}")
	public @ResponseBody
	String getPlayer(@PathVariable("playerId") int playerId) throws Exception {
		Player player = PlayerDAO.getPlayer(playerId);
		if(player == null) {
			return "{}";
		}

		return new ObjectMapper().writeValueAsString(player);
	}

	@PostMapping("/createPlayer/{username}")
	public @ResponseBody
	String createPlayer(@PathVariable("username") String username) throws Exception {
		Player player = new Player(-1, username, 0.5, 0.25, 0.5, 1, null, new Date());
		PlayerDAO.addPlayer(player);

		return new ObjectMapper().writeValueAsString(player);
	}
}
