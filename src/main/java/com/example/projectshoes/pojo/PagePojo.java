package com.example.projectshoes.pojo;

public class PagePojo {
  private String renderHtml;
  private Integer currentPage;
  private Integer totalPage;

  public PagePojo(String renderHtml, Integer currentPage, Integer totalPage) {
    this.renderHtml = renderHtml;
    this.currentPage = currentPage;
    this.totalPage = totalPage;
  }

  public String getRenderHtml() {
    return renderHtml;
  }

  public void setRenderHtml(String renderHtml) {
    this.renderHtml = renderHtml;
  }

  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public Integer getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }
}
