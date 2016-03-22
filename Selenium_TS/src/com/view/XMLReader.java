package com.view;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLReader extends DefaultHandler {
	static int numberLines = 0;
	static String indentation = "";
	static String displayText[] = new String[1000];

	static boolean displayBoolean;
	static String findNode;

	public static void main(final String args[]) {
		final XMLReader obj = new XMLReader();
		findNode = "MyCurrentCourses";
		obj.childLoop("./Conf/WebElements.xml");

		for (int index = 0; index < numberLines; index++) {
			System.out.println(displayText[index]);
		}
	}

	public void childLoop(final String uri) {
		final DefaultHandler saxHandler = this;
		final SAXParserFactory saxFactory = SAXParserFactory.newInstance();
		try {
			final SAXParser saxParser = saxFactory.newSAXParser();
			saxParser.parse(new File(uri), saxHandler);
		} catch (final Throwable t) {
		}
	}

	@Override
	public void startDocument() {
		if (displayBoolean) {
			displayText[numberLines] = indentation;
			displayText[numberLines] += "<?xml version=\"1.0\" encoding=\""
					+ "UTF-8" + "\"?>";
			numberLines++;
		}
	}

	@Override
	public void processingInstruction(final String target, final String data) {
		
		if (displayBoolean) {
			displayText[numberLines] = indentation;
			displayText[numberLines] += "<?";
			displayText[numberLines] += target;
			if (data != null && data.length() > 0) {
				displayText[numberLines] += ' ';
				displayText[numberLines] += data;
			}
			displayText[numberLines] += "?>";
			numberLines++;
		}
	}

	@Override
	public void startElement(final String uri, final String localName,
			final String qualifiedName, final Attributes attributes) {
		
		if (qualifiedName.equals(findNode)) {
			displayBoolean = true;
		}

		if (displayBoolean) {
			displayText[numberLines] = indentation;
			indentation += "    ";
			displayText[numberLines] += '<';
			displayText[numberLines] += qualifiedName;
			if (attributes != null) {
				final int numberAttributes = attributes.getLength();
				for (int loopIndex = 0; loopIndex < numberAttributes; loopIndex++) {
					displayText[numberLines] += ' ';
					displayText[numberLines] += attributes.getQName(loopIndex);
					displayText[numberLines] += "=\"";
					displayText[numberLines] += attributes.getValue(loopIndex);
					displayText[numberLines] += '"';
				}
			}
			displayText[numberLines] += '>';
			numberLines++;
		}
	}

	@Override
	public void characters(final char characters[], final int start, final int length) {
		if (displayBoolean) {
			final String characterData = (new String(characters, start, length))
					.trim();
			if (characterData.indexOf("\n") < 0 && characterData.length() > 0) {
				displayText[numberLines] = indentation;
				displayText[numberLines] += characterData;
				numberLines++;
			}
		}
	}

	@Override
	public void ignorableWhitespace(final char characters[], final int start, final int length) {
		if (displayBoolean) {
		}
	}

	@Override
	public void endElement(final String uri, final String localName, final String qualifiedName) {
		if (displayBoolean) {
			indentation = indentation.substring(0, indentation.length() - 4);
			displayText[numberLines] = indentation;
			displayText[numberLines] += "</";
			displayText[numberLines] += qualifiedName;
			displayText[numberLines] += '>';
			numberLines++;
		}
		if (qualifiedName.equals(findNode)) {
			displayBoolean = false;
		}
	}

	@Override
	public void warning(final SAXParseException exception) {
		System.err.println("Warning: " + exception.getMessage());
	}

	@Override
	public void error(final SAXParseException exception) {
		System.err.println("Error: " + exception.getMessage());
	}

	@Override
	public void fatalError(final SAXParseException exception) {
		System.err.println("Fatal error: " + exception.getMessage());
	}
}