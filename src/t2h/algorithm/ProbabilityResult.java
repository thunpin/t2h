package t2h.algorithm;

public class ProbabilityResult {
	private final float dependent;
	private final float independent;
	
	public ProbabilityResult(final float dependent, final float independent) {
		this.dependent = dependent;
		this.independent = independent;
	}
	
	public float getDependent() {
		return dependent;
	}
	public float getIndependent() {
		return independent;
	}
}
