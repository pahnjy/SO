package com.pineone.icbms.so.service.pr;


/**
 * Created by melvin on 2016. 8. 22..
 */
public class ServiceTransFormObject {

    public ServiceTransFormObject(){};

    public ServiceTransFormObject(String name){
        this.name = name;
    }
    private String id;
    private String name;
    private String deviceObjectId;
    private String conceptServiceId;
    private String status;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceObjectId() {
        return deviceObjectId;
    }

    public void setDeviceObjectId(String deviceObjectId) {
        this.deviceObjectId = deviceObjectId;
    }

    public String getConceptServiceId() {
        return conceptServiceId;
    }

    public void setConceptServiceId(String conceptServiceId) {
        this.conceptServiceId = conceptServiceId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public ServiceTransFormObject(String id, String name, String deviceObjectId, String conceptServiceId, String status) {

        this.id = id;
        this.name = name;
        this.deviceObjectId = deviceObjectId;
        this.conceptServiceId = conceptServiceId;
        this.status = status;
    }
}