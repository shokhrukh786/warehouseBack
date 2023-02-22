package uz.shohruh.omborxonabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.LoginDto;
import uz.shohruh.omborxonabackend.service.AuthService;


import javax.validation.Valid;

/**
 * AuthController da hamma yullar(malumotlar) ochiq bo'ladi.
 * Bu controllerdan registeratsidan o'tganlar odiy userlar bo'ladi,
 * ularda ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT kabi permissionlari
 * avtomatik tizim beradi.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

//    @PostMapping("/register")  //success larga 200 qaytariladi umumiy holda
//    public HttpEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto){
//        ApiResponse apiResponse = authService.registerUser(registerDto);
//        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
//    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDto loginDto){
        ApiResponse apiResponse = authService.login(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:401).body(apiResponse);
    }

}
