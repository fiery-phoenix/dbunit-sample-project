package sample.dbunit.jpa.utils;

import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.dbunit.database.IDatabaseConnection;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.util.Assert;

import javax.sql.DataSource;

/**
 * @author Kseniya Panasyuk
 */
public class DbUnitConnectionSetter extends AbstractTestExecutionListener {

	public static final String COMMON_DATABASE_CONNECTION_BEAN_NAME = "dataSource";

	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {
		prepareDatabaseConnection(testContext, new String[]{COMMON_DATABASE_CONNECTION_BEAN_NAME});
	}

	private void prepareDatabaseConnection(TestContext testContext, String[] connectionBeanNames)
			throws Exception {
		for (String connectionBeanName : connectionBeanNames) {
			Object databaseConnection = testContext.getApplicationContext().getBean(connectionBeanName);
			if (databaseConnection instanceof DataSource) {
				databaseConnection = DatabaseDataSourceConnectionFactoryBean
						.newConnection((DataSource) databaseConnection);
			}
			Assert.isInstanceOf(IDatabaseConnection.class, databaseConnection);
			Connections.put(connectionBeanName, (IDatabaseConnection) databaseConnection);
		}
	}

}
