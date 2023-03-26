package com.example.fwq.selvet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class busListById
 */
@WebServlet("/busListById")
public class busListById extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public busListById() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        JSONObject jsonobject = JSONObject.fromObject(json);
        String busid = jsonobject.getString("busid");
        String urlString = request.getRequestURL().toString();
        urlString = urlString.substring(0, urlString.lastIndexOf("/"));
        System.out.println(urlString);
        System.out.println(request.getRemoteHost());
        System.err.println(new MyUtil().simpDate("yyyy-MM-dd HH:mm:ss", new java.util.Date()));
        reader.close();
        DB db = new DB();
        JSONObject jsonObject2 = new JSONObject();
        db.getRs("select * from bus where busid=" + busid);
        ResultSet set = db.getRs();
        try {
            if (set != null) {
                jsonObject2.put("RESULT", "S");
                List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
                while (set.next()) {
                    JSONObject jsonObject3 = new JSONObject();
                    jsonObject3.put("busid", set.getInt(1));
                    jsonObject3.put("pathName", set.getString(2));
                    jsonObject3.put("startSite", set.getString(3));
                    jsonObject3.put("endSite", set.getString(4));
                    jsonObject3.put("runTime1", set.getString(5));
                    jsonObject3.put("runTime2", set.getString(6));
                    jsonObject3.put("price", set.getFloat(7));
                    jsonObject3.put("mileage", set.getFloat(8));
                    jsonObjects.add(jsonObject3);
                }
                jsonObject2.put("ROWS_DETAIL", jsonObjects.toString());
            } else {
                jsonObject2.put("RESULT", "F");
            }
        } catch (SQLException e) {
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
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
