package com.citibank.ods.common.security.connector;

import com.citibank.latam.sgway.util.RecordList;

/**
 * @author leonardo.nakada
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface SecurityGatewayInterface
{
  public boolean canAccessSystem( String aLoginID, String userIP,
                                 String sessionSpec, int aSystemID )
                                                                    throws Exception;

  public RecordList getSystemModulesAndFunctions( String aLoginID,
                                                 String userIP,
                                                 String sessionSpec,
                                                 int aSystemID )
                                                                throws Exception;

  public RecordList getSystemModules( String aLoginID, String userIP,
                                     String sessionSpec, int aSystemID )
                                                                        throws Exception;

  public RecordList getSystemModuleFunctions( String aLoginID, String userIP,
                                             String sessionSpec, int aSystemID,
                                             int aModuleID ) throws Exception;

  public RecordList getUserBasicProfile( String userID, String ipAddress,
                                        String sessionSpecs ) throws Exception;;
}