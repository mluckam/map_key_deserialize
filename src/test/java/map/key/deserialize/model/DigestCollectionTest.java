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
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DigestCollectionTest {
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = JsonMapper.builder().build();
    }

    @Test
    void testDeserialize() throws IOException, DigestException {
        DigestCollection expected = getDigestCollection();
        DigestCollection actual = null;
        try (InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("digestCollection.json")) {
            actual = objectMapper.readValue(input, DigestCollection.class);
        }

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSerialize() throws URISyntaxException, IOException, DigestException {
        String expected = Files.readString(Paths.get(
                Thread.currentThread().getContextClassLoader()
                        .getResource("digestCollection.json").toURI()));

        String actual =
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                        getDigestCollection());

        Assertions.assertEquals(expected, actual);
    }

    private DigestCollection getDigestCollection() throws DigestException {
        Set<DescriptorDigest> set = new LinkedHashSet<>();
        set.add(DescriptorDigest.fromHash(
                "a3ed95caeb02ffe68cdd9fd84406680ae93d633cb16422d00e8a7c22955b46d4"));
        set.add(DescriptorDigest.fromHash(
                "612d056ba4f3953cb870408b85cc96bcf8207ee83d16fb92108065255f5eea21"));
        return  new DigestCollection(set);
    }
}
