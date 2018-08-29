package fjs.cs.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fjs.cs.common.Constants;
import fjs.cs.common.Utils;
import fjs.cs.dao.MSTCUSTOMER;
import fjs.cs.dao.MSTCUSTOMER_DAO;
import fjs.cs.dao.MSTUSER_DAO;

public class Search extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher myRD = null;
		// tao session
		HttpSession session = req.getSession();
		String cusname = req.getParameter("txtsearchName");
		String cussex = req.getParameter("llbSex");
		String cusbirthdayFrom = req.getParameter("txtBirthdayFrom");
		String cusbirthdayTo = req.getParameter("txtBirthdayTo");
		// chuyen tu null -< ""
		cusname = Utils.nullToBlank(cusname);
		cussex = Utils.nullToBlank(cussex);
		cusbirthdayFrom = Utils.nullToBlank(cusbirthdayFrom);
		cusbirthdayTo = Utils.nullToBlank(cusbirthdayTo);
		// Luu dieu kien search vao session

		session.setAttribute("CUSNAME", cusname);
		session.setAttribute("CUSSEX", cussex);
		session.setAttribute("CUSBIRTHDAY", cusbirthdayFrom);
		session.setAttribute("CUSBIRTHDAY", cusbirthdayTo);

		// get session dieu kien search
		// MSTCUSTOMER dkSearch = (MSTCUSTOMER)
		// session.getAttribute("DKSearch");

		// Search voi dieu kien search

		MSTCUSTOMER_DAO dao = new MSTCUSTOMER_DAO();
		List<MSTCUSTOMER> listCustomer = new ArrayList<MSTCUSTOMER>();
		 listCustomer = dao.getListCustomer();
		MSTCUSTOMER mst = new MSTCUSTOMER() {
		};
		mst.setCustomer_Name(cusname);
		mst.setCustomer_Name(cussex);
		mst.setCustomer_Name(cusbirthdayFrom);
		mst.setCustomer_Name(cusbirthdayTo);

		session.setAttribute("listCustomer", listCustomer);
		// Hien thi man hinh Search
		myRD = req.getRequestDispatcher(Constants.T002_SEARCH);
		myRD.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher myRD = null;
		MSTCUSTOMER_DAO dao = new MSTCUSTOMER_DAO();
		String mode = req.getParameter("mode");
		
		if (mode.equals("searchname")) {
			HttpSession session = req.getSession();

			List<MSTCUSTOMER> list = new ArrayList<MSTCUSTOMER>();
			String cusname = req.getParameter("txtsearchName");
			String cussex = req.getParameter("llbSex");
			String cusbirthdayFrom = req.getParameter("txtBirthdayFrom");
			String cusbirthdayTo = req.getParameter("txtBirthdayTo");
			
			list = MSTCUSTOMER_DAO.getSearch(cusname, cussex, cusbirthdayFrom,cusbirthdayTo);

			session.setAttribute("listCustomer", list);
			// System.out.println(session.toString());
			// resp.sendRedirect("Search");
			myRD = req.getRequestDispatcher(Constants.T002_SEARCH);
			myRD.forward(req, resp);
			}
		
		if (mode.equals("delete")) {
			// // tra ve id da chon o checkbox 22022,22023
			String strList = req.getParameter("listDelete");
			// tra ve mang id
			String[] listId = strList.split(","); // [22022,22023]
			for (String id : listId) {
				MSTCUSTOMER_DAO.DeleteByID(id);
			}
			resp.sendRedirect("Search");
		}
		if (mode.equals("addnew")) {
			MSTCUSTOMER mstcustomer = new MSTCUSTOMER() {
			};
			String cusname = req.getParameter("txtCustomer_name");
			String cussex = req.getParameter("txtSex");
			String cusbirth = req.getParameter("txtBirthday");
			String cusemail = req.getParameter("txtEmail");
			String cusaddress = req.getParameter("txtAddress");

			System.out.println(mstcustomer.toString());
			if (MSTCUSTOMER_DAO.AddNew(mstcustomer)) {
				System.out.println("OKKK");
			}
			// resp.sendRedirect("Edit");
			myRD = req.getRequestDispatcher(Constants.T003_EDIT);
			myRD.forward(req, resp);
		}

	}
}
