package com.espl.zero.sfdc.model;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.sforce.soap.partner.DescribeLayout;
import com.sforce.soap.partner.DescribeLayoutResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Field;

@Component
public class Supplier extends SFDCModel{
	
	static{
		API_NAME = "EmployeeDemo__c";
	}

	@Override
	public DescribeSObjectResult describeSObjectResult() {
		return super.describeSObjectResult(API_NAME);
	}

	@Override
	public Field[] getCustomFields() {
		return super.getCustomFields(API_NAME);
	}

	@Override
	public int getCustomFieldsLength() {
		return super.getCustomFieldsLength(API_NAME);
	}

	@Override
	public String getAllFieldsForQuery() {
		return super.getAllFieldsForQuery(API_NAME);
	}

	@Override
	public Field[] getAllFields() {
		return super.getAllFields(API_NAME);
	}

	@Override
	public DescribeLayoutResult describeLayoutResult() {
		return super.describeLayoutResult(API_NAME);
	}

	@Override
	public DescribeLayout[] getLayouts() {
		return super.getLayouts(API_NAME);
	}

}
