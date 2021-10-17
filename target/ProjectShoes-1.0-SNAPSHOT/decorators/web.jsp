<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Home</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link tont-awesome.minype="image/x-icon" href="<c:url value='/template/web/images/fav-icon.png'/> " rel="icon">
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/template/web/css/f.css'/> ">
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/template/web/css/owl.carousel.css'/> ">
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/template/web/css/bootstrap.min.css'/> ">
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/template/web/css/magnific-popup.css'/> ">
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/web/css/animate.css'/> ">
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/web/css/shoes.css'/> ">
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/template/web/css/responsive.css'/> ">

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
    <div id="fb-root"></div>
    <script async defer crossorigin="anonymous"
            src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v12.0&appId=585165199352568&autoLogAppEvents=1"
            nonce="OzjgfUeq"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-client_id"
          content="945852596576-qdpeq7msdshcetuudjfqfe3svda8rljk.apps.googleusercontent.com">


</head>
<body>
<div id="preloader"></div>
<div class="sidebar-search-wrap">
    <div class="sidebar-table-container">
        <div class="sidebar-align-container">
            <div class="search-closer right-side"></div>
            <div class="search-container">
                <form method="get" id="search-form">
                    <input type="text" id="s" class="search-input" name="s"
                           placeholder="Search text">
                </form>
                <span>Search and Press Enter</span>
            </div>
        </div>
    </div>
</div>
<!-- Search Screen end -->

<%@ include file="/common/web/header.jsp" %>
<dec:body/>
<%@ include file="/common/web/footer.jsp" %>


<script data-cfasync="false" src="<c:url value='/template/web/js/email-decode.min.js'/>"></script>
<script src="<c:url value='/template/web/js/jquery-3.4.1.min.js'/>"></script>
<script src="<c:url value='/template/web/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/template/web/js/jquery.magnific-popup.min.js'/>"></script>
<script src="<c:url value='/template/web/js/owl.carousel.min.js'/>"></script>
<script src="<c:url value='/template/web/js/custom.js'/>"></script>
<script>
  /* ------------ Newslater-popup JS Start ------------- */
  $(window).on('load', function () {
    setTimeout(function () {
      mfp = $.magnificPopup.instance;
      if (!mfp.isOpen) {
        jQuery.magnificPopup.open({
          items: {
            // src: '#newslater-popup'
          },
          type: 'inline'
        }, 0);
      }
    }, 10000)
  });
  /* ------------ Newslater-popup JS End ------------- */
</script>

<script>

  function statusChangeCallback(response) {  // Called with the results from FB.getLoginStatus().
    console.log('statusChangeCallback');
    console.log(response);                   // The current login status of the person.
    if (response.status === 'connected') {   // Logged into your webpage and Facebook.
      testAPI();
    } else {                                 // Not logged into your webpage or we are unable to tell.
      // document.getElementById('status').innerHTML = 'Please log ' +
      // 'into this webpage.';
    }
  }

  function checkLoginState() {               // Called when a person is finished with the Login Button.
    FB.getLoginStatus(function (response) {   // See the onlogin handler
      statusChangeCallback(response);
    });
  }

  window.fbAsyncInit = function () {
    FB.init({
      appId: '585165199352568',
      cookie: true,                     // Enable cookies to allow the server to access the session.
      xfbml: true,                     // Parse social plugins on this webpage.
      version: 'v2.8'           // Use this Graph API version for this call.
    });

    FB.getLoginStatus(function (response) {   // Called after the JS SDK has been initialized.
      statusChangeCallback(response);        // Returns the login status.
    });
  };

  function testAPI() {                      // Testing Graph API after login.  See statusChangeCallback() for when this call is made.
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function (response) {
      console.log('Successful login for: ' + response.name);
      document.getElementById('status').innerHTML =
          'Thanks for logging in, ' + response.name + '!';
    });
  }

</script>

<script type="text/javascript">
  function onSignIn(googleUser) {
    // window.location.href='success.jsp';
    var profile = googleUser.getBasicProfile();
    var imagurl = profile.getImageUrl();
    var name = profile.getName();
    var email = profile.getEmail();
    document.getElementById("myImg").src = imagurl;
    document.getElementById("myP").style.visibility = "hidden";
  }
</script>

<script>
  function myFunction() {
    gapi.auth2.getAuthInstance().disconnect();
    location.reload();
  }
</script>

</body>

</html>
