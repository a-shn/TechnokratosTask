package ru.itis.services.interfaces;

import ru.itis.dtos.CvDto;
import ru.itis.models.Cv;

import java.util.List;

public interface CvService {
    void publish(CvDto cv);

    Cv getCvById(String id);

    void deleteCvById(String id);

    void updateCv(CvDto cvDto, String id);

    List<Cv> getCvs();
}
