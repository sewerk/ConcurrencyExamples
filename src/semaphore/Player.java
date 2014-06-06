package semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Player extends Thread {

	private String move;
	private int count;
	private Semaphore sema;
	private CountDownLatch latch;

	public Player(String move, int count, Semaphore sema) {
		this.move = move;
		this.count = count;
		this.sema = sema;
	}
	
	public Player(String move, int count, Semaphore sema, CountDownLatch latch) {
		this(move,count,sema);
		this.latch = latch;
		
	}

	@Override
	public void run() {
		await();
		
		while (count-- > 0) {
			sema.acquireUninterruptibly();
			System.out.println(move + count);
			sema.release();
		}
	}

	private void await() {
		if (latch != null) {
			try {
				latch.countDown();
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
