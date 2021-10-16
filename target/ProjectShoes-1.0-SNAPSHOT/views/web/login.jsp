<%@include file="/common/taglib.jsp" %>
<script>
  <c:if test="${not empty type}">
  localStorage.setItem("jsessionid",${cookie.jsessionid})
  console.log("successfull")
  </c:if>
</script>
<section class="page-banner">
    <div class="container">
        <div class="page-banner-in">
            <div class="row">
                <div class="col-xl-6 col-lg-6 col-12">
                    <h1 class="page-banner-title text-uppercase">Login</h1>
                </div>
                <div class="col-xl-6 col-lg-6 col-12">
                    <ul class="right-side">
                        <li><a href="<c:url value="/trang-chu"/>">Home</a></li>
                        <li>Login</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="login pt-100">
    <div class="container">
        <div class="billing-details">
            <h2 class="checkout-title text-uppercase text-center mb-30">CUSTOMER LOGIN</h2>
            <div class="text-center mb-5 mx-1 mx-md-4 mt-4">
                <c:if test="${not empty message}">
                    <div class="alert alert-${alert}">
                            ${message}
                    </div>
                </c:if>
            </div>
            <form class="checkout-form" action="<c:url value='/dang-nhap'/>" id="formLogin"
                  method="post">
                <div class="form-group">
                    <label class="form-label">Username</label>
                    <input type="text" id="username" name="username" class="form-control"
                           placeholder="Username" required>
                </div>
                <div class="form-group">
                    <label class="form-label">Password</label>
                    <input type="password" id="password" name="password" class="form-control"
                           placeholder="Enter your Password"
                           required>
                </div>
                <div class="login-btn-g">
                    <div class="row">
                        <div class="col-6">
                            <div class="check-box">
                                        <span>
								                <input type="checkbox" class="checkbox" id="account"
                                                       name="Create an Account?">
								                <label for="account">Remember Me</label>
								            </span>
                            </div>
                        </div>
                        <div class="col-6">
                            <input type="hidden" value="login" name="action">
                            <button name="submit" type="submit" class="btn btn-color right-side">Log
                                In
                            </button>
                        </div>
                    </div>
                </div>
                <div class="text-center">
                    <div class="fb-login-button" data-width="" data-size="large"
                         data-button-type="continue_with" data-layout="default"
                         data-auto-logout-link="false" data-use-continue-as="false"></div>
                </div>
                <div class="text-center">
                    <div class="g-signin2" data-onsuccess="onSignIn" id="myP"
                         style="display: inline-block; height: 40px; width: calc(231.200px); border-radius: 100%; padding-top: calc(0.6px)"></div>
                    <div id="status">
                    </div>
                </div>
                <br>
                <div class="text-center">
                    <a title="Forgot Password" class="link forgot-password mtb-20"
                       href="<c:url value='/forgot-password'/>">Forgot
                        your password?</a>
                </div>
                <div class="new-account text-center mt-20">
                    <span>Don't have an account?</span>
                    <a class="link" title="Create New Account" href="<c:url value='/dang-ky'/>">Create
                        New Account</a>
                </div>
            </form>
        </div>
    </div>
</section>
