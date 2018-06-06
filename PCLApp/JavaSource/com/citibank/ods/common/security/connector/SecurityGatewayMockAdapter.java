package com.citibank.ods.common.security.connector;

import com.citibank.latam.sgway.service.LegacySgConnector;
import com.citibank.latam.sgway.util.RecordList;

/**
 * @author leonardo.nakada
 * 
 * Adaptador do Legacy Connection do Objeto MOCK SECURITY GATEWAY
 */
public class SecurityGatewayMockAdapter implements SecurityGatewayInterface
{
  LegacySgConnector lc = null;

  public SecurityGatewayMockAdapter()
  {
    lc = new LegacySgConnector();
  }

  /**
   * Verifica se o usuário pode acessar o sistema
   */
  public boolean canAccessSystem( String loginID_, String userIP_,
                                 String sessionSpec_, int systemID_ )
                                                                     throws Exception
  {
    return lc.canAccessSystem( loginID_, userIP_, sessionSpec_, systemID_ );
  }

  /**
   * Carrega os módulos e as funções
   */
  public RecordList getSystemModulesAndFunctions( String loginID_,
                                                 String userIP_,
                                                 String sessionSpec_,
                                                 int systemID_ )
                                                                throws Exception
  {
    return lc.getSystemModulesAndFunctions( loginID_, userIP_, sessionSpec_,
                                            systemID_ );
  }

  /**
   * Carrega os módulos
   */
  public RecordList getSystemModules( String loginID_, String userIP_,
                                     String sessionSpec_, int systemID_ )
                                                                         throws Exception
  {
    return lc.getSystemModules( loginID_, userIP_, sessionSpec_, systemID_ );
  }
  
  /**
   * Carrega as funções
   */
  public RecordList getSystemModuleFunctions( String loginID_,
                                                 String userIP_,
                                                 String sessionSpec_,
                                                 int systemID_, int moduleID_ )
                                                                throws Exception
  {
    return lc.getSystemModuleFunctions( loginID_, userIP_, sessionSpec_,
                                            systemID_, moduleID_ );
  }

  /*
   * Carrega Nome e Sobrenome do usuário.
   */
  public RecordList getUserBasicProfile( String loginID_, String userIP_,
                                        String sessionSpec_ ) throws Exception
  {
    return lc.getUserBasicProfile( loginID_, userIP_, sessionSpec_ );
  }

}