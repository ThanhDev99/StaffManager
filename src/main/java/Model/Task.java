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
public class Task {
    private Integer id;
    private String name;
    private String department;
    private String status;
    private String staff;
    private String description;
    private String regDate;
    private String deadline;
}
