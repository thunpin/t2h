package t2h.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * store the temporal information
 * 
 * @author tptfc
 *
 */
public class Repository {
	private final Map<String, Map<String, List<Information>>> informations;
	private final Map<String, Long> informationsCount;
	private final Map<Set<Information>, Long> relationshipsCount;
	
	private final static Repository instance = new Repository();
	
	private Repository() {
		informations = new HashMap<>();
		informationsCount = new HashMap<>();
		relationshipsCount = new HashMap<>();
	}
	
	public static Repository get() {
		return instance;
	}

	/**
	 * add a new information
	 * 
	 * @param inf information to add
	 */
	public synchronized void add(final Information inf) {
		final Map<String, List<Information>> elements;
		final Long count; 
		
		if (informations.containsKey(inf.getType())) {
			elements = informations.get(inf.getType());
			count = informationsCount.get(inf.getType());
		} else {
			elements = new HashMap<>();
			count = 0l;
		}
		
		if (!elements.containsKey(inf.getName())) {
			elements.put(inf.getName(), new ArrayList<Information>());
		}
		
		elements.get(inf.getName()).add(inf);
		informationsCount.put(inf.getType(), count + 1);
	}
	
	/**
	 * add a set of informations and create a relationship between then
	 * @param infos
	 */
	public synchronized void add(final Set<Information> infos) {
		for (Information info : infos) {
			this.add(info);
		}
		
		final Long count;
		if (relationshipsCount.containsKey(infos)) {
			count = relationshipsCount.get(infos);
		} else {
			count = 0l;
		}
		
		relationshipsCount.put(infos, count + 1);
	}
	
	//GETs AND SETs
	public Map<String, Map<String, List<Information>>> getInformations() {
		return informations;
	}
	public Map<String, Long> getInformationsCount() {
		return informationsCount;
	}
	public Map<Set<Information>, Long> getRelationshipsCount() {
		return relationshipsCount;
	}
}
