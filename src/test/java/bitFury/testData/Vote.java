package bitFury.testData;

import java.util.Random;

import bitFury.guerillaMail.GuerrillaMail;

public class Vote {

	private String elecitons;
	private Candidate candidate;
	private String pin2;
	private static GuerrillaMail email;
	private BallotReceipt ballotReceipt;

	public Vote(String elections) throws Exception {
		this.elecitons = elections;
		this.pin2 = generatePin2();
		Vote.email = new GuerrillaMail();
	}

	private String generatePin2() {
		String strAllowedCharacters = "0123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			sb.append(strAllowedCharacters.charAt(random.nextInt(strAllowedCharacters.length())));
		}
		return sb.toString();
	}

	public String getElecitons() {
		return elecitons;
	}

	public void setElecitons(String elecitons) {
		this.elecitons = elecitons;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public String getPin2() {
		return pin2;
	}

	public void setPin2(String pin2) {
		this.pin2 = pin2;
	}

	public GuerrillaMail getEmail() throws Exception {
		return email;
	}

	public void setEmail(GuerrillaMail email) {
		Vote.email = email;
	}

	public BallotReceipt getBallotReceipt() {
		return ballotReceipt;
	}

	public void setBallotReceipt(BallotReceipt ballotReceipt) {
		this.ballotReceipt = ballotReceipt;
	}

	public String getEmailAddress() throws Exception {
		return email.getEmailAddress();
	}

}
