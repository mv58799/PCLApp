package com.citibank.ods.common.entity;

import java.io.Serializable;

import com.citibank.ods.common.BaseObject;

/**
 * 
 * Classe base para as calsse do tipo Entity. Estas classes devem ser
 * serializ�veis porque podem ser agregadas �s classes do tipo FncVO que s�o
 * armazenadas em sess�o.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class BaseEntity extends BaseObject implements Serializable
{

}