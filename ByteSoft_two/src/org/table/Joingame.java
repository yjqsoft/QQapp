package org.table;

/**
 * Joingame entity. @author MyEclipse Persistence Tools
 */

public class Joingame implements java.io.Serializable {

	// Fields

	private Integer no;
	private String userName;
	private String userPhone;
	private String gamePlace;
	private String gameName;
	private String gameType;

	// Constructors

	/** default constructor */
	public Joingame() {
	}

	/** full constructor */
	public Joingame(String userName, String userPhone, String gamePlace, String gameName, String gameType) {
		this.userName = userName;
		this.userPhone = userPhone;
		this.gamePlace = gamePlace;
		this.gameName = gameName;
		this.gameType = gameType;
	}

	// Property accessors

	public Integer getNo() {
		return this.no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getGamePlace() {
		return this.gamePlace;
	}

	public void setGamePlace(String gamePlace) {
		this.gamePlace = gamePlace;
	}

	public String getGameName() {
		return this.gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameType() {
		return this.gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

}