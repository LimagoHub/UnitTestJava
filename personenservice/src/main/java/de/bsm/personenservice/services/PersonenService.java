package de.bsm.personenservice.services;

import java.util.List;
import java.util.UUID;

import de.bsm.personenservice.repositories.Person;
import de.bsm.personenservice.repositories.PersonenRepository;

public class PersonenService {
	
	private final PersonenRepository repository;
	private final List<String> antipathen;

	public PersonenService(PersonenRepository repository, final List<String> antipathen) {
		this.repository = repository;
		this.antipathen = antipathen;
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
			speichernImpl(person);
		} catch (RuntimeException e) {
			throw new PersonServiceException("Interner Server Fehler",e);
		}
		
	}

	private void speichernImpl(Person person) throws PersonServiceException {
		checkPerson(person);
		person.setId(UUID.randomUUID().toString());
		repository.saveOrUpdate(person);
	}

	private void checkPerson(Person person) throws PersonServiceException {
		validatedPerson(person);
		fachlichePruefung(person);
	}

	private void fachlichePruefung(Person person) throws PersonServiceException {
		if(antipathen.contains(person.getVorname()))
			throw new PersonServiceException("Antipath");
	}

	private void validatedPerson(Person person) throws PersonServiceException {
		if(person == null)
			throw new PersonServiceException("Person darf nicht null sein.");
		
		if(person.getVorname() == null || person.getVorname().length() < 2)
			throw new PersonServiceException("Vorname zu kurz");
		
		if(person.getNachname() == null || person.getNachname().length() < 2)
			throw new PersonServiceException("Nachname zu kurz");
	}
	
	public List<Person> findAllJohns() throws PersonServiceException{
		return null;
	}
}
