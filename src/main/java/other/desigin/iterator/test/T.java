package other.desigin.iterator.test;

import other.desigin.iterator.Menu.MenuArray;
import other.desigin.iterator.Menu.MenuList;
import other.desigin.iterator.waitress.Waitress;

public class T {
	public static void main(String[] args) {
		Waitress waitress = new Waitress(new MenuList(), new MenuArray());
		waitress.printMenu();
	}
}
