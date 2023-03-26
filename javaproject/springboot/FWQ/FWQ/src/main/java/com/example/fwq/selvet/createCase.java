package com.example.fwq.selvet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class createCase
 */
@WebServlet("/createCase")
public class createCase extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */

    public createCase() {
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
        String name = jsonobject.getString("name");
        String sex = jsonobject.getString("sex");
        String ID = jsonobject.getString("ID");
        String birthday = jsonobject.getString("birthday");
        String tel = jsonobject.getString("tel");
        String address = jsonobject.getString("address");
        String urlString = request.getRequestURL().toString();
        urlString = urlString.substring(0, urlString.lastIndexOf("/"));
        System.out.println(urlString);
        System.out.println(request.getRemoteHost());
        System.err.println(new MyUtil().simpDate("yyyy-MM-dd HH:mm:ss", new java.util.Date()));
        reader.close();
        DB db = new DB();
        JSONObject jsonObject2 = new JSONObject();
        try {
            int row = db.update("insert into caseinfo (name,sex,ID,birthday,tel,address) values('" + name + "','"
                    + sex + "','" + ID + "','" + birthday + "','" + tel + "','" + address + "')");
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
