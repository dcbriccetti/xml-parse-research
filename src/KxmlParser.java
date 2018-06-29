import org.kxml2.io.KXmlParser;
import org.kxml2.kdom.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class KxmlParser {
  static Document parse(Path submission) throws IOException, XmlPullParserException {
    try (InputStream is = Files.newInputStream(submission);
         InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8)) {
      Document tempDoc = new Document();
      KXmlParser parser = new KXmlParser();
      parser.setInput(isr);
      parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
      tempDoc.parse(parser);
      return tempDoc;
    } catch (IOException | XmlPullParserException e) {
      throw e;
    }
  }
}
