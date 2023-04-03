package filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.User;

// @WebFilter({"/home/index.jsp", "/home/following.jsp"})
@WebFilter({"/moim/create", "/moim/join-task"})
public class AuthFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, 
							FilterChain chain)
			throws IOException, ServletException {
		String uri = request.getRequestURI();
		
		boolean logon = (boolean)request.getSession().getAttribute("logon");
		User logonUser = (User)request.getSession().getAttribute("logonUser");
		
		if(!logon || logonUser == null) {
			response.sendRedirect("/user/login?url="+uri);
		}else {
			chain.doFilter(request, response);
		}
		
	}
}


