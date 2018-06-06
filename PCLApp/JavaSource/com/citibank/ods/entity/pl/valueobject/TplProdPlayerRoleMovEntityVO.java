package com.citibank.ods.entity.pl.valueobject;


/**
* Classe que instancia os valores correspondente a um registro da tabela : TplProdPlayerRoleMovement
* @author acacio.domingos
* @date 09/04/2007
*/


public class TplProdPlayerRoleMovEntityVO extends BaseTplProdPlayerRoleEntityVO {

private String m_opernCode;
/**
* Retorna atributo opernCode
* @author acacio.domingos
* @date 09/04/2007
*/
public String getopernCode()
{
	  return m_opernCode;
}

/**
* Atribui o valor passado como parametro a variavel m_opernCode
* @param opernCode
*/
public void setOpernCode( String opernCode_ )
{
	  m_opernCode = opernCode_;
}
}
