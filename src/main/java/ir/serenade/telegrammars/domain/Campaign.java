package ir.serenade.telegrammars.domain;

import javax.persistence.*;
import java.sql.Ref;
import java.util.Date;

/**
 * Created by serenade on 8/7/17.
 */
@Entity
public class Campaign {

    public static enum BudgetGroup {
        BELOW_1_MILLION("budget.group.1"), BETWEEN_1_10_MILLION("budget.group.2"),
        BETWEEN_10_100_MILLION("budget.group.3"), ABOVE_100_MILLION("budget.group.4");
        private String name;

        BudgetGroup(String name) {
            this.name = name;
        }



    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1024)
    private String description;

    @Column(nullable = false)
    private String callToAction;

    private byte[] mediaData;

    private String mediaType;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    private BudgetGroup budget;

    @Column(length = 1024)
    private String text;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;


    @OneToOne
    @JoinColumn(name = "reference_id", nullable = true)
    private Reference reference;


    public Campaign() {
    }

    public Campaign(User user, String name) {
        this.name = name;
        this.owner = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCallToAction() {
        return callToAction;
    }

    public void setCallToAction(String callToAction) {
        this.callToAction = callToAction;
    }

    public byte[] getMediaData() {
        return mediaData;
    }

    public void setMediaData(byte[] mediaData) {
        this.mediaData = mediaData;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BudgetGroup getBudget() {
        return budget;
    }

    public void setBudget(BudgetGroup budget) {
        this.budget = budget;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }
}
