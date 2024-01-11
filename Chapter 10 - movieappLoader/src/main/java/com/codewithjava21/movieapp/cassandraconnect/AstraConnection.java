package com.codewithjava21.movieapp.cassandraconnect;

import com.datastax.oss.driver.api.core.CqlSession;

public class AstraConnection extends CassandraConnection {

    static final String ASTRA_ZIP_FILE = System.getenv("ASTRA_DB_SECURE_BUNDLE_PATH");
    static final String ASTRA_PASSWORD = System.getenv("ASTRA_DB_APP_TOKEN");
    static final String ASTRA_KEYSPACE = System.getenv("ASTRA_DB_KEYSPACE");
    
    public AstraConnection() {
    	super("token", ASTRA_PASSWORD, ASTRA_ZIP_FILE, ASTRA_KEYSPACE);
    }
    
    public CqlSession getCqlSession() {
    	return super.getCqlSession();
    }
    
    public void finalize() {
    	super.finalize();
    }
}
