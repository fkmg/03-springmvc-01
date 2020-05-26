package com.sxt;


import org.junit.Test;

public class TestJava {

    @Test
    public void  testSwitch(){
        String [] tests = {"hello","","world"};
        for(int i=0; i< tests.length;i++){
            switch (tests[i]){
                case "hello": {
                    System.out.println("hello 世界!");
                    break;
                }
                case "":{
                    System.out.println("hello 师姐!");
                    break;
                }
                default:{
                    System.out.println("你好,世界");
                }

            }
        }

    }
}
