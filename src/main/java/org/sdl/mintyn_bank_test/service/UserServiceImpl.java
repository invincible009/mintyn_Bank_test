package org.sdl.mintyn_bank_test.service;

import org.sdl.mintyn_bank_test.dto.*;
import org.sdl.mintyn_bank_test.entity.Authority;
import org.sdl.mintyn_bank_test.entity.User;
import org.sdl.mintyn_bank_test.exception.UserExistException;
import org.sdl.mintyn_bank_test.repository.AuthorityRepository;
import org.sdl.mintyn_bank_test.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.encoder = encoder;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        var user = new User();
        user.setEmail(request.email());
        user.setFirstname(request.firstname());
        user.setLastname(request.lastname());
        var encodedPass = encoder.encode(request.password());
        user.setPassword(encodedPass);
        user.setCreatedAt(String.valueOf(new Date(System.currentTimeMillis())));

        List<AuthorityDto> authorityDtoList = request.authorities();
        Set<Authority> authorities = new HashSet<>();

        for (AuthorityDto authorityDto : authorityDtoList) {
            Optional<Authority> existingAuthority = authorityRepository.findAuthorityByName(authorityDto.authorityName());
            authorities.add(existingAuthority.orElseGet(() -> new Authority(authorityDto.authorityName())));
        }
        user.setAuthoritySet(authorities);
        var savedUser = userRepository.saveAndFlush(user);
        return new CreateUserResponse("User registered successfully",savedUser.getId());
    }

    @Override
    public void checkIfUserExistsByEmail(String email) {
        Optional<User> existingUser = userRepository.findUserByEmail(email);
        existingUser.ifPresent(user -> {
            throw new UserExistException(email);
        });
    }
}
