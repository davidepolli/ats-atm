package it.noema.ats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.noema.ats.model.ATM;

/**
 * use of spring data for accessing record in the persistence with JPA
 * @author Noema STI
 *
 */
public interface ATMRepository extends JpaRepository<ATM, Long> {

	public List<ATM> findByCityContainingIgnoreCase(String city);
	
	@Query("SELECT a FROM #{#entityName} a WHERE "
			+ "lower(a.street) LIKE %?1% "
			+ "OR lower(a.postalcode) LIKE %?1% "
			+ "OR lower(a.housenumber) LIKE %?1% "
			+ "OR lower(a.city) LIKE %?1% "
			+ "OR lower(a.type) LIKE %?1% ")
	public List<ATM> findByKeyword(String keyword);
	
	@Query("SELECT DISTINCT a.city FROM #{#entityName} a ORDER BY a.city ASC")
	public List<String> findCities();
	
}

