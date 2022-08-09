package servlet;

import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = "/products")
public class ProductServlet extends HttpServlet {


    private static Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            products.add(new Product(i + 1, "Product" + (i + 1), i + 23 * 0.35f));
        }
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<th>id</th>");
        writer.println("<th>Title</th>");
        writer.println("<th>Price</th>");
        writer.println("</tr>");
        for (Product product : products) {
            writer.println("<tr>");
            writer.println("<td><a href='" + getServletContext().getContextPath() + "/product/" + product.getId() + "'>" + product.getId() + "</a></td>");
            writer.println("<td>" + product.getTitle() + "</td>");
            writer.println("<td>" + product.getCost() + "</td>");
            writer.println("</tr>");
        }

        writer.println("</table>");
        writer.close();
    }
}
