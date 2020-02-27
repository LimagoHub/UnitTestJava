package de.math;

public class MultipliziererImpl implements Multiplizierer {

	@Override
	public long times(int a, int b) {
		long retval = 0;
		for (int i = 0; i < a; i++) {
			retval += b;
		}
		return retval;
	}

}
