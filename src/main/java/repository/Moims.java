package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import data.Moim;

public class Moims {
	public static final String user = "C##MOIM";
	public static final String password = "1q2w3e4r";
	public static final String url = "jdbc:oracle:thin:@192.168.4.22:1521:xe";

	public static int create(String id, String managerId, String event, String type, String cate, String description,
			int maxPerson, String beginDate, String endDate) {
		try {
			// 1. 연결
			Connection conn = DriverManager.getConnection(url, user, password);

			// 2. 요청객체 준비
			String sql = "INSERT INTO MOIMS VALUES(?, ?, ?, ?, ?, ?, ?, 1, "
					+ "TO_DATE(?, 'YYYY-MM-DD HH24:MI'), TO_DATE(?, 'YYYY-MM-DD HH24:MI'), null)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, managerId);
			ps.setString(3, event);
			ps.setString(4, type);
			ps.setString(5, cate);
			ps.setString(6, description);
			ps.setInt(7, maxPerson);
			ps.setString(8, beginDate);
			ps.setString(9, endDate);

			int r = ps.executeUpdate();
			conn.close();

			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static List<Moim> findLatest() {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = " SELECT * FROM "
					+ " (SELECT * FROM MOIMS WHERE TYPE='public' AND BEGIN_DATE > SYSDATE ORDER BY BEGIN_DATE-SYSDATE) "
					+ " WHERE ROWNUM <=3";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Moim> moimList = new LinkedList<>();
			while (rs.next()) {
				Moim moim = new Moim();
				moim.setId(rs.getString("ID"));
				moim.setManagerId(rs.getString("manager_id"));
				moim.setEvent(rs.getString("event"));
				moim.setCate(rs.getString("cate"));
				moim.setType(rs.getString("type"));
				moim.setDescription(rs.getString("description"));
				moim.setMaxPerson(rs.getInt("Max_Person"));
				moim.setCurrentPerson(rs.getInt("current_Person"));
				moim.setBeginDate(rs.getDate("Begin_Date"));
				moim.setEndDate(rs.getDate("End_Date"));
				moim.setFinalCost(rs.getInt("final_cost"));

				moimList.add(moim);
			}
			conn.close();

			return moimList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Moim> findCate(String cate) {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = "SELECT * FROM MOIMS WHERE TYPE='public' AND BEGIN_DATE > SYSDATE AND CATE =? ORDER BY BEGIN_DATE-SYSDATE";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cate);
			ResultSet rs = ps.executeQuery();
			List<Moim> moimList = new LinkedList<>();
			while (rs.next()) {
				Moim moim = new Moim();
				moim.setId(rs.getString("ID"));
				moim.setManagerId(rs.getString("manager_id"));
				moim.setEvent(rs.getString("event"));
				moim.setCate(rs.getString("cate"));
				moim.setType(rs.getString("type"));
				moim.setDescription(rs.getString("description"));
				moim.setMaxPerson(rs.getInt("Max_Person"));
				moim.setCurrentPerson(rs.getInt("current_Person"));
				moim.setBeginDate(rs.getDate("Begin_Date"));
				moim.setEndDate(rs.getDate("End_Date"));
				moim.setFinalCost(rs.getInt("final_cost"));

				moimList.add(moim);
			}
			conn.close();

			return moimList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Moim> findAll() {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = "SELECT * FROM MOIMS WHERE TYPE='public' AND BEGIN_DATE > SYSDATE ORDER BY BEGIN_DATE-SYSDATE";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Moim> moimList = new LinkedList<>();
			while (rs.next()) {
				Moim moim = new Moim();
				moim.setId(rs.getString("ID"));
				moim.setManagerId(rs.getString("manager_id"));
				moim.setEvent(rs.getString("event"));
				moim.setCate(rs.getString("cate"));
				moim.setType(rs.getString("type"));
				moim.setDescription(rs.getString("description"));
				moim.setMaxPerson(rs.getInt("Max_Person"));
				moim.setCurrentPerson(rs.getInt("current_Person"));
				moim.setBeginDate(rs.getDate("Begin_Date"));
				moim.setEndDate(rs.getDate("End_Date"));
				moim.setFinalCost(rs.getInt("final_cost"));

				moimList.add(moim);
			}
			conn.close();

			return moimList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Moim> findByCate(String[] cates) {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = "SELECT * FROM MOIMS WHERE BEGIN_DATE > SYSDATE";
			if (cates == null) {
				sql += " ORDER BY BEGIN_DATE";
			} else {
				switch (cates.length) {
				case 1 -> sql += " AND CATE IN (?)";
				case 2 -> sql += " AND CATE IN (?, ?)";
				case 3 -> sql += " AND CATE IN (?, ?, ?)";
				case 4 -> sql += " AND CATE IN (?, ?, ?, ?)";
				case 5 -> sql += " AND CATE IN (?, ?, ?, ?, ?)";
				case 6 -> sql += " AND CATE IN (?, ?, ?, ?, ?, ?)";
				case 7 -> sql += " AND CATE IN (?, ?, ?, ?, ?, ?, ?)";
				}
				sql += " ORDER BY BEGIN_DATE";
			}
			PreparedStatement ps = conn.prepareStatement(sql);
			if (cates != null) {
				for (int i = 0; i < cates.length; i++) {
					ps.setString(i + 1, cates[i]);
				}
			}

			ResultSet rs = ps.executeQuery();
			List<Moim> moimList = new LinkedList<>();
			while (rs.next()) {
				Moim moim = new Moim();
				moim.setId(rs.getString("ID"));
				moim.setManagerId(rs.getString("manager_id"));
				moim.setEvent(rs.getString("event"));
				moim.setCate(rs.getString("cate"));
				moim.setType(rs.getString("type"));
				moim.setDescription(rs.getString("description"));
				moim.setMaxPerson(rs.getInt("Max_Person"));
				moim.setCurrentPerson(rs.getInt("current_Person"));
				moim.setBeginDate(rs.getDate("Begin_Date"));
				moim.setEndDate(rs.getDate("End_Date"));
				moim.setFinalCost(rs.getInt("final_cost"));

				moimList.add(moim);
			}
			conn.close();
			return moimList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Moim findById(String id) {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = "SELECT MOIMS.*, USERS.NAME AS MANAGER_NAME , AVATARS.URL AS MANAGER_URL " + "FROM MOIMS "
					+ "JOIN USERS ON MOIMS.MANAGER_ID = USERS.ID " + "JOIN AVATARS ON AVATARS.ID = USERS.AVATAR_ID "
					+ "WHERE MOIMS.ID = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			Moim moim = null;
			if (rs.next()) {
				moim = new Moim();
				moim.setId(rs.getString("ID"));
				moim.setManagerId(rs.getString("manager_id"));
				moim.setEvent(rs.getString("event"));
				moim.setCate(rs.getString("cate"));
				moim.setType(rs.getString("type"));
				moim.setDescription(rs.getString("description"));
				moim.setMaxPerson(rs.getInt("Max_Person"));
				moim.setCurrentPerson(rs.getInt("current_Person"));
				moim.setBeginDate(rs.getDate("Begin_Date"));
				moim.setEndDate(rs.getDate("End_Date"));
				moim.setFinalCost(rs.getInt("final_cost"));

				moim.setManagerName(rs.getString("MANAGER_NAME"));
				moim.setManagerAvatarURL(rs.getString("MANAGER_URL"));

			}
			conn.close();
			return moim;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	
	

}

/*
 * 
 * if (cates != null) { switch (cates.length) { case 7 : ps.setString(7,
 * cates[6]); case 6 : ps.setString(6, cates[5]); case 5 : ps.setString(5,
 * cates[4]); case 4 : ps.setString(4, cates[3]); case 3 : ps.setString(3,
 * cates[2]); case 2 : ps.setString(2, cates[1]); case 1 : ps.setString(1,
 * cates[0]); } }
 */
