package ir.serenade.telegrammars.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Reference {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pinNumber;

    private String postLink;

    private Long totalView;


    @OneToMany(mappedBy = "reference", cascade = CascadeType.ALL)
    @OrderBy("creationDate ASC")
    private List<ReportViews> views;

    public Reference() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public String getPostLink() {
        return postLink;
    }

    public void setPostLink(String postLink) {
        this.postLink = postLink;
    }

    public List<ReportViews> getViews() {
        return views;
    }

    public void setViews(List<ReportViews> views) {
        this.views = views;
    }

    public Long getTotalView() {
        return totalView;
    }

    public void setTotalView(Long totalView) {
        this.totalView = totalView;
    }
}
