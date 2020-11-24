package Game;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		outer:
		while (true) {
			System.out.printf("<Ajou Chess Board>%n%n");
			System.out.println("원하는 메뉴를 선택하세요.");
			menu();
			System.out.print(">>");
			try {
				int choice = Integer.parseInt(input.nextLine());
				switch(choice) {
				case 1:
					createUnits();
					System.out.println("체스판 생성이 완료되었습니다.");
					System.out.println();
					System.out.println("==========================");
					break;
				case 2:
					createRandomUnits();
					System.out.println("체스판 생성이 완료되었습니다.");
					System.out.println();
					System.out.println("==========================");
					break;
				case 3:
					System.out.printf("삭제를 원하는 체스 말의 위치를 입력하세요. ");
					String value = input.nextLine();
					if (isValue(value)) {
						deleteUnit(value);
						System.out.println();
						System.out.println("==========================");
					} else {
						System.out.println("입력에러! 다시 입력해주세요.");
					}
					break;
				case 4:
					observeChessBoard();
					System.out.println();
					System.out.println("==========================");
					break;
				case 5:
					System.out.println("움직일 말의 위치를 입력하세요. ");
					String inputPiece = input.nextLine();
					System.out.println("말이 이동할 위치를 입력하세요. ");
					String movePiece = input.nextLine();
					if (isValue(inputPiece) && isValue(movePiece)) {
						moveUnit(inputPiece, movePiece);
						System.out.println();
						System.out.println("==========================");
					} else {
						System.out.println("입력에러! 다시 입력해주세요.");
					}
					break;
				case 6:
					System.out.println("프로그램을 종료합니다.");
					break outer;
				default:
					System.out.println("입력에러! 다시 입력해주세요.");
				}
			} catch (NumberFormatException ext) {
				System.out.println("입력에러! 다시 입력해주세요.");
				continue;
			}
		}
		input.close();
	}
	
	public static void menu() {
		System.out.println("1. 체스 말 생성");
		System.out.println("2. 체스 말 랜덤 생성");
		System.out.println("3. 체스 말 삭제");
		System.out.println("4. 체스판 보기");
		System.out.println("5. 체스 말 이동");
		System.out.println("6. 종료");
	}
	
	public static void observeChessBoard() {
		String[][] board = Board.getBoard();
		for(int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				System.out.printf("%-3s", board[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void createUnits() {
		Board.boardInit();
		Controller.getController().unitControllerInit();
		String[][] board = Board.getBoard();
		final int[][] unitNum = {
				{0, 1, 2, 3, 4, 5, 3, 2, 1, 0},    // P, R, N, B, K, Q, B, N, R, P
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  }; // P, P, P, P, P, P, P, P, P, P
		// black
		for (int i = 0; i <= 1; i++)
		{
			for (int j = 0; j < board[0].length; j++)
			{
				make(i, j, unitNum[i][j], 1);
			}
		}
		
		// white
		for (int x = 9, i = 0; x >= 8; x--, i++)
		{
			for (int y = 0; y < board[0].length; y++)
			{
				make(x, y, unitNum[i][y], 0);
			}
		}
	}
	
	public static void make(int i, int j, int unit, int color)
	{
		UnitController controller = Controller.getController();
		final String[] units = {"P", "R", "N", "B", "K", "Q"};
		String name = units[unit] + color;
		switch(unit) {
		case 0:
			controller.createPiece(new Pawn(name, color, new int[] {j, i}));
			break;
		case 1:
			controller.createPiece(new Rook(name, color, new int[] {j, i}));
			break;
		case 2:
			controller.createPiece(new Knight(name, color, new int[] {j, i}));
			break;
		case 3:
			controller.createPiece(new Bishop(name, color, new int[] {j, i}));
			break;
		case 4:
			controller.createPiece(new King(name, color, new int[] {j, i}));
			break;
		case 5:
			controller.createPiece(new Queen(name, color, new int[] {j, i}));
			break;
		}
	}
	
	public static void createRandomUnits() {
		Board.boardInit();
		Controller.getController().unitControllerInit();
		String[][] board = Board.getBoard();
		int[] pieceNum = {12, 2, 2, 2, 1, 1}; // P, R, N, B, K, Q
		
		// black + white
		for (int k = 0; k <= 1; k++)
		{
			for (int i = 0; i < pieceNum.length; i++)
			{
				for (int j = 0; j < pieceNum[i]; j++)
				{
					while(true) {
						double y1 = Math.random() * 10; // 0 ~ 9
						double x1 = Math.random() * 10; // 0 ~ 9
						int y = (int)y1;
						int x =  (int)x1;
						if (board[y][x] == "0" || board[y][x] == "1")
						{
							make((int)y1, (int)x1, i, k);
							break;
						}
					}
				}
			}
		}
	}
	
	public static void deleteUnit(String input) {
		String[][] board = Board.getBoard();
		UnitController controller = Controller.getController();
		int[] arr = coordinateDecoder(input);
		int y = arr[1];
		int x = arr[0];
		if (board[y][x] == "0" || board[y][x] == "1") {
			System.out.println("해당 위치에 체스 말이 없습니다.");
		} else {
			controller.getUnitArr()[y][x] = null;
			board[y][x] = Board.getBackup()[y][x];
			System.out.println("체스 말이 삭제되었습니다.");
		}
	}
	
	public static void moveUnit(String inputPiece, String movePiece) {
		String[][] board = Board.getBoard();
		UnitController controller = Controller.getController();
		int[] iarr = coordinateDecoder(inputPiece);
		int[] marr = coordinateDecoder(movePiece);
		int ix = iarr[0]; int iy = iarr[1]; int mx = marr[0]; int my = marr[1];
		
		if (board[iy][ix] == "0" || board[iy][ix] == "1") {
			System.out.println("해당 위치로 이동할 수 없습니다.");
		} else {
			boolean flag = controller.getUnitArr()[iy][ix].move(my, mx);
			if (flag) {
				System.out.printf("말을 %s(으)로 이동 시켰습니다.%n", movePiece);
			} else {
				System.out.println("해당 위치로 이동할 수 없습니다.");
			}
		}
	}
	
	public static int[] coordinateDecoder(String input) {
		int x, y;
		if (input.trim().length() == 3) {
			char x1 = input.toLowerCase().charAt(0);
			x = x1 - 'a';
			y = 0;
		} else {
			char x1 = input.toLowerCase().charAt(0);
			char y1 = input.charAt(1);
			x = x1 - 'a';
			y = 10 - (y1 - '0');
		}
		int[] arr = new int[2];
		arr[0] = x;
		arr[1] = y;
		return arr;
	}
	
	public static boolean isValue(String input) {
		input = input.trim();
		if (input.length() > 3 || input.length() == 1 || input.length() == 0)
		{
			return false;
		} 
		if (input.length() == 3)
		{
			if (!(input.charAt(2) == '0' && input.charAt(1) == '1'))
			{
				return false;
			}
		} 
		if ((input.charAt(0) >= 'A' && input.charAt(0) <= 'J') || 
			(input.charAt(0) >= 'a' && input.charAt(0) <= 'j')) {
			if (input.charAt(1) >= '1' && input.charAt(1) <= '9') {
				return true;
			}
		}
		return false;
	}

}
