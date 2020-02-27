package de.bsm.personenservice.repositories;

import java.util.List;

public interface PersonenRepository {
	
	public void saveOrUpdate(Person person);
	public void delete(Person person);
	public Person findById(String id);
	public List<Person> findAll();

}
