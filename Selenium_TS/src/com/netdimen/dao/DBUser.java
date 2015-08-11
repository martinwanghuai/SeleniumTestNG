package com.netdimen.dao;

import java.util.Locale;

public class DBUser extends DBObject {

	private String appraiserId;
	private String LEVEL1ID;
	private String LEVEL2ID;
	private String LEVEL3ID;
	private String LEVEL4ID;
	private String LEVEL5ID;
	private String COSTCENTER;
	private String USERROLE;
	private String Current_ORGID;
	private String Parent_ORGID;
	private String appraiserId_EKPID;
	private String DOMAIN_ID;
	private String OrgCode;
	private String OrgDescription;
	private String Time_zone;
	private String Domain_name;
	private String User_ID;
	private String User_Language;
	private Locale User_Locale;

	private final Locale China_HK_Locale = new Locale("zh", "HK");
	private final Locale China_CN_Locale = new Locale("zh", "CN");
	private final Locale China_TW_Locale = new Locale("zh", "TW");
	private final Locale English_Locale = new Locale("en");
	private final Locale Deutsch_Locale = new Locale("de");
	private final Locale Japan_Locale = new Locale("jp");
	private final Locale Franch_Locale = new Locale("fr");
	private final Locale Canada_Locale = new Locale("en", "CA");

	public Locale getUser_Locale() {
		return User_Locale;
	}

	public String getOrgCode() {
		return OrgCode;
	}

	public String getTime_zone() {
		return Time_zone;
	}

	public void setTime_zone(final String time_zone) {
		Time_zone = time_zone;
	}

	public String getDomain_name() {
		return Domain_name;
	}

	public void setDomain_name(final String domain_name) {
		Domain_name = domain_name;
	}

	public void setOrgCode(final String orgCode) {
		OrgCode = orgCode;
	}

	public String getOrgDescription() {
		return OrgDescription;
	}

	public void setOrgDescription(final String orgDescription) {
		OrgDescription = orgDescription;
	}

	public DBUser() {
	}

	public String getAppraiserId() {
		return appraiserId;
	}

	public void setAppraiserId(final String appraiserId) {
		this.appraiserId = MapDBValue(appraiserId);
	}

	public String getLEVEL1ID() {
		return LEVEL1ID;
	}

	public void setLEVEL1ID(final String lEVEL1ID) {
		LEVEL1ID = MapDBValue(lEVEL1ID);
	}

	public String getLEVEL2ID() {
		return LEVEL2ID;
	}

	public void setLEVEL2ID(final String lEVEL2ID) {
		LEVEL2ID = MapDBValue(lEVEL2ID);
	}

	public String getLEVEL3ID() {
		return LEVEL3ID;
	}

	public void setLEVEL3ID(final String lEVEL3ID) {
		LEVEL3ID = MapDBValue(lEVEL3ID);
	}

	public String getLEVEL4ID() {
		return LEVEL4ID;
	}

	public void setLEVEL4ID(final String lEVEL4ID) {
		LEVEL4ID = MapDBValue(lEVEL4ID);
	}

	public String getLEVEL5ID() {
		return LEVEL5ID;
	}

	public void setLEVEL5ID(final String lEVEL5ID) {
		LEVEL5ID = MapDBValue(lEVEL5ID);
	}

	public String getCOSTCENTER() {
		return COSTCENTER;
	}

	public void setCOSTCENTER(final String cOSTCENTER) {
		COSTCENTER = MapDBValue(cOSTCENTER);
	}

	public String getUSERROLE() {
		return USERROLE;
	}

	public void setUSERROLE(final String uSERROLE) {
		USERROLE = uSERROLE;
	}

	public String getCurrent_ORGID() {
		return Current_ORGID;
	}

	public void setCurrent_ORGID(final String current_ORGID) {
		Current_ORGID = current_ORGID;
	}

	public String getParent_ORGID() {
		return Parent_ORGID;
	}

	public void setParent_ORGID(final String parent_ORGID) {
		Parent_ORGID = parent_ORGID;
	}

	public String getAppraiserId_EKPID() {
		return appraiserId_EKPID;
	}

	public void setAppraiserId_EKPID(final String appraiserId_EKPID) {
		this.appraiserId_EKPID = MapDBValue(appraiserId_EKPID);
	}

	public String getDOMAIN_ID() {
		return DOMAIN_ID;
	}

	public void setDOMAIN_ID(final String dOMAIN_ID) {
		DOMAIN_ID = dOMAIN_ID;
	}

	public String getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(final String user_ID) {
		User_ID = user_ID;
	}

	public void setUser_Language(final String string) {
		this.User_Language = string;
		switch (this.User_Language) {
		case "1":
			this.User_Locale = English_Locale;
			break;
		case "2":
			this.User_Locale = China_CN_Locale;
			break;
		case "3":
			this.User_Locale = China_TW_Locale;
			break;
		case "4":
			this.User_Locale = Japan_Locale;
			break;
		case "6":
			this.User_Locale = Deutsch_Locale;
			break;
		case "9":
			this.User_Locale = Franch_Locale;
			break;
		case "24":
			this.User_Locale = Canada_Locale;
			break;
		case "10":
			this.User_Locale = China_HK_Locale;
			break;
		default:
			throw new RuntimeException(User_ID + " locale= " + string
					+ " which is not support yet");
		}
	}

}
