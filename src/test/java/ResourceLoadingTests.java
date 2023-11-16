import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceLoadingTests {

    private static final String EXPECTED = "foo";

    @Test
    void srcTestResourcesAddTest() throws IOException {
        String fileContents = Files.readString(Path.of("src/test/resources/test.txt"));
        assertEquals(EXPECTED, fileContents, "This test is expected to fail with run on a remote executor");
    }

    @Test
    void buildResourcesTestAddTest() throws IOException {
        String fileContents = Files.readString(Path.of("build/resources/test/test.txt"));
        assertEquals(EXPECTED, fileContents);
    }

    @Test
    void classpathResourceLoadingAddTest() throws IOException, URISyntaxException {
        Path path = Paths.get(this.getClass().getClassLoader().getResource("test.txt").toURI());
        String fileContents = Files.readString(path);
        assertEquals(EXPECTED, fileContents);
    }

}