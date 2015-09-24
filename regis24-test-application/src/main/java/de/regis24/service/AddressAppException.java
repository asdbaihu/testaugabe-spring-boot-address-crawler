package de.regis24.service;

/**
 * Created by vbourdine on 22.09.2015.
 */

public class AddressAppException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddressAppException() {
        super();
    }

    public AddressAppException(String message) {
        super(message);
    }

    public AddressAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressAppException(Throwable cause) {
        super(cause);
    }
}