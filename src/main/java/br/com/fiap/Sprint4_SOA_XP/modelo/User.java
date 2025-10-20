package br.com.fiap.Sprint4_SOA_XP.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EntityListeners(User.RoleNormalizer.class) // ✅ ativa listener interno
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // ✅ Construtor auxiliar
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // ✅ Listener interno: garante role em maiúsculo antes de persistir
    public static class RoleNormalizer {
        @PrePersist
        @PreUpdate
        public void normalizeRole(User user) {
            if (user.getRole() != null) {
                try {
                    user.setRole(Role.valueOf(user.getRole().name().toUpperCase()));
                } catch (Exception e) {
                    user.setRole(Role.USER); // fallback seguro
                }
            } else {
                user.setRole(Role.USER); // padrão
            }
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + role.name());
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
