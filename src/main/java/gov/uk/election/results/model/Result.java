package gov.uk.election.results.model;

import javax.xml.bind.annotation.XmlElement;

public class Result {
	
	private String partyCode;
	
	private long votes;
	
	private double share;
	
	@XmlElement(name="partyCode")
	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
	
	@XmlElement(name="votes")
	public long getVotes() {
		return votes;
	}

	public void setVotes(long votes) {
		this.votes = votes;
	}
	
	@XmlElement(name="share")
	public double getShare() {
		return share;
	}

	public void setShare(double share) {
		this.share = share;
	}
	
	
}
