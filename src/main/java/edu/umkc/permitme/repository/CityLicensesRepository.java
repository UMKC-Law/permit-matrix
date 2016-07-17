package edu.umkc.permitme.repository;

import edu.umkc.permitme.domain.CityLicenses;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CityLicenses entity.
 */
@SuppressWarnings("unused")
public interface CityLicensesRepository extends JpaRepository<CityLicenses,Long> {

	@Query("select cityLicense from CityLicenses cityLicense where cityLicense.contractor_license_number = ?1")
	public CityLicenses findOneByContractorLicenseNumber(String contractorLicenseNumber);

}
