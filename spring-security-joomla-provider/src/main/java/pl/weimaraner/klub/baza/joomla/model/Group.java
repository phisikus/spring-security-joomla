package pl.weimaraner.klub.baza.joomla.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class represents usergroup entity from Joomla 3
 */
@Entity
@Table(name = "usergroups")
public class Group {

    /**
     * Group identification number.
     */
    @Id
    @NotNull
    @Column
    private Long id;

    /**
     * This field is a part of Nested Set implementation.
     *
     * @See <a href="http://docs.joomla.org/Using_nested_sets">http://docs.joomla.org/Using_nested_sets</a>
     */
    @NotNull
    @Column(name = "parent_id")
    private Long parentId;


    /**
     * This field is a part of Nested Set implementation.
     *
     * @See <a href="http://docs.joomla.org/Using_nested_sets">http://docs.joomla.org/Using_nested_sets</a>
     */
    @NotNull
    private Long lft;

    /**
     * This field is a part of Nested Set implementation.
     *
     * @See <a href="http://docs.joomla.org/Using_nested_sets">http://docs.joomla.org/Using_nested_sets</a>
     */
    @NotNull
    private Long rgt;

    /**
     * Group name.
     */
    @NotNull
    @Size(max = 100)
    private String title;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getLft() {
        return lft;
    }

    public void setLft(Long lft) {
        this.lft = lft;
    }

    public Long getRgt() {
        return rgt;
    }

    public void setRgt(Long rgt) {
        this.rgt = rgt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", lft=" + lft +
                ", rgt=" + rgt +
                ", title='" + title + '\'' +
                '}';
    }
}
