package gov.nasa.pds.ws;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class WSDataFile {
    /**
     * The id of the record of the 'data_file' table
     */
    private long id;

    /**
     * The name of the data file as specified in the DB
     */
    private String name;

    /**
     * The filename of the data file as specified in the DB (only filename, path is not included)
     */
    private String filename;

    /**
     * File's content. Only for ascii files. Null for binary files.
     */
    private String content;

    /**
     * Used only for binary files.
     */
    private DataHandler dataHandler;

    public WSDataFile() {
        // Empty
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the dataHandler
     */
    @XmlMimeType("application/octet-stream")
    public DataHandler getDataHandler() {
        return dataHandler;
    }

    /**
     * @param dataHandler the dataHandler to set
     */
    public void setDataHandler(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }
}
