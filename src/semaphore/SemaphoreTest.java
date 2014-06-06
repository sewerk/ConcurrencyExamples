package semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	public static void main(String[] args) throws InterruptedException {
		
		Semaphore sema = new Semaphore(1, true);
		
		int round = 1000;
		
		Thread player1 = new Player("ping  ", round, sema);
		Thread player2 = new Player(" pong ", round, sema);
		
		player1.start();
		player2.start();
		
		player1.join();
		player2.join();
		
		System.out.println("END.");
	}
}
