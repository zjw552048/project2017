package other.desigin.iterator.waitress;

import java.util.Iterator;

import other.desigin.iterator.Menu.Menu;

public class Waitress {
	private Menu menuList;
	private Menu menuArray;
	
	public Waitress(Menu menuList, Menu menuArray) {
		super();
		this.menuList = menuList;
		this.menuArray = menuArray;
	}
	
	public void printMenu(){
		Iterator<?> listIterator = menuList.createItorator();
		printMenu(listIterator);
		Iterator<?> arrayIterator = menuArray.createItorator();
		printMenu(arrayIterator);
		
	}
	
	private void printMenu(Iterator<?> iterator){
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}
