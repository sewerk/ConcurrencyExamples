package countDown;

import java.util.concurrent.CountDownLatch;

public class Racer implements Runnable {

	private final static int DISTANCE = 100;
	private CountDownLatch countDown;
	private final int acceleration;
	private int speed;

	public Racer(CountDownLatch countDown, int acceleration) {
		this.countDown = countDown;
		this.acceleration = acceleration;
	}

	@Override
	public void run() {
		try {
			countDown.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
		
		int traveled = 0;
		while(traveled < DISTANCE) {
			speed += acceleration;
			traveled += speed;
			System.out.println(Thread.currentThread().getName() + " traveled " + traveled);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " FINISHED");
	}

}
