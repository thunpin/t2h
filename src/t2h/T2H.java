package t2h;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import t2h.algorithm.Distance;
import t2h.algorithm.Probability;
import t2h.algorithm.ProbabilityResult;
import t2h.repository.Element;
import t2h.repository.Repository;
import t2h.repository.Tag;

public class T2H {
	private static final int NUMBERS_OF_RESULT = 3;
	private static final String MONTH_TAG = "month";
	private static final String WEEK_TAG = "week";
	private static final int HOUR_ANGLE_VALUE = 15;
	private final static T2H instance = new T2H();
	
	private T2H() {
	}
	
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
		final Set<Tag> tags = createTimeTags(calendar);
		
		final float angle = hour * HOUR_ANGLE_VALUE + HOUR_ANGLE_VALUE / minute;
		final Element element = new Element(value, angle, date, tags);
		Repository.get().add(element);
	}

	/**
	 * create time tags
	 * 
	 * @param calendar
	 * @return time tags
	 */
	public Set<Tag> createTimeTags(final Calendar calendar) {
		final Integer week = calendar.get(Calendar.WEEK_OF_MONTH);
		final Integer month = calendar.get(Calendar.MONTH);
		
		final Tag weekTag = new Tag(WEEK_TAG, week.toString());
		final Tag monthTag = new Tag(MONTH_TAG, month.toString());
		
		
		final Set<Tag> tags = new HashSet<>();
		tags.add(weekTag);
		tags.add(monthTag);
		return tags;
	}
	
	/**
	 * 
	 * @param tags context elements
	 * @return elements ordered by relevance
	 */
	public List<T2hResult> request(Set<Tag> tags) {
		final Calendar calendar = Calendar.getInstance();
		final long date = calendar.getTimeInMillis();
		final int hour = calendar.get(Calendar.HOUR);
		final int minute = calendar.get(Calendar.MINUTE);
		final float currentPoint = hour * HOUR_ANGLE_VALUE + HOUR_ANGLE_VALUE / minute;
		
		final Distance distance = new Distance();
		final Probability probability = new Probability();
		
		final Collection<String> values = Repository.get().getElements().keySet();
		final List<T2hResult> results = new ArrayList<>();
		
		for (final String value : values) {
			final float distanceResult = distance.calculate(value, currentPoint);
			final ProbabilityResult probabilityResult = probability.calculate(value, tags);
			
			final float score = (
						distanceResult + 
						probabilityResult.getDependent() + 
						probabilityResult.getIndependent()
					) / NUMBERS_OF_RESULT;
			
			final T2hResult result = new T2hResult(value, score, date);
			results.add(result);
		}
		
		Collections.sort(results);
		
		return results;
	}
}
