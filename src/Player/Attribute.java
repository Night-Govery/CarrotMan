package Player;

public class Attribute {
	public int max_Health;
	public int current_Health;
	public int max_Damage;
	public int max_Armor;
	public int exp;;
	public int level;
	public String name;
	public int shootTime;
	
	
	public Attribute(int max_Health, int max_Damage, int max_Armor, int level, int exp, String name, int shootTime) {
		this.max_Armor = max_Armor;
		this.max_Damage = max_Damage;
		this.max_Health = max_Health;
		this.level = level;
		this.exp = exp;
		this.name = name;
		this.shootTime = shootTime;
		current_Health = max_Health;
	}
}
