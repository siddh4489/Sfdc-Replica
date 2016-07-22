package com.espl.zero.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.espl.zero.sfdc.model.DataModel;
import com.espl.zero.sfdc.model.Supplier;
import com.sforce.soap.partner.DeleteResult;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.FieldType;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.UpsertResult;
import com.sforce.soap.partner.sobject.SObject;

@Controller
@RequestMapping(value = "/employees")
@SessionAttributes("supplier")
public class SupplierController {
	@Autowired
	private Supplier supplier;
	@Autowired
	private Environment environment;

	@RequestMapping(value = { "", "/list" }, method = RequestMethod.GET)
	public ModelAndView index(Model model) {
		model.addAttribute("supplier", supplier);
		
		SObject[] supplierResords = null;
		String SOQL = "SELECT " + supplier.getAllFieldsForQuery() + " FROM " + Supplier.API_NAME;
		QueryResult queryResult = supplier.query(SOQL);
		System.out.println("SOQL : " + SOQL);
		boolean done = false;
		if (queryResult.getSize() > 0) {
			while (!done) {
				supplierResords = queryResult.getRecords();
				if (queryResult.isDone()) {
					done = true;
				} else {
					queryResult = supplier.queryMore(queryResult.getQueryLocator());
				}
			}
			System.out.println("SUPPLIER RECORDS :");
			for (SObject sObject : supplierResords) {
				for (Field field : supplier.getCustomFields()) {
//					if(field.getType()==FieldType.reference && sObject.getField(field.getRelationshipName())!=null){
//						XmlObject value = (XmlObject) sObject.getField(field.getRelationshipName());
//						sObject.setField(field.getName(), value.getChild("Name").getValue());
//					}
					System.out.println("supplier['"+field.getName()+"'] :" + sObject.getField(field.getName()));
					if(field.getType()==FieldType.reference){
						System.out.println("supplier['"+field.getName()+"_Name"+"'] :" + sObject.getField(field.getName()+"_Name"));
					}
				} 
				System.out.println("\n");
			}
			model.addAttribute("supplierResords", supplierResords);
			model.addAttribute("supplierResordsCount",supplierResords.length);
		}
		return new ModelAndView("supplier/list");
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(Model model){
		model.addAttribute("supplier", supplier);
		model.addAttribute("dataModel", new DataModel());
		return new ModelAndView("supplier/create");
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute("dataModel") DataModel dataModel, Model model){
		dataModel.validate(supplier);
		if(dataModel.isValid()){											// VALID
			UpsertResult[] results = supplier.Save(dataModel);
			if(results[0].isSuccess())
				return new ModelAndView("redirect:/employees");				// SUCCESS
			else
				dataModel.setErrors(results[0].getErrors());				// ERROR
		}
		model.addAttribute("dataModel", dataModel);
		return new ModelAndView("supplier/create");
	}
	
	@RequestMapping(value="/delete/{Id}",method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable("Id") String Id,Model model){
		model.addAttribute("supplier", supplier);
		
													// VALID
			//UpsertResult[] results = supplier.(model);
		
		//DeleteResult[] = supplier.delete(ID[] Id);

		return new ModelAndView("supplier/edit");
	}
	
	@RequestMapping(value="/edit/{Id}",method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable("Id") String Id,Model model){
		model.addAttribute("supplier", supplier);
		
		String SOQL = "SELECT " + supplier.getAllFieldsForQuery() + " FROM " + Supplier.API_NAME + " WHERE Id = '"+Id+"'";
		QueryResult queryResult = supplier.query(SOQL);
		
		if (queryResult.getSize() > 0) {
			SObject supplierRecord = queryResult.getRecords()[0];
			DataModel dataModel = new DataModel();
			dataModel.setFieldData(supplierRecord, supplier);
			model.addAttribute("dataModel", dataModel);
		}

		return new ModelAndView("supplier/edit");
	}
	
	
	
	@RequestMapping(value="/details/{Id}",method=RequestMethod.GET)
	public ModelAndView details(@PathVariable("Id") String Id,Model model){
		model.addAttribute("supplier", supplier);
		
		System.out.println(Supplier.API_NAME+"-------------details id=---------------"+Id);
		String SOQL = "SELECT " + supplier.getAllFieldsForQuery() + " FROM " + Supplier.API_NAME + " WHERE Id = '"+Id+"'";
		QueryResult queryResult = supplier.query(SOQL);
		System.out.println("----size---"+queryResult.getSize());
		if (queryResult.getSize() > 0) {
			System.out.println("---queryResult.getRecords()[0]---"+queryResult.getRecords()[0]);
			SObject supplierRecord = queryResult.getRecords()[0];
			DataModel dataModel = new DataModel();
			dataModel.setFieldData(supplierRecord, supplier);
			model.addAttribute("dataModel", dataModel);
		}

		return new ModelAndView("supplier/details");
	}
	
	
	
	@RequestMapping(value="/edit/{Id}",method=RequestMethod.POST)
	public ModelAndView editPost(@PathVariable("Id") String Id,@ModelAttribute("dataModel") DataModel dataModel, Model model){
		dataModel.validate(supplier); 
		if(dataModel.isValid()){											// VALID
			if(dataModel.isValid(Id)){
				UpsertResult[] results = supplier.Save(dataModel);
				if(results[0].isSuccess())
					return new ModelAndView("redirect:/employees");				// SUCCESS
				else
					dataModel.setErrors(results[0].getErrors());				// ERROR
			}else{
				return new ModelAndView("redirect:/employees");					// ID MISSMATCH ERROR...
			}
		}
		model.addAttribute("dataModel", dataModel);
		return new ModelAndView("supplier/edit");
	}
	
}
