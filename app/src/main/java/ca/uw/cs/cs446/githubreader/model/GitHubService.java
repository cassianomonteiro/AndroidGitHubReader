package ca.uw.cs.cs446.githubreader.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cassiano on 08/01/18.
 */

public interface GitHubService {

    @GET("users/{username}/repos")
    Call<List<UserRepo>> getUserRepos(@Path("username") String user);
}
