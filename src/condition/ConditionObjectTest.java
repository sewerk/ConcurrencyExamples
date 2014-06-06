package condition;

public class ConditionObjectTest {

	private static final int ITERATIONS = 10;
	
	public static final String TAB = "                 ";

	public static void main(String[] args) throws InterruptedException {
		
		final ChannelWithCondition c = new ChannelWithCondition();
		Thread sender = new Thread(sender(c));
		Thread sender2 = new Thread(sender(c));
		Thread sender3 = new Thread(sender(c));
		Thread reader = new Thread(reader(c));
		Thread reader2 = new Thread(reader(c));
		Thread reader3 = new Thread(reader(c));
		
		reader.start();
		sender.start();
		sender2.start();
		sender3.start();
		reader2.start();
		
		Thread.sleep(500);
		reader3.start();
	}

	private static Runnable reader(final ChannelWithCondition c) {
		return new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < ITERATIONS; i++) {
					System.out.println("RECEIVER ask");
					int j = c.get();
					System.out.println(j);
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}

	private static Runnable sender(final ChannelWithCondition c) {
		return new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < ITERATIONS; i++) {
					System.out.println(TAB + "SENDER put " + i);
					c.put(i);
				}
			}
		};
	}
}
