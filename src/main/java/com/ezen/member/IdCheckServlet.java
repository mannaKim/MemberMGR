package com.ezen.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.MemberDao;

/**
 * Servlet implementation class IdCheckServlet
 */
@WebServlet("/idcheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		MemberDao mdao = MemberDao.getInstance();
		int result = mdao.confirmId(userid); //userid가 사용 가능한지 판단하는 메서드 호출
		//리턴값이 1이면 사용 불가능(다른 사람이 사용중), -1이면 사용 가능으로 판단
		
		//※ LoginServlet에서 사용한 getMember메서드를 사용해서 아래와 같이 표현해도 됨
		//MemberDto mdto = mdto.getMember(userid);
		//if(mdto==null) result=-1;
		//else result=1;
		
		request.setAttribute("result", result);
		request.setAttribute("userid", userid); //중복체크한 아이디와 결과를 모두 리퀘스트에 담습니다.
		
		RequestDispatcher rd = request.getRequestDispatcher("member/idcheck.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
