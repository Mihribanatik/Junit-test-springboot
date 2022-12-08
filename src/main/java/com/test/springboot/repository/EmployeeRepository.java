package com.test.springboot.repository;

import com.test.springboot.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee,Long> {

	Optional<Employee> findByEmail(String email);

	//define custom query using JPQL with index
	@Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
	Employee findByJPQL(String firstName,String lastName);

	//define custom query using JPQL with named param
	@Query("select e from Employee e where e.firstName = :firstName and e.lastName = :lastName")
	Employee findByJPQLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

	//define custom query using Native SQL with index
	@Query(value = "select * from employee e where e.first_name = :firstName and e.last_name = :lastName",nativeQuery = true)
	Employee findByNativeSQL(@Param("firstName") String firstName, @Param("lastName") String lastName);

	Page<Employee> getAllEmployeePageable(Pageable pageable);
}
