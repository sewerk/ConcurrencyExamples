package lock;
import java.util.concurrent.locks.ReentrantLock;


public class ReentrantLockTest {

	
	public static void main(String[] args) throws InterruptedException {
		final ReentrantLock lock = new ReentrantLock();
		final Counter count = new Counter(10, lock);
		
		Thread a = new Thread(new Reader(count));
		Thread b = new Thread(new Reader(count));
		Thread c = new Thread(new Reader(count));
		
		a.start();
		b.start();
		c.start();
		
		a.join();
		b.join();
		c.join();
		
		System.out.println("END.");
	}
}
