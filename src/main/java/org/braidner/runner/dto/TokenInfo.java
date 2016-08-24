package org.braidner.runner.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KuznetsovNE on 19.08.2016.
 */
public class TokenInfo {

    @SerializedName("user_id")
    private String userId;
    private String error;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
