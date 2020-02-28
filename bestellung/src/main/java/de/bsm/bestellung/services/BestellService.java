package de.bsm.bestellung.services;

public interface BestellService {
	
	
	void bestellungErfassen(String bestellung, String kreditkartenNummer, double amount) throws BestellServiceException, KundePleiteException, ServerException;

}
