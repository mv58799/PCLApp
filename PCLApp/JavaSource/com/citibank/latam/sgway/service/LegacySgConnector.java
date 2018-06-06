package com.citibank.latam.sgway.service;

import java.io.InputStream;
import java.util.List;
import java.util.StringTokenizer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.citibank.latam.common.bind.RECORDTYPE;
import com.citibank.latam.common.bind.XML;
import com.citibank.latam.sgway.util.RSRecordList;
import com.citibank.latam.sgway.util.RecordList;

public class LegacySgConnector
{

  public LegacySgConnector()
  {
  }

  public boolean authenticateCredentials( String aLoginID, String aPassword,
                                         String ipAddress ) throws Exception
  {
    return "senha".equals( aPassword );
  }

  /**
   * @param loginID_
   * @param userIP_
   * @param sessionSpec_
   * @return
   */
  public RecordList getUserBasicProfile( String loginID_, String userIP_,
                                        String sessionSpec_ )
  {
    RSRecordList recordList = new RSRecordList();
    String[] columnNames = new String[ 2 ];
    String[] columnValues = new String[ 2 ];

    columnNames[ 0 ] = "FIRST_NAME";
    columnNames[ 1 ] = "LAST_NAME";
    columnValues[ 0 ] = "ODS";
    columnValues[ 1 ] = "TESTE";

    recordList.setRecordCount( 1 );
    recordList.setColumnCount( 2 );
    recordList.setColumnNames( columnNames );
    recordList.setColumnValues( columnValues );

    return recordList;
  }

  public RecordList getSystemModulesAndFunctions( String aLoginID,
                                                 String userIP,
                                                 String sessionSpec,
                                                 int aSystemID )
                                                                throws Exception
  {
    RecordList rs = null;

    String fileName = "/access.xml";
    InputStream xmlInput = getClass().getResourceAsStream( fileName );
    rs = ( RecordList ) xmltoRecordList( xmlInput, ACCESS );
    return rs;
  }

  public RecordList getSystemModules( String aLoginID, String userIP,
                                     String sessionSpec, int aSystemID )
                                                                        throws Exception
  {
    //    return ( new LegacyControllerMock() ).getSystemModules( aLoginID,
    //     aSystemID );
    //        RecordList rs = null;
    //    
    //        String fileName = "/access.xml";
    //        InputStream xmlInput = getClass().getResourceAsStream( fileName );
    //        rs = ( RecordList ) xmltoRecordList( xmlInput, MODULES );
    //        return rs;

    RSRecordList recordList = new RSRecordList();

    String[] columnNames = { "GROUP_ID", "GROUP_NAME" };
    String[] columnValues = { "1", "admin-" };

    recordList.setColumnCount( 2 );
    recordList.setColumnNames( columnNames );
    recordList.setColumnValues( columnValues );
    recordList.setRecordCount( 1 );

    return recordList;

  }

  public RecordList getSystemModuleFunctions( String aLoginID, String userIP,
                                             String sessionSpec, int aSystemID,
                                             int aModuleID ) throws Exception
  {
    //return ( new LegacyControllerMock() ).getSystemModuleFunctions( aLoginID,
    // aSystemID, aModuleID );
    //    RecordList rs = null;
    //
    //    String fileName = "/access.xml";
    //    InputStream xmlInput = getClass().getResourceAsStream( fileName );
    //    rs = ( RecordList ) xmltoRecordList( xmlInput, MODULES_FUNCTIONS,
    // aModuleID );
    //    return rs;

    RSRecordList recordList = new RSRecordList();

    String[] columnNames = { "FUNCTION_NAME", "GROUP_NAME" };
    String[] columnValues = { "Pro.LoaProL.L.SH.Cle", "admin-" };

    recordList.setColumnCount( 2 );
    recordList.setColumnNames( columnNames );
    recordList.setColumnValues( columnValues );
    recordList.setRecordCount( 1 );

    return recordList;
  }

  public RecordList getUserCNPJCPF( String aLoginID, String userIP,
                                   String sessionSpec ) throws Exception
  {
    RecordList rs = null;
    String fileName = "/customer.xml";
    InputStream xmlInput = getClass().getResourceAsStream( fileName );
    rs = ( RecordList ) xmltoRecordList( xmlInput, CUSTOMER );
    return rs;
  }

  public boolean canAccessSystem( String aLoginID, String userIP,
                                 String sessionSpec, int aSystemID )
                                                                    throws Exception
  {
    if ( aLoginID.equals( "cannotaccesssystem" ) )
    {
      return false;
    }
    else
    {
      return true;
    }
  }

  private RecordList xmltoRecordList( InputStream xmlInput, String xmlType )
                                                                            throws Exception
  {
    return xmltoRecordList( xmlInput, xmlType, 0 );
  }

