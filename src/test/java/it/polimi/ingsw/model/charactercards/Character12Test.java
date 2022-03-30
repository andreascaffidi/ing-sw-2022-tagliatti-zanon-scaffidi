package it.polimi.ingsw.model.charactercards;

class Character12Test {

    /*private Character12 character;
    private TableExpertMode table;
    private PlayerExpertMode player1;
    private PlayerExpertMode player2;
    private List<PlayerExpertMode> players;

    @BeforeEach
    void init() {
        character = new Character12();
        character.setColor(ColorS.BLUE);
        players = new ArrayList<>();
        player1 = new PlayerExpertMode("1");
        player2 = new PlayerExpertMode("2");
        players.add(player1);
        players.add(player2);
        table = new TableExpertMode(players);
    }

    @Test
    void effect() {
        //Player1 ha 4 studenti BLU,
        // quindi quando chiamo effect(), 3 di questi andranno nella bag e 1 rimane in schoolboard

        Student student1 = new Student(ColorS.BLUE);
        Student student2 = new Student(ColorS.BLUE);
        Student student3 = new Student(ColorS.BLUE);
        Student student7 = new Student(ColorS.BLUE);
        player1.getSchoolBoard().getDiningRoom().addStudent(student1);
        player1.getSchoolBoard().getDiningRoom().addStudent(student2);
        player1.getSchoolBoard().getDiningRoom().addStudent(student3);
        player1.getSchoolBoard().getDiningRoom().addStudent(student7);
        character.effect(table);
        assertFalse(player1.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student1));
        assertFalse(player1.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student2));
        assertFalse(player1.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student3));
        assertTrue(player1.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student7));
        assertTrue(table.getBag().getStudents().contains(student1));
        assertTrue(table.getBag().getStudents().contains(student2));
        assertTrue(table.getBag().getStudents().contains(student3));
        assertFalse(table.getBag().getStudents().contains(student7));

        //Player2 ha 2 studenti BLU e 1 ROSSO,
        //quindi quando chiamo effect(), devo metterli tutti nella bag e nessuno rimane in schoolboard

        Student student4 = new Student(ColorS.BLUE);
        Student student5 = new Student(ColorS.BLUE);
        Student student6 = new Student(ColorS.RED);
        player2.getSchoolBoard().getDiningRoom().addStudent(student4);
        player2.getSchoolBoard().getDiningRoom().addStudent(student5);
        player2.getSchoolBoard().getDiningRoom().addStudent(student6);
        character.effect(table);
        assertFalse(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student4));
        assertFalse(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.BLUE).contains(student5));
        assertFalse(player2.getSchoolBoard().getDiningRoom().getLine(ColorS.RED).contains(student6));
        assertTrue(table.getBag().getStudents().contains(student4));
        assertTrue(table.getBag().getStudents().contains(student5));
        assertTrue(table.getBag().getStudents().contains(student6));
    }

    @Test
    void setup() {
        assertTrue(true, "not needed");
    }

    @Test
    void setAndGetColor() {
        character.setColor(ColorS.BLUE);
        assertEquals(character.getColor(), ColorS.BLUE);
    }*/
}