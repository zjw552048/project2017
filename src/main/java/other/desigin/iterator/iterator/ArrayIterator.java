package other.desigin.iterator.iterator;

import java.util.Iterator;

import other.desigin.iterator.Menu.MenuItem;

public class ArrayIterator implements Iterator<MenuItem>{
	private MenuItem[] MenuItems;
	private int position = 0;
	
	
	public ArrayIterator(MenuItem[] menuItems) {
		super();
		MenuItems = menuItems;
	}

	@Override
	public boolean hasNext() {
		if(position<MenuItems.length && MenuItems[position]!=null){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public MenuItem next() {
		MenuItem menuItem = MenuItems[position];
		position++;
		return menuItem;
	}

}
