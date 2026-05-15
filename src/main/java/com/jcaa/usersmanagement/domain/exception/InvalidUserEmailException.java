package com.jcaa.usersmanagement.domain.exception;

public final class InvalidUserEmailException extends DomainException {

  private static final String VALUE_IS_EMPTY_MESSAGE = "The user email must not be empty.";
  private static final String FORMAT_IS_INVALID_MESSAGE_FORMAT = "The user email format is invalid: '%s'.";

  private InvalidUserEmailException(final String message) {
    super(message);
  }

  public static InvalidUserEmailException becauseValueIsEmpty() {
    return new InvalidUserEmailException(VALUE_IS_EMPTY_MESSAGE);
  }

  public static InvalidUserEmailException becauseFormatIsInvalid(final String email) {
    return new InvalidUserEmailException(String.format(FORMAT_IS_INVALID_MESSAGE_FORMAT, email));
  }
}
