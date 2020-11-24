package Game;

public class Pawn extends Unit {
	public Pawn(String name, int color, int[] loc) {
		super(name, color, loc);
	}
	
	public boolean move(int my, int mx) {
		UnitController controller = Controller.getController();
		final int[] xaction = {0};
		final int[] yaction = {-1, 1};
		
		for (int i = 0; i < yaction.length; i++)
		{
			for (int j = 0; j < xaction.length; j++)
			{
				int fx, fy;
				fx = this.getLoc()[0] + xaction[j];
				fy = this.getLoc()[1] + yaction[i];
				if (fx < 0 || fy < 0 || fx > 9 || fy > 9) {
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
			}
		}
		return false;
	}
	
	public String toString() {
		return this.getName();
	}
}
