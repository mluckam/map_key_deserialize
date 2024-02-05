package map.key.deserialize.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.cloud.tools.jib.api.DescriptorDigest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DigestTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = JsonMapper.builder().build();
    }

    @Test
    void testDeserialize() throws IOException, DigestException {
        Digest expected = getDigest();
        Digest actual = null;
        try (InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("digest.json")) {
            actual = objectMapper.readValue(input, Digest.class);
        }

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSerialize() throws URISyntaxException, IOException, DigestException {
        String expected = Files.readString(Paths.get(
                Thread.currentThread().getContextClassLoader()
                        .getResource("digest.json").toURI()));

        String actual =
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(getDigest());

        Assertions.assertEquals(expected, actual);
    }

    private Digest getDigest() throws DigestException {
        return  new Digest(DescriptorDigest.fromHash(
                "a3ed95caeb02ffe68cdd9fd84406680ae93d633cb16422d00e8a7c22955b46d4"));
    }
}
