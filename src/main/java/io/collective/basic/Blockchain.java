package io.collective.basic;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private final List<Block> blocks = new ArrayList<>();

    public boolean isEmpty() {
        return blocks.isEmpty();
    }

    public void add(Block block) {
        blocks.add(block);
    }

    public int size() {
        return blocks.size();
    }

    public boolean isValid() throws NoSuchAlgorithmException {
        for (int i = 0; i < blocks.size(); i++) {
            Block currentBlock = blocks.get(i);

            // Check if the block is mined
            if (!isMined(currentBlock)) {
                return false;
            }

            if (i > 0) {
                Block previousBlock = blocks.get(i - 1);

                // Check if the current block's previous hash matches the previous block's hash
                if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                    return false;
                }
            }

            // Check if the current block's hash is correctly calculated
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }
        }
        return true;
    }

    public static Block mine(Block block) throws NoSuchAlgorithmException {
        int nonce = 0;
        Block mined = new Block(block.getPreviousHash(), block.getTimestamp(), nonce);
        while (!isMined(mined)) {
            nonce++;
            mined = new Block(block.getPreviousHash(), block.getTimestamp(), nonce);
        }
        return mined;
    }

    public static boolean isMined(Block minedBlock) {
        return minedBlock.getHash().startsWith("00");
    }
}
