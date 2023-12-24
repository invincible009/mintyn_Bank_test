package org.sdl.mintyn_bank_test.config;



public class MintynBnkAuthenticationProvider {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public MintynBnkAuthenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        var username = authentication.getName();
//        var pwd = authentication.getCredentials().toString();
//        Optional<User> optionalUser = userRepository.findUserByEmail(username);
//        if(optionalUser.get() != null) {
//            if (passwordEncoder.matches(pwd, optionalUser.get().getPassword())) {
//                return new UsernamePasswordAuthenticationToken(username, pwd, grantedAuthorities(optionalUser.get().getAuthoritySet()));
//            } else {
//                throw new BadCredentialsException("Invalid Username or password");
//            }
//        }else {
//            throw new BadCredentialsException("No User registered with this details");
//        }
//    }
//
//    private List<GrantedAuthority> grantedAuthorities(Set<Authority> authoritySet){
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for(Authority authority: authoritySet){
//            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
//        }
//        return grantedAuthorities;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//    }
}
