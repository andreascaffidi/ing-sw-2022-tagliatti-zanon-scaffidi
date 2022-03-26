package it.polimi.ingsw.model.schoolBoard;

import it.polimi.ingsw.exceptions.GetCoinException;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.pawns.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DiningRoomExpertModeTest {
    DiningRoomExpertMode diningRoomExpertMode;
    @BeforeEach
    void init() {
        diningRoomExpertMode = new DiningRoomExpertMode();
    }
    @Test
    void addStudent() {
        ColorS randColor = ColorS.values()[new Random().nextInt(ColorS.values().length)];
        diningRoomExpertMode.addStudent(new Student(randColor));
        diningRoomExpertMode.addStudent(new Student(randColor));
        GetCoinException getCoinException = Assertions.assertThrows(
                GetCoinException.class,()->diningRoomExpertMode.addStudent(new Student(randColor))
        ) ;
    }

    @Test
    void removeStudent() {
    }
}