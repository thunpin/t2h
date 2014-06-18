package t2h.repository;

/**
 * temporal data information
 * 
 * @author tptfc
 *
 */
public class Information {
	private final long date;
	private final String name;
	private final String value;
	private final boolean continuos;
	
	public Information(final long date, final String name, final String value, boolean continuos) {
		this.date = date;
		this.name = name;
		this.value = value;
		this.continuos = continuos;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final Long tmpDate = date; 
		return value.hashCode() ^ tmpDate.hashCode();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		final boolean result;
		
		if (obj instanceof Information) {
			final Information info = (Information)obj;
			result = date == info.date && name.equals(info.name) && value.equals(info.value);
		} else {
			result = false;
		}
		
		return result;
	}

	
	//	GETs AND SETs
	public long getDate() {
		return date;
	}
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
	public boolean isContinuos() {
		return continuos;
	}
}