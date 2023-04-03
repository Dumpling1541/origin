package controller.moim;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Moim;
import repository.Moims;


@WebServlet("/moim/search")
public class MoimSearchController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] cates = req.getParameterValues("cate"); //여러개의 데이터 넘길때
//		System.out.println(Arrays.toString(cates));
		Moims.findByCate(cates);
	
		
		
		List<Moim> list =Moims.findByCate(cates);
		req.setAttribute("list", list);
//		System.out.println(list);
		
//		
//		String cate = req.getParameter("cate");
//		
//		if(cate != null) {
//			List<Moim> li = Moims.findCate(cate);
//			req.setAttribute("cate", li);			
//			
//		}else {
//			List<Moim> li = Moims.findAll();
//			req.setAttribute("cate", li);
//		}
//		
		req.getRequestDispatcher("/WEB-INF/views/moim/search.jsp").forward(req, resp);		
	}
}
