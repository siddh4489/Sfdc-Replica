package com.espl.zero.sfdc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.espl.zero.sfdc.ConnectionFactory;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;

public class PartnerQueryApi extends ForceApi{
	public static final QueryResult query(ConnectionFactory connectionFactory,String SOQL){
		QueryResult result = null;
		try {
			result = connectionFactory.getPartnerConnection().query(SOQL);
		} catch (ConnectionException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	public static final QueryResult queryMore(ConnectionFactory connectionFactory,String queryLocator){
		QueryResult result = null;
		try {
			result = connectionFactory.getPartnerConnection().queryMore(queryLocator);
		} catch (ConnectionException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}
