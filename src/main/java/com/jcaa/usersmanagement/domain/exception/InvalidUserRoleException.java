package com.jcaa.usersmanagement.domain.exception;

public final class InvalidUserRoleException extends DomainException {

  private static final String VALUE_IS_INVALID_MESSAGE_FORMAT = "The user role '%s' is not valid.";

  private InvalidUserRoleException(final String message) {
    super(message);
  }

  public static InvalidUserRoleException becauseValueIsInvalid(final String role) {
    return new InvalidUserRoleException(String.format(VALUE_IS_INVALID_MESSAGE_FORMAT, role));
  }
}
