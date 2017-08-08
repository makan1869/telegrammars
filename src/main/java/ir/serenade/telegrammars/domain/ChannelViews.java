package ir.serenade.telegrammars.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ChannelViews {

    @Id
    @GeneratedValue
    private Long id;

    private String channelName;

    private Date creationDate;

    private Long views;

    @ManyToOne
    @JoinColumn(name = "reference_number")
    private Reference reference;

    public ChannelViews() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }
}
