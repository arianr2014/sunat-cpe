package io.github.carlosthe19916.pe.models;

public interface CdrModel {

    String getId();

    String getFileId();
    void setFileId(String fileId);

    String getTicket();
    void setTicket(String ticket);
}
