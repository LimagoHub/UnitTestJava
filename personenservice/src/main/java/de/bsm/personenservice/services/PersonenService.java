package de.bsm.personenservice.services;

import de.bsm.personenservice.repositories.Person;
import de.bsm.personenservice.repositories.PersonenRepository;

public class PersonenService {
	
	private final PersonenRepository repository;

	public PersonenService(PersonenRepository repository) {
		this.repository = repository;
	}
	
	/*
	 * parameter darf nicht null sein
	 * weder Vorname noch Nachname dürfen Null sein
	 * weder Vorname noch Nachname dürfen weniger als 2 Zeichen haben
	 * 
	 * Kein Attila
	 * 
	 * 
	 * Technische Exceptions in PSE wandeln
	 */
	public void speichern(Person person) throws PersonServiceException {
		if(person == null)
			throw new PersonServiceException("Person darf nicht null sein.");
		
		if(person.getVorname() == null)
			throw new PersonServiceException("Vorname zu kurz");
	}
	

}
