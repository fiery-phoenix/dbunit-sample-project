package sample.dbunit.jpa;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.google.common.collect.Iterables;
import org.junit.Ignore;
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
import sample.dbunit.jpa.utils.DbUnitConnectionSetter;
import sample.dbunit.jpa.entities.Customer;
import sample.dbunit.jpa.utils.tables.SampleTables;

import static org.dbunit.data.support.DbUnitDataUtils.cleanInsert;
import static org.dbunit.data.support.DbUnitDataUtils.table;
import static org.dbunit.data.support.DbUnitDataUtils.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static sample.dbunit.jpa.utils.tables.Customers.FIRST_NAME;
import static sample.dbunit.jpa.utils.tables.Customers.ID;
import static sample.dbunit.jpa.utils.tables.Customers.LAST_NAME;

/**
 * @author Kseniya Panasyuk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("local")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class,
		DbUnitConnectionSetter.class})
public class CustomerRepositoryWithDataSupportTest {

	@Autowired
	private CustomerRepository repository;

	@Test
	@Ignore
	public void testContextLoads() throws Exception {
		cleanInsert(table(SampleTables.CUSTOMERS).withRow(with(ID, 1), with(FIRST_NAME, "Fiery"), with(LAST_NAME, "Phoenix")));
		Iterable<Customer> all = repository.findAll();
		assertThat(all, is(notNullValue()));
		assertThat(Iterables.size(all), is(1));
	}

}
