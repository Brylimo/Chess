package Game;

public class Bishop extends Unit {
	public Bishop(String name, int color, int[] loc) {
		super(name, color, loc);
	}
	
	public boolean move(int my, int mx) {
		UnitController controller = Controller.getController();
		final int[] xaction = {-1, -1, -1, 0, 0, 1, 1, 1};
		final int[] yaction = {0, 1, -1, 1, -1, 0, -1, 1};
		boolean[] check = {false, false, false, false, false, false, false, false};
		
		for (int i = 1; i <= 2; i++)
		{
			for (int j = 0; j < xaction.length; j++)
			{
				int fx, fy;
				if (i == 2 && xaction[j] * yaction[j] == 0) { continue; }
				if (check[j] == false) {
					fx = this.getLoc()[0] + xaction[j] * i;
					fy = this.getLoc()[1] + yaction[j] * i;
					if (fx < 0 || fy < 0 || fx > 9 || fy > 9) {
						check[j] = true;
						continue;
					}
					if (mx == fx && my == fy) {
						if (controller.getUnitArr()[fy][fx] == null) {
							Board.movePiece(this.getLoc()[0], this.getLoc()[1], fx, fy);
							controller.moveUnit(this.getLoc()[0], this.getLoc()[1], fx, fy);
							return true;
						} else if (controller.getUnitArr()[fy][fx].getColor() != this.getColor()){
							Board.movePiece(this.getLoc()[0], this.getLoc()[1], fx, fy);
							controller.moveUnit(this.getLoc()[0], this.getLoc()[1], fx, fy);
							return true;
						}
					}
					if (controller.getUnitArr()[fy][fx] != null) {
						check[j] = true;
					}
				}
			}
		}
		return false;
	}
	
	public String toString() {
		return this.getName();
	}
}
