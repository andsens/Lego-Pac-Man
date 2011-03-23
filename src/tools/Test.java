package tools;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean s = false;
		for(int i = 2000; i > 0; i--) {
			if(i % 400 > 199) {
				if(!s)
					System.out.println("switch");
				s = true;
				continue;
			}
			s = false;
		}
		System.out.println("");
		s = false;
		for(int i = 1000; i > 0; i--) {
			if(i % 400 > 199) {
				if(!s)
					System.out.println("switch");
				s = true;
				continue;
			}
			s = false;
		}
	}

}
