//HauntedHouse
//James Nelson
//September 8, 2021
//Computer Programming 1

/******************
Create a project with a house/maze/building etc with at least:
 -10 Rooms
 -1 Sound Files
 -Several images using piskel
 -GUI based, text, or a hybrid of the two
******************/

import javax.swing.*;
import java.io.File;
import javax.sound.sampled.*;
import java.util.*;

public class HauntedHouse {
   public static void main(String args[]) {
      // arrayList for the inventory
      ArrayList<String> inventory = new ArrayList<String>();
      // starting the game
      HauntedHouse game = new HauntedHouse();
      game.intro(game, inventory);
   }

   // GUI to show the player if they want to pick an iterm up or leave it
   public void knife(HauntedHouse game, ArrayList<String> inventory) {
      Object knifeObject[] = { "take knife", "leave it" };

      ImageIcon knifeImage = new ImageIcon("./img/knife.png");
      String knifeOption = (String) JOptionPane.showInputDialog(null,
            "You turn around the corner and find a knife on the floor. \n You wonder what left this here \n Pick an option: take knife, leave it",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, knifeImage, knifeObject, knifeObject);
      if (knifeOption.toLowerCase().equals("take knife")) {
         inventory.add("knife");
         JOptionPane.showMessageDialog(null,
               "You pick up the knife and go back to the intersection from where you came from", "HauntedHouse",
               JOptionPane.INFORMATION_MESSAGE, null);
         game.success(game, inventory);
      } else if (knifeOption.toLowerCase().equals("leave it")) {
         JOptionPane.showMessageDialog(null,
               "You leave the knife and go back to the intersection from where you came from", "HauntedHouse",
               JOptionPane.INFORMATION_MESSAGE, null);
      }
   }

   public void medkit(HauntedHouse game, ArrayList<String> inventory) {
      Object medkitObject[] = { "take medkit", "leave it" };

      ImageIcon medkitImage = new ImageIcon("./img/medkit.png");
      String medkitOption = (String) JOptionPane.showInputDialog(null,
            "You turn around the corner and find a medkit on the floor. \n You wonder what left this here \n Pick an option: take medkit, leave it",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, medkitImage, medkitObject, medkitObject);
      if (medkitOption.toLowerCase().equals("take medkit")) {
         inventory.add("medkit");
         JOptionPane.showMessageDialog(null,
               "You pick up the medkit and go back to the intersection from where you came from", "HauntedHouse",
               JOptionPane.INFORMATION_MESSAGE, null);
         game.success(game, inventory);
      } else if (medkitOption.toLowerCase().equals("leave it")) {
         JOptionPane.showMessageDialog(null,
               "You leave the medkit and go back to the intersection from where you came from", "HauntedHouse",
               JOptionPane.INFORMATION_MESSAGE, null);
      }
   }

   // success sound when choosing a correct direction
   public void success(HauntedHouse game, ArrayList<String> inventory) {
      try {
         AudioInputStream input = AudioSystem.getAudioInputStream(new File("audio/success.wav"));
         Clip clip = AudioSystem.getClip();
         Thread.sleep(10);
         clip.open(input);
         clip.loop(0);
         Thread.sleep(1000);
      } catch (Exception sound) {
      }
   }

   // death sound when choosing an incorrect direction
   public void died(HauntedHouse game, ArrayList<String> inventory) {
      try {
         AudioInputStream input = AudioSystem.getAudioInputStream(new File("audio/died.wav"));
         Clip clip = AudioSystem.getClip();
         Thread.sleep(10);
         clip.open(input);
         clip.loop(0);
         Thread.sleep(1000);
      } catch (Exception sound) {
      }
   }

