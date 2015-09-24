package de.regis24.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import de.regis24.persistence.model.Customer;

/**
 * Created by vbourdine on 22.09.2015.
 */
@Transactional
public interface CustomerRepository  extends JpaRepository<Customer, Long>{

}
