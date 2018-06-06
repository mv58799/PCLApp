//
//�2002-2007 Accenture. All rights reserved. 
//
/**
* Classe abstrata que declara o m�todo que valida o acesso de um usu�rio para uma determinada fun��o do sistema.
* 
* @see com.citibank.ods.common.security;
* @version 1.0
* @author marcelo.s.oliveira,June 1 , 2007
* 
*/

package com.citibank.ods.common.security;

import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.user.User;

public abstract class Authorization
{

    public abstract boolean hasAccess(User user, String s)
        throws UnexpectedException;
}
