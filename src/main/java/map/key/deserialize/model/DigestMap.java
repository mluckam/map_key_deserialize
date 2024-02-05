package map.key.deserialize.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.cloud.tools.jib.api.DescriptorDigest;
import java.io.IOException;
import java.security.DigestException;
import java.util.Map;
import java.util.Objects;

public class DigestMap {

    private final Map<DescriptorDigest, String> descriptorDigestStringMap;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public DigestMap(@JsonProperty("descriptorDigestStringMap") Map<DescriptorDigest,
                                    String> descriptorDigestStringMap) {
        this.descriptorDigestStringMap = descriptorDigestStringMap;
    }

//    @JsonDeserialize(keyUsing = DigestMapDeserializer.class)
    public Map<DescriptorDigest, String> getDescriptorDigestStringMap() {
        return descriptorDigestStringMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigestMap digestMap = (DigestMap) o;
        return Objects.equals(descriptorDigestStringMap, digestMap.descriptorDigestStringMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptorDigestStringMap);
    }

    @Override
    public String toString() {
        return "DigestMap{" +
                "descriptorDigestStringMap=" + descriptorDigestStringMap +
                '}';
    }

    private static class DigestMapDeserializer extends KeyDeserializer {

        @Override
        public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
            try {
                return DescriptorDigest.fromDigest(key);
            } catch (DigestException e) {
                throw new IOException(e);
            }
        }
    }
}