  private RecordList xmltoRecordList( InputStream xmlInput, String xmlType,
                                     int moduleID ) throws Exception
  {
    JAXBContext jc = JAXBContext.newInstance( "com.citibank.latam.common.bind" );
    Unmarshaller u = jc.createUnmarshaller();
    XML xml = ( XML ) u.unmarshal( xmlInput );

    RSRecordList recordList = new RSRecordList();
    int recordCount = Integer.parseInt( xml.getRECORDLIST().getRECORDCOUNT() );
    int columnCount = Integer.parseInt( xml.getRECORDLIST().getCOLUMNCOUNT() );

    String[] columnNames = new String[ columnCount ];
    String[] columnValues = new String[ recordCount * columnCount ];

    if ( ( recordCount > 0 ) && ( columnCount > 0 ) )
    {
      String value = xml.getRECORDLIST().getCOLUMNNAME();
      StringTokenizer st = new StringTokenizer( value, "|:|" );

      for ( int i = 0; i < columnCount; i++ )
      {
        columnNames[ i ] = st.nextToken();
      }
      if ( ACCESS.equals( xmlType ) )
      {
        for ( int i = 0; i < recordCount; i++ )
        {
          RECORDTYPE record = ( RECORDTYPE ) ( ( List ) xml.getRECORDLIST().getRECORD() ).get( i );
          columnValues[ ( i * columnCount ) ] = record.getGROUPNAME();
          columnValues[ ( i * columnCount ) + 1 ] = record.getFUNCTIONNAME();
        }
      }
      else if ( MODULES.equals( xmlType ) )
      {
        for ( int i = 0; i < recordCount; i++ )
        {
          RECORDTYPE record = ( RECORDTYPE ) ( ( List ) xml.getRECORDLIST().getRECORD() ).get( i );
          columnValues[ ( i * columnCount ) ] = record.getMODULEID();
          columnValues[ ( i * columnCount ) + 1 ] = record.getMODULENAME();
        }
      }
      else if ( MODULES_FUNCTIONS.equals( xmlType ) )
      {
        int pos = 0;
        for ( int i = 0; i < recordCount; i++ )
        {
          RECORDTYPE record = ( RECORDTYPE ) ( ( List ) xml.getRECORDLIST().getRECORD() ).get( i );
          if ( moduleID == Integer.parseInt( record.getPARENTFUNCTIONID() ) )
          {
            columnValues[ ( pos * columnCount ) ] = record.getFUNCTIONID();
            columnValues[ ( pos * columnCount ) + 1 ] = record.getFUNCTIONNAME();
            columnValues[ ( pos * columnCount ) + 2 ] = record.getPARENTFUNCTIONID();
            pos++;
          }
        }
        recordCount = pos;
      }
      else if ( CUSTOMER.equals( xmlType ) )
      {
        for ( int i = 0; i < recordCount; i++ )
        {
          RECORDTYPE record = ( RECORDTYPE ) ( ( List ) xml.getRECORDLIST().getRECORD() ).get( i );
          if ( record.getCNPJNUMBER() != null )
          {
            columnValues[ ( i * columnCount ) ] = record.getCNPJNUMBER();
            columnValues[ ( i * columnCount ) + 1 ] = "";
          }
          else
          {
            columnValues[ ( i * columnCount ) ] = "";
            columnValues[ ( i * columnCount ) + 1 ] = record.getCPFNUMBER();
          }
          columnValues[ ( i * columnCount ) + 2 ] = record.getUSERID();
        }
      }
    }
    recordList.setRecordCount( recordCount );
    recordList.setColumnCount( columnCount );
    recordList.setColumnNames( columnNames );
    recordList.setColumnValues( columnValues );

    return recordList;
  }

  public static void main( String[] args ) throws Exception
  {

    LegacySgConnector lc = new LegacySgConnector();

    RecordList list = lc.getSystemModulesAndFunctions( "", "", "", 0 );

    //System.out.println( list.getXml() );

    list = lc.getUserCNPJCPF( "", "", "" );

    System.out.println( list.getXml() );

  }

