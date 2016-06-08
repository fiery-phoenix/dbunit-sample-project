package sample.dbunit.jpa.utils.tables;

import org.dbunit.dataset.Column;


import static org.dbunit.dataset.datatype.DataType.BIGINT;
import static org.dbunit.dataset.datatype.DataType.VARCHAR;

/**
 * @author Kseniya Panasyuk
 */
public interface Customers {

	Column ID = new Column("ID", BIGINT);
	Column FIRST_NAME = new Column("FIRST_NAME", VARCHAR);
	Column LAST_NAME = new Column("LAST_NAME", VARCHAR);

}
