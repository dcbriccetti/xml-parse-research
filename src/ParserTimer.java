import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParserTimer {
  private static final int REPS = 1_000;
  private static final PrintStream out = System.out;

  public static void main(String[] args) throws IOException {
    List<String> filenames = Arrays.asList(
        "forminst1.xml",
        "forminst1-formatted.xml",
        "forminst1-long.xml");
    NumberFormat nf = NumberFormat.getNumberInstance();
    List<Parser> parsers = Arrays.asList(new StreamReaderParser(), new KxmlParser());

    StringBuilder tsv = new StringBuilder("File\tIteration\t");
    tsv.append(parsers.stream().map(ParserTimer::parserName).collect(Collectors.joining("\t"))).append('\n');
    out.println("Parsing each file from an already in-memory StringReader " + nf.format(REPS) + " times");
    for (String filename: filenames) {
      for (int n = 1; n <= 3; ++n) {
        out.printf("\nParsing file %s, iteration %d\n", filename, n);
        tsv.append(filename).append('\t').append(n).append('\t');
        String xml = new String(Files.readAllBytes(Paths.get(filename)));
        for (Parser parser: parsers) {
          long t1 = System.currentTimeMillis();
          for (int i = 0; i < REPS; ++i) {
            StringReader sr = new StringReader(xml);
            parser.parse(sr, "submissionDate");
          }
          long ms = System.currentTimeMillis() - t1;
          out.printf("%s: %s ms\n", parserName(parser), nf.format(ms));
          tsv.append(ms).append('\t');
        }
        tsv.append('\n');
      }
    }
    out.println(tsv.toString());
  }

  private static String parserName(Parser parser) {
    return parser.getClass().getSimpleName();
  }
}
