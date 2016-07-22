package com.espl.zero.sfdc;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

@Component
public class ConnectionFactory {

	@Value("${sfdc.username}")
	private String USERNAME;
	@Value("${sfdc.password}")
	private String PASSWORD;
	@Value("${sfdc.authEndPoint}")
	private String AUTH_END_POINT;
	
	private static PartnerConnection partnerConnection;
	private static ConnectorConfig config = new ConnectorConfig();
	
	public String getUsername() {
		return USERNAME;
	}
	public String getAuthEndpoint() {
		return AUTH_END_POINT;
	}
	public String getSessionId(){
		return config.getSessionId();
	}
	
	public PartnerConnection getPartnerConnection() {
		if (partnerConnection == null) {
			createPartnerConnection();
		}
		return partnerConnection;
	}

	private void createPartnerConnection() {
		System.out.println("Creating new Partner Connection for " + USERNAME);

		config = new ConnectorConfig();
		config.setUsername(USERNAME);
		config.setPassword(PASSWORD);
		config.setAuthEndpoint(AUTH_END_POINT);
		
		try {
			partnerConnection = new PartnerConnection(config);
		} catch (ConnectionException ex) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(
					Level.SEVERE, null, ex);
			System.out.println("ERROR in ConnectionFactory.createPartnerConnection()");
		}
	}

}
