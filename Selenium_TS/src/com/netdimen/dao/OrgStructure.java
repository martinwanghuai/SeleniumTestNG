package com.netdimen.dao;

public final class OrgStructure {
	public final String OrgID;// ekpID
	public final String ORG_Code; // "Organization Code";
	public final String ORG_Desc; // "Description";
	public final String Parent_ORG_ID;// parent ekpID
	public eSignture esigntureObj;

	public OrgStructure(final String ID, final String ORG_Code, final String ORG_Desc,
			final String Parent_ID) {
		this.OrgID = ID;
		this.ORG_Code = ORG_Code;
		this.ORG_Desc = ORG_Desc;
		this.Parent_ORG_ID = Parent_ID;
	}

	public void setESignture(final eSignture e) {
		this.esigntureObj = e;
	}
}