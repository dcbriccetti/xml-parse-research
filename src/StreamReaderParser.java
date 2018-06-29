import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.Reader;
import java.util.Optional;

import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public class StreamReaderParser implements Parser {
  private final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

  @Override public Optional<String> parse(Reader ioReader, String attributeName) {
    Optional<String> result = Optional.empty();
    try {
      XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(ioReader);

      while (reader.hasNext()) {
        int eventCode = reader.next();
        if (eventCode == START_ELEMENT) {
          int c = reader.getAttributeCount();
          for (int i = 0; !result.isPresent() && i < c; ++i) {
            if (reader.getAttributeLocalName(i).equals(attributeName)) {
              result = Optional.of(reader.getAttributeValue(i));
            }
          }
          break;
        }
      }
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
    return result;
  }
}
