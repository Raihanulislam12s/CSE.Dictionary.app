package com.shojib.asoftbd.eeedictionary;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class WordDefinitionDetailActivity extends Activity {


    TextView wordTextView;

    TextView definitionTextView;



    @Override


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_word_definition_detail);

        wordTextView=(TextView) findViewById(R.id.wordTextView);

        definitionTextView=(TextView) findViewById(R.id.definitionTextView);


        Log.d("DICTIONARY", "third activity started");


        wordTextView.setText(getIntent().getStringExtra("word"));

        definitionTextView.setText(getIntent().getStringExtra("definition"));



    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

// Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_word_definition_detail, menu);

        return true;
    }

}



//public class WordDefinitionDetailActivity extends AppCompatActivity {
//
//    TextView wordText;
//    TextView definitionText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_word_definition_detail);
//        wordText= (TextView) findViewById(R.id.wordTextView);
//        definitionText= (TextView) findViewById(R.id.definitionTextView);
//
//        wordText.setText(getIntent().getStringExtra("word"));
//        definitionText.setText(getIntent().getStringExtra("definition"));
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_word_definition_detail, menu);
//        return true;
//    }
//
//
//}
