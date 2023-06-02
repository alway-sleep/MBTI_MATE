package com.cafe.mbti.domain;

import java.util.Date;

public class MemberVO {
	private int memberNumber;
	private String memberId, memberPw, memberNickname, memberName, memberRRN, memberPhone, memberEmail, memberRegion, memberMBTI;
	private int memberGrade, memberPremium, memberAge;
	private String memberGender, memberPicture;
	private Date memberRegdate, memberUpdate;

	public MemberVO() {}

	public MemberVO(int memberNumber, String memberId, String memberPw, String memberNickname, String memberName,
			String memberRRN, String memberPhone, String memberEmail, String memberRegion, String memberMBTI,
			int memberGrade, int memberPremium, int memberAge, String memberGender, String memberPicture,
			Date memberRegdate, Date memberUpdate) {
		this.memberNumber = memberNumber;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberNickname = memberNickname;
		this.memberName = memberName;
		this.memberRRN = memberRRN;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberRegion = memberRegion;
		this.memberMBTI = memberMBTI;
		this.memberGrade = memberGrade;
		this.memberPremium = memberPremium;
		this.memberAge = memberAge;
		this.memberGender = memberGender;
		this.memberPicture = memberPicture;
		this.memberRegdate = memberRegdate;
		this.memberUpdate = memberUpdate;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberRRN() {
		return memberRRN;
	}

	public void setMemberRRN(String memberRRN) {
		this.memberRRN = memberRRN;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberRegion() {
		return memberRegion;
	}

	public void setMemberRegion(String memberRegion) {
		this.memberRegion = memberRegion;
	}

	public String getMemberMBTI() {
		return memberMBTI;
	}

	public void setMemberMBTI(String memberMBTI) {
		this.memberMBTI = memberMBTI;
	}

	public int getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(int memberGrade) {
		this.memberGrade = memberGrade;
	}

	public int getMemberPremium() {
		return memberPremium;
	}

	public void setMemberPremium(int memberPremium) {
		this.memberPremium = memberPremium;
	}

	public int getMemberAge() {
		return memberAge;
	}

	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public String getMemberPicture() {
		return memberPicture;
	}

	public void setMemberPicture(String memberPicture) {
		this.memberPicture = memberPicture;
	}

	public Date getMemberRegdate() {
		return memberRegdate;
	}

	public void setMemberRegdate(Date memberRegdate) {
		this.memberRegdate = memberRegdate;
	}

	public Date getMemberUpdate() {
		return memberUpdate;
	}

	public void setMemberUpdate(Date memberUpdate) {
		this.memberUpdate = memberUpdate;
	}

	@Override
	public String toString() {
		return "MemberVO [memberNumber=" + memberNumber + ", memberId=" + memberId + ", memberPw=" + memberPw
				+ ", memberNickname=" + memberNickname + ", memberName=" + memberName + ", memberRRN=" + memberRRN
				+ ", memberPhone=" + memberPhone + ", memberEmail=" + memberEmail + ", memberRegion=" + memberRegion
				+ ", memberMBTI=" + memberMBTI + ", memberGrade=" + memberGrade + ", memberPremium=" + memberPremium
				+ ", memberAge=" + memberAge + ", memberGender=" + memberGender + ", memberPicture=" + memberPicture
				+ ", memberRegdate=" + memberRegdate + ", memberUpdate=" + memberUpdate + "]";
	}
}
