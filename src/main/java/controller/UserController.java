package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mindrot.jbcrypt.BCrypt;

import data.Avatar;
import data.User;
import repository.Avatars;
import repository.Users;
import service.CookieService;
import service.UserService;

/*
 * 사용자의 가입이나 로그인, 정보 변경등을 처리하는 컨트롤러
 * 
 */

@WebServlet("/user/*")
public class UserController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String ctxPath = req.getContextPath();
		String uri = req.getRequestURI().substring(ctxPath.length());
		switch (uri) {
		case "/user/join" -> {
			List<Avatar> li = Avatars.findAll();
			req.setAttribute("avatars", li);

			req.getRequestDispatcher("/WEB-INF/views/user/join.jsp").forward(req, resp);
		}
		case "/user/join-task" -> {
			req.setCharacterEncoding("utf-8");

			String id = req.getParameter("id").toLowerCase();
			String pass = req.getParameter("pass");
			String name = req.getParameter("name");
			String avatar = req.getParameter("avatar");

			if (!UserService.validate(id, pass, name)) {
				resp.sendRedirect("/user/join?cause=valid");
				return;
			}
			String hashedPw = BCrypt.hashpw(pass, BCrypt.gensalt());

			if (Users.create(id, hashedPw, name, avatar) != 1) {
				resp.sendRedirect("/user/join?cause=access");
				return;
			}

			resp.sendRedirect("/user/login?userId=" + id);
		}
		case "/user/login" -> {
			Cookie[] cookies = req.getCookies();
			Cookie found = CookieService.findByName(cookies, "ID_SAVE");
			if(found !=null) {
				req.setAttribute("idSave", found.getValue());
			}
			req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
		}
		
		
		case "/user/login-task" -> {
			String id = req.getParameter("id");
			String pass = req.getParameter("pass");
			//User found = Users.findById(id);
			SqlSessionFactory factory =(SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
			
			SqlSession sqlSession = factory.openSession();
			User found = sqlSession.selectOne("users.findById", id);
			sqlSession.close();
			// System.out.println(found == null);
			// System.out.println(BCrypt.checkpw(pass, found.getPass()));
			// =============================================
			if (found != null && BCrypt.checkpw(pass, found.getPass())) {
				HttpSession session = req.getSession();
				
				session.setAttribute("logon", true);
				session.setAttribute("logonUser", found);
				//================================================
				Cookie c = new Cookie("ID_SAVE", id);
				c.setMaxAge(60*60*24*7);
				c.setPath("/");
				resp.addCookie(c);
				//================================================
				resp.sendRedirect("/");
				return;
			} else {
				resp.sendRedirect("/user/login?cause=error");
				return;
			}
		}

		default -> {

		}
		}
	}
}
