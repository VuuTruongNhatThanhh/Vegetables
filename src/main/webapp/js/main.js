//navbar-header
$(".nav-item").click(function () {
    $(".nav-item").removeClass("active");
    $(this).addClass("active");
});

//hien menu
$("#menu").click(function () {
    if ($(".menu-drop").css("display") == "none")
        $(".menu-drop").css("display", "block");
    else
        $(".menu-drop").css("display", "none");
});

$(".input-radio").on("click", function () {
    $(".payment_box").css("display", "none")
    $(this).siblings("div.payment_box").css("display", "block");
});

$(".cart").hover(function () {
    $(".top-cart-content").css("display", "block");
}, function () {
    $(".top-cart-content").css("display", "none");
});

$(".top-cart-content").hover(function () {
    $(".top-cart-content").css("display", "block");
}, function () {
    $(".top-cart-content").css("display", "none");
});























