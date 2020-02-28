package de.bsm.bestellung.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.bsm.bestellung.repositories.BestellRepo;

public class BestellServiceImpl implements BestellService {

	private final CreditcardChecker creditcardChecker;
	private final BestellRepo bestellRepo;
	private final Pattern pattern = Pattern.compile("^(M|V)(\\d{10})$");
	

	public BestellServiceImpl(CreditcardChecker creditcardChecker, BestellRepo bestellRepo) {
		this.creditcardChecker = creditcardChecker;
		this.bestellRepo = bestellRepo;
	}

	@Override
	public void bestellungErfassen(String bestellung, String kreditkartenNummer, double amount) throws BestellServiceException, KundePleiteException, ServerException {
		try {
			
			if(bestellung == null)
				throw new BestellServiceException("Bestellung darf nicht null sein.");
			
			if(kreditkartenNummer == null)
				throw new BestellServiceException("Kreditkartennummer darf nicht null sein.");
			
			if(amount < 0.0) 
				throw new BestellServiceException("Wert der Bestellung darf nicht negativ sein.");
			
			final Matcher matcher = pattern.matcher(kreditkartenNummer);
			if(! matcher.matches()) throw new BestellServiceException("Kreditkartennummer hat ein falsches Format.");
			
			final String type = matcher.group(1).equals("M") ? "MASTER" : "VISA";
			final String number = matcher.group(2);
			
			if (creditcardChecker.checkCreditcard(type, number, amount)) 
				bestellRepo.save(bestellung);
			else
				throw new KundePleiteException();
			
		} catch (RuntimeException | CreditCardServerException e) {
			throw new ServerException("Interner Serverfehler", e);
		} 

	}

}
