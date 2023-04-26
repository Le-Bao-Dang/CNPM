package controller;

import bean.Product;
import services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddProduct", value = "/admins/addProduct")
public class AddProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        int sizeProduct = ProductService.getInstance().getListProduct().size();
        int price = Integer.parseInt(request.getParameter("price"));
        String imgSrc = "";
        String detail = request.getParameter("detail");
        String decription = request.getParameter("decription");
//        add Price
//        add Image
        List<String> imageList = new ArrayList<>();
//        Categry
//        add Product
        Product product = new Product(sizeProduct + 1, name, decription, price, imgSrc, 0);
        ProductService.getInstance().addProduct(product);
        System.out.println("đã thêm");
//        response.sendRedirect("/admins/uploadImageProduct");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
