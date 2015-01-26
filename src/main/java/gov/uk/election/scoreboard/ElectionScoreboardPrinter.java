package gov.uk.election.scoreboard;

import gov.uk.election.scoreboard.model.ScoreLine;
import gov.uk.election.util.AppConstants;
import gov.uk.election.util.OutputPrinter;

import java.util.List;

public class ElectionScoreboardPrinter {
	
	private ElectionScoreboarder electionScoreboarder = new ElectionScoreboarder();
	
	public void displayScoreboard() {
		List<ScoreLine> scoreLines = electionScoreboarder.prepareScoreLines();
		OutputPrinter.println("PartyCodes\tSeatsWon\tVoteShare");
		OutputPrinter.println("---------------------------------------------");
		for (ScoreLine scoreLine : scoreLines) {
			if (scoreLine.getSeatsWon() >= AppConstants.REQUIRED_SEATS_TO_WIN) {
				highlightWinner(scoreLine);
			} else {
				OutputPrinter.println(scoreLine.toString());
			}
		}

		System.out.println();
	}

	private void highlightWinner(ScoreLine scoreLine) {
		OutputPrinter.println("****************- WINS -*********************");
		OutputPrinter.println(scoreLine.toString());
		OutputPrinter.println("*********************************************");
	}
}
