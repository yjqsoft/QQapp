package org.table;

/**
 * GameId entity. @author MyEclipse Persistence Tools
 */

public class GameId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer gamedetailsid;

	// Constructors

	/** default constructor */
	public GameId() {
	}

	/** full constructor */
	public GameId(Integer id, Integer gamedetailsid) {
		this.id = id;
		this.gamedetailsid = gamedetailsid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGamedetailsid() {
		return this.gamedetailsid;
	}

	public void setGamedetailsid(Integer gamedetailsid) {
		this.gamedetailsid = gamedetailsid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GameId))
			return false;
		GameId castOther = (GameId) other;

		return ((this.getId() == castOther.getId())
				|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())))
				&& ((this.getGamedetailsid() == castOther.getGamedetailsid())
						|| (this.getGamedetailsid() != null && castOther.getGamedetailsid() != null
								&& this.getGamedetailsid().equals(castOther.getGamedetailsid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (getGamedetailsid() == null ? 0 : this.getGamedetailsid().hashCode());
		return result;
	}

}