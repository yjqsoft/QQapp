

import java.util.Date;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */

public class Userinfo implements java.io.Serializable {

	// Fields

	private String account;
	private String navheader;
	private String nickname;
	private Integer levels;
	private String region;
	private String sex;
	private Date birth;
	private String signature;
	private String netBanking;
	private String reallyName;

	// Constructors

	/** default constructor */
	public Userinfo() {
	}

	/** minimal constructor */
	public Userinfo(String account, String nickname, Integer levels) {
		this.account = account;
		this.nickname = nickname;
		this.levels = levels;
	}

	/** full constructor */
	public Userinfo(String account, String navheader, String nickname, Integer levels, String region, String sex,
			Date birth, String signature, String netBanking, String reallyName) {
		this.account = account;
		this.navheader = navheader;
		this.nickname = nickname;
		this.levels = levels;
		this.region = region;
		this.sex = sex;
		this.birth = birth;
		this.signature = signature;
		this.netBanking = netBanking;
		this.reallyName = reallyName;
	}

	// Property accessors

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNavheader() {
		return this.navheader;
	}

	public void setNavheader(String navheader) {
		this.navheader = navheader;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getLevels() {
		return this.levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return this.birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getNetBanking() {
		return this.netBanking;
	}

	public void setNetBanking(String netBanking) {
		this.netBanking = netBanking;
	}

	public String getReallyName() {
		return this.reallyName;
	}

	public void setReallyName(String reallyName) {
		this.reallyName = reallyName;
	}

}