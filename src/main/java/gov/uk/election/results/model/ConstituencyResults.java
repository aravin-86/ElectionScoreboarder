package gov.uk.election.results.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="constituencyResults")
public class ConstituencyResults {
	
	private ConstituencyResult constituencyResult;
	
	@XmlElement(name="constituencyResult")
	public ConstituencyResult getConstituencyResult() {
		return constituencyResult;
	}

	public void setConstituencyResult(ConstituencyResult constituencyResult) {
		this.constituencyResult = constituencyResult;
	}
}
