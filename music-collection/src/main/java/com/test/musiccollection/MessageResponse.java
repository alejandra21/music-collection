/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection;

/**
 *
 * @author alejandra
 */
public class MessageResponse {
    
    private String status;
    private String content;

    public MessageResponse() {
        this.status  = null;
        this.content = null;
    }

    public String getStatus() {
        return status;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
