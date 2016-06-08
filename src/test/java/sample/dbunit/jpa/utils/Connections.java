package sample.dbunit.jpa.utils;

import org.dbunit.database.IDatabaseConnection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Kseniya Panasyuk
 */
public class Connections {

	private static final Map<String, IDatabaseConnection> DBUNIT_CONNECTIONS = new ConcurrentHashMap<>();

	private Connections() {
	}

	public static IDatabaseConnection getConnection(String name) {
		return DBUNIT_CONNECTIONS.get(name);
	}

	public static void put(String name, IDatabaseConnection connection) {
		DBUNIT_CONNECTIONS.putIfAbsent(name, connection);
	}
}
