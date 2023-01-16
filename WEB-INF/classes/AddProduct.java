import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.sql.*;
public class AddProduct extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		PrintWriter out=null;
		try{
			out=res.getWriter();
			String name=req.getParameter("name");
			String price=req.getParameter("price");
			String info=req.getParameter("info");
			
			//jdbc code
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/productdb","root","Incapp@12");
			Statement st=con.createStatement();
			st.executeUpdate("insert into product_info (product_name,product_price,product_info) values('"+name+"','"+price+"','"+info+"')");
			con.close();
			//jdbc code END
			out.print("<!doctype html>");
			out.print("<html>");
			out.print("<head>");
			out.print("<title>Product App</title>");
			out.print("</head>");
			out.print("<body>");
			out.print("<h1>Product APP</h1>");
			out.print("<p style='color:green;'>Product Successfully Added!</p>");
			out.print("</body>");
			out.print("</html>");
			out.close();
		}catch(SQLIntegrityConstraintViolationException ex){
			out.print("<!doctype html>");
			out.print("<html>");
			out.print("<head>");
			out.print("<title>Product App</title>");
			out.print("</head>");
			out.print("<body>");
			out.print("<h1>Product APP</h1>");
			out.print("<p style='color:red;'>User Already Added!</p>");
			out.print("</body>");
			out.print("</html>");
			out.close();
		}catch(Exception ex){
			out.print(ex);
			out.close();
		}
	}
}