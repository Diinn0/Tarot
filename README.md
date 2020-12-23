# Mystic Tarot #

## I.	Modelization ##
For this project, two entities are created: Card and CardList.
Card have an id that represent its number. For example: I. Le Bateleur.
Card have also name, image and lore that represent its description.
CardList represent a deck. It contains an Array List of cards, a name and useful methods to draw and search cards.
There are two JFrame used. For the main application and for form to add a card.

## II.	Saving ##
Cards saving is working with Gson. CardList entity are saved because this entity contains all cards. So, Cards are serialized in Json. Image are serialized too. This make save easily share because save file contains all images.

To be more precise, Buffered Image are serialized in Base64. This Base64 String are deserialized into Buffered Image when save are loaded.

## III.	Utils ##
Some file is used to facilitate the program.
We can find filter, for JFileChooser (image and json files).
We can also find an integer to Roman transposer, for Card naming.
And finally, an adapter to give information to Gson for serialize BufferedImage.
 

_Note: This code isn't finished yet that means it working but form verifications are not done. Reading of the future isn't done also. Consider this is an Alpha_
