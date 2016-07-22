package com.espl.zero.sfdc.util;

import com.espl.zero.sfdc.resultparsers.DescribeSObjectResultParser;
import com.sforce.soap.partner.DescribeLayoutResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;

public class SObjectPageLayoutParser {

	private PartnerConnection connection;
	private String sObjectType;
	
	private DescribeSObjectResultParser sObjectResult;
	private DescribeLayoutResult layoutResult;

	public static SObjectPageLayoutParser createInstance(String sObjectType,
			PartnerConnection connection) throws ConnectionException {
		SObjectPageLayoutParser parser = new SObjectPageLayoutParser(
				sObjectType, connection);
		return parser;
	}

	private SObjectPageLayoutParser(String sObjectType,
			PartnerConnection connection) throws ConnectionException {
		this.sObjectType = sObjectType;
		this.connection = connection;

//		this.describeSObject();
//		this.describeLayout();
	}

	private void describeLayout() throws ConnectionException {
		this.layoutResult = connection.describeLayout(sObjectType, null, null);
	}

	private void describeSObject() throws ConnectionException {
		DescribeSObjectResult objectResult = connection.describeSObject(sObjectType);
		this.sObjectResult = new DescribeSObjectResultParser(objectResult);
	}

	public String getsObjectType() {
		return sObjectType;
	}

	public DescribeSObjectResultParser getsObjectResult() {
		if(this.sObjectResult==null){
			try {
				describeSObject();
			} catch (ConnectionException e) {
				e.printStackTrace();
				return null;
			}
		}
		return sObjectResult;
	}

	public DescribeLayoutResult getLayoutResult() {
		if(this.layoutResult==null){
			try {
				describeLayout();
			} catch (ConnectionException e) {
				e.printStackTrace();
				return null;
			}
		}
		return layoutResult;
	}

	
}
