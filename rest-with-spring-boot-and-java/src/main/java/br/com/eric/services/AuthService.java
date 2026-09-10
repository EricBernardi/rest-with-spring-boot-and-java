package br.com.eric.services;

import br.com.eric.repository.UserRepository;
import br.com.eric.utils.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public String authenticate(String username, String password) {
        UserDetails user = loadUserByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtUtil.generateToken(username);
    }
}
