package com.citibank.ods.persistence.pl.dao;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.bg.TbgSegmentEntity;
import com.citibank.ods.entity.pl.TbgOfficerEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao;
 * @version 1.0
 * @author acacio.domingos,Apr 24, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public interface TbgSegmentDAO extends BaseTbgSegmentDAO
{
  public void update( TbgSegmentEntity segmentEntity_ );

  public void delete( TbgSegmentEntity segmentEntity_ );

  public TbgOfficerEntity insert( TbgSegmentEntity segmentEntity_ );

  public DataSet list( String segNameCode_ );

  public boolean exists( TbgSegmentEntity segmentEntity_ );

  public DataSet loadDomain( );
}