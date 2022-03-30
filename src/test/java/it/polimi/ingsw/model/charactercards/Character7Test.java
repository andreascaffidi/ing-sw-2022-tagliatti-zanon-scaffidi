package it.polimi.ingsw.model.charactercards;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Character7Test {

    /*private Character7 character;
    private TableExpertMode table;
    private PlayerExpertMode player1;
    private PlayerExpertMode player2;
    private List<PlayerExpertMode> players;

    @BeforeEach
    void init() {
        character = new Character7();
        players = new ArrayList<>();
        player1 = new PlayerExpertMode("1");
        player2 = new PlayerExpertMode("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    @Test
    void effect() {

        //creo 3 studenti blu e 2 rossi
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.BLUE);
        Student student4 = new Student(ColorS.RED);
        Student student5 = new Student(ColorS.RED);

        //aggiungo 3 studenti blu alla carta
        character.getCardStudents().add(student1);
        character.getCardStudents().add(student2);
        character.getCardStudents().add(student3);

        //scelgo 2 studenti della carta (BLU)
        character.getCardStudentsChosen().add(student2);

        //scelgo 2 studenti dell'entrata (ROSSI)
        character.getEntranceStudentChosen().add(student4);
        character.getEntranceStudentChosen().add(student5);

        character.effect(this.table);

        //dopo l'effetto mi aspetto che:

        //la carta non contenga più gli studenti BLU
        assertFalse(character.getCardStudents().contains(student1));
        assertFalse(character.getCardStudents().contains(student2));

        //ma contenga quelli rossi
        assertTrue(character.getCardStudents().contains(student4));
        assertTrue(character.getCardStudents().contains(student5));

        //l'entrata non contenga più gli studenti rossi
        assertTrue(character.getEntranceStudentChosen().contains(student1));
        assertTrue(character.getEntranceStudentChosen().contains(student2));

        //ma quelli blu
        assertFalse(character.getEntranceStudentChosen().contains(student4));
        assertFalse(character.getEntranceStudentChosen().contains(student5));
    }

    @Test
    void setup() {
        //aggiungo 6 Students alla Bag
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.BLUE);
        Student student4 = new Student(ColorS.BLUE);
        Student student5 = new Student(ColorS.BLUE);
        Student student6 = new Student(ColorS.BLUE);
        this.table.getBag().addStudent(student1);
        this.table.getBag().addStudent(student2);
        this.table.getBag().addStudent(student3);
        this.table.getBag().addStudent(student4);
        this.table.getBag().addStudent(student5);
        this.table.getBag().addStudent(student6);

        character.setup(this.table);

        //dopo la chiamata di setup assumo che quei 6 studenti siano sulla card
        assertTrue(character.getCardStudents().contains(student1));
        assertTrue(character.getCardStudents().contains(student2));
        assertTrue(character.getCardStudents().contains(student3));
        assertTrue(character.getCardStudents().contains(student4));
        assertTrue(character.getCardStudents().contains(student5));
        assertTrue(character.getCardStudents().contains(student6));

        //e non siano più nella Bag
        assertFalse(this.table.getBag().getStudents().contains(student1));
        assertFalse(this.table.getBag().getStudents().contains(student2));
        assertFalse(this.table.getBag().getStudents().contains(student3));
        assertFalse(this.table.getBag().getStudents().contains(student4));
        assertFalse(this.table.getBag().getStudents().contains(student5));
        assertFalse(this.table.getBag().getStudents().contains(student6));
    }

    @Test
    void setAndGetCardStudentsChosen() {
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        List<Student> students = new ArrayList<Student>();
        students.add(student1);
        students.add(student2);
        character.setCardStudentsChosen(students);
        assertEquals(character.getCardStudentsChosen(), students);
    }

    @Test
    void setAndGetEntranceStudentChosen() {
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        List<Student> students = new ArrayList<Student>();
        students.add(student1);
        students.add(student2);
        character.setEntranceStudentChosen(students);
        assertEquals(character.getEntranceStudentChosen(), students);
    }

    @Test
    void setAndGetCardStudents() {
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        List<Student> students = new ArrayList<Student>();
        students.add(student1);
        students.add(student2);
        character.setCardStudents(students);
        assertEquals(character.getCardStudents(), students);
    }*/
}