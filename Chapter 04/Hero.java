package chapter4;

import java.util.Map;

public class Hero extends Player {
	
	private Map<String,String> spellbook;
	
	public Hero(String name, int attack, int maxDamage, int defense) {
		super(name, attack, maxDamage, defense);
		
		super.setHitPoints(5);
	}

	public Hero(String name, int attack, int maxDamage, int defense, Map<String,String> spellbook) {
		this(name, attack, maxDamage, defense);
		this.spellbook = spellbook;
	}
	
	public Map<String, String> getSpellbook() {
		return spellbook;
	}
	
	public void setSpellbook(Map<String, String> spellbook) {
		this.spellbook = spellbook;
	}
}
