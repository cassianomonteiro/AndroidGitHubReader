package ca.uw.cs.cs446.githubreader.activity.userrepos;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.uw.cs.cs446.githubreader.R;
import ca.uw.cs.cs446.githubreader.model.UserRepo;

/**
 * Created by cassiano on 08/01/18.
 */

public class UserReposRecyclerViewAdapter extends RecyclerView.Adapter<RepoItemViewHolder> {

    // Arraylist to hold our data. Initialized as empty ArrayList to avoid null pointers in case of no results.
    private List<UserRepo> userRepos = new ArrayList<>();

    public UserReposRecyclerViewAdapter(List<UserRepo> userRepos) {
        this.userRepos = userRepos;
    }

    // This method is called when there are no reusable ViewHolders available.
    // In this case, a new ViewHolder instance needs to be created.
    @Override
    public RepoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Create a new view
        ConstraintLayout repoItemView = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_repo, parent, false);

        // Get references to view components
        TextView repoNameView = repoItemView.findViewById(R.id.repoName);
        TextView repoLanguageView = repoItemView.findViewById(R.id.repoLanguage);
        TextView repoDescriptionView = repoItemView.findViewById(R.id.repoDescription);

        // Create a new ViewHolder instance and attach references to it
        RepoItemViewHolder viewHolder = new RepoItemViewHolder(repoItemView);
        viewHolder.nameTextView = repoNameView;
        viewHolder.languageTextView = repoLanguageView;
        viewHolder.descriptionTextView = repoDescriptionView;

        return viewHolder;
    }

    // This method is called to reuse a ViewHolder.
    // All data displayed should be set/reset to make sure no older data is displayed
    @Override
    public void onBindViewHolder(RepoItemViewHolder holder, int position) {

        // Get repo object to have its data displayed at "position" in the list
        UserRepo repo = userRepos.get(position);

        // Set data to the view components
        holder.nameTextView.setText(repo.getName());
        holder.languageTextView.setText(repo.getLanguage());
        holder.descriptionTextView.setText(repo.getDescription());
    }

    // This method tell the RecyclerView how many objects in total are available to be displayed.
    @Override
    public int getItemCount() {
        return userRepos.size();
    }
}
