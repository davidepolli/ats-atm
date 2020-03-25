package it.noema.ats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.noema.ats.model.ATM;

public interface ATMRepository extends JpaRepository<ATM, Long> {

	public List<ATM> findByCity(String city);
	
	@Query("SELECT a FROM #{#entityName} a WHERE "
			+ "a.street LIKE %?1% "
			+ "OR a.postalcode LIKE %?1% "
			+ "OR a.housenumber LIKE %?1% "
			+ "OR a.city LIKE %?1% "
			+ "OR a.type LIKE %?1% ")
	public List<ATM> findByKeyword(String keyword);
}


//package it.noema.ats.atmweb.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import it.noema.ats.atmweb.model.ATM;
//
//public interface ATMRepository extends JpaRepository<ATM, Long> {
//
//	public List<ATM> findByCity(String city);
//	
//	@Query("SELECT a FROM #{#entityName} a WHERE "
//			+ "LOWER(a.street) like '%" + "LOWER(?1)" + "%' "
//			+ "OR LOWER(a.postalcode) like '%" + "LOWER(?1)" + "%' "
//			+ "OR LOWER(a.housenumber) like '%" + "LOWER(?1)" + "%' "
//			+ "OR LOWER(a.city) like '%" + "LOWER(?1)" + "%' "
//			+ "OR LOWER(a.type) like '%" + "LOWER(?1)" + "%' ")
//	public List<ATM> findByKeyword(String keyword);
//	
//	
//}