   // intro sequence with opening lore and map
   public void intro(HauntedHouse game, ArrayList<String> inventory) {
      inventory.clear();
      ImageIcon maze = new ImageIcon("./img/startMaze.png");
      Object introObject[] = { "start", "exit" };

      String introOption = (String) JOptionPane.showInputDialog(null,
            "HauntedHouse Maze Project \n You have arrived at a House that appears to be Haunted. \n The door is locked so you decide to go to the back. \n You enter the back in which the walls are vines. \n As you enter, the entrance behind you closes and now you are in a maze that you must escape. \n There may be unknown creatures here as well. \n There is a map on your left. \n Light green is the start\n Light red is the exit to the stairs \n Dark red are enemies \n Light blue are items \n Good Luck \n Select an option: start, exit",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, maze, introObject, introObject[0]);

      if (introOption.toLowerCase().equals("start")) {
         game.success(game, inventory);
         game.start(game, inventory);
      } else if (introOption.toLowerCase().equals("exit")) {
         System.exit(0);
      } else {
         game.invalid(game, inventory);
         game.intro(game, inventory);
      }
   }

   // invalid screen if a selection is invalid
   public void invalid(HauntedHouse game, ArrayList<String> inventory) {
      JOptionPane.showMessageDialog(null, "Invalid Selection!", "HauntedHouse", JOptionPane.ERROR_MESSAGE, null);
   }

   public void back(HauntedHouse game, ArrayList<String> inventory) {
      JOptionPane.showMessageDialog(null, "You go back to the intersection from where you came from", "HauntedHouse",
            JOptionPane.INFORMATION_MESSAGE, null);
   }

