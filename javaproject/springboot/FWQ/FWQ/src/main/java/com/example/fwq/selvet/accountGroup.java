package com.example.fwq.selvet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class accountGroup
 */
@WebServlet("/accountGroup")
public class accountGroup extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public accountGroup() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
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
        String userid = jsonobject.getString("userid");
        String urlString = request.getRequestURL().toString();
        urlString = urlString.substring(0, urlString.lastIndexOf("/"));
        System.out.println(urlString);
        System.out.println(request.getRemoteHost());
        System.err.println(new MyUtil().simpDate("yyyy-MM-dd HH:mm:ss", new java.util.Date()));
        reader.close();
        DB db = new DB();
        JSONObject jsonObject2 = new JSONObject();
        db.getRs("select * from accountgroup where userid='" + userid + "'");
        ResultSet set = db.getRs();
        try {
            if (set != null) {
                jsonObject2.put("RESULT", "S");
                List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
                while (set.next()) {
                    JSONObject jsonObject3 = new JSONObject();
                    jsonObject3.put("userid", set.getString(2));
                    jsonObject3.put("index", set.getInt(1));
                    jsonObject3.put("groupName", set.getString(3));
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