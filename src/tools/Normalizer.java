package tools;


public class Normalizer {
	
	private int[] history = null;
	
	public int min, max, latestNormalized;
	public float avg;
	private boolean hasHistory;
	
	private int outputRangeMin, outputRangeMax;
	
	public Normalizer(int historySize) {
		this(historySize, 0, 100);
	}
	
	public Normalizer(int historySize, int outputRangeMin, int outputRangeMax) {
		if (historySize > 0) { 
			history = new int[historySize];
			hasHistory = true;
		} else {
			hasHistory = false;
		}
		
		this.outputRangeMin = outputRangeMin;
		this.outputRangeMax = outputRangeMax;
	}
	
	/**
	 * Register a new value read out, which in turn will update
	 * {@link #min}, {@link #max}, {@link #avg} and {@link #latestNormalized}.
	 * 
	 * @param value New read value
	 */
	public int register(int value) {
		int discardedValue = 0;
		
		// Left switch by one to make room for new readout
		if (hasHistory) {
			discardedValue = history[0];
			for(int i = 1; i < history.length; i++)
				history[i-1] = history[i];
			history[history.length-1] = value;
		}
		
		// Update max value
		if(value > max) {
			max = value;
		} else if (hasHistory && discardedValue == max) {
			max = history[0];
			for(int point : history)
				max = Math.max(max, point);
		}
		
		// Update min value
		if(value < min) {
			min = value;
		} else if (hasHistory && discardedValue == min) {
			min = history[0];
			for(int point : history)
				min = Math.min(min, point);
		}
		
		// Update avg value
		if (hasHistory) {
			avg -= discardedValue/history.length;
			avg += value/history.length;
		}
		
		// Update latest normalized
		return latestNormalized = normalize(value);
	}
	
	/**
	 * Normalize a given value based on the following rules:
	 * @TODO Document algorithm for normalization
	 * 
	 * @param value Unnormalized value 
	 * @return Normalized value
	 */
	public int normalize(int value) {
		int divisor = Math.max(max - min, 1);
		
		int output = ((value - min) * outputRangeMax) / divisor;
		if (output < outputRangeMin)
			return outputRangeMin;
		if (output > outputRangeMax)
			return outputRangeMax;
		return output;
	}
}
