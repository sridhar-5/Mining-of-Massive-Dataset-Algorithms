package com.amrita.MomdsProject.dataModels;

public class Tuples {

    // The expected hash function should be of form (ax + b) mod N
    // a and b are the Hash coefficients and N is decided from the length of the longest values in the similarity set

    private int a;
    private int b;
    public Tuples(int a, int b){
        this.a = a;
        this.b = b;
    }

    public int get_a(){
        return a;
    }

    public int get_b(){
        return b;
    }

    public void set_a(int a){
        this.a = a;
    }

    public void set_b(int b){
        this.b = b;
    }
}
