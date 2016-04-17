/*
 * Copyright (C) 2015 TopCoder Inc., All Rights Reserved.
 */
package com.kyrosoft.ims.model.dto;

import java.util.List;

/**
 * <p>
 * Represents search result.
 * </p>
 * <p>
 * <strong>Thread Safety:</strong> This class is mutable and not thread safe.
 * </p>
 *
 * @param <T>
 *            Generic type.
 *
 * @author TCSASSEMBLER
 * @version 1.0
 */
public class SearchResult<T> extends BaseSearchParameters {
    /**
     * Represents the total record count.
     */
    private int total;

    /**
     * Represents the total pages count.
     */
    private int totalPages;

    /**
     * Represents the values.
     */
    private List<T> values;

    /**
     * Empty constructor.
     */
    public SearchResult() {
    }

    /**
     * Returns the total.
     *
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the total.
     *
     * @param total
     *            the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Returns the totalPages.
     *
     * @return the totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Sets the totalPages.
     *
     * @param totalPages
     *            the totalPages to set
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * Returns the values.
     *
     * @return the values
     */
    public List<T> getValues() {
        return values;
    }

    /**
     * Sets the values.
     *
     * @param values
     *            the values to set
     */
    public void setValues(List<T> values) {
        this.values = values;
    }
}
