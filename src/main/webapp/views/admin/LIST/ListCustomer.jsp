<%@include file="/common/taglib.jsp" %>
<c:url var="APICustomer" value="/api-customer/"/>
<c:url var="loadCustomer" value="/admin-customers"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="page-holder bg-gray-100">
    <div class="container-fluid px-lg-4 px-xl-5">
        <!-- Breadcrumbs -->
        <div class="page-breadcrumb">
            <ul class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                <li class="breadcrumb-item active">Customers</li>
            </ul>
        </div>
        <!-- Page Header-->
        <div class="page-header">
            <h1 class="page-heading">Customers</h1>
            <ul class="list-inline text-sm">
                <li class="list-inline-item"><a class="text-gray-600" href="#"><i
                        class="fas fa-upload me-2"> </i>Import</a></li>
                <li class="list-inline-item"><a class="text-gray-600" href="#"><i
                        class="fas fa-download me-2"> </i>Export</a></li>
            </ul>
        </div>
        <form id="submitForm">
            <div class="card card-table mb-4">
                <div class="card-body">
                    <div class="preload-wrapper">
                        <div class="table-responsive" id="customerPage">
                            <table class="table table-hover mb-0" id="customersDatatable">
                                <thead>
                                <tr>
                                    <th>Customer</th>
                                    <th>Firstname</th>
                                    <th>Lastname</th>
                                    <th>Gender</th>
                                    <th>Address</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Status</th>
                                    <th>Content</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${customers}">
                                    <tr class="align-middle text-muted text-sm">
                                        <td class="text-dark text-base" style="min-width: 250px;">
                                            <input class="me-2 mt-3 form-check-input"
                                                   type="checkbox"
                                                   id="check_${item.id}" value="${item.id}"><a
                                                class="text-reset text-decoration-none"
                                                href="#!"><img class="avatar p-1 me-2"
                                                               src="${item.user.avatar}"
                                        ><strong>${item.user.username}</strong></a>
                                        </td>
                                        <td>${item.firstName}</td>
                                        <td>${item.lastName}</td>
                                        <td>${item.gender}</td>
                                        <td>${item.address}</td>
                                        <td>${item.user.email}</td>
                                        <td>${item.phone}</td>
                                        <td class="text-base"><span
                                                class="badge badge-${item.badge}-light">${item.user.status}</span>
                                        </td>
                                        <td>${item.content}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <span class="me-2" id="categoryBulkAction">
                  <select class="form-select form-select-sm d-inline w-auto"
                          id="selectCategory"
                          name="categoryBulkAction">
                    <option>Bulk Actions</option>
                    <option>Delete</option>
                  </select>
                  <button id="btnListCustomer" class="btn btn-sm btn-outline-primary align-top">Apply</button></span>
                    </div>
                </div>
            </div>
        </form>
        <nav aria-label="Page navigation">
            <input type="hidden" value="" id="page" name="page"/>
            <input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
        </nav>
        <div style="margin-left: 415px">
            <ul class="pagination" id="pagination"></ul>
        </div>
    </div>
</div>
<script>
  $("#btnListCustomer").click(function () {
    var data = {};
    var ids = $('tbody input[type=checkbox]:checked').map(function () {
      return $(this).val();
    }).get();
    data['ids'] = ids;
    if ($('#selectCategory').val() === 'Delete') {
      deleteCustomer(data);
    }
  });

  function deleteCustomer(data) {
    $.ajax({
      url: '${APICustomer}',
      type: 'DELETE',
      contentType: 'application/json',
      data: JSON.stringify(data),
      dataType: 'json',
      success: function (result) {
        if (result) {
          window.location.href = "${loadCustomer}"
        }
      }
    })
  }
</script>
<script>
  var totalPages = ${model.totalPage};
  var currentPage = ${model.page};
  var limit = 5; // tổng số item hiển thị trên 1 page
  $(function () {
    window.pagObj = $('#pagination').twbsPagination({
      totalPages: totalPages,
      visiblePages: 5,
      startPage: currentPage,
      onPageClick: function (event, page) {
        // kiểm tra điều kiện load lại trang
        if (currentPage !== page) {
          $('#maxPageItem').val(limit);
          $('#page').val(page);
          $('#formSubmit').submit(getCustomerPage());  //Dùng jquery để gọi tới đối tượng form
        }
      }
    }).on('page', function (event, page) {
      console.info(page + ' (from event listening)');
    });
  });

  function getCustomerPage() {
    $.ajax({
      url: '${APICustomer}?page=' + $('#page').val() + '&maxPageItem=' + $('#maxPageItem').val(),
      type: 'GET',
      contentType: 'application/json',
      dataType: 'json',
      success: function (result) {
        $('#customerPage').html(result.renderHtml)
        totalPages = result.totalPage;
        currentPage = result.currentPage;
      }
    })
  }
</script>
