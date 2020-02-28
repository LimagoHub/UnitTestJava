package de.bsm.bestellung.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
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
	
	
	@Test
	public void bestellungErfassen_WrongCreditCardFormattooShort_throwsBestellServiceException() throws Exception{
		try {
			underTest.bestellungErfassen(VALID_ORDER, "V012345678", VALID_AMOUNT);
			fail("SChwein gehabt");
		} catch (BestellServiceException e) {
			assertEquals("Kreditkartennummer hat ein falsches Format.", e.getMessage());
		} 
		
	}
	
	
	@Test
	public void bestellungErfassen_WrongCreditCardFormattooLong_throwsBestellServiceException() throws Exception{
		try {
			underTest.bestellungErfassen(VALID_ORDER, "V01234567890", VALID_AMOUNT);
			fail("SChwein gehabt");
		} catch (BestellServiceException e) {
			assertEquals("Kreditkartennummer hat ein falsches Format.", e.getMessage());
		} 
		
	}
	
	@Test
	public void bestellungErfassen_WrongCreditCardFormatNeitherVisaNorMaster_throwsBestellServiceException() throws Exception{
		try {
			underTest.bestellungErfassen(VALID_ORDER, "X0123456789", VALID_AMOUNT);
			fail("SChwein gehabt");
		} catch (BestellServiceException e) {
			assertEquals("Kreditkartennummer hat ein falsches Format.", e.getMessage());
		} 
		
	}
	
	
	@Test
	public void bestellungErfassen_AmountNegative_throwsBestellServiceException() throws Exception{
		try {
			underTest.bestellungErfassen(VALID_ORDER, VALID_MASTERCARD_NUMBER, -10.0);
			fail("SChwein gehabt");
		} catch (BestellServiceException e) {
			assertEquals("Wert der Bestellung darf nicht negativ sein.", e.getMessage());
		} 
		
	}
	
	@Test(expected = KundePleiteException.class)
	public void bestellungErfassen_KundeInsolvent_throwsKundeIstPleiteException() throws Exception{
		
		when(creditcardCheckerMock.checkCreditcard(anyString(), anyString(), anyDouble())).thenReturn(false);
		
		underTest.bestellungErfassen(VALID_ORDER, VALID_MASTERCARD_NUMBER, VALID_AMOUNT);
		
		
	}

	@Test(expected = ServerException.class)
	public void bestellungErfassen_CreditcardServerDown_throwsServerDownException() throws Exception{
		
		when(creditcardCheckerMock.checkCreditcard(anyString(), anyString(), anyDouble())).thenThrow(new CreditCardServerException());

		underTest.bestellungErfassen(VALID_ORDER, VALID_MASTERCARD_NUMBER, VALID_AMOUNT);
	}
	
	@Test(expected = ServerException.class)
	public void bestellungErfassen_UnexpectedRuntimeException_throwsServerDownException() throws Exception{
		
		when(creditcardCheckerMock.checkCreditcard(anyString(), anyString(), anyDouble())).thenThrow(new ArrayIndexOutOfBoundsException());

		underTest.bestellungErfassen(VALID_ORDER, VALID_MASTERCARD_NUMBER, VALID_AMOUNT);
	}
	
	@Test
	public void bestellungErfassen_CreditCardServerCall_CreditCardServerReceivesExpectedParameters() throws Exception{
		
		when(creditcardCheckerMock.checkCreditcard(anyString(), anyString(), anyDouble())).thenReturn(true);
		underTest.bestellungErfassen(VALID_ORDER, VALID_MASTERCARD_NUMBER, VALID_AMOUNT);
		verify(creditcardCheckerMock).checkCreditcard("MASTER", "0123456789", VALID_AMOUNT);
	}
	
	
	@Test(expected = ServerException.class)
	public void bestellungErfassen_RepositoryDown_throwsServerDownException() throws Exception{
		
		when(creditcardCheckerMock.checkCreditcard(anyString(), anyString(), anyDouble())).thenReturn(true);
		doThrow(new RuntimeException("Upps")).when(bestellRepoMock).save(anyString());
		

		underTest.bestellungErfassen(VALID_ORDER, VALID_MASTERCARD_NUMBER, VALID_AMOUNT);
	}
	
	
	
	

	@Test
	public void bestellungErfassen_ProperUseMaster_success() throws Exception{
		
		when(creditcardCheckerMock.checkCreditcard(anyString(), anyString(), anyDouble())).thenReturn(true);
		
		underTest.bestellungErfassen(VALID_ORDER, VALID_MASTERCARD_NUMBER, VALID_AMOUNT);
		
		verify(bestellRepoMock).save(VALID_ORDER);
	}
	

	@Test
	public void bestellungErfassen_ProperUseVisa_success() throws Exception{
		
		when(creditcardCheckerMock.checkCreditcard(anyString(), anyString(), anyDouble())).thenReturn(true);
		
		underTest.bestellungErfassen(VALID_ORDER, VALID_VISACARD_NUMBER, VALID_AMOUNT);
		
		verify(bestellRepoMock).save(VALID_ORDER);
	}
	
	
	@Test
	public void bestellungErfassen_ServiceInOrder_success() throws Exception{
		
		when(creditcardCheckerMock.checkCreditcard(anyString(), anyString(), anyDouble())).thenReturn(true);
		
		underTest.bestellungErfassen(VALID_ORDER, VALID_MASTERCARD_NUMBER, VALID_AMOUNT);
		InOrder inOrder = inOrder(creditcardCheckerMock, bestellRepoMock);
		
		inOrder.verify(creditcardCheckerMock).checkCreditcard("MASTER", "0123456789", VALID_AMOUNT);
		inOrder.verify(bestellRepoMock).save(VALID_ORDER);
	}


//	InOrder inOrder = inOrder(firstMock, secondMock);
//
//	//following will make sure that firstMock was called before secondMock
//	inOrder.verify(firstMock).methodOne();
//	inOrder.verify(secondMock).methodTwo();
	

}
