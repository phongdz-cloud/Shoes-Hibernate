<%@include file="/common/taglib.jsp" %>
<c:url var="APICustomer" value="/api-customer"/>
<c:url var="loadProfile" value="/change-profile"/>
<head>
    <title>Update profile</title>
    <meta charset="utf-8">
</head>
<body>
<section class="page-banner">
    <div class="container">
        <div class="page-banner-in">
            <div class="row">
                <div class="col-xl-6 col-lg-6 col-12">
                    <h1 class="page-banner-title text-uppercase">UPDATE PROFILE</h1>
                </div>
                <div class="col-xl-6 col-lg-6 col-12">
                    <ul class="right-side">
                        <li><a href="<c:url value="/trang-chu"/>">Home</a></li>
                        <li>Update profile</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="login pt-100">
    <div class="container">
        <div class="billing-details">
            <h2 class="checkout-title text-uppercase text-center mb-30">UPDATE PROFILE</h2>
            <div class="checkout-title  text-center mb-30">
                <c:if test="${not empty message}">
                    <div class="alert alert-success">
                            ${message}
                    </div>
                </c:if>
            </div
            <form class="checkout-form" id="formSubmit">
                    <div class="form-group">
                        <label class="form-label">Firstname</label>
                        <input type="text" id="firstname" name="firstName" class="form-control"
                               placeholder="Firstname" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label">Lastname</label>
                        <input type="text" id="lastname" name="lastName" class="form-control"
                               placeholder="Firstname" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label">Gender</label>
                        <select id="gender" name="gender" class="custom-select">
                            <option>Male</option>
                            <option>Female</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-label">Phone</label>
                        <input type="text" id="phone" name="phone" class="form-control"
                               placeholder="Phone"
                               required>
                    </div>
                    <div class="form-group">
                        <label class="form-label">Address</label>
                        <input type="text" id="address" name="address" class="form-control"
                               placeholder="Address"
                               required>
                    </div>
                    <div class="form-group">
                        <label class="form-label">Content</label>
                        <textarea id="content" name="content"
                                  style="width: 500px; height: 175px"></textarea>
                    </div>
                <div class="form-group">
                    <label class="form-label">Avatar</label>
                    <input type="file" id="avatar" name="avatar" class="form-control"
                           placeholder="Avatar"
                           required>
                </div>
                <div class="login-btn-g">
                    <div class="row">
                        <div class="col-12">
                            <button id="btnUpdateUser" type="button"
                                    class="btn btn-color right-side">
                                Update
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>

<script>
  var linkURL = '';
  var editor = '';
  $(document).ready(function () {
    editor = CKEDITOR.replace('content')
  })

  $('#btnUpdateUser').click(function (e) {
    e.preventDefault();
    var data = {};
    var formData = $('#formSubmit').serializeArray();
    $.each(formData, function (i, v) {
      data["" + v.name + ""] = v.value;
    });
    data["content"] = editor.getData();
    console.log(data)
    if ($('#avatar').val() === '') {
      addCustomerOrUpdate(data);
    } else {
      uploadImage(data);
    }
  })

  function uploadImage(data) {
    const ref = firebase.storage().ref()
    console.log(document.querySelector('#avatar'));
    const file = document.querySelector('#avatar').files[0];
    const metadata = {
      contentType: file.type
    };
    const name = file.name;
    const uploadIMG = ref.child(name).put(file, metadata);
    uploadIMG.then(snapshort => snapshort.ref.getDownloadURL())
    .then(url => {
      data["avatar"] = url;
      addCustomerOrUpdate(data);
    })
    .catch(console.error)
  }

  function addCustomerOrUpdate(data) {
    $.ajax({
      url: '${APICustomer}',
      type: 'PUT',
      contentType: 'application/json',
      data: JSON.stringify(data),
      dataType: 'json',
      success: function (result) {
        if (result) {
          window.location.href = "${loadProfile}?action=updateProfile"
        }
      }
    })
  }
</script>

</body>


