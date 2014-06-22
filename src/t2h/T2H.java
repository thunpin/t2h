package t2h;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import t2h.repository.Element;
import t2h.repository.Repository;
import t2h.repository.Tag;

public class T2H {
	private static final String MONTH_TAG = "month";
	private static final String WEEK_TAG = "week";
	private static final int HOUR_ANGLE_VALUE = 15;
	private final static T2H instance = new T2H();
	
	/**
	 * singletton pattern
	 * @return unique instance
	 */
	public static T2H get() {
		return instance;
	}
	
	/**
	 * Add a new element
	 * 
	 * @param value
	 * @param date
	 * @param tag
	 */
	public void add(final String value, final long date, final Tag ...tag) {
		final Calendar calendar = Calendar.getInstance();
		final int hour = calendar.get(Calendar.HOUR_OF_DAY);
		final int minute = calendar.get(Calendar.MINUTE);
		final Integer week = calendar.get(Calendar.WEEK_OF_MONTH);
		final Integer month = calendar.get(Calendar.MONTH);
		
		final Tag weekTag = new Tag(WEEK_TAG, week.toString());
		final Tag monthTag = new Tag(MONTH_TAG, month.toString());
		
		final float angle = hour * HOUR_ANGLE_VALUE + HOUR_ANGLE_VALUE / minute;
		final Set<Tag> tags = new HashSet<>();
		tags.add(weekTag);
		tags.add(monthTag);
		final Element element = new Element(value, angle, date, tags);
		Repository.get().add(element);
	}
	
	/**
	 * 
	 * @param tag
	 * @return elements ordered by relevance
	 */
	public List<T2hResult> request(Tag ...tag) {
		Repository.get().getElements();
		return null;
	}
}
