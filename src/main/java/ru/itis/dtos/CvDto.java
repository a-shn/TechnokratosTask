package ru.itis.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Cv;

import java.text.SimpleDateFormat;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CvDto {
    private String ownerId;
    // У полей произвольные значения
    private String name;
    private String surname;
    private String contacts;
    private String bornDate;
    private String desiredPosition;
    private String aboutMe;

    public static CvDto from(Cv cv) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return CvDto.builder()
                .ownerId(cv.getOwnerId())
                .name(cv.getName())
                .surname(cv.getSurname())
                .contacts(cv.getContacts())
                .aboutMe(cv.getAboutMe())
                .bornDate(formatter.format(cv.getBornDate()))
                .desiredPosition(cv.getDesiredPosition())
                .aboutMe(cv.getAboutMe())
                .build();
    }
}
