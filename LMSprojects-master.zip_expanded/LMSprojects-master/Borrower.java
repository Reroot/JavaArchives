package com.team.alpha.entity;

import org.springframework.stereotype.Component;

@Component
public class Borrower {
	private int cardNo;
	private String BorrowerName;
	private String borrowerAddress;
	private String borrowerPhone;
	
	public Borrower() {
		
	}
	
	public Borrower(int cardNo, String borrowerName, String borrowerAddress, String borrowerPhone) {
		this.cardNo = cardNo;
		this.BorrowerName = borrowerName;
		this.borrowerAddress = borrowerAddress;
		this.borrowerPhone = borrowerPhone;
	}

	public int getCardNo() {
		return cardNo;
	}

	public String getBorrowerName() {
		return BorrowerName;
	}

	public String getBorrowerAddress() {
		return borrowerAddress;
	}

	public String getBorrowerPhone() {
		return borrowerPhone;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	public void setBorrowerName(String borrowerName) {
		BorrowerName = borrowerName;
	}

	public void setBorrowerAddress(String borrowerAddress) {
		this.borrowerAddress = borrowerAddress;
	}

	public void setBorrowerPhone(String borrowerPhone) {
		this.borrowerPhone = borrowerPhone;
	}

	@Override
	public String toString() {
		return "Borrower info: [cardNo=" + cardNo + ", BorrowerName=" + BorrowerName + ", borrowerAddress=" + borrowerAddress
				+ ", borrowerPhone=" + borrowerPhone + "]"+"\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BorrowerName == null) ? 0 : BorrowerName.hashCode());
		result = prime * result + ((borrowerAddress == null) ? 0 : borrowerAddress.hashCode());
		result = prime * result + ((borrowerPhone == null) ? 0 : borrowerPhone.hashCode());
		result = prime * result + cardNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Borrower other = (Borrower) obj;
		if (BorrowerName == null) {
			if (other.BorrowerName != null)
				return false;
		} else if (!BorrowerName.equals(other.BorrowerName))
			return false;
		if (borrowerAddress == null) {
			if (other.borrowerAddress != null)
				return false;
		} else if (!borrowerAddress.equals(other.borrowerAddress))
			return false;
		if (borrowerPhone == null) {
			if (other.borrowerPhone != null)
				return false;
		} else if (!borrowerPhone.equals(other.borrowerPhone))
			return false;
		if (cardNo != other.cardNo)
			return false;
		return true;
	}
	
}
