package com.oguztasgin.SpringMono.controller;

import com.oguztasgin.SpringMono.dto.request.LoginRequestDto;
import com.oguztasgin.SpringMono.dto.request.RegisterRequestDto;
import com.oguztasgin.SpringMono.dto.response.UserControllerFindAllResponseDto;
import com.oguztasgin.SpringMono.exception.ErrorType;
import com.oguztasgin.SpringMono.exception.SpringMonoException;
import com.oguztasgin.SpringMono.repository.entity.User;
import com.oguztasgin.SpringMono.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.oguztasgin.SpringMono.constants.EndPoints.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * http://localhost:8080/v1/dev/user/register?username=muhammet&password=123&email=m@m.com
     * @param dto
     * @return
     */
    @GetMapping(REGISTER)
    public ResponseEntity<Boolean> register(RegisterRequestDto dto){
        return ResponseEntity.ok(userService.registerMapper(dto));
    }

    public ResponseEntity<User> doLogin(LoginRequestDto dto){
        return ResponseEntity.ok(null);
    }

    /**
     * http://localhost:8080/v1/dev/user/findall
     * @return
     */
    @GetMapping(FINDALL)
    public ResponseEntity<List<UserControllerFindAllResponseDto>> findAll(){
        return ResponseEntity.ok(userService.findAllResponseDtos());
    }

    @GetMapping("/findbyid")
    public ResponseEntity<User> findById(Long id){
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) throw new SpringMonoException(ErrorType.KULLANICI_BULUNAMADI);
        return ResponseEntity.ok(user.get());
    }

}