   // start screen with the first intersection and choice
   public void start(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon start = new ImageIcon("./img/start.png");
      Object startObject[] = { "straight", "left", "go back" };
      String startOption = (String) JOptionPane.showInputDialog(null,
            "You have entered the maze and you arrive at an intersection. Choose a direction: straight, left, go back",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, start, startObject, startObject[0]);

      if (startOption.toLowerCase().equals("straight")) {
         game.success(game, inventory);
         game.s1(game, inventory);
      } else if (startOption.toLowerCase().equals("left")) {
         game.died(game, inventory);
         game.death(game, inventory);
         game.start(game, inventory);
      } else if (startOption.toLowerCase().equals("go back")) {
         game.back(game, inventory);
         game.intro(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.start(game, inventory);
      }
   }

   // death screen with an option to restart or exit
   public void death(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon dead = new ImageIcon("./img/dead.png");
      ImageIcon enemy = new ImageIcon("./img/s4Image.png");
      Object deathObject[] = { "start over", "exit" };
      Object fightObject[] = { "fight", "run" };

      String fightOption = (String) JOptionPane.showInputDialog(null,
            "As you turn the corner, a monster appears and comes towards you \n Choose an option: fight, run",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, enemy, fightObject, fightObject[0]);

      if (fightOption.toLowerCase().equals("fight") && !inventory.contains("knife")) {
         game.died(game, inventory);
         String deathOption = (String) JOptionPane.showInputDialog(null,
               "You tried to kill the monster with your hands but it didn't work and the monster has killed you \n Choose an option: start over, exit",
               "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, dead, deathObject, deathObject[0]);

         if (deathOption.toLowerCase().equals("start over")) {
            game.success(game, inventory);
            game.intro(game, inventory);
         } else if (deathOption.toLowerCase().equals("exit")) {
            System.exit(0);
         } else {
            game.invalid(game, inventory);
            game.death(game, inventory);
         }
      } else if (fightOption.toLowerCase().equals("fight") && inventory.contains("knife")) {
         JOptionPane.showMessageDialog(null,
               "You successfully killed the monster and you go back to the intersection from where you came",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, null);

      } else if (fightOption.toLowerCase().equals("run")) {
         JOptionPane.showMessageDialog(null,
               "You successfully ran away from the monster and you go back to the intersection from where you came",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, null);
      } else {
         game.invalid(game, inventory);
         game.death(game, inventory);
      }
   }

   // first option screen with an intersection
   public void s1(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon s1Image = new ImageIcon("./img/s1Image.png");
      Object s1Object[] = { "left", "right", "back right", "go back" };
      String s1Option = (String) JOptionPane.showInputDialog(null,
            "You move through the maze and you encountered an intersection \n Choose a direction: left, right, back right",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, s1Image, s1Object, s1Object[0]);

      if (s1Option.toLowerCase().equals("left")) {
         game.success(game, inventory);
         game.i1(game, inventory);
      } else if (s1Option.toLowerCase().equals("right")) {
         game.success(game, inventory);
         game.knife(game, inventory);
         game.s1(game, inventory);
      } else if (s1Option.toLowerCase().equals("back right")) {
         game.died(game, inventory);
         game.death(game, inventory);
         game.s1(game, inventory);
      } else if (s1Option.toLowerCase().equals("go back")) {
         game.back(game, inventory);
         game.start(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.s1(game, inventory);
      }

   }

   // interlude screen through a hallway
   public void i1(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon i1Image = new ImageIcon("./img/i1Image.png");
      Object i1Object[] = { "straight", "go back" };
      String i1Option = (String) JOptionPane.showInputDialog(null,
            "As you walk through the corridor, you wonder who lived here, and what creatures are in here. \n You hear something behind you in the distance in the maze \n Choose a direction: straight, go back",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, i1Image, i1Object, i1Object[0]);
      if (i1Option.toLowerCase().equals("straight")) {
         game.success(game, inventory);
         game.s2(game, inventory);
      } else if (i1Option.toLowerCase().equals("go back")) {
         game.back(game, inventory);
         game.s1(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.i1(game, inventory);
      }
   }

   // second option screen with another intersection
   public void s2(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon s2Image = new ImageIcon("./img/s2Image.png");
      Object s2Object[] = { "right", "left", "go back" };
      String s2Option = (String) JOptionPane.showInputDialog(null,
            "You walk past the corridor and the noises from behind you, You have encounted another intersection. \n Choose a direction: right, left",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, s2Image, s2Object, s2Object[0]);
      if (s2Option.toLowerCase().equals("right")) {
         game.success(game, inventory);
         game.s3(game, inventory);
      } else if (s2Option.toLowerCase().equals("left")) {
         game.success(game, inventory);
         game.medkit(game, inventory);
         game.s2(game, inventory);
      } else if (s2Option.toLowerCase().equals("go back")) {
         game.back(game, inventory);
         game.i1(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.s2(game, inventory);
      }
   }

   // third option with another intersection
   public void s3(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon s3Image = new ImageIcon("./img/s3Image.png");
      Object s3Object[] = { "right", "left", "go back" };
      String s3Option = (String) JOptionPane.showInputDialog(null,
            "The end seems to be approaching, but the maze continues and you reach another intersection. There appear to be noises all around you \n Choose a direction: right, left",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, s3Image, s3Object, s3Object[0]);
      if (s3Option.toLowerCase().equals("left")) {
         game.success(game, inventory);
         game.s4(game, inventory);
      } else if (s3Option.toLowerCase().equals("right")) {
         game.died(game, inventory);
         game.death(game, inventory);
         game.s3(game, inventory);
      } else if (s3Option.toLowerCase().equals("go back")) {
         game.back(game, inventory);
         game.s2(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.s3(game, inventory);
      }
   }

   // fourth option with monster approaching you and you having to fight it
   public void s4(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon s4Image = new ImageIcon("./img/s4Image.png");
      ImageIcon dead = new ImageIcon("./img/dead.png");
      Object deathObject[] = { "start over", "exit" };
      Object fightObject[] = { "fight", "run" };
      String s4Option = (String) JOptionPane.showInputDialog(null,
            "As you turn left, you see the exit, but a monster from the left sees you and comes towards you from the corridor. \n Choose an option: fight, run",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, s4Image, fightObject, fightObject[0]);
      if (s4Option.toLowerCase().equals("fight") && !inventory.contains("knife")) {
         game.died(game, inventory);
         String deathOption = (String) JOptionPane.showInputDialog(null,
               "You tried to kill the monster with your hands but it didn't work and the monster has killed you \n Choose an option: start over, exit",
               "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, dead, deathObject, deathObject[0]);

         if (deathOption.toLowerCase().equals("start over")) {
            game.success(game, inventory);
            game.intro(game, inventory);
         } else if (deathOption.toLowerCase().equals("exit")) {
            System.exit(0);
         } else {
            game.invalid(game, inventory);
            game.death(game, inventory);
         }
      } else if (s4Option.toLowerCase().equals("fight") && inventory.contains("knife")) {
         game.success(game, inventory);
         game.mazeEnd(game, inventory);

      } else if (s4Option.toLowerCase().equals("run")) {
         JOptionPane.showMessageDialog(null,
               "You successfully ran away from the monster and you go back to the intersection from where you came",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, null);
         game.s3(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.s4(game, inventory);
      }
   }

   // win screen with an option to exit or restart
   public void mazeEnd(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon win = new ImageIcon("./img/stairs.png");
      Object mazeEndObject[] = { "enter", "go back" };

      String mazeEndOption = (String) JOptionPane.showInputDialog(null,
            "You fought the monster and you successfully killed the monster with your knife. \n As you go through the exit, there is a flight of stairs to an upper level \n There is a door at the top. \n Enter an option: enter, go back ",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, win, mazeEndObject, mazeEndObject[0]);

      if (mazeEndOption.toLowerCase().equals("enter")) {
         game.success(game, inventory);
         game.main(game, inventory);
      } else if (mazeEndOption.toLowerCase().equals("go back")) {
         game.back(game, inventory);
         game.s4(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.mazeEnd(game, inventory);
      }
   }

   public void main(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon mainImage = new ImageIcon("./img/mainImage.png");
      Object mainObject[] = { "enter classroom", "enter bedroom", "go towards red door", "go back" };

      String mainOption = (String) JOptionPane.showInputDialog(null,
            "You go up the stairs and you seem to have entered the Haunted House. \n There are 2 rooms that you can go in and there is an ominous large red door in front of you \n You wonder how to get in there \n Choose an option: enter classroom, enter bedroom, go towards red door, go back",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, mainImage, mainObject, mainObject[0]);

      if (mainOption.toLowerCase().equals("enter classroom")) {
         game.success(game, inventory);
         game.classroom(game, inventory);
      } else if (mainOption.toLowerCase().equals("enter bedroom")) {
         game.success(game, inventory);
         game.bedroom(game, inventory);
      } else if (mainOption.toLowerCase().equals("go towards red door")) {
         game.success(game, inventory);
         game.door(game, inventory);
      } else if (mainOption.toLowerCase().equals("go back")) {
         game.back(game, inventory);
         game.mazeEnd(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.main(game, inventory);
      }
   }

   public void classroom(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon classroomImage = new ImageIcon("./img/classroomImage.png");
      ImageIcon tableImage = new ImageIcon("./img/tableImage.png");
      ImageIcon chairImage = new ImageIcon("./img/chairImage.png");
      ImageIcon rustyKnifeImage = new ImageIcon("./img/rustyKnife.png");
      ImageIcon chalkBoardImage = new ImageIcon("./img/chalkBoardImage.png");
      ImageIcon computerImage = new ImageIcon("./img/computerImage.png");
      ImageIcon cabinetImage = new ImageIcon("./img/cabinetImage.png");
      ImageIcon neutralImage = new ImageIcon("./img/neutralImage.png");
      Object classroomObject[] = { "front table", "desk", "chair", "computer", "chalk board", "cabinet", "go back" };
      Object cabinetObject[] = { "open cabinet", "go back" };
      Object neutralObject[] = { "exit", "start over" };

      String classroomOption = (String) JOptionPane.showInputDialog(null,
            "You go to the right to the classroom and you go inside. \n It seems to be abandoned and there are many items appeared to be scattered around the room. \n Choose an item/option: front table, desk, chair, computer, chalk board, cabinet, go back",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, classroomImage, classroomObject, classroomObject[0]);

      if (classroomOption.toLowerCase().equals("front table")) {
         JOptionPane.showMessageDialog(null,
               "You go over to the front table and find a lot of papers and pens. \n Nothing much of use here.",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, tableImage);
         game.classroom(game, inventory);
      } else if (classroomOption.toLowerCase().equals("desk")) {
         JOptionPane.showMessageDialog(null,
               "You look on the desk and you find a rusty knife on the table. \n Even though you already have a nicer one, it might come in handy if it breaks.",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, rustyKnifeImage);
         inventory.add("rusty knife");
         game.classroom(game, inventory);
      } else if (classroomOption.toLowerCase().equals("chair")) {
         JOptionPane.showMessageDialog(null,
               "You go over to the chair and see what is around it. \n just some paper and books. \n Nothing really of use here",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, chairImage);
         game.classroom(game, inventory);
      } else if (classroomOption.toLowerCase().equals("computer")) {
         JOptionPane.showMessageDialog(null,
               "You go over to the computer and see what is on screen. \n A text file appears to be open with a part of a code written there. \n You will have to look around more to find the rest of the code somewhere. \n You go back to see the classroom for more items and clues.",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, computerImage);
         game.classroom(game, inventory);
      } else if (classroomOption.toLowerCase().equals("chalk board")) {
         JOptionPane.showMessageDialog(null,
               "You go over to the chalk board and look at it. \n There seems to be part of a code written on the chalk board. \n You will have to look around more for the rest of the code. \n You go back to see the classroom for more items and clues.",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, chalkBoardImage);
         game.classroom(game, inventory);
      } else if (classroomOption.toLowerCase().equals("cabinet")) {
         String cabinetOption = (String) JOptionPane.showInputDialog(null,
               "You go to the cabinet and at first it appears to just have junk in it, but you hear some kind of wormhole/dimesional sound coming from it as well. \n Select an option: open cabinet, go back",
               "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, cabinetImage, cabinetObject, cabinetObject[0]);

         if (cabinetOption.toLowerCase().equals("open cabinet")) {
            game.success(game, inventory);
            String neutralOption = (String) JOptionPane.showInputDialog(null,
                  "As you open the cabinet you get sucked into the wormhole in the bottom. \n You go back home where everything seems to be normal. \n But you don't know what kind of still remianed in that house. Select an option: exit, start over",
                  "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, neutralImage, neutralObject, neutralObject[0]);
            if (neutralOption.toLowerCase().equals("exit")) {
               System.exit(0);
            } else if (neutralOption.toLowerCase().equals("start over")) {
               game.intro(game, inventory);
            }
         } else if (cabinetOption.toLowerCase().equals("go back")) {
            game.back(game, inventory);
            game.classroom(game, inventory);
         }
      } else if (classroomOption.toLowerCase().equals("go back")) {
         game.back(game, inventory);
         game.main(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.classroom(game, inventory);
      }
   }

   public void bedroom(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon bedroomImage = new ImageIcon("./img/bedroomImage.png");
      ImageIcon bedsideImage = new ImageIcon("./img/bedsideImage.png");
      ImageIcon carpetImage = new ImageIcon("./img/carpetImage.png");
      ImageIcon bedImage = new ImageIcon("./img/bedImage.png");
      ImageIcon medkitImage = new ImageIcon("./img/medkit.png");
      Object bedroomObject[] = { "bed", "bedside table", "dresser", "carpet", "go back" };

      String bedroomOption = (String) JOptionPane.showInputDialog(null,
            "You go left into the bedroom and there are many pieces of furniture present with many things around the room \n Choose an option: bed, bedside table, dresser, carpet, go back",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, bedroomImage, bedroomObject, bedroomObject[0]);

      if (bedroomOption.toLowerCase().equals("bed")) {
         JOptionPane.showMessageDialog(null,
               "You go over to the bed and observe it. \n You notice on the side that there is a part of a code that is marked on the side of the bed \n You will have to look around for the rest of the code.",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, bedImage);
         game.bedroom(game, inventory);
      } else if (bedroomOption.toLowerCase().equals("bedside table")) {
         JOptionPane.showMessageDialog(null,
               "You go over to the bedside table and you open the drawers. There is just junk and random things. \n Nothing much of use here.",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, bedsideImage);
         game.bedroom(game, inventory);
      } else if (bedroomOption.toLowerCase().equals("dresser")) {
         JOptionPane.showMessageDialog(null,
               "You go over to the dresser and you open the drawers. There is just junk and some clothes. \n But then on top of the dresser you notice medkit on top \n You take the medkit just in case for the future",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, medkitImage);
         inventory.add("medkit");
         game.bedroom(game, inventory);
      } else if (bedroomOption.toLowerCase().equals("carpet")) {
         JOptionPane.showMessageDialog(null,
               "You go over to the carpet and observe it. \n You notice that on the carpet is a part of a code  \n You will have to look around for the rest of the code.",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, carpetImage);
         game.bedroom(game, inventory);
      } else if (bedroomOption.toLowerCase().equals("go back")) {
         game.back(game, inventory);
         game.main(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.bedroom(game, inventory);
      }
   }

   public void door(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon doorImage = new ImageIcon("./img/doorImage.png");

      String doorOption = (String) JOptionPane.showInputDialog(null,
            "You arrive at the ominous door but it is locked with a code input. \n Enter the code to continue. \n If you do not know the code, enter an invalid code and you will go back to the main room",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, doorImage, null, null);

      if (doorOption.toLowerCase().equals("2697")) {
         game.success(game, inventory);
         game.died(game, inventory);
         game.boss(game, inventory);
      } else {
         game.died(game, inventory);
         JOptionPane.showMessageDialog(null, "Incorrect code, returning you back to the main room", "HauntedHouse",
               JOptionPane.ERROR_MESSAGE, null);
         game.main(game, inventory);
      }
   }

   public void boss(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon bossImage = new ImageIcon("./img/bossImage.png");
      ImageIcon goodImage = new ImageIcon("./img/goodImage.png");
      ImageIcon badImage = new ImageIcon("./img/badImage.png");
      int health = 100;
      int bossHealth = 100;
      Object bossObject[] = { "fight", "heal", "defend" };
      Object goodObject[] = { "exit", "start over" };
      Object badObject[] = { "exit", "start over" };

      JOptionPane.showMessageDialog(null,
            "As you enter the code and walk through the door, you see a large boss monster coming at you. \n It says that you shall not pass unless you defeat them \n Let the battle commense!",
            "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, bossImage);
      while (true) {
         Random rand = new Random();
         int bossAttack = rand.nextInt(50);

         String bossOption = (String) JOptionPane.showInputDialog(null,
               "Fight the boss! \n Your hp is: " + health + ". The boss health is: " + bossHealth + ".", "HauntedHouse",
               JOptionPane.YES_NO_CANCEL_OPTION, bossImage, bossObject, bossObject[0]);
         if (bossOption.toLowerCase().equals("fight")) {
            bossHealth = bossHealth - 20;
            health = health - bossAttack;

            JOptionPane.showMessageDialog(null,
                  "You have attacked for 20 damage and the boss has attacked you for " + bossAttack
                        + " damage. \n Your health is: " + health + ". The boss health is: " + bossHealth + ".",
                  "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, null);
            if (bossHealth <= 0) {
               game.success(game, inventory);
               String goodOption = (String) JOptionPane.showInputDialog(null,
                     "You have successfully killed the boss with your knife and were able to escape the Haunted House. \n Peace has been restored with the world and everything is normal, for now... Choose an option: exit, start over",
                     "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, goodImage, goodObject, goodObject[0]);
               if (goodOption.toLowerCase().equals("exit")) {
                  System.exit(0);
               } else if (goodOption.toLowerCase().equals("start over")) {
                  game.intro(game, inventory);
               }
            }
            if (health <= 0) {
               game.died(game, inventory);
               String badOption = (String) JOptionPane.showInputDialog(null,
                     "As you tried to kill the boss, the boss killed you instead. The world will never be at peace again... Choose an option: exit, start over",
                     "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, badImage, badObject, badObject[0]);
               if (badOption.toLowerCase().equals("exit")) {
                  System.exit(0);
               } else if (badOption.toLowerCase().equals("start over")) {
                  game.intro(game, inventory);
               }
            }
            bossAttack = rand.nextInt(40);
         } else if (bossOption.toLowerCase().equals("heal") && inventory.contains("medkit")) {
            health = health + 30;
            JOptionPane.showMessageDialog(null, "You have healed for 30 damage", "HauntedHouse",
                  JOptionPane.INFORMATION_MESSAGE, null);
            inventory.remove("medkit");
         } else if (bossOption.toLowerCase().equals("heal") && !inventory.contains("medkit")) {
            JOptionPane.showMessageDialog(null, "You do not have any healing in your inventory!", "HauntedHouse",
                  JOptionPane.ERROR_MESSAGE, null);
         } else if (bossOption.toLowerCase().equals("defend")) {
            JOptionPane.showMessageDialog(null, "You have defended against the boss and took 0 damage", "HauntedHouse",
                  JOptionPane.INFORMATION_MESSAGE, null);
         }
      }
   }
}
