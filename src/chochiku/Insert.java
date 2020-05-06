package chochiku;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Page;

@WebServlet(urlPatterns={"/chochiku/insert"})
public class Insert extends HttpServlet {

	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("UTF-8");

		try {

		String user=request.getParameter("user");
		int age=Integer.parseInt(request.getParameter("age"));
		int charge=Integer.parseInt(request.getParameter("charge"));
		int gcharge=Integer.parseInt(request.getParameter("gcharge"));
		int upper=60;

		Page.header(out);

		out.println(user + "様の将来の貯蓄額");

		out.println("<p>現在の月額の貯蓄額で</p>");
		out.println("<p>"+(age+10)+"歳で"+(charge*120)+"円</p>");
		out.println("<p>"+(age+20)+"歳で"+(charge*240)+"円</p>");
		out.println("<p>貯めることが出来ます。</p>");
		out.println("<br></br>");

//		out.println("");
		out.println("<p>例えば、60歳までに" + gcharge + "円貯めるには</p>");
		out.println("<p>"+(upper-age)+"年後まで毎年"+(gcharge/(upper-age))+"円必要です。</p>");

		int a = age;
		double g = gcharge;


// 目標金額を達成している必要があるため、11カ月で割っています。
		for (a = age; a < 60; a++) {
				out.println(a + "歳になるまで毎月" + (g/(upper-age)/11) + "円必要です。<br></br>");
		}


		Page.footer(out);

		} catch (NumberFormatException e) {
			out.println("数字を入力してください。");
		}
	}
}
