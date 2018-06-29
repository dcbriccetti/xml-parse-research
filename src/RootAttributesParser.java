import com.sun.xml.internal.stream.events.StartElementEvent;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RootAttributesParser {
  private final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

  Map<String, String> getRootAttributes(String fileName) throws XMLStreamException, FileNotFoundException {
    XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileReader(fileName));
    while (reader.hasNext()) {
      XMLEvent event = reader.nextEvent();
      if (event instanceof StartElementEvent) {
        Map<String, String> attributes = new HashMap<>();
        Iterator<Attribute> attrIter = ((StartElementEvent) event).getAttributes();
        while (attrIter.hasNext()) {
            Attribute a = attrIter.next();
            attributes.put(a.getName().getLocalPart(), a.getValue());
        }
        return attributes;
      }
    }
    return Collections.emptyMap();
  }
}
