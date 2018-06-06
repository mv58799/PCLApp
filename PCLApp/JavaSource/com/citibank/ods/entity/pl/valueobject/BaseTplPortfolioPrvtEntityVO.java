package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;
/**
* Classe que instancia os valores correspondente a um registro da tabela : BaseTplPortfolioPrvt
* @author l.braga
* @date 28/03/2007
*/


public class BaseTplPortfolioPrvtEntityVO extends BaseEntityVO {

private BigInteger m_portfBrchCode;
private String m_portfCode;
private BigInteger m_portfCostBusGrpCode;
private BigInteger m_portfCostDivCode;
private BigInteger m_portfCostPrftyCtrCode;
private BigInteger m_portfCostRegionCode;
private BigInteger m_portfCostRespOffcrCode;
private String m_portfCservSuplCode;
private String m_portfNameText;
private BigInteger m_portfNetwkSubGrpCode;
private BigInteger m_portfNetwkSubNetwkGrpCode;
private BigInteger m_portfOffcrNbr;
private String m_portfOpernType;
private String m_portfRegionCode;
private String m_portfSegCode;
private BigInteger m_portfSegSubCode;
private Date m_portfStartDate;
private String m_portfStatCode;
private String m_portfUnitCode;
private String m_recStatCode;
/**
* Retorna atributo portfBrchCode
* @author l.braga
* @date 28/03/2007
*/
public BigInteger getPortfBrchCode()
{
	  return m_portfBrchCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfBrchCode
* @param portfBrchCode
*/
public void setPortfBrchCode( BigInteger portfBrchCode_ )
{
	  m_portfBrchCode = portfBrchCode_;
}
/**
* Retorna atributo portfCode
* @author l.braga
* @date 28/03/2007
*/
public String getPortfCode()
{
	  return m_portfCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfCode
* @param portfCode
*/
public void setPortfCode( String portfCode_ )
{
	  m_portfCode = portfCode_;
}
/**
* Retorna atributo portfCostBusGrpCode
* @author l.braga
* @date 28/03/2007
*/
public BigInteger getPortfCostBusGrpCode()
{
	  return m_portfCostBusGrpCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfCostBusGrpCode
* @param portfCostBusGrpCode
*/
public void setPortfCostBusGrpCode( BigInteger portfCostBusGrpCode_ )
{
	  m_portfCostBusGrpCode = portfCostBusGrpCode_;
}
/**
* Retorna atributo portfCostDivCode
* @author l.braga
* @date 28/03/2007
*/
public BigInteger getPortfCostDivCode()
{
	  return m_portfCostDivCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfCostDivCode
* @param portfCostDivCode
*/
public void setPortfCostDivCode( BigInteger portfCostDivCode_ )
{
	  m_portfCostDivCode = portfCostDivCode_;
}
/**
* Retorna atributo portfCostPrftyCtrCode
* @author l.braga
* @date 28/03/2007
*/
public BigInteger getPortfCostPrftyCtrCode()
{
	  return m_portfCostPrftyCtrCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfCostPrftyCtrCode
* @param portfCostPrftyCtrCode
*/
public void setPortfCostPrftyCtrCode( BigInteger portfCostPrftyCtrCode_ )
{
	  m_portfCostPrftyCtrCode = portfCostPrftyCtrCode_;
}
/**
* Retorna atributo portfCostRegionCode
* @author l.braga
* @date 28/03/2007
*/
public BigInteger getPortfCostRegionCode()
{
	  return m_portfCostRegionCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfCostRegionCode
* @param portfCostRegionCode
*/
public void setPortfCostRegionCode( BigInteger portfCostRegionCode_ )
{
	  m_portfCostRegionCode = portfCostRegionCode_;
}
/**
* Retorna atributo portfCostRespOffcrCode
* @author l.braga
* @date 28/03/2007
*/
public BigInteger getPortfCostRespOffcrCode()
{
	  return m_portfCostRespOffcrCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfCostRespOffcrCode
* @param portfCostRespOffcrCode
*/
public void setPortfCostRespOffcrCode( BigInteger portfCostRespOffcrCode_ )
{
	  m_portfCostRespOffcrCode = portfCostRespOffcrCode_;
}
/**
* Retorna atributo portfCservSuplCode
* @author l.braga
* @date 28/03/2007
*/
public String getPortfCservSuplCode()
{
	  return m_portfCservSuplCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfCservSuplCode
* @param portfCservSuplCode
*/
public void setPortfCservSuplCode( String portfCservSuplCode_ )
{
	  m_portfCservSuplCode = portfCservSuplCode_;
}
/**
* Retorna atributo portfNameText
* @author l.braga
* @date 28/03/2007
*/
public String getPortfNameText()
{
	  return m_portfNameText;
}

/**
* Atribui o valor passado como parametro a variavel m_portfNameText
* @param portfNameText
*/
public void setPortfNameText( String portfNameText_ )
{
	  m_portfNameText = portfNameText_;
}
/**
* Retorna atributo portfNetwkSubGrpCode
* @author l.braga
* @date 28/03/2007
*/
public BigInteger getPortfNetwkSubGrpCode()
{
	  return m_portfNetwkSubGrpCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfNetwkSubGrpCode
* @param portfNetwkSubGrpCode
*/
public void setPortfNetwkSubGrpCode( BigInteger portfNetwkSubGrpCode_ )
{
	  m_portfNetwkSubGrpCode = portfNetwkSubGrpCode_;
}
/**
* Retorna atributo portfNetwkSubNetwkGrpCode
* @author l.braga
* @date 28/03/2007
*/
public BigInteger getPortfNetwkSubNetwkGrpCode()
{
	  return m_portfNetwkSubNetwkGrpCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfNetwkSubNetwkGrpCode
* @param portfNetwkSubNetwkGrpCode
*/
public void setPortfNetwkSubNetwkGrpCode( BigInteger portfNetwkSubNetwkGrpCode_ )
{
	  m_portfNetwkSubNetwkGrpCode = portfNetwkSubNetwkGrpCode_;
}
/**
* Retorna atributo portfOffcrNbr
* @author l.braga
* @date 28/03/2007
*/
public BigInteger getPortfOffcrNbr()
{
	  return m_portfOffcrNbr;
}

/**
* Atribui o valor passado como parametro a variavel m_portfOffcrNbr
* @param portfOffcrNbr
*/
public void setPortfOffcrNbr( BigInteger portfOffcrNbr_ )
{
	  m_portfOffcrNbr = portfOffcrNbr_;
}
/**
* Retorna atributo portfOpernType
* @author l.braga
* @date 28/03/2007
*/
public String getPortfOpernType()
{
	  return m_portfOpernType;
}

/**
* Atribui o valor passado como parametro a variavel m_portfOpernType
* @param portfOpernType
*/
public void setPortfOpernType( String portfOpernType_ )
{
	  m_portfOpernType = portfOpernType_;
}
/**
* Retorna atributo portfRegionCode
* @author l.braga
* @date 28/03/2007
*/
public String getPortfRegionCode()
{
	  return m_portfRegionCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfRegionCode
* @param portfRegionCode
*/
public void setPortfRegionCode( String portfRegionCode_ )
{
	  m_portfRegionCode = portfRegionCode_;
}
/**
* Retorna atributo portfSegCode
* @author l.braga
* @date 28/03/2007
*/
public String getPortfSegCode()
{
	  return m_portfSegCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfSegCode
* @param portfSegCode
*/
public void setPortfSegCode( String portfSegCode_ )
{
	  m_portfSegCode = portfSegCode_;
}
/**
* Retorna atributo portfSegSubCode
* @author l.braga
* @date 28/03/2007
*/
public BigInteger getPortfSegSubCode()
{
	  return m_portfSegSubCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfSegSubCode
* @param portfSegSubCode
*/
public void setPortfSegSubCode( BigInteger portfSegSubCode_ )
{
	  m_portfSegSubCode = portfSegSubCode_;
}
/**
* Retorna atributo portfStartDate
* @author l.braga
* @date 28/03/2007
*/
public Date getPortfStartDate()
{
	  return m_portfStartDate;
}

/**
* Atribui o valor passado como parametro a variavel m_portfStartDate
* @param portfStartDate
*/
public void setPortfStartDate( Date portfStartDate_ )
{
	  m_portfStartDate = portfStartDate_;
}
/**
* Retorna atributo portfStatCode
* @author l.braga
* @date 28/03/2007
*/
public String getPortfStatCode()
{
	  return m_portfStatCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfStatCode
* @param portfStatCode
*/
public void setPortfStatCode( String portfStatCode_ )
{
	  m_portfStatCode = portfStatCode_;
}
/**
* Retorna atributo portfUnitCode
* @author l.braga
* @date 28/03/2007
*/
public String getPortfUnitCode()
{
	  return m_portfUnitCode;
}

/**
* Atribui o valor passado como parametro a variavel m_portfUnitCode
* @param portfUnitCode
*/
public void setPortfUnitCode( String portfUnitCode_ )
{
	  m_portfUnitCode = portfUnitCode_;
}
/**
* Retorna atributo recStatCode
* @author l.braga
* @date 28/03/2007
*/
public String getrecStatCode()
{
	  return m_recStatCode;
}

/**
* Atribui o valor passado como parametro a variavel m_recStatCode
* @param recStatCode
*/
public void setrecStatCode( String recStatCode_ )
{
	  m_recStatCode = recStatCode_;
}
}