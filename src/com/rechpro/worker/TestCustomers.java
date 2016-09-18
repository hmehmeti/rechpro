package com.rechpro.worker;

import java.util.HashMap;

import com.rechpro.persistence.Customer;

public class TestCustomers {

	public Customer getUser1(){
		HashMap<Enum, String> customParameterList = new HashMap<Enum, String>();
		customParameterList.put(UserParameters.ID, "12345678");
		customParameterList.put(UserParameters.SEX, "Herr");
		customParameterList.put(UserParameters.FIRSTNAME ,"Hasan");
		customParameterList.put(UserParameters.LASTNAME ,"Mehmeti");
		customParameterList.put(UserParameters.STREET ,"Gumbarastr.");
		customParameterList.put(UserParameters.NO ,"24.");
		customParameterList.put(UserParameters.POSTCODE ,"76555");
		customParameterList.put(UserParameters.CITY ,"Stuttgart");
		customParameterList.put(UserParameters.COUNTRY ,"Kosova");
		customParameterList.put(UserParameters.PHONE ,"8799978788");
		customParameterList.put(UserParameters.MOBILE_PHONE ,"0049 176 999939393");
		customParameterList.put(UserParameters.FAX ,"");
		customParameterList.put(UserParameters.EMAIL ,"mehmeti@tirro.wirro");
		customParameterList.put(UserParameters.BIRTHDAY ,"12.9.1990");
		customParameterList.put(UserParameters.BANK_NO ,"123456");
		customParameterList.put(UserParameters.BLZ ,"8788788");
		customParameterList.put(UserParameters.IBAN ,"DE 89384958393");
		customParameterList.put(UserParameters.BIC_NO ,"");
		
		Customer custom = new Customer(customParameterList);
		return custom;
	}
	
	public Customer getUser2(){
		HashMap<Enum, String> customParameterList = new HashMap<Enum, String>();
		customParameterList.put(UserParameters.ID, "888779678");
		customParameterList.put(UserParameters.SEX, "Frau");
		customParameterList.put(UserParameters.FIRSTNAME ,"Hasaniye");
		customParameterList.put(UserParameters.LASTNAME ,"Mehmeti");
		customParameterList.put(UserParameters.STREET ,"Laplopstr.");
		customParameterList.put(UserParameters.NO ,"15.");
		customParameterList.put(UserParameters.POSTCODE ,"76555");
		customParameterList.put(UserParameters.CITY ,"Stuttgart");
		customParameterList.put(UserParameters.COUNTRY ,"Kosova");
		customParameterList.put(UserParameters.PHONE ,"879458788");
		customParameterList.put(UserParameters.MOBILE_PHONE ,"0049 176 98839393");
		customParameterList.put(UserParameters.FAX ,"");
		customParameterList.put(UserParameters.EMAIL ,"mehmetiye@tirro.wirro");
		customParameterList.put(UserParameters.BIRTHDAY ,"12.9.1990");
		customParameterList.put(UserParameters.BANK_NO ,"123456");
		customParameterList.put(UserParameters.BLZ ,"8788788");
		customParameterList.put(UserParameters.IBAN ,"DE 89384958393");
		customParameterList.put(UserParameters.BIC_NO ,"656556");
		
		Customer custom = new Customer(customParameterList);
		return custom;
	}
}
