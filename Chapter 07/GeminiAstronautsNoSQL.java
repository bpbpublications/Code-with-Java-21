package chapter7;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import chapter7.AstronautCassandraDAL.AstronautMission;

public class GeminiAstronautsNoSQL {
			    
	public static void main(String[] args) {

		String bundleLoc = System.getenv("ASTRA_DB_BUNDLE");
        String username = System.getenv("ASTRA_DB_USER");
        String password = System.getenv("ASTRA_DB_PASSWORD");
        String keyspace = System.getenv("ASTRA_DB_KEYSPACE");
        
        AstronautCassandraDAL astronautDAL = 
        		new AstronautCassandraDAL(username, password, bundleLoc, keyspace);
        
        System.out.println("Project Gemini Astronauts:");

		List<String> geminiAstronauts = astronautDAL.getGeminiRoster();
		
		for (String astronaut : geminiAstronauts) {
			System.out.println(astronaut);
		}
		
		System.out.println();

		Set<Integer> randomMissions = new HashSet<>();
		Random random = new Random();
		// generate 3 random numbers
		while (randomMissions.size() < 3) {
			int missionNumber = random.nextInt(10) + 3;
			randomMissions.add(missionNumber);
		}
		
		for (Integer missionNum : randomMissions) {
			
			StringBuilder mission = new StringBuilder("Gemini ");
			mission.append(missionNum.toString());
			List<AstronautMission> missionAstronauts = 
					astronautDAL.getMissionAstronauts(mission.toString());
			
			for (AstronautMission astronautMission : missionAstronauts) {
				System.out.print(astronautMission.missionName() + " ");
				System.out.print(astronautMission.startDate() + " -> ");
				System.out.print(astronautMission.endDate() + " - ");
				System.out.println(astronautMission.astronautName());
			}
			
			System.out.println();
		}
	}
}
