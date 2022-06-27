package com.mihaimiuta.api;

import java.util.Date;

public class Run {
    private final String id;
    private final Date creationDate;

    private final String status;

    private final String initiator;

    public Run(String _id, Date _creationDate, String _status, String _initiator) {
        this.id = _id;
        this.creationDate = _creationDate;
        this.status = _status;
        this.initiator = _initiator;
    }

    public String getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getStatus() {
        return status;
    }

    public String getInitiator() {
        return initiator;
    }
}
