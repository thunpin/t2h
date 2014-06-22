package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import t2h.T2H;
import t2h.T2hResult;
import t2h.repository.Tag;

public class Main {
	private static final int LAST_INDEX = 9;
	private static final int FIRST_INDEX = 0;

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.err.println("use analyse file.csv");
		} else {
			final String fileName = args[0];
			final BufferedReader reader = new BufferedReader(new FileReader(fileName));
			final Set<String> allOld = new HashSet<>();
			
			long total = 0;
			long optimunResultHit = 0;
			long t2hResultHit = 0;
			
			String line;
			while ((line = reader.readLine()) != null) {
				final String[] values = line.split(",");
				final String value = values[0];
				final long date = Long.parseLong(values[1]);
				
				if (allOld.contains(value)) {
					optimunResultHit++;
					
					final Calendar calendar = Calendar.getInstance();
					final Set<Tag> tags = T2H.get().createTimeTags(calendar);
					final List<T2hResult> results = T2H.get().request(tags);
					final List<T2hResult> cutedList = results.subList(FIRST_INDEX, LAST_INDEX);
					if (contains(value, cutedList)) {
						t2hResultHit++;
					}
				}
				
				total++;
				T2H.get().add(value, date);
			}
			
			System.out.print(total + "," + optimunResultHit + "," + t2hResultHit);
			
			reader.close();
		}
	}
	
	/**
	 * contains value in results
	 * @param value
	 * @param results
	 * @return
	 */
	private static boolean contains(final String value, final List<T2hResult> results) {
		boolean contains = false;
		for (T2hResult result : results) {
			contains = result.getValue().equalsIgnoreCase(value);
		}
		return contains;
	}
}
