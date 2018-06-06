package com.citibank.ods.common.factory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.citibank.ods.common.BaseObject;
import com.citibank.ods.common.exception.UnexpectedException;

/**
 * 
 * Classe respons�vel pela instacia��o de objetos gereciados pelo Application
 * Server. A instancia��o dos objetos � realizada atrav�s do processo de lookup
 * no JNDI definido no Application Server. A cria��o de contexto com o
 * Application Server � estabelecida atrav�s das configura��es definidas em
 * algum arquivo jndi.properties que esteja no CLASSPATH. Portanto, o
 * jndi.properties utilizado para estabelecer o contexto � o jndi.properties
 * existente no pr�prio Application Server onde a aplica��o for instalada.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class JNDIFactory extends BaseObject
{
	
   private static Object pool = null;

  /**
   * m_initialContext - contexto com o Application Server.
   */
  private static Context m_initialContext = null;

  /**
   * 
   * Instancia um objeto atrav�s de lookup no JNDI do Application Server.
   * 
   * @param jndiFullPath_ nome utilizado para lookup no JNDI do Application
   *          Server.
   * @return inst�ncia do objeto obtido atrav�s de lookup no JNDI do Application
   *         Server.
   * @throws UnexpectedException quando o jndiFullPath_ for null; ou quando n�o
   *           for poss�vel construir um contexto de conversa��o com o
   *           Application Server; ou quando o jndiFullPath n�o existir no JDNI
   *           do ApplicationServer ou quando ocorrer algum erro inesperado na
   *           instancia��o do objeto pelo Application Server.
   */
   public static Object createObject( String jndiFullPath_ ) throws UnexpectedException{
  
      Object lookedUpObject = null;

      if ( jndiFullPath_ == null ){
         throw new UnexpectedException( "jndiFullPath_ cannot be null" );
      }

      lookedUpObject = lookup( jndiFullPath_ );

      if ( lookedUpObject == null ){
         throw new UnexpectedException( "could not instantiate object" );
      }

      return lookedUpObject;
   }
  
   public static Object createObjectUserTrans( String jndiFullPath_ )
     throws UnexpectedException, SecurityException, IllegalStateException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException
   {

      Object lookedUpObject = null;

      if ( jndiFullPath_ == null ){
         throw new UnexpectedException( "jndiFullPath_ cannot be null" );
      }

      lookedUpObject = lookupUserTrans( jndiFullPath_ );

      if ( lookedUpObject == null ){
         throw new UnexpectedException( "could not instantiate object" );
      }

      return lookedUpObject;
   }
  
  /**
   * 
   * Faz a pesquisa (lookup) do nome informado no Application Server.
   * 
   * @param jndiFullPath_ - nome do objeto a ser pesquisado no JNDI do
   *          Application Server.
   * @return inst�ncia do objeto obtido atrav�s de lookup no JNDI do Application
   *         Server.
   * @throws UnexpectedException quando n�o for poss�vel construir um contexto
   *           de conversa��o com o Application Server; ou quando o jndiFullPath
   *           n�o existir no JDNI do ApplicationServer ou quando ocorrer algum
   *           erro inesperado na instancia��o do objeto pelo Application
   *           Server.
   */
  private static Object lookup( String jndiFullPath_ )
                                                      throws UnexpectedException
  {
    Object lookedUpObject = null;
    Context context = getInitialContext();

    try
    {
    	//lookedUpObject = context.lookup( jndiFullPath_ );
    
    	Context envirContext = (Context) context.lookup("java:comp/env");
    	pool = (DataSource) envirContext.lookup("jdbc/DSPrivateLayer");
    
    }
    catch ( NamingException e_ )
    {
      throw new UnexpectedException( "cannot lookup jndiFullPath_: ["
                                                                   + jndiFullPath_ + "].", e_ );
    }

    return pool;
  }
  
   private static Object lookupUserTrans( String jndiFullPath_ )
          throws UnexpectedException, NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException
   {
	  
   Object lookedUpObject = null;
   Context context = getInitialContext();

   try{
   
      lookedUpObject = context.lookup( jndiFullPath_ );
   }
   catch ( NamingException e_ ){
      throw new UnexpectedException( "cannot lookup jndiFullPath_: ["
                                     + jndiFullPath_ + "].", e_ );
   }

   return lookedUpObject;
   }

  private static Object getJndiTemplate() {
	// TODO Auto-generated method stub
	return null;
}

/**
   * 
   * Obt�m o contexto de convers�o com o Application Server. O contexto de
   * Conversa��o � "cached" no atributo m_initialContext. Caso ainda n�o exista
   * nenum contexto "cached" um contexto � criado e colocado no "cache"
   * (m_initialContext).
   * 
   * @return Inst�ncia do contexto de conversa��o com o Application Server.
   * @throws UnexpectedException quando n�o for poss�vel a cria��o de um
   *           contexto de conversa��o com o ApplicationServer (neste caso, n�o
   *           havia um contexto em "cache").
   */
  private static Context getInitialContext()
  {
    if ( m_initialContext == null )
    {
      createInitialContext();
    }
    return m_initialContext;
  }

  /**
   * 
   * Cria uma inst�ncia do contexto de conversa��o com o Application Server no
   * "cache" (m_initialContext) garantindo que a inst�ncia somente seja criada
   * caso ainda n�o exista um contexto em "cache".
   * 
   * @throws UnexpectedException quando n�o for poss�vel a cria��o de um
   *           contexto de conversa��o com o ApplicationServer.
   */
  private static synchronized void createInitialContext()
                                                         throws UnexpectedException
  {
    if ( m_initialContext == null )
    {
      try
      {
        m_initialContext = new InitialContext();
      }
      catch ( NamingException e_ )
      {
        throw new UnexpectedException( "cannot create initial context", e_ );
      }
    }
  }
}