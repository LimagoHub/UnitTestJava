package de.math;

public class MultipliziererOptimierer {
	
	private final Multiplizierer multiplizierer;

	public MultipliziererOptimierer(Multiplizierer multiplizierer) {
		this.multiplizierer = multiplizierer;
	}

	public long times(int a, int b) {
		if(a < b)
			return multiplizierer.times(a, b);
		return multiplizierer.times(b, a);
	}
	
	

}
