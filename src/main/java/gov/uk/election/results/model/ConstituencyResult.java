package gov.uk.election.results.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class ConstituencyResult {
	
	private int seqNo;
	
	private int constituencyId;

	private String constituencyName;

	private List<Result> result = new ArrayList<Result>();
	
	@XmlAttribute(name="seqNo")
	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	
	@XmlElement(name="consituencyId")
	public int getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(int constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	@XmlElement(name="constituencyName")
	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	@XmlElementWrapper(name="results")
	@XmlElement(name="result")
	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	

}
