import org.kxml2.io.KXmlParser;
import org.kxml2.kdom.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.Reader;
import java.util.Optional;

public class KxmlParser implements Parser {
  @Override public Optional<String> parse(Reader form, String attributeName) {
    try {
      Document document = new Document();
      KXmlParser parser = new KXmlParser();
      parser.setInput(form);
      parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
      document.parse(parser);
      return Optional.ofNullable(document.getRootElement().getAttributeValue(null, attributeName));
    } catch (XmlPullParserException | IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }
}
