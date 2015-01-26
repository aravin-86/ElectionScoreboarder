package gov.uk.election.results.validator;

import gov.uk.election.results.aggregator.FileStats;
import gov.uk.election.results.aggregator.FileStatsHolder;
import gov.uk.election.results.model.ConstituencyResults;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

public class ResultsXMLFileValidator implements FileValidator {
	
	private final static Logger LOG = Logger.getLogger(ResultsXMLFileValidator.class);
	
	@Override
	public boolean validate(File file) throws JAXBException, FileNotFoundException {
		boolean valid = true;
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ConstituencyResults.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.unmarshal(new BufferedReader(new FileReader(file)));
		} catch(UnmarshalException uEx){
			valid = false;
			FileStats fileStats = FileStatsHolder.fileStatsMap.get(file.getName());
			fileStats.setValid(false);
			fileStats.setIngested(true);
			LOG.error("Exception while unmarshalling XML", uEx);
		} catch(JAXBException jaxbException){
			LOG.error("Jaxb error", jaxbException);
		}
		
		return valid;
	}

	

}
