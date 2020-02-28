package de.bsm.bestellung.services;

public interface CreditcardChecker {
	
	
	boolean checkCreditcard(String type /* entweder MASTER oder VISA */, String number /* exakt 10 Ziffern*/, double amount) throws CreditCardServerException; /* potenzielle RuntimeException */

}
