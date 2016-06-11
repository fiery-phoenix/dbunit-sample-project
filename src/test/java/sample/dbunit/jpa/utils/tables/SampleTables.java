package sample.dbunit.jpa.utils.tables;

import org.dbunit.data.support.model.ConnectionAwareTable;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.Column;
import sample.dbunit.jpa.utils.Connections;

import java.util.Arrays;

import static sample.dbunit.jpa.utils.DbUnitConnectionSetter.COMMON_DATABASE_CONNECTION_BEAN_NAME;

/**
 * @author Kseniya Panasyuk
 */
public enum SampleTables implements ConnectionAwareTable {

	CUSTOMERS("CUSTOMERS", Customers.getColumns());

	private final String name;

	private final Column[] columns;

	SampleTables(String name, Column[] columns) {
		this.name = name;
		this.columns = columns;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Column[] getColumns() {
		return Arrays.copyOf(columns, columns.length);
	}

	@Override
	public IDatabaseConnection getConnection() {
		return Connections.getConnection(COMMON_DATABASE_CONNECTION_BEAN_NAME);
	}
}
