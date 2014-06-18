package t2h.repository;

import java.util.Set;

public class Element {
	private final String value;
	private final float angle;
	private final Set<Tag> tags;
	
	public Element(final String value, float angle, Set<Tag> tags) {
		this.value = value;
		this.angle = angle;
		this.tags = tags;
	}

	public String getValue() {
		return value;
	}
	public float getAngle() {
		return angle;
	}
	public Set<Tag> getTags() {
		return tags;
	}
}
