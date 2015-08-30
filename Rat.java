import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Rat {
	final int[] road;
	final int maxSteps;
	int step;
	ArrayList<Integer> keys;
	ArrayList<Box> boxes;
	
	public Rat(ArrayList<Integer> keys, ArrayList<Box> boxes) {
		maxSteps = boxes.size() - 1; //size() = length + 1
		road = new int[maxSteps]; //путь крысы в лабиринте, у нас- цифра=номер сундука, начинаем с нулевого
		this.keys = keys;
		this.boxes = boxes;
		Collections.sort(this.boxes); //сортируем сундуки по количеству ключей
	}

	public String start(){
	    while (step < maxSteps){
	    	if (boxes.get(road[step]).canUnlock(keys)) { //если открыли - идем дальше
	    		boxes.get(road[step]).openBox(keys); //открываем ящик
                step++;
    		} else { //ящик не открылся 
    			if (road[step] == maxSteps && step==0) return "NO SOLUTION"; //отходить некуда - выходим
    			if (road[step] < maxSteps) //если это не последний ящик
    				road[step]++; //выбираем следующий ящик
    			else 
    				goBack();  //все ящики перебрали, отходим на шаг назад
    		}
	    } //прошли лабиринт до конца
	    for (int i=0; i < maxSteps; i++){ //в массиве - ПОРЯДКОВЫЕ номера после сортировки
	    	road[i] = boxes.get(road[i]).getNumber(); //возвращаем изначальные номера ящиков
	    }
	    return (Arrays.toString(road));
    }
	
	void goBack(){
		road[step] = 0; //следующий перебор начнем с нуля
		step--; 
		boxes.get(road[step]).undoBox(keys); //закрываем открытый на предыдущем шаге ящик
		if (road[step] < maxSteps) 
			road[step]++; 
		else 
			goBack(); //если на предыдущем шаге все ящики перебраны - отходим еще на шаг
	}
}
