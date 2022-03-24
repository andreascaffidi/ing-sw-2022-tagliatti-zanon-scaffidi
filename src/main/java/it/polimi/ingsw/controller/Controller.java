package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.Wizard;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Controller {

    private Table table;
    private RoundPhases roundPhase;

    private Map<Player, Integer[]> playerParams;


    public Controller(List<Player> players){

        this.table = new Table(players);
        int randomPlayerIndex = new Random().nextInt(players.size());
        this.table.setCurrentPlayer(players.get(randomPlayerIndex));
        this.roundPhase = RoundPhases.PLANNING;

    }


    /*
        INIZIO ROUND
            FASE PIANIFICAZIONE
                -   per ogni giocatore si pascano 3 studenti e si piazzano su una nuvola
                -   ogni giocatore scehlie carta assistente
     */

        public void setupRound(){

        }



        public void playAssistant(int i, Player player){
            if(this.roundPhase != RoundPhases.PLANNING){
                throw new RuntimeException("");
            }
           //todo: implementare

            Assistant card = new Assistant(0,0, Wizard.WIZARD_1);
            Integer[] params = new Integer[]{card.getValue(),card.getMotherNatureMovements()};

            this.playerParams.put(player, params);
        }




        private Player nextPlayer(){
            return null;
        }

        /*
            studente , from , to
            colore, da[entrance,cloud,island], a[entrance,cloud,island]
        */
    //FASE 1 - SPOSTARE 3 STUDENTI
        public void moveStudent(){

        }

        private void replaceProfessors(){

        }


        //FASE 2
        public void moveMotherNature(){

        }

        //FASE 3
        public void chooseCloud(){

        }



        private void endRound(){

        }














}
