package gov.uk.election.results.aggregator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileStatsHolder {
	
	public static Map<String, FileStats> fileStatsMap = new ConcurrentHashMap<String, FileStats>();

}
