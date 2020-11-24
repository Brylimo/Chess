package Game;

public class Controller {
	private static UnitController controller = new UnitController();

	public static UnitController getController() {
		return controller;
	}

	public static void setController(UnitController controller) {
		Controller.controller = controller;
	}
}
