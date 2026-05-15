package com.jcaa.usersmanagement.domain.exception;

public final class InvalidUserNameException extends DomainException {

  private static final String VALUE_IS_EMPTY_MESSAGE = "The user name must not be empty.";
  private static final String LENGTH_IS_TOO_SHORT_MESSAGE_FORMAT = "The user name must have at least %d characters.";

  private InvalidUserNameException(final String message) {
    super(message);
  }

  public static InvalidUserNameException becauseValueIsEmpty() {
    return new InvalidUserNameException(VALUE_IS_EMPTY_MESSAGE);
  }

  public static InvalidUserNameException becauseLengthIsTooShort(final int minimumLength) {
    return new InvalidUserNameException(
        String.format(LENGTH_IS_TOO_SHORT_MESSAGE_FORMAT, minimumLength));
  }
}
