package MySpotServer.Entites;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Marking implements Serializable {

	@Id
	@GeneratedValue
	private int id;

	private Date date;

	private double amount;

	private double strength;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Territory location;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Player player;

	public Marking() {
	}

	public Marking(int id, Date date, double amount, double strength, Territory location, Player player) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.strength = strength;
		this.location = location;
		this.player = player;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getStrength() {
		return strength;
	}

	public void setStrength(double strength) {
		this.strength = strength;
	}

	public Territory getLocation() {
		return location;
	}

	public void setLocation(Territory location) {
		this.location = location;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
