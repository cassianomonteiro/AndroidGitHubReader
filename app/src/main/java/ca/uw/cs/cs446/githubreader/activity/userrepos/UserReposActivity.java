package ca.uw.cs.cs446.githubreader.activity.userrepos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import ca.uw.cs.cs446.githubreader.R;
import ca.uw.cs.cs446.githubreader.model.GitHubService;
import ca.uw.cs.cs446.githubreader.model.UserRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserReposActivity extends AppCompatActivity {

    private String username;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_repos);

        // Get a private reference to the progress bar
        progressBar = findViewById(R.id.progressBar);

        // Get the piece of data passed to this screen and store in a private variable
        username = getIntent().getStringExtra("USERNAME");

        // Set the title on the top bar at the screen
        // We want this in onCreate to make sure it appears when the screen is shown to the user
        setTitle(username);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Create a Retrofit instance to handle HTTP requests
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our custom GitHub service using Retrofit's factory
        GitHubService service = retrofit.create(GitHubService.class);

        // Create a call to execute the HTTP request
        Call<List<UserRepo>> userReposCall = service.getUserRepos(username);

        // Show the progress while waiting for request.
        // We show it before starting the request.
        progressBar.setVisibility(View.VISIBLE);

        /**
         * Calling "execute" method runs it synchronously.
         * This will cause NetworkOnMainThreadException
         */
//        try {
//            Response<List<UserRepo>> response = userReposCall.execute();
//            RecyclerView reposRecyclerView = findViewById(R.id.reposRecyclerView);
//            reposRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
//            reposRecyclerView.setAdapter(new UserReposRecyclerViewAdapter(response.body()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Calling "enqueue" method runs it asynchronously and calls the callback methods
        userReposCall.enqueue(new Callback<List<UserRepo>>() {

            // This method will be called on a successful response from the HTTP request
            @Override
            public void onResponse(Call<List<UserRepo>> call, Response<List<UserRepo>> response) {

                // Hide the progress bar as the request is completed
                progressBar.setVisibility(View.INVISIBLE);

                // Get the data returned by the HTTP request
                List<UserRepo> userRepos = response.body();

                // If no results, the body is null
                if (userRepos != null) {

                    // Get the RecyclerView instance
                    RecyclerView reposRecyclerView = findViewById(R.id.reposRecyclerView);

                    // Set the LayoutManager to be vertical (default)
                    reposRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

                    // Set the adapter which will fill the data on the RecyclerView items
                    reposRecyclerView.setAdapter(new UserReposRecyclerViewAdapter(userRepos));
                }
            }

            // This method will be called on a FAILED response from the HTTP request
            @Override
            public void onFailure(Call<List<UserRepo>> call, Throwable t) {
                // Hide the progress bar as the request is completed
                // Would be nice to show some error message to the user
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }
}
