package t2h.repository;

public class Tag {
	private final String name;
	private final String value;
	
	public Tag(final String name, final String value) {
		this.name = name;
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode() ^ value.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		final boolean test;
		if (obj != null && obj instanceof Tag) {
			final Tag tag = (Tag)obj;
			test = tag.name.equals(name) && tag.value.equals(value);
		} else {
			test = false;
		}
		
		return test;
	}

	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
}
