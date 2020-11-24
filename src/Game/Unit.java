package Game;

public abstract class Unit {
	private String name;
	private int color;	// 0 - white, 1 - dark
	private int[] loc = new int[2]; // x, y
	
	public Unit(String name, int color, int[] loc) {
		this.name = name;
		this.color = color;
		this.loc = loc;
	}
	
	public int[] getLoc() {
		return loc;
	}

	public void setLoc(int[] loc) {
		this.loc = loc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public String getName() {
		return this.name;
	}
	
	public int getColor() {
		return this.color;
	}
	
	public abstract boolean move(int my, int mx);
}
