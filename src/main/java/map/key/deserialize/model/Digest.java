package map.key.deserialize.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.cloud.tools.jib.api.DescriptorDigest;
import java.util.Objects;

public class Digest {

    private final DescriptorDigest digest;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Digest(@JsonProperty("digest") DescriptorDigest digest) {
        this.digest = digest;
    }

    public DescriptorDigest getDigest() {
        return digest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Digest digest1 = (Digest) o;
        return Objects.equals(digest, digest1.digest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(digest);
    }
}
