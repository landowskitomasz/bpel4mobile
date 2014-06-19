package com.bpel4mobile.hotel.android.service;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.util.Log;
import com.bpel4mobile.hotel.android.model.TaskName;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class Bpel4MobileTasksResolver {

    public static final String URL = "http://192.168.0.11:8282/hotel-cleanup-mobile-middleware/mobile/bpel4mobile/rest/";

    private final AccountManager accountManager;

    public Bpel4MobileTasksResolver(Context context){
        accountManager = AccountManager.get(context);
    }

    public boolean climeTask(TaskName taskName, String taskUUID, Account user) {
        ClimeRequest climeRequest = new ClimeRequest();
        climeRequest.setTaskName(taskName);
        climeRequest.setTaskUUID(taskUUID);
        return post(URL + "clime", user, climeRequest);
    }

    public Boolean completeTask(TaskName taskName, String taskUUID, String result, Account user) {
        CompleteRequest completeRequest = new CompleteRequest();
        completeRequest.setTaskName(taskName);
        completeRequest.setTaskUUID(taskUUID);
        completeRequest.setResult(result);

        return post(URL + "resolve", user, completeRequest);
    }

    private HttpHeaders prepareHeaders(final Account account){
        HttpAuthentication authHeader = new HttpBasicAuthentication(account.name, accountManager.getPassword(account));
        Log.i(Bpel4MobileTasksResolver.class.getName(), "Account: " + account.name + " password: " + accountManager.getPassword(account));
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAuthorization(authHeader);
        return requestHeaders;
    }

    private <T> boolean post(String url, Account account, T request){
        HttpEntity<T> requestEntity = new HttpEntity<T>(request, prepareHeaders(account));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return HttpStatus.OK.equals(response.getStatusCode());
        } catch (HttpClientErrorException e){
            Log.e(Bpel4MobileTasksResolver.class.getName(), "Authentication error", e);
            return  false;
        }
    }
}
