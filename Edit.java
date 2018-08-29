package fjs.cs.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fjs.cs.common.Constants;
import fjs.cs.dao.MSTCUSTOMER;
import fjs.cs.dao.MSTCUSTOMER_DAO;

public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher myRD = null;
		// Hien thi man hinh Edit
		myRD = req.getRequestDispatcher(Constants.T003_EDIT);
		myRD.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String mode = req.getParameter("mode");
		
		if (mode.equals("save")) {
			MSTCUSTOMER mstcustomer = new MSTCUSTOMER() {
			};
			String customerName = req.getParameter("txtCustomer_name");
			String sex = req.getParameter("txtSex");
			String address = req.getParameter("txtAddress");
			String email = req.getParameter("txtEmail");
			String birthday = req.getParameter("txtBirthday");
			mstcustomer.setCustomer_Name(customerName);
			mstcustomer.setSex(sex);
			mstcustomer.setAddress(address); //
			mstcustomer.setBirthday(birthday);
			if (MSTCUSTOMER_DAO.AddNew(mstcustomer)) {
				resp.sendRedirect("Search");
			}
			// doGet(req, resp);
			// req.getRequestDispatcher(Constants.T003_EDIT).include(req, resp);
			req.getRequestDispatcher("/Search").forward(req, resp);

		}
		if(mode.equals("logout")){
			HttpSession session = req.getSession();
			session.invalidate();
//		req.getRequestDispatcher(Constants.T001_LOGIN).include(req, resp);
//			resp.sendRedirect("WebContent/jsp/T001.jsp");
//			resp.sendRedirect(Constants.T001_LOGIN);
			RequestDispatcher myRD = null;
			// Hien thi man hinh Login
			myRD = req.getRequestDispatcher(Constants.T001_LOGIN);
			myRD.forward(req, resp);
		}
	}

}
