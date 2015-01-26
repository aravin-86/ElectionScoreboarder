package gov.uk.election.notifier;

import gov.uk.election.results.aggregator.FileStats;
import gov.uk.election.results.aggregator.FileStatsHolder;
import gov.uk.election.util.OutputPrinter;

public class Notifier {
	
	public void notifySupervisor(String fileName){
		//logic here to notify the supervisor.
		//potentially needs supervisor email id to send notifications.
		OutputPrinter.println();
		OutputPrinter.println("Supervisor notified about the invalid file <"+fileName+">");
		FileStats fileStats = FileStatsHolder.fileStatsMap.get(fileName);
		fileStats.setNotified(true);
	}
}
