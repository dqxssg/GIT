package com.example.fwq.selvet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class charge
 */
@WebServlet("/charge")
public class charge extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public charge() {
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
        String accountId = jsonobject.getString("accountId");
        int type = jsonobject.getInt("type");
        String urlString = request.getRequestURL().toString();
        urlString = urlString.substring(0, urlString.lastIndexOf("/"));
        System.out.println(urlString);
        System.out.println(request.getRemoteHost());
        System.err.println(new MyUtil().simpDate("yyyy-MM-dd HH:mm:ss", new java.util.Date()));
        reader.close();
        DB db = new DB();
        JSONObject jsonObject2 = new JSONObject();
        try {
            String sql = "update accountinfo set balance=balance-cost,cost=0 where ";
            if (type == 1)
                sql += "type='ˮ��'";
            else if (type == 2)
                sql += "type='���'";
            int row = db.update(sql + " and userid='" + userid + "' and accountId='" + accountId + "'");
            if (row == 1) {
                jsonObject2.put("RESULT", "S");
            } else {
                jsonObject2.put("RESULT", "F");
            }
        } catch (Exception e) {
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