package com.espl.zero.sfdc.resultparsers;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.namespace.QName;

import com.sforce.soap.partner.ActionOverride;
import com.sforce.soap.partner.ChildRelationship;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.IActionOverride;
import com.sforce.soap.partner.IChildRelationship;
import com.sforce.soap.partner.IField;
import com.sforce.soap.partner.INamedLayoutInfo;
import com.sforce.soap.partner.IRecordTypeInfo;
import com.sforce.soap.partner.NamedLayoutInfo;
import com.sforce.soap.partner.RecordTypeInfo;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.bind.TypeMapper;
import com.sforce.ws.parser.XmlInputStream;
import com.sforce.ws.parser.XmlOutputStream;

public final class DescribeSObjectResultParser{
	DescribeSObjectResult sObjectResult;
	int customFieldsCount = -1;

	public DescribeSObjectResultParser(DescribeSObjectResult sObjectResult) {
		this.sObjectResult = sObjectResult;
	}

	public ActionOverride[] getActionOverrides() {
		// TODO Auto-generated method stub
		return sObjectResult.getActionOverrides();
	}

	public boolean getActivateable() {
		// TODO Auto-generated method stub
		return sObjectResult.getActivateable();
	}

	public ChildRelationship[] getChildRelationships() {
		// TODO Auto-generated method stub
		return sObjectResult.getChildRelationships();
	}

	public boolean getCompactLayoutable() {
		// TODO Auto-generated method stub
		return sObjectResult.getCompactLayoutable();
	}

	public boolean getCreateable() {
		// TODO Auto-generated method stub
		return sObjectResult.getCreateable();
	}

	public boolean getCustom() {
		// TODO Auto-generated method stub
		return sObjectResult.getCustom();
	}

	public boolean getCustomSetting() {
		// TODO Auto-generated method stub
		return sObjectResult.getCustomSetting();
	}

	public boolean getDeletable() {
		// TODO Auto-generated method stub
		return sObjectResult.getDeletable();
	}

	public boolean getDeprecatedAndHidden() {
		// TODO Auto-generated method stub
		return sObjectResult.getDeprecatedAndHidden();
	}

	public boolean getFeedEnabled() {
		// TODO Auto-generated method stub
		return sObjectResult.getFeedEnabled();
	}

	public Field[] getFields() {
		// TODO Auto-generated method stub
		return sObjectResult.getFields();
	}

	public String getKeyPrefix() {
		// TODO Auto-generated method stub
		return sObjectResult.getKeyPrefix();
	}

	public String getLabel() {
		// TODO Auto-generated method stub
		return sObjectResult.getLabel();
	}

	public String getLabelPlural() {
		// TODO Auto-generated method stub
		return sObjectResult.getLabelPlural();
	}

	public boolean getLayoutable() {
		// TODO Auto-generated method stub
		return sObjectResult.getLayoutable();
	}

	public boolean getListviewable() {
		// TODO Auto-generated method stub
		return sObjectResult.getListviewable();
	}

	public boolean getLookupLayoutable() {
		// TODO Auto-generated method stub
		return sObjectResult.getLookupLayoutable();
	}

	public boolean getMergeable() {
		// TODO Auto-generated method stub
		return sObjectResult.getMergeable();
	}

	public String getName() {
		// TODO Auto-generated method stub
		return sObjectResult.getName();
	}

	public NamedLayoutInfo[] getNamedLayoutInfos() {
		// TODO Auto-generated method stub
		return sObjectResult.getNamedLayoutInfos();
	}

	public boolean getQueryable() {
		// TODO Auto-generated method stub
		return sObjectResult.getQueryable();
	}

	public RecordTypeInfo[] getRecordTypeInfos() {
		// TODO Auto-generated method stub
		return sObjectResult.getRecordTypeInfos();
	}

	public boolean getReplicateable() {
		// TODO Auto-generated method stub
		return sObjectResult.getReplicateable();
	}

	public boolean getRetrieveable() {
		// TODO Auto-generated method stub
		return sObjectResult.getRetrieveable();
	}

	public boolean getSearchLayoutable() {
		// TODO Auto-generated method stub
		return sObjectResult.getSearchLayoutable();
	}

	public boolean getSearchable() {
		// TODO Auto-generated method stub
		return sObjectResult.getSearchable();
	}

	public boolean getTriggerable() {
		// TODO Auto-generated method stub
		return sObjectResult.getTriggerable();
	}

	public boolean getUndeletable() {
		// TODO Auto-generated method stub
		return sObjectResult.getUndeletable();
	}

	public boolean getUpdateable() {
		// TODO Auto-generated method stub
		return sObjectResult.getUpdateable();
	}

	public String getUrlDetail() {
		// TODO Auto-generated method stub
		return sObjectResult.getUrlDetail();
	}

