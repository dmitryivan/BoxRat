import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	private static boolean fin;
	String temp=" ";
	public static void main(String[] args) {
		String filename = "chestsAndTreasure.txt";
		for (int i = 0; i < 9; i++) {
			processTask(filename, i);
			System.out.println();
		}
	}

	public static void processTask(String filename, int tasknum) {
		ArrayList<Integer> keys = new ArrayList<>();
		ArrayList<Box> boxes = new ArrayList<>();
		boolean readBegin = (tasknum == 0) ? true : false;
		int sepnum = 0;
		int keysQty = 0;
		int boxesQty = 0;
		boolean fin= false;
		ArrayList<Integer> history = new ArrayList<>();


		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			int i = 1;
			String str = "";

			while ((str = br.readLine()) != null) {
				if (readBegin && !str.matches("^\\s*$")) {
					switch (i) {
					case 1:
						keysQty = Integer.parseInt(str.split("\\s+")[0]);
						boxesQty = Integer.parseInt(str.split("\\s+")[1]);
						break;
					case 2:
						for (int j = 0; j < keysQty; j++) {
							keys.add(Integer.parseInt(str.split("\\s+")[j]));
						}
						break;
					default:
						int k = 1;
						int lock = 0;
						ArrayList<Integer> storedKeys = new ArrayList<>();
						for (String v : str.split("\\s+")) {
							switch (k) {
							case 1:
								lock = Integer.parseInt(v);
								break;
							case 2:
								break;
							default:
								storedKeys.add(Integer.parseInt(v));
							}
							k++;
						}
						boxes.add(new Box(i - 2, lock, storedKeys));
					}

					if (++i >= boxesQty + 3)
						break;

				} else if (str.matches("^\\s*$")) {
					if (tasknum == ++sepnum)
						readBegin = true;
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file!");
		}

		if (keys.isEmpty() || boxes.isEmpty()) {
			System.out
					.println("Error intializing task from file! Check task number!");
		} else {
			System.out.println("Task N" + (tasknum ) + " initial data:");
			System.out.println("Keys: " + keys);
			System.out.println("Boxes:");

			for (Box bx : boxes) {
				System.out.println(bx);
			}

			System.out.println("The answer is:");

			Rat a=new Rat(keys,boxes);
			System.out.println( a.start() );
			
		}
	}
}
