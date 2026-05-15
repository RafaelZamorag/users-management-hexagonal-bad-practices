package com.jcaa.usersmanagement.domain.exception;

public final class EmailSenderException extends DomainException {

  private static final String SMTP_FAILED_MESSAGE_FORMAT = "Could not send email to '%s'. SMTP Error: %s";
  private static final String SEND_FAILED_MESSAGE = "The email notification could not be sent.";

  private EmailSenderException(final String message) {
    super(message);
  }

  private EmailSenderException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static EmailSenderException becauseSmtpFailed(
      final String destinationEmail, final String smtpError) {
    return new EmailSenderException(
        String.format(SMTP_FAILED_MESSAGE_FORMAT, destinationEmail, smtpError));
  }

  public static EmailSenderException becauseSendFailed(final Throwable cause) {
    return new EmailSenderException(SEND_FAILED_MESSAGE, cause);
  }
}
