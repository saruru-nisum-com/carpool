package com.nisum.carpool.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.nisum.carpool.service.dto.CPCancellRideReasons;
import com.nisum.carpool.service.dto.ReasonsDTO;

public class CPCancellationReasons extends DefaultHandler {

	private CPCancellRideReasons cpCanRideReasonsObj;
	private String temp;
	static Map<Integer, String> cpResMapObj = new HashMap<Integer, String>();
	static List<ReasonsDTO> reasonsdtolist  = new ArrayList<ReasonsDTO>();
	private static Logger appLogger = Logger.getLogger(CPCancellationReasons.class);
	
	
	public static List<ReasonsDTO> readRiderStatusReasonCodes() {
		appLogger.info("Start of readRiderStatusReasonCodes() method in CPCancellationReasons");
		try {
			// Create a "parser factory" for creating SAX parsers
			SAXParserFactory spfac = SAXParserFactory.newInstance();

			// Now use the parser factory to create a SAXParser object
			SAXParser sp = spfac.newSAXParser();

			// Create an instance of this class; it defines all the handler methods
			CPCancellationReasons handler = new CPCancellationReasons();

			// Finally, tell the parser to parse the input and notify the handler
			//com.nisum.carpool.service.dto
			System.out.println("Current directory "+System.getProperty("user.dir")+"/src/main/resources");
			sp.parse(System.getProperty("user.dir")+"/src/main/resources/CPRiderStatusReasons.xml", handler);

			Set<Entry<Integer, String>> cpResSetObj = cpResMapObj.entrySet();
			Iterator<Entry<Integer, String>> itrObj = cpResSetObj.iterator();
			while (itrObj.hasNext()) {
				Entry<Integer, String> entryObj = (Entry<Integer, String>) itrObj.next();
				appLogger.debug("Key " + entryObj.getKey() + " Value " + entryObj.getValue());
				System.out.println("Key " + entryObj.getKey() + " Value " + entryObj.getValue());
			}
		} catch (IOException ioException) {

			appLogger.error("Exception occured into readRiderStatusReasonCodes() method in CPCancellationReasons ", ioException.fillInStackTrace());
			
		} catch (SAXException saxException) {

			appLogger.error("Exception occured into readRiderStatusReasonCodes() method in CPCancellationReasons ", saxException.fillInStackTrace());
			
		} catch (ParserConfigurationException pcException) {

			appLogger.error("Exception occured into readRiderStatusReasonCodes() method in CPCancellationReasons ", pcException.fillInStackTrace());
		}
		appLogger.info("End of readRiderStatusReasonCodes() method in CPCancellationReasons");
		return reasonsdtolist;
	}

	/*
	 * When the parser encounters plain text (not XML elements), it calls(this
	 * method, which accumulates them in a string buffer
	 */
	public void characters(char[] buffer, int start, int length) {
		temp = new String(buffer, start, length);
	}

	/*
	 * Every time the parser encounters the beginning of a new element, it calls
	 * this method, which resets the string buffer
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		temp = "";
		if (qName.equalsIgnoreCase("RejectionReason")) {
			cpCanRideReasonsObj = new CPCancellRideReasons();
			cpCanRideReasonsObj.setRejectionType((attributes.getValue("type")));

		}
	}

	/*
	 * When the parser encounters the end of an element, it calls this method
	 */
	/*
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("RejectionReason")) {
			cpResMapObj.put(cpCanRideReasonsObj.getReasonCode(), cpCanRideReasonsObj.getReasonName());
		} else if (qName.equalsIgnoreCase("ReasonName")) {
			cpCanRideReasonsObj.setReasonName(temp);
		} else if (qName.equalsIgnoreCase("ReasonCode")) {
			cpCanRideReasonsObj.setReasonCode(Integer.valueOf(temp));
		}

	}*/
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("RejectionReason")) {
			
			ReasonsDTO rdto = new ReasonsDTO();
			rdto.setId(cpCanRideReasonsObj.getReasonCode());
			rdto.setName(cpCanRideReasonsObj.getReasonName());
			
			reasonsdtolist.add(rdto);
			
		} else if (qName.equalsIgnoreCase("ReasonName")) {
			cpCanRideReasonsObj.setReasonName(temp);
		} else if (qName.equalsIgnoreCase("ReasonCode")) {
			cpCanRideReasonsObj.setReasonCode(Integer.valueOf(temp));
		}

	}
	
	
}
