package backend.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import backend.dto.Deal;
import backend.dto.User;
import backend.model.service.DealServiceImpl;
import backend.model.service.UserService;
import backend.model.service.UserServiceImpl;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = UserServiceImpl.getUserService();

	String root;

	// method = "post"
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	// method = "get"
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		root = request.getContextPath();
		String act = request.getParameter("act");
		try {
			if (act == null) {
				response.sendRedirect(root + "/index.jsp");
			} else if (act.equals("login")) { // 로그인
				login(request, response);
			} else if (act.equals("logout")) { // 로그아웃
				logout(request, response);
			} else if (act.equals("gotologin")) { // 회원가입
				response.sendRedirect(root + "/login.jsp");
			} else if (act.equals("gotojoin")) { // 회원가입
				response.sendRedirect(root + "/join.jsp");
			} else if (act.equals("join")) { // 회원가입
				join(request, response);
			} else if (act.equals("userinfo")) { // 회원정보
				userinfo(request, response);
			} else if (act.equals("update")) { // 회원정보
				updateinfo(request, response);
			} else if (act.equals("delete")) { // 회원정보
				deleteinfo(request, response);
			} else if (act.equals("idcheck")) {
				int cnt = idcheck(request, response);
				response.getWriter().append(cnt + "");
			} else if (act.equals("searchCategory")) { // 동별, 아파트별 검색
				searchCategory(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 동별, 아파트별 검색
	protected void searchCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String search = request.getParameter("search");
		String searchfor = request.getParameter("searchfor");
		List<Deal> list;
		if("apt".equals(search)) {
			list = DealServiceImpl.getDealServiceImpl().getSearch("apt", searchfor);
		}else {
			list = DealServiceImpl.getDealServiceImpl().getSearch("dong", searchfor);			
		}
		
//		String dong = request.getParameter("dong");
//		String apt = request.getParameter("apt");
//		List<Deal> list = DealServiceImpl.getDealServiceImpl().getSearch(dong, apt);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().append(json);
		
		request.setAttribute("searchfor", searchfor);
		request.setAttribute("search", search);
		request.setAttribute("deals", list);
		RequestDispatcher disp = request.getRequestDispatcher("/search.jsp");
		disp.forward(request, response);
	}

	// 회원 정보 삭제
	private void deleteinfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		System.out.println("deleteinfo start");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		userService.delete(user.getId());

		logout(request, response);
	}

	// 회원 정보 수정
	private void updateinfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		System.out.println("updateinfo start");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");

		User user = new User(id, password, name, email, tel);
		userService.update(user);

		HttpSession session = request.getSession();
		session.setAttribute("userInfo", user);

		response.sendRedirect(root + "/index.jsp");
	}

	// 회원 정보
	private void userinfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("userinfo start");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		if(user!=null) {
			request.setAttribute("id", user.getId());
			request.setAttribute("password", user.getPassword());
			request.setAttribute("name", user.getName());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("tel", user.getTel());
		}
		RequestDispatcher disp = request.getRequestDispatcher("/userInfo.jsp");
		disp.forward(request, response);
	}

	// 회원 가입
	private void join(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String id = request.getParameter("userid");
		String password = request.getParameter("userpwd");
		String name = request.getParameter("username");
		String email = request.getParameter("emailid") + "@" + request.getParameter("emaildomain");
		String tel = request.getParameter("tel1") + request.getParameter("tel2") + request.getParameter("tel3");

		User user = new User(id, password, name, email, tel);
		userService.join(user);

		String path = "/index.jsp";
		response.sendRedirect(root + path);
	}

	// 로그인
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String id = request.getParameter("userid");
		String password = request.getParameter("userpwd");

		User user = userService.login(id, password);

		String path = "/index.jsp";
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", user);
			response.sendRedirect(root + path);
		} else {
			request.setAttribute("msg", "아이디 또는 비밀번호를 확인하세요.");
			RequestDispatcher disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
		} 
	}

	// 로그아웃
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		session.invalidate();

		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	protected int idcheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int cnt = 1;
		String id = request.getParameter("ckid");
		cnt = userService.checkId(id);
		return cnt;
	}

}
