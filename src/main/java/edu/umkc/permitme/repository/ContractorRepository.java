package edu.umkc.permitme.repository;

import edu.umkc.permitme.domain.Contractor;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Contractor entity.
 */
@SuppressWarnings("unused")
public interface ContractorRepository extends JpaRepository<Contractor,Long> {

    @Query("select contractor from Contractor contractor where contractor.user.login = ?#{principal.username}")
    List<Contractor> findByUserIsCurrentUser();

}
