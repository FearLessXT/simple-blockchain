package io.collective.basic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private final String previousHash;
    private final long timestamp;
    private final int nonce;
    private final String hash;

    public Block(String previousHash, long timestamp, int nonce) throws NoSuchAlgorithmException {
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.hash = calculateHash();
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getNonce() {
        return nonce;
    }

    public String getHash() {
        return hash;
    }

    public String calculateHash() throws NoSuchAlgorithmException {
        String input = previousHash + timestamp + nonce;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes());
        return String.format("%064x", new BigInteger(1, hashBytes));
    }
}
