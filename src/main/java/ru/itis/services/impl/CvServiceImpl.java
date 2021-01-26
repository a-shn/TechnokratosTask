package ru.itis.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dtos.CvDto;
import ru.itis.models.Cv;
import ru.itis.repositories.CvRepository;
import ru.itis.services.interfaces.CvService;

import java.text.ParseException;
import java.util.List;

@Service
@AllArgsConstructor
public class CvServiceImpl implements CvService {
    private final CvRepository cvRepository;

    @Override
    public void publish(CvDto cvDto) {
        try {
            cvRepository.save(Cv.from(cvDto));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date!");
        }
    }

    @Override
    public Cv getCvById(String id) {
        return cvRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id!"));
    }

    @Override
    public void deleteCvById(String id) {
        cvRepository.deleteById(id);
    }

    @Override
    public void updateCv(CvDto cvDto, String id) {
        try {
            Cv cv = Cv.from(cvDto);
            cv.set_id(id);
            cvRepository.save(cv);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date!");
        }
    }

    @Override
    public List<Cv> getCvs() {
        return cvRepository.findAll();
    }


}
