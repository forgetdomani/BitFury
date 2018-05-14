package bitFury.testData;

public class BallotReceipt {
	private String receiptMemo;
	private String receiptHash;

	public BallotReceipt() {
	}

	public BallotReceipt(String receiptMemo, String receiptHash) {
		this.receiptMemo = receiptMemo;
		this.receiptHash = receiptHash;
	}

	public String getReceiptMemo() {
		return receiptMemo;
	}

	public void setReceiptMemo(String receiptMemo) {
		this.receiptMemo = receiptMemo;
	}

	public String getReceiptHash() {
		return receiptHash;
	}

	public void setReceiptHash(String receiptHash) {
		this.receiptHash = receiptHash;
	}

	public boolean equals(BallotReceipt b) {
		return (receiptMemo.equals(b.getReceiptMemo()) && receiptHash.equals(b.getReceiptHash()));
	}
}
