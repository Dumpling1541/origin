package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class TestController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 쿠키..?
		// 브라우저에서 관리되는 것으로 데이터를 담긴 일종의 파일이라고 보면 된다.
		// 쿠키에는 도메인값이 있는데 (쿠키 발행처)
		// 브라우저는 웹 요청을 보낼때 발행처가 같다면 쿠키를 같이 모함켜 전송을 하게 되어있다.

		// 백엔드쪽에서는 클라이언트가 보낸 쿠키들을 확인하는게 가능하다.
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				System.out.println(c.toString());
			}
		}else {
			System.out.println("non found cookie");
		}
		req.getRequestDispatcher("/WEB-INF/views/test.jsp").forward(req, resp);
	}

}
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String msg = "오늘 진짜 집에 가고싶다.";
//		
////		System.out.println(BCrypt.gensalt());
//		String salt ="$2a$10$yyanTSAY1faVKds1VCmyge";
//		
//		for(int cnt =1; cnt<=5; cnt++) {
//			String hashed = BCrypt.hashpw(msg, BCrypt.gensalt());
//			System.out.println(hashed);
//			boolean f = msg.equals(hashed);
//			System.out.println("string equals ="+f);
//			
//			boolean ff = BCrypt.checkpw(msg, hashed);
//			System.out.println("Bcrypt.checkpw = "+ ff);
//		}
//		String hashed = BCrypt.hashpw(msg, salt);
//		boolean f = BCrypt.checkpw(msg, hashed);
//		System.out.println("string equals ="+f);

//		String hashed = BCrypt.hashpw(msg, "asdf32");
//		System.out.println(hashed);
//		PrintWriter out = resp.getWriter();
//		out.println(hashed);
