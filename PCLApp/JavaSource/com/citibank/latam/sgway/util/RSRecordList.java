package com.citibank.latam.sgway.util;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.StringTokenizer;

public class RSRecordList implements RecordList
{

  public RSRecordList()
  {
    columnValues = new ArrayList();
    columnNames = new ArrayList();
    columnIndexes = new HashMap();
    recordCount = 0;
    columnCount = 0;
  }

  public RSRecordList( ResultSet resultset )
    throws SQLException
  {
    columnValues = new ArrayList();
    columnNames = new ArrayList();
    columnIndexes = new HashMap();
    recordCount = 0;
    columnCount = 0;
    populate( resultset );
  }

  public RSRecordList( ResultSet resultset, int i )
    throws SQLException
  {
    columnValues = new ArrayList();
    columnNames = new ArrayList();
    columnIndexes = new HashMap();
    recordCount = 0;
    columnCount = 0;
    populate( resultset, i );
  }

  public RSRecordList( ResultSet resultset, int i, int j )
    throws SQLException
  {
    columnValues = new ArrayList();
    columnNames = new ArrayList();
    columnIndexes = new HashMap();
    recordCount = 0;
    columnCount = 0;
    populate( resultset, j );
  }

  public void clear()
  {
    columnValues.clear();
    columnNames.clear();
    columnIndexes.clear();
    recordCount = 0;
    columnCount = 0;
  }

  public void populate( ResultSet resultset )
    throws SQLException
  {
    getColumnNames( resultset );
    fetchRecords( resultset );
  }

  public void populate( ResultSet resultset, int i )
    throws SQLException
  {
    System.out.println( "RSRecordList in populate" + resultset );
    getColumnNames( resultset );
    System.out.println( "In populate - Before Calling FetchRecords" + resultset );
    fetchRecords( resultset, i );
  }

  public void populate( ResultSet resultset, int i, int j )
    throws SQLException
  {
    getColumnNames( resultset );
    fetchRecords( resultset, i, j );
  }

  public void populate( RecordList recordlist )
  {
    int i = recordlist.getRecordCount();
    if ( i == 0 )
    {
      return;
    }
    int j = recordlist.getColumnCount();
    if ( j != columnCount )
    {
      if ( recordCount > 0 )
      {
        throw new IndexOutOfBoundsException(
          "RecordList object being appended has " + j + " columns.  Must be " +
          columnCount );
      }
      clear();
      columnCount = j;
      for ( int k = 0; k < columnCount; k++ )
      {
        columnNames.add( recordlist.getColumnName( k ) );

      }
    }
    for ( int l = 0; l < i; l++ )
    {
      for ( int i1 = 0; i1 < columnCount; i1++ )
      {
        columnValues.add( recordlist.getObject( l, i1 ) );

      }
      recordCount++;
    }

  }

  private void getColumnNames( ResultSet resultset )
    throws SQLException
  {
    System.out.println( "Inside getColumnName in RSRecordList" + resultset );
    ResultSetMetaData resultsetmetadata = resultset.getMetaData();
    System.out.println( "After ResultSetMetaData" );
    columnCount = resultsetmetadata.getColumnCount();
    for ( int i = 0; i < columnCount; i++ )
    {
      columnNames.add( resultsetmetadata.getColumnName( i + 1 ) );

    }
  }

  private void fetchRecords( ResultSet resultset, int i, int j )
    throws SQLException
  {
    if ( i > 0 )
    {
      if ( resultset.getType() == 1003 )
      {
        while ( i-- > 0 )
        {
          if ( !resultset.next() )
          {
            return;
          }
          else
          {
            resultset.absolute( i + 1 );
          }
        }
      }
    }
    fetchRecords( resultset, j );
  }

  private void fetchRecords( ResultSet resultset, int i )
    throws SQLException
  {
    if ( i == 0 )
    {
      fetchRecords( resultset );
      return;
    }
    for ( ; i > 0 && resultset.next(); i-- )
    {
      for ( int j = 0; j < columnCount; j++ )
      {
        columnValues.add( resultset.getObject( j + 1 ) );

      }
      recordCount++;
    }

  }

  private void fetchRecords( ResultSet resultset )
    throws SQLException
  {
    System.out.println( "fetch Records in RSRecordList" + resultset );
    while ( resultset.next() )
    {
      for ( int i = 0; i < columnCount; i++ )
      {
        columnValues.add( resultset.getObject( i + 1 ) );

      }
      recordCount++;
    }
  }

