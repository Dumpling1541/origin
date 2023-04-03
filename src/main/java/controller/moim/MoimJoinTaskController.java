package controller.moim;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.Moim;
import data.User;

@WebServlet("/moim/join-task")
public class MoimJoinTaskController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSessionFactory factory= 
				(SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession();
				
		String target = req.getParameter("target"); // 모임ID
		// 모임 ID에 해당하는 정보를 읽어온 후 (mybatis 방식으로 처리)
		Moim found = sqlSession.selectOne("moims.findById", target);
		// max , current 값을 비교해서 참가할 데이터를 넣어도 되는지 아닌지 판단
		if(found.getCurrentPerson() >= found.getMaxPerson()) {
			resp.sendRedirect("/moims/detail?id="+target);
			return;
		}
		
		User logonUser = (User)req.getSession().getAttribute("logonUser");
		// 참석정보를 등록시켜도 된다면...
		Map<String, Object> params = new HashMap<>();
		params.put("moimId", target);
		params.put("userId", logonUser.getId());
		params.put("status", found.getType().equals("public") ? 2 : 1);
		
		int r = sqlSession.insert("attendances.create", params);
		// 이 모임 정보의 타입을 확인해서 private이면 status를 1로 등록 / 아니면 2로 등록
		// myBatis로 처리
		
		// public 인 경우에는 이 해당모임의 current 값을 증가 // myBatis 로 처리
		if(found.getType().equals("public")) {
			sqlSession.update("moims.updateCurrentPerson", target);
		}
		// 처리 결과에 따라서 적절한 응답.
	
		sqlSession.close();
		
		resp.sendRedirect("/moim/detail?id="+target);
		return;

	}

}
