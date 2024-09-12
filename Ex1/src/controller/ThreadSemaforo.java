package controller;

import java.util.concurrent.Semaphore;

public class ThreadSemaforo extends Thread {
	private int idThread;
	private Semaphore semaforo;

	public ThreadSemaforo(Semaphore semaforo, int idThread) {
		this.semaforo = semaforo;
		this.idThread = idThread;
	}

	public void run() {
		if(idThread % 3 == 1) {
			for(int i = 0; i < 2; i++) {
				calculosThreads(1000, 200, idThread);
				transacoesBanco(1000, idThread);
			}
		}
		else if(idThread % 3 == 2) {
			for(int i = 0; i < 3; i++) {
				calculosThreads(1500, 500, idThread);
				transacoesBanco(1500, idThread);
			}
		} else {
			for(int i = 0; i < 3; i++) {
				calculosThreads(2000, 1000, idThread);
				transacoesBanco(1500, idThread);
			}
		}
	}

	private void calculosThreads(int max, int min, int id) {
		int diferencaMaxMin = max - min;
		int tempo = (int) ((Math.random() * diferencaMaxMin ) + min);
		System.out.println("A thread " + id + " esta fazendo calculos. " + tempo + "ms");
		try {
			sleep(tempo);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private void transacoesBanco(int valorTempo, int id) {
		int tempo = valorTempo;
		try {
			semaforo.acquire();
			sleep(tempo);
			System.out.println("A thread " + id + " esta fazendo uma transacao para o banco de dados! " + tempo + "ms");
		} catch(Exception e) {
			System.err.println(e);
		} finally {
			semaforo.release();
		}
		
	}

}
