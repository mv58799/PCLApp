//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.1-05/30/2003 05:06 AM(java_re)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2005.06.02 at 05:03:40 BRT 
//


package com.citibank.latam.common.bind.impl;

public class RECORDLISTTYPEImpl implements com.citibank.latam.common.bind.RECORDLISTTYPE, com.sun.xml.bind.JAXBObject, com.citibank.latam.common.bind.impl.runtime.UnmarshallableObject, com.citibank.latam.common.bind.impl.runtime.XMLSerializable, com.citibank.latam.common.bind.impl.runtime.ValidatableObject
{

    protected java.lang.String _COLUMNCOUNT;
    protected com.sun.xml.bind.util.ListImpl _RECORD = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
    protected java.lang.String _RECORDCOUNT;
    protected java.lang.String _COLUMNNAME;
    public final static java.lang.Class version = (com.citibank.latam.common.bind.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.citibank.latam.common.bind.RECORDLISTTYPE.class);
    }

    public java.lang.String getCOLUMNCOUNT() {
        return _COLUMNCOUNT;
    }

    public void setCOLUMNCOUNT(java.lang.String value) {
        _COLUMNCOUNT = value;
    }

    public java.util.List getRECORD() {
        return _RECORD;
    }

    public java.lang.String getRECORDCOUNT() {
        return _RECORDCOUNT;
    }

    public void setRECORDCOUNT(java.lang.String value) {
        _RECORDCOUNT = value;
    }

    public java.lang.String getCOLUMNNAME() {
        return _COLUMNNAME;
    }

    public void setCOLUMNNAME(java.lang.String value) {
        _COLUMNNAME = value;
    }

    public com.citibank.latam.common.bind.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.citibank.latam.common.bind.impl.runtime.UnmarshallingContext context) {
        return new com.citibank.latam.common.bind.impl.RECORDLISTTYPEImpl.Unmarshaller(context);
    }

    public void serializeElementBody(com.citibank.latam.common.bind.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx2 = 0;
        final int len2 = _RECORD.size();
        if (_RECORDCOUNT!= null) {
            context.startElement("", "RECORD_COUNT");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _RECORDCOUNT));
            } catch (java.lang.Exception e) {
                com.citibank.latam.common.bind.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_COLUMNCOUNT!= null) {
            context.startElement("", "COLUMN_COUNT");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _COLUMNCOUNT));
            } catch (java.lang.Exception e) {
                com.citibank.latam.common.bind.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_COLUMNNAME!= null) {
            context.startElement("", "COLUMN_NAME");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _COLUMNNAME));
            } catch (java.lang.Exception e) {
                com.citibank.latam.common.bind.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        while (idx2 != len2) {
            context.startElement("", "RECORD");
            int idx_6 = idx2;
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _RECORD.get(idx_6 ++)));
            context.endNamespaceDecls();
            int idx_7 = idx2;
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _RECORD.get(idx_7 ++)));
            context.endAttributes();
            context.childAsElementBody(((com.sun.xml.bind.JAXBObject) _RECORD.get(idx2 ++)));
            context.endElement();
        }
    }

    public void serializeAttributes(com.citibank.latam.common.bind.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx2 = 0;
        final int len2 = _RECORD.size();
    }

    public void serializeAttributeBody(com.citibank.latam.common.bind.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx2 = 0;
        final int len2 = _RECORD.size();
        if (_RECORDCOUNT!= null) {
            context.startElement("", "RECORD_COUNT");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _RECORDCOUNT));
            } catch (java.lang.Exception e) {
                com.citibank.latam.common.bind.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_COLUMNCOUNT!= null) {
            context.startElement("", "COLUMN_COUNT");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _COLUMNCOUNT));
            } catch (java.lang.Exception e) {
                com.citibank.latam.common.bind.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_COLUMNNAME!= null) {
            context.startElement("", "COLUMN_NAME");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _COLUMNNAME));
            } catch (java.lang.Exception e) {
                com.citibank.latam.common.bind.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        while (idx2 != len2) {
            context.startElement("", "RECORD");
            int idx_6 = idx2;
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _RECORD.get(idx_6 ++)));
            context.endNamespaceDecls();
            int idx_7 = idx2;
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _RECORD.get(idx_7 ++)));
            context.endAttributes();
            context.childAsElementBody(((com.sun.xml.bind.JAXBObject) _RECORD.get(idx2 ++)));
            context.endElement();
        }
    }

    public void serializeURIs(com.citibank.latam.common.bind.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        int idx2 = 0;
        final int len2 = _RECORD.size();
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.citibank.latam.common.bind.RECORDLISTTYPE.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0003I\u0000\u000ecachedHashCodeL\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava"
+"/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0002xp\u0002\u001f\u00b2\u00bdppsq\u0000~\u0000\u0000\u0000PhQppsq\u0000~\u0000\u0000\u0000"
+"5\u009a\u00dfppsr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001\u0000\u001a\u00cdmp"
+"psr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tna"
+"meClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.gra"
+"mmar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fco"
+"ntentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003\u0000\u001a\u00cdbsr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005"
+"valuexp\u0000p\u0000sr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001f"
+"Lorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/"
+"sun/msv/util/StringPair;xq\u0000~\u0000\u0003\u0000\u001a\u00cdWppsr\u0000#com.sun.msv.datatype"
+".xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.da"
+"tatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datat"
+"ype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd."
+"XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String"
+";L\u0000\btypeNameq\u0000~\u0000\u0018L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/W"
+"hiteSpaceProcessor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0006s"
+"tringsr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcessor$Preser"
+"ve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcesso"
+"r\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun.msv.grammar.Expression$NullSetExp"
+"ression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\nppsr\u0000\u001bcom.sun.msv.util.StringPai"
+"r\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0018L\u0000\fnamespaceURIq\u0000~\u0000\u0018xpq\u0000~\u0000\u001cq\u0000~\u0000"
+"\u001bsr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalN"
+"ameq\u0000~\u0000\u0018L\u0000\fnamespaceURIq\u0000~\u0000\u0018xr\u0000\u001dcom.sun.msv.grammar.NameClas"
+"s\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\fRECORD_COUNTt\u0000\u0000sr\u00000com.sun.msv.grammar.Expr"
+"ession$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\tsq\u0000~\u0000\u000e\u0001psq\u0000~\u0000\b\u0000"
+"\u001a\u00cdmppsq\u0000~\u0000\n\u0000\u001a\u00cdbq\u0000~\u0000\u000fp\u0000q\u0000~\u0000\u0013sq\u0000~\u0000$t\u0000\fCOLUMN_COUNTq\u0000~\u0000(q\u0000~\u0000*sq"
+"\u0000~\u0000\b\u0000\u001a\u00cdmppsq\u0000~\u0000\n\u0000\u001a\u00cdbq\u0000~\u0000\u000fp\u0000q\u0000~\u0000\u0013sq\u0000~\u0000$t\u0000\u000bCOLUMN_NAMEq\u0000~\u0000(q\u0000~"
+"\u0000*sq\u0000~\u0000\b\u0001\u00cfJgppsr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0000xr\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000"
+"\u0003\u0001\u00cfJ\\q\u0000~\u0000\u000fpsq\u0000~\u0000\n\u0001\u00cfJYq\u0000~\u0000\u000fp\u0000sq\u0000~\u0000\n\u0001\u00cfJNpp\u0000sq\u0000~\u0000\b\u0001\u00cfJCppsq\u0000~\u00005\u0001"
+"\u00cfJ8q\u0000~\u0000\u000fpsr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003e"
+"xpq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\u000bxq\u0000~\u0000\u0003\u0001\u00cfJ5q\u0000~\u0000\u000fpsr\u00002com.sun.msv.gram"
+"mar.Expression$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003\u0000\u0000\u0000\bq\u0000~\u0000+"
+"psr\u0000 com.sun.msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000%q\u0000~\u0000*s"
+"q\u0000~\u0000$t\u0000)com.citibank.latam.common.bind.RECORDTYPEt\u0000+http://j"
+"ava.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000$t\u0000\u0006RECORDq\u0000~\u0000(q\u0000~\u0000*"
+"sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTable"
+"t\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-com."
+"sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0002\u0000\u0004I\u0000\u0005count"
+"I\u0000\tthresholdL\u0000\u0006parentq\u0000~\u0000H[\u0000\u0005tablet\u0000![Lcom/sun/msv/grammar/E"
+"xpression;xp\u0000\u0000\u0000\n\u0000\u0000\u00009pur\u0000![Lcom.sun.msv.grammar.Expression;\u00d68"
+"D\u00c3]\u00ad\u00a7\n\u0002\u0000\u0000xp\u0000\u0000\u0000\u00bfppppppppppppppppppppppppppppppppppppppppppppq"
+"\u0000~\u0000;ppppppppppq\u0000~\u0000:ppppppppppppppppppppppppq\u0000~\u00007pppppq\u0000~\u0000\u0006pp"
+"q\u0000~\u0000\tq\u0000~\u0000,q\u0000~\u00000q\u0000~\u00004pppppppppppppppppppppppppppppppppppppppp"
+"pppppppppppppppppppppppppppppppppppppppppppppppppq\u0000~\u0000\u0005q\u0000~\u0000\u0007p"
+"pppppp"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.citibank.latam.common.bind.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.citibank.latam.common.bind.impl.runtime.UnmarshallingContext context) {
            super(context, "-------------");
        }

        protected Unmarshaller(com.citibank.latam.common.bind.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.citibank.latam.common.bind.impl.RECORDLISTTYPEImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        if (("RECORD" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 10;
                            return ;
                        }
                        state = 12;
                        continue outer;
                    case  10 :
                        if (("PARENT_FUNCTION_ID" == ___local)&&("" == ___uri)) {
                            _RECORD.add(((com.citibank.latam.common.bind.impl.RECORDTYPEImpl) spawnChildFromEnterElement((com.citibank.latam.common.bind.impl.RECORDTYPEImpl.class), 11, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("FUNCTION_NAME" == ___local)&&("" == ___uri)) {
                            _RECORD.add(((com.citibank.latam.common.bind.impl.RECORDTYPEImpl) spawnChildFromEnterElement((com.citibank.latam.common.bind.impl.RECORDTYPEImpl.class), 11, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("FUNCTION_ID" == ___local)&&("" == ___uri)) {
                            _RECORD.add(((com.citibank.latam.common.bind.impl.RECORDTYPEImpl) spawnChildFromEnterElement((com.citibank.latam.common.bind.impl.RECORDTYPEImpl.class), 11, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("MODULE_NAME" == ___local)&&("" == ___uri)) {
                            _RECORD.add(((com.citibank.latam.common.bind.impl.RECORDTYPEImpl) spawnChildFromEnterElement((com.citibank.latam.common.bind.impl.RECORDTYPEImpl.class), 11, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("MODULE_ID" == ___local)&&("" == ___uri)) {
                            _RECORD.add(((com.citibank.latam.common.bind.impl.RECORDTYPEImpl) spawnChildFromEnterElement((com.citibank.latam.common.bind.impl.RECORDTYPEImpl.class), 11, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("USER_ID" == ___local)&&("" == ___uri)) {
                            _RECORD.add(((com.citibank.latam.common.bind.impl.RECORDTYPEImpl) spawnChildFromEnterElement((com.citibank.latam.common.bind.impl.RECORDTYPEImpl.class), 11, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("CPF_NUMBER" == ___local)&&("" == ___uri)) {
                            _RECORD.add(((com.citibank.latam.common.bind.impl.RECORDTYPEImpl) spawnChildFromEnterElement((com.citibank.latam.common.bind.impl.RECORDTYPEImpl.class), 11, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("CNPJ_NUMBER" == ___local)&&("" == ___uri)) {
                            _RECORD.add(((com.citibank.latam.common.bind.impl.RECORDTYPEImpl) spawnChildFromEnterElement((com.citibank.latam.common.bind.impl.RECORDTYPEImpl.class), 11, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        if (("GROUP_NAME" == ___local)&&("" == ___uri)) {
                            _RECORD.add(((com.citibank.latam.common.bind.impl.RECORDTYPEImpl) spawnChildFromEnterElement((com.citibank.latam.common.bind.impl.RECORDTYPEImpl.class), 11, ___uri, ___local, ___qname, __atts)));
                            return ;
                        }
                        _RECORD.add(((com.citibank.latam.common.bind.impl.RECORDTYPEImpl) spawnChildFromEnterElement((com.citibank.latam.common.bind.impl.RECORDTYPEImpl.class), 11, ___uri, ___local, ___qname, __atts)));
                        return ;
                    case  0 :
                        if (("RECORD_COUNT" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        state = 3;
                        continue outer;
                    case  12 :
                        if (("RECORD" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 10;
                            return ;
                        }
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  3 :
                        if (("COLUMN_COUNT" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        state = 6;
                        continue outer;
                    case  6 :
                        if (("COLUMN_NAME" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        state = 9;
                        continue outer;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  8 :
                        if (("COLUMN_NAME" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                    case  9 :
                        state = 12;
                        continue outer;
                    case  2 :
                        if (("RECORD_COUNT" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  11 :
                        if (("RECORD" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 12;
                            return ;
                        }
                        break;
                    case  10 :
                        _RECORD.add(((com.citibank.latam.common.bind.impl.RECORDTYPEImpl) spawnChildFromLeaveElement((com.citibank.latam.common.bind.impl.RECORDTYPEImpl.class), 11, ___uri, ___local, ___qname)));
                        return ;
                    case  0 :
                        state = 3;
                        continue outer;
                    case  12 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  3 :
                        state = 6;
                        continue outer;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  5 :
                        if (("COLUMN_COUNT" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 6;
                            return ;
                        }
                        break;
                }
                super.leaveElement(___uri, ___local, ___qname);
                break;
            }
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        state = 12;
                        continue outer;
                    case  10 :
                        _RECORD.add(((com.citibank.latam.common.bind.impl.RECORDTYPEImpl) spawnChildFromEnterAttribute((com.citibank.latam.common.bind.impl.RECORDTYPEImpl.class), 11, ___uri, ___local, ___qname)));
                        return ;
                    case  0 :
                        state = 3;
                        continue outer;
                    case  12 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  3 :
                        state = 6;
                        continue outer;
                    case  6 :
                        state = 9;
                        continue outer;
                }
                super.enterAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        state = 12;
                        continue outer;
                    case  10 :
                        _RECORD.add(((com.citibank.latam.common.bind.impl.RECORDTYPEImpl) spawnChildFromLeaveAttribute((com.citibank.latam.common.bind.impl.RECORDTYPEImpl.class), 11, ___uri, ___local, ___qname)));
                        return ;
                    case  0 :
                        state = 3;
                        continue outer;
                    case  12 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  3 :
                        state = 6;
                        continue outer;
                    case  6 :
                        state = 9;
                        continue outer;
                }
                super.leaveAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void handleText(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                try {
                    switch (state) {
                        case  4 :
                            eatText0(value);
                            state = 5;
                            return ;
                        case  9 :
                            state = 12;
                            continue outer;
                        case  7 :
                            eatText1(value);
                            state = 8;
                            return ;
                        case  10 :
                            _RECORD.add(((com.citibank.latam.common.bind.impl.RECORDTYPEImpl) spawnChildFromText((com.citibank.latam.common.bind.impl.RECORDTYPEImpl.class), 11, value)));
                            return ;
                        case  0 :
                            state = 3;
                            continue outer;
                        case  1 :
                            eatText2(value);
                            state = 2;
                            return ;
                        case  12 :
                            revertToParentFromText(value);
                            return ;
                        case  3 :
                            state = 6;
                            continue outer;
                        case  6 :
                            state = 9;
                            continue outer;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

        private void eatText0(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _COLUMNCOUNT = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _COLUMNNAME = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _RECORDCOUNT = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
