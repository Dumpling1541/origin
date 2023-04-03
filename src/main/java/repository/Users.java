package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import data.User;

public class Users {
	static final String url = "jdbc:oracle:thin:@192.168.4.22:1521:xe";
	static final String user = "C##MOIM";
	static final String password = "1q2w3e4r";
	
	public static int create(String id, String pass, String name, String avatarid) {
		try {
			Connection conn =DriverManager.getConnection(url,user,password);
			String sql = "INSERT INTO USERS VALUES(?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pass);
			ps.setString(3, name);
			ps.setString(4, avatarid);
			
			int r = ps.executeUpdate();
			conn.close();
		
			return r;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
			
		}
		
		
	}
	public static User findById(String id){
		try {
			Connection conn =DriverManager.getConnection(url,user,password);
			
			String sql = "SELECT USERS.*, AVATARS.URL AS AVATAR_URL FROM USERS JOIN AVATARS ON USERS.AVATAR_ID = AVATARS.ID WHERE USERS.ID=?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
		
			// 3. 요청후 응답 처리
			ResultSet rs = ps.executeQuery();
			User one =null;

			while (rs.next()) {
				one = new User();
				
				one.setId(rs.getString("id"));
				one.setPass(rs.getString("pass"));
				one.setName(rs.getString("name"));
				one.setAvatarId(rs.getString("avatar_id"));
				one.setAvatarURL(rs.getString("avatar_url"));
			
			
			}

			conn.close();
			return one;
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
		
		
	}
}
