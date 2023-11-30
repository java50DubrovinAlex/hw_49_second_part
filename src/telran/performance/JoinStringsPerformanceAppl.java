package telran.performance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import telran.text.*;

public class JoinStringsPerformanceAppl {

	private static final int N_STRINGS = 1000;
	private static final int N_RUNS = 1000;
	private static final String PACKAGE = "telran.text.";

	public static  void main(String[] args) throws NoSuchMethodException, SecurityException
	, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		String[] strings = getStrings();
		for(int i = 0; i < args.length; i++) {
			@SuppressWarnings("unchecked")
			Class<JoinStrings> clazz = (Class<JoinStrings>) Class.forName(PACKAGE.concat(args[i]));
			Constructor <JoinStrings> constructor = clazz.getConstructor();
			JoinStrings joinStrings = constructor.newInstance();
			PerformanceTest test = getTest(args[i], strings, joinStrings);
			test.run();
		}
		
		
	}

	private static PerformanceTest getTest(String className, String[] strings, JoinStrings joinStrings) {
		String testName = getTestName(className);
		return new JoinStringsPerformanceTest(testName, N_RUNS, strings, joinStrings);
	}

	private static String getTestName(String className) {
		
		return String.format("%s; Number of the strings is %d", className, N_STRINGS);
	}

	private static String[] getStrings() {
		String[] res = new String[N_STRINGS];
		Arrays.fill(res, "string");
		return res;
	}

}