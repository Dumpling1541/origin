package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import data.Avatar;

public class Avatars {
	static final String url = "jdbc:oracle:thin:@192.168.4.22:1521:xe";
	static final String user = "C##MOIM";
	static final String password = "1q2w3e4r";
	
	public static List<Avatar> findAll() {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM AVATARS";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Avatar> li = new ArrayList<>();
			while(rs.next()) {
				Avatar one = new Avatar();
				one.setId(rs.getString("id"));
				one.setUrl(rs.getString("url"));
				li.add(one);
				
			}
			conn.close();
			return li;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
		
	}
}
