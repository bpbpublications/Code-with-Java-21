package chapter7;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class AstronautPostgresDAL {
	
	private static PostgresConn postgres;

	protected record AstronautMission(String missionName, String startDate,
		    String endDate, String astronautName) {};
		    
	public AstronautPostgresDAL (String url, String username, String password) {

		postgres = new PostgresConn(url, username, password);
	}
	
	public List<String> getGeminiRoster() {
		return getGeminiRoster(20);
	}
	
	public List<String> getGeminiRoster(int limit) {
		List<String> returnVal = new ArrayList<>();
		
		String astronautSQL = "SELECT name FROM astronauts LIMIT " + limit;

		try {
			Statement pgStatement = postgres.getConn().createStatement();
			ResultSet geminiAstronauts = pgStatement.executeQuery(astronautSQL);
			
			while (geminiAstronauts.next()) {
				returnVal.add(geminiAstronauts.getString("name"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return returnVal;
	}

	public List<AstronautMission> getMissionAstronauts(String missionName) {
		
		List<AstronautMission> returnVal = new ArrayList<>();
		
		String missionSQL = "SELECT m.name AS missionname, m.start_date, m.end_date, a.name "
				+ "FROM astronauts a "
				+ "INNER JOIN astronaut_missions am ON am.astronaut_name = a.name "
				+ "INNER JOIN missions m ON m.id = am.mission_id "
				+ "WHERE m.name = ?;";
		try {

			PreparedStatement missionStatement = postgres.getConn().prepareStatement(missionSQL);		
			missionStatement.setString(1, missionName);
			ResultSet missionAstronauts = missionStatement.executeQuery();
	
			while (missionAstronauts.next()) {
				AstronautMission astronautMission = new AstronautMission(
						missionAstronauts.getString("missionname"),
						missionAstronauts.getString("start_date"),
						missionAstronauts.getString("end_date"),
						missionAstronauts.getString("name"));
				returnVal.add(astronautMission);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnVal;
	}
	
	public void closeConnection() {
		postgres.closePostgresConnection();
	}
}
