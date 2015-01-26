package gov.uk.election;

public class Election {
	
	private ElectionResultsMonitor electionResultsMonitor = new ElectionResultsMonitor();
	
	public void begin(String resultsFolder){
		electionResultsMonitor.monitor(resultsFolder);
	}

	public ElectionResultsMonitor getElectionResultsMonitor() {
		return electionResultsMonitor;
	}

	public void setElectionResultsMonitor(ElectionResultsMonitor electionResultsMonitor) {
		this.electionResultsMonitor = electionResultsMonitor;
	}
}
