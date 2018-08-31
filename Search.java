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
		/*
		 * get session dieu kien search MSTCUSTOMER dkSearch = (MSTCUSTOMER)
		 * session.getAttribute("DKSearch"); Search voi dieu kien search
		 */

		MSTCUSTOMER_DAO dao = new MSTCUSTOMER_DAO();
		List<MSTCUSTOMER> listCustomer = new ArrayList<MSTCUSTOMER>();
		//hien thi data
		listCustomer = dao.getListCustomer();
		MSTCUSTOMER mst = new MSTCUSTOMER() {
		};
		// set data
		mst.setCustomer_Name(cusname);
		mst.setSex(cussex);
		mst.setBirthday(cusbirthdayFrom);
		mst.setBirthday(cusbirthdayTo);

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
			// tao session
			HttpSession session = req.getSession();
			// tao 1 danh sach cus
			List<MSTCUSTOMER> list = new ArrayList<MSTCUSTOMER>();
			String cusname = req.getParameter("txtsearchName");
			String cussex = req.getParameter("llbSex");
			String cusbirthdayFrom = req.getParameter("txtBirthdayFrom");
			String cusbirthdayTo = req.getParameter("txtBirthdayTo");
			// dieu kien hien thi tat ca khi chua search

			if (cusname.equals("") && cussex.equals("")
					&& cusbirthdayFrom.equals("") && cusbirthdayTo.equals("")) {
				list = dao.getListCustomer();
			}

			if (!"".equals(cusname) || !"".equals(cussex)
					|| !"".equals(cusbirthdayFrom) || !"".equals(cusbirthdayTo)) {
				System.out.println(cussex);
				list = MSTCUSTOMER_DAO.getSearch(cusname, cussex,
						cusbirthdayFrom, cusbirthdayTo);
			}

			// gan danh sach vao sesion
			session.setAttribute("listCustomer", list);
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
			myRD = req.getRequestDispatcher(Constants.T003_EDIT);
			myRD.forward(req, resp);
		}

	}
}
