package com.kyrosoft.ims.model.dto;

/**
 * Created by Administrator on 4/15/2016.
 */
/**
 * Base search parameter
 *
 * @author fahrur
 * @version 1.0
 */
public abstract class BaseSearchParameters {
    /**
     * Represents the page size.
     *
     * Optional.
     */
    private int pageSize;

    /**
     * Represents the page number.
     *
     * Positive integer or 0 (no paging).
     *
     * Required.
     */
    private int pageNumber;

    /**
     * Represents the sort by field.
     *
     * Optional.
     */
    private String sortBy;

    /**
     * Represents the sort type.
     *
     * Optional.
     */
    private SortType sortType;

    /**
     * Instantiates a new instance.
     */
    protected BaseSearchParameters() {
    }

    /**
     * Returns the pageSize.
     *
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the pageSize.
     *
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Returns the pageNumber.
     *
     * @return the pageNumber
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets the pageNumber.
     *
     * @param pageNumber
     *            the pageNumber to set
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Returns the sortBy.
     *
     * @return the sortBy
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * Sets the sortBy.
     *
     * @param sortBy
     *            the sortBy to set
     */
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Returns the sortType.
     *
     * @return the sortType
     */
    public SortType getSortType() {
        return sortType;
    }

    /**
     * Sets the sortType.
     *
     * @param sortType
     *            the sortType to set
     */
    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

}