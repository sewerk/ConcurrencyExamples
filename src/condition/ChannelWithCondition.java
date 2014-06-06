package condition;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ChannelWithCondition {

	private static final int MAX_SIZE = 2;
	private List<Integer> buffor;
	private ReentrantLock lock;
	
	private Condition empty;
	private Condition full;
	
	public ChannelWithCondition() {
		buffor = new LinkedList<>();
		lock = new ReentrantLock();
		empty = lock.newCondition();
		full = lock.newCondition();
	}
	
	public void put(int i) {
		lock.lock();
		try {
			while (buffor.size() == MAX_SIZE) {
				System.out.println(ConditionObjectTest.TAB + " put awiat");				
				full.await();
			}
			System.out.println(ConditionObjectTest.TAB + " put");
			buffor.add(i);
			empty.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public int get() {
		lock.lock();
		try {
			while (buffor.isEmpty()) {
				System.out.println(" get awiat");				
				empty.await();
			}
			
			System.out.println(" get");
			int tmp = buffor.remove(0);
			full.signalAll();
			return tmp;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		throw new IndexOutOfBoundsException("Unexpected behaviour");
	}
	
	

}
