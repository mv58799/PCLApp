/*
 * Created on Mar 14, 2007
 *
 */
package com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author leonardo.nakada
 *  
 */
public class ProdRiskCatPrvtHistoryListFncVO extends
    BaseProdRiskCatPrvtListFncVO
{
  /**
   * Constante do nome do elemento Data
   */
  public static final String C_PROD_RISK_CAT_REF_DATE_DESCRIPTION = "Data de Referência";

  private Date m_prodRiskCatRefDateSrc;

  /**
   * Código da categorida de risco no registro de historico
   */
  private BigInteger m_prodRiskCatCodeHistSrc;

  /**
   * @return Returns the prodRiskCatRefDateSrc.
   */
  public Date getProdRiskCatRefDateSrc()
  {
    return m_prodRiskCatRefDateSrc;
  }

  /**
   * @param prodRiskCatRefDateSrc_ The prodRiskCatRefDateSrc to set.
   */
  public void setProdRiskCatRefDateSrc( Date prodRiskCatRefDateSrc_ )
  {
    m_prodRiskCatRefDateSrc = prodRiskCatRefDateSrc_;
  }

  public BigInteger getProdRiskCatCodeHistSrc()
  {
    return m_prodRiskCatCodeHistSrc;
  }

  public void setProdRiskCatCodeHistSrc( BigInteger prodRiskCatCodeHistSrc_ )
  {
    m_prodRiskCatCodeHistSrc = prodRiskCatCodeHistSrc_;
  }
}