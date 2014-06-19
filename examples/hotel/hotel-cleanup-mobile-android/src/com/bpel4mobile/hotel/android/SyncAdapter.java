package com.bpel4mobile.hotel.android;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;
import com.bpel4mobile.hotel.android.model.*;
import com.bpel4mobile.hotel.android.service.Bpel4MobileTasksResolver;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyncAdapter extends AbstractThreadedSyncAdapter  {

    private static final String GET_TASKS_OPERATION = "tasks";

    private final AccountManager accountManager;

    private final String url;

	public SyncAdapter(Context context, boolean autoInitialize) {
		super(context, autoInitialize);
		Log.d(SyncAdapter.class.getName(), "New sync adapter created.");
        accountManager = AccountManager.get(context);
        url = Bpel4MobileTasksResolver.URL;
	}

	@Override
	public void onPerformSync(Account account, 
			Bundle extras,
            String authority,
            ContentProviderClient provider,
            SyncResult syncResult) {

		Log.d(SyncAdapter.class.getName(), "Sync started.");

        ResponseEntity<Task[]> response = get(url + GET_TASKS_OPERATION, account, Task[].class);
        Log.d(SyncAdapter.class.getName(), "Found " + response.getBody().length + "tasks.");

        Map<String, Task> existingTasks = getExistingTasks(account);
        for(Task task : response.getBody()){
            Task existingTask = existingTasks.get(task.getUuid());
            if(existingTask != null){
                existingTasks.remove(task.getUuid());
                if(!existingTask.getState().equals(task.getState())){
                    updateTask(account, existingTask, task);
                }
                syncResult.stats.numUpdates++;
                continue;
            } else {
                createNewTask(account, task);
                syncResult.stats.numInserts++;
            }
        }

        for(Task taskToRemove : existingTasks.values()){
            removeTask(account, taskToRemove);
            syncResult.stats.numDeletes++;
        }

        Log.d(SyncAdapter.class.getName(), "Sync finished.");
	}

    private void updateTask(Account account, Task existingTask, Task task) {
        Database database = new Database(this.getContext(), account);

        task.setId(existingTask.getId());
        database.getRuntimeExceptionDao(Task.class).update(task);
    }

    private void removeTask(Account account, Task taskToRemove) {
        Database database = new Database(this.getContext(), account);
        database.getRuntimeExceptionDao(Task.class).delete(taskToRemove);
        Log.i(this.getClass().getName(), "removeTask invoked.");
    }

    private void createNewTask(Account account, Task task) {
        Database database = new Database(this.getContext(), account);
        Request request = task.getRequest();
        Room room  = request.getRoom();
        Category category = room.getCategory();

        createCategoryIfNotExists(database, category);
        createRoomIfNotExists(database,  room);

        database.getRuntimeExceptionDao(Request.class).create(request);
        database.getRuntimeExceptionDao(Task.class).create(task);

        Log.i(this.getClass().getName(), "createNewTask invoked.");
    }

    private void createRoomIfNotExists(Database database, Room room) {
        List<Room> existingRooms = new ArrayList<Room>();
        try {
            existingRooms = database.getRuntimeExceptionDao(Room.class).queryBuilder().where().eq(Room.ID, room.getId()).query();
        } catch (SQLException e) {
            Log.e(this.getClass().getName(), "Unable to get existing room by id.");
        }
        if(existingRooms.isEmpty()){
            database.getRuntimeExceptionDao(Room.class).create(room);
        } else {
            room.setId(existingRooms.get(0).getId());
        }
    }

    private void createCategoryIfNotExists(Database database, Category category) {
        List<Category> existingCategories = new ArrayList<Category>();
        try {
            existingCategories = database.getRuntimeExceptionDao(Category.class).queryBuilder().where().eq(Category.NAME, category.getName()).query();
        } catch (SQLException e) {
            Log.e(this.getClass().getName(), "Unable to get existing category by name.");
        }
        if(existingCategories.isEmpty()){
            database.getRuntimeExceptionDao(Category.class).create(category);
        } else {
            category.setId(existingCategories.get(0).getId());
        }
    }

    private Map<String, Task> getExistingTasks(Account account) {
        Database database = new Database(this.getContext(), account);
        List<Task> tasks = database.getRuntimeExceptionDao(Task.class).queryForAll();

        HashMap<String, Task> tasksMap = new HashMap<String, Task>();
        for(Task task: tasks){
            tasksMap.put(task.getUuid(), task);
        }
        return tasksMap;
    }

    private HttpHeaders prepareHeaders(final Account account){
        HttpAuthentication authHeader = new HttpBasicAuthentication(account.name, accountManager.getPassword(account));
        Log.i(SyncAdapter.class.getName(), "Account: " + account.name + " password: " + accountManager.getPassword(account));
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAuthorization(authHeader);
        return requestHeaders;
    }

    private <T> ResponseEntity<T> get(String url, Account account, Class<T> responseType){
        HttpEntity<?> requestEntity = new HttpEntity<Object>(prepareHeaders(account));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        try {
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);
            return response;
        } catch (HttpClientErrorException e){
            Log.e(SyncAdapter.class.getName(), "Authentication error", e);
            return  null;
        }
    }
}
