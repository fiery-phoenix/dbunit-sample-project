package sample.dbunit.jpa;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.google.common.collect.Iterables;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import sample.dbunit.jpa.entities.Customer;

import javax.sql.DataSource;
import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * @author Kseniya Panasyuk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("local")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class})
public class CustomerRepositoryWithoutDatabaseSetupAnnotationTest {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private CustomerRepository repository;

	@Test
	public void testContextLoads() throws Exception {
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
		Iterable<Customer> all = repository.findAll();
		assertThat(all, is(notNullValue()));
		assertThat(Iterables.size(all), is(3));
	}

	private IDatabaseConnection getConnection() throws Exception {
		return new DatabaseConnection(dataSource.getConnection());
	}

	private IDataSet getDataSet() throws Exception {
		File file = new File("src/test/resources/sampleData.xml");
		return new FlatXmlDataSetBuilder().build(file);
	}

}
