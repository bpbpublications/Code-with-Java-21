package chapter7;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class AstronautCassandraDAL {

	private CassandraConn cassandra;
	
	protected record AstronautMission(String missionName, Instant startDate,
		    Instant endDate, String astronautName) {};
		    
	public AstronautCassandraDAL(String username, String password,
			String bundleLoc, String keyspace) {
		
		cassandra = new CassandraConn(username, password, bundleLoc, keyspace);
	}
	
	public List<String> getGeminiRoster() {
		return getGeminiRoster(20);
	}
	
	public List<String> getGeminiRoster(int limit) {
		List<String> returnVal = new ArrayList<>();
		
		String astronautCQL = "SELECT name FROM astronauts LIMIT " + limit;

		try {
			ResultSet astronauts = cassandra.getCqlSession().execute(astronautCQL);
			
			for (Row astronaut: astronauts) {
				returnVal.add(astronaut.getString("name"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		
		return returnVal;
	}
	
	public List<AstronautMission> getMissionAstronauts(String missionName) {
		
		List<AstronautMission> returnVal = new ArrayList<>();
		
		String missionCQL = "SELECT mission_name, mission_start_date, mission_end_date, astronaut_name "
				+ "FROM astronauts_by_mission "
				+ "WHERE mission_name = ?;";
		PreparedStatement missionStatement = cassandra.getCqlSession().prepare(missionCQL);			

		BoundStatement boundCQLMission = missionStatement.bind(missionName);
		ResultSet missionAstronauts = cassandra.getCqlSession().execute(boundCQLMission);
		
		for (Row amRow : missionAstronauts) {
			AstronautMission astronautMission = new AstronautMission(
					amRow.getString("mission_name"),
					amRow.getInstant("mission_start_date"),
					amRow.getInstant("mission_end_date"),
					amRow.getString("astronaut_name"));
			returnVal.add(astronautMission);
		}
		
		return returnVal;
	}
}
