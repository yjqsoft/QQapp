package org.table;

/**
 * Gamedetails entity. @author MyEclipse Persistence Tools
 */

public class Gamedetails implements java.io.Serializable {

	// Fields

	private Integer id;
	private String gametype;
	private String hosttime;
	private String hostplace;
	private String applytime;
	private String organizer;
	private String activeobject;
	private String games;
	private String phone;

	// Constructors

	/** default constructor */
	public Gamedetails() {
	}

	/** minimal constructor */
	public Gamedetails(String gametype, String hosttime, String hostplace, String applytime) {
		this.gametype = gametype;
		this.hosttime = hosttime;
		this.hostplace = hostplace;
		this.applytime = applytime;
	}

	/** full constructor */
	public Gamedetails(String gametype, String hosttime, String hostplace, String applytime, String organizer,
			String activeobject, String games, String phone) {
		this.gametype = gametype;
		this.hosttime = hosttime;
		this.hostplace = hostplace;
		this.applytime = applytime;
		this.organizer = organizer;
		this.activeobject = activeobject;
		this.games = games;
		this.phone = phone;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGametype() {
		return this.gametype;
	}

	public void setGametype(String gametype) {
		this.gametype = gametype;
	}

	public String getHosttime() {
		return this.hosttime;
	}

	public void setHosttime(String hosttime) {
		this.hosttime = hosttime;
	}

	public String getHostplace() {
		return this.hostplace;
	}

	public void setHostplace(String hostplace) {
		this.hostplace = hostplace;
	}

	public String getApplytime() {
		return this.applytime;
	}

	public void setApplytime(String applytime) {
		this.applytime = applytime;
	}

	public String getOrganizer() {
		return this.organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getActiveobject() {
		return this.activeobject;
	}

	public void setActiveobject(String activeobject) {
		this.activeobject = activeobject;
	}

	public String getGames() {
		return this.games;
	}

	public void setGames(String games) {
		this.games = games;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}