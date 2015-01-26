package gov.uk.election.results.aggregator;

import gov.uk.election.results.model.ConstituencyResult;
import gov.uk.election.results.model.ConstituencyResults;
import gov.uk.election.results.model.Result;
import gov.uk.election.scoreboard.model.PartyResult;
import gov.uk.election.scoreboard.model.ResultComparator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectionResultsAggregator {

	public static Map<String, PartyResult> scoreboardMap = new HashMap<String, PartyResult>(); 
	
	public static long totalVotesRegistered = 0;
	
	public synchronized void recordVoteTally(ConstituencyResults constituencyResults){
		ConstituencyResult constituencyResult = constituencyResults.getConstituencyResult();
					
		for(Result result: constituencyResult.getResult()){
			String partyCode = result.getPartyCode().trim();
			if(scoreboardMap.containsKey(partyCode)){
				PartyResult partyResult = scoreboardMap.get(partyCode);
				partyResult.setPartyCodes(partyCode);
				partyResult.setTotalVotes(partyResult.getTotalVotes()+result.getVotes());
			}else{
				PartyResult partyResult = new PartyResult();
				partyResult.setPartyCodes(partyCode);
				partyResult.setTotalVotes(result.getVotes());
				scoreboardMap.put(partyCode, partyResult);
			}
			
			totalVotesRegistered += result.getVotes();
		}
		
		//Updates the seats won count for the party
		setConstituencyWinner(constituencyResult.getResult());
		
	}
	
	public void resetScoreboard(){
		scoreboardMap = new HashMap<String, PartyResult>(); 
		totalVotesRegistered = 0;
	}
	
	private void setConstituencyWinner(List<Result> results){
		Collections.sort(results, new ResultComparator());
		Result winnerResult = results.get(0);
		String partyCode = winnerResult.getPartyCode().trim();
		if(scoreboardMap.containsKey(partyCode)){
			PartyResult partyResult = scoreboardMap.get(partyCode);
			partyResult.setSeatsWon(partyResult.getSeatsWon()+1);
		}else{
			PartyResult partyResult = new PartyResult();
			partyResult.setSeatsWon(1);
			scoreboardMap.put(partyCode, partyResult);
		}
	}
}
