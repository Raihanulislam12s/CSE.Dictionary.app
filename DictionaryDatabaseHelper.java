package com.shojib.asoftbd.eeedictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Shopno-Shomu on 27-02-16.
 */

public class DictionaryDatabaseHelper extends SQLiteOpenHelper {


    final static String DICTIONARY_DATABASE="dictionary";

    final static String ITEM_ID_COLUMN="id";

    final static String WORD_COLUMN="word";

    final static String DEFINITION_COLUMN="definition";





    final static String CREATE_DATABASE_QUERY="CREATE TABLE "+DICTIONARY_DATABASE+
            " ( "+
            ITEM_ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT, "+

            WORD_COLUMN+" TEXT , "+
            DEFINITION_COLUMN+" TEXT)";



    final static String ON_UPGRADE_QUERY="DROP TABLE "+DICTIONARY_DATABASE;


    Context context;




    public DictionaryDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DICTIONARY_DATABASE, factory, version);

        this.context=context;

    }



    @Override

    public void onCreate(SQLiteDatabase database) {

        database.execSQL(CREATE_DATABASE_QUERY);
    }


    @Override

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(ON_UPGRADE_QUERY);

        onCreate(database);

    }



    public long insertData(WordDefinition wordDefinition) {


        SQLiteDatabase database=this.getWritableDatabase();

        ContentValues values=new ContentValues();


        values.put(WORD_COLUMN, wordDefinition.word);

        values.put(DEFINITION_COLUMN, wordDefinition.definition);


        return database.insert(DICTIONARY_DATABASE, null, values);	}



    public long updateData(WordDefinition wordDefinition) {

        SQLiteDatabase database=this.getWritableDatabase();

        ContentValues values=new ContentValues();


        values.put(WORD_COLUMN, wordDefinition.word);

        values.put(DEFINITION_COLUMN, wordDefinition.definition);

        return database.update(DICTIONARY_DATABASE, values, WORD_COLUMN+" =?", new String[]{wordDefinition.word});


    }



    public void deleteData(WordDefinition wordDefinition) {

        SQLiteDatabase database=this.getWritableDatabase();

        String queryString="DELETE FROM "+DICTIONARY_DATABASE+" WHERE "+WORD_COLUMN+" = '"+wordDefinition.word+"'";

        database.execSQL(queryString);
    }



    public ArrayList<WordDefinition> getAllWords() {

        ArrayList<WordDefinition> arrayList=new ArrayList<WordDefinition>();

        SQLiteDatabase database=this.getReadableDatabase();

        String selectAllQueryString="SELECT * FROM "+DICTIONARY_DATABASE;

        Cursor cursor=database.rawQuery(selectAllQueryString, null);


        if (cursor.moveToFirst()) {

            do {

                WordDefinition wordDefinition=new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINITION_COLUMN)));

                arrayList.add(wordDefinition);
            } while (cursor.moveToNext());	}
        return arrayList;
    }


    public WordDefinition getWordDefinition(String word) {

        SQLiteDatabase database=this.getReadableDatabase();

        WordDefinition wordDefinition=null;


        String selectQueryString="SELECT * FROM "+DICTIONARY_DATABASE+ " WHERE "+WORD_COLUMN+" = '"+word+ "'";

        Cursor cursor=database.rawQuery(selectQueryString, null);

        if (cursor.moveToFirst()) {

            wordDefinition=new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINITION_COLUMN)));

        }


        return wordDefinition;


    }



    public WordDefinition getWordDefinition(long id) {

        SQLiteDatabase database=this.getReadableDatabase();

        WordDefinition wordDefinition=null;


        String selectQueryString="SELECT * FROM "+DICTIONARY_DATABASE+ " WHERE "+ITEM_ID_COLUMN+" = '"+id+ "'";

        Cursor cursor=database.rawQuery(selectQueryString, null);

        if (cursor.moveToFirst()) {

            wordDefinition=new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINITION_COLUMN)));

        }
        return wordDefinition;
    }



    public void initializeDatabaseFortheFirstTime(ArrayList<WordDefinition> wordDefinitions) {

        SQLiteDatabase database=this.getWritableDatabase();

        database.execSQL("BEGIN");


        ContentValues contentValues=new ContentValues();

        for (WordDefinition wordDefinition : wordDefinitions) {

            contentValues.put(WORD_COLUMN, wordDefinition.word);

            contentValues.put(DEFINITION_COLUMN, wordDefinition.definition);
            database.insert(DICTIONARY_DATABASE, null, contentValues);
        }


        database.execSQL("COMMIT");
    }


}


