package uz.shohruh.omborxonabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.shohruh.omborxonabackend.entity.User;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.LoginDto;
import uz.shohruh.omborxonabackend.repository.RoleRepository;
import uz.shohruh.omborxonabackend.repository.UserRepository;
import uz.shohruh.omborxonabackend.security.JwtProvider;


@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository; //user classni database bilan ishlab beruvchi interface
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

//    public ApiResponse registerUser(RegisterDto registerDto){
//        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
//            return new ApiResponse("parollar mos emas", false);
//        if (userRepository.existsByUsername(registerDto.getUsername()))
//            return new ApiResponse("Bunday username avval ro'yxatdan o'tgan", false);
//        User user = new User(
//                registerDto.getFullName(),
//                registerDto.getUsername(),
//                passwordEncoder.encode(registerDto.getPassword()),
//                roleRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("role", "name", AppConstants.USER)),
//                true
//        );
//        userRepository.save(user);
//        return new ApiResponse("Muvaqqiyatli ro'yxatdan o'tdingiz", true);
//    }

    public ApiResponse login(LoginDto loginDto) {
        try{
            /**
             * bu parol va login solishtiradi,loadUserByUsername chaqiradi, user classida 4 ta filtni ham tekshiradi
             * ular accountNonExpired,accountNonLocked,credentialsNonExpired,enabled
             */
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()));
            //token yasash, shu yergacha kelsa demak userni parol va loginlari to'g'ri hisoblanadi
            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generatedToken(loginDto.getUsername(), user.getRole());
            return new ApiResponse("Token", true, token);
        }catch (BadCredentialsException e){
            return new ApiResponse("parol yoki login xato", false);
        }

    }

    @Override //tokenni ichidan username oladigan method
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
