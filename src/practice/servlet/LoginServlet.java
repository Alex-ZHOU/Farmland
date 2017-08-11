package practice.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//�û���½servlet
@WebServlet("/practice/servlet/LoginServlet")
public class LoginServlet extends HttpServlet {

	 public LoginServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<User> list = DB.getAll();
		for (User user : list) {
			// ����û���¼�ɹ�
			if (user.getUsername().equals(username)
					&& user.getPassword().equals(password)) {
				HttpSession session = request.getSession();
				// �ֶ�����session����Ч��Ϊ30����
				String sessionId = session.getId();
				Cookie cookie = new Cookie("JSESSIONID", sessionId);
				cookie.setMaxAge(2);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
				// ��¼�ɹ���Ҫ�����û��ĵ�¼״̬��key���û������String��ʽvalue�����û�����(model)�������ҳ��Ӧ�����õ�
				session.setAttribute("user", user);
				// �ض�����ҳ��URL��д��ʽ
				String url = response.encodeRedirectURL(request
						.getContextPath() + "/practice/index.jsp");
				response.sendRedirect(url);
				return;
			}
		}

		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("�û������������");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

// ģ��洢�û������ݿ�
class DB {
	private static List<User> list = new ArrayList<User>();
	static {
		list.add(new User("aaa", "123"));
		list.add(new User("bbb", "123"));
		list.add(new User("ccc", "123"));
	}

	public static List<User> getAll() {
		return list;
	}
}


