package com.boltsystem.loaddispatch.dto;

import com.boltsystem.loaddispatch.entity.LoadStatus;

public class LoadStatusUpdateRequest {
    private LoadStatus status;

    public LoadStatus getStatus() {
        return status;
    }

    public void setStatus(LoadStatus status) {
        this.status = status;
    }
}
