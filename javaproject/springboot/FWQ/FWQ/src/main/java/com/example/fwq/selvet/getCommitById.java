package com.example.fwq.selvet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.db.DB;
import com.example.db.MyUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class getCommitById
 */
@WebServlet("/getCommitById")
public class getCommitById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getCommitById() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedReader reader = request.getReader();
		String json = reader.readLine();
		JSONObject jsonobject = JSONObject.fromObject(json);
		String id = jsonobject.getString("id");
		String urlString = request.getRequestURL().toString();
		urlString = urlString.substring(0, urlString.lastIndexOf("/"));
		System.out.println(urlString);
		System.out.println(request.getRemoteHost());
		System.err.println(new MyUtil().simpDate("yyyy-MM-dd HH:mm:ss", new java.util.Date()));
		reader.close();
		DB db = new DB();
		JSONObject jsonObject2 = new JSONObject();
		db.getRs("select * from commit where id ='" + id + "'");
		ResultSet set = db.getRs();
		try {
			if (set != null) {
				jsonObject2.put("RESULT", "S");
				List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
				while (set.next()) {
					JSONObject jsonObject3 = new JSONObject();
					jsonObject3.put("num", set.getInt(2));
					jsonObject3.put("commit", set.getString(3));
					jsonObject3.put("commitTime", set.getString(4).replace(".0", ""));
					jsonObject3.put("reviewer", set.getString(5));
					jsonObjects.add(jsonObject3);
				}
				jsonObject2.put("ROWS_DETAIL", jsonObjects.toString());
			} else {
				jsonObject2.put("RESULT", "F");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			jsonObject2.clear();
			jsonObject2.put("RESULT", "F");
			e.printStackTrace();
		} finally {
			db.closed();
			PrintWriter owtPrintWriter = response.getWriter();
			owtPrintWriter.write(jsonObject2.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
