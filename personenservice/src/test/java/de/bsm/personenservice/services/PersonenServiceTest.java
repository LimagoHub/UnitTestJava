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

import java.util.Arrays;
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
	
	@Test
	public void findAllJohns_3JohnsInList_returns3Johns() throws Exception {
		final List<Person> allPersons = Arrays.asList(
				new Person("John","Doe"),
				new Person("Max","Mustermann"),
				new Person("Jane","Doe"),
				new Person("John","Wayne"),
				new Person("John","Rambo"),
				new Person("John Boy","Walton")
		);
		
		when(repositoryMock.findAll()).thenReturn(allPersons);
		List<Person> allJohns =  underTest.findAllJohns();
		assertEquals(3, allJohns.size());
		
		for (Person person : allJohns) {
			assertEquals("John", person.getVorname());
		}
	}

	@Test
	public void findAllJohns_RuntimeExceptionInUnderlyingService_ThrowsPersonenServiceException() throws Exception {
				
		try {
			when(repositoryMock.findAll()).thenThrow(new RuntimeException("Upps"));
			underTest.findAllJohns();
			fail("Unerwartet keine Exception");
			
		} catch (PersonServiceException e) {
			assertEquals("Interner Server Fehler", e.getMessage());
		}
	}

}
