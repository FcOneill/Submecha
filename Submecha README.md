# Submecha

This is a game that was created as part of an assignment for my Game Development module. We were given a Java based engine for a top-down space shooter and were required to expand it to fit a given list of criteria such as: changing the theme, having multiple levels, on screen buttons, sounds effects, etc. This game met all the 
requirements and received  an A+. 

In Submecha, you play as a submarine with a limited vision circle and enemies lurking in the water around you. You can scan the environment to reveal enemy locations however, this also exposes you to them. There are 3 levels of the game to work through each progressing in difficulty. When all enemies are destroyed you progress to the 
next level. All enemies can be destroyed with one hit however the same goes for the player.

### Key Features and Additions

The initial engine we were given used only a single coordinate point to represent the player and was only capable of moving along one axis at a time. One of the first changes I made was to add an additional point at the front of the submarine. This allowed me to represent the submarine as a vector and thus apply rotation to it. With this rotation the submarine can now move in diagonal directions and
can pitch up and down to aim torpedoes.

Some basic enemy logic was added. In the original engine the enemies simply moved from the top of the screen to the bottom. Now they remain static but also have the ability to return fire. If an enemy has been scanned by the player, they can fire 2-3 torpedoes at the location of the player. To prevent players from not using the scan and just using
the vision cone to search for enemies, enemy subs will also fire if the player is within a certain proximity of them.

To meet the requirements, on screen buttons for the "sonar scan" and "fire torpedo" buttons were added. They player can click the on screen buttons or use "Q" and "Space" respectively. 

3 levels were added to the game. To increase the difficulty, each level has an increasing number of enemies. Level 3 also adds extra terrain to the level which can allow both the player and 
enemies to hide behind rocks. 

### Video and Screenshots

[Submecha demonstration](https://www.youtube.com/watch?v=iiFN8T8SLkA)
![Submecha Title screen](https://user-images.githubusercontent.com/62139085/130854465-360e6239-c32c-4834-b911-7089315df20e.png)
![Submecha Controls](https://user-images.githubusercontent.com/62139085/130854872-7a92139d-0616-43ea-935f-01834ac32568.png)
![Submecha1](https://user-images.githubusercontent.com/62139085/130855089-8bc020d2-119e-40ae-9c8c-dacd293a32ef.png)
![Submecha2](https://user-images.githubusercontent.com/62139085/130855322-7ee78ebc-3085-41fd-b01c-f458f9332cb7.png)
![Submecha3](https://user-images.githubusercontent.com/62139085/130855618-b7546fe8-cc9c-49cd-9f76-ab58645da0f4.png)


