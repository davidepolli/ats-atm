package it.noema.ats.exception;

public class ServiceException extends Exception {

	public ServiceException(Exception e) {
		super(e);
	}
	
	public ServiceException(String message) {
		super(message);
	}
}
