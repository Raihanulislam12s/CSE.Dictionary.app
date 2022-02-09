package com.shojib.asoftbd.eeedictionary;

import java.util.ArrayList;


public class WordDefinition {


    String word,definition;



    public WordDefinition(String word,ArrayList<String> alldefinition) {


        this.word=word;


        StringBuilder stringBuilder=new StringBuilder();


        for (String string : alldefinition) {

            stringBuilder.append(string);
        }


        this.definition=stringBuilder.toString();
    }



    public WordDefinition(String word,String alldefinition) {


        this.word=word;

        this.definition=alldefinition;


    }



}



/**
 * Created by Shopno-Shomu on 27-02-16.
 */
//public class WordDefinition {
//    String word, definition;
//
//    public WordDefinition(String word, ArrayList<String> allDefinition) {
//        this.word = word;
//        StringBuilder stringBuilder= new StringBuilder(); //here lots of string , so we can save all string in StringBuilder class to save in 1 class..
//        for (String string: allDefinition){               // by using stringbuilder connect all string pairly..
//            stringBuilder.append(string);
//        }
//
//        this.definition=stringBuilder.toString();       // gether all string, we make 1 contructor.
//    }
//
//    public WordDefinition(String word, String allDefinition) {
//        this.word = word;
//        this.definition = allDefinition;
//    }
//}
