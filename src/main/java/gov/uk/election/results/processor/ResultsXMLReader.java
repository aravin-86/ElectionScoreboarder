package gov.uk.election.results.processor;

import gov.uk.election.results.aggregator.FileStats;
import gov.uk.election.results.aggregator.FileStatsHolder;
import gov.uk.election.results.model.ConstituencyResults;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ResultsXMLReader {
	public ConstituencyResults ingestXML(File xmlFile) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ConstituencyResults.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ConstituencyResults constituencyResults = (ConstituencyResults) jaxbUnmarshaller.unmarshal(xmlFile);
		
		FileStats fileStats = FileStatsHolder.fileStatsMap.get(xmlFile.getName());
		fileStats.setIngested(true);
		return constituencyResults;
	}
}
