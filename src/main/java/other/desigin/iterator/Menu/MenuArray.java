package other.desigin.iterator.Menu;

import java.util.Iterator;

import other.desigin.iterator.iterator.ArrayIterator;

public class MenuArray implements Menu{
	private MenuItem[] MenuItems;
	private int numberOfItems = 0;
	private static final int MAX_ITEMs = 4;
	
	
	
	public MenuArray() {
		super();
		MenuItems = new MenuItem[MAX_ITEMs];
		addItem("A","Array description",true,1);
		addItem("B","Array description",true,1);
		addItem("C","Array description",true,1);
		addItem("D","Array description",true,1);
	}
	
	private void addItem(String name, String description, boolean vagetarian, double price){
		if(numberOfItems>=MAX_ITEMs){
			throw new IllegalStateException("超过菜单上限，无法添加！");
		}else{
			MenuItems[numberOfItems] = new MenuItem(name, description, vagetarian, price);
			numberOfItems++;
		}
	}


	@Override
	public Iterator<MenuItem> createItorator() {
		return new ArrayIterator(MenuItems);
	}
	
}
