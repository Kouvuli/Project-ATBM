package Utils;



import Entities.*;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;


public class HibernateUtils {
    private final static SessionFactory FACTORY;
    static {
        Configuration conf=new Configuration();
        Properties props=new Properties();
        props.put(Environment.DIALECT,"org.hibernate.dialect.Oracle8iDialect");
        props.put(Environment.DRIVER,"oracle.jdbc.OracleDriver");
        props.put(Environment.URL,"jdbc:oracle:thin:@localhost:1521/pdb");
        props.put(Environment.PASS,"19120644@Tam");
        props.put(Environment.USER,"sys");
        props.put(Environment.SHOW_SQL,"true");

        conf.setProperties(props);


        conf.addAnnotatedClass(BenhNhan.class);
        conf.addAnnotatedClass(CSYT.class);
        conf.addAnnotatedClass(HSBA.class);
        conf.addAnnotatedClass(HSBA_DV.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(ThongBao.class);


        ServiceRegistry registry=new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY=conf.buildSessionFactory(registry);
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
}
