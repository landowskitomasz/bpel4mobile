package com.bpel4mobile.hotel.android.service;

import android.os.Bundle;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class AuthenticationService {

    public static final String CLEAN_UP_SYSTEM_USER_TYPE = "com.bpel4mobile.hotel.account";

    public static final String AUTHENTICATION_OPERATION = "authenticate";

    private final Context _context;

    private final AccountManager accountManager;

    private final String url;

    public AuthenticationService(final Context context) {
        this._context = context;
        this.accountManager = AccountManager.get(_context);
        url = Bpel4MobileTasksResolver.URL;
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public boolean authenticate(final String login, final String password) {

        if (isOnline()) {
            HttpAuthentication authHeader = new HttpBasicAuthentication(login, password);
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAuthorization(authHeader);
            HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);


            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            HttpStatus status = null;
            try {
                ResponseEntity<String> response = restTemplate.exchange(url + AUTHENTICATION_OPERATION, HttpMethod.POST,
                        requestEntity, String.class);
                status = response.getStatusCode();
            } catch (HttpClientErrorException e) {
                return false;
            }

            if (HttpStatus.OK.equals(status)) {
                Account account = new Account(login, CLEAN_UP_SYSTEM_USER_TYPE);
                String savedPassword = accountManager.getPassword(account);
                if (savedPassword == null) {
                    accountManager.addAccountExplicitly(account, password, Bundle.EMPTY);
                    ContentResolver.setSyncAutomatically(account, "com.bpel4mobile.hotel.android.syncdata.provider", true);
                }
                return true;
            } else {
                Log.e(AuthenticationService.class.getName(),
                        "Authentication finished with error, http status: " + status.getReasonPhrase() + " value:"
                                + status.value()
                );
            }

        } else {
            Account account = new Account(login, CLEAN_UP_SYSTEM_USER_TYPE);
            String correctPassword = accountManager.getPassword(account);

            if (correctPassword != null) {
                return correctPassword.equals(password);
            }
        }
        return false;
    }

}
