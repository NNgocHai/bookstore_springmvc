
package com.bookstore.controller.admin;


import com.bookstore.dao_impl.NavigationDao_impl;
        import com.bookstore.entity.CuonSachEntity;
        import com.bookstore.service_impl.CategoryService_impl;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.ModelMap;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.RequestDispatcher;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

@Controller
@RequestMapping("/admin/")
public class ProductViewList2 {
    public ProductViewList2() {
        super();
    }

    @RequestMapping(value = "product/list", method = RequestMethod.GET)
    public String doGet(ModelMap model,
                        @RequestParam(value = "page", required = false ) String page,
                        @ModelAttribute(value = "message") String message) {

        if (page == null)
            page= "1";
        int maxResult = 10;// so bang record load len 1 trang
        int maxNavigationPage = 6;// max so trang hien ra

        CategoryService_impl categoryService =new CategoryService_impl();
        NavigationDao_impl<CuonSachEntity> navigationDaoImpl = new NavigationDao_impl<CuonSachEntity>();
        navigationDaoImpl.Navigation(Integer.valueOf(page),maxResult,maxNavigationPage );
        model.addAttribute("cuonsachList", navigationDaoImpl.getList());
        model.addAttribute("navigationDaoImpl", navigationDaoImpl);
        model.addAttribute("message",   message);

        return "admin/viewlistcuonsach";

    }
}
