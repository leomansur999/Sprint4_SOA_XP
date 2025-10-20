package br.com.fiap.Sprint4_SOA_XP;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TesteEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = "$2a$10$XfB58KVGR5RarnPY6KjEH.dMPjWMBskLry9dalODF21vzioFHLJjW"; // o seu hash
        System.out.println("Senha confere? " + encoder.matches("123456", hash));
    }
}
