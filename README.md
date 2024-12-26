##Desktop Java game using Swing. Rules are simple:
• there is an N×N spider web;
• there are K spiders on the web, one of which is controlled by a person;
• the spider moves along the thread of the web; the game is played step by step;
• the spider's life is L units; at each step the life decreases by 1;
• insects can randomly get into the web;
• an insect can escape from the web with some probability;
• when an insect is eaten, the spider's life is restored by some amount;
• the goal of the game is for the spiders to not die as long as possible; the spider dies if its life decreases to zero;
• when the spider that the user has control over dies, another spider is assigned to it and the game continues;
• minimum requirements for the intelligence of the computer player: the spider moves to the nearest insect that it can reach. If there are several such insects, then the choice is made randomly.

**Additional requirements:**
• display the current life of each spider;
• display the fact that the insect has been eaten;
• display the number of steps taken.
• it is necessary to provide extension points in the program, using which it is possible to implement the variable part of the program (in addition to the basic functionality).

**Variability:**
• creation of insect varieties with different appearance, size, way of getting into the web and the ability to escape from the web. Also, the type of insect affects the life of the spider in different ways.

**Implement:**
• "grasshoppers", "flies", "mosquitoes" and "wasps" can get into the web. The larger the insect, the less often it gets into the web;
• the size of the insect determines how likely it is to escape from the web;
• the size of the insect determines how much it restores the life of the spider; "wasps" can sometimes bite, then the life of the spider is reduced;
• the grasshopper can jump along the web (break out and get back in, but in a different position), but each time the probability of breaking out of the web decreases.
