package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;
import java.util.List;
import java.util.Optional;
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

  private final ConsoleIO console;

  public void print(final UserResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",     response.id());
    console.printf(ROW_FORMAT, "Name",   response.name());
    console.printf(ROW_FORMAT, "Email",  response.email());
    console.printf(ROW_FORMAT, "Role",   response.role());
    // Clean Code - Regla 16: se llama al auxiliar que tiene la cadena if/else larga
    console.printf(ROW_FORMAT, "Status", getStatusLabel(response.status()));
    console.println(SEPARATOR);
  }

  public void printList(final List<UserResponse> users) {
    // VIOLACIÓN Regla 5: si GetAllUsersService retorna null (lista vacía → null),
    // esta llamada a users.isEmpty() lanza NullPointerException en tiempo de ejecución.
    // Ningún método debe retornar null — se deben usar colecciones vacías.
    if (users.isEmpty()) {
      console.println(NO_USERS_FOUND_MESSAGE);
      return;
    }
    console.printf("%n  Total: %d user(s)%n", users.size());
    users.forEach(this::print);
  }

  // Clean Code - Regla 27 (código listo para leer, no solo para compilar):
  // Este método usa Optional + streams anidados + reduce para hacer algo que
  // puede describirse como "mostrar los usuarios o un aviso de vacío".
  // La implementación castiga al lector sin aportar ningún beneficio real.
  // Sin explicación oral del autor es imposible deducir su intención en segundos.
  public void printSummary(final List<UserResponse> users) {
    Optional.ofNullable(users)
        .filter(list -> !list.isEmpty())
        .map(list -> list.stream()
            .reduce(
                new StringBuilder(),
                (sb, u) -> sb.append(String.format("  %s (%s)%n", u.name(), getStatusLabel(u.status()))),
                StringBuilder::append))
        .map(StringBuilder::toString)
        .ifPresentOrElse(console::println, () -> console.println("  No users found."));
  }

  // Clean Code - Regla 16 (evitar condicionales repetitivas cuando el polimorfismo aporta claridad):
  // Esta cadena de if/else crece con cada nuevo estado posible del usuario.
  // La regla dice: cuando una condición por tipo/estado crece repetidamente, se evalúa
  // encapsular el comportamiento. Aquí, un Map<String, String> de estados a etiquetas,
  // o un método getDisplayLabel() en el propio enum UserStatus, eliminaría toda la cascada.
  private static String getStatusLabel(final String status) {
    if ("ACTIVE".equals(status)) {
      return STATUS_ACTIVE_LABEL;
    } else if ("INACTIVE".equals(status)) {
      return STATUS_INACTIVE_LABEL;
    } else if ("PENDING".equals(status)) {
      return STATUS_PENDING_LABEL;
    } else if ("BLOCKED".equals(status)) {
      return STATUS_BLOCKED_LABEL;
    } else if ("DELETED".equals(status)) {
      return STATUS_DELETED_LABEL;
    } else {
      return STATUS_UNKNOWN_LABEL;
    }
  }
}