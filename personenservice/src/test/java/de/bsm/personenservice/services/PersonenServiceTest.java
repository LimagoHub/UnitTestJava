package de.bsm.personenservice.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.bsm.personenservice.repositories.Person;
import de.bsm.personenservice.repositories.PersonenRepository;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonenServiceTest {
	@Mock
	private PersonenRepository repositoryMock;
	@InjectMocks
	private PersonenService underTest;
	
	
	@Test
	public void speichern_WrongParameterNull_ThrowsPersonenServiceException () throws Exception {
		try {
			underTest.speichern(null);
			fail("Unerwartet keine Exception");
		} catch (PersonServiceException e) {
			assertEquals("Person darf nicht null sein.", e.getMessage());
		}
	}

	@Test
	public void speichern_WrongVornameNull_ThrowsPersonenServiceException () throws Exception {
		try {
			final Person person = new Person(null,"Doe");
			underTest.speichern(person);
			fail("Unerwartet keine Exception");
		} catch (PersonServiceException e) {
			assertEquals("Vorname zu kurz", e.getMessage());
		}
	}

}
