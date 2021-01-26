package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.itis.dtos.CvDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Document(collection = "cv")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cv {
    @Id
    private String _id;
    // У полей произвольные значения
    private String name;
    private String surname;
    private String contacts;
    private Date bornDate;
    private String desiredPosition;
    private String aboutMe;

    public static Cv from(CvDto cvDto) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return Cv.builder()
                .name(cvDto.getName())
                .surname(cvDto.getSurname())
                .contacts(cvDto.getContacts())
                .aboutMe(cvDto.getAboutMe())
                .bornDate(formatter.parse(cvDto.getBornDate()))
                .desiredPosition(cvDto.getDesiredPosition())
                .aboutMe(cvDto.getAboutMe())
                .build();
    }
}
