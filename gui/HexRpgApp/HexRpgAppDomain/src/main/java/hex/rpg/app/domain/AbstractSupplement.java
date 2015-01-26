package hex.rpg.app.domain;

import hex.rpg.core.domain.Supplement;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author hln
 */
public abstract class AbstractSupplement implements Supplement  {

    private Long id;
    private String title;
    private Type type;
    private String shortDescription;
    private String description;
    private String refereeInfo;
    private String refereeNotes;
    private String mediaType;
    private Integer index;
    private byte[] content;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getRefereeInfo() {
        return refereeInfo;
    }

    @Override
    public void setRefereeInfo(String refereeInfo) {
        this.refereeInfo = refereeInfo;
    }

    @Override
    public String getRefereeNotes() {
        return refereeNotes;
    }

    @Override
    public void setRefereeNotes(String refereeNotes) {
        this.refereeNotes = refereeNotes;
    }

    @Override
    public String getMediaType() {
        return mediaType;
    }

    @Override
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public Integer getIndex() {
        return index;
    }

    @Override
    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public InputStream getContent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public byte[] getContentAsByteArray() {
        return content;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public Integer getSize() {
        return content.length;
    }

    @Override
    public List<Supplement> getSupplements() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public void addSupplement(Supplement supplement) {
    }

    @Override
    public boolean hasSupplements() {
        return false;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public int compareTo(Supplement obj) {
        return this.getId().compareTo(((Supplement) obj).getId());
    }

    @Override
    public String createPath() {
        String result = BASE_PATH + getOwnerClassName() + "/" + title;
        if (getFileExtension() != null) {
            result += "." + getFileExtension();
        }
        return result;
    }

    protected abstract String getOwnerClassName();

    protected String getFileExtension() {
        if (mediaType.startsWith("text")) {
            return "txt";
        } else if (mediaType.startsWith("image")) {
            return mediaType.substring(mediaType.lastIndexOf("/") + 1);
        } else if (mediaType.contains("+")) {
            return mediaType.substring(mediaType.lastIndexOf("+") + 1);
        }
        return null;
    }

}
