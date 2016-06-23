package com.example.capstone.teamchooser.helperClasses;

import java.util.ArrayList;

//We will be using this class to read the instructions from a file and add it
//the different sections to the list in AppInfo

public class InstructionsManager {
    private static ArrayList<Instruction> m_instructionsList = null;

    public static ArrayList<Instruction> getAllInstructions() {
        if( m_instructionsList == null ) {
            preCompiledInstructions();
        }
        return m_instructionsList;
    }

    public static ArrayList<Instruction> generateFromFile() {
        return null;
    }

    //This is a method for adding new instructions directly from a different class and with no
    //need for having a file
    public static void generateSimple( String title, String description ) {
        if ( m_instructionsList == null ) {
            m_instructionsList = new ArrayList<>();
        }

        Instruction newInstruction = new Instruction();
        newInstruction.setTitle( title );
        newInstruction.setDescription( description );

        m_instructionsList.add( newInstruction );
    }

    private static void preCompiledInstructions() {
        if ( m_instructionsList == null ) {
            m_instructionsList = new ArrayList<>();
        } else {
            return;
        }
        m_instructionsList.add( new Instruction(
                "Quick-Start Instructions",
                "1. On the Game List screen, press Add game.  \n" +
                "2. Enter the name for your game (for example, \"Friday Hockey\" or \"Monday Footy\") and press Save.  \n" +
                "3. Select your new game from the Game Listscreen, by tapping it.  This will take you to the game screen.\n" +
                "4. Press “+” to begin entering the names and ratings of all the people who may play in the game.\n" +
                "5. For each player, input a name and a rating number between 1 and 10. (See below for advice on how to choose the ratings; these are important if you want to achieve balanced teams.)\n" +
                "6. Press Save for each new player.\n" +
                "7. When done entering players, press Done.\n" +
                "8. Back on the game screen (such as \"Friday Hockey\") you'll see the list of players you have entered. Indicate who is present on a given day by tapping their name. Tap the name again to mark them as away.\n" +
                "9. Once you've selected everyone who is present, press Make teams. "
        ));

        m_instructionsList.add(new Instruction(
                "Setting Up Games",
                "You can create as many games as you'd like, with no limit to the number of players in each game. Change game settings by tapping the 'i' (info) circle next to the game name in the Game List. You can change the details of a game in the Game Settings screen, and also create a duplicate. Duplicating a game makes it easy to experiment, for example with different player ratings.\n" +
                "On the Game Settings screen, setting the Balance Offence-Defence Count switch to On will balance the number of players you have designated as offence with the number you have designated as defence."
        ));

        m_instructionsList.add(new Instruction(
                "Super Optimizer with O-D Quality Balance",
                "You can also make use of an experimental version of the balance optimizer, called the “Super Optimizer,” in the Game Settings screen. This Super Optimizer applies a different approach to balancing the teams. Additionally, it enables you to balance the average ratings, or quality, of offence and defence, not just the count. \n" +
                        "\n" +
                        "Notes about the Super Optimizer: \n" +
                        "\n" +
                        "If you set Balance O-D Ratings On, the optimizer may sacrifice some of the overall balance between the two teams, but not much.\n" +
                        "In some cases, such as when only a few players are designated as offence or defence, it’s not possible to balance the quality of offence and defence. TeamChooser indicates if it was able to do so on the Make Teams screen by showing “(ODQ Bal)” in the statistics line beside the team heading. If this indicator is not present, this means that there wasn’t sufficient opportunity to do the O-D rating balancing."
        ));

        m_instructionsList.add(new Instruction(
                "Adding and Rating Players",
                "Once you have created a game, click on the game name (on the Game List screen) to access its player screen. Here, press the “+” button to add and rate players. The key to achieving a balanced game is to accurately rate the level of effectiveness of each player on a scale from 1 to 10 (actually, you can use a larger scale if you’d like). The effectiveness rating should boil down all of their attributes to one number.  That number represents the player’s contribution to the outcome of the game. Some skilled players do not necessarily put out huge effort, and may be less effective than unskilled players who try harder.\n" +
                        "\n" +
                        "We suggest the following method to select the ratings for your players: List the players in order of effectiveness. Then, group players who play at a similar level. Assuming a 10-point scale (you can vary this if you’d like), assign the top group a score of 8 the next 6, 4, and so on. Within the groups, make adjustments to reflect differences. Make sure you leave a little room at the bottom and top of the scale to add new players outside of the range of the current players.\n" +
                        "\n" +
                        "If you feel uncomfortable making these ratings yourself, try consulting others.  We have found it helpful to consult with 1 or 2 other people to reach a consensus on the numbers.  However, it is best not to tell too many people what the ratings are, as it is personal and can be sensitive."
        ));

        m_instructionsList.add( new Instruction(
                "Other Players Settings",
                "When you add players, you can set whether they are more suited to offence or defence.  To balance the teams by offence and by defence (in addition to the overall balance), use the switches in the Game Settingsscreen, as described above. You can also set a player to be on a particular team by turning on the Pre-assignswitch, and then selecting the team you wish them to appear on. This is an easy way to put players on the same team, or to ensure they are opponents."
        ));

        m_instructionsList.add( new Instruction(
                "Changing Player Settings",
                "You can edit the player details by first selecting Edit…on the game screen. Then select the 'i' (info) link for the desired player. When you are done, press Done editing players."
        ));

        m_instructionsList.add( new Instruction(
                "What to Balance: The Play or the Score?",
                "If your game type involves a unique player position that can have a big impact on the outcome – such as a goaltender in hockey or soccer, a quarterback in American football, or a pitcher in baseball – you need to consider whether it’s more important to have even play or a close score. If your preference is for even play, then we suggest excluding the unique player position from the player list and just assigning it manually. Alternatively, if you prefer a close score, include the player position and pre-assign the two people who will play this position to opposite teams. Do your best to rate these players according to the contribution they will have, which is generally much stronger than the other players due to their unique position. (Note: In our experience, most players have more fun when the play is balanced, regardless of the score.)"
        ));

        m_instructionsList.add( new Instruction(
                "Making the Teams",
                "Once you’ve checked the names of the players who have shown up for the game, select Make teams. Based on your ratings and the game settings, TeamChooser will optimize the balance of the teams. "
        ));

        m_instructionsList.add( new Instruction(
                "Reviewing the Game Balance",
                "Beside each team heading you will see four numbers. The first number gives the average rating of all players on that team.  The next number, after the “D:” gives the average rating of the defence players. Then you’ll see the average rating of the offence players (after “O:”). Finally, the total number of players on the team is shown after “COUNT”. "
        ));

        m_instructionsList.add( new Instruction(
                "Players Arriving Late",
                "Sometimes players arrive late, after you've selected Make teams. To minimally disrupt the assigned teams and still preserve a balanced game, press the New Arrivals button at the top of the Teams screen. Select Yes in the confirmation message, and then select the new players that have arrived. When you select Make teams again, TeamChooser will assign the late players and make no more than two changes to the current teams. TeamChooser will highlight the names of the players who’ve had their team change so you can let them know. Also, the newly arrived players will be indicated."
        ));

        m_instructionsList.add( new Instruction(
                "Tweaking Teams",
                "To make minor changes to the teams suggested by TeamChooser, press the Tweak button. This will swap two players to make the average rating of each team as close as possible. Of course, if you’ve done a good job rating the players, you can trust TeamChooser’s initial suggestion to provide the best balance."
        ));

        m_instructionsList.add( new Instruction(
                "Disclosure",
                "TeamChooser sends statistics to us (the developers) on the use of the application, including average team ratings, number of players in games and game names. This information helps us understand how TeamChooser is being used, for us to make valuable improvements. Player names and individual ratings are not recorded or transmitted."
        ));

        m_instructionsList.add( new Instruction(
                "Feeback?",
                "Please help us improve TeamChooser. Email us at TeamChooserapp@gmail.com with your suggestions."
        ));
    }
}
