package com.example.fwq.selvet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class charge
 */
@WebServlet("/chargeList")
public class chargeList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public chargeList() {
        super();
        // TODO Auto-generated constructor stub
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
        int type = jsonobject.getInt("type");
        String urlString = request.getRequestURL().toString();
        urlString = urlString.substring(0, urlString.lastIndexOf("/"));
        System.out.println(urlString);
        System.out.println(request.getRemoteHost());
        System.err.println(new MyUtil().simpDate("yyyy-MM-dd HH:mm:ss", new java.util.Date()));
        reader.close();
        DB db = new DB();
        JSONObject jsonObject2 = new JSONObject();
        String sql;
        if (type == 1)
            sql = "SELECT * FROM accountinfo where userid='" + userid + "' and type='ˮ��'";
        else
            sql = "SELECT * FROM accountinfo where userid='" + userid + "' and type='���'";
        db.getRs(sql);
        ResultSet set = db.getRs();
        try {
            if (set != null) {
                jsonObject2.put("RESULT", "S");
                List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
                while (set.next()) {
                    JSONObject jsonObject3 = new JSONObject();
                    jsonObject3.put("id", set.getInt(1));
                    jsonObject3.put("userid", set.getString(2));
                    jsonObject3.put("groupId", set.getInt(3));
                    jsonObject3.put("type", set.getString(4));
                    jsonObject3.put("accountId", set.getString(5));
                    jsonObject3.put("accountAddress", set.getString(6));
                    jsonObject3.put("banlance", set.getFloat(7));
                    jsonObject3.put("cost", set.getFloat(8));
                    jsonObject3.put("unit", set.getString(9));
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
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
