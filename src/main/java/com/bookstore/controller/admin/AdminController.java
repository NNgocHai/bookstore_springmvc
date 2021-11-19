package com.bookstore.controller.admin;

import com.bookstore.dao.AdminDao;
import com.bookstore.dao.DonHangDao;
import com.bookstore.dao.ShipperDao;
import com.bookstore.dao_impl.AdminDao_impl;
import com.bookstore.dao_impl.DonHangDao_impl;
import com.bookstore.dao_impl.NavigationDao_impl;
import com.bookstore.dao_impl.ShipperDao_impl;
import com.bookstore.entity.*;
import com.bookstore.service.*;
import com.bookstore.service_impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    AdminService adminService = new AdminService_impl();
    @Autowired
    GioHangService gioHangService= new GioHangService_impl();
    @Autowired
    AdminDao adminDao = new AdminDao_impl();
    @Autowired
    CategoryService categoryService =new CategoryService_impl();
    @Autowired
    ChiTietDonHangService chiTietDonHangService = new ChiTietDonHangService_impl();
    @Autowired
    CustomerService customerService = new CustomerService_impl();
    @Autowired
    GiaoHangService giaoHangService = new GiaoHangService_impl();
    @Autowired
    ShipperDao shipperDao = new ShipperDao_impl();
    @Autowired
    DonHangDao donHangDao = new DonHangDao_impl();
    @Autowired
    DonHangService donHangService=new DonHangService_impl();
    @Autowired
    ProductService productService = new ProductService_impl();
    @Autowired
    NavigationDao_impl<CuonSachEntity> navigationDaoImpl = new NavigationDao_impl<CuonSachEntity>();
    @Autowired
    ReviewService reviewService=new ReviewService_impl();
    @Autowired
    ShipperService shipperService = new ShipperService_impl();

    @RequestMapping("admin/list")
    public String index(@ModelAttribute("message") String message, ModelMap model){

        List<AdminsEntity> adminList = adminService.findAll();
        model.addAttribute("adminList", adminList);
        model.addAttribute("message",   message);
        return "admin/viewlistadmin";
    }

    @RequestMapping(value = "admin/add", method = RequestMethod.GET)
    public String showform(ModelMap model,
                        @RequestParam(value = "message", required = false) String message){
        model.addAttribute("admin", new AdminsEntity());
        model.addAttribute("message", message);
        return "admin/addadmin";

    }
    @RequestMapping(value = "admin/add", method = RequestMethod.POST)
    public String addAdmin(@ModelAttribute("admin") AdminsEntity admin,
                         ModelMap model, BindingResult errors) {
        if (admin.getGmail_Admin().trim().length() == 0) {
            errors.rejectValue("gmail_Admin", "admin", "Vui lòng nhập gmail!");
        }
        if (admin.getHoten_Admin().trim().length() == 0) {
            errors.rejectValue("hoten_Admin", "admin", "Vui lòng nhập tên!");
        }
        if (admin.getMatkhau_Admin().trim().length() == 0) {
            errors.rejectValue("matkhau_Admin", "admin", "Vui lòng nhập mật khẩu!");
        }
        if (admin.getTaikhoan_Admin().trim().length() == 0) {
            errors.rejectValue("taikhoan_Admin", "admin", "Vui lòng nhập tài khoàn!");
        }
        if (errors.hasErrors()) {
            model.addAttribute("admin", admin);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/addadmin";
        }
        try {
            model.addAttribute("message", "Thêm thành công!");
            adminService.save(admin);
            return "redirect:/admin/admin/add";

        } catch (Exception e) {
            model.addAttribute("admin", admin);
            model.addAttribute("message", "Thêm thất bại!");
            return "admin/addadmin";
        }
    }

    @RequestMapping(value = "admin/edit", method = RequestMethod.GET)
    public String showEditForm(@RequestParam("admin-id") String adminId,
                               @RequestParam(value = "message", required = false) String message,
                               ModelMap model) {

        int admin_id= Integer.parseInt(adminId);
        AdminsEntity adminsEntity = adminService.findById(admin_id);
        model.addAttribute("admin", adminsEntity);

        return "admin/editadmin";
    }

    @RequestMapping(value = "admin/edit", method = RequestMethod.POST)
    public String editAdmin(@RequestParam("admin-id") String ma_admin,
                            @ModelAttribute("admin") AdminsEntity adminsEntity,
                            ModelMap model,
                            BindingResult errors,
                            final RedirectAttributes redirectAttributes) {

        try {
            if (adminsEntity.getGmail_Admin().trim().length() == 0) {
                errors.rejectValue("gmail_Admin", "admin", "Vui lòng nhập gmail!");
            }
            if (adminsEntity.getHoten_Admin().trim().length() == 0) {
                errors.rejectValue("hoten_Admin", "admin", "Vui lòng nhập tên!");
            }
            if (adminsEntity.getMatkhau_Admin().trim().length() == 0) {
                errors.rejectValue("matkhau_Admin", "admin", "Vui lòng nhập mật khẩu!");
            }
            if (adminsEntity.getTaikhoan_Admin().trim().length() == 0) {
                errors.rejectValue("taikhoan_Admin", "admin", "Vui lòng nhập tài khoàn!");
            }
            if (errors.hasErrors()) {
                model.addAttribute("admin", adminsEntity);
                model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
                return "admin/editadmin";
            }

            if(!ma_admin.equals("") || !ma_admin.equals("0")){
                adminService.update(adminsEntity);
                redirectAttributes.addFlashAttribute("message", "Đã chỉnh sửa id: " + ma_admin + " thành công");
                return "redirect:/admin/admin/list";
            }
            else {
                model.addAttribute("message", "Vui lòng điền đầy đủ các thông tin");
                model.addAttribute("admin", adminsEntity);

                return "admin/editadmin";
            }
        }
        catch (Exception e)
        {
            model.addAttribute("admin", adminsEntity);
            model.addAttribute("message", "Chỉnh sửa thất bại");
            return "admin/editadmin";
        }
    }

    @RequestMapping("admin/delete")
    public String delete(@RequestParam("admin-id") int admin_idd,
                        HttpSession session,
                        ModelMap model) {

        List<Integer> listId = new ArrayList<Integer>();
        String admin_tk = (String) session.getAttribute("user_admin");
        try{
            if(!(adminService.checkDelete(admin_tk, admin_idd))) {
                listId.add(admin_idd);
                adminService.deleteList(listId);
                String message= "Xóa thành công";
                model.addAttribute("message", message);
                return "redirect:/admin/admin/list";
            }
            else
            {
                model.addAttribute("message", "Xóa thất bại");
                return "redirect:/admin/admin/list";
            }
        }
        catch (Exception e)
        {
            model.addAttribute("message", "Xóa thất bại");
            return "redirect:/admin/admin/list";
        }
    }

    @RequestMapping("home")
    public String Home(ModelMap model) {
        List<Object[]> ReportDoanhThu7Ngay= gioHangService.ReportDoanhThu7Ngay();
        List<Long> DoanhThu7NgayList= new ArrayList<Long>();
        List<String> ColorList_DoanhThu7Ngay= new ArrayList<String>();

        List<String> NgayList= new ArrayList<String>();
        for(Object[] objects: ReportDoanhThu7Ngay) {
            String pattern = "MM/dd/yyyy";
            DateFormat df = new SimpleDateFormat(pattern);
            String convert = df.format(objects [0]);
            NgayList.add("'"+ convert + "'");
            DoanhThu7NgayList.add((Long) objects[1]);
            ColorList_DoanhThu7Ngay.add("`rgb(${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}`,");

        }


        List<Object[]> ReportDoanhThu_DauSach= chiTietDonHangService.ReportDoanhThu_DauSach();
        List<Long> DoanhThu_DauSachList= new ArrayList<Long>();
        List<String> TenDauSachList= new ArrayList<String>();
        List<String> ColorList_DoanhThuDauSach= new ArrayList<String>();

        for(Object[] objects: ReportDoanhThu_DauSach) {
            TenDauSachList.add("'"+(String) objects[1] + "'");
            DoanhThu_DauSachList.add((Long) objects[2]);
            ColorList_DoanhThuDauSach.add("`rgb(${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}`,");

        }
        model.addAttribute("ColorList_DoanhThuDauSach", ColorList_DoanhThuDauSach);
        model.addAttribute("ColorList_DoanhThu7Ngay", ColorList_DoanhThu7Ngay);


        model.addAttribute("DoanhThu_DauSachList", DoanhThu_DauSachList);
        model.addAttribute("TenDauSachList", TenDauSachList);
        model.addAttribute("NgayList", NgayList);
        model.addAttribute("DoanhThu7NgayList", DoanhThu7NgayList);

        return "admin/index";
    }
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String Login(){

        return "admin/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String Login(@RequestParam("user") String user,
                         @RequestParam("password") String password,
                         ModelMap model,
                         HttpSession session) {

        boolean a = adminDao.checkAdminLogin(user,password);
        if (a) {
            session.setAttribute("user_admin", user);
            session.setAttribute("password_admin", password);
            return "redirect:/admin/home";
        } else {
            model.addAttribute("errorMessage", "Tài khoản hoặc mật khẩu sai!");
            return "admin/login";
        }

    }

    @RequestMapping("logout")
    public String Logout(HttpSession session){

        session.removeAttribute("user_admin");
        session.removeAttribute("password_admin");

        session.invalidate();

        return "admin/login";
    }
    @RequestMapping("cate/list")
    public String cateList(@ModelAttribute("message") String message,
                           ModelMap model) {
        List<CategoryEntity> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("message", message);

        return "admin/viewlistcategory";
    }

    @RequestMapping(value = "cate/add", method = RequestMethod.GET)
    public String addCateForm(@RequestParam(value = "message", required = false) String message,
                              ModelMap model) {

        model.addAttribute("category", new CategoryEntity());
        model.addAttribute("message", message);
        return "admin/addcategory";
    }

    @RequestMapping(value = "cate/add", method = RequestMethod.POST)
    public String addCate(@ModelAttribute("category") CategoryEntity cate,
                          ModelMap model, BindingResult errors) {

        try{
            if(cate.getTen_DauSach().equals("") ||
                    cate.getTen_DauSach().trim().length() == 0){
                errors.rejectValue("ten_DauSach", "cate", "Vui lòng điền tên đầu sách");
            }
            else{
                categoryService.save(cate);
                model.addAttribute("message", "Thêm đầu sách thành công!");
                return "redirect:/admin/cate/add";
            }
        }
        catch (Exception e)
        {
            model.addAttribute("category", cate);
            model.addAttribute("message", "Thêm đầu sách thất bại");
        }
        return "admin/addcategory";
        //return "redirect:/admin/cate/list";
    }

    @RequestMapping(value = "cate/edit", method = RequestMethod.GET)
    public String editCateForm(@RequestParam("category-id") String cate_id,
                               ModelMap model){
        int category_id= Integer.parseInt(cate_id);
        CategoryEntity categoryEntity = categoryService.findById(category_id);
        model.addAttribute("category", categoryEntity);

        return "admin/editcategory";
    }

    @RequestMapping(value = "cate/edit", method = RequestMethod.POST)
    public String editCate(@RequestParam("category-id") String cate_id,
                           @ModelAttribute("category") CategoryEntity categoryEntity,
                           ModelMap model, BindingResult errors,
                           final RedirectAttributes redirectAttributes) {

        if(categoryEntity.getTen_DauSach().equals("") ||
                categoryEntity.getTen_DauSach().trim().length() == 0){
            errors.rejectValue("ten_DauSach", "cate", "Vui lòng điền tên đầu sách");
        }

        if(!categoryEntity.getMa_DauSach().equals("") ||
                !categoryEntity.getTen_DauSach().equals(""))
        {
            categoryService.update(categoryEntity);
            redirectAttributes.addFlashAttribute("message", "Đã chỉnh sửa id: " + cate_id + " thành công");
            return "redirect:/admin/cate/list";
        }
        else {

            model.addAttribute("message","Tên đầu sách trống");
            model.addAttribute("category_id",cate_id);

            return "admin/editcategory";
        }

    }

    @RequestMapping("cate/delete")
    public String deleteCate(@RequestParam("category-id") String cate_id,
                             ModelMap model
                             ) {
        int category_id= Integer.parseInt(cate_id);

        List<Integer> listId = new ArrayList<Integer>();
        try {
            listId.add(category_id);
            categoryService.deleteList(listId);
            model.addAttribute("message",  "Xoá thành công");
            return "redirect:/admin/cate/list";
        }
            catch(Exception e){
                model.addAttribute("message", "Xóa thất bại");
                return "redirect:/admin/cate/list";
            }

    }
    @RequestMapping("chitietdonhang/list")
    public String detailList( ModelMap model) {
        List<ChiTietDonHangEntity> list = chiTietDonHangService.findSpec();
        model.addAttribute("list", list);

        return "admin/viewlistCTDH";
    }

    @RequestMapping("chitietdonhang/details")
    public String Detail(HttpSession session,
                        @RequestParam("DH-id") String donhang_id,
                        ModelMap model) {

        if (session.getAttribute("user_admin") == null){
            return "redirect:/admin/login";
        }
        else {
            int DH_id = Integer.parseInt(donhang_id);

            List<ChiTietDonHangEntity> listdetails = chiTietDonHangService.FindDetails(DH_id);
            model.addAttribute("DH_id", DH_id);
            model.addAttribute("listdetails", listdetails);

            return "admin/viewlistCTDHdetails";
        }
    }

    @RequestMapping("user/list")
    public String userList(@ModelAttribute("message") String message,
                           ModelMap model) {
        List<CustomerEntity> customerList = customerService.findAll();
        model.addAttribute("customerList", customerList);
        model.addAttribute("message", message);

        return "admin/viewlistcustomer";
    }

    @RequestMapping(value = "user/add", method = RequestMethod.GET)
    public String addUserForm(ModelMap model,
                              @RequestParam(value = "message", required = false) String message){

        model.addAttribute("customer", new CustomerEntity());
        model.addAttribute("message", message);
        return "admin/adduser";
    }

    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("customer") CustomerEntity customerEntity,
                          ModelMap model,
                          BindingResult errors) {

        if(customerEntity.getTaikhoan_Customer().equals("")){
            errors.rejectValue("taikhoan_Customer", "customer", "Vui lòng điền tài khoản");
        }

        if(customerEntity.getMatkhau_Customer().equals("")){
            errors.rejectValue("matkhau_Customer", "customer", "Vui lòng điền mật khẩu");
        }
        if(customerEntity.getHoten_Customer().equals("")){
            errors.rejectValue("hoten_Customer", "customer", "Vui lòng điền tên");
        }
        if(customerEntity.getGmail_Customer().equals("")){
            errors.rejectValue("gmail_Customer", "customer", "Vui lòng điền địa chỉ email");
        }
        if(customerEntity.getSdt_Customer().equals("")){
            errors.rejectValue("sdt_Customer", "customer", "Vui lòng điền số điện thoại");
        }
        if(customerEntity.getVitien().equals("")){
            errors.rejectValue("vitien", "customer", "Vui lòng điền số khởi đầu");
        }

        if (errors.hasErrors()) {
            model.addAttribute("customer", customerEntity);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/adduser";
        }

        try{
            customerService.save(customerEntity);
            model.addAttribute("message", "Thêm  thành công!");
            return "redirect:/admin/user/add";
        }
        catch (Exception e)
        {
            model.addAttribute("message", "Thêm  thất bại!");
            return "admin/adduser";
        }


    }

    @RequestMapping(value = "user/edit", method = RequestMethod.GET)
    public String editForm(@RequestParam("id") String userid,
                           ModelMap model) {

        int id= Integer.parseInt(userid);
        CustomerEntity customerEntity = customerService.findById(id);
        model.addAttribute("customer", customerEntity);

        return "admin/edituser";
    }

    @RequestMapping(value = "user/edit", method = RequestMethod.POST)
    public String editUser(@RequestParam("id") String id,
                           @ModelAttribute("customer") CustomerEntity customerEntity,
                           ModelMap model,BindingResult errors,
                           final RedirectAttributes redirectAttributes) {

        if(customerEntity.getTaikhoan_Customer().equals("")){
            errors.rejectValue("taikhoan_Customer", "customer", "Vui lòng điền tài khoản");
        }

        if(customerEntity.getMatkhau_Customer().equals("")){
            errors.rejectValue("matkhau_Customer", "customer", "Vui lòng điền mật khẩu");
        }
        if(customerEntity.getHoten_Customer().equals("")){
            errors.rejectValue("hoten_Customer", "customer", "Vui lòng điền tên");
        }
        if(customerEntity.getGmail_Customer().equals("")){
            errors.rejectValue("gmail_Customer", "customer", "Vui lòng điền địa chỉ email");
        }
        if(customerEntity.getSdt_Customer().equals("")){
            errors.rejectValue("sdt_Customer", "customer", "Vui lòng điền số điện thoại");
        }
        if(customerEntity.getVitien().equals("")){
            errors.rejectValue("vitien", "customer", "Vui lòng điền số khởi đầu");
        }

        if (errors.hasErrors()) {
            model.addAttribute("customer", customerEntity);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/edituser";
        }

        try {
            customerService.update(customerEntity);
            redirectAttributes.addFlashAttribute("message", "Đã chỉnh sửa id: " + id + " thành công");
            return "redirect:/admin/user/list";

        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage", "Chỉnh sửa Customer thất bại");
            return "admin/edituser";
        }
    }

    @RequestMapping("user/delete")
    public String userDelete(@RequestParam("id") String userid,
                             ModelMap model) {
        int id= Integer.parseInt(userid);
        List<Integer> listId = new ArrayList<Integer>();
        try{
            listId.add(id);
            customerService.deleteList(listId);
            model.addAttribute("message", "Xoá thành công");
            return "redirect:/admin/user/list";

        }
        catch (Exception e)
        {
            model.addAttribute("message", "Xoá thất bại");
            return "redirect:/admin/user/list";
        }
    }
    @RequestMapping("giaohang/empty")
    public String GiaoHang(ModelMap model) {
        return "admin/emptyDH";

    }
    @RequestMapping(value = "giaohang/list", method = RequestMethod.GET)
    public String GiaoHangList(ModelMap model) {

        List<GiaoHangEntity> list = giaoHangService.findTT_GH();
        model.addAttribute("list", list);
        return "admin/viewlistGH";
    }
    @RequestMapping(value = "giaohang/phancong", method = RequestMethod.GET)
    public String PhanCong(ModelMap model) {
        List<ShipperEntity> listSP = shipperDao.findAll();
        model.addAttribute("listSP", listSP);
        List<DonHangEntity> listDHCG = donHangDao.Find_DHCG();
        model.addAttribute("listDHCG", listDHCG);
        if (listDHCG.size() != 0) {
            return "admin/phancongGH";

        } else {
            return "admin/emptyDH";
        }
    }
    @RequestMapping(value = "giaohang/phancong", method = RequestMethod.POST)
    public String PhanCong() {
        return "";
    }
    @RequestMapping("giaohang/phancong_2")
    public String PhanCong_2(ModelMap model, @RequestParam(value = "id", required = false) String id,
                        HttpServletRequest request) {

        if (id == null) {
            List<DonHangEntity> listDHCG = donHangDao.Find_DHCG();
            List<DonHangEntity> listDH_DaChon = new ArrayList<DonHangEntity>();
            for (DonHangEntity donHangEntity : listDHCG) {
                if (request.getParameter(String.valueOf(donHangEntity.getMa_DH())) != null) {
                    listDH_DaChon.add(donHangEntity);
                }
            }
            List<ShipperEntity> shipperEntities = new ArrayList<ShipperEntity>();
            shipperEntities = shipperService.findAll();
            HttpSession session = request.getSession();
            session.setAttribute("listDH_DaChon", listDH_DaChon);
            model.addAttribute("shipperEntities", shipperEntities);

            return "admin/phancongGH_2";

        } else {

            HttpSession session = request.getSession();
            List<DonHangEntity> listDH_DaChon = (List<DonHangEntity>) session.getAttribute("listDH_DaChon");
            for (DonHangEntity donHangEntity : listDH_DaChon) {
                GiaoHangIDKey giaoHangIDKey = new GiaoHangIDKey();
                GiaoHangEntity giaoHangEntity = new GiaoHangEntity();
                giaoHangIDKey.setMa_DH(donHangEntity.getMa_DH());
                giaoHangIDKey.setMa_Shiper(Integer.valueOf(id));
                giaoHangEntity.setId(giaoHangIDKey);
                giaoHangService.save(giaoHangEntity);
            }
            session.removeAttribute("listDH_DaChon");
            return "/admin/phancongGH";

        }
    }
    @RequestMapping("order/delete")
    public String OrderDelete(ModelMap model, @RequestParam(value = "order_id", required = false) String order_id) {

        DonHangService_impl donhang = new DonHangService_impl();
        List<Integer> listId = new ArrayList<Integer>();
        try{
            listId.add(Integer.parseInt(order_id));
            donhang.deleteList(listId);
//            model.addAttribute("donhangList", donhang.findAll());
            model.addAttribute("message", "Xóa thành công");
            return "redirect:/admin/order/list";

        }
        catch (Exception e)
        {
            model.addAttribute("message", "Xóa thất bại");
            return "redirect:/admin/order/list";
        }
    }

    @RequestMapping(value = "order/edit", method = RequestMethod.GET)
    public String OrderEdit(ModelMap model, @RequestParam("order_id") String order_id) {

        DonHangEntity donHangEntity = donHangService.findById(Integer.parseInt(order_id));
        model.addAttribute("order", donHangEntity);
        return "admin/editdonhang";
    }
    @RequestMapping(value = "order/edit", method = RequestMethod.POST)
    public String doPost(@ModelAttribute("order") DonHangEntity order,
                         ModelMap model, BindingResult errors, @RequestParam("order_id") String order_id) {
        if (order.getMa_Customer() == null) {
            errors.rejectValue("ma_Customer", "order", "Vui lòng nhập mã khách hàng!");
        }
        order.setMa_DH(Integer.parseInt(order_id));
        if (order.getDiachi().toString().trim().length() == 0) {
            errors.rejectValue("diachi", "order", "Vui lòng nhập địa chỉ!");
        }
        if (order.getSdt() == null) {
            errors.rejectValue("sdt", "order", "Vui lòng nhập số điện thoại!");
        }
        if (order.getNgaydat() == null) {
            errors.rejectValue("ngaydat", "order", "Vui lòng nhập ngày đặt đơn hàng!");
        }
        if (order.getTongtien() == null) {
            errors.rejectValue("tongtien", "order", "Vui lòng nhập tổng số tiền!");
        }
        if (order.getActiveDH() == null) {
            errors.rejectValue("activeDH", "order", "Vui lòng nhập tình trạng đơn hàng!");
        }

        if (errors.hasErrors()) {
            model.addAttribute("order", order);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/editdonhang";
        }

        try {
            DonHangService donHangService = new DonHangService_impl();
            model.addAttribute("message", "Cập nhật thành công!");
            donHangService.update(order);
            return "admin/editdonhang";
        } catch (Exception e) {
            model.addAttribute("order", order);
            model.addAttribute("message", "Cập nhật thất bại!");
            return "admin/editdonhang";
        }
    }
    @RequestMapping("order/list")
    public String OrderList(ModelMap model, @ModelAttribute("message") String message) {
        List<DonHangEntity> donhangList = donHangService.findAll();
        model.addAttribute("donhangList", donhangList);
        model.addAttribute("message",   message);
        return "/admin/viewlistdonhang";
    }
    @RequestMapping(value = "product/add", method = RequestMethod.GET)
    public String ProductAdd(ModelMap model, @ModelAttribute(value = "message") String message) {
        model.addAttribute("product", new CuonSachEntity());
        model.addAttribute("message", message);
        return "admin/addcuonsach";

    }

    @RequestMapping(value = "product/add", method = RequestMethod.POST)
    public String ProductAdd(@ModelAttribute("product") CuonSachEntity product,
                         ModelMap model, BindingResult errors) {
        if (product.getMa_DauSach()== null) {
            errors.rejectValue("ma_DauSach", "product", "Vui lòng nhập mã đầu sách!");
        }
        if (product.getTen_CuonSach().toString().trim().length() == 0) {
            errors.rejectValue("ten_CuonSach", "product", "Vui lòng nhập tên sách!");
        }
        if (product.getGiabia() == null) {
            errors.rejectValue("giabia", "product", "Vui lòng nhập giá!");
        }
        if (product.getSoluong()== null) {
            errors.rejectValue("soluong", "product", "Vui lòng nhập số lượng!");
        }
        if (product.getTacgia().toString().trim().length() == 0) {
            errors.rejectValue("tacgia", "product", "Vui lòng nhập tên tác giả!");
        }
        if (product.getDiscount()== null) {
            errors.rejectValue("discount", "product", "Vui lòng nhập discount!");
        }
        if (product.getMota().toString().trim().length() == 0) {
            errors.rejectValue("mota", "product", "Vui lòng nhập mô tả!");
        }
        if (errors.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/addcuonsach";
        }

        try {

            productService.save(product);


            model.addAttribute("message", "Thêm thành công!");
            return "redirect:/admin/product/add";
        } catch (Exception e) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Thêm thất bại!");
            return "admin/addcuonsach";
        }
    }
    @RequestMapping(value = "product/delete", method = RequestMethod.GET)
    public String ProductDelete(ModelMap model, @RequestParam("cuonsach_id") String cuonsach_id) {

        List<Integer> listId = new ArrayList<Integer>();
        try{
            listId.add(Integer.parseInt(cuonsach_id));
            productService.deleteList(listId);
//            model.addAttribute("cuonsachList",productService.findAll());
            model.addAttribute("message", "Xóa thành công");
            return "redirect:/admin/product/list";
        }
        catch (Exception e){
            model.addAttribute("message", "Xóa thất bại");
            return "redirect:/admin/product/list";
        }

    }

    @RequestMapping(value = "product/edit", method = RequestMethod.GET)
    public String ProductEdit(ModelMap model, @RequestParam("cuonsach_id") String id) {

        CuonSachEntity cuonSachEntity=productService.findById(Integer.parseInt(id));
        model.addAttribute("product",cuonSachEntity);
        return "admin/editcuonsach";
    }

    @RequestMapping(value = "product/edit", method = RequestMethod.POST)
    public String ProductEdit(@ModelAttribute("product") CuonSachEntity product,
                         ModelMap model, BindingResult errors, @RequestParam("cuonsach_id") String cuonsach_id) {
        product.setMa_CuonSach(Integer.parseInt(cuonsach_id));
        if (product.getMa_DauSach()== null) {
            errors.rejectValue("ma_DauSach", "product", "Vui lòng nhập mã đầu sách!");
        }
        if (product.getTen_CuonSach().toString().trim().length() == 0) {
            errors.rejectValue("ten_CuonSach", "product", "Vui lòng nhập tên sách!");
        }
        if (product.getGiabia() == null) {
            errors.rejectValue("giabia", "product", "Vui lòng nhập giá!");
        }
        if (product.getSoluong()== null) {
            errors.rejectValue("soluong", "product", "Vui lòng nhập số lượng!");
        }
        if (product.getTacgia().toString().trim().length() == 0) {
            errors.rejectValue("tacgia", "product", "Vui lòng nhập tên tác giả!");
        }
        if (product.getAnh_CuonSach().toString().trim().length() == 0) {
            errors.rejectValue("anh_CuonSach", "product", "Vui lòng nhập ảnh!");
        }
        if (product.getDiscount()== null) {
            errors.rejectValue("discount", "product", "Vui lòng nhập discount!");
        }
        if (product.getMota().toString().trim().length() == 0) {
            errors.rejectValue("mota", "product", "Vui lòng nhập mô tả!");
        }
        if (errors.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/editcuonsach";
        }

        try {
            model.addAttribute("message", "Cập nhật thành công!");
            productService.update(product);
            return "admin/editcuonsach";
        } catch (Exception e) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Cập nhật thất bại!");
            return "admin/editcuonsach";
        }
    }
    @RequestMapping(value = "product/list", method = RequestMethod.GET)
    public String ProductList(ModelMap model,
                        @RequestParam(value = "page", required = false ) String page,
                        @ModelAttribute(value = "message") String message) {

        if (page == null)
            page= "1";
        int maxResult = 10;// so bang record load len 1 trang
        int maxNavigationPage = 6;// max so trang hien ra

        navigationDaoImpl.Navigation(Integer.valueOf(page),maxResult,maxNavigationPage );
        model.addAttribute("cuonsachList", navigationDaoImpl.getList());
        model.addAttribute("navigationDaoImpl", navigationDaoImpl);
        model.addAttribute("message",   message);

        return "admin/viewlistcuonsach";

    }
    @RequestMapping(value = "review/list", method = RequestMethod.GET)
    public String ReviewList(ModelMap model ) {
        List<ReviewEntity> reviewList = reviewService.findAll();
        model.addAttribute("reviewList", reviewList);
        return "/admin/reviewviewlist";
    }

    @RequestMapping(value = "ship/add", method = RequestMethod.GET)
    public String ShipperAdd(ModelMap model,
                        @RequestParam(value = "message", required = false) String message){
        model.addAttribute("shipper", new ShipperEntity());
        model.addAttribute("message", message);
        return "admin/addshipper";
    }

    @RequestMapping(value = "ship/add", method = RequestMethod.POST)
    public String ShipperAdd(@ModelAttribute("shipper") ShipperEntity shipper,
                         ModelMap model, BindingResult errors) {

        if (shipper.getGmail_Shipper().trim().length() == 0) {
            errors.rejectValue("gmail_Shipper", "shipper", "Vui lòng nhập gmail!");
        }
        if (shipper.getHoten_Shipper().trim().length() == 0) {
            errors.rejectValue("hoten_Shipper", "shipper", "Vui lòng nhập tên!");
        }
        if (shipper.getMatkhau_Shipper().trim().length() == 0) {
            errors.rejectValue("matkhau_Shipper", "shipper", "Vui lòng nhập mật khẩu!");
        }
        if (shipper.getTaikhoan_Shipper().trim().length() == 0) {
            errors.rejectValue("taikhoan_Shipper", "shipper", "Vui lòng nhập tài khoàn!");
        }
        if (errors.hasErrors()) {
            model.addAttribute("shipper", shipper);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/addshipper";
        }
        try {
            model.addAttribute("message", "Thêm thành công!");
            shipperService.save(shipper);
            return "redirect:/admin/ship/add";
        } catch (Exception e) {
            model.addAttribute("shipper", shipper);
            model.addAttribute("message", "Thêm thất bại!");
            return "admin/addshipper";
        }



    }
    @RequestMapping(value = "ship/delete", method = RequestMethod.GET)
    public String ShipperDelete(ModelMap model, @RequestParam("shipper_id") String shipper_id) {
        int id= Integer.parseInt(shipper_id);
        List<Integer> listId = new ArrayList<Integer>();

        try {
            listId.add(id);
            shipperService.deleteList(listId);
//            model.addAttribute("shipperList", shipper.findAll());
            model.addAttribute("message", "Xóa thành công");
            return "redirect:/admin/ship/list";
        }
        catch (Exception e){
            model.addAttribute("message", "Xóa thất bại");
            return "redirect:/admin/ship/list";
        }


    }

    @RequestMapping(value = "ship/edit", method = RequestMethod.GET)
    public String ShipperEdit(ModelMap model, @RequestParam(value = "shipper_id", required = false) String shipper_id){
        int id = Integer.parseInt(shipper_id);
        ShipperEntity shipper = shipperService.findById(id);
        model.addAttribute("shipper", shipper);
        return "admin/editshipper";
    }

    @RequestMapping(value = "ship/edit", method = RequestMethod.POST)
    public String ShipperEdit(@ModelAttribute("shipper") ShipperEntity shipper,
                         ModelMap model, BindingResult errors, @RequestParam("shipper_id") String ma_Shipper)  {
        shipper.setMa_Shipper(Integer.parseInt(ma_Shipper));
        if (shipper.getGmail_Shipper().trim().length() == 0) {
            errors.rejectValue("gmail_Shipper", "shipper", "Vui lòng nhập gmail!");
        }
        if (shipper.getHoten_Shipper().trim().length() == 0) {
            errors.rejectValue("hoten_Shipper", "shipper", "Vui lòng nhập tên!");
        }
        if (shipper.getMatkhau_Shipper().trim().length() == 0) {
            errors.rejectValue("matkhau_Shipper", "shipper", "Vui lòng nhập mật khẩu!");
        }
        if (shipper.getTaikhoan_Shipper().trim().length() == 0) {
            errors.rejectValue("taikhoan_Shipper", "shipper", "Vui lòng nhập tài khoàn!");
        }
        if (errors.hasErrors()) {
            model.addAttribute("shipper", shipper);
            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "admin/editshipper";
        }
        try {
            model.addAttribute("message", "Cập nhật thành công!");
            shipperService.update(shipper);
            return "admin/editshipper";
        } catch (Exception e) {
            model.addAttribute("shipper", shipper);
            model.addAttribute("message", "Cập nhật thất bại!");
            return "admin/editshipper";
        }
    }
    @RequestMapping(value = "ship/list", method = RequestMethod.GET)
    public String ShipperList(ModelMap model, @ModelAttribute("message") String message) {
        List<ShipperEntity> shipperList = shipperService.findAll();
        model.addAttribute("shipperList", shipperList);
        model.addAttribute("message",   message);
        return "admin/shipperviewlist";

    }
}