  public static void main2( String args[] )
  {
    try
    {
      RecordList rs = null;
      LegacySgConnector lsgc = new LegacySgConnector();
      String aUserID = args[ 0 ];
      String aPassword = args[ 1 ];
      int aSystemID = Integer.parseInt( args[ 2 ] );
      int aModuleID = Integer.parseInt( args[ 3 ] );
      int aFunctionID = Integer.parseInt( args[ 4 ] );
      int rcrdCount = 0;
      //   String sessionSpec = lsgc.getLoginID(aUserID, aPassword, "int");
      String sessionSpec = "";
      String ipAddress = "163.35.121.165";
      System.out.println( "User ID IS " + aUserID );
      System.out.println( "System ID IS " + aSystemID );
      System.out.println( "Module ID IS " + aModuleID );
      System.out.println( "Function ID IS " + aFunctionID );
      //      System.out.println("**************************************");
      //      System.out.println("getSystems Method ");
      //      rs = lsgc.getSystems(aUserID, sessionSpec, ipAddress);
      //      rcrdCount = rs.getRecordCount();
      //      for (int i = 0; i < rcrdCount; i++) {
      //        System.out.println("SYSTEM_ID IS " + rs.getString(i, "SYSTEM_ID"));
      //        System.out.println("SYSTEM_NAME IS " + rs.getString(i, "SYSTEM_NAME"));
      //      }
      //
      //      System.out.println("**************************************");
      //      System.out.println("getSystemModules Method ");
      //      rs = lsgc.getSystemModules(aUserID, sessionSpec, ipAddress, aSystemID);
      //      rcrdCount = rs.getRecordCount();
      //      for (int i = 0; i < rcrdCount; i++) {
      //        System.out.println("MODULE_ID IS " + rs.getInt(i, "MODULE_ID"));
      //        System.out.println("MODULE_NAME IS " + rs.getString(i, "MODULE_NAME"));
      //      }
      //
      System.out.println( "**************************************" );
      System.out.println( "getSystemModulesAndFunctions Method " );
      rs = lsgc.getSystemModulesAndFunctions( aUserID, sessionSpec, ipAddress,
                                              aSystemID );
      rcrdCount = rs.getRecordCount();
      for ( int i = 0; i < rcrdCount; i++ )
      {
        System.out.println( "MODULE_ID IS " + rs.getInt( i, "MODULE_ID" ) );
        System.out.println( "MODULE_NAME  IS "
                            + rs.getString( i, "MODULE_NAME" ) );
        System.out.println( "FUNCTION_ID IS " + rs.getInt( i, "FUNCTION_ID" ) );
        System.out.println( "FUNCTION_NAME  IS "
                            + rs.getString( i, "FUNCTION_NAME" ) );
      }

      //      System.out.println("**************************************");
      //      System.out.println("getUserBasicProfile Method ");
      //      rs = lsgc.getUserBasicProfile(aUserID, sessionSpec, ipAddress);
      //      rcrdCount = rs.getRecordCount();
      //      for (int i = 0; i < rcrdCount; i++) {
      //        System.out.println("FIRST_NAME IS " + rs.getString(i, "FIRST_NAME"));
      //        System.out.println("middle_name IS " + rs.getString(i, "MIDDLE_NAME"));
      //        System.out.println("last_name IS " + rs.getString(i, "LAST_NAME"));
      //        System.out.println("email_address IS " +
      //                           rs.getString(i, "EMAIL_ADDRESS"));
      //      }
      //
      //      System.out.println("**************************************");
      //      System.out.println("getSystemModuleFunctions");
      //      rs = lsgc.getSystemModuleFunctions(aUserID, sessionSpec, ipAddress,
      //                                         aSystemID, aModuleID);
      //      System.out.println(rs.toString());
      //      rcrdCount = rs.getRecordCount();
      //      for (int i = 0; i < rcrdCount; i++) {
      //        System.out.println("FUNCTION_ID IS " + rs.getInt(i, "FUNCTION_ID"));
      //        System.out.println("FUNCTION_NAME IS " +
      //                           rs.getString(i, "FUNCTION_NAME"));
      //      }

      System.out.println( "**************************************" );
      System.out.println( "canAccessSystem" );
      boolean retVal = lsgc.canAccessSystem( aUserID, sessionSpec, ipAddress,
                                             aSystemID );
      System.out.println( retVal );
      //      System.out.println("**************************************");
      //      System.out.println("canAccessSystemModule");
      //      retVal = lsgc.canAccessSystemModule(aUserID, sessionSpec, ipAddress,
      //                                          aSystemID, aModuleID);
      //      System.out.println(retVal);
      //      System.out.println("**************************************");
      //      System.out.println("canAccessSystemModuleFunction");
      //      retVal = lsgc.canAccessSystemModuleFunction(aUserID, sessionSpec,
      //                                                  ipAddress, aSystemID,
      //                                                  aModuleID, aFunctionID);
      //      System.out.println(retVal);
      System.out.println( "**************************************" );
      System.out.println( "getUserCNPJCPF Method " );
      //         sessionSpec = lsgc.getLoginID("mph41", "test123", "ext");
      rs = lsgc.getUserCNPJCPF( aUserID, sessionSpec, ipAddress );
      System.out.println( rs.toString() );
      rcrdCount = rs.getRecordCount();
      for ( int i = 0; i < rcrdCount; i++ )
      {
        System.out.println( "cnpj_number IS " + rs.getString( i, "CNPJ_NUMBER" ) );
        System.out.println( "cpf_number IS " + rs.getString( i, "CPF_NUMBER" ) );
      }

    }
    catch ( Exception ex )
    {
      ex.printStackTrace();
      System.out.println( "EXCEPTION  = " + ex.getMessage() );
    }
  }

  public static final String MODULES = "modules";

  public static final String MODULES_FUNCTIONS = "modulesFunctions";

  public static final String ACCESS = "access";

  public static final String CUSTOMER = "customer";

}