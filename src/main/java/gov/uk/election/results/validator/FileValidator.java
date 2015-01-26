package gov.uk.election.results.validator;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

public interface FileValidator {
	
	boolean validate(File file) throws JAXBException, FileNotFoundException;

}
