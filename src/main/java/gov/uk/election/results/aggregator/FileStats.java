package gov.uk.election.results.aggregator;

public class FileStats {
	
	private boolean detected = true;
	
	private boolean ingested;
	
	private boolean processed;
	
	private boolean notified;
	
	private boolean valid = true;
	
	public boolean isIngested() {
		return ingested;
	}
	public void setIngested(boolean ingested) {
		this.ingested = ingested;
	}
	public boolean isProcessed() {
		return processed;
	}
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	public boolean isDetected() {
		return detected;
	}
	public void setDetected(boolean detected) {
		this.detected = detected;
	}
	public boolean isNotified() {
		return notified;
	}
	public void setNotified(boolean notified) {
		this.notified = notified;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
