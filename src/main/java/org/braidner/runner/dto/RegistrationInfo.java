package org.braidner.runner.dto;

import org.braidner.runner.domain.DeviceType;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Braidner
 */
public class RegistrationInfo {
    @NotEmpty private String username;
    @NotEmpty private String deviceId;
    private String authToken;
    private DeviceType deviceType;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
