package ua.nure.dehtiarov;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc")
public class Calculator extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Pattern pattern = Pattern.compile("(\\d+?\\.?)?\\d+");
		Matcher matcher = pattern.matcher(req.getParameter("first"));
		HttpSession httpSession = req.getSession();
		boolean isOk = false;
		StringBuilder errors = new StringBuilder();
		if (matcher.matches()) {
			matcher = pattern.matcher(req.getParameter("second"));
			if (matcher.matches()) {
				isOk = true;
			} else {
				errors.append("Second operand is illegal");
			}

		} else {
			System.out.println("err1");
			errors.append("\nFirst operand is illegal");
		}

		if (isOk) {
			double firstOperand = Double.valueOf(req.getParameter("first"));
			double secondOperand = Double.valueOf(req.getParameter("second"));
			String operation = req.getParameter("operation");
			boolean divideByZero = false;
			double result = 0;
			switch (operation) {
			case "+":
				result = firstOperand + secondOperand;
				break;
			case "-":
				result = firstOperand - secondOperand;
				break;
			case "*":
				result = firstOperand * secondOperand;
				break;
			case "/":
				if (secondOperand == 0) {
					divideByZero = true;
					httpSession.setAttribute("result", "IllegalArgumentException");
				} else {
					result = firstOperand / secondOperand;
				}
				break;
			}
			if (!divideByZero) {
				httpSession.setAttribute("result", result);
			}
		} else {
			httpSession.setAttribute("result", errors.toString());
		}

		resp.sendRedirect("index.jsp");
	}
}
