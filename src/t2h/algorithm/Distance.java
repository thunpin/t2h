package t2h.algorithm;

import java.util.List;

import t2h.repository.Element;
import t2h.repository.Repository;

/**
 * distance algorithm to continuous context elements
 * @author tptfc
 *
 */
public class Distance {
	private static final int MIN_FREQUENCE = 3;
	private static final int MAX_ANGLE = 4;
	private static final int FULL_CIRCUFERENCE = 360;
	private static final int MAX_DIF_NUMBER = 180;

	/**
	 * 
	 * @param value
	 * @param currentPoint
	 * @return
	 */
	public float calculate(final String value, final float currentPoint) {
		final List<Element> elements = Repository.get().getElements().get(value);
		
		float distance = 0;
		long count = 0;
		
		
		for (Element element : elements) {
			float elementDistance = element.getAngle(); 
			float calculated = currentPoint - elementDistance;
			if (calculated < 0) {
				calculated *= -1;
			}
			
			if (calculated > MAX_DIF_NUMBER) {
				calculated = FULL_CIRCUFERENCE - calculated;
			}
			
			if (calculated < MAX_ANGLE) {
				count++;
				distance += calculated;
			}
		}
		
		if (count < MIN_FREQUENCE) {
			distance = 0;
		}
		
		return distance;
	}
}
