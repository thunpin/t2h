package t2h.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * store the temporal information
 * 
 * @author tptfc
 *
 */
public class Repository {
	private static final Repository instance = new Repository();
	private long elementsTotalCount;
	private final Map<String, List<Element>> elements;
	private final Map<String, Long> elementsCount;
	private final Map<String, Map<Tag, Long>> elementsTagCount;
	private final Map<Tag, Long> tagsCount;
	private final Map<String, Long> tagsTypeCount;
	
	private Repository() {
		this.elements = new HashMap<String, List<Element>>();
		this.elementsCount = new HashMap<String, Long>();
		this.elementsTotalCount = 0;
		this.elementsTagCount = new HashMap<String, Map<Tag,Long>>();
		this.tagsCount = new HashMap<Tag, Long>();
		this.tagsTypeCount = new HashMap<String, Long>();
	}
	
	public static Repository get() {
		return instance;
	}
	
	/**
	 * add a new element
	 * @param element
	 */
	public synchronized void add(final Element element) {
		final List<Element> elements;
		final Long count;
		
		if (this.elements.containsKey(element.getValue())) {
			elements = this.elements.get(element.getValue());
			count = elementsCount.get(element.getValue());
		} else {
			elements = new ArrayList<Element>();
			this.elements.put(element.getValue(), elements);
			count = 0l;
		}
		
		elements.add(element);
		this.elementsCount.put(element.getValue(), count + 1);
		this.elementsTotalCount++;
	}

	
	
	public Map<String, List<Element>> getElements() {
		return elements;
	}
	public Map<String, Long> getElementsCount() {
		return elementsCount;
	}

	public long getElementsTotalCount() {
		return elementsTotalCount;
	}
	public void setElementsTotalCount(long elementsTotalCount) {
		this.elementsTotalCount = elementsTotalCount;
	}
	public Map<String, Map<Tag, Long>> getElementsTagCount() {
		return elementsTagCount;
	}
	public Map<Tag, Long> getTagsCount() {
		return tagsCount;
	}
	public Map<String, Long> getTagsTypeCount() {
		return tagsTypeCount;
	}
}
