package worktimemanagement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        // Если аргумент передан, использует его как исходный пароль, иначе по умолчанию.
        String password = args.length > 0 ? args[0] : "130106";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);
        System.out.println("Исходный пароль: " + password);
        System.out.println("Хэшированный пароль: " + hashedPassword);
    }
}
