import org.kxml2.kdom.Document;
import org.xmlpull.v1.XmlPullParserException;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {

  public static void main(String[] args) throws IOException, XMLStreamException, XmlPullParserException {
    parse1();
    parse2();
  }

  private static void parse2() throws IOException, XmlPullParserException {
    for (int i = 0; i < 1; ++i) {
      Document d = KxmlParser.parse(Paths.get("forminst1.xml"));
      System.out.println(d.getRootElement().getAttributeValue(null, "submissionDate"));
    }
  }

  private static void parse1() throws XMLStreamException, FileNotFoundException {
    RootAttributesParser p = new RootAttributesParser();
    for (int i = 0; i < 1_000; ++i)
      p.getRootAttributes("forminst1.xml");
  }

}
