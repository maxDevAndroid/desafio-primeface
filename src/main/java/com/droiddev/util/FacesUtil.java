package com.droiddev.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public final class FacesUtil {
    private FacesUtil(){}

    public static void addInfoMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    public static void addErrorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }
}
