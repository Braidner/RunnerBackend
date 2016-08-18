package org.braidner.runner.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Braidner
 */
@Document(collection = "runs")
public class Run implements Comparable<Run> {

    @Id private String guid;
    @Indexed private String username;

    private Date startRun;
    private Date endRun;
    private RunInfo runInfo;

    public Run() {
    }

    public Run(String username) {
        this.username = username;
        this.startRun = new Date();
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Date getStartRun() {
        return startRun;
    }

    public void setStartRun(Date startRun) {
        this.startRun = startRun;
    }

    public Date getEndRun() {
        return endRun;
    }

    public void setEndRun(Date endRun) {
        this.endRun = endRun;
    }

    public RunInfo getRunInfo() {
        return runInfo;
    }

    public void setRunInfo(RunInfo runInfo) {
        this.runInfo = runInfo;
    }

    @Override
    public int compareTo(Run o) {
        return startRun.compareTo(o.startRun);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
