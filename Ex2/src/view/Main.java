package view;

import java.util.concurrent.Semaphore;
import controller.ThreadPratos;

public class Main {
	public static void main(String args[]) {
		int espacos = 1;
		Semaphore semaforo = new Semaphore(espacos);
		for(int i = 0; i < 5; i++) {
			ThreadPratos th = new ThreadPratos(i, semaforo);
			th.start();
		}
	}

}
