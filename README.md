# Advent-of-Code-2020
Just using Scala, avoiding procedural tedium.

I use `repl.sh` to run `Main.scala` with `sbt` to test solutions as they are edited.

So far my best rank was **794** for day 3 part 1, but strangely this was also the only day where my part 2 rank of **1074** was worse than my part 1 rank...
Another interesting result was day 12. I only got rank **2500** for part 1, but then I cut a bunch of people and got **949** for part 2. This is probably because I had already used a unit vector for the ship orientation, so I only had to change a few rules to use it as the waypoint.

I am looking for ways to improve the performance of days 7 and 11. For day 7, the given challenge completes quickly, but the community "big boy" input turns my laptop to mush for a few minutes. For day 11, the program seems very straightforward, and it should run in O(n), but it crawls through each step. If you happen to have an eye for scala bottlenecks, please let me know what you think.
