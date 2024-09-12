package controller;

import java.util.concurrent.Semaphore;

public class ThreadPratos extends Thread{
	private int idPratos;
	private Semaphore semaforo;
	
	public ThreadPratos(int idPratos, Semaphore semaforo) {
		this.idPratos = idPratos;
		this.semaforo = semaforo;
	}
	
	public void run() {
		String nomePrato;
		if(idPratos % 2 == 0) {
			nomePrato = "Lasanha a Bolonhesa";
			cozinharPrato(nomePrato, 1200, 600, idPratos);
			entregaPrato(nomePrato, idPratos, semaforo);
		} else {
			nomePrato = "Sopa de Cebola";
			cozinharPrato(nomePrato, 800, 500, idPratos);
			entregaPrato(nomePrato, idPratos, semaforo);
		}
	}
	
	private void cozinharPrato(String nomePrato, int max, int min, int id) {
		int diferençaMaxMin = max - min;
		int tempo = (int) ((Math.random() * diferençaMaxMin) + min);
		int tempoTotalCozimento = 0;
		double porcentagem = 0;
		
		System.out.println("O prato '" + nomePrato + "' começou a ser preparado.");
		while(tempoTotalCozimento < tempo) {
			try {
				sleep(tempo);
				tempoTotalCozimento += 100;
				porcentagem = ((double) tempoTotalCozimento/ tempo) * 100 ;
				if(porcentagem >= 100) {
					System.out.println("Tempo de cozimento do prato '"+ nomePrato + "': 100%");
				}else {
					System.out.printf("Tempo de cozimento do prato '"+ nomePrato + "': %.2f", porcentagem);
					System.out.println("%");
				}
				
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		
	}
	
	private void entregaPrato(String nomePrato, int id, Semaphore semaforo) {
		try {
			System.out.println("Prato '" + nomePrato + "' sendo preparado para entregar! ID: " + id);
			semaforo.acquire();
			sleep(500);
			System.out.println(nomePrato + " foi entregue! ID: " + id);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			semaforo.release();
		}
	}
	

}
