package de.bsm.personenservice.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.bsm.personenservice.repositories.Person;
import de.bsm.personenservice.repositories.PersonenRepository;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;
@RunWith(MockitoJUnitRunner.class)
public class PersonenServiceTest {
	@Mock
	private PersonenRepository repositoryMock;
	@Mock
	private List<String> antipathen;
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

	@Test
	public void speichern_WrongVornameTooShort_ThrowsPersonenServiceException () throws Exception {
		try {
			final Person person = new Person("J","Doe");
			underTest.speichern(person);
			fail("Unerwartet keine Exception");
		} catch (PersonServiceException e) {
			assertEquals("Vorname zu kurz", e.getMessage());
		}
	}

	@Test
	public void speichern_WrongNachnameNull_ThrowsPersonenServiceException () throws Exception {
		try {
			final Person person = new Person("John",null);
			underTest.speichern(person);
			fail("Unerwartet keine Exception");
		} catch (PersonServiceException e) {
			assertEquals("Nachname zu kurz", e.getMessage());
		}
	}

	@Test
	public void speichern_WrongNachnameTooShort_ThrowsPersonenServiceException () throws Exception {
		try {
			final Person person = new Person("John","D");
			underTest.speichern(person);
			fail("Unerwartet keine Exception");
		} catch (PersonServiceException e) {
			assertEquals("Nachname zu kurz", e.getMessage());
		}
	}

	@Test
	public void speichern_Antipath_ThrowsPersonenServiceException () throws Exception {
		
		when(antipathen.contains(anyString())).thenReturn(true);
		
		try {
			final Person person = new Person("John","Doe");
			underTest.speichern(person);
			fail("Unerwartet keine Exception");
		} catch (PersonServiceException e) {
			assertEquals("Antipath", e.getMessage());
		}
	}

	@Test
	public void speichern_RuntimeExceptionInUnderlyingService_ThrowsPersonenServiceException () throws Exception {
		
		when(antipathen.contains(anyString())).thenReturn(false);
		doThrow(new RuntimeException("Upps")).when(repositoryMock).saveOrUpdate((Person) any()); 
		
		try {
			final Person person = new Person("John","Doe");
			underTest.speichern(person);
			fail("Unerwartet keine Exception");
		} catch (PersonServiceException e) {
			assertEquals("Interner Server Fehler", e.getMessage());
		}
	}

	@Test
	public void speichern_HappyDay_ValidIdIsSet () throws Exception {
			when(antipathen.contains(anyString())).thenReturn(false);
		
			final Person person = new Person("John","Doe");
			underTest.speichern(person);
			verify(repositoryMock).saveOrUpdate(person);
			assertNotNull(person.getId());
	}
	@Test
	public void speichern_HappyDay_PersonSavedInRepository () throws Exception {
			when(antipathen.contains(anyString())).thenReturn(false);
		
			final Person person = new Person("John","Doe");
			underTest.speichern(person);
			verify(repositoryMock).saveOrUpdate(person);
	}

}
