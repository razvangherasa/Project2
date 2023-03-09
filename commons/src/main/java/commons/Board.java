
package commons;

import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @OneToMany
    public List<CList> list;

    /**
     * Constructor for the Board class
     */
    public Board() {
        this.list = new ArrayList<>();
    }

    /**
     * Equals method of the Board class
     * @param obj is an object with which we need to check the equality
     * @return whether the list is equal to the parameter given, or not
     */

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
    /**
     * Hashcode method of the Board class
     * @return the hashcode generated for a list
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * toString method of the Board class
     * @return the board in a human-readable format
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, MULTI_LINE_STYLE);
    }
}