package controller.moim;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.User;
import repository.Moims;

@WebServlet("/moim/create-task")
public class MoimCreateTaskController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("utf-8");

		String event = req.getParameter("event");
		String type = req.getParameter("type");
		String cate = req.getParameter("cate");
		String description = req.getParameter("description");
		int maxPerson = Integer.parseInt(req.getParameter("maxPerson"));
		String date = req.getParameter("date");
		String begin = req.getParameter("begin");
		String end = req.getParameter("end");
		String beginDate = date + " " + begin;

		String endDate;
		if (end.equals("")) {
			endDate = null;
		} else {
			endDate = date + " " + end;
		}

		System.out.println(beginDate + "/" + endDate);

		String id = UUID.randomUUID().toString().split("-")[0];
		User logonUser = (User) session.getAttribute("logonUser");
		String managerId = logonUser.getId();
		// ========================================================================

		// ======================================================================
		int r = Moims.create(id, managerId, event, type, cate, description, maxPerson, beginDate, endDate);
		if (r == 1) {
			resp.sendRedirect("/moim/detail?id=" + id);
//			req.getRequestDispatcher("/WEB-INF/views/moim/create-succes.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("/moim/create?cause=error");
		}
	}

}
