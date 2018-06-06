package com.citibank.latam.sgway.util;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;

public interface RecordList extends Serializable
{

  public abstract int getRecordCount();

  public abstract int getColumnCount();

  public abstract String getColumnName( int i );

  public abstract boolean isNull( int i, int j );

  public abstract boolean isNull( int i, String s );

  public abstract Object getObject( int i, int j );

  public abstract Object getObject( int i, String s );

  public abstract String getString( int i, int j );

  public abstract String getString( int i, String s );

  public abstract int getInt( int i, int j );

  public abstract int getInt( int i, String s );

  public abstract double getDouble( int i, int j );

  public abstract double getDouble( int i, String s );

  public abstract String getNumericString( int i, int j,
                                           NumberFormat numberformat );

  public abstract String getNumericString( int i, String s,
                                           NumberFormat numberformat );

  public abstract Date getDate( int i, int j );

  public abstract Date getDate( int i, String s );

  public abstract String getDateString( int i, int j, DateFormat dateformat );

  public abstract String getDateString( int i, String s, DateFormat dateformat );

  public abstract Blob getBlob( int i, int j );

  public abstract Blob getBlob( int i, String s );

  public abstract Clob getClob( int i, int j );

  public abstract Clob getClob( int i, String s );

  public abstract String getClobString( int i, int j );

  public abstract String getClobString( int i, String s );

  public abstract int findRecord( int i, Object obj, int j );

  public abstract int findRecord( String s, Object obj, int i );

  public abstract String getXml();
}