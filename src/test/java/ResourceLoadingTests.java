import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ResourceLoadingTests {

    @ParameterizedTest(name = "{0} contains {1} when read from src/test/resources")
    @MethodSource("argProvider")
    void srcTestResourcesAddTest(String resource, String expected) throws IOException {
        String fileContents = Files.readString(Path.of("src/test/resources/" + resource));
        assertEquals(expected, fileContents, "This test is expected to fail with run on a remote executor");
    }

    @ParameterizedTest(name = "{0} contains {1} when read from build/resources/test")
    @MethodSource("argProvider")
    void buildResourcesTestAddTest(String resource, String expected) throws IOException {
        String fileContents = Files.readString(Path.of("build/resources/test/" + resource));
        assertEquals(expected, fileContents);
    }

    @ParameterizedTest(name = "{0} contains {1} when loaded via classloader")
    @MethodSource("argProvider")
    void classpathResourceLoadingAddTest(String resource, String expected) throws IOException, URISyntaxException {
        Path path = Paths.get(this.getClass().getClassLoader().getResource(resource).toURI());
        String fileContents = Files.readString(path);
        assertEquals(expected, fileContents);
    }

    static Stream<Arguments> argProvider() {
        return Stream.of(
                arguments("test.txt", "foo"),
                arguments("x/y/nested.txt", "bar")
        );
    }

}