package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String country;
    private String dateStart;
    private String department;
    private String ratedCapacity;
    private Boolean sex;
    private String birthday;
    private String image;

    private String time;
    private String note;
    private String isCheckin;
}
