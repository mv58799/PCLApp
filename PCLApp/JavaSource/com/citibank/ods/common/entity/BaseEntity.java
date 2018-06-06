package com.citibank.ods.common.entity;

import java.io.Serializable;

import com.citibank.ods.common.BaseObject;

/**
 * 
 * Classe base para as calsse do tipo Entity. Estas classes devem ser
 * serializáveis porque podem ser agregadas às classes do tipo FncVO que são
 * armazenadas em sessão.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class BaseEntity extends BaseObject implements Serializable
{

}