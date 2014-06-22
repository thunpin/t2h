package t2h;

public class T2hResult {
	private final String value;
	private final long date;
	
	public T2hResult(final String value, final long date) {
		this.value = value;
		this.date = date;
	}

	public String getValue() {
		return value;
	}
	public long getDate() {
		return date;
	}
}
