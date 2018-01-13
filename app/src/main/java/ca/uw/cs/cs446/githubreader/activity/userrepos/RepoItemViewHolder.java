package ca.uw.cs.cs446.githubreader.activity.userrepos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by cassiano on 09/01/18.
 */

// Provides a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
public class RepoItemViewHolder extends RecyclerView.ViewHolder {

    public TextView nameTextView;
    public TextView languageTextView;
    public TextView descriptionTextView;

    public RepoItemViewHolder(View itemView) {
        super(itemView);
    }

}
