package edu.umkc.permitme.repository;

import edu.umkc.permitme.domain.Project;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Project entity.
 */
@SuppressWarnings("unused")
public interface ProjectRepository extends JpaRepository<Project,Long> {
	
	List<Project> findByContractorId(Long id);

}
