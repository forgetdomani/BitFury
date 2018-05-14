package bitFury.testData;

public class Candidate {

	private String elections;
	private String fullname;
	private String candidateInfo;

	public Candidate(String fullname) {
		this.fullname = fullname;
	}

	public String getElections() {
		return elections;
	}

	public void setElections(String elections) {
		this.elections = elections;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getCandidateInfo() {
		return candidateInfo;
	}

	public void setCandidateInfo(String candidateInfo) {
		this.candidateInfo = candidateInfo;
	}

}
