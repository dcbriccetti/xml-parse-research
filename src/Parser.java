import java.io.Reader;
import java.util.Optional;

public interface Parser {
  Optional<String> parse(Reader reader, String attributeName);
}
