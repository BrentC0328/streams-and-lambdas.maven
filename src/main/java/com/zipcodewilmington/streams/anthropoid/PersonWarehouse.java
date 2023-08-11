package com.zipcodewilmington.streams.anthropoid;

import com.zipcodewilmington.streams.tools.ReflectionUtils;
import com.zipcodewilmington.streams.tools.logging.LoggerHandler;
import com.zipcodewilmington.streams.tools.logging.LoggerWarehouse;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 5/29/17.
 * The warehouse is responsible for storing, retrieving, and filtering personSequence
 *
 * @ATTENTION_TO_STUDENTS You are FORBIDDEN from using loops of any sort within the definition of this class.
 */
public final class PersonWarehouse implements Iterable<Person> {
    private final LoggerHandler loggerHandler = LoggerWarehouse.getLogger(PersonWarehouse.class);
    private final List<Person> people = new ArrayList<>();

    /**
     * @param person the Person object to add to the warehouse
     * @ATTENTION_TO_STUDENTS You are FORBIDDEN from modifying this method
     */
    public void addPerson(Person person) {
        loggerHandler.disbalePrinting();
        loggerHandler.info("Registering a new person object to the person warehouse...");
        loggerHandler.info(ReflectionUtils.getFieldMap(person).toString());
        people.add(person);
    }

    /**
     * @return list of names of Person objects
     */ // TODO
    public List<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        people.stream().forEach(p -> names.add(p.getName()));
        return names;
    }


    /**
     * @return list of uniquely named Person objects
     */ //TODO
    public Stream<Person> getUniquelyNamedPeople() {
        //We make a HashMap because HashMaps only allow unique keys.
        Map<String, Person> filterThePeopleMap = new HashMap<>();

        //The filter in this stream is a lambda. It takes each person in the List people and streams them into the map I created
        //The way the filter works is by using putIfAbsent. maps.putIfAbsent will return null if the person is not found in the map already.
        //It will return a person if the person is found.
        //So, if the putIfAbsent method returns null, it means it has not found a person in the spot
        //and that it passes the filter condition, and the lambda will finish through and add that person to the map.
        Stream<Person> result = people.stream().filter(person -> filterThePeopleMap

                .putIfAbsent(person.getName(), person) == null);


        return result;
    }
    /**
     * @param character starting character of Person objects' name
     * @return a Stream of respective
     */ //TODO
    public Stream<Person> getUniquelyNamedPeopleStartingWith(Character character) {
        Map<String, Person> filterThePeopleMap = new HashMap<>();

        return getUniquelyNamedPeople().filter(person -> filterThePeopleMap.putIfAbsent(person.getName(), person) == null
                && person.getName().indexOf(character) == 0);
    }

    /**
     * @param n first `n` Person objects
     * @return a Stream of respective
     */ //TODO
    public Stream<Person> getFirstNUniquelyNamedPeople(int n) {
        Map<String, Person> filterThePeopleMapAgain = new HashMap<>();

        return  people.stream().filter(person -> filterThePeopleMapAgain.putIfAbsent(person.getName(), person) == null
        && filterThePeopleMapAgain.size() <= n);
    }

    /**
     * @return a mapping of Person Id to the respective Person name
     */ // TODO
    public Map<Long, String> getIdToNameMap() {
        Map<Long, String> personIdandNameMap = new HashMap<>();

        people.stream().forEach(person -> personIdandNameMap.put(person.getPersonalId(), person.getName()));


        return personIdandNameMap;
    }


    /**
     * @return Stream of Stream of Aliases
     */ // TODO
    public Stream<Stream<String>> getNestedAliases() {
        //Aliases is a String[].
        //Want to stream all the aliases
        //Then put that stream in a stream
        //So you can Stream while you Stream?
        //We'll be back after these messages

        return null;
    }


    /**
     * @return Stream of all Aliases
     */ // TODO
    public Stream<String> getAllAliases() {
        return null;
    }

    // DO NOT MODIFY
    public Boolean contains(Person p) {
        return people.contains(p);
    }

    // DO NOT MODIFY
    public void clear() {
        people.clear();
    }

    // DO NOT MODIFY
    public int size() {
        return people.size();
    }

    @Override // DO NOT MODIFY
    public Iterator<Person> iterator() {
        return people.iterator();
    }
}
