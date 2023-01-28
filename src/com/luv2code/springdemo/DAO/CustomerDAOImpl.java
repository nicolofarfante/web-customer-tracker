package com.luv2code.springdemo.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query ... sort by lastName
		Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = query.getResultList();
		
		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Customer customer = currentSession.get(Customer.class, id);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("delete from Customer where id=:id");
		
		query.setParameter("id", id);
		
		query.executeUpdate();
	}

}
