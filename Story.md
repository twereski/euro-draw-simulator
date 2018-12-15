Story: Simulation of draws of euro elimination groups 2020

As a user
I wont to run draw simulation that will be consistent with 
UEFA draw rules.

Scenario 1: Run simulation
Given pots with teams according uefa ranking
 And empty groups where team will be put after draw
When the simulation run
Then every group is full (according group capacity)
 And pots are empty
 And competition-related reasons role has been fulfilled
 And prohibited team clashes role has been fulfilled
 And winter venue restrictions role has been fulfilled
 And excessive travel restriction role has been fulfilled
 