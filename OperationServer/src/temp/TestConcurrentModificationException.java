package temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class TestConcurrentModificationException {

	private static List<String> strs = Collections.synchronizedList(new ArrayList<String>());
	public static boolean addString(String String) {
		strs.add(String);
		return true;
	}

	public static Vector<String> getStrs(String t) {
		Vector<String> ss = new Vector<String>();
		for(String s:strs) {
			if (s.equals(t)) {
				ss.add(s);
			}
		}
		return ss;
	}

	public static boolean removeString(String t) {
		boolean flag=false;
		for(String s:strs) {
			if (s.equals(t)) {
				strs.remove(s);
				flag=true;
			}
		}
		return flag;
	}
	public static void main(String[] args) {
		TestConcurrentModificationException.addString("1");
		TestConcurrentModificationException.addString("2");
		TestConcurrentModificationException.addString("3");
		TestConcurrentModificationException.addString("4");
		TestConcurrentModificationException.addString("5");
		System.out.println(TestConcurrentModificationException.getStrs("1"));
		TestConcurrentModificationException.removeString("1");
	}
}
