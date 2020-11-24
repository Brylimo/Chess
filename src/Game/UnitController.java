package Game;

public class UnitController {
	private Unit[][] unitArr;
	
	public UnitController() {
		unitArr = new Unit[10][10];
	}
	
	public Unit[][] getUnitArr() {
		return unitArr;
	}

	public void moveUnit(int cx, int cy, int mx, int my) {
		unitArr[my][mx] = unitArr[cy][cx];
		unitArr[my][mx].setLoc(new int[] {mx, my});
		unitArr[cy][cx] = null;
	}

	public void createPiece(Unit piece) {
		int x = piece.getLoc()[0];
		int y = piece.getLoc()[1];
		unitArr[y][x] = piece;
		Board.setPiece(piece);
	}
	
	public void unitControllerInit() {
		unitArr = new Unit[10][10];
	}
}
