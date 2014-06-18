package t2h.algorithm;

import java.util.List;

import t2h.repository.Information;
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

	public float calculate(final String type, final String value, final float currentPoint) {
		final List<Information> elements = Repository.get().getInformations().get(type).get(value);
		
		float distance = 0;
		long count = 0;
		
		
		for (Information element : elements) {
			float elementDistance = Long.parseLong(element.getValue()); 
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
