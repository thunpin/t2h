package t2h;

public class T2hResult implements Comparable<T2hResult> {
	private final String value;
	private final float score;
	private final long date;
	
	/**
	 * 
	 * @param value
	 * @param date
	 */
	public T2hResult(final String value, final float score, final long date) {
		this.value = value;
		this.score = score;
		this.date = date;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(T2hResult obj) {
		final Float objScore = obj.score;
		final Float curScore = this.score;
		return objScore.compareTo(curScore);
	}

	public String getValue() {
		return value;
	}
	public long getDate() {
		return date;
	}
}
