package de.bsm.bestellung.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.bsm.bestellung.repositories.BestellRepo;

@RunWith(MockitoJUnitRunner.class)
public class BestellServiceImplTest {
	
	private static final String VALID_ORDER = "Nagel";
	private static final String VALID_MASTERCARD_NUMBER = "M0123456789";
	private static final String VALID_VISACARD_NUMBER = "V0123456789";
	private static final double VALID_AMOUNT = 10.0;
	
	@Mock
	private BestellRepo bestellRepoMock;

	@Mock
	private CreditcardChecker creditcardCheckerMock;
	@InjectMocks
	private BestellServiceImpl underTest;
	
	
	@Test
	public void bestellungErfassen_BestellungIsNull_throwsBestelServiceException() throws Exception {
		try {
			underTest.bestellungErfassen(null, VALID_MASTERCARD_NUMBER, VALID_AMOUNT);
			fail("SChwein gehabt");
		} catch (BestellServiceException e) {
			assertEquals("Bestellung darf nicht null sein.", e.getMessage());
		} 
	}

	@Test
	public void bestellungErfassen_CreditCardNumberNull_throwsBestelServiceException() throws Exception {
		try {
			underTest.bestellungErfassen(VALID_ORDER, null, VALID_AMOUNT);
			fail("SChwein gehabt");
		} catch (BestellServiceException e) {
			assertEquals("Kreditkartennummer darf nicht null sein.", e.getMessage());
		} 
	}

}
