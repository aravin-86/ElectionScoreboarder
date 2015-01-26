package gov.uk.election.results.processor;

import gov.uk.election.notifier.Notifier;
import gov.uk.election.results.aggregator.ElectionResultsAggregator;
import gov.uk.election.results.aggregator.FileStats;
import gov.uk.election.results.aggregator.FileStatsHolder;
import gov.uk.election.results.model.ConstituencyResults;
import gov.uk.election.results.validator.FileValidator;
import gov.uk.election.results.validator.ResultsXMLFileValidator;
import gov.uk.election.scoreboard.ElectionScoreboardPrinter;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

public class ElectionResultsProcessor {
	
	private FileValidator resultsXMLValidator = new ResultsXMLFileValidator();
	
	private ElectionResultsAggregator electionResultsAggregator = new ElectionResultsAggregator();
	
	private ElectionScoreboardPrinter electionScoreboardPrinter = new ElectionScoreboardPrinter();
	
	private Notifier notifier = new Notifier();
	
	private ResultsXMLReader resultsXMLReader = new ResultsXMLReader();
	
	public void process(File resultsFile) throws JAXBException, FileNotFoundException{
		
		boolean valid = resultsXMLValidator.validate(resultsFile);
		if(!valid){
			notifier.notifySupervisor(resultsFile.getName());
		}else{
			ConstituencyResults constituencyResults = getResultsXMLReader().ingestXML(resultsFile);
			electionResultsAggregator.recordVoteTally(constituencyResults);
			electionScoreboardPrinter.displayScoreboard();
		}
		
		FileStats fileStats = FileStatsHolder.fileStatsMap.get(resultsFile.getName());
		fileStats.setProcessed(true);
	}

	public FileValidator getResultsXMLValidator() {
		return resultsXMLValidator;
	}

	public void setResultsXMLValidator(FileValidator resultsXMLValidator) {
		this.resultsXMLValidator = resultsXMLValidator;
	}

	public Notifier getNotifier() {
		return notifier;
	}

	public void setNotifier(Notifier notifier) {
		this.notifier = notifier;
	}

	public ElectionResultsAggregator getElectionResultsAggregator() {
		return electionResultsAggregator;
	}

	public void setElectionResultsAggregator(ElectionResultsAggregator electionResultsAggregator) {
		this.electionResultsAggregator = electionResultsAggregator;
	}

	public ResultsXMLReader getResultsXMLReader() {
		return resultsXMLReader;
	}

	public void setResultsXMLReader(ResultsXMLReader resultsXMLReader) {
		this.resultsXMLReader = resultsXMLReader;
	}

	public ElectionScoreboardPrinter getElectionScoreboardPrinter() {
		return electionScoreboardPrinter;
	}

	public void setElectionScoreboardPrinter(ElectionScoreboardPrinter electionScoreboardPrinter) {
		this.electionScoreboardPrinter = electionScoreboardPrinter;
	}
}
