package pl.cyfronet.indigo.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.cyfronet.indigo.security.PortalUser;

import java.util.ArrayList;

@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView home(PortalUser user) {
        ArrayList<String> roles = new ArrayList<>();

        for(GrantedAuthority authority : user.getAuthorities()) {
            roles.add(authority.getAuthority());
        }

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("user", user);
        mav.addObject("user_roles", roles);
        mav.addObject("admin_sites", roles);
        return mav;
    }
}