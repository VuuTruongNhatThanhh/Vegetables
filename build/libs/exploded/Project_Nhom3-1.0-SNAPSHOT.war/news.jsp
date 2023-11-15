<%--
  Created by IntelliJ IDEA.
  User: thien
  Date: 11/5/2022
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tin tức</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/news.css" type="text/css">
</head>
<body>
<%@include file="Include/header.jsp" %>
<section class="breadcrumb-section set-bg" style="background-image: url(images/bg.png);">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb_text">
                    <h2>Tin tức thực phẩm</h2>
                    <div class="breadcrumb_option">
                        <a href="./index.jsp">Trang chủ</a>
                        <span>Tin tức</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="ftco-section ftco-degree-bg">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 ftco-animate">
                <div class="row">
                    <div class="col-md-12 d-flex ftco-animate">
                        <div class="blog-entry align-self-stretch d-md-flex">
                            <a href="newsdetail.jsp" class="block-20"
                               style="background-image: url('images/news/image_1.jpg');">
                            </a>
                            <div class="text d-block pl-md-4">
                                <div class="meta mb-3">
                                    <div><a href="#">10/11/2022</a></div>
                                    <%--                                    <div><a href="#">Admin</a></div>--%>
                                    <%--                                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>--%>
                                </div>
                                <h3 class="heading"><a href="newsdetail.jsp">Cách chế biến những món ăn tươi ngon nhất
                                    từ rau củ quả</a></h3>
                                <p>Như chúng ta đã biết thì rau củ quả rất tốt cho sức khỏe, để có thể chế biến chúng
                                    thành những món ăn bắt mắt thì thật sự rất khó,
                                    mà để trẻ em thích thú với việc ăn chúng thì càng lại khó hơn nữa. Trong bài viết
                                    này chúng ta sẽ tìm cách ... </p>
                                <p><a href="newsdetail.jsp" class="btn btn-black py-2 px-3">Xem thêm</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 d-flex ftco-animate">
                        <div class="blog-entry align-self-stretch d-md-flex">
                            <a href="newsdetail.jsp" class="block-20"
                               style="background-image: url('images/news/image_2.jpg');">
                            </a>
                            <div class="text d-block pl-md-4">
                                <div class="meta mb-3">
                                    <div><a href="#">12/11/2022</a></div>
                                    <%--                                    <div><a href="#">Admin</a></div>--%>
                                    <%--                                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>--%>
                                </div>
                                <h3 class="heading"><a href="newsdetail.jsp">Thực phẩm hữu cơ - giải pháp cho bữa ăn của người bận rộn</a></h3>
                                <p>Một trong những băn khoăn lớn nhất của chúng ta khi ăn chính là rau bẩn. Nền công nghiệp canh tác hiện đại thường dùng thuốc trừ sâu, thuốc tăng trưởng và phân bón hóa học để thúc đẩy quá trình sinh trưởng của rau, nhằm thu lợi nhuận nhanh hơn. Tuy nhiên phương pháp này lại đem đến nguồn rau, củ độc hại, không tốt cho sức khỏe...
                                </p>
                                <p><a href="newsdetail.jsp" class="btn btn-black py-2 px-3">Xem thêm</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 d-flex ftco-animate">
                        <div class="blog-entry align-self-stretch d-md-flex">
                            <a href="newsdetail.jsp" class="block-20"
                               style="background-image: url('images/news/image_3.jpg');">
                            </a>
                            <div class="text d-block pl-md-4">
                                <div class="meta mb-3">
                                    <div><a href="#">14/11/2022</a></div>
                                    <%--                                    <div><a href="#">Admin</a></div>--%>
                                    <%--                                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>--%>
                                </div>
                                <h3 class="heading"><a href="newsdetail.jsp">Ăn rau củ quả như thế nào là đủ?</a></h3>
                                <p>Theo thông tin từ Viện Dinh dưỡng quốc gia, người Việt đang ăn thịt nhiều gấp đôi so với mức khuyến nghị. Cụ thể, trung bình một người tiêu thụ 134 gram thịt/ngày, trong khi con số Tổ chức Phòng chống Ung thư Thế giới đưa ra không vượt quá 70 gram/ngày. Đáng chú ý là song song với tiêu thụ thừa thịt, người Việt còn đang có xu hướng…</p>
                                <p><a href="newsdetail.jsp" class="btn btn-black py-2 px-3">Xem thêm</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 d-flex ftco-animate">
                        <div class="blog-entry align-self-stretch d-md-flex">
                            <a href="newsdetail.jsp" class="block-20"
                               style="background-image: url('images/news/image_4.jpg');">
                            </a>
                            <div class="text d-block pl-md-4">
                                <div class="meta mb-3">
                                    <div><a href="#">17/10/2022</a></div>
                                    <%--                                    <div><a href="#">Admin</a></div>--%>
                                    <%--                                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>--%>
                                </div>
                                <h3 class="heading"><a href="newsdetail.jsp">Rau củ quả giúp tăng cường sức khỏe tâm thần trẻ em</a></h3>
                                <p>Chuyên trang MedicineNet dẫn kết quả một nghiên cứu mới do nhóm học giả của Đại học East Anglia (Anh) thực hiện và được đăng tải trên tập san BMJ Nutrition Prevention & Health ...</p>
                                <p><a href="newsdetail.jsp" class="btn btn-black py-2 px-3">Xem thêm</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 d-flex ftco-animate">
                        <div class="blog-entry align-self-stretch d-md-flex">
                            <a href="newsdetail.jsp" class="block-20"
                               style="background-image: url('images/news/image_5.jpg');">
                            </a>
                            <div class="text d-block pl-md-4">
                                <div class="meta mb-3">
                                    <div><a href="#">22/08/2022</a></div>
                                    <%--                                    <div><a href="#">Admin</a></div>--%>
                                    <%--                                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>--%>
                                </div>
                                <h3 class="heading"><a href="newsdetail.jsp">Những món chay đơn giản mà ngon mùa Vu Lan</a>
                                </h3>
                                <p>Món chay được ưu ái trong thực đơn mùa Vu Lan với ý nghĩa thể hiện sự báo đáp công ơn và cầu phúc cho cha mẹ.</p>
                                <p><a href="newsdetail.jsp" class="btn btn-black py-2 px-3">Xem thêm</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 d-flex ftco-animate">
                        <div class="blog-entry align-self-stretch d-md-flex">
                            <a href="newsdetail.jsp" class="block-20"
                               style="background-image: url('images/news/image_6.jpg');">
                            </a>
                            <div class="text d-block pl-md-4">
                                <div class="meta mb-3">
                                    <div><a href="#">10/11/2022</a></div>
                                    <%--                                    <div><a href="#">Admin</a></div>--%>
                                    <%--                                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>--%>
                                </div>
                                <h3 class="heading"><a href="newsdetail.jsp">Nâng giá trị nông sản Việt</a>
                                </h3>
                                <p>Dịch Covid-19 thúc đẩy các mặt hàng nông sản, thủy sản chế biến phát triển mạnh khi việc tiêu thụ sản phẩm tươi khó khăn. Nhờ đó, giá trị gia tăng của nông sản Việt được nâng lên...</p>
                                <p><a href="newsdetail.jsp" class="btn btn-black py-2 px-3">Xem thêm</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- .col-md-8 -->
            <div class="col-lg-4 sidebar ftco-animate">
                <div class="sidebar-box">
                    <form action="#" class="search-form">
                        <div class="form-group">
                            <span class="icon ion-ios-search"></span>
                            <input type="text" class="form-control" placeholder="Bạn muốn tìm gì...">
                        </div>
                    </form>
                </div>
                <div class="sidebar-box ftco-animate">
                    <h3 class="heading">Hãy ghé thăm cửa hàng của chúng tôi</h3>
                    <ul class="categories">
                        <li><a href="#">Rau <span>(12)</span></a></li>
                        <li><a href="#">Củ <span>(22)</span></a></li>
                        <li><a href="#">Quả <span>(37)</span></a></li>
                        <li><a href="#">Ưu đãi <span>(42)</span></a></li>
                    </ul>
                </div>

                <div class="sidebar-box ftco-animate">
                    <h3 class="heading">Tin tức mới</h3>
                    <div class="block-21 mb-4 d-flex">
                        <a class="blog-img mr-4" style="background-image: url(images/news/image_1.jpg);"></a>
                        <div class="text">
                            <h3 class="heading-1"><a href="#">Cách chế biến những món ăn ngon nhất từ rau củ quả</a>
                            </h3>
                            <div class="meta">
                                <div><a href="#"><span class="icon-calendar"></span>10/11/2022</a></div>
                                <%--                                <div><a href="#"><span class="icon-person"></span> Admin</a></div>--%>
                                <%--                                <div><a href="#"><span class="icon-chat"></span> 19</a></div>--%>
                            </div>
                        </div>
                    </div>
                    <div class="block-21 mb-4 d-flex">
                        <a class="blog-img mr-4" style="background-image: url(images/news/image_2.jpg);"></a>
                        <div class="text">
                            <h3 class="heading-1"><a href="#">Các thực phẩm tốt cho sức khỏe tim mạch</a></h3>
                            <div class="meta">
                                <div><a href="#"><span class="icon-calendar"></span> 10/11/2022</a></div>
                                <%--                                <div><a href="#"><span class="icon-person"></span> Admin</a></div>--%>
                                <%--                                <div><a href="#"><span class="icon-chat"></span> 19</a></div>--%>
                            </div>
                        </div>
                    </div>
                    <div class="block-21 mb-4 d-flex">
                        <a class="blog-img mr-4" style="background-image: url(images/news/image_3.jpg);"></a>
                        <div class="text">
                            <h3 class="heading-1"><a href="#">Cách chế biến những món chay cho trẻ nhỏ</a></h3>
                            <div class="meta">
                                <div><a href="#"><span class="icon-calendar"></span> 10/11/2022</a></div>
                                <%--                                <div><a href="#"><span class="icon-person"></span> Admin</a></div>--%>
                                <%--                                <div><a href="#"><span class="icon-chat"></span> 19</a></div>--%>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="sidebar-box ftco-animate">
                    <h3 class="heading">Từ khóa được tìm kiếm nhiều</h3>
                    <div class="tagcloud">
                        <a href="#" class="tag-cloud-link">Bông cải</a>
                        <a href="#" class="tag-cloud-link">Cà chua</a>
                        <a href="#" class="tag-cloud-link">Xoài</a>
                        <a href="#" class="tag-cloud-link">Táo</a>
                        <a href="#" class="tag-cloud-link">Cà rốt</a>
                        <a href="#" class="tag-cloud-link">Cam</a>
                        <a href="#" class="tag-cloud-link">Rau muống</a>
                        <a href="#" class="tag-cloud-link">Rau dền</a>
                    </div>
                </div>

                <div class="sidebar-box ftco-animate">
                    <h3 class="heading">TAP ORGANIC</h3>
                    <p>Chúng tôi luôn cung cấp thực phẩm sạch và đem đến những trải nghiệm tốt nhất cho khách hàng</p>
                </div>
            </div>

        </div>
    </div>
</section> <!-- .section -->
<%@include file="Include/footer.jsp" %>
</body>
<script src="bootstrap/js/jquery.min.js"></script>
<script src="js/main.js"></script>
</html>
