package chapter4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class WorkingWithCollections {

	public static void main(String[] args) {
		String[] heroes = {"Byorki", "K'lar", "Tyrenni", "Athena", "Jarrod"};
		// Set<String> heroSet = new HashSet<>();
		// Set<String> heroSet = new LinkedHashSet<>();
		Set<String> heroSet = new TreeSet<>();

		Collections.addAll(heroSet, heroes);
		
		heroSet.add("Byorki");
		heroSet.add("Rik");
		
		heroSet.remove("Rik");
		
		//System.out.println(heroSet);
		printCollection(heroSet);
		
		List<String> monsterList = new ArrayList<>();
		monsterList.add("Kobald");
		monsterList.add("Skeleton");
		monsterList.add("Zombie");
		monsterList.add("Rats");
		monsterList.add("Skeleton");

		Collections.sort(monsterList);
		
		monsterList.remove("Skeleton");
		
		printCollection(monsterList);
		System.out.println(monsterList.get(1));
		
		//List<String> cityList = new ArrayList<>();
		LinkedList<String> cityList = new LinkedList<>();
		cityList.add("Elddim");
		cityList.add("Crystwind");
		cityList.add("Fallraen");
		cityList.add("Meren");
		cityList.add("Lang");
		
		printCollection(cityList);
		
		System.out.println(cityList.get(3));
		
		cityList.remove("Meren");
		printCollection(cityList);

		System.out.println(cityList.peek());
		System.out.println(cityList.peekLast());
		printCollection(cityList);
		
		System.out.println(cityList.poll());
		printCollection(cityList);
		
		// Map<String,String> spellbook = new HashMap<>();
		// Map<String,String> spellbook = new LinkedHashMap<>(5,.8f,true);
		Map<String,String> spellbook = new TreeMap<>();
		
		spellbook.put("Fireball", "A ball of fire that inflicts 8 damage per level of magic.");
		spellbook.put("Healing Touch", "Touching an injured player recovers 5 hit points per character level.");
		spellbook.put("Lightning Bolt", "A stream of lightning that inflicts 10 damage per level of magic.");
		spellbook.put("Create Water", "Creates 10 liters of water per level of magic.");
		spellbook.put("Transmutation", "Converts common items into gold.");
		
		printMap(spellbook);
		// System.out.print(spellbook.get("Lightning Bolt"));
		printKeys(spellbook);
		System.out.println();
		
		record Room(String name, String description, List<String> exits) {
		
			public String getExits() {
				StringBuilder exitDesc = new StringBuilder();
								
				if (exits.isEmpty()) {
					exitDesc.append("There are no obvious exits.");	
				} else if (exits.size() == 1) {
					exitDesc.append("There is an exit to the ");
					exitDesc.append(exits.get(0));
				} else if (exits.size() == 2) {
					exitDesc.append("There are exits to the ");
					exitDesc.append(exits.get(0));
					exitDesc.append(" and ");
					exitDesc.append(exits.get(1));
				} else {
					exitDesc.append("There are exits to the ");

					boolean first = true;
					
					for (String exit : exits) {
							if (!first) {
								exitDesc.append(", ");
							} else {
								first = false;
							}
							exitDesc.append(exit);
					}
				}
				
				exitDesc.append(".");
				
				return exitDesc.toString();
			}
		}
		
		List<String> cabinExits = new ArrayList<>();
		cabinExits.add("South");
		cabinExits.add("West");
		
		Room lakeCabin = new Room("Lake cabin", "You are standing outside of a cabin"
				+ " on a lake, with water visible to the South and East. There is"
				+ " a red dock to the South.", cabinExits);
		
		System.out.println(lakeCabin.description());
		System.out.println(lakeCabin.getExits());
	}

	private static void printCollection(Collection collection) {
		
		for (Object element : collection) {
			System.out.printf("%s, ", element.toString());
		}
		
		System.out.println();
	}
	
	private static void printMap(Map map) {
		
		System.out.println();
		Set<Object> keys = map.keySet();
		
		for (Object key : keys) {
			System.out.printf("%s: %s\n", key, map.get(key));
		}
	}
	
	private static void printKeys(Map map) {

		System.out.println();
		Set<Object> keys = map.keySet();
		
		for (Object key : keys) {
			System.out.printf("%s, ", key);
		}

		System.out.println();
	}
}
