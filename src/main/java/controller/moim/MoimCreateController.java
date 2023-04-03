package controller.moim;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/moim/create")
public class MoimCreateController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		req.setAttribute("today",  new Date());
		
		req.getRequestDispatcher("/WEB-INF/views/moim/create.jsp").forward(req, resp);
	}
}
