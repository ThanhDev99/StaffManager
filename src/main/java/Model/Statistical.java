package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain = true)
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Statistical {
    private Integer id;
    private String staffId;
    private String staffName;
    private Integer month;
    private Integer quarter;
    private Integer year;
    private String violation;
    private String note;
    private Double salary;
}
