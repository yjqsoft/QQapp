package org.table;

import java.sql.Timestamp;

/**
 * Game entity. @author MyEclipse Persistence Tools
 */

public class Game implements java.io.Serializable {

	// Fields

	private Integer id;
	private String gameType;
	private String gameTitle;
	private Timestamp time;
	private String picture;
	private String content;
	private Integer gamedetailsid;

	// Constructors

	/** default constructor */
	public Game() {
	}

	/** full constructor */
	public Game(String gameType, String gameTitle, Timestamp time, String picture, String content,
			Integer gamedetailsid) {
		this.gameType = gameType;
		this.gameTitle = gameTitle;
		this.time = time;
		this.picture = picture;
		this.content = content;
		this.gamedetailsid = gamedetailsid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGameType() {
		return this.gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getGameTitle() {
		return this.gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getGamedetailsid() {
		return this.gamedetailsid;
	}

	public void setGamedetailsid(Integer gamedetailsid) {
		this.gamedetailsid = gamedetailsid;
	}

}