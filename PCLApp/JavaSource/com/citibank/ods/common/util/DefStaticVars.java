package com.citibank.ods.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * @author Sandra Nishimura
 * 
 */
public class DefStaticVars {

	private static DefStaticVars def = null;

	public String getCurrentEnvironment;

	public String logProperties;

	public int logRefresh;

	public String dbDataSource;

	public String dbJDBCDriver;
	
	public String dbUserTransaction;

	// Security Gateway variable
	// public String sgtPropertiesLocation;

	// CitiDBPassword variables
	public String getOraclePassPasswordFile;

	public String getOraclePassKeyFile;

	public String getOraclePassUserID;

	public String getOraclePassPwd;

	public String getOraclePassServerName;

	// public String extrasProperties;
	public String getVersion;

	public Hashtable env = new Hashtable();

	/**
	 * Method loadVars.
	 */
	private void loadVars() {
		System.out.println("|DefStaticVars| loadVars");

		// websphere 4
		//env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
		// env.put(Context.PROVIDER_URL, "iiop://localhost:900");

		Context ctx;
		InputStream f = null;
		Properties p = new Properties();

		try {
			// String currentEnvironment =
			// com.citibank.sad.configurationmanager.EnvironmentUtil.getCurrentEnvironment();
			getCurrentEnvironment = "DEV";

			ctx = new InitialContext(env);
			// String plConfiguration = ( String ) ctx.lookup(
			// "java:comp/env/pl."
			// + currentEnvironment
			// + ".configuration.location" );
			String plConfiguration = (String) ctx
					.lookup("java:comp/env/pl." + getCurrentEnvironment + ".configuration.location");

			f = this.getClass().getClassLoader().getResourceAsStream(plConfiguration);
			p.load(f);

			logProperties = p.getProperty("pl.log.properties.location").trim();
			logRefresh = Integer.parseInt(p.getProperty("pl.log.refresh").trim());

			dbDataSource = p.getProperty("pl.db.datasource").trim();
			dbJDBCDriver = p.getProperty("pl.db.driver").trim();
			dbUserTransaction = p.getProperty("pl.db.usertransaction").trim();

			// Carregar valores do arquivo
			// sgtPropertiesLocation =
			// p.getProperty("pl.sgt.properties.location").trim();

			getOraclePassPasswordFile = p.getProperty("pl.getOracle.passFile").trim();
			getOraclePassKeyFile = p.getProperty("pl.getOracle.keyFile").trim();
			getOraclePassUserID = p.getProperty("pl.getOracle.userID").trim();
			getOraclePassPwd = p.getProperty("pl.getOracle.pwd");
			getOraclePassServerName = p.getProperty("pl.getOracle.serverName").trim();
			getVersion = p.getProperty("pl.version").trim();

			// extrasProperties =
			// p.getProperty("pl.extras.properties.location").trim();
		} catch (FileNotFoundException fne) {
			System.err.println("|DefStaticVars| PL File properties not found");
		} catch (IOException ioe) {
			System.err.println("|DefStaticVars| Error loading PL File properties");
		} catch (Throwable t) {
			System.err.println("Error loading environment entries");
		}

		System.out.println("logProperties: " + logProperties);
		System.out.println("logRefresh: " + logRefresh);
		System.out.println("dbDataSource: " + dbDataSource);
		System.out.println("dbJDBCDriver: " + dbJDBCDriver);
		// System.out.println("sgtPropertiesLocation: " +
		// sgtPropertiesLocation);
		System.out.println("getOraclePassPasswordFile: " + getOraclePassPasswordFile);
		System.out.println("getOraclePassKeyFile: " + getOraclePassKeyFile);
		System.out.println("getOraclePassUserID: " + getOraclePassUserID);
		System.out.println("getOraclePassServerName: " + getOraclePassServerName);
		// System.out.println("extrasProperties: " + extrasProperties);
	}

	/**
	 * Method getInstance.
	 * 
	 * @return DefStaticVars
	 */
	public static DefStaticVars getInstance() {
		if (def == null) {
			System.out.println("|DefStaticVars| init");
			def = new DefStaticVars();
			def.loadVars();
		}
		return def;
	}

}