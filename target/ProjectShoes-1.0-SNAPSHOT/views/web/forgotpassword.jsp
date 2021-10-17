<%@include file="/common/taglib.jsp" %>
<section class="page-banner">
    <div class="container">
        <div class="page-banner-in">
            <div class="row">
                <div class="col-xl-6 col-lg-6 col-12">
                    <h1 class="page-banner-title text-uppercase">Forgot password</h1>
                </div>
                <div class="col-xl-6 col-lg-6 col-12">
                    <ul class="right-side">
                        <li><a href="<c:url value="/trang-chu"/>">Home</a></li>
                        <li>Forgot password</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="login pt-100">
    <div class="container">
        <div class="billing-details">
            <h2 class="checkout-title text-uppercase text-center mb-30">FORGOT PASSWORD</h2>
            <form class="checkout-form" action="<c:url value='/dang-nhap'/>" id="formLogin"
                  method="post">
                <div class="form-group">
                    <label class="form-label">Email</label>
                    <input type="text" id="email" name="email" class="form-control"
                           placeholder="Email" required>
                </div>
                <div class="login-btn-g">
                    <div class="row">
                        <div class="col-8">
                            <button name="submit" type="submit" class="btn btn-color right-side">
                                Send email
                            </button>
                        </div>
                    </div>
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