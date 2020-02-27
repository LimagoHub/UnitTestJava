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
		try {
			if(person == null)
				throw new PersonServiceException("Person darf nicht null sein.");
			
			if(person.getVorname() == null || person.getVorname().length() < 2)
				throw new PersonServiceException("Vorname zu kurz");
			
			if(person.getNachname() == null || person.getNachname().length() < 2)
				throw new PersonServiceException("Nachname zu kurz");
			
			if(person.getVorname().equals("Attila"))
				throw new PersonServiceException("Antipath");
			
			repository.saveOrUpdate(person);
		} catch (RuntimeException e) {
			throw new PersonServiceException("Interner Server Fehler",e);
		}
		
	}
	

}
