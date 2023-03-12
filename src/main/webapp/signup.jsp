<%--
  Created by IntelliJ IDEA.
  User: thien
  Date: 12/4/2022
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="fontawesome-free-6.2.0-web/css/all.min.css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body id="abc">
<%
    String error = (String) request.getAttribute("error");
    String signUpName = request.getParameter("signUpName");
    String signUpEmail = request.getParameter("signUpEmail");
    String signUpPass = request.getParameter("signUpPass");
    String checkPass = request.getParameter("checkPass");
    signUpName = (signUpName == null) ? "" : signUpName;
    signUpEmail = (signUpEmail == null) ? "" : signUpEmail;
    signUpPass = (signUpPass == null) ? "" : signUpPass;
    checkPass = (checkPass == null) ? "" : checkPass;
    error = (error == null) ? "" : error;

%>
<div class="main">
    <div class="container b-container" id="b-container">
        <form class="form" id="b-form" method="post" action="/SignUp">
            <h2 class="form_title title">Tạo tài khoản</h2>
            <div class="form__icons"><fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
            </fb:login-button></div>

            <div id="status">
            </div>
            <span class="form__span">hoặc dùng email để đăng ký</span>
            <input class="form__input" type="text" name="signUpName" placeholder="Tên người dùng" value=<%=signUpName%>>
            <input class="form__input" type="email" name="signUpEmail" placeholder="Email" value=<%=signUpEmail%>>
            <input class="form__input" type="password" name="signUpPass" placeholder="Mật khẩu" value=<%=signUpPass%>>
            <input class="form__input" type="password" name="checkPass" placeholder="Xác nhận mật khẩu"
                   value=<%=checkPass%>>
            <p style="color: red;font-size: 18px"><%=error%>
            </p>
            <input style="margin-top: 0px" type="submit" class="button" value="ĐĂNG KÝ">
        </form>
    </div>
    <div class="switch" id="switch-cnt">
        <div class="switch__circle"></div>
        <div class="switch__circle switch__circle--t"></div>
        <div class="switch__container" id="switch-c1">
            <h2 class="switch__title title">CHÀO BẠN!</h2>
            <p class="switch__description description">Bạn đã có tài khoản?</p>
            <a href="login.jsp">
                <button class="switch__button button switch-btn">ĐĂNG NHẬP</button>
            </a>
        </div>
    </div>
</div>
<script>
    // This is called with the results from from FB.getLoginStatus().
    function statusChangeCallback(response) {
        console.log('statusChangeCallback');
        console.log(response);
        // The response object is returned with a status field that lets the
        // app know the current login status of the person.
        // Full docs on the response object can be found in the documentation
        // for FB.getLoginStatus().
        if (response.status === 'connected') {
            // Logged into your app and Facebook.
            testAPI();
        } else if (response.status === 'not_authorized') {
            // The person is logged into Facebook, but not your app.
            document.getElementById('status').innerHTML = 'Login with Facebook ';
        } else {
            // The person is not logged into Facebook, so we're not sure if
            // they are logged into this app or not.
            document.getElementById('status').innerHTML = 'Login with Facebook ';
        }
    }
    // This function is called when someone finishes with the Login
    // Button. See the onlogin handler attached to it in the sample
    // code below.
    function checkLoginState() {
        FB.getLoginStatus(function(response) {
            statusChangeCallback(response);
        });
    }
    window.fbAsyncInit = function() {
        FB.init({
            appId : '590399655968529',
            cookie : true, // enable cookies to allow the server to access
            // the session
            xfbml : true, // parse social plugins on this page
            version : 'v2.2' // use version 2.2
        });
        // Now that we've initialized the JavaScript SDK, we call
        // FB.getLoginStatus(). This function gets the state of the
        // person visiting this page and can return one of three states to
        // the callback you provide. They can be:
        //
        // 1. Logged into your app ('connected')
        // 2. Logged into Facebook, but not your app ('not_authorized')
        // 3. Not logged into Facebook and can't tell if they are logged into
        // your app or not.
        //
        // These three cases are handled in the callback function.

        FB.getLoginStatus(function(response) {
            statusChangeCallback(response);
        });
    };
    // Load the SDK asynchronously
    (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

    // Here we run a very simple test of the Graph API after login is
    // successful. See statusChangeCallback() for when this call is made.
    function testAPI() {
        console.log('Welcome! Fetching your information.... ');
        FB.api('/me?fields=name,email', function(response) {
            console.log('Successful login for: ' + response.name);

            document.getElementById("status").innerHTML = '<p>Welcome '+response.name+'! <a href=LoadControl?user_name='+ response.name.replace(" ", "_") +'&user_email='+ response.email +'>Continue with facebook login</a></p>'
        });
    }
</script>


<script type="text/javascript">
</script>
</body>
</html>
