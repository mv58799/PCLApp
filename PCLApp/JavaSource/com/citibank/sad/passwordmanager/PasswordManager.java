package com.citibank.sad.passwordmanager;

public class PasswordManager {

  private native int execute();

  static {
    try {
      System.loadLibrary("PasswordManager");
    }
    catch (Exception ex) {
      System.out.println( "Library PasswordManager not Loaded with errors:" + ex.getMessage() );
    }
  }

  /**
   * Constructor generico para recuperar, extrair, atribuir e remover senhas de arquivos criptografados
   * @param passwordFilename String que representa o nome do arquivo que contem senhas
   * @param keyFilename String que representa o nome do arquivo que contem chaves
   * @param userName String que representa o nome do usuario
   * @param serverName String que representa o nome do servidor
   * @param password String que representa a senha
   * @param force boolean se VERDADEIRO possibilita caso esta a senha exista sua substituicao
   */
  public PasswordManager(String passwordFilename, String keyFilename, String userName, String serverName, String password, boolean force) {
    super();
    this.setPasswordFilename(passwordFilename);
    this.setKeyFilename(keyFilename);
    this.setUserName(userName);
    this.setServerName(serverName);
    this.setPassword(password);
    this.setForce(force);
  }

  /**
   * Constructor generico para recuperar, extrair, atribuir e remover senhas de arquivos criptografados
   * @param passwordFilename String que representa o nome do arquivo que contem senhas
   * @param keyFilename String que representa o nome do arquivo que contem chaves
   * @param userName String que representa o nome do usuario
   * @param serverName String que representa o nome do servidor
   */
  public PasswordManager(String passwordFilename, String keyFilename, String userName, String serverName) {
    this(passwordFilename, keyFilename, userName, serverName, null, false);
  }

  /**
   * Constructor generico para recuperar, extrair, atribuir e remover senhas de arquivos criptografados
   * @param passwordFilename String que representa o nome do arquivo que contem senhas
   * @param keyFilename String que representa o nome do arquivo que contem chaves
   */
  public PasswordManager(String passwordFilename, String keyFilename) {
    this(passwordFilename, keyFilename, null, null, null, false);
  }

  /**
   * Funcao WRAPPER responsavel por recuperar senhas de arquivos criptografados
   * @throws Exception lanca exception caso haja algum problema relacionado ao acesso para o recurso JNI
   * @return int retorna VERDADEIRO ou FALSO ou codigo de erro
   */
  public int get() throws Exception {
    setMethod(JNI_METHOD_GET);
    return execute();
  }

  /**
   * Funcao WRAPPER responsavel por atribuir senhas de arquivos criptografados
   * @throws Exception lanca exception caso haja algum problema relacionado ao acesso para o recurso JNI
   * @return int retorna VERDADEIRO ou FALSO ou codigo de erro
   */
  public int set() throws Exception {
    setMethod(JNI_METHOD_SET);
    return execute();
  }

  /**
   * Funcao WRAPPER responsavel por extrair senhas de arquivos criptografados
   * @param index int indice que representa o numero da linha da tabela a ser listada
   * @throws Exception lanca exception caso haja algum problema relacionado ao acesso para o recurso JNI
   * @return int retorna VERDADEIRO ou FALSO ou codigo de erro
   */
  public int list(int index) throws Exception {
    setMethod(JNI_METHOD_LIST);
    setIndex(index);
    return execute();
  }

  /**
   * Funcao WRAPPER responsavel por remover senhas de arquivos criptografados
   * @throws Exception lanca exception caso haja algum problema relacionado ao acesso para o recurso JNI
   * @return int retorna VERDADEIRO ou FALSO ou codigo de erro
   */
  public int remove() throws Exception {
    setMethod(JNI_METHOD_REMOVE);
    return execute();
  }

  /**
   * Retorna uma String para o nome do arquivo de senha
   * @return String nome do arquivo de senha
   */
  public String getPasswordFilename() {
    return passwordFilename;
  }

  /**
   * Especifica uma String para o nome do arquivo de senha
   * @param passwordFilename String nome do arquivo de senha
   */
  public void setPasswordFilename(String passwordFilename) {
    this.passwordFilename = passwordFilename;
  }

  /**
   * Retorna uma String para o nome do arquivo de chave
   * @return String nome do arquivo de chave
   */
  public String getKeyFilename() {
    return keyFilename;
  }

