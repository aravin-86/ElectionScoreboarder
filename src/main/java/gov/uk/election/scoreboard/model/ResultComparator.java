package gov.uk.election.scoreboard.model;

import gov.uk.election.results.model.Result;

import java.util.Comparator;

public class ResultComparator implements Comparator<Result>{

	@Override
	public int compare(Result o1, Result o2) {
		if(o1.getVotes() > o2.getVotes()){
			return -1;
		}else if(o1.getVotes() < o2.getVotes()){
			return 1;
		}else {
			return 0;
		}
	}

}
