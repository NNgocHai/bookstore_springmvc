<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.entity.GioHangEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    //    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
//    response.setHeader("Pragma" , "no-cache");
//    response.setHeader("Expires" , "0");


    if (session.getAttribute("user_admin") == null) {
        response.sendRedirect(request.getContextPath() + "/admin/login");
    }


%>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div class="content-wrapper">
    <div class="container-fluid">
        <div class="row mt-3">
            <div class="col-lg-12">
                <div style="display: flex; justify-content: center; align-items: center; min-height: 90vh">
                    <canvas style="background-color: white; margin: 0 40px;" id="myChart" width="400" height="400"></canvas>
                    <canvas style="background-color: white; margin: 0 40px;" id="myChart2" width="400" height="400"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js" integrity="sha512-d9xgZrVZpmmQlfonhQUvTR7lMPtO7NkZMkA0ABN3PHCbKA5nqylQ/yWlFAyY6hYgdF1Qh6nYiuADWwKB4C2WSw==" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.bundle.js" integrity="sha512-zO8oeHCxetPn1Hd9PdDleg5Tw1bAaP0YmNvPY8CwcRyUk7d7/+nyElmFrB6f7vg4f7Fv4sui1mcep8RIEShczg==" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.css" integrity="sha512-C7hOmCgGzihKXzyPU/z4nv97W0d9bv4ALuuEbSf6hm93myico9qa0hv4dODThvCsqQUmKmLcJmlpRmCaApr83g==" crossorigin="anonymous" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js" integrity="sha512-hZf9Qhp3rlDJBvAKvmiG+goaaKRZA6LKUO35oK6EsM0/kjPK32Yw7URqrq3Q+Nvbbt8Usss+IekL7CRn83dYmw==" crossorigin="anonymous"></script>
<script>
    Chart.defaults.global.responsive = false;
    var ctx = document.getElementById('myChart');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ${TenDauSachList},
            datasets: [{
                label: 'Doanh thu theo đầu sách',
                data: ${DoanhThu_DauSachList},
                backgroundColor: ${ColorList_DoanhThuDauSach}

            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
    var ctx2 = document.getElementById('myChart2');
    var myChart2 = new Chart(ctx2, {
        type: 'bar',
        data: {
            labels: ${NgayList},
            datasets: [{
                label: 'Doanh thu 7 ngày gần nhất',
                data: ${DoanhThu7NgayList},
                backgroundColor: ${ColorList_DoanhThu7Ngay}

            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>
</body>
</html>
