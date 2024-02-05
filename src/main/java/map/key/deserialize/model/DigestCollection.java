package map.key.deserialize.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.cloud.tools.jib.api.DescriptorDigest;
import java.util.Objects;
import java.util.Set;

public class DigestCollection {

    private final Set<DescriptorDigest> digestCollection;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public DigestCollection(@JsonProperty("digestCollection") Set<DescriptorDigest> digestCollection) {
        this.digestCollection = digestCollection;
    }

    public Set<DescriptorDigest> getDigestCollection() {
        return digestCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigestCollection that = (DigestCollection) o;
        return Objects.equals(digestCollection, that.digestCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(digestCollection);
    }

    @Override
    public String toString() {
        return "DigestCollection{" +
                "digestCollection=" + digestCollection +
                '}';
    }
}
