package com.ezen.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.dao.MemberDao;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/withdraw.do")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 삭제
		HttpSession session = request.getSession();
		session.invalidate();
		
		//전달된 아이디로 회원 레코드 삭제
		MemberDao mdao = MemberDao.getInstance();
		int result = mdao.deleteMember(request.getParameter("userid"));
		if(result==1) {
			request.setAttribute("message", "탈퇴 성공.");
		} else {
			request.setAttribute("message", "탈퇴 오류. 관리자에게 문의하세요.");
		}
		
		//로그인 페이지로 이동
		RequestDispatcher dp = request.getRequestDispatcher("member/loginForm.jsp");
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
