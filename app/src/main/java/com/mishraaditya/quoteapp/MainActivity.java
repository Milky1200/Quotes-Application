package com.mishraaditya.quoteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    MainViewModel mainViewModel;
    TextView text, author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        text=findViewById(R.id.quoteText);
        author=findViewById(R.id.quoteAuthor);

        MainViewModelFactory mainViewModelFactory=new MainViewModelFactory(getApplicationContext());
        mainViewModel=new ViewModelProvider(this,mainViewModelFactory).get(MainViewModel.class);
        setText();

    }

    public void setText(){
        text.setText(mainViewModel.getQuote().text);
        author.setText(mainViewModel.getQuote().author);
    }

    public void onPrevious(View view) {
        quote qt=mainViewModel.prevQuote();
        text.setText(qt.text);
        author.setText(qt.author);
    }

    public void onNext(View view) {
        quote qt=mainViewModel.nextQuote();
        text.setText(qt.text);
        author.setText(qt.author);
    }

    public void onShare(View view) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text);
        startActivity(intent);

    }
}