package gov.uk.election;

public class ElectionRunner {
	public static void main(String[] args){
		Election election = new Election();
		election.begin(args[0]);
	}
}
