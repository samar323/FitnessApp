package dao;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class DAO {
	private Connection con;

	public DAO() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessdb?allowPublicKeyRetrieval=true", "root",
				"Samar323@");

	}

	public boolean insertUser(HashMap user) throws Exception {
		try {
			PreparedStatement p = con.prepareStatement(
					"insert into users (email,password,name,phone,gender,age,address,photo) values(?,?,?,?,?,?,?,?)");
			p.setString(1, (String) user.get("email"));
			p.setString(2, (String) user.get("password"));
			p.setString(3, (String) user.get("name"));
			p.setString(4, (String) user.get("phone"));
			p.setString(5, (String) user.get("gender"));
			p.setInt(6, (Integer) user.get("age"));
			p.setString(7, (String) user.get("address"));
			p.setBinaryStream(8, (InputStream) user.get("photo"));
			p.executeUpdate();
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	public boolean insertTrainer(HashMap trainer) throws Exception {
		try {
			PreparedStatement p = con.prepareStatement(
					"insert into trainers (email,password,name,phone,gender,dob,address,category,aadhaar,photo,certificate1,certificate2,status,fileName1,fileName2,exp,description) values(?,?,?,?,?,?,?,?,?,?,?,?,'pending',?,?,?,?)");
			p.setString(1, (String) trainer.get("email"));
			p.setString(2, (String) trainer.get("password"));
			p.setString(3, (String) trainer.get("name"));
			p.setString(4, (String) trainer.get("phone"));
			p.setString(5, (String) trainer.get("gender"));
			p.setDate(6, (java.sql.Date) trainer.get("dob"));
			p.setString(7, (String) trainer.get("address"));
			p.setString(8, (String) trainer.get("category"));
			p.setString(9, (String) trainer.get("aadhaar"));
			p.setBinaryStream(10, (InputStream) trainer.get("photo"));
			p.setBinaryStream(11, (InputStream) trainer.get("certificate1"));
			p.setBinaryStream(12, (InputStream) trainer.get("certificate2"));
			p.setString(13, (String) trainer.get("fileName1"));
			p.setString(14, (String) trainer.get("fileName2"));
			p.setString(15, (String) trainer.get("exp"));
			p.setString(16, (String) trainer.get("description"));
			p.executeUpdate();
			return true;
		} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
			return false;
		}

	}
	public boolean insertSchedule(HashMap schedule) throws Exception {
		try {
			PreparedStatement p = con.prepareStatement(
					"insert into trainer_schedule (email,ex1,ex2,ex3,ex4,meal1,meal2,meal3,meal4) values(?,?,?,?,?,?,?,?,?)");
			p.setString(1, (String) schedule.get("email"));
			p.setString(2, (String) schedule.get("ex1"));
			p.setString(3, (String) schedule.get("ex2"));
			p.setString(4, (String) schedule.get("ex3"));
			p.setString(5, (String) schedule.get("ex4"));
			p.setString(6, (String) schedule.get("meal1"));
			p.setString(7, (String) schedule.get("meal2"));
			p.setString(8, (String) schedule.get("meal3"));
			p.setString(9, (String) schedule.get("meal4"));
			p.executeUpdate();
			return true;
		} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
			return false;
		}

	}
	
	public boolean insertScheduleRecord(HashMap record) throws Exception {
		try {
			PreparedStatement p = con.prepareStatement(
					"insert into user_schedule_record (user_email,record_date,ex1,ex2,ex3,ex4,meal1,meal2,meal3,meal4,ex_rating,meal_rating) values(?,CURRENT_DATE,?,?,?,?,?,?,?,?,?,?)");
			p.setString(1, (String) record.get("email"));
			p.setString(2, (String) record.get("ex1"));
			p.setString(3, (String) record.get("ex2"));
			p.setString(4, (String) record.get("ex3"));
			p.setString(5, (String) record.get("ex4"));
			p.setString(6, (String) record.get("meal1"));
			p.setString(7, (String) record.get("meal2"));
			p.setString(8, (String) record.get("meal3"));
			p.setString(9, (String) record.get("meal4"));
			p.setInt(10, (int) record.get("ex_rating"));
			p.setInt(11, (int) record.get("meal_rating"));
			p.executeUpdate();
			return true;
		} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
			return false;
		}
		

	}
	public boolean insertUserFollowTrainer(HashMap follow) throws Exception {
		try {
			PreparedStatement p = con.prepareStatement(
					"insert into user_follow_schedule (user_email,trainer_email) values(?,?)");
			p.setString(1, (String) follow.get("user_email"));
			p.setString(2, (String) follow.get("trainer_email"));
			p.executeUpdate();
			return true;
		} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
			return false;
		}

	}
	
	public boolean deleteFollowTrainer(HashMap follow) throws Exception {
		try {
			PreparedStatement p = con.prepareStatement(
					"delete from user_follow_schedule where user_email=? and trainer_email=?");
			p.setString(1, (String) follow.get("user_email"));
			p.setString(2, (String) follow.get("trainer_email"));
			p.executeUpdate();
			return true;
		} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
			return false;
		}

	}
	public boolean checkFollow(String user_email, String trainer_email) throws Exception {
		PreparedStatement p = con.prepareStatement("select * from user_follow_schedule where user_email=? and trainer_email=?");
		p.setString(1, user_email);
		p.setString(2, trainer_email);
		ResultSet rs = p.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}

	}
	
	public boolean checkCurrentDateRecord(String user_email) throws Exception {
		PreparedStatement p = con.prepareStatement("select * from user_schedule_record where user_email=? and record_date=CURRENT_DATE");
		p.setString(1, user_email);
		ResultSet rs = p.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}

	}

	public boolean changeTrainerStatus(String email, String status) throws Exception {
		PreparedStatement p = con.prepareStatement("update trainers set status=? where email=?");
		p.setString(1, status);
		p.setString(2, email);
		p.executeUpdate();
		return true;

	}

	public HashMap getUser(String email, String password) throws Exception {
		PreparedStatement p = con.prepareStatement("select * from users where email=? and password=?");
		p.setString(1, email);
		p.setString(2, password);
		ResultSet rs = p.executeQuery();
		if (rs.next()) {
			HashMap user = new HashMap();
			user.put("name", rs.getString("name"));
			user.put("email", rs.getString("email"));
			user.put("phone", rs.getString("phone"));
			user.put("age", rs.getInt("age"));
			user.put("gender", rs.getString("gender"));
			user.put("address", rs.getString("address"));
			return user;
		} else {
			return null;
		}
	}

	public HashMap getTrainer(String email, String password) throws Exception {
		PreparedStatement p = con.prepareStatement("select * from trainers where email=? and password=?");
		p.setString(1, email);
		p.setString(2, password);
		ResultSet rs = p.executeQuery();
		if (rs.next()) {
			HashMap trainer = new HashMap();
			trainer.put("name", rs.getString("name"));
			trainer.put("email", rs.getString("email"));
			trainer.put("phone", rs.getString("phone"));
			trainer.put("dob", rs.getDate("dob"));
			trainer.put("gender", rs.getString("gender"));
			trainer.put("address", rs.getString("address"));
			trainer.put("category", rs.getString("category"));
			trainer.put("aadhaar", rs.getString("aadhaar"));
			trainer.put("status", rs.getString("status"));
			trainer.put("exp", rs.getString("exp"));
			trainer.put("description", rs.getString("description"));
			trainer.put("fileName1", rs.getString("fileName1"));
			trainer.put("fileName2", rs.getString("fileName2"));
			return trainer;
		} else {
			return null;
		}
	}
	public HashMap getTrainerByEmail(String email) throws Exception {
		PreparedStatement p = con.prepareStatement("select * from trainers where email=?");
		p.setString(1, email);
		ResultSet rs = p.executeQuery();
		if (rs.next()) {
			HashMap trainer = new HashMap();
			trainer.put("name", rs.getString("name"));
			trainer.put("email", rs.getString("email"));
			trainer.put("phone", rs.getString("phone"));
			trainer.put("dob", rs.getDate("dob"));
			trainer.put("gender", rs.getString("gender"));
			trainer.put("address", rs.getString("address"));
			trainer.put("category", rs.getString("category"));
			trainer.put("exp", rs.getString("exp"));
			trainer.put("description", rs.getString("description"));
			trainer.put("fileName1", rs.getString("fileName1"));
			trainer.put("fileName2", rs.getString("fileName2"));
			return trainer;
		} else {
			return null;
		}
	}
	

	public ArrayList<HashMap> getAllTrainer(String status) throws Exception {
		PreparedStatement p = con.prepareStatement("select * from trainers where status=?");
		p.setString(1, status);
		ResultSet rs = p.executeQuery();
		ArrayList<HashMap> allTrainers = new ArrayList();
		while (rs.next()) {
			HashMap trainer = new HashMap();
			trainer.put("name", rs.getString("name"));
			trainer.put("email", rs.getString("email"));
			trainer.put("phone", rs.getString("phone"));
			trainer.put("dob", rs.getDate("dob"));
			trainer.put("gender", rs.getString("gender"));
			trainer.put("address", rs.getString("address"));
			trainer.put("category", rs.getString("category"));
			trainer.put("aadhaar", rs.getString("aadhaar"));
			trainer.put("status", rs.getString("status"));
			trainer.put("fileName1", rs.getString("fileName1"));
			trainer.put("fileName2", rs.getString("fileName2"));
			allTrainers.add(trainer);
		}
		return allTrainers;
	}
	public ArrayList<HashMap> getTrainersByNameCategory(String name, String category) throws Exception {
		PreparedStatement p = con.prepareStatement("select * from trainers where name like ? and category=? and status='Accept'");
		p.setString(1, "%"+name+"%");
		p.setString(2, category);
		ResultSet rs = p.executeQuery();
		ArrayList<HashMap> allTrainers = new ArrayList();
		while (rs.next()) {
			HashMap trainer = new HashMap();
			trainer.put("name", rs.getString("name"));
			trainer.put("email", rs.getString("email"));
			trainer.put("phone", rs.getString("phone"));
			trainer.put("gender", rs.getString("gender"));
			trainer.put("category", rs.getString("category"));
			trainer.put("exp", rs.getString("exp"));
			allTrainers.add(trainer);
		}
		return allTrainers;
	}

	public HashMap getAdmin(String aid, String password) throws Exception {
		PreparedStatement p = con.prepareStatement("select * from admin where aid=? and password=?");
		p.setString(1, aid);
		p.setString(2, password);
		ResultSet rs = p.executeQuery();
		if (rs.next()) {
			HashMap user = new HashMap();
			user.put("name", rs.getString("name"));
			return user;
		} else {
			return null;
		}
	}

	public byte[] getPhoto(String email, String type) throws Exception {
		PreparedStatement p;
		if (type.equalsIgnoreCase("user")) {
			p = con.prepareStatement("select (photo) from users where email=?");
		} else {
			p = con.prepareStatement("select (photo) from trainers where email=?");
		}

		p.setString(1, email);
		ResultSet rs = p.executeQuery();
		if (rs.next()) {
			byte photo[] = rs.getBytes("photo");
			if (photo.length != 0) {
				return photo;
			} else {
				
				return null;
			}
		} else {
			return null;
		}

	}


