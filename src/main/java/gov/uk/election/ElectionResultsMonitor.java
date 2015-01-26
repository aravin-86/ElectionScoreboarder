package gov.uk.election;

import gov.uk.election.results.aggregator.FileStats;
import gov.uk.election.results.aggregator.FileStatsHolder;
import gov.uk.election.results.processor.ElectionResultsProcessor;
import gov.uk.election.util.AppConstants;
import gov.uk.election.util.OutputPrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

public class ElectionResultsMonitor {

	private final static Logger LOG = Logger
			.getLogger(ElectionResultsMonitor.class);

	private ElectionResultsProcessor electionResultsProcessor = new ElectionResultsProcessor();
	
	public void monitor(String resultsFolder) {

		try {
			Path myDir = Paths.get(resultsFolder);
			OutputPrinter.println("Watching the directory shown below.");
			OutputPrinter.println(resultsFolder);
			WatchService newResultFileWatcher = myDir.getFileSystem().newWatchService();
			myDir.register(newResultFileWatcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);

			WatchKey watckKey = newResultFileWatcher.take();
			boolean valid = true;
			do {
				for (WatchEvent<?> event : watckKey.pollEvents()) {
					if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
						@SuppressWarnings("unchecked")
						WatchEvent<Path> ev = (WatchEvent<Path>)event;
				        Path fileName = ev.context();
				        File file = new File(resultsFolder + "/" + fileName);
				        FileStatsHolder.fileStatsMap.put(file.getName(), new FileStats());
				        
						ExecutorService executorService = Executors.newFixedThreadPool(25);

						executorService.execute(new Runnable() {

							@Override
							public void run() {
								try {
									while(!Files.isReadable(file.toPath())){
										//To make sure that OS is not using the file(ie. copy event is complete)
									}
									electionResultsProcessor.process(file);

								} catch (JAXBException jaxbEx) {
									OutputPrinter.println(AppConstants.ERROR_MESSAGE);
									LOG.fatal("Error when parsing XML", jaxbEx);
								} catch (FileNotFoundException fnfEx) {
									OutputPrinter.println(AppConstants.ERROR_MESSAGE);
									LOG.fatal("File not found", fnfEx);
								}

							}
						});

					}

				}

				valid = watckKey.reset();
				
			} while (valid);
		} catch (IOException ioEx) {
			OutputPrinter.println(AppConstants.ERROR_MESSAGE);
			LOG.fatal("IOException has occured", ioEx);
		} catch (Exception ex) {
			OutputPrinter.println(AppConstants.ERROR_MESSAGE);
			LOG.fatal("Exception has occured", ex);
		}

	}

	public ElectionResultsProcessor getElectionResultsProcessor() {
		return electionResultsProcessor;
	}

	public void setElectionResultsProcessor(
			ElectionResultsProcessor electionResultsProcessor) {
		this.electionResultsProcessor = electionResultsProcessor;
	}

}
