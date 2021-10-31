package com.example.projectshoes;

import com.example.projectshoes.utils.HibernateUtil;
import org.hibernate.Session;

public class TestHibernate {

  public static void main(String[] args) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      System.out.println(session);
      System.out.println(session.isConnected());
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

  }
}
