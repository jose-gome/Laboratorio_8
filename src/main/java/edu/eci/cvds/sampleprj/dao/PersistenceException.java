package edu.eci.cvds.sampleprj.dao;

public class PersistenceException extends Exception {

	public PersistenceException(String message, org.apache.ibatis.exceptions.PersistenceException e) {
		super(message, e);
		
	}
	
	
	
}

