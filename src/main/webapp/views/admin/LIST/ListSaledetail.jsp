<%@include file="/common/taglib.jsp" %>
<c:url var="APISaledetail" value="/api-saledetail/"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<body>
<div class="page-breadcrumb">
    <ul class="breadcrumb">
        <li class="breadcrumb-item"><a href="/admin-home">Home</a></li>
        <li class="breadcrumb-item active">Saledetail</li>
    </ul>
</div>
<div class="page-header d-flex justify-content-between align-items-center">
    <div>
        <h1 class="page-heading">Saledetails</h1>
        <ul class="list-inline text-sm">
            <li class="list-inline-item"><a class="text-gray-600" href="#!"><i
                    class="fas fa-upload me-2"> </i>Import</a></li>
            <li class="list-inline-item"><a class="text-gray-600" href="#!"><i
                    class="fas fa-download me-2"> </i>Export</a></li>
        </ul>
    </div>
</div>
<section class="mb-5">
    <%--    <form id="formSubmit" action="<c:url value="/admin-saledetail"/>" method="get">--%>
    <form id="formSubmit" action="<c:url value="/api-saledetail/"/>" method="get">
        <div class="card card-table">
            <div class="preload-wrapper">
                <div id="saleDetailsDatatables">
                    <table class="table table-hover table-borderless align-middle mb-0"
                           id="saledetailsDatatable">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>User_ID</th>
                            <th>Product_ID</th>
                            <th>Delivery_ID</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th>Status_delivery</th>
                            <th>Createddate</th>
                            <th>CreatedBy</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${saledetailModel}">
                            <tr class="align-middle">
                                <td>
                                    <input class="form-check-input" type="checkbox"
                                           id="checkbox_${item.id}" value="${item.id}">
                                    <label class="form-check-label"
                                           for="check${item.id}">${item.id}</label>
                                </td>
                                <td>${item.user.id}</td>
                                <td>${item.product.name}</td>
                                <td>${item.delivery.id}</td>
                                <td>${item.quantity}</td>
                                <td>${item.total}</td>
                                <td>
                                        ${item.delivery.status}
                                </td>
                                <td class="text-muted">${item.createdDate}</td>
                                <td class="text-muted">${item.createdBy}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <span class="me-2" id="categoryBulkAction">
                <label>Sort</label>
                <select id="sort" onchange="Sort(this)"
                        class="form-select form-select-sm d-inline w-auto"
                        name="categoryBulkAction">
                    <option value="" disabled selected>Choose your option</option>
                    <option>A-Z</option>
<%--                    <option>Total</option>--%>
                </select>
                    <button id="btnDelete" type="button"
                            class="btn btn-sm btn-outline-primary align-top" data-toggle="tooltip"
                            title='Xoa bai viet'>
                    <span>
                        <i class="fa fa-trash-o bigger-110 pink"></i>
                    </span>
                    Delete
                </button>
            </span>
            </div>
        </div>
        <nav aria-label="Page navigation">
            <input type="hidden" value="" id="page" name="page"/>
            <input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
        </nav>
        <div style="margin-left: 415px">
            <ul class="pagination" id="pagination"></ul>
        </div>
    </form>
</section>
<script>
  $("#btnDelete").click(function () {
    var data = {};
    var ids = $('tbody input[type=checkbox]:checked').map(function () {
      return $(this).val();
    }).get();
    data['ids'] = ids;
    deleteSaledetail(data);
  });

  function deleteSaledetail(data) {
    $.ajax({
      url: '${APISaledetail}',
      type: 'DELETE',
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: function (result) {
        $('#notification').html(`
                    <div class="alert alert-success">
                            Congratulations,Delete Product success
                    </div>`)
        // console.log(result)
        window.location.href = "/admin-saledetail";
      },
      error: function (error) {
        $('#notification').html(`
                    <div class="alert alert-danger">
                            Failed!
                    </div>`)
      }
    });
  }

  function Sort(param) {
    var message = document.getElementById('sort');
    var value = param.value;
    if (value === 'A-Z') {
      var table, rows, switching, i, x, y, shouldSwitch;
      table = document.getElementById("saledetailsDatatable");
      switching = true;
      while (switching) {
        switching = false;
        rows = table.rows;
        for (i = 1; i < (rows.length - 1); i++) {
          // Start by saying there should be no switching:
          shouldSwitch = false;
          x = rows[i].getElementsByTagName("TD")[1];
          y = rows[i + 1].getElementsByTagName("TD")[1];
          // Check if the two rows should switch place:
          if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
            // If so, mark as a switch and break the loop:
            shouldSwitch = true;
            break;
          }
        }
        if (shouldSwitch) {
          rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
          switching = true;
        }
      }
    }
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
          $('#formSubmit').submit();  //Dùng jquery để gọi tới đối tượng form
        }
      }
    }).on('page', function (event, page) {
      console.info(page + ' (from event listening)');
    });
  });

  $('#formSubmit').submit(function (e) {
    e.preventDefault(); // submit về 1 API
    getSaleDetailPage();
  })

  function getSaleDetailPage() {
    $.ajax({
      url: '${APISaledetail}?page=' + $('#page').val() + '&maxPageItem=' + $('#maxPageItem').val(),
      type: 'GET',
      contentType: 'application/json',
      dataType: 'json',
      success: function (result) {
        $('#saleDetailsDatatables').html(result.renderHtml)
        totalPages = result.totalPage;
        currentPage = result.currentPage;
      }
    })
  }
</script>
</body>
</html>
