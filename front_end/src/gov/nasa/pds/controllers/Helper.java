/*
 * Copyright (C) 2011 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.controllers;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;


/**
 * <p>
 * Helper class for the component. It provides useful methods for all the classes in this component.
 * </p>
 *
 * <p>
 * <strong>Thread Safety: </strong> This class has no state, and thus it is thread safe.
 * </p>
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public final class Helper {
    /**
     * <p>
     * Prevents to create a new instance.
     * </p>
     */
    private Helper() {
        // empty
    }

    /**
     * Converts the object to a string.
     *
     * @param obj
     *            the object.
     *
     * @return the string.
     */
    public static String toString(Object obj) {
        if (obj instanceof ModelAndView) {
            return toString((ModelAndView) obj);
        } else if (obj instanceof HttpServletRequest) {
            return toString((HttpServletRequest) obj);
        } else if (obj instanceof List<?>) {
            return toString((List<?>) obj);
        }

        return String.valueOf(obj);
    }

    /**
     * Converts the model and view to a string.
     *
     * @param modelAndView
     *            the model and view (not <code>null</code>).
     *
     * @return the string.
     */
    private static String toString(ModelAndView modelAndView) {
        StringBuilder sb = new StringBuilder();
        sb.append(modelAndView.getClass().getName()).append("{").append("viewName:").append(
            modelAndView.getViewName()).append(", model:{");
        Map<String, Object> model = modelAndView.getModel();
        boolean first = true;
        for (Entry<String, Object> entry : model.entrySet()) {
            if (!first) {
                sb.append(", ");
            }
            first = false;

            sb.append(entry.getKey()).append("=").append(toString(entry.getValue()));
        }
        sb.append("}}");

        return sb.toString();
    }

    /**
     * Converts the request to a string.
     *
     * @param request
     *            the request (not <code>null</code>).
     *
     * @return the string.
     */
    @SuppressWarnings("unchecked")
    private static String toString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.getClass().getName()).append("{").append("request URL:").append(request.getRequestURL())
            .append(", request parameters:{");
        Enumeration<String> parameterNames = request.getParameterNames();
        boolean first = true;
        while (parameterNames.hasMoreElements()) {
            if (!first) {
                sb.append(", ");
            }
            first = false;
            String parameterName = parameterNames.nextElement();
            sb.append(parameterName).append("=").append(Arrays.asList(request.getParameterValues(parameterName)));
        }
        sb.append("}}");

        return sb.toString();
    }

    /**
     * Converts the List to a string.
     *
     * @param obj
     *            the List (not <code>null</code>).
     *
     * @return the string.
     */
    private static String toString(List<?> obj) {
        StringBuilder sb = new StringBuilder("[");

        boolean first = true;
        for (Object element : obj) {
            if (!first) {
                // Append a comma
                sb.append(", ");
            }
            first = false;

            sb.append(toString(element));
        }

        sb.append("]");

        return sb.toString();
    }
}
