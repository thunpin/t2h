package t2h.algorithm;

import java.util.Set;

import t2h.repository.Element;
import t2h.repository.Repository;
import t2h.repository.Tag;


public class Probability {
	/**
	 * 
	 * @param element
	 * @param tags
	 * @return
	 */
	public ProbabilityResult calculate(final Element element, final Set<Tag> tags) {
		float dependent = -1;
		float independent = 0;
		
		for (Tag tag: tags) {
			final float current = calculate(element, tag);
			if (dependent == -1) {
				dependent = current;
			} else {
				dependent *= current;
			}
			
			independent += current;
		}
		
		if (dependent == -1) {
			dependent = 0;
		}
		
		return new ProbabilityResult(dependent, independent);
	}
	
	/**
	 * calculate probability
	 * 
	 * @param element
	 * @param tag
	 * @return
	 */
	private float calculate(final Element element, final Tag tag) {
		final long AND = Repository.get().getElementsTagCount().get(element.getValue()).get(tag);
		final long total = Repository.get().getTagsCount().get(tag);
		final float andProbabilite = AND/total;
		
		final long elementCount = Repository.get().getElementsCount().get(element.getValue());
		final long totalElementsCount = Repository.get().getElementsTotalCount();
		final float elementProbability = elementCount / totalElementsCount;
		return andProbabilite / elementProbability;
	}
}
