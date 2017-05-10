package com.github.danielfernandez.matchday.data;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import com.github.danielfernandez.matchday.business.Match;
import com.github.danielfernandez.matchday.business.MatchEvent;
import com.github.danielfernandez.matchday.business.Player;
import com.github.danielfernandez.matchday.business.Team;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * Class containing the test data that will be used, as well as the logic required to
 * insert it into MongoDB at application startup.
 */
public class Data {

    private static final String LOGGER_INITIALIZE = Data.class.getName() + ".INITIALIZE";


    // This list has to have an even number of elements, in order to form matches with them
    public static final List<Team> TEAMS =
            Arrays.asList(
                    new Team("SPC", "Spearmint Caterpillars"),
                    new Team("BAD", "Basil Dragons"),
                    new Team("SPS", "Sweet Paprika Savages"),
                    new Team("PAW", "Parsley Warriors"),
                    new Team("PCO", "Polar Corianders"),
                    new Team("CSA", "Cinnamon Sailors"),
                    new Team("LTR", "Laurel Troglodytes"),
                    new Team("ARP", "Angry Red Peppers"),
                    new Team("ROS", "Rosemary 75ers"),
                    new Team("SHU", "Saffron Hunters"));


    // These are the players that will be responsible for match events
    public static final List<Player> PLAYERS =
            Arrays.asList(
                    new Player("SPS","Tashia Tomato"),
                    new Player("SPC","Hyun Peach"),
                    new Player("SPC","Londa Apple"),
                    new Player("ARP","Lelah Avocado"),
                    new Player("ROS","Manuela Blueberry"),
                    new Player("ROS","Dannie Blackberry"),
                    new Player("SPS","Chan Starfruit"),
                    new Player("BAD","Zandra Tangerine"),
                    new Player("CSA","Ahmed Orange"),
                    new Player("BAD","Pasquale Papaya"),
                    new Player("CSA","Niesha Papaya"),
                    new Player("SHU","Mirella Fig"),
                    new Player("PAW","Ahmed Pomegranate"),
                    new Player("ROS","Stephaine Blueberry"),
                    new Player("PAW","Chana Kumquat"),
                    new Player("ROS","Nevada Honeydew"),
                    new Player("ROS","Chana Jujube"),
                    new Player("PCO","Hellen Fig"),
                    new Player("BAD","Zandra Nectarine"),
                    new Player("SHU","Angela Plum"),
                    new Player("BAD","Beryl Strawberry"),
                    new Player("CSA","Dannie Kiwi"),
                    new Player("ROS","Dania Dragonfruit"),
                    new Player("ARP","Niesha Mango"),
                    new Player("SPS","Tashia Jujube"),
                    new Player("ROS","Zandra Peach"),
                    new Player("SPC","Rebbecca Mango"),
                    new Player("PAW","Rickie Watermelon"),
                    new Player("SPC","Wayne Pomegranate"),
                    new Player("CSA","Mirella Orange"),
                    new Player("PCO","Ahmed Plum"),
                    new Player("SPC","Tashia Satsuma"),
                    new Player("BAD","Angela Grapefruit"),
                    new Player("SPS","Larue Blueberry"),
                    new Player("BAD","Linh Grape"),
                    new Player("SPC","Niesha Huckleberry"),
                    new Player("SPS","Lanie Satsuma"),
                    new Player("ROS","Luann Cantaloupe"),
                    new Player("PAW","Lelah Guava"),
                    new Player("SPS","Jerry Coconut"),
                    new Player("SPS","Oliver Papaya"),
                    new Player("ARP","Pasquale Honeydew"),
                    new Player("LTR","Rebbecca Pear"),
                    new Player("PAW","Larue Grapefruit"),
                    new Player("ROS","Lanie Pineapple"),
                    new Player("SPC","Manuel Blackberry"),
                    new Player("BAD","Terresa Pineapple"),
                    new Player("ARP","Hellen Grapefruit"),
                    new Player("ARP","Kellee Apple"),
                    new Player("CSA","Pennie Pomegranate"),
                    new Player("SPS","Londa Avocado"),
                    new Player("ROS","Rebbecca Strawberry"),
                    new Player("PAW","Lelah Tangerine"),
                    new Player("SPS","Pansy Huckleberry"),
                    new Player("PAW","Niesha Cherry"),
                    new Player("LTR","Pansy Passonfruit"),
                    new Player("CSA","Wayne Papaya"),
                    new Player("CSA","Dortha Passonfruit"),
                    new Player("CSA","Kellee Watermelon"),
                    new Player("SPS","Lucie Cantaloupe"),
                    new Player("LTR","Dortha Jujube"),
                    new Player("ROS","Hyun Huckleberry"),
                    new Player("PCO","Benton Pomegranate"),
                    new Player("PAW","Lelah Nectarine"),
                    new Player("CSA","Nevada Pineapple"),
                    new Player("SHU","Margurite Tangerine"),
                    new Player("SPC","Mirella Pineapple"),
                    new Player("BAD","Pasquale Plum"),
                    new Player("PCO","Iluminada Starfruit"),
                    new Player("CSA","Eliana Pineapple"),
                    new Player("ARP","Margurite Grape"),
                    new Player("ROS","Linh Honeydew"),
                    new Player("PAW","Hyun Starfruit"),
                    new Player("ROS","Terresa Lemon"),
                    new Player("BAD","Benton Lemon"),
                    new Player("SPS","Dania Banana"),
                    new Player("SPC","Jerry Jujube"),
                    new Player("SPS","Luann Lemon"),
                    new Player("PCO","Beryl Fig"),
                    new Player("PCO","Stephaine Blackberry"),
                    new Player("SHU","Benton Banana"),
                    new Player("ROS","Rickie Pear"),
                    new Player("SHU","Hellen Coconut"),
                    new Player("PCO","Benton Satsuma"),
                    new Player("SPS","Rickie Grape"),
                    new Player("SPC","Angela Dragonfruit"),
                    new Player("ROS","Stephaine Pear"),
                    new Player("PCO","Chana Date"),
                    new Player("ARP","Jerry Nectarine"),
                    new Player("LTR","Londa Raspberry"),
                    new Player("SPC","Janelle Date"),
                    new Player("SPC","Dannie Grapefruit"),
                    new Player("SPC","Janelle Kumquat"),
                    new Player("SHU","Manuel Apricot"),
                    new Player("PAW","Eliana Lemon"),
                    new Player("SPS","Annalisa Lime"),
                    new Player("BAD","Annalisa Apricot"),
                    new Player("LTR","Zandra Tomato"),
                    new Player("BAD","Ahmed Peach"),
                    new Player("LTR","Stephaine Dragonfruit"),
                    new Player("LTR","Lucie Grape"),
                    new Player("PCO","Iluminada Blueberry"),
                    new Player("PAW","Oliver Lime"),
                    new Player("ARP","Mirella Clementine"),
                    new Player("CSA","Chan Cherry"),
                    new Player("SHU","Janelle Clementine"),
                    new Player("ARP","Margurite Pear"),
                    new Player("SHU","Eliana Strawberry"),
                    new Player("LTR","Chan Banana"),
                    new Player("SHU","Terresa Tangerine"),
                    new Player("ROS","Oliver Banana"),
                    new Player("SPS","Mirella Lime"),
                    new Player("LTR","Margurite Cherry"),
                    new Player("PAW","Stephaine Starfruit"),
                    new Player("SHU","Dania Avocado"),
                    new Player("SPS","Jerry Tomato"),
                    new Player("PCO","Annalisa Honeydew"),
                    new Player("ROS","Kellee Huckleberry"),
                    new Player("BAD","Janelle Pear"),
                    new Player("LTR","Hyun Clementine"),
                    new Player("SHU","Manuela Peach"),
                    new Player("LTR","Dannie Apricot"),
                    new Player("BAD","Kellee Passonfruit"),
                    new Player("BAD","Hellen Grape"),
                    new Player("LTR","Manuela Fig"),
                    new Player("PAW","Manuel Apricot"),
                    new Player("PCO","Larue Blackberry"),
                    new Player("ROS","Terresa Kiwi"),
                    new Player("ARP","Dania Strawberry"),
                    new Player("SHU","Eliana Apple"),
                    new Player("ARP","Pennie Coconut"),
                    new Player("SPS","Pennie Fig"),
                    new Player("ARP","Pansy Boysenberry"),
                    new Player("LTR","Angela Guava"),
                    new Player("SHU","Dania Coconut"),
                    new Player("ARP","Pasquale Kumquat"),
                    new Player("PCO","Wayne Nectarine"),
                    new Player("BAD","Londa Tangerine"),
                    new Player("SHU","Tashia Tomato"),
                    new Player("LTR","Lanie Guava"),
                    new Player("CSA","Rebbecca Mango"),
                    new Player("PAW","Hyun Blackberry"),
                    new Player("SHU","Beryl Plum"),
                    new Player("PCO","Rickie Peach"),
                    new Player("SPS","Luann Apple"),
                    new Player("SPC","Manuel Boysenberry"),
                    new Player("BAD","Nevada Blueberry"),
                    new Player("PCO","Eliana Pomegranate"),
                    new Player("SPC","Chan Banana"),
                    new Player("CSA","Luann Dragonfruit"),
                    new Player("PCO","Londa Mango"),
                    new Player("LTR","Ahmed Cantaloupe"),
                    new Player("SPC","Annalisa Clementine"),
                    new Player("SPC","Annalisa Nectarine"),
                    new Player("SPS","Linh Watermelon"),
                    new Player("PCO","Pansy Date"),
                    new Player("ARP","Linh Plum"),
                    new Player("PCO","Jerry Orange"),
                    new Player("PCO","Wayne Strawberry"),
                    new Player("BAD","Manuel Dragonfruit"),
                    new Player("SHU","Luann Raspberry"),
                    new Player("SPC","Lanie Mango"),
                    new Player("CSA","Oliver Clementine"),
                    new Player("SHU","Janelle Lime"),
                    new Player("PAW","Manuela Avocado"),
                    new Player("CSA","Nevada Date"),
                    new Player("PAW","Terresa Tomato"),
                    new Player("ARP","Rebbecca Apricot"),
                    new Player("PCO","Dortha Watermelon"),
                    new Player("PCO","Dortha Raspberry"),
                    new Player("CSA","Hellen Avocado"),
                    new Player("CSA","Nevada Papaya"),
                    new Player("ARP","Angela Apple"),
                    new Player("LTR","Oliver Grapefruit"),
                    new Player("PAW","Manuela Lemon"),
                    new Player("BAD","Beryl Passonfruit"),
                    new Player("CSA","Lucie Starfruit"),
                    new Player("SPC","Chana Kumquat"),
                    new Player("ARP","Chan Kiwi"),
                    new Player("ROS","Pennie Coconut"),
                    new Player("CSA","Larue Cantaloupe"),
                    new Player("PAW","Beryl Cherry"),
                    new Player("LTR","Iluminada Satsuma"),
                    new Player("ARP","Larue Huckleberry"),
                    new Player("SHU","Wayne Guava"),
                    new Player("BAD","Dortha Date"),
                    new Player("SHU","Zandra Cantaloupe"),
                    new Player("CSA","Iluminada Watermelon"),
                    new Player("LTR","Linh Kiwi"),
                    new Player("SHU","Tashia Satsuma"),
                    new Player("LTR","Lanie Passonfruit"),
                    new Player("PAW","Lucie Orange"),
                    new Player("ROS","Chana Kiwi"),
                    new Player("LTR","Margurite Boysenberry"),
                    new Player("SPC","Niesha Raspberry"),
                    new Player("SPS","Pansy Cherry"),
                    new Player("ARP","Rickie Boysenberry"),
                    new Player("ARP","Dannie Honeydew"),
                    new Player("PAW","Kellee Boysenberry"),
                    new Player("BAD","Pasquale Guava"));

    
    
    
    public static void initializeAllData(final ReactiveMongoTemplate mongoTemplate) {
        
        /*
         *  Drop collections, then create them again
         */
        final Mono<Void> initializeCollections =
                mongoTemplate
                        .dropCollection(Team.class)
                        .then(mongoTemplate.dropCollection(Match.class))
                        .then(mongoTemplate.dropCollection(Player.class))
                        .then(mongoTemplate.dropCollection(MatchEvent.class))
                        .then(mongoTemplate.createCollection(Team.class))
                        .then(mongoTemplate.createCollection(Match.class))
                        .then(mongoTemplate.createCollection(Player.class))
                        .then(mongoTemplate.createCollection(MatchEvent.class))
                        .then();

        /*
         * Add some test data to the collections: teams and players will come from the
         * utility Data class, but we will generate matches between teams randomly each
         * time the application starts (for the fun of it)
         */
        final Mono<Void> initializeData =
                mongoTemplate
                        // Insert all the teams into the corresponding collection and log
                        .insert(Data.TEAMS, Team.class)
                        .log(LOGGER_INITIALIZE, Level.FINEST)
                        // Collect all inserted team codes and randomly shuffle the list
                        .map(Team::getCode).collectList().doOnNext(Collections::shuffle)
                        .flatMapMany(list -> Flux.fromIterable(list))
                        // Create groups of two teams and insert a new Match for them
                        .buffer(2).map(twoTeams -> new Match(twoTeams.get(0), twoTeams.get(1)))
                        .flatMap(mongoTemplate::insert)
                        .log(LOGGER_INITIALIZE, Level.FINEST)
                        // Finally insert the players into their corresponding collection
                        .thenMany(Flux.fromIterable(Data.PLAYERS))
                        .flatMap(mongoTemplate::insert)
                        .log(LOGGER_INITIALIZE, Level.FINEST)
                        .then();


        /*
         * Perform the initialization, blocking (that's OK, we are bootstrapping a testing app)
         */
        initializeCollections.then(initializeData).block();
        
    }
    


    private Data() {
        super();
    }

}
