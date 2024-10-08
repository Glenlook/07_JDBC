package edu.kh.jdbc.controller;

import java.io.IOException;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;
import edu.kh.jdbc.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/selectUser")
public class SelectUserServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			// 전달 받은 파라미터 얻어오기
			String userNo = req.getParameter("userNo");
			// -> SQL 에서 TO_NUMBER를 이용해 자료형 변경 예정
			
			UserService service = new UserServiceImpl();
			
			User user = service.selectUesr(userNo);
			
			req.setAttribute("resultUser", user);
			
			String path = "/WEB-INF/view/selectUser.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
}
