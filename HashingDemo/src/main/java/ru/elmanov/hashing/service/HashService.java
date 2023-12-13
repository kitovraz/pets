package ru.elmanov.hashing.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

@Slf4j
@Service
public class HashService {

    public static final String SALT = "slat";

    @SneakyThrows
    public String hash(byte[] bytes) {
        final var salt = createSalt();

        final var messageDigest = MessageDigest.getInstance("SHA3-512");

        messageDigest.update(SALT.getBytes(StandardCharsets.UTF_8));
        byte[] digest = messageDigest.digest(bytes);
        log.info("BIN digest: {}", digest);

        final var hexDigest = toHex(digest);
        log.info("BIN digest: {}", hexDigest);
        return hexDigest;
    }

    @SneakyThrows
    private byte[] createSalt() {
        final var secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        final var bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        return bytes;
    }

    private String toHex(byte[] value) {
        return new String(Hex.encode(value));
    }
}
