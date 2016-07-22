package com.espl.zero.sfdc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.espl.zero.sfdc.ConnectionFactory;
import com.espl.zero.sfdc.api.PartnerApi;
import com.espl.zero.util.enums.LayoutType;
import com.sforce.soap.partner.DescribeLayout;
import com.sforce.soap.partner.DescribeLayoutResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.FieldType;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.UpsertResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.soap.partner.DeletedRecord; 
import com.sforce.ws.bind.XmlObject;

@Component
public abstract class SFDCModel {
	public static String API_NAME = "API_NAME";
	@Autowired
	private ConnectionFactory connectionFactory;
	
	private DescribeSObjectResult sObjectResult = null;
	private DescribeLayoutResult layoutResult = null;

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ DescribeSObject +++++++++++++++++++//
	public abstract DescribeSObjectResult describeSObjectResult();
	protected final DescribeSObjectResult describeSObjectResult(String API_NAME) {
		if (this.sObjectResult == null) {
			this.sObjectResult = PartnerApi.describeSObject(connectionFactory, API_NAME);
		}
		return this.sObjectResult;
	}
	
	public final Field getNameField(){
		Field[] fields = getAllFields();
		Field result = null;
		for (Field field : fields) {
			if(field.getNameField()){
				result = field;
				break;
			}
		}
		return result;
	}
	
	public abstract Field[] getCustomFields();
	protected final Field[] getCustomFields(String API_NAME) {
		ArrayList<Field> fields = new ArrayList<Field>();
		for (Field field : getAllFields(API_NAME)) {
			if (field.getCustom()) {
				fields.add(field);
			}
		}

		Field customFields[] = new Field[fields.size()];
		customFields = fields.toArray(customFields);
		return customFields;
	}
	
	public abstract int getCustomFieldsLength();
	protected final int getCustomFieldsLength(String API_NAME){
		return getCustomFields(API_NAME).length;
	}
	
	public abstract Field[] getAllFields();
	protected final Field[] getAllFields(String API_NAME){
		return describeSObjectResult(API_NAME).getFields();
	}
	
	public abstract String getAllFieldsForQuery();
	protected final String getAllFieldsForQuery(String API_NAME) {
		StringBuilder queryFields = new StringBuilder();
		for (Field field : getAllFields(API_NAME)) {
			queryFields.append(field.getName());
			queryFields.append(",");
			if(field.getType()==FieldType.reference){
				queryFields.append(field.getRelationshipName()+".Name").append(",");
			}
		}
		return queryFields.toString().substring(0,
				queryFields.toString().length() - 1);
	}
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ DescribeSObject +++++++++++++++++++//
	//
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ DescribeSObjectLayout +++++++++++++++++++//
	public abstract DescribeLayoutResult describeLayoutResult();
	protected final DescribeLayoutResult describeLayoutResult(String API_NAME){
		if(this.layoutResult==null){
			this.layoutResult = PartnerApi.describeLayout(connectionFactory, API_NAME);
		}
		return this.layoutResult;
	}

	public abstract DescribeLayout[] getLayouts();
	protected final DescribeLayout[] getLayouts(String API_NAME){
		return this.describeLayoutResult(API_NAME).getLayouts();
	}
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ DescribeSObjectLayout +++++++++++++++++++//
	//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ Force.com Database Query +++++++++++++++++++//
	public QueryResult query(String SOQL){
		QueryResult result = PartnerApi.query(connectionFactory, SOQL);
		for (SObject sObject : result.getRecords()) {
			for (Field field : getCustomFields()) {
				if(field.getType()==FieldType.reference && sObject.getField(field.getRelationshipName())!=null){
					XmlObject value = (XmlObject) sObject.getField(field.getRelationshipName());
					sObject.setField(field.getName()+"_Name", value.getChild("Name").getValue());
//				}else{
//					sObject.addField(field.getName()+"_Name", "");
				}
			}
		}
		return result;
	}
	public QueryResult queryMore(String queryLocator){
		return PartnerApi.queryMore(connectionFactory, queryLocator);
	}
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ Force.com Database Query +++++++++++++++++++//
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ CRUD +++++++++++++++++++//
	public final UpsertResult[] Save(DataModel dataModel){
		SObject sObject = new SObject(API_NAME);
		List<String> fieldsToNullList = new ArrayList<String>();
		for (String fieldName : dataModel.getField().keySet()) {
			sObject.setField(fieldName, dataModel.getField().get(fieldName));
			// Add NullValue fields to set null value
			if(dataModel.getField().get(fieldName).isEmpty()){
				fieldsToNullList.add(fieldName);
			}
		}
		String[] fieldsToNull = new String[fieldsToNullList.size()];
		fieldsToNull = fieldsToNullList.toArray(fieldsToNull);
		
		for (String string : fieldsToNull) {
			System.out.println(string +" = NULL");
		}
		sObject.setFieldsToNull(fieldsToNull);
		
		return PartnerApi.Save(connectionFactory, sObject);
	}
	
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ CRUD +++++++++++++++++++//
	
	public final List<Field> convertToList(Field[] fields){
		List<Field> list = new ArrayList<Field>();
		for (Field field : fields) {
			list.add(field);
		}
		return list;
	}
	public static final Map<String,Field> convertToMap(Field[] fields){
		Map<String,Field> map = new HashMap<String, Field>();
		for (Field field : fields) {
			map.put(field.getName(), field);
		}
		return map;
	}
	
	public final String getDetailsLayoutType(){
		return LayoutType.DETAILS_LAYOUT;
	}
	public final String getEditLayoutType(){
		return LayoutType.EDIT_LAYOUT;
	}
	public final Field getField(String fieldName){
		Field returnField = null;
		for (Field field : getAllFields()) {
			if(field.getName().equals(fieldName)){
				returnField = field;
				break;
			}
		}
		return returnField;
	}
}
