package listener;

import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


@WebListener
public class MoimInitializer implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			// 예전 방식의 DAO에서 필요한 코드니까 살려두고..!
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			String resource = "mybatis/config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			System.out.println(sqlSessionFactory);
			sce.getServletContext().setAttribute("sqlSessionFactory", sqlSessionFactory);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
