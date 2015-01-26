package gov.uk.election.results.aggregator;

import gov.uk.election.results.model.ConstituencyResults;
import gov.uk.election.results.processor.ResultsXMLReader;
import gov.uk.election.scoreboard.model.PartyResult;

import java.io.File;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ElectionResultsAggregatorTest {
	
	private static ElectionResultsAggregator electionResultsAggregator = new ElectionResultsAggregator();
	
	private static File file;
	
	@BeforeClass
	public static void setUp() throws JAXBException{
		file = new File("src/test/resource/results-stub/result001.xml");
		FileStatsHolder.fileStatsMap.put(file.getName(), new FileStats());
		ResultsXMLReader resultsXMLReader = new ResultsXMLReader();
		ConstituencyResults constituencyResults = resultsXMLReader.ingestXML(file);
		electionResultsAggregator.resetScoreboard();
		electionResultsAggregator.recordVoteTally(constituencyResults);
	}
	
	@Test
	public void testRecordVoteTallyForScoreboardMapSize(){
		Assert.assertNotNull(ElectionResultsAggregator.scoreboardMap);
		Assert.assertEquals(7, ElectionResultsAggregator.scoreboardMap.size());
		Assert.assertNotNull(ElectionResultsAggregator.scoreboardMap.get("LAB"));
	}
	
	@Test
	public void testRecordVoteTallyForParties(){
		Assert.assertNotNull(ElectionResultsAggregator.scoreboardMap);
		Assert.assertNotNull(ElectionResultsAggregator.scoreboardMap.get("LAB"));
		Assert.assertNotNull(ElectionResultsAggregator.scoreboardMap.get("CON"));
		Assert.assertNotNull(ElectionResultsAggregator.scoreboardMap.get("LD"));
		Assert.assertNotNull(ElectionResultsAggregator.scoreboardMap.get("PC"));
		Assert.assertNotNull(ElectionResultsAggregator.scoreboardMap.get("OTH"));
		Assert.assertNotNull(ElectionResultsAggregator.scoreboardMap.get("GRN"));
		Assert.assertNotNull(ElectionResultsAggregator.scoreboardMap.get("UKIP"));
	}
	
	@Test
	public void testRecordVoteTallyForPartyResult(){
		Assert.assertNotNull(ElectionResultsAggregator.scoreboardMap);
		PartyResult labPartyResult = ElectionResultsAggregator.scoreboardMap.get("LAB");
		Assert.assertEquals(8994, labPartyResult.getTotalVotes());
		Assert.assertEquals(1, labPartyResult.getSeatsWon());
		Assert.assertEquals("LAB", labPartyResult.getPartyCodes());
		
		//TODO: similar assertions for other parties
	}
}
