import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.sql.*;
public class SearchProduct extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		PrintWriter out=null;
		try{
			out=res.getWriter();
			String name=req.getParameter("name");
			
			//jdbc code
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/productdb","root","Incapp@12");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from product_info where product_name like '%"+name+"%'");
			out.print("<!doctype html>");
			out.print("<html>");
			out.print("<head>");
			out.print("<title>Product App</title>");
			out.print("</head>");
			out.print("<body>");
			out.print("<h1>Search result:</h1>");
			while(rs.next()){
				out.print("<div style='background-color:yellow;padding:20px;margin:10px;'>");	
				out.print("<p>Product Name: <b>"+rs.getString("product_name")+"</b></p>");
				out.print("<p>Product Price: <b>"+rs.getString("product_price")+"</b></p>");
				out.print("<p>Product Info: <b>"+rs.getString("product_info")+"</b></p>");
				
				out.print("</div>");
			}
			out.print("</body>");
			out.print("</html>");
			out.close();
			con.close();
			//jdbc code END
		}catch(Exception ex){
			out.print(ex);
			out.close();
		}
	}
}