  public int getRecordCount()
  {
    return recordCount;
  }

  public int getColumnCount()
  {
    return columnCount;
  }

  public String getColumnName( int i )
  {
    return ( String ) columnNames.get( i );
  }

  public int getColumnIndex( String s )
  {
    Integer integer = ( Integer ) columnIndexes.get( s );
    if ( integer == null )
    {
      for ( int i = 0; i < columnCount; i++ )
      {
        if ( columnNames.get( i ).toString().compareToIgnoreCase( s ) != 0 )
        {
          continue;
        }
        integer = new Integer( i );
        columnIndexes.put( s, integer );
        break;
      }

      if ( integer == null )
      {
        throw new IndexOutOfBoundsException( "Index for column '" + s +
                                             "' is not found." );
      }
    }
    return integer.intValue();
  }

  public boolean isNull( int i, int j )
  {
    return getObject( i, j ) == null;
  }

  public boolean isNull( int i, String s )
  {
    return getObject( i, s ) == null;
  }

  public Object getObject( int i, int j )
  {
    return columnValues.get( i * columnCount + j );
  }

  public Object getObject( int i, String s )
  {
    return getObject( i, getColumnIndex( s ) );
  }

  public String getString( int i, int j )
  {
    Object obj = getObject( i, j );
    return obj != null ? obj.toString() : "";
  }

  public String getString( int i, String s )
  {
    Object obj = getObject( i, s );
    return obj != null ? obj.toString() : "";
  }

  public int getInt( int i, int j )
  {
    Object obj = getObject( i, j );
    return obj != null ? Integer.parseInt( obj.toString() ) : 0;
  }

  public int getInt( int i, String s )
  {
    Object obj = getObject( i, s );
    return obj != null ? Integer.parseInt( obj.toString() ) : 0;
  }

  public double getDouble( int i, int j )
  {
    Object obj = getObject( i, j );
    return obj != null ? Double.parseDouble( obj.toString() ) : 0.0D;
  }

  public double getDouble( int i, String s )
  {
    Object obj = getObject( i, s );
    return obj != null ? Double.parseDouble( obj.toString() ) : 0.0D;
  }

  public String getNumericString( int i, int j, NumberFormat numberformat )
  {
    Object obj = getObject( i, j );
    if ( obj == null )
    {
      return "";
    }
    else
    {
      return numberformat.format( Double.parseDouble( obj.toString() ) );
    }
  }

  public String getNumericString( int i, String s, NumberFormat numberformat )
  {
    return getNumericString( i, getColumnIndex( s ), numberformat );
  }

  public Date getDate( int i, int j )
  {
    Date date = null;
    Object obj = getObject( i, j );
    if ( obj != null && ( obj instanceof Date ) )
    {
      date = ( Date ) obj;
    }
    else
    if ( obj != null )
    {
      StringTokenizer stringtokenizer = new StringTokenizer( obj.toString(),
        "/" );
      int k = Integer.parseInt( stringtokenizer.nextToken() );
      int l = Integer.parseInt( stringtokenizer.nextToken() );
      int i1 = Integer.parseInt( stringtokenizer.nextToken() );
      int j1 = Integer.parseInt( stringtokenizer.nextToken() );
      int k1 = Integer.parseInt( stringtokenizer.nextToken() );
      int l1 = Integer.parseInt( stringtokenizer.nextToken() );
      GregorianCalendar gregoriancalendar = new GregorianCalendar( i1, k, l, j1,
        k1, l1 );
      date = gregoriancalendar.getTime();
    }
    return date;
  }

  public Date getDate( int i, String s )
  {
    Date date = null;
    Object obj = getObject( i, s );
    if ( obj != null && ( obj instanceof Date ) )
    {
      date = ( Date ) obj;
    }
    else
    if ( obj != null )
    {
      StringTokenizer stringtokenizer = new StringTokenizer( obj.toString(),
        "/" );
      int j = Integer.parseInt( stringtokenizer.nextToken() );
      int k = Integer.parseInt( stringtokenizer.nextToken() );
      int l = Integer.parseInt( stringtokenizer.nextToken() );
      int i1 = Integer.parseInt( stringtokenizer.nextToken() );
      int j1 = Integer.parseInt( stringtokenizer.nextToken() );
      int k1 = Integer.parseInt( stringtokenizer.nextToken() );
      GregorianCalendar gregoriancalendar = new GregorianCalendar( l, j, k, i1,
        j1, k1 );
      date = gregoriancalendar.getTime();
    }
    return date;
  }

