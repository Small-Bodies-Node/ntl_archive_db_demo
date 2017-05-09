package gov.nasa.pds.entities;

public class Page {
    int pageNumber;
    int itemsPerPage;

    public Page() {
        // Empty
    }

    public Page(int pageNumber, int itemsPerPage) {
        this.pageNumber = pageNumber;
        this.itemsPerPage = itemsPerPage;
    }

    // pageNumber property
    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    // itemsPerPage property
    public int getItemsPerPage() {
        return itemsPerPage;
    }
    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
}
