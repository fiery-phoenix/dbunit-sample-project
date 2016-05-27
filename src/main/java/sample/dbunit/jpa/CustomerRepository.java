package sample.dbunit.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import sample.dbunit.jpa.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByLastName(String lastName);
}
