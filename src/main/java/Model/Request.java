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
public class Request {
    private Integer id;
    private String staff;
    private String departmentReceive;
    private String departmentSend;
    private String dateSend;
    private String status;
    private String content;
}