//public class DictionaryDatabaseHelper extends SQLiteOpenHelper{
//
//    final static String DICTIONARY_DATABASE = "dictionary";
//    final static String ITEM_ID_COLUMN= "id";
//    final static String WORD_COLUMN= "word";
//    final static String DEFINATION_COLUMN= "definition";
//
//    final static String CREATE_DATABASE_QUERY= "CREATE TABLE"+DICTIONARY_DATABASE+"("+ITEM_ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT, "+WORD_COLUMN+" TEXT,"+DEFINATION_COLUMN+"TEXT )";
//    final static String ON_UPGRADE_QUERY= "DROP TABLE"+DICTIONARY_DATABASE; //for upgrading table droped.
//    Context context;
//
//
//    public DictionaryDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, DICTIONARY_DATABASE, factory, version); //here database created but table not created..in onCreat table is created.
//        this.context=context; //context is saved for later uses.
//    }
//
//
//
//
//    @Override
//    public void onCreate(SQLiteDatabase database) {
//            database.execSQL(CREATE_DATABASE_QUERY); // String creating for database object should execute QUERY...
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) { //1st upgrading .. table should be droped...
//
//        database.execSQL(ON_UPGRADE_QUERY); //by using this query previous table willbe deleted.. so need to creat again so call onCReat
//        onCreate(database);
//    }
//
//    public long insertData(WordDefinition wordDefinition){
//        SQLiteDatabase database=this.getWritableDatabase(); //for insert Writable select
//        ContentValues values= new ContentValues(); // key value pair type object... to input data in DB
//
//        values.put(WORD_COLUMN, wordDefinition.word); // from wordDefinition class
//        values.put(DEFINATION_COLUMN, wordDefinition.definition);
//
//        return database.insert(DICTIONARY_DATABASE, null, values);
//    }
//    public long updateData(WordDefinition wordDefinition){
//        SQLiteDatabase database=this.getWritableDatabase(); //for insert Writable select
//        ContentValues values= new ContentValues(); // key value pair type object... to input data in DB
//
//        values.put(WORD_COLUMN, wordDefinition.word); // from wordDefinition class
//        values.put(DEFINATION_COLUMN, wordDefinition.definition);
//
//        return database.update(DICTIONARY_DATABASE, values, WORD_COLUMN + "=?", new String[]{wordDefinition.word}); //it alz return a long value
//
//    }
//
//    public void deleteData(WordDefinition wordDefinition){
//        SQLiteDatabase database=this.getWritableDatabase(); //for delete its remain void....
//        String queryString= "DELETE FROM"+DICTIONARY_DATABASE+"WHERE"+WORD_COLUMN+" = '"+wordDefinition.word+"'";
//        database.execSQL(queryString);   // bcz here no return type.. so execSQL use..
//    }
//
//    public ArrayList<WordDefinition> getAllWords(){ //Avobe we put data in DB.. now we get by this method..
//
//        ArrayList<WordDefinition> arrayList= new ArrayList<WordDefinition>();
//        SQLiteDatabase database= this.getReadableDatabase(); //here we read..
//        // Cursor.... use for checking data
//        String selectAllQuery= "SELECT * FROM"+DICTIONARY_DATABASE;
//        Cursor cursor=database.rawQuery(selectAllQuery,null);
//
//        if(cursor.moveToFirst()){ //Cursor 1stly moved in 1st..
//            do {
//                WordDefinition wordDefinition=new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINATION_COLUMN)));
//                                                            // here we can write: cursor.getString(1), cursor.getString(2).. 1 is WORD_COLUMN & 2 is DEFINITION.. where 0 is ID..
//                arrayList.add(wordDefinition); // for adding wordDefinition with arrylist
//            } while (cursor.moveToNext());
//        }
//
//        return arrayList;
//    }
//
//    public WordDefinition getWordDefinition(String word){
//
//        SQLiteDatabase database=this.getReadableDatabase(); //here we read
//        WordDefinition wordDefinition= null;
//        String selectQuery="SELECT * FROM"+DICTIONARY_DATABASE+"WHERE"+WORD_COLUMN+"='"+word+"'";
//        Cursor cursor=database.rawQuery(selectQuery, null); // after execute RAW Query it should be saved in Cursor..
//
//        if (cursor.moveToFirst()){ // only 1 item is required here so DO While loop didn't use here...
//            wordDefinition=new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINATION_COLUMN)));
//        }
//         return wordDefinition;
//
//    } //polymorphism
//    public WordDefinition getWordDefinition(long id){
//
//        SQLiteDatabase database=this.getReadableDatabase(); //here we read
//        WordDefinition wordDefinition= null;
//        String selectQuery="SELECT * FROM"+DICTIONARY_DATABASE+"WHERE"+ITEM_ID_COLUMN+"='"+id+"'";
//        Cursor cursor=database.rawQuery(selectQuery, null); // after execute RAW Query it should be saved in Cursor..
//
//        if (cursor.moveToFirst()){ // only 1 item is required here so DO While loop didn't use here...
//            wordDefinition=new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINATION_COLUMN)));
//        }
//        return wordDefinition;
//
//    }
//
//    public void initializeDatabaseFirstTime(ArrayList<WordDefinition> wordDefinitions){
//        SQLiteDatabase database=this.getWritableDatabase(); //write... when lots of data are insert
//        database.execSQL("BEGIN"); //here use BEGIN which cannot stop when COMMIT is not come..
//
//        ContentValues contentValues= new ContentValues();
//        for (WordDefinition wordDefinition: wordDefinitions){
//            contentValues.put(WORD_COLUMN,wordDefinition.word);
//            contentValues.put(DEFINATION_COLUMN,wordDefinition.definition);
//
//            database.insert(DICTIONARY_DATABASE,null,contentValues);
//        }
//
//        database.execSQL("COMMIT");
//
//    }
//
//
//}
