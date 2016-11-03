package v3.clientstrong.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.javiersantos.bottomdialogs.BottomDialog;

import java.util.ArrayList;
import java.util.List;

import v3.clientstrong.R;
import v3.clientstrong.mainFragments.WorkoutsListFragment;
import v3.clientstrong.models.Workout;

/**
 * Created by runquest.
 * Date: 2016-10-19
 */
public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.WorkoutViewHolder> {

    private static String TAG = WorkoutListAdapter.class.getSimpleName();

    private WorkoutsListFragment mContext;
    private List<Workout> mWorkoutList;
    private RecyclerView mWorkoutListView;

    public WorkoutListAdapter(WorkoutsListFragment fragment, ArrayList<Workout> workoutList, RecyclerView workoutListView) {
        this.mContext = fragment;
        this.mWorkoutList = workoutList;
        this.mWorkoutListView = workoutListView;
    }

    @Override
    public int getItemCount() {
        return mWorkoutList.size();
    }

    @Override
    public void onBindViewHolder(WorkoutViewHolder workoutViewHolder, int i) {
        Workout workout = mWorkoutList.get(i);
        workoutViewHolder.mWorkoutName.setText(workout.name);
    }

    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cell_workouts_list, viewGroup, false);
        return new WorkoutViewHolder(itemView);
    }

    class WorkoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mWorkoutName;

        WorkoutViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            mWorkoutName = (TextView) v.findViewById(R.id.workout_name);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = mWorkoutListView.getChildPosition(v);
            Workout workoutItem = mWorkoutList.get(itemPosition);

            new BottomDialog.Builder(mContext.getActivity())
                    .setTitle(workoutItem.name)
                    .setContent(workoutItem.description)
                    .setPositiveBackgroundColorResource(R.color.colorAccent)
                    .setPositiveText("START WORKOUT")
                    .setNegativeText("DISMISS")
                    .onPositive(new BottomDialog.ButtonCallback() {
                        @Override
                        public void onClick(BottomDialog dialog) {
                            Toast.makeText(mContext.getActivity(), "success", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
        }
    }
}
