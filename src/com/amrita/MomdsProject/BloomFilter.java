package com.amrita.MomdsProject;

import com.amrita.MomdsProject.dataModels.Tuples;

import java.util.ArrayList;
import java.util.Set;

public class BloomFilter {
    private int[] BitArray;
    private Set<Integer> similaritySet;
    private Tuples[] HashFunctionCoeff;
    private int modForHashFunction;

    // Bloom filter
    public BloomFilter(Set<Integer> similaritySet, Tuples[] HashFunctionCoeff){
        this.similaritySet = similaritySet;
        this.HashFunctionCoeff = HashFunctionCoeff;
    }

    public String generateBitArrayFromSimilaritySet(){
        modForHashFunction = calculateMod();
        BitArray = new int[modForHashFunction];
        //init the bit array with all 0
        for(int i = 0; i < modForHashFunction; i++){
            BitArray[i] = 0;
        }

        for(int element: similaritySet){
            String binary = convertIntegerToBinary(element);
            int[] evenOdd = countOnesInOddPlaces(binary);
            //assuming x as the 1's at even places for hash function - 1
            int hash1 = (HashFunctionCoeff[0].get_a() * evenOdd[0] + HashFunctionCoeff[0].get_b() ) % modForHashFunction;
            int hash2 = (HashFunctionCoeff[1].get_a() * evenOdd[1] + HashFunctionCoeff[1].get_b() ) % modForHashFunction;

            BitArray[hash1] = 1;
            BitArray[hash2] = 1;
        }

        // read bitarray int and conv into string
        String bitArray = "";
        for(int i = 0; i < BitArray.length; i++){
            bitArray += String.valueOf(BitArray[i]);
        }
        return bitArray;
    }

    public Boolean checkIfElementBelongsToSet(int element){

        int [] bitArrayElement = new int[modForHashFunction];
        //init the bit array with all 0
        for(int i = 0; i < modForHashFunction; i++){
            bitArrayElement[i] = 0;
        }

        String binary = convertIntegerToBinary(element);
        int[] evenOdd = countOnesInOddPlaces(binary);
        //assuming x as the 1's at even places for hash function - 1
        int hash1 = (HashFunctionCoeff[0].get_a() * evenOdd[0] + HashFunctionCoeff[0].get_b() ) % modForHashFunction;
        int hash2 = (HashFunctionCoeff[1].get_a() * evenOdd[1] + HashFunctionCoeff[1].get_b() ) % modForHashFunction;

        bitArrayElement[hash1] = 1;
        bitArrayElement[hash2] = 1;

        ArrayList<Integer> allOnePositions = getAllOnePositionsBitArray(bitArrayElement);
        for(int position: allOnePositions){
            if(BitArray[position] != 1){
                return false;
            }
        }
        return true;
    }

    private ArrayList<Integer> getAllOnePositionsBitArray(int[] bitArray){
        ArrayList<Integer> positions = new ArrayList<>();
        for(int i = 0; i < bitArray.length; i++){
            if(bitArray[i] == 1){
                positions.add(i);
            }
        }
        return positions;
    }
    private int[] countOnesInOddPlaces(String binary){
        int even = 0;
        int odd = 0;
        char[] binaryCharArray = binary.toCharArray();
        for(int i = 0; i < binary.length(); i++){
            if(i % 2 == 0 && binaryCharArray[i] == '1'){
                even++;
            }else if(i % 2 != 0 && binaryCharArray[i] == '1'){
                odd++;
            }
        }
        return new int[]{even, odd};
    }
    private int findMaxValue(int[] similaritySet){
        int max = -1111111;
        for(int i = 0; i < similaritySet.length; i++){
            if (similaritySet[i] > max){
                max = similaritySet[i];
            }
        }
        return max;
    }

    private int calculateMod(){
        int[] dummyArray = new int[similaritySet.size()];
        int i = 0;
        for(int val: similaritySet){
            dummyArray[i] = val;
            i++;
        }
        int maxInSimilaritySet = findMaxValue(dummyArray);
        String binaryMax = convertIntegerToBinary(maxInSimilaritySet);
        return binaryMax.length();
    }

    private String convertIntegerToBinary(int decimal){
        // Function to print integer to binary using
        // inbuilt toBinaryString method
        return Integer.toBinaryString(decimal);
    }
}
