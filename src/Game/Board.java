package Game;

public class Board {
	private static String[][] board = 
	{
			{ "1", "0", "1", "0", "1", "0", "1", "0", "1", "0" },
			{ "0", "1", "0", "1", "0", "1", "0", "1", "0", "1" },
			{ "1", "0", "1", "0", "1", "0", "1", "0", "1", "0" },
			{ "0", "1", "0", "1", "0", "1", "0", "1", "0", "1" },
			{ "1", "0", "1", "0", "1", "0", "1", "0", "1", "0" },
			{ "0", "1", "0", "1", "0", "1", "0", "1", "0", "1" },
			{ "1", "0", "1", "0", "1", "0", "1", "0", "1", "0" },
			{ "0", "1", "0", "1", "0", "1", "0", "1", "0", "1" },
			{ "1", "0", "1", "0", "1", "0", "1", "0", "1", "0" },
			{ "0", "1", "0", "1", "0", "1", "0", "1", "0", "1" },
	};
	private static String[][] backup = new String[10][10];
	
	static {
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				backup[i][j] = board[i][j];
			}
		}
	}
	
	public static String[][] getBackup() {
		return backup;
	}

	public static String[][] getBoard() {
		return board;
	}

	public static void setBoard(String[][] board) {
		Board.board = board;
	}
	
	public static void setPiece(Unit piece) {
		int y = piece.getLoc()[1];
		int x = piece.getLoc()[0];
		board[y][x] = piece.getName();
	}
	
	public static void movePiece(int cx, int cy, int mx, int my) {
		board[my][mx] = board[cy][cx];
		board[cy][cx] = backup[cy][cx];
	}
	
	public static void boardInit() {
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				board[i][j] = backup[i][j];
			}
		}
	}
	
}
