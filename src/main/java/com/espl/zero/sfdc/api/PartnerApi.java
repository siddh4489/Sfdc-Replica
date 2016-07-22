package com.espl.zero.sfdc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.espl.zero.sfdc.ConnectionFactory;
import com.sforce.soap.partner.DescribeLayoutResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.ws.ConnectionException;

public final class PartnerApi extends PartnerQueryApi{
	public static final DescribeSObjectResult describeSObject(ConnectionFactory connectionFactory,String sObjectType){
		DescribeSObjectResult result = null;
		try {
			result = connectionFactory.getPartnerConnection().describeSObject(sObjectType);
		} catch (ConnectionException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	public static final DescribeLayoutResult describeLayout(ConnectionFactory connectionFactory,String sObjectType){
		DescribeLayoutResult result = null;
		try {
			result = connectionFactory.getPartnerConnection().describeLayout(sObjectType, null, null);
		} catch (ConnectionException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}
