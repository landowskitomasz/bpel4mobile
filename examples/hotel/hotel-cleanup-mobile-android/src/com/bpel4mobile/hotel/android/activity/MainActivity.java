package com.bpel4mobile.hotel.android.activity;

import java.util.List;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bpel4mobile.hotel.android.R;
import com.bpel4mobile.hotel.android.adapter.TasksListAdapter;
import com.bpel4mobile.hotel.android.model.*;
import com.bpel4mobile.hotel.android.service.SessionManager;

public class MainActivity extends FragmentActivity {

    private SessionManager sessionManager;

    private Database database;

    private ListView taskLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskLists = (ListView)findViewById(R.id.tasks_list);

        sessionManager = new SessionManager(this);
        database = new Database(this, sessionManager.getCurrentUser());

        printDatabase();

        taskLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                if(item instanceof  Task){
                    taskSelected((Task)item);
                } else {
                    Log.e(MainActivity.class.getName(), "Unknown item type.");
                }
            }
        });
        loadTasksList();

        ContentResolver.addStatusChangeListener(ContentResolver.SYNC_OBSERVER_TYPE_ACTIVE, new SyncStatusObserver() {

            @Override
            public void onStatusChanged(int i) {
                Log.i(MainActivity.class.getName(), "Sync status changed.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(MainActivity.class.getName(), "Reload invoked.");
                        loadTasksList();
                    }
                });
            }
        });
    }

    private void printDatabase() {
        List<Task> tasks = database.getRuntimeExceptionDao(Task.class).queryForAll();
        for(Task task : tasks){
            Log.d(MainActivity.class.getName(), "Task id " +task.getId());
            Log.d(MainActivity.class.getName(), "Task uuid " +task.getUuid());
            Log.d(MainActivity.class.getName(), "Task state " +task.getState());
            Log.d(MainActivity.class.getName(), "Task name " +task.getName());
            Log.d(MainActivity.class.getName(), "Task assignee " +task.getAssignee());
            Log.d(MainActivity.class.getName(), "Task climeDate " +task.getClimeDate());
            Log.d(MainActivity.class.getName(), "Task priority " +task.getPriority());
            Log.d(MainActivity.class.getName(), "Task request id " +task.getRequest().getId());
        }
        List<Request> requests = database.getRuntimeExceptionDao(Request.class).queryForAll();
        for(Request request : requests){
            Log.d(MainActivity.class.getName(), "Request id " +request.getId());
            Log.d(MainActivity.class.getName(), "Request clean up performer  " +request.getCleanUpPerformer());
            Log.d(MainActivity.class.getName(), "Request deadline " +request.getDeadline());
            Log.d(MainActivity.class.getName(), "Request room id " +request.getRoom().getId());
        }
        List<Room> rooms = database.getRuntimeExceptionDao(Room.class).queryForAll();
        for(Room room : rooms){
            Log.d(MainActivity.class.getName(), "Room id " +room.getId());
            Log.d(MainActivity.class.getName(), "Room number  " +room.getNumber());
            Log.d(MainActivity.class.getName(), "Room floor " +room.getFloor());
            Log.d(MainActivity.class.getName(), "Room category " +room.getCategory().getId());
        }
    }

    private void taskSelected(Task item) {
        if(Task.TaskState.completed.equals(item.getState())){
            return;
        }

        Intent intent = null;
        if(TaskName.cleanUpTask.equals(item.getName())){
            intent = new Intent(this, CleanUpTaskDetailsActivity.class);
            intent.putExtra(CleanUpTaskDetailsActivity.TASK_UUID, item.getUuid());
        } else if (TaskName.verifyTask.equals(item.getName())){
            intent = new Intent(this, VerifyTaskDetailsActivity.class);
            intent.putExtra(VerifyTaskDetailsActivity.TASK_UUID, item.getUuid());
        } else {
            Log.e(MainActivity.class.getName(), "Unknown task name.");
            return;
        }
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem arg0) {
                sessionManager.logoutUser();
                return true;
            }
        });
        menu.getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem arg0) {
                ContentResolver.requestSync(sessionManager.getCurrentUser(), "com.bpel4mobile.hotel.android.syncdata.provider", Bundle.EMPTY);
                return true;
            }
        });
        return true;
    }

    private void loadTasksList(){
        List<Task> tasks = database.getRuntimeExceptionDao(Task.class).queryForAll();
        taskLists.setAdapter(new TasksListAdapter(this, R.layout.item_task, tasks));
    }
}