	public String getUrlEdit() {
		// TODO Auto-generated method stub
		return sObjectResult.getUrlEdit();
	}

	public String getUrlNew() {
		// TODO Auto-generated method stub
		return sObjectResult.getUrlNew();
	}

	public boolean isActivateable() {
		// TODO Auto-generated method stub
		return sObjectResult.isActivateable();
	}

	public boolean isCompactLayoutable() {
		// TODO Auto-generated method stub
		return sObjectResult.isCompactLayoutable();
	}

	public boolean isCreateable() {
		// TODO Auto-generated method stub
		return sObjectResult.isCreateable();
	}

	public boolean isCustom() {
		// TODO Auto-generated method stub
		return sObjectResult.isCustom();
	}

	public boolean isCustomSetting() {
		// TODO Auto-generated method stub
		return sObjectResult.isCustomSetting();
	}

	public boolean isDeletable() {
		// TODO Auto-generated method stub
		return sObjectResult.isDeletable();
	}

	public boolean isDeprecatedAndHidden() {
		// TODO Auto-generated method stub
		return sObjectResult.isDeprecatedAndHidden();
	}

	public boolean isFeedEnabled() {
		// TODO Auto-generated method stub
		return sObjectResult.isFeedEnabled();
	}

	public boolean isLayoutable() {
		// TODO Auto-generated method stub
		return sObjectResult.isLayoutable();
	}

	public boolean isListviewable() {
		// TODO Auto-generated method stub
		return sObjectResult.isListviewable();
	}

	public boolean isLookupLayoutable() {
		// TODO Auto-generated method stub
		return sObjectResult.isLookupLayoutable();
	}

	public boolean isMergeable() {
		// TODO Auto-generated method stub
		return sObjectResult.isMergeable();
	}

	public boolean isQueryable() {
		// TODO Auto-generated method stub
		return sObjectResult.isQueryable();
	}

	public boolean isReplicateable() {
		// TODO Auto-generated method stub
		return sObjectResult.isReplicateable();
	}

	public boolean isRetrieveable() {
		// TODO Auto-generated method stub
		return sObjectResult.isRetrieveable();
	}

	public boolean isSearchLayoutable() {
		// TODO Auto-generated method stub
		return sObjectResult.isSearchLayoutable();
	}

	public boolean isSearchable() {
		// TODO Auto-generated method stub
		return sObjectResult.isSearchable();
	}

	public boolean isTriggerable() {
		// TODO Auto-generated method stub
		return sObjectResult.isTriggerable();
	}

	public boolean isUndeletable() {
		// TODO Auto-generated method stub
		return sObjectResult.isUndeletable();
	}

	public boolean isUpdateable() {
		// TODO Auto-generated method stub
		return sObjectResult.isUpdateable();
	}

	public void load(XmlInputStream __in, TypeMapper __typeMapper)
			throws IOException, ConnectionException {
		// TODO Auto-generated method stub
		sObjectResult.load(__in, __typeMapper);
	}

	public void setActionOverrides(IActionOverride[] actionOverrides) {
		// TODO Auto-generated method stub
		sObjectResult.setActionOverrides(actionOverrides);
	}

	public void setActivateable(boolean activateable) {
		// TODO Auto-generated method stub
		sObjectResult.setActivateable(activateable);
	}

	public void setChildRelationships(IChildRelationship[] childRelationships) {
		// TODO Auto-generated method stub
		sObjectResult.setChildRelationships(childRelationships);
	}

	public void setCompactLayoutable(boolean compactLayoutable) {
		// TODO Auto-generated method stub
		sObjectResult.setCompactLayoutable(compactLayoutable);
	}

	public void setCreateable(boolean createable) {
		// TODO Auto-generated method stub
		sObjectResult.setCreateable(createable);
	}

	public void setCustom(boolean custom) {
		// TODO Auto-generated method stub
		sObjectResult.setCustom(custom);
	}

	public void setCustomSetting(boolean customSetting) {
		// TODO Auto-generated method stub
		sObjectResult.setCustomSetting(customSetting);
	}

	public void setDeletable(boolean deletable) {
		// TODO Auto-generated method stub
		sObjectResult.setDeletable(deletable);
	}

	public void setDeprecatedAndHidden(boolean deprecatedAndHidden) {
		// TODO Auto-generated method stub
		sObjectResult.setDeprecatedAndHidden(deprecatedAndHidden);
	}

	public void setFeedEnabled(boolean feedEnabled) {
		// TODO Auto-generated method stub
		sObjectResult.setFeedEnabled(feedEnabled);
	}

	public void setFields(IField[] fields) {
		// TODO Auto-generated method stub
		sObjectResult.setFields(fields);
	}

	public void setKeyPrefix(String keyPrefix) {
		// TODO Auto-generated method stub
		sObjectResult.setKeyPrefix(keyPrefix);
	}

