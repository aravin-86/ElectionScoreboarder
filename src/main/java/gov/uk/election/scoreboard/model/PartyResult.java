package gov.uk.election.scoreboard.model;


public class PartyResult {
	
	private String partyCodes;

	private int seatsWon;
	
	private long totalVotes;

	public int getSeatsWon() {
		return seatsWon;
	}

	public void setSeatsWon(int seatsWon) {
		this.seatsWon = seatsWon;
	}

	public long getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(long totalVotes) {
		this.totalVotes = totalVotes;
	}

	public String getPartyCodes() {
		return partyCodes;
	}

	public void setPartyCodes(String partyCodes) {
		this.partyCodes = partyCodes;
	}
	
}