public byte[] getFile(String email, String certificate) throws Exception {
	PreparedStatement p;
	if (certificate.equalsIgnoreCase("1")) {
		p = con.prepareStatement("select (certificate1) from trainers where email=?");
	} else {
		p = con.prepareStatement("select (certificate2) from trainers where email=?");
	}

	p.setString(1, email);
	ResultSet rs = p.executeQuery();
	if (rs.next()) {
		byte file[] = rs.getBytes(1);
		if (file.length != 0) {
			return file;
		} else {
			return null;
		}
	} else {
		return null;
	}

}
public HashMap getTrainerScheduleByEmail(String email) throws Exception {
	PreparedStatement p = con.prepareStatement("select * from trainer_schedule where email=?");
	p.setString(1, email);
	ResultSet rs = p.executeQuery();
	if (rs.next()) {
		HashMap schedule = new HashMap();
		schedule.put("ex1", rs.getString("ex1"));
		schedule.put("ex2", rs.getString("ex2"));
		schedule.put("ex3", rs.getString("ex3"));
		schedule.put("ex4", rs.getString("ex4"));
		schedule.put("meal1", rs.getString("meal1"));
		schedule.put("meal2", rs.getString("meal2"));
		schedule.put("meal3", rs.getString("meal3"));
		schedule.put("meal4", rs.getString("meal4"));
		return schedule;
	} else {
		return null;
	}
}

