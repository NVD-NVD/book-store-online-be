package com.ute.bookstoreonlinebe.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 04/20/2022
 * Time: 7:30 PM
 * Filename: UserNotFoundAuthenticationException
 */
public class UserNotFoundAuthenticationException extends AuthenticationException {
    public UserNotFoundAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotFoundAuthenticationException(String msg) {
        super(msg);
    }
}
