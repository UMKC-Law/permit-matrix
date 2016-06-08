package edu.umkc.permitme.repository;

import edu.umkc.permitme.domain.RightOfWay;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the RightOfWay entity.
 */
@SuppressWarnings("unused")
public interface RightOfWayRepository extends JpaRepository<RightOfWay,Long> {

}