public ArrayList<HashMap> getUserRecord(String user_email) throws Exception {
	PreparedStatement p = con.prepareStatement("select * from user_schedule_record where user_email=? and record_date<=? and record_date>=?");
	
	p.setString(1, user_email);
	java.time.LocalDate cd=java.time.LocalDate.now();
	java.time.LocalDate bd=cd.minusDays(30);
	java.sql.Date cDate=java.sql.Date.valueOf(cd);
	java.sql.Date bDate=java.sql.Date.valueOf(bd);
	p.setDate(2, cDate);
	p.setDate(3, bDate);
	ResultSet rs = p.executeQuery();
	ArrayList<HashMap> allRecords=new ArrayList<HashMap>();
	while (rs.next()) {
		HashMap record = new HashMap();
		record.put("record_date", rs.getDate("record_date"));
		record.put("ex1", rs.getString("ex1"));
		record.put("ex2", rs.getString("ex2"));
		record.put("ex3", rs.getString("ex3"));
		record.put("ex4", rs.getString("ex4"));
		record.put("meal1", rs.getString("meal1"));
		record.put("meal2", rs.getString("meal2"));
		record.put("meal3", rs.getString("meal3"));
		record.put("meal4", rs.getString("meal4"));
		record.put("ex_rating", rs.getInt("ex_rating"));
		record.put("meal_rating", rs.getInt("meal_rating"));
		allRecords.add(record);
	}
	return allRecords;
}
public String getFollowTrainerEmail(String email) throws Exception {
	PreparedStatement p = con.prepareStatement("select * from user_follow_schedule where user_email=?");
	p.setString(1, email);
	ResultSet rs = p.executeQuery();
	if (rs.next()) {
		return rs.getString("trainer_email");
	} else {
		return null;
	}
}
}


