package org.khanal.SbHibernateShoppingcart.controllers.auth;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Throwable cause){
        super(message, cause);
    }
}
