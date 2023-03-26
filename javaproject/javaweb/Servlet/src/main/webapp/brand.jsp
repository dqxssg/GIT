<%@ page import="org.example.pojo.Brand" %>
<%@ page import="java.util.ArrayList" %>
<%
    //查询数据库
    ArrayList<Brand> brands = new ArrayList<>();
    brands.add(new Brand(1, "三只松鼠1", "三只松鼠2", 100, "三只松鼠3", 1));
    brands.add(new Brand(2, "小米1", "小米2", 200, "小米3", 0));
    brands.add(new Brand(3, "苹果1", "苹果2", 300, "苹果3", 1));
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<input type="button" value="新增"><br>
<hr>
<table border="1" cellspacing="0" width="800">
    <tr>
        <th>序号</th>
        <th>品牌名称</th>
        <th>企业名称</th>
        <th>排序</th>
        <th>品牌介绍</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <%
        for (int i = 0; i < brands.size(); i++) {
            Brand brand = brands.get(i);
    %>
    </tr>
    <tr align="center">
        <td><%=brand.getId()%>
        </td>
        <td><%=brand.getBrandName()%>
        </td>
        <td><%=brand.getCompanyName()%>
        </td>
        <td><%=brand.getOrdered()%>
        </td>
        <td><%=brand.getDescription()%>
        </td>
        <%
            if (brand.getStatus() == 0) {
                //启用
        %>
        <td><%="启用"%>
                <%
            } else {
                //禁用
        %>
        <td><%="禁用"%>
            <%
                }
            %>
        </td>
        <td><a href="#">修改</a><a href="#">删除</a></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>