package com.citibank.ods.common.security.connector;

/**
 * @author leonardo.nakada
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SecurityGatewayFactory
{
  public static synchronized SecurityGatewayInterface getSecurityGatewayLegacyConnector()
  {
    return new SecurityGatewayMockAdapter();
  }
}
