package com.ca.formation.formationdemo1.repositories;

import com.ca.formation.formationdemo1.models.Personne;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PersonneRepositoryTest {

  @Autowired
  PersonneRepository personneRepository;

  @Test
  public void ajouterPersonne() {
    Personne personne = personneRepository.save(new Personne("tonux", "samb", 50));
    assertNotNull(personne);
    assertEquals("tonux",personne.getNom() );
  }

  @Test
  public void findbyid(){
    //Given
    Personne pers = personneRepository.save(new Personne("seck", "baye", 24));
    //When
    Optional<Personne> person = personneRepository.findById(pers.getId());
    //Then
    Assertions.assertNotNull(person);
    assertEquals("seck", person.get().getNom());
  }

  @Test
  public void update(){
    //Given
    Personne person = personneRepository.save(new Personne("tonux", "samb", 50));
    person.setPrenom("Coundoul");
    //When
    Personne personUpdated = personneRepository.save(person);
    //Then
    Assertions.assertNotNull(personUpdated);
    assertEquals("Coundoul", personUpdated.getPrenom());
  }
  @Test
  public void findAll(){
    List<Personne> list = new ArrayList<Personne>();
    list.add(new Personne("tonux", "samb", 50));
    list.add(new Personne("lahad","mbacke",24));
    list.add(new Personne("baye","seck",24));
    list.add(new Personne("moussa","diop",25));
    list.add(new Personne("marieme","aidara",24));
    personneRepository.saveAll(list);

    List<Personne> personList = (List<Personne>) personneRepository.findAll();

    org.assertj.core.api.Assertions.assertThat(personList.size()).isPositive();
  }
  @Test
  public void deletePerson(){
    Personne person  =  personneRepository.save(new Personne("baye","seck",24));
    //Personne person = personneRepository.findById(1L).get();
    personneRepository.delete(personneRepository.findById(person.getId()).get());

    Personne person1 = null;

    Optional<Personne> person2 = personneRepository.findById(person.getId());

    if (person2.isPresent()){
      person1 = person2.get();
    }
    org.assertj.core.api.Assertions.assertThat(person1).isNull();
  }
  @Test
  public void findbyNom(){

    List<Personne> person = personneRepository.findByNom("Abdel");
    Assertions.assertNotNull(person);
    assertNotEquals(0, person.size());
  }

}
