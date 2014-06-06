package countDown;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	public static void main(String[] args) {
		CountDownLatch countDown = new CountDownLatch(1);
		
		Thread a = new Thread(new Racer(countDown, 1));
		Thread b = new Thread(new Racer(countDown, 2));
		Thread c = new Thread(new Racer(countDown, 3));
		
		a.start();
		b.start();
		c.start();

		System.out.println("Start!");
		countDown.countDown();
		
		
	}
}
