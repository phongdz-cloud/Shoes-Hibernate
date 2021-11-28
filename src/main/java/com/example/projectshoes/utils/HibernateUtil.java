package com.example.projectshoes.utils;

import com.example.projectshoes.model.AbstractModel;
import com.example.projectshoes.model.CategoryModel;
import com.example.projectshoes.model.CustomerModel;
import com.example.projectshoes.model.DeliveryModel;
import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.model.RoleModel;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.model.StockModel;
import com.example.projectshoes.model.UserModel;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

  private static StandardServiceRegistry registry;
  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration();
//        // Hibernate setting quivalent to hibernate.cfg.xml's properties
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/test_shoes?useSSL=false");
//        settings.put(Environment.USER, "root");
//        settings.put(Environment.PASS, "123456");

        settings.put(Environment.URL, System.getenv("URL"));
        settings.put(Environment.USER, System.getenv("USER"));
        settings.put(Environment.PASS, System.getenv("PASS"));
//
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.POOL_SIZE,"100");
        configuration.setProperties(settings);
        configuration.addAnnotatedClass(AbstractModel.class);
        configuration.addAnnotatedClass(CategoryModel.class);
        configuration.addAnnotatedClass(CustomerModel.class);
        configuration.addAnnotatedClass(DeliveryModel.class);
        configuration.addAnnotatedClass(ProductModel.class);
        configuration.addAnnotatedClass(RoleModel.class);
        configuration.addAnnotatedClass(SaledetailModel.class);
        configuration.addAnnotatedClass(StockModel.class);
        configuration.addAnnotatedClass(UserModel.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

      } catch (Exception e) {
        e.printStackTrace();
        if (registry != null) {
          StandardServiceRegistryBuilder.destroy(registry);
        }
      }
    }
    return sessionFactory;
  }

  public static void shutdown() {
    if (registry != null) {
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }


}