  public String getDateString( int i, int j, DateFormat dateformat )
  {
    Date date = getDate( i, j );
    if ( date == null )
    {
      return "";
    }
    else
    {
      return dateformat.format( date );
    }
  }

  public String getDateString( int i, String s, DateFormat dateformat )
  {
    return getDateString( i, getColumnIndex( s ), dateformat );
  }

  public Blob getBlob( int i, int j )
  {
    return ( Blob ) getObject( i, j );
  }

  public Blob getBlob( int i, String s )
  {
    return ( Blob ) getObject( i, s );
  }

  public Clob getClob( int i, int j )
  {
    return ( Clob ) getObject( i, j );
  }

  public Clob getClob( int i, String s )
  {
    return ( Clob ) getObject( i, s );
  }

  public String getClobString( int i, int j )
  {
    Clob clob = getClob( i, j );
    if ( clob == null )
    {
      return "";
    }
    try
    {
      return clob.getSubString( 1L, ( int ) clob.length() );
    }
    catch ( SQLException sqlexception )
    {
      System.err.print( "Failed to get contents of CLOB" + sqlexception );
      return "Failed to get contents of CLOB: " + sqlexception;
    }
  }

  public String getClobString( int i, String s )
  {
    return getClobString( i, getColumnIndex( s ) );
  }

  public void setObject( int i, int j, Object obj )
  {
    if ( j < 0 || j >= columnCount )
    {
      throw new IndexOutOfBoundsException( "Invalid columnIndex: " + j +
                                           ". Must be between 0 and " +
                                           ( columnCount - 1 ) );
    }
    else
    {
      columnValues.set( i * columnCount + j, obj );
      return;
    }
  }

  public void setObject( int i, String s, Object obj )
  {
    setObject( i, getColumnIndex( s ), obj );
  }

  public int findRecord( int i, Object obj, int j )
  {
    String s = obj.toString();
    for ( int k = j; k < recordCount; k++ )
    {
      Object obj1 = getObject( k, i );
      if ( obj1 == null )
      {
        if ( obj == null )
        {
          return k;
        }
      }
      else
      if ( obj1.toString().equals( s ) )
      {
        return k;
      }
    }

    return -1;
  }

  public int findRecord( String s, Object obj, int i )
  {
    return findRecord( getColumnIndex( s ), obj, i );
  }

  public String getXml()
  {
    String s = "";
    String s1 = "";
    Object obj = null;
    s = s + "<XML><RECORD_LIST>";
    s = s + "<RECORD_COUNT>";
    s = s + recordCount;
    s = s + "</RECORD_COUNT>";
    s = s + "<COLUMN_COUNT>";
    s = s + columnCount;
    s = s + "</COLUMN_COUNT>";
    s = s + "<COLUMN_NAME>";
    for ( int i = 0; i < columnCount; i++ )
    {
      s = s + getColumnName( i );
      s = s + "|:|";
    }

    s = s + "</COLUMN_NAME>\n";
    for ( int j = 0; j < recordCount; j++ )
    {
      s = s + "<RECORD>";
      for ( int k = 0; k < columnCount; k++ )
      {
        String s2 = getColumnName( k );
        s = s + "<" + s2 + ">";
        Object obj1 = getObject( j, k );
        if ( obj1 instanceof Date )
        {
          SimpleDateFormat simpledateformat = new SimpleDateFormat(
            "MM/dd/yyyy/hh/mm/ss" );
          s = s + getDateString( j, k, simpledateformat );
        }
        else
        {
          s = s + obj1;
        }
        s = s + "</" + s2 + ">";
      }

      s = s + "</RECORD>\n";
    }

    s = s + "</RECORD_LIST></XML>\n";

    return s;
  }

  public void setRecordCount( int i )
  {
    recordCount = i;
  }

  public void setColumnCount( int i )
  {
    columnCount = i;
  }

  public void setColumnNames( String as[] )
  {
    for ( int i = 0; i < as.length; i++ )
    {
      columnNames.add( i, as[ i ] );

    }
  }

  public void setColumnValues( String as[] )
  {
    for ( int i = 0; i < as.length; i++ )
    {
      columnValues.add( i, as[ i ] );

    }
  }

  private ArrayList columnValues;
  private ArrayList columnNames;
  private HashMap columnIndexes;
  private int recordCount;
  private int columnCount;
}