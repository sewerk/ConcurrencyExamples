package lock;

public class Reader implements Runnable {

	private Counter count;
	private int executed;

	public Reader(Counter count) {
		this.count = count;
	}

	@Override
	public void run() {
		int n = 0;
		do {
			try {
				n = count.getN();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executed++;
			System.out
					.println(Thread.currentThread().getName() + " finds " + n);
		} while (n > 0);

		System.out.println(Thread.currentThread().getName() + " executed "
				+ executed + " times");
	}

}
