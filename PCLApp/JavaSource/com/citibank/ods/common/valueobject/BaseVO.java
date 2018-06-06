package com.citibank.ods.common.valueobject;

import java.io.Serializable;

import com.citibank.ods.common.BaseObject;

/**
 * 
 * Classe base para as classes do tipo VO. As classes deste tipo devem ser
 * serializ�veis poir tem o objeto de somente trafegar informa��o. Portanto,
 * poder�o ser agregadas ao FncVO que ser� disponibilizado em session.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class BaseVO extends BaseObject implements Serializable
{
}