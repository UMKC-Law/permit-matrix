package edu.umkc.permitme.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CityLicenses.
 */
@Entity
@Table(name = "city_licenses")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CityLicenses implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "company_name")
    private String company_name;

    @Column(name = "business_address")
    private String business_address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zip_code;

    @Column(name = "business_phone")
    private String business_phone;

    @Column(name = "contractor_license_number")
    private String contractor_license_number;

    @Column(name = "license_expiration_date")
    private String license_expiration_date;

    @Column(name = "supervisor_last_name")
    private String supervisor_last_name;

    @Column(name = "supervisor_first_name")
    private String supervisor_first_name;

    @Column(name = "supervisor_middle_name")
    private String supervisor_middle_name;

    @Column(name = "master_craftsman_certificate_number")
    private String master_craftsman_certificate_number;

    @Column(name = "certificate_expiration_date")
    private String certificate_expiration_date;

    @Column(name = "business_license_expiration_date")
    private String business_license_expiration_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getBusiness_address() {
        return business_address;
    }

    public void setBusiness_address(String business_address) {
        this.business_address = business_address;
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

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getBusiness_phone() {
        return business_phone;
    }

    public void setBusiness_phone(String business_phone) {
        this.business_phone = business_phone;
    }

    public String getContractor_license_number() {
        return contractor_license_number;
    }

    public void setContractor_license_number(String contractor_license_number) {
        this.contractor_license_number = contractor_license_number;
    }

    public String getLicense_expiration_date() {
        return license_expiration_date;
    }

    public void setLicense_expiration_date(String license_expiration_date) {
        this.license_expiration_date = license_expiration_date;
    }

    public String getSupervisor_last_name() {
        return supervisor_last_name;
    }

    public void setSupervisor_last_name(String supervisor_last_name) {
        this.supervisor_last_name = supervisor_last_name;
    }

    public String getSupervisor_first_name() {
        return supervisor_first_name;
    }

    public void setSupervisor_first_name(String supervisor_first_name) {
        this.supervisor_first_name = supervisor_first_name;
    }

    public String getSupervisor_middle_name() {
        return supervisor_middle_name;
    }

    public void setSupervisor_middle_name(String supervisor_middle_name) {
        this.supervisor_middle_name = supervisor_middle_name;
    }

    public String getMaster_craftsman_certificate_number() {
        return master_craftsman_certificate_number;
    }

    public void setMaster_craftsman_certificate_number(String master_craftsman_certificate_number) {
        this.master_craftsman_certificate_number = master_craftsman_certificate_number;
    }

    public String getCertificate_expiration_date() {
        return certificate_expiration_date;
    }

    public void setCertificate_expiration_date(String certificate_expiration_date) {
        this.certificate_expiration_date = certificate_expiration_date;
    }

    public String getBusiness_license_expiration_date() {
        return business_license_expiration_date;
    }

    public void setBusiness_license_expiration_date(String business_license_expiration_date) {
        this.business_license_expiration_date = business_license_expiration_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CityLicenses cityLicenses = (CityLicenses) o;
        if(cityLicenses.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, cityLicenses.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CityLicenses{" +
            "id=" + id +
            ", company_name='" + company_name + "'" +
            ", business_address='" + business_address + "'" +
            ", city='" + city + "'" +
            ", state='" + state + "'" +
            ", zip_code='" + zip_code + "'" +
            ", business_phone='" + business_phone + "'" +
            ", contractor_license_number='" + contractor_license_number + "'" +
            ", license_expiration_date='" + license_expiration_date + "'" +
            ", supervisor_last_name='" + supervisor_last_name + "'" +
            ", supervisor_first_name='" + supervisor_first_name + "'" +
            ", supervisor_middle_name='" + supervisor_middle_name + "'" +
            ", master_craftsman_certificate_number='" + master_craftsman_certificate_number + "'" +
            ", certificate_expiration_date='" + certificate_expiration_date + "'" +
            ", business_license_expiration_date='" + business_license_expiration_date + "'" +
            '}';
    }
}
