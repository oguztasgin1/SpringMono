package com.oguztasgin.SpringMono.mvccontroller;

import com.oguztasgin.SpringMono.dto.request.LoginRequestDto;
import com.oguztasgin.SpringMono.dto.request.RegisterRequestDto;
import com.oguztasgin.SpringMono.repository.entity.User;
import com.oguztasgin.SpringMono.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static com.oguztasgin.SpringMono.constants.EndPoints.*;

@Controller
@RequestMapping(MVCUSER)
@RequiredArgsConstructor
public class UserMvcController {

    private final UserService userService;

    /**
     * http://localhost:8080/mvc/user/login
     * MVC sayfalarına gelen isteklerde kullanıcıya bir HTML sayfası dönmeniz gerekmektedir.
     * bu nedenle MVC yapısında tema ile birleştirilen model kullanıcıya ModelAndView üzerinden
     * dönülür.
     * ModelAndView -> bir html sayfası ve model talep eder(model zorunlu değildir.)
     * bu bilgiler ile bir html sayfası oluştutarak kullanıcıya dönüş yapar.
     */
    @GetMapping(LOGIN)
    public ModelAndView login(){
        ModelAndView model = new ModelAndView();
        /**
         * templates içinde bulunan html sayfasının adıdır.
         */
        model.setViewName("login");
        model.addObject("title","Giriş Sayfası");
        model.addObject("error","");
        return model;
    }

    @PostMapping(LOGIN)
    public ModelAndView login(LoginRequestDto dto){
        ModelAndView model = new ModelAndView();
        Optional<User> user = userService.findOptionalByUsernameAndPassword(dto);
        /**
         * Kullanıcıadı yada şifre hatalı ise, yok ise
         */
        if(user.isEmpty()){
            model.setViewName("login");
            model.addObject("error","Kullanıcı adı ya da şifre hatalıdır.");
            return model;
        }else{
            return new ModelAndView("redirect:/v1/dev/urun/index");
        }
    }

    @GetMapping(REGISTER)
    public ModelAndView register(){
        ModelAndView model = new ModelAndView();
        model.setViewName("register");
        model.addObject("error","");
        return model;
    }

    @PostMapping(REGISTER)
    public ModelAndView register(RegisterRequestDto dto){
        ModelAndView model = new ModelAndView();
        /**
         * Eğer kullanıcı mevcut ise uyarı bildirimi yap.
         */
        if(userService.existsUserByUsername(dto.getUsername())){
            model.setViewName("register");
            model.addObject("error",
                    dto.getUsername()+" kullanıcı adı data önce başkası tarafından alınmıştır.");
        }else{
            userService.register(dto);
            return new ModelAndView("redirect:login");
            //model.setViewName("redirect:login");
        }
        return model;
    }

    @GetMapping(INDEX)
    public ModelAndView index(){
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }


}