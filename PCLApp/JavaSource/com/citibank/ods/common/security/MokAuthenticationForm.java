// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MokAuthenticationForm.java

package com.citibank.ods.common.security;

import org.apache.struts.action.ActionForm;

public class MokAuthenticationForm extends ActionForm
{
	private String  m_username ;
	private String  m_password;
	private String  m_groupName;
   
    public String getm_password()
    {
        return m_password;
    }

    public void setm_password(String password_)
    {
        this.m_password = password_;
    }

    public String getm_username()
    {
        return m_username;
    }

    public void setm_username(String username_)
    {
        this.m_username = username_;
    }

    public String getm_groupName()
    {
        return m_groupName;
    }

    public void setm_groupName(String groupName_)
    {
        this.m_groupName = groupName_;
    }
}
