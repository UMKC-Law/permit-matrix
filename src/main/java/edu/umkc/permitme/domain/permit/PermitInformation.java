package edu.umkc.permitme.domain.permit;

import java.util.List;

import edu.umkc.permitme.domain.Address;

public class PermitInformation {
	private String permitName;
	private String pdfUri;
	private String departmentName;
	private Address departmentAddress;
	private List<String> instructions;
	private String purpose;
	
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPermitName() {
		return permitName;
	}
	public void setPermitName(String permitName) {
		this.permitName = permitName;
	}
	public String getPdfUri() {
		return pdfUri;
	}
	public void setPdfUri(String pdfUri) {
		this.pdfUri = pdfUri;
	}
	public List<String> getInstructions() {
		return instructions;
	}
	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Address getDepartmentAddress() {
		return departmentAddress;
	}
	public void setDepartmentAddress(Address departmentAddress) {
		this.departmentAddress = departmentAddress;
	}
}
