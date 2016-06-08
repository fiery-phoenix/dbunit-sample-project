package sample.dbunit.jpa.utils.tables;

import org.dbunit.data.support.model.ConnectionAwareTable;
import org.dbunit.database.IDatabaseConnection;
import sample.dbunit.jpa.utils.Connections;

import static sample.dbunit.jpa.utils.DbUnitConnectionSetter.COMMON_DATABASE_CONNECTION_BEAN_NAME;

/**
 * @author Kseniya Panasyuk
 */
public enum SampleTables implements ConnectionAwareTable {

	CUSTOMERS("CUSTOMERS");

	private final String name;

	SampleTables(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IDatabaseConnection getConnection() {
		return Connections.getConnection(COMMON_DATABASE_CONNECTION_BEAN_NAME);
	}
}
