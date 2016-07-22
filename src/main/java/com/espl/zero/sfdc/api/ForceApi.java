package com.espl.zero.sfdc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.espl.zero.sfdc.ConnectionFactory;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.UpsertResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;

public class ForceApi {
	protected static final Logger logger = LoggerFactory.getLogger(ForceApi.class);
	
	public static UpsertResult[] Save(ConnectionFactory connectionFactory,SObject sObject){
		UpsertResult[] result = null;
		try {
			result = connectionFactory.getPartnerConnection().upsert("Id", new SObject[]{sObject});
		} catch (ConnectionException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}
