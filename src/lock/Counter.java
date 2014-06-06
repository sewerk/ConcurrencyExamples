package lock;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {

	int n;
	private ReentrantLock lock;
	
	public Counter(int n, ReentrantLock lock) {
		this.n = n;
		this.lock = lock;
	}
	
	public int getN() throws InterruptedException {
		lock.lock();
		final int nn = n - 1;
		Thread.sleep(100);//long read
		n -= 1;
		lock.unlock();
		return nn;
	}
}
