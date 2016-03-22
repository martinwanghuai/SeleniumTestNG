package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.config.Config;

/**
 * @author lester.li This is a class which is to use as get DBUser from DB
 */
public class DBUserDAO {

	private final Connection sqlConnnection;
	private final DBUser dbUser;
	// users is not a table but a view in DB
	public String sqlFindbyUserId = "select users.userid,dappraiser,dappraiserid,costcenter,userrole,userprefs.time_zone,leaf_orgid,orgcode,description,"
			+ "parentid,logical_domain.domain_id,logical_domain.domain_name,level1id,level2id,level3id,level4id,level5id "
			+ "from users, code_orgstructure, userprefs, logical_domain where currentPID =? and "
			+ "users.leaf_orgid = code_orgstructure.orgid and users.userid =userprefs.userid and logical_domain.domain_id =users.domain_id";

	public String sqlUserLanguage = "select users_meta.languagepref from users_meta,users where users.currentPID =? and users.currentPID=users_meta.currentPID";

	public DBUserDAO(final Connection conn) {

		this.sqlConnnection = conn;
		dbUser = new DBUser();

		switch (Config.getInstance().getProperty("default.dbType")) {
		case "mssql":
			this.sqlFindbyUserId = sqlFindbyUserId;
			break;
		case "oracle":
			this.sqlFindbyUserId = sqlFindbyUserId.toLowerCase();
			break;
		case "mysql":
			this.sqlFindbyUserId = sqlFindbyUserId;
			break;
		default:
			System.out.println("warning: No config");
		}
	}

	public DBUser findByUserId(String usrId) {
		ResultSet rs = null;
		usrId = usrId.toLowerCase().trim();
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = sqlConnnection
					.prepareStatement(sqlFindbyUserId);
			if (usrId.equals("")) {
				return null;
			}
			
			preparedStatement.setString(1, usrId);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				this.dbUser.setUser_ID(usrId);
				this.dbUser.setEKPID(dbUser.MapDBValue(rs.getString("userid")));
				this.dbUser.setAppraiserId(rs.getString("DAppraiser"));
				this.dbUser.setCOSTCENTER(rs.getString("COSTCENTER"));
				this.dbUser.setUSERROLE(rs.getString("USERROLE"));
				this.dbUser.setCurrent_ORGID(rs.getString("LEAF_ORGID"));
				this.dbUser.setParent_ORGID(rs.getString("PARENTID"));
				this.dbUser.setOrgCode(rs.getString("OrgCode"));
				this.dbUser.setOrgDescription(rs.getString("Description"));
				this.dbUser.setAppraiserId_EKPID(rs.getString("DAppraiserID"));
				this.dbUser.setLEVEL1ID(rs.getString("LEVEL1ID"));
				this.dbUser.setLEVEL2ID(rs.getString("LEVEL2ID"));
				this.dbUser.setLEVEL3ID(rs.getString("LEVEL3ID"));
				this.dbUser.setLEVEL4ID(rs.getString("LEVEL4ID"));
				this.dbUser.setLEVEL5ID(rs.getString("LEVEL5ID"));
				this.dbUser.setDOMAIN_ID(rs.getString("domain_id"));
				this.dbUser.setTime_zone(dbUser.MapDBValue(rs
						.getString("time_zone")));
				this.dbUser.setDomain_name(dbUser.MapDBValue(rs
						.getString("domain_name")));
			}
			rs.close();
			preparedStatement.close();

			preparedStatement = sqlConnnection
					.prepareStatement(sqlUserLanguage);
			preparedStatement.setString(1, usrId);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				this.dbUser.setUser_Language(rs.getString("languagepref"));
			}
			rs.close();
			preparedStatement.close();
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dbUser == null) {
			throw new RuntimeException("sqlUserLanguage with usrId=" + usrId
					+ " is not found");
		}
		return dbUser;
	}
}
