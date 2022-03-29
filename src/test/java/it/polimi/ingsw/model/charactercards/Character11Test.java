package it.polimi.ingsw.model.charactercards;

class Character11Test {

    /*private Character11 character;
    private TableExpertMode table;
    private PlayerExpertMode player1;
    private PlayerExpertMode player2;
    private List<PlayerExpertMode> players;
    private List<Student> students;
    private Student studentChosen;
    private static final int NUM_OF_STUDENTS = 4;

    @BeforeEach
    void init() {
        character = new Character11();
        players = new ArrayList<PlayerExpertMode>();
        player1 = new PlayerExpertMode("1");
        player2 = new PlayerExpertMode("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }


    @Test
    void effect() {
        //creo 3 studenti
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.RED);

        //2 li aggiungo sulla carta
        character.getStudents().add(student1);
        character.getStudents().add(student2);

        //1 lo aggiungo nella bag
        table.getBag().addStudent(student3);

        //lo studente scelto è student1
        character.setStudentChosen(student1);

        character.effect(table);

        //lo studente scelto sarà nella dining room del currentplayer
        assertTrue(player1.getSchoolBoard().getDiningRoom().getLine(student1.getColor()).contains(student1));
        //e non sarà più sulla carta
        assertFalse(character.getStudents().contains(student1));

        //dato che l'unico studente nella bag è student3, lo prendo
        assertFalse(table.getBag().getStudents().contains(student3));
        //e lo metto sulla carta
        assertTrue(character.getStudents().contains(student3));

    }

    @Test
    void setup() {
        //creo 4 studenti
        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.BLUE);
        Student student4 = new Student(ColorS.BLUE);

        //li aggiungo alla bag
        table.getBag().addStudent(student1);
        table.getBag().addStudent(student2);
        table.getBag().addStudent(student3);
        table.getBag().addStudent(student4);

        character.setup(table);

        //dovrebbero essere sulla carta
        assertTrue(character.getStudents().contains(student1));
        assertTrue(character.getStudents().contains(student2));
        assertTrue(character.getStudents().contains(student3));
        assertTrue(character.getStudents().contains(student4));

        //e non dovrebbero essere più nella bag
        assertFalse(character.getStudents().contains(student1));
        assertFalse(character.getStudents().contains(student2));
        assertFalse(character.getStudents().contains(student3));
        assertFalse(character.getStudents().contains(student4));
    }

    @Test
    void setAndGetStudentChosen() {
        studentChosen = new Student(ColorS.BLUE);
        character.setStudentChosen(studentChosen);
        assertEquals(character.getStudentChosen(), studentChosen);
    }*/
}