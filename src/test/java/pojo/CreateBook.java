package pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBook {
    private String id;
    private String modelType;
    private String name;
    private int edition;
    private int trimSizeCode;
    private double trimSizeWidth;
    private String state;
    private long isbn;
    private String publicationType;
    private int numberOfPages;
    private String  textColorCode;
    private String hasBleeds;
    private String hasPerforation;
    private String  subjectCodeBISAC;
}

