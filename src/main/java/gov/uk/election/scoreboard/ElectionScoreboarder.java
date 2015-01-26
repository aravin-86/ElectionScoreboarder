package gov.uk.election.scoreboard;

import gov.uk.election.results.aggregator.ElectionResultsAggregator;
import gov.uk.election.scoreboard.model.PartyResult;
import gov.uk.election.scoreboard.model.ScoreLine;
import gov.uk.election.util.AppConstants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ElectionScoreboarder {

	public List<ScoreLine> prepareScoreLines() {
		List<PartyResult> partyResults = sortPartyResults();
		List<ScoreLine> scoreLines = new ArrayList<ScoreLine>();
		for (int i = 0; i < 3; i++) {
			PartyResult partyResult = partyResults.get(i);

			ScoreLine scoreLine = new ScoreLine();
			scoreLine.setPartyCode(partyResult.getPartyCodes());
			scoreLine.setSeatsWon(partyResult.getSeatsWon());
			scoreLine
					.setVoteShare(computeVoteShare(partyResult.getTotalVotes()));

			scoreLines.add(scoreLine);
		}

		ScoreLine othersScoreLine = new ScoreLine();
		othersScoreLine.setPartyCode(AppConstants.OTHERS);
		int othersTotalVote = 0;
		for (int i = 3; i < partyResults.size(); i++) {
			PartyResult partyResult = partyResults.get(i);
			othersScoreLine.setSeatsWon(othersScoreLine.getSeatsWon()
					+ partyResult.getSeatsWon());
			othersTotalVote += partyResult.getTotalVotes();
		}

		othersScoreLine.setVoteShare(computeVoteShare(othersTotalVote));
		scoreLines.add(othersScoreLine);

		return scoreLines;
	}

	private List<PartyResult> sortPartyResults() {
		Collection<PartyResult> partyResultCollection = ElectionResultsAggregator.scoreboardMap
				.values();
		List<PartyResult> partyResults = new ArrayList<PartyResult>(
				partyResultCollection);
		Collections.sort(partyResults, new Comparator<PartyResult>() {

			@Override
			public int compare(PartyResult o1, PartyResult o2) {
				if (o1.getSeatsWon() > o2.getSeatsWon()) {
					return -1;
				} else if (o1.getSeatsWon() < o2.getSeatsWon()) {
					return 1;
				} else if (o1.getTotalVotes() > o2.getTotalVotes()) {
					return -1;
				} else if (o1.getTotalVotes() < o2.getTotalVotes()) {
					return 1;
				}
				return 0;
			}
		});
		return partyResults;
	}

	private BigDecimal computeVoteShare(long votes) {
		double voteShare = ((double) votes * 100.0 / ElectionResultsAggregator.totalVotesRegistered);
		return new BigDecimal(voteShare);
	}

}
