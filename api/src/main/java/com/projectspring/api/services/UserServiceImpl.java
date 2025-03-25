package com.projectspring.api.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.projectspring.api.dtos.UserDto;
import com.projectspring.api.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectspring.api.generic.GenericService;
import com.projectspring.api.generic.GenericServiceImpl;
import com.projectspring.api.mappers.UserMapper;
import com.projectspring.api.entities.Place;
import com.projectspring.api.entities.Role;
import com.projectspring.api.repositories.PlaceRepository;
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

    private final PlaceRepository placeRepository;

    private static final String USER_NOT_FOUND_WITH_NAME = "Utilisateur introuvable avec le nom: ";

    public UserServiceImpl(UserRepository repository, UserMapper mapper, UserRepository userRepository,
            RoleRepository roleRepository, PlaceRepository placeRepository) {
        super(repository, mapper);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.placeRepository = placeRepository;
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

            return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                    user.get().getPassword(),
                    authorities);
        }
    }

    public User createUser(UserDto userDto) {
        Optional<User> existingUser = repository.findByUsername(userDto.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("L'adresse ou le nom d'utilisateur est déjà utilisée.");
        }

        User user = toEntity(userDto);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        if (userRole.isEmpty()) {
            Role newRole = new Role();
            newRole.setName("ROLE_USER");
            roleRepository.save(newRole);
            userRole = Optional.of(newRole);
        }

        user.getRoles().add(userRole.get());

        return repository.save(user);
    }

    @Transactional
    public User addPlaceToFavorite(List<Long> placeIds) {
        Long userId = getAuthenticatedUserId();
        User user = repository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND_WITH_NAME + userId));

        Set<Place> places = new HashSet<>(placeRepository.findAllById(placeIds));

        if (places.isEmpty()) {
            throw new IllegalArgumentException("Aucun lieu trouvé avec les IDs fournis");
        }

        user.getRecording().addAll(places);

        return repository.save(user);
    }

    @Transactional
    public User removeFavorite(Long placeId) {
        Long userId = getAuthenticatedUserId();
        User user = repository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec l'ID: " + userId));

        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("Lieu introuvable"));

        user.getRecording().remove(place);

        return repository.save(user);
    }

    @Transactional
    public User addPlaceToLater(List<Long> placeIds) {
        Long userId = getAuthenticatedUserId();
        User user = repository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec l'ID: " + userId));

        Set<Place> places = new HashSet<>(placeRepository.findAllById(placeIds));

        if (places.isEmpty()) {
            throw new IllegalArgumentException("Aucun lieu trouvé avec les IDs fournis");
        }

        user.getToLater().addAll(places);

        return repository.save(user);
    }

    @Transactional
    public User removeForLater(Long placeId) {
        Long userId = getAuthenticatedUserId();
        User user = repository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec l'ID: " + userId));

        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("Lieu introuvable"));

        user.getToLater().remove(place);

        return repository.save(user);
    }

    public Set<Place> getFavoriteByUserId() {
        Long userId = getAuthenticatedUserId();
        Optional<User> userOptional = repository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Utilisateur introuvable avec l'ID: " + userId);
        }
        return userOptional.get().getRecording();
    }

    public Set<Place> getToLaterByUserId() {
        Long userId = getAuthenticatedUserId();
        Optional<User> userOptional = repository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Utilisateur introuvable avec l'ID: " + userId);
        }
        return userOptional.get().getToLater();
    }

    private Long getAuthenticatedUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = (principal instanceof UserDetails userDetails) ? userDetails.getUsername()
                : principal.toString();

        User user = repository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec le nom: " + username));

        return user.getId();
    }
}
