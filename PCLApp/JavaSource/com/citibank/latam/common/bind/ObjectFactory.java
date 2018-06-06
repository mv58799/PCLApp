//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.1-05/30/2003 05:06 AM(java_re)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.06.02 at 05:03:40 BRT 
//


package com.citibank.latam.common.bind;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.citibank.latam.common.bind package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
public class ObjectFactory
    extends com.citibank.latam.common.bind.impl.runtime.DefaultJAXBContextImpl
{

    private static java.util.HashMap defaultImplementations = new java.util.HashMap();
    public final static java.lang.Class version = (com.citibank.latam.common.bind.impl.JAXBVersion.class);

    static {
        defaultImplementations.put("com.citibank.latam.common.bind.XMLTYPE", "com.citibank.latam.common.bind.impl.XMLTYPEImpl");
        defaultImplementations.put("com.citibank.latam.common.bind.RECORDTYPE", "com.citibank.latam.common.bind.impl.RECORDTYPEImpl");
        defaultImplementations.put("com.citibank.latam.common.bind.XML", "com.citibank.latam.common.bind.impl.XMLImpl");
        defaultImplementations.put("com.citibank.latam.common.bind.RECORDLISTTYPE", "com.citibank.latam.common.bind.impl.RECORDLISTTYPEImpl");
    }

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.citibank.latam.common.bind
     * 
     */
    public ObjectFactory() {
        super(new com.citibank.latam.common.bind.ObjectFactory.GrammarInfoImpl());
    }

    /**
     * Create an instance of the specified Java content interface.
     * 
     * @param javaContentInterface the Class object of the javacontent interface to instantiate
     * @return a new instance
     * @throws JAXBException if an error occurs
     */
    public java.lang.Object newInstance(java.lang.Class javaContentInterface)
        throws javax.xml.bind.JAXBException
    {
        return super.newInstance(javaContentInterface);
    }

    /**
     * Get the specified property. This method can only be
     * used to get provider specific properties.
     * Attempting to get an undefined property will result
     * in a PropertyException being thrown.
     * 
     * @param name the name of the property to retrieve
     * @return the value of the requested property
     * @throws PropertyException when there is an error retrieving the given property or value
     */
    public java.lang.Object getProperty(java.lang.String name)
        throws javax.xml.bind.PropertyException
    {
        return super.getProperty(name);
    }

    /**
     * Set the specified property. This method can only be
     * used to set provider specific properties.
     * Attempting to set an undefined property will result
     * in a PropertyException being thrown.
     * 
     * @param name the name of the property to retrieve
     * @param value the value of the property to be set
     * @throws PropertyException when there is an error processing the given property or value
     */
    public void setProperty(java.lang.String name, java.lang.Object value)
        throws javax.xml.bind.PropertyException
    {
        super.setProperty(name, value);
    }

    /**
     * Create an instance of XMLTYPE
     * 
     * @throws JAXBException if an error occurs
     */
    public com.citibank.latam.common.bind.XMLTYPE createXMLTYPE()
        throws javax.xml.bind.JAXBException
    {
        return new com.citibank.latam.common.bind.impl.XMLTYPEImpl();
    }

    /**
     * Create an instance of RECORDTYPE
     * 
     * @throws JAXBException if an error occurs
     */
    public com.citibank.latam.common.bind.RECORDTYPE createRECORDTYPE()
        throws javax.xml.bind.JAXBException
    {
        return new com.citibank.latam.common.bind.impl.RECORDTYPEImpl();
    }

    /**
     * Create an instance of XML
     * 
     * @throws JAXBException if an error occurs
     */
    public com.citibank.latam.common.bind.XML createXML()
        throws javax.xml.bind.JAXBException
    {
        return new com.citibank.latam.common.bind.impl.XMLImpl();
    }

    /**
     * Create an instance of RECORDLISTTYPE
     * 
     * @throws JAXBException if an error occurs
     */
    public com.citibank.latam.common.bind.RECORDLISTTYPE createRECORDLISTTYPE()
        throws javax.xml.bind.JAXBException
    {
        return new com.citibank.latam.common.bind.impl.RECORDLISTTYPEImpl();
    }

    private static class GrammarInfoImpl
        extends com.citibank.latam.common.bind.impl.runtime.AbstractGrammarInfoImpl
    {


        public java.lang.Class getDefaultImplementation(java.lang.Class javaContentInterface) {
            java.lang.Class c = null;
            try {
                c = java.lang.Class.forName(((java.lang.String) defaultImplementations.get(javaContentInterface.getName())));
            } catch (java.lang.Exception _x) {
                c = null;
            }
            return c;
        }

        public com.citibank.latam.common.bind.impl.runtime.UnmarshallingEventHandler createUnmarshaller(java.lang.String uri, java.lang.String local, com.citibank.latam.common.bind.impl.runtime.UnmarshallingContext context) {
            if (("XML" == local)&&("" == uri)) {
                return new com.citibank.latam.common.bind.impl.XMLImpl().createUnmarshaller(context);
            }
            return null;
        }

        public java.lang.Class getRootElement(java.lang.String uri, java.lang.String local) {
            if (("XML" == local)&&("" == uri)) {
                return (com.citibank.latam.common.bind.impl.XMLImpl.class);
            }
            return null;
        }

        public boolean recognize(java.lang.String uri, java.lang.String local) {
            if (("XML" == local)&&("" == uri)) {
                return true;
            }
            return false;
        }

        public java.lang.String[] getProbePoints() {
            return new java.lang.String[] {"", "XML"};
        }

    }

}
