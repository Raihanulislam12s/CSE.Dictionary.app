package com.shojib.asoftbd.eeedictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 * Created by Shopno-Shomu on 27-02-16.
 */

public class DictionaryLoader {



    public static void loadData(BufferedReader bufferedReader, DictionaryDatabaseHelper dictionaryDatabaseHelper) {


        ArrayList<WordDefinition> allWords=new ArrayList<WordDefinition>();

        try {


            BufferedReader fileReader=bufferedReader;

            try {

                int c=17;

                c=fileReader.read();


                while (c!=(-1)) {

                    StringBuilder stringBuilder=new StringBuilder();


                    while ((char)c!='\n'&&c!=-1) {

                        try {

                            stringBuilder.append((char)c);

                        } catch (Exception e) {

                            // TODO Auto-generated catch block

                            System.out.println(stringBuilder.length());

                            //e.printStackTrace();

                        }

                        c= fileReader.read();

                        if (c==-1) {

                            return;
                        }

                    }

                    String wordString=stringBuilder.toString();

                    ArrayList<String> definition=new ArrayList<String>();

                    while (c=='\n'||c=='\t') {


                        c= fileReader.read();

                        if (c=='\n'||c=='\t'||c=='\r') {


                            StringBuilder stringBuilder2=new StringBuilder();

                            while (c!='\n') {

                                stringBuilder2.append((char)c);

                                c=fileReader.read();
                            }

                            String definitionString=stringBuilder2.toString();

                            definition.add(definitionString);
                        }else {

                            break;
                        }

                    }

                    wordString=wordString.trim();

                    //Logger.log("word Loaded: "+(++counter)+" :"+wordString);

                    allWords.add(new WordDefinition(wordString, definition));

                }

            } catch (IOException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();
            }



            try {
                dictionaryDatabaseHelper.initializeDatabaseFortheFirstTime(allWords);

                fileReader.close();

            } catch (IOException e) {

                // TODO Auto-generated catch block

                e.printStackTrace();
            }
        } catch (Exception e) {

            // TODO Auto-generated catch block

            e.printStackTrace();
        }


    }



}


//public class DictionaryLoader {      //following method 2 perameter should be passed, 1. which file read, this file parameter (Buffer Reader), & 2 is Database parameter
//    public static void loadData(BufferedReader bufferedReader, DictionaryDatabaseHelper dictionaryDatabaseHelper){
//
//        ArrayList<WordDefinition> allWords= new ArrayList<>(); //initialize
//
//        try {
//            BufferedReader fileReader = bufferedReader;
//
//            try {
//                int c=17;
//                c=fileReader.read();
//                while (c!=(-1)) {
//                    StringBuilder stringBuilder = new StringBuilder();
//
//                    while ((char)c!='\n'&&c!=-1){
//                        try {
//                            stringBuilder.append((char)c);
//                        } catch (Exception e){
//                            System.out.println(stringBuilder.length());
//                        }
//                        c=fileReader.read();
//                        if (c==-1){
//                            return;
//                        }
//                    }
//                    String wordString = stringBuilder.toString();
//
//                    ArrayList<String> definition = new ArrayList<>();
//                    while (c=='\n'||c=='t'){
//                        c=fileReader.read();
//                        if (c=='\n'||c=='\t'||c=='\r'){
//                            StringBuilder stringBuilder2=new StringBuilder();
//                            while (c!='\n'){
//                                stringBuilder2.append((char)c);
//                                c=fileReader.read();
//                            }
//                            String definitionString = stringBuilder2.toString();
//                            definition.add(definitionString);
//                        }else {
//                            break;
//                        }
//                    }
//
//                    wordString=wordString.trim();
//
//                    allWords.add(new WordDefinition(wordString, definition));
//                }
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//            try {
//
//                dictionaryDatabaseHelper.initializeDatabaseFirstTime(allWords);
//                fileReader.close();
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//
//    }
//}
