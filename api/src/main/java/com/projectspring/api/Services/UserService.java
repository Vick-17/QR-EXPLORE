package com.projectspring.api.Services;

import java.util.ArrayList;
import java.util.Collection;

import com.projectspring.api.Entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectspring.api.Dto.UserDto;
import com.projectspring.api.Generic.GenericService;
import com.projectspring.api.Generic.GenericServiceImpl;
import com.projectspring.api.Mappers.UserMapper;
import com.projectspring.api.Entities.Role;
import com.projectspring.api.Repositories.RoleRepository;
import com.projectspring.api.Repositories.UserRepository;

import jakarta.transaction.Transactional;

/**
 * Classe encapsulant le code permettant de gérer les utilisateurs.
 * Doit nécessairement implémenter "UserDetailsService" qui sera utilisée par
 * "AuthenticationManager"
 */
@Service
public class UserService extends GenericServiceImpl<User, Long, UserDto, UserRepository, UserMapper>
        implements UserDetailsService, GenericService<UserDto, Long> {

    

    public UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }



    private static final String USER_NOT_FOUND_MESSAGE = "L'utilisateur avec le nom %s n'existe pas.";
    private static final String USER_FOUND_MESSAGE = "L'utilisateur avec le nom %s existe en base de données.";

    @Autowired
    private UserRepository userRepositories;

    @Autowired
    private RoleRepository roleRepositories;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * Instancie un objet "User" qui est une instance d'une classe héritant de
     * "UserDetails"
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ATTENTION -> objet de la classe "models.User"
        User user = userRepositories.findByUsername(username);
        if (user == null) {
            // pas d'utilisateur, on renvoie une exception
            String message = String.format(USER_NOT_FOUND_MESSAGE, username);
            logger.error(message);
            throw new UsernameNotFoundException(message);
        } else {
            // utilisateur retrouvé, on instancie une liste d' "authorities" qui
            // correspondent à des roles
            logger.debug(USER_FOUND_MESSAGE, username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });

            /**
             * ATTENTION -> instanciation d'un objet de la classe "User" provenant du
             * package "org.springframework.security.core.userdetails"
             * Cette classe hérite de "UserDetails" et est propre au framework de sécurité
             * de Spring.
             */
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    authorities);
        }
    }

    public UserDto createUser(UserDto users) {
        User existingUser = repository.findByUsername(users.getUsername());
        if (existingUser != null && existingUser.getUsername().equals(users.getUsername())) {
            throw new RuntimeException("L'adresse ou le nom d'utilisateur e-mail est déjà utilisée.");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordEncode = bCryptPasswordEncoder.encode(users.getPassword());
        users.setPassword(passwordEncode);
        Role userRole = roleRepositories.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepositories.save(userRole);
        }
        users.getRoles().add(userRole);
        return saveOrUpdate(users);
    }



}
