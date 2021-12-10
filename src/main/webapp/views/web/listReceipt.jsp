<%@include file="/common/taglib.jsp" %>
<c:url var="APIReceipt" value="/api-receipt/"/>

<html>
<header>

</header>
<body>
<form id="formSubmit" >
    <div id="receiptsDatatable">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">code</th>
                <th scope="col">name</th>
                <th scope="col">size</th>
                <th scope="col">price</th>
                <th scope="col">quantity</th>
                <th scope="col">total</th>
            </tr>
            </thead>
            <tbody id="myTable">
            <c:forEach var="item" items="${receipts}">
                <tr>
                    <td>${item.code}</td>
                    <td>${item.product.name}</td>
                    <td>${item.product.size}</td>
                    <td>${item.product.price}$</td>
                    <td>${item.quantity}</td>
                    <td>${item.total}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <nav aria-label="Page navigation">
        <input type="hidden" value="" id="page" name="page"/>
        <input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
    </nav>
    <div style="margin-left: 415px">
        <ul class="pagination" id="pagination"></ul>
    </div>
</form>


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
          $('#formSubmit').submit(getReceiptPage());  //Dùng jquery để gọi tới đối tượng form
        }
      }
    }).on('page', function (event, page) {
      console.info(page + ' (from event listening)');
    });
  });

  $('#formSubmit').submit(function (e) {
    e.preventDefault(); // submit về 1 API
    getReceiptPage();
  })

  function getReceiptPage() {
    $.ajax({
      url: '${APIReceipt}?page=' + $('#page').val() + '&maxPageItem=' + $('#maxPageItem').val(),
      type: 'GET',
      contentType: 'application/json',
      dataType: 'json',
      success: function (result) {
        $('#receiptsDatatable').html(result.renderHtml)
        totalPages = result.totalPage;
        currentPage = result.currentPage;
      }
    })
  }
</script>

<script>
  $(document).ready(function () {
    $("#myInput").on("keyup", function () {
      var value = $(this).val().toLowerCase();
      $("#myTable tr").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    });
  });
</script>

</body>
</html>
