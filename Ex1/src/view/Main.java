package view;

import java.util.concurrent.Semaphore;
import controller.ThreadSemaforo;

public class Main {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for(int i = 1; i < 22; i++) {
			ThreadSemaforo thread = new ThreadSemaforo(semaforo, i);
			thread.start();
		}
	}

}
