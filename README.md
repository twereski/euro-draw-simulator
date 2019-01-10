# euro-draw-simulator
Qualifiers for Euro 2020 draw simulator - TDD and BDD practice

# Goal
Implement draw algorithm using TDD/BDD technique of software development. 
Complete application should allow simulate draw procedure.

## Behavior-driven development/Test-driven development
_Behavior-driven development (BDD) is a software development methodology in which an application is specified 
and designed by describing how its behavior should appear to an outside observer._
1. Business Rules are store in story (./Story.md)
2. The story is converted into a test scenario which is then the base for building 
spock test (com.example.draw.SimulationFacadeTest)
3. Test coven only module facade (com.example.draw.SimulationFacade) which is an API for domain 
and only place where can interact with domain. 
4. TDD - Gradual completion of the test with a code. Run test / Write code / Run Test / Refactor ..
Important: Either test or code must be in proper state. Do not modify code and test in the same time. 
  
_TDD and BDD is that they are not testing techniques. They are about improving the design process._

## Spock
Test framework where very easy is to use domain-specific language (DSL) with natural language constructs.

```
when: "the simulation is running according to procedure"
facade.run()
then: "every group should be full (according group capacity)"
configuration.getGroupRepository().findAll().each { assert !it.hasFreePlaces() }
```
## DDD
In story, test and code is used the same ubiquitous language. Domain code is distilled and independent from
the rest of system. 
Ports and adapters: PotRepository as port and InMemoryPotRepository as in-memory adapter outside domain.

# Draw conditions

Regulations source: https://www.uefa.com/MultimediaFiles/Download/uefaorg/General/02/57/32/05/2573205_DOWNLOAD.pdf

1.	The teams are divided into 7 pots on the basis of the Overall UEFA Nations League Rankings issued on 21 November 2018.
2.	10 groups; 5 groups of 5 teams (Groups A-E) and 5 groups of 6 teams (Groups F-J);
3.	Draw conditions apply for "competition-related reasons", "prohibited team clashes", "winter venue restrictions" 
and "excessive travel restrictions"
4.	If a team is or could be drawn into a group which already contains two such teams, that team is placed in the 
first available group in alphabetical order as indicated by the computer.


