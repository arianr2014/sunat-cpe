package io.github.carlosthe19916.core.keys;

import java.security.PublicKey;
import java.util.Map;

public interface PublicKeyLoader {

    Map<String, PublicKey> loadKeys() throws Exception;

}
