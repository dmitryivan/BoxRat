import java.util.ArrayList;

public class Box implements Comparable<Box>{
	private final int number;
	private Integer key;
	private final Integer oldKey;
	private final ArrayList<Integer> storedKeys;
	
	public Box(int number, int lock, ArrayList<Integer> storedKeys) {
		super();
		this.number = number-1;
		this.key = lock;
		this.oldKey = lock;
		this.storedKeys = storedKeys;
	}

	public int getNumber() {
		return number;
	}

	public int getLock() {
		return key;
	}

	public void openBox(ArrayList<Integer> keys){
		keys.remove((Integer)key);
		keys.addAll(storedKeys);
		this.key=-1;
	}
	
	public void undoBox(ArrayList<Integer> keys){
		this.key=oldKey;
		keys.remove(storedKeys);
		keys.add((Integer)key);
	}
	
	public boolean canUnlock(ArrayList<Integer> keys){
		if (key==-1) return false;
			return keys.contains((Integer)key); 
	}
	
	
	@Override
	public String toString(){
		return String.format("%2d) key: %s stores: %s", number, key, storedKeys);
	}

	@Override
	public int compareTo(Box other) {
		if (storedKeys.size()-other.storedKeys.size()>0) return -1; 
		if (storedKeys.size()-other.storedKeys.size()<0) return 1;
		return 0;
		}
	
}
