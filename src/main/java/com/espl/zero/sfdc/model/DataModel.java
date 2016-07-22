package com.espl.zero.sfdc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sforce.soap.partner.Error;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.FieldType;
import com.sforce.soap.partner.sobject.SObject;

public final class DataModel{
	private Map<String, String> field = new HashMap<String, String>();
	private List<String> errors = new ArrayList<String>();

	public Map<String, String> getField() {
		return field;
	}
	public String getField(String fieldName){
		return field.get(fieldName);
	}
	public boolean setFieldData(SObject sObject,SFDCModel model){
		if(sObject.getType().equals(model.API_NAME)){
			for (Field field : model.getAllFields()) {
				Object fieldValue = sObject.getField(field.getName());
				if(fieldValue!=null){
					this.field.put(field.getName(), fieldValue.toString());
				}
			}
			return true;
		}
		return false;
	}

	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(Error[] errors){
		for (Error error : errors) {
			this.errors.add(error.getMessage());
		}
	}

	public final boolean isValid(){
		return errors.size() == 0;
	}
	public final boolean isValid(String Id){
		if(field.containsKey("Id") && field.get("Id").equals(Id))
			return true;
		else
			return false;
	}
	
	public final boolean validate(SFDCModel model){
		Field[] customFields = model.getCustomFields();
		Field nameField = model.getNameField(); 
		
		if(nameField.getAutoNumber()==false && nameField.getNillable()==false){
			errors.add(nameField.getLabel() + " is required !");
		}
		
		for (Field field : customFields) {
			String value = getField().get(field.getName());
			
			// Check if field is required 
			if(field.getNillable()==false){
				// check if field is available and not null 
				if(getField().containsKey(field.getName()) && value.trim().length()==0){
					errors.add(field.getLabel() + " is required !");
				}else if(getField().containsKey(field.getName())==false){
					errors.add(field.getLabel() + " is required and is not found !");
//				}else if(field.getType()==FieldType.email && !validEmail(value)){
//					errors.add(field.getLabel() + " has invalid email address !");
				}
			}
			
			// Check if field is picklist and value is none
		//	else if((field.getType()==FieldType.picklist && value.equals("--None--")) || value.isEmpty() || value == null){
				//getField().replace(field.getName()., "");
		//	}
			
			// Email field is not required & valid
//			if(field.getType()==FieldType.email && !validEmail(value)){
//				errors.add(field.getLabel() + " is invalid !");
//			}
		}
		
		return isValid();
	}
	
	private static final boolean validEmail(String email){
		if(email.trim().isEmpty())
			return true;
		
		String emailRegex = "^[a-zA-Z0-9._|\\\\%#~`=?&/$^*!}{+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}

