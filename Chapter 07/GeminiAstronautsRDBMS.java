package chapter7;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chapter7.AstronautPostgresDAL.AstronautMission;

import java.util.Random;

public class GeminiAstronautsRDBMS {
	
	public static void main(String[] args) {
		// get database creds
		// from ElephantSQL - postgres://iubylxio:QYKQfZNgDFShS_EY8lcpAh1yZoi6nbA0@rajje.db.elephantsql.com/iubylxio
		// works! jdbc:postgresql://rajje.db.elephantsql.com/iubylxio
		String url = System.getenv("POSTGRES_URL");
        String username = System.getenv("POSTGRES_USER");
        String password = System.getenv("POSTGRES_PASSWORD");

        AstronautPostgresDAL astronautDAL = new AstronautPostgresDAL(url, username, password);
        
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
		
		astronautDAL.closeConnection();
	}
}
