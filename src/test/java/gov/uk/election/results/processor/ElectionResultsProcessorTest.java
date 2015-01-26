package gov.uk.election.results.processor;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import gov.uk.election.notifier.Notifier;
import gov.uk.election.results.aggregator.ElectionResultsAggregator;
import gov.uk.election.results.aggregator.FileStats;
import gov.uk.election.results.aggregator.FileStatsHolder;
import gov.uk.election.results.model.ConstituencyResults;
import gov.uk.election.results.validator.ResultsXMLFileValidator;
import gov.uk.election.scoreboard.ElectionScoreboardPrinter;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ElectionResultsProcessorTest {
	
	@Mock
	private ResultsXMLFileValidator resultsXMLFileValidator;
	
	@Mock
	private Notifier notifier;
	
	@Mock
	private ResultsXMLReader resultsXMLReader;
	
	@Mock
	private ElectionResultsAggregator electionResultsAggregator; 
	
	@Mock
	private ElectionScoreboardPrinter electionScoreboardPrinter;
	
	private File file;
	
	@Before
	public void setUp(){
		file = new File("src/test/resource/results-stub/result001.xml");
		FileStatsHolder.fileStatsMap.put(file.getName(), new FileStats());
	}
	
	@Test
	public void testProcessWhenResultsXMLInvalid() throws FileNotFoundException, JAXBException{
		when(resultsXMLFileValidator.validate(any())).thenReturn(false);
		ElectionResultsProcessor electionResultsProcessor = new ElectionResultsProcessor();
		electionResultsProcessor.setResultsXMLValidator(resultsXMLFileValidator);
		electionResultsProcessor.setNotifier(notifier);
		electionResultsProcessor.process(file);
		verify(notifier, atLeastOnce()).notifySupervisor(file.getName());
	}
	
	@Test
	public void testProcessWhenResultsValid() throws FileNotFoundException, JAXBException{
		ConstituencyResults constituencyResults = new ConstituencyResults();
		when(resultsXMLFileValidator.validate(any())).thenReturn(true);
		when(resultsXMLReader.ingestXML(file)).thenReturn(constituencyResults);
		ElectionResultsProcessor electionResultsProcessor = new ElectionResultsProcessor();
		electionResultsProcessor.setResultsXMLValidator(resultsXMLFileValidator);
		electionResultsProcessor.setElectionResultsAggregator(electionResultsAggregator);
		electionResultsProcessor.setResultsXMLReader(resultsXMLReader);
		electionResultsProcessor.setElectionScoreboardPrinter(electionScoreboardPrinter);
		electionResultsProcessor.process(file);
		verify(resultsXMLReader, atLeastOnce()).ingestXML(file);
		verify(electionResultsAggregator, atLeastOnce()).recordVoteTally(constituencyResults);
		verify(electionScoreboardPrinter, atLeastOnce()).displayScoreboard();
		
	}
}
