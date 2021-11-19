package com.bookstore.controller.admin;
import com.bookstore.entity.CategoryEntity;
import com.bookstore.service.CategoryService;
import com.bookstore.service_impl.CategoryService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
@RequestMapping("/admin2/")
public class CategoryAdd {
    public CategoryAdd(){super();}

    @RequestMapping(value = "cate/add", method = RequestMethod.GET)
    public String addCateForm() {

        return "admin/addcategory";
    }

    @RequestMapping(value = "cate/add", method = RequestMethod.POST)
    public String addCate(@RequestParam("category-name") String category_name,
                         ModelMap model,
                         PrintWriter out) {

        try{
            if((!category_name.equals("")) ) {
                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.setTen_DauSach(category_name);
                CategoryService category = new CategoryService_impl();
                category.save(categoryEntity);

                return "redirect:/admin/cate/list";
            }
            else {
                model.addAttribute("errorMessage", "Tên đầu sách trống");
                return "admin/addcategory";
            }
        }
        catch (Exception e)
        {
            out.print("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Loi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script>");
            out.println("alert('Trung ten dau sach')");
            out.println("location.href = \"./add\";");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
        return "redirect:/admin/cate/list";
    }
}