	public void setLabel(String label) {
		// TODO Auto-generated method stub
		sObjectResult.setLabel(label);
	}

	public void setLabelPlural(String labelPlural) {
		// TODO Auto-generated method stub
		sObjectResult.setLabelPlural(labelPlural);
	}

	public void setLayoutable(boolean layoutable) {
		// TODO Auto-generated method stub
		sObjectResult.setLayoutable(layoutable);
	}

	public void setListviewable(boolean listviewable) {
		// TODO Auto-generated method stub
		sObjectResult.setListviewable(listviewable);
	}

	public void setLookupLayoutable(boolean lookupLayoutable) {
		// TODO Auto-generated method stub
		sObjectResult.setLookupLayoutable(lookupLayoutable);
	}

	public void setMergeable(boolean mergeable) {
		// TODO Auto-generated method stub
		sObjectResult.setMergeable(mergeable);
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		sObjectResult.setName(name);
	}

	public void setNamedLayoutInfos(INamedLayoutInfo[] namedLayoutInfos) {
		// TODO Auto-generated method stub
		sObjectResult.setNamedLayoutInfos(namedLayoutInfos);
	}

	public void setQueryable(boolean queryable) {
		// TODO Auto-generated method stub
		sObjectResult.setQueryable(queryable);
	}

	public void setRecordTypeInfos(IRecordTypeInfo[] recordTypeInfos) {
		// TODO Auto-generated method stub
		sObjectResult.setRecordTypeInfos(recordTypeInfos);
	}

	public void setReplicateable(boolean replicateable) {
		// TODO Auto-generated method stub
		sObjectResult.setReplicateable(replicateable);
	}

	public void setRetrieveable(boolean retrieveable) {
		// TODO Auto-generated method stub
		sObjectResult.setRetrieveable(retrieveable);
	}

	public void setSearchLayoutable(boolean searchLayoutable) {
		// TODO Auto-generated method stub
		sObjectResult.setSearchLayoutable(searchLayoutable);
	}

	public void setSearchable(boolean searchable) {
		// TODO Auto-generated method stub
		sObjectResult.setSearchable(searchable);
	}

	public void setTriggerable(boolean triggerable) {
		// TODO Auto-generated method stub
		sObjectResult.setTriggerable(triggerable);
	}

	public void setUndeletable(boolean undeletable) {
		// TODO Auto-generated method stub
		sObjectResult.setUndeletable(undeletable);
	}

	public void setUpdateable(boolean updateable) {
		// TODO Auto-generated method stub
		sObjectResult.setUpdateable(updateable);
	}

	public void setUrlDetail(String urlDetail) {
		// TODO Auto-generated method stub
		sObjectResult.setUrlDetail(urlDetail);
	}

	public void setUrlEdit(String urlEdit) {
		// TODO Auto-generated method stub
		sObjectResult.setUrlEdit(urlEdit);
	}

	public void setUrlNew(String urlNew) {
		// TODO Auto-generated method stub
		sObjectResult.setUrlNew(urlNew);
	}

	public String toString() {
		// TODO Auto-generated method stub
		return sObjectResult.toString();
	}

	public void write(QName __element, XmlOutputStream __out,
			TypeMapper __typeMapper) throws IOException {
		// TODO Auto-generated method stub
		sObjectResult.write(__element, __out, __typeMapper);
	}

	public String getAllFieldsForQuery() {
		StringBuilder queryFields = new StringBuilder();
		for (Field field : sObjectResult.getFields()) {
			queryFields.append(field.getName());
			queryFields.append(",");
		}
		return queryFields.toString().substring(0,
				queryFields.toString().length() - 1);
	}
	
	public Field[] getCustomFields(){
		ArrayList<Field> fields = new ArrayList<Field>();
		for (Field field : sObjectResult.getFields()) {
			if (field.getCustom()) {
				fields.add(field);
			}
		}
		
		Field customFields[] = new Field[fields.size()];  
		customFields = fields.toArray(customFields);
		return customFields;
	}

	public String getCustomFieldsForQuery() {
		StringBuilder queryFields = new StringBuilder();
		int customFieldCount = 0;
		for (Field field : sObjectResult.getFields()) {
			if (field.getCustom()) {
				queryFields.append(field.getName());
				queryFields.append(",");
				customFieldCount++;
			}
		}
		if (this.customFieldsCount <= 0)
			this.customFieldsCount = customFieldCount;

		return queryFields.toString().substring(0,
				queryFields.toString().length() - 1);
	}

	public int getCustomFieldsCount() {
		if (this.customFieldsCount == -1) {
			int customFieldCount = 0;
			for (Field field : sObjectResult.getFields()) {
				if (field.getCustom()) {
					customFieldCount++;
				}
			}
			this.customFieldsCount = customFieldCount;
		}
		return this.customFieldsCount;
	}
}
