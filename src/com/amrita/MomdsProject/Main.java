package com.amrita.MomdsProject;

import com.amrita.MomdsProject.dataModels.Tuples;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Integer> similaritySet = new HashSet<>();
        similaritySet.add(25);
        similaritySet.add(159);
        similaritySet.add(585);

        Tuples[] hashCoeffs = new Tuples[2];
        Tuples hash1 = new Tuples(2, 1);
        Tuples hash2 = new Tuples(3, 1);
        hashCoeffs[0] = hash1;
        hashCoeffs[1] = hash2;

        BloomFilter bloomFilter = new BloomFilter(similaritySet, hashCoeffs);

        bloomFilter.generateBitArrayFromSimilaritySet();
        System.out.println(bloomFilter.checkIfElementBelongsToSet(118));
    }
}