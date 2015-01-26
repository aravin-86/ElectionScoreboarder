package gov.uk.election.scoreboard;

import gov.uk.election.results.aggregator.ElectionResultsAggregator;
import gov.uk.election.results.aggregator.FileStats;
import gov.uk.election.results.aggregator.FileStatsHolder;
import gov.uk.election.results.model.ConstituencyResults;
import gov.uk.election.results.processor.ResultsXMLReader;
import gov.uk.election.scoreboard.model.ScoreLine;
import gov.uk.election.util.AppConstants;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ElectionScoreboarderTest {

	private static ElectionResultsAggregator electionResultsAggregator = new ElectionResultsAggregator();
	
	private static List<ScoreLine> scoreLines;

	@BeforeClass
	public static void setUp() throws JAXBException {
		// Update the scoreboard map with one of the results for test.
		File file = new File("src/test/resource/results-stub/result001.xml");
		FileStatsHolder.fileStatsMap.put(file.getName(), new FileStats());
		ResultsXMLReader resultsXMLReader = new ResultsXMLReader();
		ConstituencyResults constituencyResults = resultsXMLReader
				.ingestXML(file);
		electionResultsAggregator.resetScoreboard();
		electionResultsAggregator.recordVoteTally(constituencyResults);
		ElectionScoreboarder electionScoreboarder = new ElectionScoreboarder();
		scoreLines = electionScoreboarder.prepareScoreLines();
	}
	
	@Test
	public void test1PrepareScoreLines_Size() {
		Assert.assertNotNull(scoreLines);
		Assert.assertEquals(4, scoreLines.size());
	}
	
	@Test
	public void test2PrepareScoreLines_SeatsWon() {
		Assert.assertNotNull(scoreLines);
		Assert.assertEquals(1, scoreLines.get(0).getSeatsWon());
	}
	
	@Test
	public void test3PrepareScoreLines_VoteShare() {
		Assert.assertNotNull(scoreLines);
		Assert.assertEquals(32, scoreLines.get(0).getVoteShare().intValue());
		Assert.assertEquals(29, scoreLines.get(1).getVoteShare().intValue());
		Assert.assertEquals(19, scoreLines.get(2).getVoteShare().intValue());
		Assert.assertEquals(18, scoreLines.get(3).getVoteShare().intValue());
	}
	
	@Test
	public void test4PrepareScoreLines_Order() {
		Assert.assertNotNull(scoreLines);
		Assert.assertTrue(scoreLines.get(0).getSeatsWon() >= scoreLines.get(1).getSeatsWon());
		Assert.assertTrue(scoreLines.get(1).getSeatsWon() >= scoreLines.get(2).getSeatsWon());
	}
	
	@Test
	public void test5PrepareScoreLines_Others() {
		Assert.assertNotNull(scoreLines);
		Assert.assertEquals(AppConstants.OTHERS, scoreLines.get(3).getPartyCode());
	}
	
	@Test
	public void test6PrepareScoreLines_SecondResult() throws JAXBException{
		File file = new File("src/test/resource/results-stub/result002.xml");
		FileStatsHolder.fileStatsMap.put(file.getName(), new FileStats());
		ResultsXMLReader resultsXMLReader = new ResultsXMLReader();
		ConstituencyResults constituencyResults = resultsXMLReader
				.ingestXML(file);
		electionResultsAggregator.recordVoteTally(constituencyResults);
		ElectionScoreboarder electionScoreboarder = new ElectionScoreboarder();
		scoreLines = electionScoreboarder.prepareScoreLines();
		
		Assert.assertNotNull(scoreLines);
		System.out.println(scoreLines);
		Assert.assertEquals(2, scoreLines.get(0).getSeatsWon());
		Assert.assertEquals(33, scoreLines.get(0).getVoteShare().intValue());
		Assert.assertEquals(23, scoreLines.get(1).getVoteShare().intValue());
		Assert.assertEquals(22, scoreLines.get(2).getVoteShare().intValue());
		Assert.assertEquals(20, scoreLines.get(3).getVoteShare().intValue());
	}
}
