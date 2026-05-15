package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UserResponsePrinter {

  private static final String SEPARATOR = "-".repeat(52);
  private static final String ROW_FORMAT = "  %-10s : %s%n";
  private static final String NO_USERS_FOUND_MESSAGE = "  No users found.";
  private static final String STATUS_ACTIVE_LABEL = "Activo";
  private static final String STATUS_INACTIVE_LABEL = "Inactivo";
  private static final String STATUS_PENDING_LABEL = "Pendiente de activacion";
  private static final String STATUS_BLOCKED_LABEL = "Bloqueado";
  private static final String STATUS_DELETED_LABEL = "Eliminado";
  private static final String STATUS_UNKNOWN_LABEL = "Estado desconocido";

  private static final Map<String, String> STATUS_LABELS = Map.of(
      "ACTIVE", STATUS_ACTIVE_LABEL,
      "INACTIVE", STATUS_INACTIVE_LABEL,
      "PENDING", STATUS_PENDING_LABEL,
      "BLOCKED", STATUS_BLOCKED_LABEL,
      "DELETED", STATUS_DELETED_LABEL
  );

  private final ConsoleIO console;

  public void print(final UserResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",     response.id());
    console.printf(ROW_FORMAT, "Name",   response.name());
    console.printf(ROW_FORMAT, "Email",  response.email());
    console.printf(ROW_FORMAT, "Role",   response.role());
    console.printf(ROW_FORMAT, "Status", getStatusLabel(response.status()));
    console.println(SEPARATOR);
  }

  public void printList(final List<UserResponse> users) {
    if (users == null || users.isEmpty()) {
      console.println(NO_USERS_FOUND_MESSAGE);
      return;
    }
    console.printf("%n  Total: %d user(s)%n", users.size());
    users.forEach(this::print);
  }

  public void printSummary(final List<UserResponse> users) {
    if (users == null || users.isEmpty()) {
      console.println(NO_USERS_FOUND_MESSAGE);
      return;
    }
    for (final UserResponse u : users) {
      console.printf("  %s (%s)%n", u.name(), getStatusLabel(u.status()));
    }
  }

  private static String getStatusLabel(final String status) {
    return STATUS_LABELS.getOrDefault(status, STATUS_UNKNOWN_LABEL);
  }
}