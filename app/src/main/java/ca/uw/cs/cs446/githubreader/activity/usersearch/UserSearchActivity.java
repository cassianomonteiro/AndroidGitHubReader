package ca.uw.cs.cs446.githubreader.activity.usersearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import ca.uw.cs.cs446.githubreader.R;
import ca.uw.cs.cs446.githubreader.activity.userrepos.UserReposActivity;

public class UserSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
    }

    public void onSearchTap(View view) {

        // Get the reference for the EditText UI component
        EditText usernameText = findViewById(R.id.username_text);

        // Create an intent to show a new screen passing data to it
        Intent userSearchIntent = new Intent(this, UserReposActivity.class);

        // Put a piece of data to pass to the new screen in a key-value bundle
        userSearchIntent.putExtra("USERNAME", usernameText.getText().toString());

        // Show new screen
        startActivity(userSearchIntent);
    }
}
