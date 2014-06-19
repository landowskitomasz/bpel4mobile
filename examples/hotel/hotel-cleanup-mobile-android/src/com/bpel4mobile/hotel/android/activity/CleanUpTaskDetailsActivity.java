package com.bpel4mobile.hotel.android.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.bpel4mobile.hotel.android.R;
import com.bpel4mobile.hotel.android.model.Database;
import com.bpel4mobile.hotel.android.model.Task;
import com.bpel4mobile.hotel.android.model.TaskName;
import com.bpel4mobile.hotel.android.service.Bpel4MobileTasksResolver;
import com.bpel4mobile.hotel.android.service.SessionManager;

import java.sql.SQLException;
import java.util.List;

public class CleanUpTaskDetailsActivity extends Activity {

    public static final String TASK_UUID = "taskUUID";

    private SessionManager sessionManager;

    private Database database;

    private Button climeButton;

    private Button finishButton;

    private Bpel4MobileTasksResolver bpel4MobileTasksResolver;

    private TextView roomNumber;

    private TextView floor;

    private TextView category;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_up_task_details);

        bpel4MobileTasksResolver = new Bpel4MobileTasksResolver(this);
        loadComponents();

        sessionManager = new SessionManager(this);
        database = new Database(this, sessionManager.getCurrentUser());

        final String taskUUID = getIntent().getExtras().getString(TASK_UUID);
        if(taskUUID == null){
            throw new IllegalArgumentException( TASK_UUID + " parameter is required.");
        }
        Task task = findTask(taskUUID);

        initializeComponents(task);

        climeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                climeTask(taskUUID);
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishCleanUp(taskUUID);
            }
        });
    }

    private void finishCleanUp(final String taskUUID) {
        final ProgressDialog dialog = ProgressDialog.show(this, getString(R.id.progress_dialog_title), getString(R.id.complete_task_progress));
        AsyncTask<String, Boolean, Boolean> task = new AsyncTask<String, Boolean, Boolean>() {
            @Override
            protected Boolean doInBackground(String... strings) {
                return bpel4MobileTasksResolver.completeTask(TaskName.cleanUpTask, strings[0], strings[1], sessionManager.getCurrentUser());
            }

            @Override
            protected void onPostExecute(Boolean result) {
                dialog.dismiss();
                if(result) {
                    Task task = findTask(taskUUID);
                    task.setState(Task.TaskState.completed);
                    database.getRuntimeExceptionDao(Task.class).update(task);

                    finish();
                }
                super.onPostExecute(result);
            }
        };
        task.execute(taskUUID, "{\"success\" : true }");
    }

    private void loadComponents() {
        roomNumber = (TextView) this.findViewById(R.id.cut_room_number);
        floor = (TextView) this.findViewById(R.id.cut_floor);
        category = (TextView) this.findViewById(R.id.cut_category_name);

        climeButton = (Button) this.findViewById(R.id.cut_clime_button);
        finishButton = (Button) this.findViewById(R.id.cut_finish_button);
    }

    private Task findTask(String taskUUID) {
        try {
            List<Task> taskList = database.getRuntimeExceptionDao(Task.class).queryBuilder().where().eq(Task.UUID, taskUUID).query();
            if(taskList.isEmpty()){
                throw new IllegalArgumentException( "Task with uuid "+taskUUID+" not exists.");
            }
            return taskList.get(0);
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to load task from database" , e);
        }
    }

    private void initializeComponents(Task task) {
        roomNumber.setText(""+task.getRequest().getRoom().getNumber());
        floor.setText(""+task.getRequest().getRoom().getFloor());
        category.setText(task.getRequest().getRoom().getCategory().getName());
        if(Task.TaskState.climed.equals(task.getState())){
            climeButton.setVisibility(View.GONE);
            finishButton.setVisibility(View.VISIBLE);
        } else {
            climeButton.setVisibility(View.VISIBLE);
            finishButton.setVisibility(View.GONE);
        }
    }

    private void climeTask(final String taskUUID) {

        final ProgressDialog dialog = ProgressDialog.show(this, getString(R.id.progress_dialog_title), getString(R.id.clime_task_progress));
        AsyncTask<String, Boolean, Boolean> task = new AsyncTask<String, Boolean, Boolean>() {
            @Override
            protected Boolean doInBackground(String... strings) {
                return bpel4MobileTasksResolver.climeTask(TaskName.cleanUpTask, strings[0], sessionManager.getCurrentUser());
            }

            @Override
            protected void onPostExecute(Boolean result) {
                dialog.dismiss();
                if(result) {
                    Task task = findTask(taskUUID);
                    task.setState(Task.TaskState.climed);
                    database.getRuntimeExceptionDao(Task.class).update(task);

                    initializeComponents(task);
                }
                super.onPostExecute(result);
            }
        };

        task.execute(taskUUID);
    }
}