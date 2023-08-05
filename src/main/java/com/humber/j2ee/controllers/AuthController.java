package com.humber.j2ee.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

	@GetMapping("/login")
	public String getLogin(@RequestParam(required = false) String logout, Model model) {
		model.addAttribute("logout", logout);
		return "auth/login";
	}
	
	@GetMapping("/logout")
	public String getLogout(HttpServletRequest request, HttpServletResponse response) {
		//get authentication info from the security context
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
        	// auth context exists, logout process
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        //redirect back to login
		return "redirect:/login?logout=true";
	}

}
