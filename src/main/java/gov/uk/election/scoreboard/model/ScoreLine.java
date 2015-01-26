package gov.uk.election.scoreboard.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ScoreLine {

	private String partyCode;
	
	private int seatsWon;
	
	private BigDecimal voteShare;

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public int getSeatsWon() {
		return seatsWon;
	}

	public void setSeatsWon(int seatsWon) {
		this.seatsWon = seatsWon;
	}

	public BigDecimal getVoteShare() {
		return voteShare;
	}

	public void setVoteShare(BigDecimal voteShare) {
		this.voteShare = voteShare;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(partyCode);
		strBuilder.append("\t|\t");
		strBuilder.append(seatsWon);
		strBuilder.append("\t|\t");
		strBuilder.append(voteShare.setScale(2, RoundingMode.CEILING));
		
		return strBuilder.toString();
	}
}
