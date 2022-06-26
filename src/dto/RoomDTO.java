package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class RoomDTO {
    private String roomID;
    private String roomType;
    private int roomQty;
    private double monthlyRent;
}
