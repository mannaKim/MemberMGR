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
import com.ezen.dto.MemberDto;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do") //별칭
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get으로 호출됐을때 실행
		String url = "member/loginForm.jsp";
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser")!=null) {
			//만약 현재 누군가 로그인해 있다면
			url = "main.do" ;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		//서블릿을 거쳐서 포워딩 된 페이지는 외부로 노출되지 않습니다.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post로 호출됐을때 실행
		request.setCharacterEncoding("UTF-8");
		
		//전달된 아이디, 비밀번호를 변수에 저장
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		
		//전달된 userid로 member테이블에서 회원을 검색하고, 검색 결과에 따라 pwd와 비교해서
		//정상 로그인 여부를 결정합니다.
		
		//로그인이 실패했을 때 포워딩 할 경로
		String url = "member/loginForm.jsp";
		
		//Dao의 회원 검색 메서드를 호출하기 위해 객체를 생성
		MemberDao mdao = MemberDao.getInstance();
		
		//userid를 전달인수로 하는 getMember()메서드를 호출해서 해당 회원의 정보를
		//dto에 담아서 리턴받습니다.
		MemberDto mdto = mdao.getMember(userid);
		if (mdto == null) { //아이디가 없을 때
			request.setAttribute("message", "아이디가 없습니다.");
		} else if(mdto.getPwd() == null) { //저장된 패스워드가 null
			request.setAttribute("message", "시스템 오류(1). 관리자에게 문의하세요.");
		} else if(!mdto.getPwd().equals(pwd)) { //비밀번호 틀림
			request.setAttribute("message", "패스워드가 틀립니다.");
			
		} else if(mdto.getPwd().equals(pwd)) { //정상 로그인

			url = "main.do"; //로그인 성공시 이동할 url 변경
			//로그인 한 사람의 정보(mdto)를 session에 저장합니다.
			//session은 각 페이지에 있는 request 객체에서 얻어 쓸 수 있는데,
			//jsp페이지에서는 그 페이지가 갖고 있는 request안의 session을 사용해도 되지만,
			//서블릿에서는 request를 전달인수로 받아서 매개변에 저장된 형태로 쓰기 때문에
			//별도로 추출하는 동작이 필요합니다.
			HttpSession session = request.getSession();
			//request와 response는 jsp 페이지에 있는 객체
			session.setAttribute("loginUser", mdto);
			
		} else { //위 if 조건들에 해당하지 않는 조건으로 로그인 실패
			request.setAttribute("message", "시스템 오류(2). 관리자에게 문의하세요.");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
