package edu.umkc.permitme.domain.permit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="excavation")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExcavationPermit {
	
	@XmlElement(name="applicantName")
	private String applicantName;
	@XmlElement
	private String applicantEmail;
	@XmlElement
	private String responsibleFirm;
	@XmlElement
	private String businessAddress;
	@XmlElement
	private String phone;
	@XmlElement
	private String cityStateZip;
	@XmlElement
	private String fax;
	@XmlElement
	private String superintendent;
	@XmlElement
	private String contactPhone;
	@XmlElement
	private String jobAddress;
	@XmlElement
	private String workDescription;
	@XmlElement
	private String bore;
	@XmlElement
	private String streetPavementExcavation;
	@XmlElement
	private String length;
	@XmlElement
	private String width;
	@XmlElement
	private String startDate;
	@XmlElement
	private String endDate;
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getApplicantEmail() {
		return applicantEmail;
	}
	public void setApplicantEmail(String applicantEmail) {
		this.applicantEmail = applicantEmail;
	}
	public String getResponsibleFirm() {
		return responsibleFirm;
	}
	public void setResponsibleFirm(String responsibleFirm) {
		this.responsibleFirm = responsibleFirm;
	}
	public String getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCityStateZip() {
		return cityStateZip;
	}
	public void setCityStateZip(String cityStateZip) {
		this.cityStateZip = cityStateZip;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getSuperintendent() {
		return superintendent;
	}
	public void setSuperintendent(String superintendent) {
		this.superintendent = superintendent;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getJobAddress() {
		return jobAddress;
	}
	public void setJobAddress(String jobAddress) {
		this.jobAddress = jobAddress;
	}
	public String getWorkDescription() {
		return workDescription;
	}
	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}
	public String getBore() {
		return bore;
	}
	public void setBore(String bore) {
		this.bore = bore;
	}
	public String getStreetPavementExcavation() {
		return streetPavementExcavation;
	}
	public void setStreetPavementExcavation(String streetPavementExcavation) {
		this.streetPavementExcavation = streetPavementExcavation;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
