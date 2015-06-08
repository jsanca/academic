package designpatterns2.vo.dto;

import java.io.Serializable;

/**
 * Student VO/DTO
 *
 * See all the properties are physical types
 *
 * @author jsanca
 */
public class StudentDTO implements Serializable {

    private String number;
    private String name;
    private Long   birthDate;
    private Long   startDate;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }
}
