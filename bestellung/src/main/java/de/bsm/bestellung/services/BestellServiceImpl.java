package de.bsm.bestellung.services;

import de.bsm.bestellung.repositories.BestellRepo;

public class BestellServiceImpl implements BestellService {

	private final CreditcardChecker creditcardChecker;
	private final BestellRepo bestellRepo;
	
	
	

	public BestellServiceImpl(CreditcardChecker creditcardChecker, BestellRepo bestellRepo) {
		this.creditcardChecker = creditcardChecker;
		this.bestellRepo = bestellRepo;
	}




	@Override
	public void bestellungErfassen(String bestellung, String kreditkartenNummer, double amount) throws BestellServiceException, KundePleiteException, ServerException {
		if(bestellung == null)
			throw new BestellServiceException("Bestellung darf nicht null sein.");
		
		if(kreditkartenNummer == null)
			throw new BestellServiceException("Kreditkartennummer darf nicht null sein.");

	}

}