  /**
   * Especifica uma String para o nome do arquivo de chave
   * @param keyFilename String nome do arquivo de chave
   */
  public void setKeyFilename(String keyFilename) {
    this.keyFilename = keyFilename;
  }

  /**
   * Retorna uma String para o nome do arquivo de chave
   * @return String nome do arquivo de chave
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Especifica uma String para o nome do usuario
   * @param userName String nome do usuario
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Retorna uma String para o nome do servidor
   * @return String nome do servidor
   */
  public String getServerName() {
    return serverName;
  }

  /**
   * Especifica uma String para o nome do servidor
   * @param serverName String nome do servidor
   */
  public void setServerName(String serverName) {
    this.serverName = serverName;
  }

  /**
   * Retorna uma String para a senha
   * @return String a senha
   */
  public String getPassword() {
    return password;
  }

  /**
   * Especifica uma String para a senha
   * @param password String a senha
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Especifica um boolean possibilitando a substituicao de senha caso exista
   * @param force boolean substituicao de senha caso exista
   */
  public void setForce(boolean force) {
    this.force = force;
  }

  /**
   * Especifica um indice que representa o numero da linha da tabela a ser listada
   * @param index int numero da linha da tabela a ser listada
   */
  public void setIndex(int index) {
    this.index = index;
  }

  /**
   * Especifica um caracterer que representa a funcionalidade a ser executada na biblioteca
   * @param method char funcionalidade a ser executada na biblioteca
   */
  private void setMethod(char method) {
    this.method = method;
  }

  private final char JNI_METHOD_GET = 'G';
  private final char JNI_METHOD_SET = 'S';
  private final char JNI_METHOD_LIST = 'L';
  private final char JNI_METHOD_REMOVE = 'R';

  private char method = JNI_METHOD_GET;
  private String passwordFilename;
  private String keyFilename;
  private String userName;
  private String serverName;
  private String password;
  private boolean force = false;
  private int index = 0;

  public final static int MAX_PATH = 512;
  public final static int FALSE_   = 0x00000000;
  public final static int TRUE_    = 0x00000001;

  public final static int ERROR_PASSWORD_FILE_ZERO_LENGTH = 0x00000100; /*256*/
  public final static int ERROR_KEY_FILE_ZERO_LENGTH      = 0x00000101; /*257*/
  public final static int ERROR_PARAMETER_NOT_SET         = 0x00000102; /*258*/
  public final static int ERROR_INVALID_KEY_FILE          = 0x00000103; /*259*/
  public final static int ERROR_INVALID_PASSWORD_FILE     = 0x00000104; /*260*/
  public final static int ERROR_USER_OR_SERVER_NOT_FOUND  = 0x00000105; /*261*/
  public final static int ERROR_USER_ALREADY_EXISTS       = 0x00000106; /*262*/
  public final static int ERROR_ADDING_RECORD             = 0x00000107; /*263*/
  public final static int ERROR_INVALID_DIRECTION         = 0x00000108; /*264*/
  public final static int ERROR_INVALID_KEY_LENGHT        = 0x00000109; /*265*/
  public final static int ERROR_READING_PASSWORD_FILE     = 0x0000010A; /*266*/
  public final static int ERROR_WRITING_PASSWORD_FILE     = 0x0000010B; /*267*/
  public final static int ERROR_READING_KEY_FILE          = 0x0000010C; /*268*/
  public final static int ERROR_WRITING_KEY_FILE          = 0x0000010D; /*269*/
  public final static int ERROR_LENGHT_PASSWORD_FILE      = 0x0000010E; /*270*/
  public final static int ERROR_LENGHT_KEY_FILE           = 0x0000010F; /*271*/
  public final static int ERROR_JNI_FIELD_ID_NOT_FOUND    = 0x00000110; /*272*/
  public final static int ERROR_JNI_GET_OBJECT_FAILED     = 0x00000111; /*273*/
  public final static int ERROR_JNI_GET_CLASS_FAILED      = 0x00000111; /*274*/
  public final static int ERROR_JNI_TYPE_UNKNOWN          = 0x00000111; /*275*/
  public final static int ERROR_INDEX_NOT_FOUND           = 0x00000112; /*276*/
  public final static int ERROR_METHOD_NOT_DEFINED        = 0x00000113; /*277*/

}
