package com.espl.zero.taghandler;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.espl.zero.sfdc.model.DataModel;
import com.espl.zero.sfdc.model.SFDCModel;
import com.espl.zero.util.enums.InputFieldType;
import com.espl.zero.util.enums.LayoutType;
import com.sforce.soap.partner.DescribeLayout;
import com.sforce.soap.partner.DescribeLayoutComponent;
import com.sforce.soap.partner.DescribeLayoutItem;
import com.sforce.soap.partner.DescribeLayoutRow;
import com.sforce.soap.partner.DescribeLayoutSection;
import com.sforce.soap.partner.Error;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.FieldType;
import com.sforce.soap.partner.PicklistEntry;

public class Layout extends TagSupport {
	private static final long serialVersionUID = 1L;
	private DescribeLayout layout;
	private String layoutType;
	private SFDCModel model;
	private DataModel dataModel;
	private Map<String, Field> fieldsMap;
	private int sectionIndex=0;
	private boolean bodyRendered = false;

	public void setLayout(DescribeLayout layout) {
		this.layout = layout;
	}

	public void setLayoutType(String layoutType) {
		this.layoutType = layoutType;
	}

	public void setModel(SFDCModel model) {
		this.model = model;
		this.fieldsMap = model.convertToMap(model.getAllFields());
	}

	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}

	@Override
	public int doStartTag() throws JspException {
		bodyRendered = false;
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doAfterBody() throws JspException {
		if(!bodyRendered){
			bodyRendered = true;
			JspWriter out = pageContext.getOut();
			try {
				sectionIndex=0;
				printErrors(out);
				generateIdField(out);
				if (layoutType.equalsIgnoreCase(LayoutType.DETAILS_LAYOUT)) {
					System.out.println("jaja--detail layout---");
					out.append("<table width=\"100%\"><tr><td>");
					for (DescribeLayoutSection section : layout
							.getDetailLayoutSections()) {
						System.out.println("--infor det"+section.getHeading());
						generateDetailsLayoutSection(out, section);
					}
					out.append("</td></tr></table>");
				} else if (layoutType.equalsIgnoreCase(LayoutType.EDIT_LAYOUT)) {
					System.out.println("jaja--edit layout---");
					out.append("<table width=\"100%\"><tr><td>");
					for (DescribeLayoutSection section : layout
							.getEditLayoutSections()) {
						
						System.out.println("--infor edit");
						generateEditLayoutSection(out, section);
					}
					out.append("</td></tr></table>");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return EVAL_BODY_AGAIN;
		}
		return EVAL_PAGE;
	}

	private void printErrors(JspWriter out) throws IOException {
		if(dataModel.getErrors().size()>0){
			out.append("<ul><b>Review all error messages below to correct your data.</b>");
			for (String error : dataModel.getErrors()) {
				out.append("<li class=\"error\">"+error+"</li>");
			}
			out.append("</ul>");
		}
	}
	private void generateIdField(JspWriter out) throws IOException{
		if(dataModel.getField().containsKey("Id")){
			String Id = dataModel.getField().get("Id");
			out.append("<input type=\"hidden\" name=\"field['Id']\" value=\""+Id+"\" />");
		}
	}
	private void generateDetailsLayoutSection(JspWriter out,DescribeLayoutSection section) throws IOException {
		
		
		int columns = section.getColumns() * 2;
		String tdSize = (100 / columns) + "%";
		
		String tableStart = "<table width=\"100%\">", 
				tableEnd = "</table>";
		String rowStart = "<tr>", 
				rowEnd = "</tr>";
		String rowHeadingStart = "<th align=\"right\" class=\"sectionHeader\" colspan=\"" + columns + "\">", 
				rowHeadingEnd = "</th>";
		String dataLabelStart = "<td align=\"right\" width=\""+tdSize+"\">",
				dataInputStart = "<td width=\""+tdSize+"\">",
				dataEnd = "</td>";
		
		String requiredInputHtml = "";
		if(sectionIndex==0){
			requiredInputHtml="<span class=\"requiredMessage\"><div class=\"requiredInput\"></div> &nbsp;= Required Information</span>";
			sectionIndex++;
		}

		// START GENERATING HTML
		out.append(tableStart);
		out.append(rowStart).append(rowHeadingStart)
				.append(section.getHeading()).append(requiredInputHtml).append(rowHeadingEnd)
				.append(rowEnd);
		for (DescribeLayoutRow row : section.getLayoutRows()) {
			
			System.out.println("------section.getLayoutRows()----"+section.getLayoutRows().length);
			out.append(rowStart);
			for (DescribeLayoutItem item : row.getLayoutItems()) {
				
				System.out.println("------section.row.getLayoutItems()()----"+row.getLayoutItems().length);
				for(DescribeLayoutComponent component : item.getLayoutComponents()){
					
					System.out.println("------section.row.item.getLayoutComponents()()()----"+item.getLayoutComponents().length);

					out.append(dataLabelStart);
					generateHtmlLabel(out,component, item.getLabel());
					System.out.println("--val--item.getLabel()----"+item.getLabel());
					out.append(dataEnd);
					out.append(dataInputStart);
					generateHtmlValue(out,component);
					out.append(dataEnd);
				}
				// IF 2 Columns and any one is blank then  
				if(item.getLabel().length()==0){
					out.append(dataLabelStart);out.append(dataEnd);
					out.append(dataInputStart);out.append(dataEnd);
				}
			}
			out.append(rowEnd);
		}
		out.append(tableEnd);
		
	}
	private void generateEditLayoutSection(JspWriter out,DescribeLayoutSection section) throws IOException {
		int columns = section.getColumns() * 2;
		String tdSize = (100 / columns) + "%";
		
		String tableStart = "<table width=\"100%\">", 
				tableEnd = "</table>";
		String rowStart = "<tr>", 
				rowEnd = "</tr>";
		String rowHeadingStart = "<th align=\"right\" class=\"sectionHeader\" colspan=\"" + columns + "\">", 
				rowHeadingEnd = "</th>";
		String dataLabelStart = "<td align=\"right\" width=\""+tdSize+"\">",
				dataInputStart = "<td width=\""+tdSize+"\">",
				dataEnd = "</td>";
		
		String requiredInputHtml = "";
		if(sectionIndex==0){
			requiredInputHtml="<span class=\"requiredMessage\"><div class=\"requiredInput\"></div> &nbsp;= Required Information</span>";
			sectionIndex++;
		}

		// START GENERATING HTML
		out.append(tableStart);
		out.append(rowStart).append(rowHeadingStart)
				.append(section.getHeading()).append(requiredInputHtml).append(rowHeadingEnd)
				.append(rowEnd);
		for (DescribeLayoutRow row : section.getLayoutRows()) {
			out.append(rowStart);
			for (DescribeLayoutItem item : row.getLayoutItems()) {
				for(DescribeLayoutComponent component : item.getLayoutComponents()){
					out.append(dataLabelStart);
					generateHtmlLabel(out,component, item.getLabel());
					out.append(dataEnd);
					out.append(dataInputStart);
					generateHtmlInput(out,component);
					out.append(dataEnd);
				}
				// IF 2 Columns and any one is blank then  
				if(item.getLabel().length()==0){
					out.append(dataLabelStart);out.append(dataEnd);
					out.append(dataInputStart);out.append(dataEnd);
				}
			}
			out.append(rowEnd);
		}
		out.append(tableEnd);

	}
	
	private void generateHtmlLabel(JspWriter out,DescribeLayoutComponent component,String labelValue) throws IOException{
		out.append("<label for=\"field['"+component.getValue()+"']\">"+labelValue+"</label>");
	}
	
	private void generateHtmlInput(JspWriter out,DescribeLayoutComponent component) throws IOException {
		if(fieldsMap.containsKey(component.getValue())){
			Field field = fieldsMap.get(component.getValue());
			switch (field.getType()) {
			case string:
			case phone:
			case email:
				generateInputText(out, component,field.getType());
				break;
			case picklist:
				generateInputPicklist(out,component,field);
				break;
			case reference:
				generateInputLookup(out,component,field);
				break;
			default:
				break;
			}
		}
	}
	
	
	private void generateHtmlValue(JspWriter out,DescribeLayoutComponent component) throws IOException {
		if(fieldsMap.containsKey(component.getValue())){
			Field field = fieldsMap.get(component.getValue());
	         	generateValue(out, component,field.getType());
		}
				
	}
	
	private void generateValue(JspWriter out,DescribeLayoutComponent component,FieldType inputType) throws IOException{
		Field field = model.getField(component.getValue());
		String value = (dataModel!=null && dataModel.getField().containsKey(component.getValue())) ? dataModel.getField().get(component.getValue()) : "";
		

		System.out.println("-----valuevalue-------"+value);
		out.append(""+value+"");
		//out.append("</div>");
	}
	
	
	private void generateInputText(JspWriter out,DescribeLayoutComponent component,FieldType inputType) throws IOException{
		Field field = model.getField(component.getValue());
		String value = (dataModel!=null && dataModel.getField().containsKey(component.getValue())) ? dataModel.getField().get(component.getValue()) : "";
		
//		out.append("<div style=\"position: relative;height: 100%;\">");
		out.append("<div class=\"input\">");
		if(field != null && field.getNillable()==false){
//			out.append("<div style=\"background-color:RED;position:absolute;left:-4px;width:3px;top:1px;bottom:1px;\"></div>");
			out.append("<div class=\"requiredInput\"></div>");
		}
		
		out.append(
				"<input type=\"text\" "
					 + "name=\"field['"+component.getValue()+"']\" "
					 + "class=\""+inputType.name()+"\" "
					 + "value=\""+value+"\" />");
		out.append("</div>");
	}
	private void generateInputPicklist(JspWriter out,DescribeLayoutComponent component,Field field) throws IOException{
		String value = (dataModel!=null && dataModel.getField().containsKey(component.getValue())) ? dataModel.getField().get(component.getValue()) : "";
		out.append("<select name=\"field['"+field.getName()+"']\">");
		out.append("<option value=\"\">--None--</option>");
		for (PicklistEntry entry : field.getPicklistValues()) {
			String selected = (value.equals(entry.getValue())) ? "selected=\"selected\"" : "";
			out.append("<option value=\""+entry.getValue()+"\" "+selected+" >"+entry.getLabel()+"</option>");
		}
		out.append("</select>");
	}
	private void generateInputLookup(JspWriter out,DescribeLayoutComponent component,Field field) throws IOException{
		String value = (dataModel!=null && dataModel.getField().containsKey(component.getValue())) ? dataModel.getField().get(component.getValue()) : "";
		String valueLabel = "";
		out.append("<div class=\"input\">");
		if(field != null && field.getNillable()==false){
			out.append("<div class=\"requiredInput\"></div>");
		}
		generateHidden(out, field, value);
		out.append(
				"<input type=\"text\" "
					 + "name=\"field['"+component.getValue().concat("_Name")+"']\" "
					 + "class=\""+field.getType().name()+"\" "
					 + "value=\""+valueLabel+"\" />");
		out.append("<a><button class=\"lookup\" type=\"button\" onclick=\"openPopup('http://www.salesforce.com');\">&#x1f50d;</button></a>");
		out.append("</div>");
	}
	private void generateHidden(JspWriter out,Field field,String value)throws IOException{
		out.append("<input type=\"hidden\" name=\"field['"+field.getName()+"']\" value=\""+value+"\" />");
	}
	
	
	
	
	
}
