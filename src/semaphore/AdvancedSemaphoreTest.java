package semaphore;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class AdvancedSemaphoreTest {

	public static void main(String[] args) throws InterruptedException {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		final int round = 10_000;
		final CountDownLatch latch = new CountDownLatch(2);
		final Semaphore semaphore = new Semaphore(1, true);

		final String testResult1 = buildOutput1(round);
		final String testResult2 = buildOutput2(round);

		Thread player1 = new Player("a", round, semaphore, latch);
		Thread player2 = new Player("b", round, semaphore, latch);

		player1.start();
		player2.start();

		player1.join();
		player2.join();

		String outResults = outContent.toString();
		if (testResult1.equals(outResults) || testResult2.equals(outResults)) {
			System.err.println("Success!");
		} else {
			String expected = outResults.charAt(0) == 'a' ? testResult1 : testResult2;
			System.err.println("Fail!\n" + findDiff(expected, outResults));
		}
	}

	private static String findDiff(String testResult, String outResults) {
		if (testResult.length() != outResults.length())
			return "Length differs, expected longer " + (testResult.length() > outResults.length());
		else {
			char[] expected = testResult.toCharArray();
			char[] result = outResults.toCharArray();
			for (int i = 0; i < expected.length; i++) {
				if (expected[i] != result[i]) {
					final int diff = 12;
					return "Should be:\n" + testResult.substring(Math.max(i - diff, 0), Math.min(i + diff, expected.length))
							+ "\nIS:\n" + outResults.substring(Math.max(i - diff, 0), Math.min(i + diff, expected.length));
				}
			}
		}
		return "NO DIFF Found";
	}

	private static String buildOutput1(final int round) {
		return buildOutput("a", "b", round);
	}
	
	private static String buildOutput2(final int round) {
		return buildOutput("b", "a", round);
	}
	
	private static String buildOutput(String a, String b, final int round) {
		final StringBuilder testResultBuilder = new StringBuilder();
		for (int i = round - 1; i >= 0; i--) {
			testResultBuilder.append(a + i + "\n");
			testResultBuilder.append(b + i + "\n");
		}
		final String testResult = testResultBuilder.toString();
		return testResult;
	}
}
