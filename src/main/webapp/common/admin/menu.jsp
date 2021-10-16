<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="sidebar py-3" id="sidebar">
    <h6 class="sidebar-heading">Main</h6>
    <ul class="list-unstyled">
        <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="index.html">
            <svg class="svg-icon svg-icon-md me-3">
                <use xlink:href="icons/orion-svg-sprite.57a86639.svg#real-estate-1"> </use>
            </svg><span class="sidebar-link-title">Dashboard</span></a></li>
        <li class="sidebar-list-item"><a class="sidebar-link text-muted " href="#" data-bs-target="#cmsDropdown" role="button" aria-expanded="false" data-bs-toggle="collapse">
            <svg class="svg-icon svg-icon-md me-3">
                <use xlink:href="icons/orion-svg-sprite.57a86639.svg#reading-1"> </use>
            </svg><span class="sidebar-link-title">CMS </span></a>
            <ul class="sidebar-menu list-unstyled collapse " id="cmsDropdown">
                <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="cms-post.html">Posts</a></li>
                <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="cms-post-new.html">Add new post</a></li>
                <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="/admin-category">Categories</a></li>
                <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="cms-media.html">Media library</a></li>
            </ul>
        </li>
        <li class="sidebar-list-item"><a class="sidebar-link text-muted " href="#" data-bs-target="#widgetsDropdown" role="button" aria-expanded="false" data-bs-toggle="collapse">
            <svg class="svg-icon svg-icon-md me-3">
                <use xlink:href="icons/orion-svg-sprite.57a86639.svg#statistic-1"> </use>
            </svg><span class="sidebar-link-title">Widgets </span></a>
            <ul class="sidebar-menu list-unstyled collapse " id="widgetsDropdown">
                <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="widgets-stats.html">Stats</a></li>
                <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="widgets-data.html">Data</a></li>
            </ul>
        </li>
        <li class="sidebar-list-item"><a class="sidebar-link text-muted active" href="#" data-bs-target="#e-commerceDropdown" role="button" aria-expanded="true" data-bs-toggle="collapse">
            <svg class="svg-icon svg-icon-md me-3">
                <use xlink:href="icons/orion-svg-sprite.57a86639.svg#delivery-truck-1"> </use>
            </svg><span class="sidebar-link-title">E-commerce </span></a>
            <ul class="sidebar-menu list-unstyled collapse show" id="e-commerceDropdown">
                <li class="sidebar-list-item"><a class="sidebar-link active text-muted" href="/admin-product">Products</a></li>
                <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="/admin-product?action=insert">Products - New</a></li>
                <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="e-commerce-orders.html">Orders</a></li>
                <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="e-commerce-order.html">Order - Detail</a></li>
                <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="e-commerce-customers.html">Customers</a></li>
            </ul>
        </li>
        <li class="sidebar-list-item"><a class="sidebar-link text-muted " href="#" data-bs-target="#userDropdown" role="button" aria-expanded="false" data-bs-toggle="collapse">
            <svg class="svg-icon svg-icon-md me-3">
                <use xlink:href="icons/orion-svg-sprite.57a86639.svg#man-1"> </use>
            </svg><span class="sidebar-link-title">User </span></a>
            <ul class="sidebar-menu list-unstyled collapse " id="userDropdown">
                <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="login.html">Login page</a></li>
                <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="register.html">Register</a></li>
                <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="login-2.html">Login v.2 <span class="badge bg-info ms-2 text-decoration-none">New</span></a></li>
                <li class="sidebar-list-item"><a class="sidebar-link text-muted" href="register-2.html">Register v.2 <span class="badge bg-info ms-2 text-decoration-none">New</span></a></li>
            </ul>
        </li>
    </ul>
</div>
