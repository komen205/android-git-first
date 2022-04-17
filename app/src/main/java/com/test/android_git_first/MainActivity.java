package com.test.android_git_first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) this.findViewById(R.id.openLink);
        btn.setOnClickListener(view -> {
            EditText txt = (EditText) this.findViewById(R.id.githubRepoLink);
            String editText = txt.getText().toString();
            if(!editText.isEmpty()) {
                String[] split = editText.split("/");
                FirstCommitGet first = new FirstCommitGet(split[3], split[4]);
                first.run(getApplicationContext());
            }
        });
    }
}