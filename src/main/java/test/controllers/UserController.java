package test.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import test.beans.Users;
import test.dao.UsersDao;



@Controller
public class UserController {
	
	
	@Autowired
	UsersDao ud;
	
	
	@RequestMapping(value="/savedata",method=RequestMethod.POST)
	public String one(@ModelAttribute("u1") Users u1)
	{
	  
		if(u1.getUcpass().equals(u1.getUpass()))
		{
		ud.register(u1);
		
		return "redirect:/login";
		}
		return null;
	}
	
	@RequestMapping("/login")
	public String two()
	{
		return "login";
	}
	
	@RequestMapping(value="/checkcred",method=RequestMethod.POST)
	public String three(@RequestParam("uname") String uname,@RequestParam("upass") String upass ,HttpSession s1)
	{
		List <Users> up=ud.checkdata(uname,upass);
		
		if(up.isEmpty())
		{
			return "redirect:/login";
		}
		
		s1.setAttribute("uname",uname );
		return "redirect:/dash";
	}
	
	
	@RequestMapping("/dash")
	public String four(HttpServletRequest request)
	{
		HttpSession s1 = request.getSession(false);
		String r = (String) s1.getAttribute("uname");
		if(r!=null) {
			
			return "dash";
		}
		return "redirect:/login";
	}
	
	@RequestMapping("/logout")
	public String five(HttpServletRequest request)
	{
		
		HttpSession s1 = request.getSession();
		s1.invalidate();
		return "redirect:/login";
	}

}
