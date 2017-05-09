package gov.nasa.pds.entities;

import java.util.List;

public class SearchCriteriaList {
    private List<SearchCriteria> list;

    /**
     * Creates SearchCriteriaList instance.
     */
    public SearchCriteriaList() {
        // Emtpy
    }

    // list property
    public List<SearchCriteria> getList() {
        return list;
    }
    public void setList(List<SearchCriteria> list) {
        this.list = list;
    }
}
