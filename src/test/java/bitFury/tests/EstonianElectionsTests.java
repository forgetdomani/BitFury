package bitFury.tests;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import bitFury.guerillaMail.EMail;
import bitFury.guerillaMail.GuerrillaMail;
import bitFury.pages.CandidatesPage;
import bitFury.pages.ElectionsPage;
import bitFury.pages.SignedBallotPage;
import bitFury.pages.StartPage;
import bitFury.pages.UnsignedBallotPage;
import bitFury.pages.VoteSentPage;
import bitFury.testData.BallotReceipt;
import bitFury.testData.Candidate;
import bitFury.testData.Vote;

public class EstonianElectionsTests {

	private static WebDriver driver;

	private static StartPage startPage;
	private static ElectionsPage electionsPage;
	private static CandidatesPage candidatesPage;
	private static UnsignedBallotPage unsignedBallotPage;
	private static SignedBallotPage signedBallotPage;
	private static VoteSentPage voteSentPage;

	private final static String VoteMailSender = "voting2016app@gmail.com";

	@BeforeClass
	public static void setup() {

		System.setProperty("webdriver.gecko.driver", "c:\\gecodriver\\geckodriver.exe");

		driver = new FirefoxDriver();
		startPage = new StartPage(driver);
		electionsPage = new ElectionsPage(driver);
		candidatesPage = new CandidatesPage(driver);
		unsignedBallotPage = new UnsignedBallotPage(driver);
		signedBallotPage = new SignedBallotPage(driver);
		voteSentPage = new VoteSentPage(driver);

		driver.manage().window().maximize();
		driver.navigate().to("https://exonum.com/demo/voting/");
	}


	@Test
	public void CheckCandidatesInfo() throws Exception {
		Vote myVote = createVoteData();

		goVote();
		chooseElections(myVote.getElecitons());
		selectCandidate(myVote.getCandidate().getFullname());
		getCandidateInfo(myVote.getCandidate());
		String officialSiteInfo = getCandidatesOfficialSiteInfo(myVote.getCandidate().getCandidateInfo().length());
		Assert.assertEquals(myVote.getCandidate().getCandidateInfo(), officialSiteInfo);
	}

	@Test
	public void VoteForSomeone() throws Exception {
		Vote myVote = createVoteData();

		goVote();
		chooseElections(myVote.getElecitons());
		selectCandidate(myVote.getCandidate().getFullname());
		voteForCandidate();
		signBallot(myVote.getPin2());
		submitBallot(myVote.getEmailAddress());
		myVote.setBallotReceipt(getBallotReceipt());

		BallotReceipt mailBallotReceipt = getBallotReceiptFromMail(myVote.getEmail());

		Assert.assertEquals(myVote.getBallotReceipt(), mailBallotReceipt);
	}

	private BallotReceipt getBallotReceiptFromMail(GuerrillaMail email) throws Exception {
		BallotReceipt retVal = new BallotReceipt();

		ArrayList<EMail> curMail = email.getEmailList();
		for (EMail mailMsg : curMail) {
			if (mailMsg.getSender().equals(VoteMailSender)) {
				// TODO: здесь надо бы распарсить письмо, вытащить из него HASH и MNEMONIC CODE
				// и сложить в retVal, но почта, увы, не приходит
			}
		}

		return retVal;
	}

	private BallotReceipt getBallotReceipt() {
		return voteSentPage.getBallotReceipt();
	}

	private void submitBallot(String email) {
		signedBallotPage.submitBallot(email);

	}

	private void signBallot(String pin2) {
		unsignedBallotPage.signBallot(pin2);
	}

	private void selectCandidate(String fullname) {
		candidatesPage.selectCandidate(fullname);

	}

	private void getCandidateInfo(Candidate candidate) {
		candidate.setCandidateInfo(candidatesPage.getCandidateInfo());
	}

	private String getCandidatesOfficialSiteInfo(int length) {
		return candidatesPage.getCandidatesOfficialSiteInfo(length);
	}

	private void voteForCandidate() {
		candidatesPage.voteForCandidate();

	}

	private void chooseElections(String elections) {
		electionsPage.chooseElections(elections);

	}

	private void goVote() {
		startPage.goVote();

	}

	private Vote createVoteData() throws Exception {
		Vote myVote = new Vote("Estonian Presidential Election");
		Candidate myCandidate = new Candidate("Eiki Nestor");
		myVote.setCandidate(myCandidate);
		return myVote;
	}

	@AfterClass
	public static void tearDown() {
		driver.close();
	}
}
