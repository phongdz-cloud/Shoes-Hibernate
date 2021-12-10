package com.example.projectshoes.utils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class PathUtil extends HttpServlet {

  public static String[] pathInf(HttpServletRequest request) {
    String path = request.getPathInfo();
    return path.split("/");
  }
}
