package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Accessors(chain = true)
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    private String staffId;
    private String password;
    private String department;

    private Boolean isManager;

    private Boolean checkIn;
    private Boolean task;
    private Boolean request;
    private Boolean staff;
    private Boolean staffManager;
    private Boolean statistical;
    private Boolean account;
    private Boolean admin;
    private Boolean chat;
}
