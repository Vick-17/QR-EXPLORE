package com.projectspring.api.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.projectspring.api.dtos.UserDto;
import com.projectspring.api.entities.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectspring.api.generic.GenericService;
import com.projectspring.api.generic.GenericServiceImpl;
import com.projectspring.api.mappers.UserMapper;
import com.projectspring.api.Entities.Role;
import com.projectspring.api.repositories.RoleRepository;
import com.projectspring.api.repositories.UserRepository;

import jakarta.transaction.Transactional;

/**
 * Classe encapsulant le code permettant de gérer les utilisateurs.
 * Doit nécessairement implémenter "UserDetailsService" qui sera utilisée par
 * "AuthenticationManager"
 */
@Service
public class UserServiceImpl
        extends GenericServiceImpl<
        User,
        UserDto,
        UserRepository,
        UserMapper> implements UserDetailsService, UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String USER_NOT_FOUND_MESSAGE = "L'utilisateur avec le nom {} n'existe pas.";
    private static final String USER_FOUND_MESSAGE = "L'utilisateur avec le nom {} existe en base de données.";

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, UserRepository userRepository, RoleRepository roleRepository) {
        super(repository, mapper);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Instancie un objet "User" qui est une instance d'une classe héritant de
     * "UserDetails"
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ATTENTION -> objet de la classe "models.User"
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            // pas d'utilisateur, on renvoie une exception
            String message = String.format(USER_NOT_FOUND_MESSAGE, username);
            LOGGER.error(message);
            throw new UsernameNotFoundException(message);
        } else {
            // utilisateur retrouvé, on instancie une liste d' "authorities" qui
            // correspondent à des roles
            LOGGER.debug(USER_FOUND_MESSAGE, username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.get().getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });

            /**
             * ATTENTION -> instanciation d'un objet de la classe "User" provenant du
             * package "org.springframework.security.core.userdetails"
             * Cette classe hérite de "UserDetails" et est propre au framework de sécurité
             * de Spring.
             */
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),
                    authorities);
        }
    }
    //TODO: erreurs détectées dans cette méthode (à corriger)
//    public UserDto createUser(UserDto user) {
//        Optional<User> existingUser = repository.findByUsername(user.getUsername());
//        if (existingUser.isPresent() && existingUser.get().getUsername().equals(user.getUsername())) {
//            throw new RuntimeException("L'adresse ou le nom d'utilisateur e-mail est déjà utilisée.");
//        }
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String passwordEncode = bCryptPasswordEncoder.encode(user.getPassword());
//        user.setPassword(passwordEncode);
//        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
//        if (userRole.isEmpty()) {
//            userRole.setName("ROLE_USER");
//            roleRepository.save(userRole.get());
//        }
//        user.getRoles().add(toEntity(userRole.get()));
//        return saveOrUpdate(user);
//    }



}
