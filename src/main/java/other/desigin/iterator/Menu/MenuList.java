package other.desigin.iterator.Menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuList implements Menu{
	private List<MenuItem> MenuItems;
	
	public MenuList() {
		super();
		MenuItems = new ArrayList<MenuItem>();
		addItem("a","List description",true,1);
		addItem("b","List description",true,1);
		addItem("c","List description",true,1);
		addItem("d","List description",true,1);
	}
	
	private void addItem(String name, String description, boolean vagetarian, double price){
		MenuItems.add(new MenuItem(name, description, vagetarian, price));
	}

	@Override
	public Iterator<MenuItem> createItorator() {
		return MenuItems.iterator();
	}
	
}
