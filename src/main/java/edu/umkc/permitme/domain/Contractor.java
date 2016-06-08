package edu.umkc.permitme.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Contractor.
 */
@Entity
@Table(name = "contractor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Contractor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "business_license_number")
    private String businessLicenseNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "agent_first_name")
    private String agentFirstName;

    @Column(name = "agent_middle_initial")
    private String agentMiddleInitial;

    @Column(name = "agent_last_name")
    private String agentLastName;

    @Column(name = "agent_job_title")
    private String agentJobTitle;

    @Column(name = "email")
    private String email;

    @Column(name = "agent_phone_number")
    private String agentPhoneNumber;

    @Column(name = "contract_license_number")
    private String contractLicenseNumber;

    @Column(name = "occupational_license_number")
    private String occupationalLicenseNumber;

    @Column(name = "master_plumber_license_number")
    private String masterPlumberLicenseNumber;

    @Column(name = "has_general_liability_coverage")
    private Boolean hasGeneralLiabilityCoverage;

    @Column(name = "requirements_met")
    private Boolean requirementsMet;

    @Column(name = "carrier")
    private String carrier;

    @Column(name = "policy_number")
    private String policyNumber;

    @OneToMany(mappedBy = "contractor")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Project> projects = new HashSet<>();

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessLicenseNumber() {
        return businessLicenseNumber;
    }

    public void setBusinessLicenseNumber(String businessLicenseNumber) {
        this.businessLicenseNumber = businessLicenseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAgentFirstName() {
        return agentFirstName;
    }

    public void setAgentFirstName(String agentFirstName) {
        this.agentFirstName = agentFirstName;
    }

    public String getAgentMiddleInitial() {
        return agentMiddleInitial;
    }

    public void setAgentMiddleInitial(String agentMiddleInitial) {
        this.agentMiddleInitial = agentMiddleInitial;
    }

    public String getAgentLastName() {
        return agentLastName;
    }

    public void setAgentLastName(String agentLastName) {
        this.agentLastName = agentLastName;
    }

    public String getAgentJobTitle() {
        return agentJobTitle;
    }

    public void setAgentJobTitle(String agentJobTitle) {
        this.agentJobTitle = agentJobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgentPhoneNumber() {
        return agentPhoneNumber;
    }

    public void setAgentPhoneNumber(String agentPhoneNumber) {
        this.agentPhoneNumber = agentPhoneNumber;
    }

    public String getContractLicenseNumber() {
        return contractLicenseNumber;
    }

    public void setContractLicenseNumber(String contractLicenseNumber) {
        this.contractLicenseNumber = contractLicenseNumber;
    }

    public String getOccupationalLicenseNumber() {
        return occupationalLicenseNumber;
    }

    public void setOccupationalLicenseNumber(String occupationalLicenseNumber) {
        this.occupationalLicenseNumber = occupationalLicenseNumber;
    }

    public String getMasterPlumberLicenseNumber() {
        return masterPlumberLicenseNumber;
    }

    public void setMasterPlumberLicenseNumber(String masterPlumberLicenseNumber) {
        this.masterPlumberLicenseNumber = masterPlumberLicenseNumber;
    }

    public Boolean isHasGeneralLiabilityCoverage() {
        return hasGeneralLiabilityCoverage;
    }

    public void setHasGeneralLiabilityCoverage(Boolean hasGeneralLiabilityCoverage) {
        this.hasGeneralLiabilityCoverage = hasGeneralLiabilityCoverage;
    }

    public Boolean isRequirementsMet() {
        return requirementsMet;
    }

    public void setRequirementsMet(Boolean requirementsMet) {
        this.requirementsMet = requirementsMet;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contractor contractor = (Contractor) o;
        if(contractor.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, contractor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Contractor{" +
            "id=" + id +
            ", businessName='" + businessName + "'" +
            ", businessLicenseNumber='" + businessLicenseNumber + "'" +
            ", phoneNumber='" + phoneNumber + "'" +
            ", streetAddress='" + streetAddress + "'" +
            ", zipCode='" + zipCode + "'" +
            ", city='" + city + "'" +
            ", state='" + state + "'" +
            ", agentFirstName='" + agentFirstName + "'" +
            ", agentMiddleInitial='" + agentMiddleInitial + "'" +
            ", agentLastName='" + agentLastName + "'" +
            ", agentJobTitle='" + agentJobTitle + "'" +
            ", email='" + email + "'" +
            ", agentPhoneNumber='" + agentPhoneNumber + "'" +
            ", contractLicenseNumber='" + contractLicenseNumber + "'" +
            ", occupationalLicenseNumber='" + occupationalLicenseNumber + "'" +
            ", masterPlumberLicenseNumber='" + masterPlumberLicenseNumber + "'" +
            ", hasGeneralLiabilityCoverage='" + hasGeneralLiabilityCoverage + "'" +
            ", requirementsMet='" + requirementsMet + "'" +
            ", carrier='" + carrier + "'" +
            ", policyNumber='" + policyNumber + "'" +
            '}';
    }
}
